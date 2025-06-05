package PetClinic.PetClinic.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class DashboardController {

    @GetMapping("/admin/admindashboard")
    public String adminDashboard() {
        return "admin/admindashboard"; // templates/admin/admindashboard.html
    }

    @GetMapping("/admin/produk")
    public String adminProduk() {
        return "admin/produk"; // templates/admin/produk.html
    }

    @GetMapping("/admin/user")
    public String adminUser() {
        return "admin/user"; // templates/admin/user.html
    }

    @GetMapping("/admin/reservasi")
    public String adminReservasi() {
        return "admin/reservasi"; // templates/admin/reservasi.html
    }

    @GetMapping("/admin/pembelian")
    public String adminPembelian() {
        return "admin/pembelian"; // templates/admin/pembelian.html
    }

    @GetMapping("/user/userdashboard")
    public String userDashboard() {
        return "user/userdashboard"; // templates/user/userdashboard.html
    }
    @GetMapping({"/admin/produk/tambah", "/admin/produk/edit/{id}"})
    public String produkForm(@PathVariable(required = false) Long id, Model model) {
    model.addAttribute("produkId", id);
    return "admin/produk-form"; // templates/admin/produk-form.html
    }
        @GetMapping({"/admin/pembelian/tambah", "/admin/pembelian/edit/{id}"})
    public String pembelianForm(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("pembelianId", id);
        return "admin/pembelian-form"; // templates/admin/pembelian-form.html
    }

    @GetMapping({"/admin/reservasi/tambah", "/admin/reservasi/edit/{id}"})
    public String reservasiForm(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("reservasiId", id);
        return "admin/reservasi-form"; // templates/admin/reservasi-form.html
    }

    @GetMapping({"/admin/user/tambah", "/admin/user/edit/{id}"})
    public String userForm(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("userId", id);
        return "admin/user-form"; // templates/admin/user-form.html
    }
}