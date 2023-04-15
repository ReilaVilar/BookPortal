package tr.com.obssintern.book_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.obssintern.book_portal.model.Author;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookDto {

    private String name;

    private String isbn;

    private String link;

    private Author author;
}
