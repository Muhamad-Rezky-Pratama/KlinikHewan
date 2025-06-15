package PetClinic.PetClinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import PetClinic.PetClinic.Service.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler; // Tambahkan ini

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Public pages and resources
                .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**", "/image/**").permitAll()
                .requestMatchers("/clinic", "/shop").permitAll()
                // Admin routes
                .requestMatchers("/admin/**").hasRole("admin")
                // Protected actions
                .requestMatchers("/reservasi/**").authenticated()
                .requestMatchers("/shop/checkout/**", "/shop/cart/**").authenticated()
                .requestMatchers("/user/**").hasRole("user")
                // All other requests need authentication
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(customLoginSuccessHandler) // Pakai handler custom
                .permitAll()
            )            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}