package com.springbootapplication.controller;

import com.springbootapplication.entity.Role;
import com.springbootapplication.entity.User;
import com.springbootapplication.service.RoleService;
import com.springbootapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, @Lazy RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String logIn(){
        return "login";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping("/admin")
    public String userList(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("addUser", new User());
        return "admin";
    }

    @PostMapping("admin/create")
    public String create(@ModelAttribute("addUser") User user,
                         @RequestParam(value = "setRoles", required = false) Long[] setRoles)  {


        if (setRoles != null) {
            user.setRoles(roleService.rolesSetFromArray(setRoles));

        } else {
            user.setRoles(Set.of(roleService.readById(1L)));
        }
        userService.create(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/update")
    public String update(@ModelAttribute("addUser") User user,
                         @RequestParam(value = "setRoles", required = false) Long[] setRoles)  {

        if (setRoles != null) {
            user.setRoles(roleService.rolesSetFromArray(setRoles));
        }

        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/delete/{id}")
    public String delete(@ModelAttribute User user, @PathVariable("id") Long id) {

        userService.deleteById(id);
        return "redirect:/admin";
    }
}
