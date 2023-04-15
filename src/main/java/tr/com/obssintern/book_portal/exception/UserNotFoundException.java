package tr.com.obssintern.book_portal.exception;


public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("User not found");
    }
}
