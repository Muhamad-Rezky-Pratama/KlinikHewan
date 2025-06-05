package PetClinic.PetClinic.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservasiViewController {

    @GetMapping("/reservasi")
    public String showReservasiPage() {
        return "reservasi"; // akan membuka templates/reservasi.html
    }
}