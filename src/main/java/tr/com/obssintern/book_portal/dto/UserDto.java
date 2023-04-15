package tr.com.obssintern.book_portal.dto;

import lombok.*;
import tr.com.obssintern.book_portal.model.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 16)
    private String username;

    @NotNull(message = "Email is required")
    private String email;

    private Integer age;

    private List<Role> roles;

}
