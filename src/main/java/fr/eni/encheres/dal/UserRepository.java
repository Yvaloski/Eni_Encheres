package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
