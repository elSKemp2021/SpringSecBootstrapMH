package web.spring_boot_security_bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import web.spring_boot_security_bootstrap.entity.Role;
import web.spring_boot_security_bootstrap.entity.User;
import web.spring_boot_security_bootstrap.repository.RoleRepository;
import web.spring_boot_security_bootstrap.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserTest {
    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TestEntityManager em;

    @Test
    public void testCreateRole() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        em.persist(adminRole);
        em.persist(userRole);
    }

    @Test
    public void testCreateUserAdmin() {
        //Before run this test run testCreateRole
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findRoleByRolename("ROLE_ADMIN"));

        User admin = new User("Админ", "Админыч", "admin@123.com", 45, "admin", roles);
        em.persist(admin);

        Assert.isTrue(userRepo.findUserByEmail("admin@123.com").isEnabled(), "User not find in DB");
    }

    @Test
    public void testCreateUserUser() {
        //Before run this test run testCreateRole
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findRoleByRolename("ROLE_USER"));

        User user = new User("Юзер", "Обыкнвениус",  "user@123.com",22, "user", roles);
        em.persist(user);

        Assert.notNull(userRepo.findUserByEmail("user@123.com"), "User is not find in DB");
    }

    @Test
    public void testCreateUserWithManyRoles() {
        //Before run this test run testCreateRole
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findRoleByRolename("ROLE_ADMIN"));
        roles.add(roleRepo.findRoleByRolename("ROLE_USER"));

        User user = new User("test", "Testing", "test@mail.ru", 111, "test", roles);
        em.persist(user);
    }
    @Test
    public void testGetUserRoles() {
        //Before run this test run testCreateUserWithManyRoles()
        User user = userRepo.findUserByEmail("test@mail.ru");
        System.out.println(user.getRoles());
    }

}
