package tr.com.obssintern.book_portal.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntityDto {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
