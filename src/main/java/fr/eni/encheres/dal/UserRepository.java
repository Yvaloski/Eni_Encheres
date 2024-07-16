package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional <User> findByUsernameOrEmail(
            @Param("usernameOrEmail") String email,
            @Param("usernameOrEmail") String username);
}
