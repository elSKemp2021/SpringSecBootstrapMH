package web.spring_boot_security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import web.spring_boot_security.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
    User findUserById(long id);
    User findUserByUsername(String username);
}
