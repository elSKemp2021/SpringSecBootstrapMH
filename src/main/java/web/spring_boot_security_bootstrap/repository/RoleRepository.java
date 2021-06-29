package web.spring_boot_security_bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.spring_boot_security_bootstrap.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRolename(String rolename);
}
