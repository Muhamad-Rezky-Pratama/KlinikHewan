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
public class LoginController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        User user = userRepo.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            if ("admin".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin/admindashboard";
            } else if ("user".equalsIgnoreCase(user.getRole())) {
                return "redirect:/user/userdashboard";
            } else {
                model.addAttribute("error", "Role tidak dikenali.");
                return "login";
            }
        }
        model.addAttribute("error", "Email atau password salah.");
        return "login";
    }
}