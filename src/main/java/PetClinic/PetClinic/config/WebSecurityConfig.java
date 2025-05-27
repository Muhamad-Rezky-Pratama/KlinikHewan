package PetClinic.PetClinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", 
                    "home.html",
                    "/login.html", 
                    "/register.html",
                    "/css/**", 
                    "/js/**",
                    "/api/**",     // <- penting agar bisa akses /api/login
                    "/admin/**",   // <- opsional jika kamu biarkan akses admin static
                    "/user/**"     // <- opsional
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.disable()); // karena pakai login via JS/API

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
