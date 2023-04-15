package tr.com.obssintern.book_portal.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.obssintern.book_portal.dto.CreateNewUserRequest;
import tr.com.obssintern.book_portal.dto.UserDto;
import tr.com.obssintern.book_portal.model.Role;
import tr.com.obssintern.book_portal.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-12T05:45:22+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapToEntity(CreateNewUserRequest createNewUserRequest) {
        if ( createNewUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( createNewUserRequest.getUsername() );
        user.setEmail( createNewUserRequest.getEmail() );
        user.setPassword( createNewUserRequest.getPassword() );
        user.setAge( createNewUserRequest.getAge() );

        return user;
    }

    @Override
    public UserDto mapTo(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setEmail( user.getEmail() );
        userDto.setAge( user.getAge() );
        List<Role> list = user.getRoles();
        if ( list != null ) {
            userDto.setRoles( new ArrayList<Role>( list ) );
        }

        return userDto;
    }
}
