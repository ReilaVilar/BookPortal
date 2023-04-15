package tr.com.obssintern.book_portal.service;

import tr.com.obssintern.book_portal.enumeration.RoleType;
import tr.com.obssintern.book_portal.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    void createNewRole(Role role);

    Role findByName(RoleType type);

}
