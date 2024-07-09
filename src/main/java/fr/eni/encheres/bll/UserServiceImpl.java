package fr.eni.encheres.bll;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepo;

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
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }
}
