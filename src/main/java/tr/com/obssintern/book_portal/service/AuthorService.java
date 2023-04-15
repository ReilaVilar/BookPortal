package tr.com.obssintern.book_portal.service;

import tr.com.obssintern.book_portal.dto.AuthorDto;
import tr.com.obssintern.book_portal.model.Author;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors(String current, String pageSize);

    void createNewAuthor(AuthorDto authorDto);

    boolean deleteByName(String name);

    void update(AuthorDto authorDto);

    Author findByName(String name);
}
