package tr.com.obssintern.book_portal.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
