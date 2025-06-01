
package PetClinic.PetClinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.UserRepository;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
public String processRegister(@RequestParam String username,
                              @RequestParam String email,
                              @RequestParam String password,
                              Model model) {
    if (userRepo.findByEmail(email) != null) {
        model.addAttribute("error", "Email sudah terdaftar.");
        return "register";
    }
    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    user.setRole("user");
    userRepo.save(user);
    model.addAttribute("success", "Registrasi berhasil! Silakan login.");
    return "register";
    }
}