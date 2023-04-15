package tr.com.obssintern.book_portal.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import tr.com.obssintern.book_portal.dto.AuthorDto;
import tr.com.obssintern.book_portal.model.Author;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AuthorMapper {

    AuthorDto mapTo(Author author);

    Author mapToEntity(AuthorDto authorDto);
}
