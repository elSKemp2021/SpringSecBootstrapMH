package web.spring_boot_security_bootstrap.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "email", length = 20, nullable = false)
    private String email;

    @Column(name = "lastname", length = 20, nullable = false)
    private String lastname;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "password")
    private String password;


    @Column(name = "username")
    private String username;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    @Autowired
    private RoleDao roleDao;

    public User() {
    }

    public User(String name,
                String lastName,
                int age,
                String email,
                String username,
                String password,
                Set<Role> roles) {
        this.name = name;
        this.lastname = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getRoleString() {
        StringBuilder stringRole = new StringBuilder();
        for(Role role : roles) {
            stringRole.append(role.getName().substring(5)).append(" ");
        }
        return stringRole.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
//@Entity
//@Table(name = "users")
//public class User implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String nameFirst;
//    private String nameLast;
//    private String email;
//    private Integer age;
//    private String password;
//    @Transient
//    private String passwordConfirm;
//    private Integer enabled;
//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private Set<Role> roles;
//
//    public User() {
//    }
//
//    public User(String nameFirst, String nameLast, String email, Integer age, String password, Set<Role> roles) {
//        this.nameFirst = nameFirst;
//        this.nameLast = nameLast;
//        this.email = email;
//        this.age = age;
//        this.password = password;
//        this.roles = roles;
//
//        //Set enabled = 1 for enable login user
//        this.enabled = 1;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNameFirst() {
//        return nameFirst;
//    }
//
//    public void setNameFirst(String nameFirst) {
//        this.nameFirst = nameFirst;
//    }
//
//    public String getNameLast() {
//        return nameLast;
//    }
//
//    public void setNameLast(String nameLast) {
//        this.nameLast = nameLast;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public String getPasswordConfirm() {
//        return passwordConfirm;
//    }
//
//    public void setPasswordConfirm(String passwordConfirm) {
//        this.passwordConfirm = passwordConfirm;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled > 0;
//    }
//
//    public Integer getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Integer enabled) {
//        this.enabled = enabled;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    public void addRole(Role role) {
//        this.roles.add(role);
//    }
//
//    public void removeRole(Role role) {
//        this.roles.remove(role);
//    }
//
//    public String rolesToString() {
//        return getRoles().stream()
//                .map(Role::toString)
//                .sorted()
//                .collect(Collectors.joining(" "));
//    }
//
//    public boolean isAdmin() {
//        return rolesToString().contains("ADMIN");
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        if (!nameFirst.equals(user.nameFirst)) return false;
//        if (!nameLast.equals(user.nameLast)) return false;
//        return email.equals(user.email);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = nameFirst.hashCode();
//        result = 31 * result + nameLast.hashCode();
//        result = 31 * result + email.hashCode();
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", nameFirst='" + nameFirst + '\'' +
//                ", nameLast='" + nameLast + '\'' +
//                ", email='" + email + '\'' +
//                ", age=" + age +
//                ", password='" + password + '\'' +
//                ", passwordConfirm='" + passwordConfirm + '\'' +
//                ", enabled=" + enabled +
//                ", roles=" + roles +
//                '}';
//    }
//}
