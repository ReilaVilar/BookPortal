package tr.com.obssintern.book_portal.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tr.com.obssintern.book_portal.dto.BookDto;
import tr.com.obssintern.book_portal.dto.CreateNewBookRequest;
import tr.com.obssintern.book_portal.model.Book;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BookMapper {

    BookDto mapTo(Book book);

    @Mapping(ignore = true, target = "author")
    Book mapToEntity(CreateNewBookRequest createNewBookRequest);
}
