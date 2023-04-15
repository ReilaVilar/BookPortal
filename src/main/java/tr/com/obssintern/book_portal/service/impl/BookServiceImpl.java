package tr.com.obssintern.book_portal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.obssintern.book_portal.dto.AuthorDto;
import tr.com.obssintern.book_portal.dto.BookDto;
import tr.com.obssintern.book_portal.dto.CreateNewBookRequest;
import tr.com.obssintern.book_portal.exception.BookExistException;
import tr.com.obssintern.book_portal.exception.BookNotFoundException;
import tr.com.obssintern.book_portal.mapper.BookMapper;
import tr.com.obssintern.book_portal.model.Author;
import tr.com.obssintern.book_portal.model.Book;
import tr.com.obssintern.book_portal.repository.AuthorRepository;
import tr.com.obssintern.book_portal.repository.BookRepository;
import tr.com.obssintern.book_portal.service.AuthorService;
import tr.com.obssintern.book_portal.service.BaseEntityService;
import tr.com.obssintern.book_portal.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BaseEntityService baseEntityService;

    private final AuthorService authorService;

    private final AuthorRepository authorRepository;

    private final BookMapper bookMapper;


    @Override
    public List<BookDto> getAllBooks(String current, String pageSize) {
        final Pageable pageRequest = PageRequest.of(Integer.parseInt(current), Integer.parseInt(pageSize));
        final Iterable<Book> allBooks = bookRepository.findByOrderByNameAsc(pageRequest);
        List<BookDto> retList = new ArrayList<>();
        allBooks.forEach(book -> retList.add(bookMapper.mapTo(book)));
        return retList;
    }

    @Override
    public void createNewBook(CreateNewBookRequest createNewBookRequest) {
        Objects.requireNonNull(createNewBookRequest, "Book cannot be null");

        final Book book = bookMapper.mapToEntity(createNewBookRequest);
        baseEntityService.nowCreated(book);
        baseEntityService.nowUpdated(book);

        Optional<Book> existBook = bookRepository.findBookByIsbn(createNewBookRequest.getIsbn());
        if (existBook.isPresent()) {
            throw new BookExistException();
        }
        final Book finalBook = this.assignAuthor(book, createNewBookRequest.getAuthorName());
        bookRepository.save(finalBook);
    }

    @Override
    public boolean deleteByIsbn(String isbn) {
        bookRepository.deleteBookByIsbn(isbn);
        return Boolean.TRUE;
    }

    @Override
    public void deleteAllByAuthor_Name(String authorName) {
        bookRepository.deleteAllByAuthor_Name(authorName);
    }

    @Override
    public void update(CreateNewBookRequest newBook) {
        final Book book = this.findByIsbn(newBook.getIsbn());
        book.setName(newBook.getName());
        book.setLink(newBook.getLink());
        this.assignAuthor(book, newBook.getAuthorName());
        baseEntityService.nowUpdated(book);
        bookRepository.save(book);
    }

    @Override
    public BookDto findByName(String name) {
        final Book book = bookRepository.findBookByName(name).orElseThrow(BookNotFoundException::new);
        return bookMapper.mapTo(book);
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<BookDto> findAllByAuthor_Name(String authorName) {
        List<Book> allBooks = bookRepository.findAllByAuthor_Name(authorName);
        List<BookDto> retList = new ArrayList<>();
        allBooks.forEach(book -> retList.add(bookMapper.mapTo(book)));
        return retList;
    }

    @Override
    public Book assignAuthor(Book book, String authorName) {
        Optional<Author> existAuthor = authorRepository.findAuthorByName(authorName);

        if (existAuthor.isPresent()) {
            final Author author = authorService.findByName(authorName);
            book.setAuthor(author);
        } else {
            AuthorDto newAuthor = new AuthorDto();
            newAuthor.setName(authorName);
            authorService.createNewAuthor(newAuthor);
            book.setAuthor(authorService.findByName(authorName));
        }
        return book;
    }
}
