package fr.eni.encheres.bll.services;

import fr.eni.encheres.bo.User;

import java.util.List;

public interface UserService {

     void addUser(User user);

     List<User> getUsers();

     User getUserById(long id);

     void deleteUser(long id);

     User updateUser(User user);

     User getUserByPseudoOrEmail(String username, String email);

     Boolean existsByEmail(String email);

     Boolean existsByPseudo(String pseudo);

     User getUserByEmail(String email);

     User findByPseudo(String username);

     void deactivateUser(long id);

     void activateUser(long id);

}
