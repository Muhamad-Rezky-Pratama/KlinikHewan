package PetClinic.PetClinic.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.UserRepository;

@Controller
public class ReservasiController {

    @Autowired
    private UserRepository userRepo;

    // Method untuk View/Halaman
    @GetMapping("/reservasi")
    public String reservasiPage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        
        User user = userRepo.findByEmail(principal.getName());
        if (user != null) {
            model.addAttribute("loggedInUser", user.getUsername());
            model.addAttribute("userRole", user.getRole());
        }
        
        return "reservasi";
    }

    // Remove this conflicting method since it's handled in DashboardController
    /*
    @GetMapping("/admin/reservasi") 
    public String adminReservasiPage(Model model) {
        List<Reservasi> reservasis = reservasiRepository.findAll();
        model.addAttribute("reservasis", reservasis);
        return "admin/reservasi";
    }
    */    // Form handling moved to DashboardController

    // REST API Endpoints remain unchanged
    // ...existing code...
}