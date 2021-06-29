package web.spring_boot_security_bootstrap.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import web.spring_boot_security_bootstrap.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
    User findUserById(long id);
    User findUserByEmail(String email);
}
