package PetClinic.PetClinic.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
private UserRepository userRepo;

@Autowired
private BCryptPasswordEncoder passwordEncoder;

// Get all users
@GetMapping
public List<User> getAllUsers() {
    return userRepo.findAll();
}

// Get user by ID
@GetMapping("/{id}")
public User getUser(@PathVariable Long id) {
    return userRepo.findById(id).orElse(null);
}

// Create user
@PostMapping
public User createUser(@RequestBody User user) {
    if (user.getPassword() == null || user.getPassword().isEmpty()) {
        throw new IllegalArgumentException("Password tidak boleh kosong");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    if (user.getRole() == null || user.getRole().isEmpty()) {
        user.setRole("USER");
    }
    return userRepo.save(user);
}

// Update user
@PutMapping("/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
    User user = userRepo.findById(id).orElse(null);
    if (user != null) {
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());

        if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }

        user.setRole(newUser.getRole());
        return userRepo.save(user);
    }
    return null;
}

// Delete user
@DeleteMapping("/{id}")
public void deleteUser(@PathVariable Long id) {
    userRepo.deleteById(id);
}

// Register (opsional)
@PostMapping("/register")
public String processRegister(@RequestBody User user) {
    if (user.getPassword() == null || user.getPassword().isEmpty()) {
        return "Password wajib diisi";
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    if (user.getRole() == null || user.getRole().isEmpty()) {
        user.setRole("user");
    }
    userRepo.save(user);
    return "Register berhasil";
}

}
