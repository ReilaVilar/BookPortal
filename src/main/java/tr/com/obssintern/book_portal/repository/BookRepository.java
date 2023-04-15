package tr.com.obssintern.book_portal.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.obssintern.book_portal.model.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByOrderByNameAsc(Pageable pageable);

    Optional<Book> findBookByName(String name);

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findAllByAuthor_Name(String authorName);

    @Transactional
    void deleteAllByAuthor_Name(String authorName);

    void deleteBookByIsbn(String isbn);
}
