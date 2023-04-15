package tr.com.obssintern.book_portal.exception;

public class BookExistException extends BaseException {
    public BookExistException() {
        super("Book already exist");
    }
}
