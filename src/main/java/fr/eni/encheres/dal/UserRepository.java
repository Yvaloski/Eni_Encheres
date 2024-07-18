package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByPseudo(String pseudo);
    Boolean existsByPseudo(String pseudo);
    Boolean existsByEmail(String email);
    Optional <User> findByPseudoOrEmail(
            @Param("PseudoOrEmail") String email,
            @Param("PseudoOrEmail") String pseudo);
}
