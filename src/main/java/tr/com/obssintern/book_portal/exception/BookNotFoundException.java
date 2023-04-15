package tr.com.obssintern.book_portal.exception;

public class BookNotFoundException extends BaseException {
    public BookNotFoundException() {
        super("Book not found");
    }
}
