package PetClinic.PetClinic.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PetClinic.PetClinic.Model.Produk;
import PetClinic.PetClinic.Repository.ProdukRepository;

@RestController
@RequestMapping("/api/produk")
@CrossOrigin
public class ProdukController {

    private static final List<String> VALID_CATEGORIES = Arrays.asList("cat-food", "dog-food", "grooming");

    @Autowired
    private ProdukRepository produkRepo;

    @GetMapping
    public List<Produk> getAllProduk() {
        return produkRepo.findAll();
    }

    @GetMapping("/{id}")
    public Produk getProdukById(@PathVariable Integer id) {
        return produkRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Produk createProduk(@RequestBody Produk produk) {
        // Validate and normalize category
        if (produk.getKategori() != null) {
            String normalizedCategory = produk.getKategori().trim().toLowerCase();
            if (VALID_CATEGORIES.contains(normalizedCategory)) {
                produk.setKategori(normalizedCategory);
            } else {
                throw new IllegalArgumentException("Invalid category: " + produk.getKategori());
            }
        }
        return produkRepo.save(produk);
    }

    @PutMapping("/{id}")
    public Produk updateProduk(@PathVariable Integer id, @RequestBody Produk updatedProduk) {
        Produk produk = produkRepo.findById(id).orElse(null);
        if (produk != null) {
            // Validate and normalize category
            if (updatedProduk.getKategori() != null) {
                String normalizedCategory = updatedProduk.getKategori().trim().toLowerCase();
                if (VALID_CATEGORIES.contains(normalizedCategory)) {
                    produk.setKategori(normalizedCategory);
                } else {
                    throw new IllegalArgumentException("Invalid category: " + updatedProduk.getKategori());
                }
            }
            
            produk.setNama(updatedProduk.getNama());
            produk.setDeskripsi(updatedProduk.getDeskripsi());
            produk.setHarga(updatedProduk.getHarga());
            produk.setGambar(updatedProduk.getGambar());
            produk.setLokasi(updatedProduk.getLokasi());
            produk.setStok(updatedProduk.getStok());
            return produkRepo.save(produk);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduk(@PathVariable Integer id) {
        produkRepo.deleteById(id);
    }
}
