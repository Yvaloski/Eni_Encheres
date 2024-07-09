package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.UserService;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}/profile")
    public User getUserProfileById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/profile/edit")
    public User editUserProfileById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/{id}/profile/edit")
    public String saveChangesUserProfile(@RequestBody User user) {
        userService.updateUser(user);
        return "OK";
    }

    @GetMapping("/{id}/delete")
    public String removeUser(@PathVariable long id) {
        userRepository.deleteById(id);
        return "OK";
    }

    @GetMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable long id) {
        //will need to add the attribute "active" in the User class :d
        return "OK";
    }

    @GetMapping("/{id}/deactivate")
    public String activateUser(@PathVariable long id) {
        //will need to add the attribute "active" in the User class :d
        return "OK";
    }


}
