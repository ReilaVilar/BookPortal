package tr.com.obssintern.book_portal.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.obssintern.book_portal.dto.AuthorDto;
import tr.com.obssintern.book_portal.model.Author;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-12T01:38:27+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDto mapTo(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setName( author.getName() );

        return authorDto;
    }

    @Override
    public Author mapToEntity(AuthorDto authorDto) {
        if ( authorDto == null ) {
            return null;
        }

        Author author = new Author();

        author.setName( authorDto.getName() );

        return author;
    }
}
