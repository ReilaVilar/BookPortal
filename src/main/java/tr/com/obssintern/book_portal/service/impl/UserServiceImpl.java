package tr.com.obssintern.book_portal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.obssintern.book_portal.dto.BookDto;
import tr.com.obssintern.book_portal.dto.CreateNewUserRequest;
import tr.com.obssintern.book_portal.dto.UserDto;
import tr.com.obssintern.book_portal.enumeration.RoleType;
import tr.com.obssintern.book_portal.exception.UserExistException;
import tr.com.obssintern.book_portal.exception.UserNotFoundException;
import tr.com.obssintern.book_portal.mapper.BookMapper;
import tr.com.obssintern.book_portal.mapper.UserMapper;
import tr.com.obssintern.book_portal.model.Book;
import tr.com.obssintern.book_portal.model.Role;
import tr.com.obssintern.book_portal.model.User;
import tr.com.obssintern.book_portal.repository.UserRepository;
import tr.com.obssintern.book_portal.service.BaseEntityService;
import tr.com.obssintern.book_portal.service.BookService;
import tr.com.obssintern.book_portal.service.RoleService;
import tr.com.obssintern.book_portal.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final BaseEntityService baseEntityService;

    private final RoleService roleService;

    private final BookService bookService;

    private final UserMapper userMapper;

    private final BookMapper bookMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> getAllUsers(String current, String pageSize) {
        final Pageable pageable = PageRequest.of(Integer.parseInt(current), Integer.parseInt(pageSize));
        final List<User> allUsers = userRepository.findAllByOrderByUsername(pageable);
        List<UserDto> retList = new ArrayList<>();
        allUsers.forEach(user -> retList.add(userMapper.mapTo(user)));
        return retList;
    }

    @Override
    public void createNewUser(CreateNewUserRequest createNewUserRequest) throws UserExistException {
        Objects.requireNonNull(createNewUserRequest, "User cannot be null");

        if (this.isExistByUsername(createNewUserRequest.getUsername())) {
            throw new UserExistException("username", createNewUserRequest.getUsername());
        }
        if (this.isExistByEmail(createNewUserRequest.getEmail())) {
            throw new UserExistException("email", createNewUserRequest.getEmail());
        }

        User user = userMapper.mapToEntity(createNewUserRequest);
        user.setPassword(passwordEncoder.encode(createNewUserRequest.getPassword()));
        final Role standardRole = roleService.findByName(RoleType.ROLE_USER);
        user.setRoles(List.of(standardRole));
        baseEntityService.nowCreated(user);
        baseEntityService.nowUpdated(user);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> toBeDeletedUser = userRepository.findById(id);
        if (toBeDeletedUser.isPresent() && !toBeDeletedUser.get().getUsername().equals("sys.admin")) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void update(UserDto userDto) {
        final User user = this.findById(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        baseEntityService.nowUpdated(user);
        userRepository.save(user);
    }

    @Override
    public boolean assignRoleToUser(String username, List<RoleType> roleTypes) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);

        List<Role> roles = new ArrayList<>();
        roleTypes.forEach(roleType -> roles.add(roleService.findByName(roleType)));

        final List<Role> rolesOfUser = user.getRoles();
        if (Objects.isNull(rolesOfUser)) {
            user.setRoles(new ArrayList<>());
        }
        roles.forEach(role -> user.getRoles().add(role));
        userRepository.save(user);
        return Boolean.TRUE;
    }

    @Override
    public boolean isExistByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public boolean isExistByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public List<BookDto> getFavourites(String username) {
        User user = this.findByUsername(username);
        return user.getFavBooks().stream().map(bookMapper::mapTo).toList();
    }

    @Override
    public List<BookDto> getReadList(String username) {
        User user = this.findByUsername(username);
        return user.getReadList().stream().map(bookMapper::mapTo).toList();
    }

    @Override
    public void addToFavourites(String username, String isbn) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        final Book book = bookService.findByIsbn(isbn);
        final List<Book> favBooks = user.getFavBooks();
        if (Objects.isNull(favBooks)) {
            user.setFavBooks(new ArrayList<>());
        }
        favBooks.add(book);
        user.setFavBooks(favBooks);
        userRepository.save(user);
    }

    @Override
    public void addToReadList(String username, String isbn) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        final Book book = bookService.findByIsbn(isbn);
        final List<Book> readList = user.getReadList();
        if (Objects.isNull(readList)) {
            user.setReadList(new ArrayList<>());
        }
        readList.add(book);
        user.setReadList(readList);
        userRepository.save(user);
    }

    @Override
    public void removeFromFavourites(String username, String isbn) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        final Book book = bookService.findByIsbn(isbn);
        final List<Book> favBooks = user.getFavBooks();
        if (Objects.isNull(favBooks)) {
            return;
        }
        favBooks.remove(book);
        user.setFavBooks(favBooks);
        userRepository.save(user);
    }

    @Override
    public void removeFromReadList(String username, String isbn) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        final Book book = bookService.findByIsbn(isbn);
        final List<Book> readList = user.getReadList();
        if (Objects.isNull(readList)) {
            return;
        }
        readList.remove(book);
        user.setReadList(readList);
        userRepository.save(user);
    }


}
