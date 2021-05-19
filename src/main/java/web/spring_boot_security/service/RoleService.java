package web.spring_boot_security.service;

import web.spring_boot_security.entity.Role;

import java.util.List;

public interface RoleService {
    void create(Role role);

    void update(Role role);

    void delete(Role role);

    List<Role> getAllRoles();

    Role getRoleById(long id);

    Role getRoleByRolename(String rolename);

}
