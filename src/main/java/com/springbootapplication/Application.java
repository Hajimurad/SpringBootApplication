package com.springbootapplication;

import com.springbootapplication.entity.Role;
import com.springbootapplication.entity.User;
import com.springbootapplication.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableTransactionManagement
public class Application implements InitializingBean {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final UserService userService;

    public Application(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        //Логин:admin@gmail.com Пароль:password
        userService.create(
                new User("$2a$12$PJ7W4DrDJOOpYsFaGAzdqOY24e2ysfIksxeuA6O8o4OQRN50OEO52",
                        "name1", "surname1", "admin@gmail.com", 21, Set.of(role1, role2)));

        //user@gmail.com Пароль: qwerty
        userService.create(
                new User("$2a$12$kJBCiXtOK6CFjSGLQHyUk.M8bhgA3kZSsE.guU2mCbgr5zM1/qmve",
                        "name2", "surname2", "user@gmail.com", 26, Set.of(role2)));

        //Логин:admin2@gmail.com Пароль:123456
        userService.create(
                new User("$2a$12$1dklUbtx4l6n2de44hg/CO4QcTXDKf5BB3HClXG4deFUPxOX2lUh2",
                        "name3", "surname3", "admin2@gmail.com", 23, Set.of(role1)));

        System.out.println("USERS_SAVED");
    }
}