package PetClinic.PetClinic.Controller;

import java.util.HashMap;
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
public class LoginController {

  @Autowired
private UserRepository userRepo;

@Autowired
private BCryptPasswordEncoder passwordEncoder;

@PostMapping("/login")
public Map<String, String> login(@RequestBody Map<String, String> req) {
    String email = req.get("email");
    String password = req.get("password");

    Map<String, String> res = new HashMap<>();
    User user = userRepo.findByEmail(email);

    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
        res.put("status", "success");
        res.put("message", "Login berhasil");
        res.put("role", user.getRole());
    } else {
        res.put("status", "fail");
        res.put("message", "Email atau password salah");
    }

    return res;
}

}
