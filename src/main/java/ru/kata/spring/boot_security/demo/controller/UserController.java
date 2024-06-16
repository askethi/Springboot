package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.HashSet;
import java.util.Set;

@Controller
//@RequestMapping("/")
public class UserController {

    final UserServiceImp us;
    final RoleDao rs;

    @Autowired
    public UserController(UserServiceImp us, RoleDao rs) {
        this.us = us;
        this.rs = rs;
    }

    @GetMapping()
    public String show(@RequestParam("username") String username, Model model) {
        model.addAttribute("user", us.findByUsername(username));
        return "user";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("users", us.listUsers());
        return "users/index";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping("/admin")
    public String create(@RequestParam("role") String rolestring, @ModelAttribute("user") User user) {
        Role role = new Role();
        rs.findByRole(rolestring);//TODO!!!!!!!!!!!!!!!
        role.setRole(rolestring);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        us.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", us.getUserById(id));
        return "users/edit";
    }

    @PostMapping("/admin/edit")
    public String update(@ModelAttribute("person") User user, @RequestParam("id") int id) {
        us.updateUserById(user, id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "users/show";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam("id") int id) {
        us.deleteUserById(id);
        return "redirect:/admin";
    }

//    @GetMapping("/admin")
//    public String adminPage() {
//        return "users/admin";
//    }
}
