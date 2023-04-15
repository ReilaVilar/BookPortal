package tr.com.obssintern.book_portal.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.obssintern.book_portal.enumeration.RoleType;
import tr.com.obssintern.book_portal.exception.RoleNotFoundException;
import tr.com.obssintern.book_portal.model.Role;
import tr.com.obssintern.book_portal.repository.RoleRepository;
import tr.com.obssintern.book_portal.service.RoleService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findByName(RoleType type) {
        return roleRepository.findRoleByName(type).orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public void createNewRole(Role role) {
        Optional<Role> existRole = roleRepository.findRoleByName(role.getName());

        if (!existRole.isPresent()) {
            roleRepository.save(role);
        }
    }
}
