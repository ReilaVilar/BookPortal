package tr.com.obssintern.book_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateNewBookRequest {

    private String name;

    private String isbn;

    private String link;

    private String authorName;

}
