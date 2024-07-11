package fr.eni.encheres.bll;

import fr.eni.encheres.bo.User;

import java.util.List;

public interface UserService {

     void addUser(User user);

     List<User> getUsers();

     User getUserById(long id);

     void deleteUser(long id);

     void updateUser(User user);

     void setActiveStatusByIdUser (long id, boolean active);

     User getUserbyUsernameOrEmail(String username, String email);

     Boolean existsByEmail(String email);

     Boolean existsByUsername(String username);

}
