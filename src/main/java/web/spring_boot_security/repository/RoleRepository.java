package web.spring_boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.spring_boot_security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRolename(String rolename);
}
