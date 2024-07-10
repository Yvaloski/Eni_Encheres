package fr.eni.encheres.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordRncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsManager userDetailsMger(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("select pseudo, password, active from utilisateur where pseudo = ?");
        manager.setAuthoritiesByUsernameQuery("select pseudo, role from roles where pseudo = ?");

        return manager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->{
            //on donne accès à la requête de type Get / security
            auth.requestMatchers(HttpMethod.GET, "/security").hasRole("ADMIN")
                    .requestMatchers("/*").permitAll()
                    .anyRequest().denyAll();

        });

        http.formLogin(form -> {
            form.loginPage("/auth/login").permitAll()
                    .defaultSuccessUrl("/");
        });

        http.logout(logout -> {
            logout.invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutRequestMatcher(new AntPathRequestMatcher("auth//logout"))
                    .logoutSuccessUrl("/")
                    .permitAll();
        });

        return http.build();
    }

}
