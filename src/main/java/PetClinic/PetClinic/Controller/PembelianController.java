package PetClinic.PetClinic.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PetClinic.PetClinic.Model.Pembelian;
import PetClinic.PetClinic.Model.Produk;
import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.PembelianRepository;
import PetClinic.PetClinic.Repository.ProdukRepository;
import PetClinic.PetClinic.Repository.UserRepository;

@RestController
@RequestMapping("/api/pembelian")
@CrossOrigin
public class PembelianController {

    @Autowired
    private PembelianRepository pembelianRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ProdukRepository produkRepo;
    

    @GetMapping
    public List<Pembelian> getAll() {
        return pembelianRepo.findAll();
    }

    @PostMapping
    public Pembelian create(@RequestBody Pembelian pembelian) {
        pembelian.setTanggal(LocalDateTime.now());
        return pembelianRepo.save(pembelian);
    }    @PostMapping("/checkout") 
    public Map<String, Object> checkout(@RequestBody List<Map<String, Object>> cartItems, Principal principal) {
        try {
            if (principal == null) {
                return Map.of("message", "Silakan login terlebih dahulu");
            }
            
            User user = userRepo.findByEmail(principal.getName());
            if (user == null) {
                return Map.of("message", "User tidak ditemukan");
            }

            List<Pembelian> pembelianList = new ArrayList<>();
            
            for (Map<String, Object> item : cartItems) {
                // Extract item details
                Integer productId = ((Number) item.get("productId")).intValue();
                Integer quantity = ((Number) item.get("quantity")).intValue();
                Double price = ((Number) item.get("price")).doubleValue();
                
                // Find product and validate stock
                Produk produk = produkRepo.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Produk tidak ditemukan: " + productId));
                
                if (produk.getStok() < quantity) {
                    throw new RuntimeException("Stok tidak mencukupi untuk produk: " + produk.getNama());
                }

                // Create new pembelian
                Pembelian pembelian = new Pembelian();
                pembelian.setUser(user);
                pembelian.setProduk(produk); // Set produk sebelum save
                pembelian.setJumlah(quantity);
                pembelian.setTotal(price * quantity);
                pembelian.setTanggal(LocalDateTime.now());
                pembelian.setStatus("PENDING");
                
                // Update stock
                produk.setStok(produk.getStok() - quantity);
                produkRepo.save(produk);
                
                // Save pembelian
                pembelianList.add(pembelianRepo.save(pembelian));
            }
            
            return Map.of(
                "message", "Checkout berhasil",
                "orderId", pembelianList.get(0).getId()
            );

        } catch (NullPointerException | ClassCastException e) {
            return Map.of("message", "Data tidak valid: " + e.getMessage());
        } catch (RuntimeException e) {
            return Map.of("message", "Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pembelianRepo.deleteById(id);
    }
}
