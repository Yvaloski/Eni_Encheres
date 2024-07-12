package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.services.UserService;
import fr.eni.encheres.bo.User;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/profile")
    public User getUserProfileById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/profile/edit")
    public User editUserProfileById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/profile/edit")
    public String saveChangesUserProfile(@RequestBody User user) {
        userService.updateUser(user);
        return "OK";
    }

    @GetMapping("/{id}/delete")
    public String removeUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "OK";
    }

    @GetMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable long id) {
        return "OK";
    }

    @GetMapping("/{id}/activate")
    public String activateUser(@PathVariable long id) {
        //will need to add the attribute "active" in the User class :d
        return "OK";
    }


}
