package tr.com.obssintern.book_portal.exception;

public class AuthorNotFoundException extends BaseException {
    public AuthorNotFoundException() {
        super("Author not found");
    }
}
