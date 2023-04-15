package tr.com.obssintern.book_portal.mapper;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import tr.com.obssintern.book_portal.dto.CreateNewUserRequest;
import tr.com.obssintern.book_portal.dto.UserDto;
import tr.com.obssintern.book_portal.model.User;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    User mapToEntity(CreateNewUserRequest createNewUserRequest);

    UserDto mapTo(User user);
}
