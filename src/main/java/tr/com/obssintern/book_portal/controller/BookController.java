package tr.com.obssintern.book_portal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.com.obssintern.book_portal.dto.BookDto;
import tr.com.obssintern.book_portal.dto.CreateNewBookRequest;
import tr.com.obssintern.book_portal.service.BookService;
import tr.com.obssintern.book_portal.service.StringService;
import tr.com.obssintern.book_portal.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    private final UserService userService;

    private final StringService stringService;

    @GetMapping("/")
    public List<BookDto> getAllBooks(@RequestBody Map<String, String> pagination) {
        return bookService.getAllBooks(pagination.get("current"), pagination.get("pageSize"));
    }

    @GetMapping("/{name}")
    public BookDto getBookByName(@PathVariable(value = "name") String name) {
        return bookService.findByName(stringService.properPathVariable(name));
    }

    @PutMapping("/admin")
    public void updateBook(@RequestBody CreateNewBookRequest newBook) {
        bookService.update(newBook);
    }

    @PostMapping("/admin")
    public void createBook(@RequestBody @Valid CreateNewBookRequest createNewBookRequest) {
        bookService.createNewBook(createNewBookRequest);
    }

    @DeleteMapping("/admin")
    public void deleteBookByIsbn(@RequestParam String isbn) {
        bookService.deleteByIsbn(isbn);
    }

    @GetMapping("/favourites/{username}")
    public List<BookDto> getFavourites(@PathVariable String username) {
        return userService.getFavourites(username);
    }

    @PostMapping("/favourites")
    public void addToFavourites(@RequestBody Map<String, String> json) {
        userService.addToFavourites(json.get("username"), json.get("isbn"));
    }

    @DeleteMapping("/favourites")
    public void removeFromFavourites(@RequestBody Map<String, String> json) {
        userService.removeFromFavourites(json.get("username"), json.get("isbn"));
    }

    @GetMapping("/read_list/{username}")
    public List<BookDto> getReadList(@PathVariable String username) {
        return userService.getReadList(username);
    }

    @PostMapping("/read_list")
    public void addToFReadList(@RequestBody Map<String, String> json) {
        userService.addToReadList(json.get("username"), json.get("isbn"));
    }

    @DeleteMapping("/read_list")
    public void removeFromReadList(@RequestBody Map<String, String> json) {
        userService.removeFromReadList(json.get("username"), json.get("isbn"));
    }
}
