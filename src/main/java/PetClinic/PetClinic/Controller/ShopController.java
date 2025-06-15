package PetClinic.PetClinic.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import PetClinic.PetClinic.Model.Produk;
import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.ProdukRepository;
import PetClinic.PetClinic.Repository.UserRepository;

@Controller
public class ShopController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProdukRepository produkRepo;

    @GetMapping("/shop")
    public String shopPage(Model model, Principal principal) {
        // Get authenticated user info
        if (principal != null) {
            User user = userRepo.findByEmail(principal.getName());
            if (user != null) {
                model.addAttribute("loggedInUser", user.getUsername());
                model.addAttribute("userRole", user.getRole());
            }
        }

        // Get products from database
        List<Produk> products = produkRepo.findAll();
        model.addAttribute("products", products);

        return "shop";
    }
}
