package tr.com.obssintern.book_portal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.com.obssintern.book_portal.dto.CreateNewUserRequest;
import tr.com.obssintern.book_portal.enumeration.RoleType;
import tr.com.obssintern.book_portal.exception.UserNotFoundException;
import tr.com.obssintern.book_portal.model.Role;
import tr.com.obssintern.book_portal.model.User;
import tr.com.obssintern.book_portal.service.RoleService;
import tr.com.obssintern.book_portal.service.UserService;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class InitConfig {

    private final RoleService roleService;

    private final UserService userService;

    @Bean
    public CommandLineRunner initRoles() {
        return args -> {
            final List<RoleType> roles = roleService.getAllRoles().stream().map(Role::getName).toList();

            Arrays.stream(RoleType.values()).filter(roleType -> !roles.contains(roleType)).forEach(roleType -> {
                Role role = new Role();
                role.setName(roleType);
                roleService.createNewRole(role);
            });
            try {
                final User adminUser = userService.findByUsername("sys.admin");

            } catch (UserNotFoundException e) {

                CreateNewUserRequest adminUser = new CreateNewUserRequest();
                adminUser.setUsername("sys.admin");
                adminUser.setEmail("admin@bookportal.com");
                adminUser.setPassword("admin");
                userService.createNewUser(adminUser);
                userService.assignRoleToUser(adminUser.getUsername(), List.of(RoleType.ROLE_ADMIN));
            } catch (IllegalStateException e) {
                System.out.println("illegal state exception");
            }
        };
    }
}
