package tr.com.obssintern.book_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewUserRequest {

    @NotNull
    @Size(min = 3, max = 16)
    private String username;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String password;

    private Integer age;

}
