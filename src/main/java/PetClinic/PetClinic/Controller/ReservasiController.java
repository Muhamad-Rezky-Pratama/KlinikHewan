package PetClinic.PetClinic.Controller;

import PetClinic.PetClinic.Model.Reservasi;
import PetClinic.PetClinic.Repository.ReservasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservasi")
@CrossOrigin
public class ReservasiController {

    @Autowired
    private ReservasiRepository reservasiRepository;

    @GetMapping
    public List<Reservasi> getAll() {
        return reservasiRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservasi getById(@PathVariable int id) {
        return reservasiRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Reservasi create(@RequestBody Reservasi r) {
        return reservasiRepository.save(r);
    }

    @PutMapping("/{id}")
    public Reservasi update(@PathVariable int id, @RequestBody Reservasi r) {
        Reservasi existing = reservasiRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setUserId(r.getUserId());
            existing.setNamaHewan(r.getNamaHewan());
            existing.setJenisLayanan(r.getJenisLayanan());
            existing.setTanggal(r.getTanggal());
            existing.setCatatan(r.getCatatan());
            existing.setDokter(r.getDokter());
            return reservasiRepository.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        reservasiRepository.deleteById(id);
    }
}
