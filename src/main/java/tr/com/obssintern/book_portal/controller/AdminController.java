package tr.com.obssintern.book_portal.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.com.obssintern.book_portal.dto.CreateNewUserRequest;
import tr.com.obssintern.book_portal.dto.UserDto;
import tr.com.obssintern.book_portal.enumeration.RoleType;
import tr.com.obssintern.book_portal.model.User;
import tr.com.obssintern.book_portal.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/")
    public List<UserDto> getAllUsers(@RequestBody Map<String, String> pagination) {
        return userService.getAllUsers(pagination.get("current"), pagination.get("pageSize"));
    }

    @PostMapping("/")
    public Boolean createUser(@RequestBody @Valid CreateNewUserRequest createNewUserRequest) {
        userService.createNewUser(createNewUserRequest);
        return Boolean.TRUE;
    }

    @PutMapping("/")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
    }

    @DeleteMapping("/")
    public Boolean deleteUser(@RequestParam @Valid Long id) {
        userService.deleteById(id);
        return Boolean.TRUE;
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable(value = "username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/assign/{username}")
    public Boolean assignUserToAdmin(@PathVariable("username") String username) {
        return userService.assignRoleToUser(username, List.of(RoleType.ROLE_ADMIN));
    }
}
