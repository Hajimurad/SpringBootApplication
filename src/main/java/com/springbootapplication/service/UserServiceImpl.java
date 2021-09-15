package com.springbootapplication.service;

import com.springbootapplication.dao.UserDAO;
import com.springbootapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl (UserDAO userDAO, @Lazy PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    private void injectCrypt(User user) {
        String password = user.getPassword();
        if (!Pattern.compile("^\\$2[ayb]\\$.{56}$").matcher(password).matches()) {
            user.setPassword(passwordEncoder.encode(password));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public User create(User user) {

        if(userDAO.findByUsername(user.getUsername()) != null){
            throw new IllegalArgumentException("This username is already used");
        }

        injectCrypt(user);
        return userDAO.create(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User readById(Long id) {
       return userDAO.readById(id);
    }

    @Override
    public User update(User user) {

        if (userDAO.readById(user.getId()) == null) {
            throw new IllegalArgumentException("User has not been found");
        }
        if(user.getPassword() != null) {
            injectCrypt(user);
        }

        return userDAO.update(user);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDAO.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return user;
    }
}
