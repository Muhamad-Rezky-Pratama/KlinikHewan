package PetClinic.PetClinic.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PetClinic.PetClinic.Model.Reservasi;
import PetClinic.PetClinic.Model.User;
import PetClinic.PetClinic.Repository.ReservasiRepository;
import PetClinic.PetClinic.Repository.UserRepository;

@RestController
@RequestMapping("/api/reservasi")
@CrossOrigin
public class ReservasiRestController {

    @Autowired
    private ReservasiRepository reservasiRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<Reservasi> getAllReservasi() {
        return reservasiRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservasi> getReservasiById(@PathVariable Integer id) {
        return reservasiRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }    @PostMapping
    public ResponseEntity<?> createReservasi(@RequestBody Reservasi reservasi, Principal principal) {
        if (principal != null) {
            User user = userRepo.findByEmail(principal.getName());
            if (user != null) {
                reservasi.setUser(user);
            }
        }
        
        // Keep the date from the form instead of setting it to now
        if (reservasi.getTanggal() == null) {
            reservasi.setTanggal(LocalDate.now());
        }
        
        Reservasi savedReservasi = reservasiRepo.save(reservasi);
        return ResponseEntity.ok(savedReservasi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservasi> updateReservasi(@PathVariable Integer id, @RequestBody Reservasi reservasi) {
        if (!reservasiRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        reservasi.setId(id);
        Reservasi updatedReservasi = reservasiRepo.save(reservasi);
        return ResponseEntity.ok(updatedReservasi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservasi(@PathVariable Integer id) {
        if (!reservasiRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        reservasiRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
