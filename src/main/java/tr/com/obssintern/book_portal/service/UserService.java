package tr.com.obssintern.book_portal.service;

import tr.com.obssintern.book_portal.dto.BookDto;
import tr.com.obssintern.book_portal.dto.CreateNewUserRequest;
import tr.com.obssintern.book_portal.dto.UserDto;
import tr.com.obssintern.book_portal.enumeration.RoleType;
import tr.com.obssintern.book_portal.model.User;

import java.util.List;

public interface UserService {
    void createNewUser(CreateNewUserRequest createNewUserRequest);

    List<UserDto> getAllUsers(String current, String pageSize);

    User findByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);

    void update(UserDto userDto);

    boolean assignRoleToUser(String username, List<RoleType> roletypes);

    boolean isExistByUsername(String username);

    boolean isExistByEmail(String email);

    List<BookDto> getFavourites(String username);

    List<BookDto> getReadList(String username);

    void addToFavourites(String username, String isbn);

    void addToReadList(String username, String isbn);

    void removeFromFavourites(String username, String isbn);

    void removeFromReadList(String username, String isbn);
}
