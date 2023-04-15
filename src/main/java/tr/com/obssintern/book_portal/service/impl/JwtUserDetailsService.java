package tr.com.obssintern.book_portal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tr.com.obssintern.book_portal.dto.MyUserDetails;
import tr.com.obssintern.book_portal.exception.UserNotFoundException;
import tr.com.obssintern.book_portal.model.User;
import tr.com.obssintern.book_portal.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UserNotFoundException {

        final User user = userService.findByUsername(username);
        final List<String> roles = user.getRoles().stream().map(role -> role.getName().name()).toList();
        return new MyUserDetails(user.getUsername(), user.getPassword(), roles);
    }
}
