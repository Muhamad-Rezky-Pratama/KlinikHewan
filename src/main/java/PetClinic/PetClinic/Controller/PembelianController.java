package PetClinic.PetClinic.Controller;

import java.time.LocalDateTime;
import java.util.List;

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
import PetClinic.PetClinic.Repository.PembelianRepository;

@RestController
@RequestMapping("/api/pembelian")
@CrossOrigin
public class PembelianController {

    @Autowired
    private PembelianRepository pembelianRepo;

    @GetMapping
    public List<Pembelian> getAll() {
        return pembelianRepo.findAll();
    }

    @PostMapping
    public Pembelian create(@RequestBody Pembelian pembelian) {
        pembelian.setTanggal(LocalDateTime.now());
        return pembelianRepo.save(pembelian);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pembelianRepo.deleteById(id);
    }
}
