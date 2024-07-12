package fr.eni.encheres.bll;

import fr.eni.encheres.bll.services.UserService;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.UserRepository;
import fr.eni.encheres.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void addUser(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void setActiveStatusByIdUser(long id, boolean active) {
    }

    @Override
    public User getUserbyUsernameOrEmail(String username, String email) {
        return userRepo.findByUsernameOrEmail(username, email).orElse(null);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }


}
