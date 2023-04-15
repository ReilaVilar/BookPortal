package tr.com.obssintern.book_portal.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obssintern.book_portal.dto.UserDto;
import tr.com.obssintern.book_portal.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByUsername(Pageable pageable);

    Optional<User> findUserByUsername(String username);

    Optional<UserDto> findUserByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);
}
