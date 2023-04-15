package tr.com.obssintern.book_portal.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obssintern.book_portal.model.Author;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByOrderByNameAsc(Pageable pageable);

    Optional<Author> findAuthorByName(String name);

    @Transactional
    Long deleteAuthorByName(String name);
}
