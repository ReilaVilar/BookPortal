package tr.com.obssintern.book_portal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.obssintern.book_portal.dto.AuthorDto;
import tr.com.obssintern.book_portal.exception.AuthorNotFoundException;
import tr.com.obssintern.book_portal.mapper.AuthorMapper;
import tr.com.obssintern.book_portal.model.Author;
import tr.com.obssintern.book_portal.repository.AuthorRepository;
import tr.com.obssintern.book_portal.service.AuthorService;
import tr.com.obssintern.book_portal.service.BaseEntityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final BaseEntityService baseEntityService;

    private final AuthorMapper authorMapper;


    @Override
    public List<AuthorDto> getAllAuthors(String current, String pageSize) {
        final Pageable pageable = PageRequest.of(Integer.parseInt(current), Integer.parseInt(pageSize));
        final Iterable<Author> allAuthors = authorRepository.findByOrderByNameAsc(pageable);
        List<AuthorDto> retList = new ArrayList<>();
        allAuthors.forEach(author -> retList.add(authorMapper.mapTo(author)));
        return retList;
    }

    @Override
    public void createNewAuthor(AuthorDto authorDto) {
        Objects.requireNonNull(authorDto, "Author cannot be null");
        final Author author = authorMapper.mapToEntity(authorDto);
        baseEntityService.nowCreated(author);
        baseEntityService.nowUpdated(author);
        authorRepository.save(author);
    }

    @Override
    public boolean deleteByName(String name) {
        return authorRepository.deleteAuthorByName(name) == 1;
    }

    @Override
    public void update(AuthorDto authorDto) {
        final Author author = this.findByName(authorDto.getName());
        author.setName(authorDto.getName());
        baseEntityService.nowUpdated(author);
        authorRepository.save(author);
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findAuthorByName(name).orElseThrow(AuthorNotFoundException::new);
    }


}
