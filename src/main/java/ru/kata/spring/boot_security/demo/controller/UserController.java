package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.wstrug.kataPP.model.User;
import ru.wstrug.kataPP.service.UserService;
import ru.wstrug.kataPP.service.UserServiceImp;


@Controller
@RequestMapping("/users")
public class UserController {

    final UserServiceImp us;

    @Autowired
    public UserController(UserServiceImp us) {
        this.us = us;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", us.listUsers());
        return "users/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        us.add(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", us.getUserById(id));
        return "users/edit";
    }

    @PostMapping("/")
    public String update(@ModelAttribute("person") User user, @RequestParam("id") int id) {
        us.updateUserById(user, id);
        return "redirect:/users";
    }

    @GetMapping("/")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "users/show";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        us.deleteUserById(id);
        return "redirect:/users";
    }
}
