package PetClinic.PetClinic.Controller;

import PetClinic.PetClinic.Model.Produk;
import PetClinic.PetClinic.Repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produk")
@CrossOrigin
public class ProdukController {

    @Autowired
    private ProdukRepository produkRepo;

    // GET all produk
    @GetMapping
    public List<Produk> getAllProduk() {
        return produkRepo.findAll();
    }

    // GET produk by id
    @GetMapping("/{id}")
    public Produk getProdukById(@PathVariable Integer id) {
        return produkRepo.findById(id).orElse(null);
    }

    // POST tambah produk
    @PostMapping
    public Produk createProduk(@RequestBody Produk produk) {
        return produkRepo.save(produk);
    }

    // PUT update produk
    @PutMapping("/{id}")
    public Produk updateProduk(@PathVariable Integer id, @RequestBody Produk updatedProduk) {
        Produk produk = produkRepo.findById(id).orElse(null);
        if (produk != null) {
            produk.setNama(updatedProduk.getNama());
            produk.setKategori(updatedProduk.getKategori());
            produk.setStok(updatedProduk.getStok());
            produk.setHarga(updatedProduk.getHarga());
            return produkRepo.save(produk);
        }
        return null;
    }

    // DELETE produk
    @DeleteMapping("/{id}")
    public void deleteProduk(@PathVariable Integer id) {
        produkRepo.deleteById(id);
    }
}
