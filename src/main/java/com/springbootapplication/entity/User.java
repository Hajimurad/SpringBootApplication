package com.springbootapplication.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users_table")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Size(min = 6, max = 64)
    @Column(name = "PASSWORD", nullable = false, length = 64)
    private String password;

    @Size(min = 4, max = 45)
    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    private String firstName;

    @Size(max = 45)
    @Column(name = "LAST_NAME", length = 45)
    private String lastName;

    @Size(max = 45)
    @Column(name = "EMAIL", nullable = false, length = 45, unique = true)
    private String username;

    @Column(name = "AGE", nullable = false, length = 3)
    private Integer age;

    public User(Long id, String password, String firstName, String lastName, String username, Integer age, Set<Role> roles) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.age = age;
        this.roles = roles;
    }

    public User(String password, String firstName, String lastName, String username, Integer age, Set<Role> roles) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.age = age;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER,  cascade = {CascadeType.MERGE})
    @JoinTable (name = "user_roles",
            joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
