package PetClinic.PetClinic.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.UserRepository;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    private void addUserToModel(Model model, Principal principal) {
        if (principal != null) {
            User user = userRepo.findByEmail(principal.getName());
            if (user != null) {
                model.addAttribute("loggedInUser", user.getUsername());
                model.addAttribute("userRole", user.getRole());
            }
        }
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        addUserToModel(model, principal);
        return "home";
    }

    @GetMapping("/clinic")
    public String clinicPage(Model model, Principal principal) {
        addUserToModel(model, principal);
        return "clinic";
    }
}