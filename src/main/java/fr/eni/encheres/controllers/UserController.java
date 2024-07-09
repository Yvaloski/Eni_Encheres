package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping("/{id}/profile")
    public String getUserProfileById(@PathVariable long id) {

        return "";
    }

}
