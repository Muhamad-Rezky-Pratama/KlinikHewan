package PetClinic.PetClinic.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegisterController {

    @Autowired
private UserRepository userRepo;

@Autowired
private BCryptPasswordEncoder passwordEncoder;

@PostMapping("/register")
public Map<String, String> register(@RequestBody User user) {
    if (userRepo.findByEmail(user.getEmail()) != null) {
        return Map.of("status", "fail", "message", "Email sudah digunakan");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    if (user.getRole() == null || user.getRole().isEmpty()) {
        user.setRole("user");
    }

    userRepo.save(user);
    return Map.of("status", "success", "message", "Berhasil daftar!");
}
}