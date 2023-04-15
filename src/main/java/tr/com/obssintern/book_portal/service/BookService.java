package tr.com.obssintern.book_portal.service;

import tr.com.obssintern.book_portal.dto.BookDto;
import tr.com.obssintern.book_portal.dto.CreateNewBookRequest;
import tr.com.obssintern.book_portal.model.Book;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks(String current, String pageSize);

    void createNewBook(CreateNewBookRequest createNewBookRequest);

    boolean deleteByIsbn(String isbn);

    void deleteAllByAuthor_Name(String authorName);

    void update(CreateNewBookRequest newBook);

    BookDto findByName(String name);

    Book findByIsbn(String isbn);

    List<BookDto> findAllByAuthor_Name(String authorName);

    Book assignAuthor(Book book, String authorName);

}
