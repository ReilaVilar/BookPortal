package tr.com.obssintern.book_portal.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.obssintern.book_portal.dto.BookDto;
import tr.com.obssintern.book_portal.dto.CreateNewBookRequest;
import tr.com.obssintern.book_portal.model.Author;
import tr.com.obssintern.book_portal.model.Book;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-12T05:45:22+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto mapTo(Book book) {
        if ( book == null ) {
            return null;
        }

        String name = null;
        String isbn = null;
        String link = null;
        Author author = null;

        name = book.getName();
        isbn = book.getIsbn();
        link = book.getLink();
        author = book.getAuthor();

        BookDto bookDto = new BookDto( name, isbn, link, author );

        return bookDto;
    }

    @Override
    public Book mapToEntity(CreateNewBookRequest createNewBookRequest) {
        if ( createNewBookRequest == null ) {
            return null;
        }

        Book book = new Book();

        book.setName( createNewBookRequest.getName() );
        book.setIsbn( createNewBookRequest.getIsbn() );
        book.setLink( createNewBookRequest.getLink() );

        return book;
    }
}
