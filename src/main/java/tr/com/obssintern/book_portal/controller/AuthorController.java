package tr.com.obssintern.book_portal.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.com.obssintern.book_portal.dto.AuthorDto;
import tr.com.obssintern.book_portal.model.Author;
import tr.com.obssintern.book_portal.service.AuthorService;
import tr.com.obssintern.book_portal.service.BookService;
import tr.com.obssintern.book_portal.service.StringService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    private final BookService bookService;

    private final StringService stringService;

    @GetMapping("/")
    public List<AuthorDto> getAllAuthors(@RequestBody Map<String, String> pagination) {
        return authorService.getAllAuthors(pagination.get("current"), pagination.get("pageSize"));
    }

    @GetMapping("/{name}")
    public Author getAuthorByName(@PathVariable("name") String name) {
        return authorService.findByName(stringService.properPathVariable(name));
    }

    @PostMapping("/admin")
    public void createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        authorService.createNewAuthor(authorDto);
    }

    @DeleteMapping("/admin")
    public void deleteAuthor(@RequestParam String name) {
        bookService.deleteAllByAuthor_Name(name);
        authorService.deleteByName(name);
    }

}
