package fr.eni.encheres.bll.services;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.UserRepository;
import fr.eni.encheres.dtos.LoginUserDto;
import fr.eni.encheres.dtos.RegisterUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService  {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        // Vérifier si l'utilisateur existe déjà
//        if (userRepository.findByUsername(input.getUserName()).isPresent()) {
//            throw new RuntimeException("L'utilisateur existe déjà");
//        }

        User user = new User();
        user.setUsername(input.getUserName());
        user.setFamilyName(input.getFamilyName());
        user.setFirstName(input.getFirstName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        user.setAddress(input.getAddress());
        user.setPostalCode(input.getPostalCode());
        user.setCity(input.getCity());
        user.setPassword(passwordEncoder.encode(input.getPassword())); // Encoder le mot de passe
        user.setCredit(0);
        user.setAdmin(true);
        user.setActive(true);
        user.setRole("ROLE_USER");


        // Enregistrer l'utilisateur dans la base de données
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}