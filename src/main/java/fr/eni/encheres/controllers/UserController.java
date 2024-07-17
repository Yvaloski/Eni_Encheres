package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.services.UserService;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dtos.UserDto;
import org.springframework.http.ResponseEntity;
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

    @PatchMapping("/{id}/profile/edit")
    public ResponseEntity<User> updateUserProfile(@PathVariable long id, @RequestBody UserDto userDto) {
        User existingUser = userService.getUserById(id);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        existingUser.setUsername(userDto.getUsername());
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setFamilyName(userDto.getFamilyName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPhone(userDto.getPhone());
        existingUser.setAddress(userDto.getAddress());
        existingUser.setPostalCode(userDto.getPostalCode());
        existingUser.setCity(userDto.getCity());
        existingUser.setPassword(userDto.getPassword());

        User updatedUser = userService.updateUser(existingUser);

        return ResponseEntity.ok(updatedUser);
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
