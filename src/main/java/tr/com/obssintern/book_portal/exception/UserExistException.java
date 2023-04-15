package tr.com.obssintern.book_portal.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserExistException extends BaseException {
    public UserExistException(String attribute, String value) {
        super(String.format("This %s already exist: %s", attribute, value));
    }
}
