package PetClinic.PetClinic.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PetClinic.PetClinic.Model.Pembelian;
import PetClinic.PetClinic.Model.User;

public interface PembelianRepository extends JpaRepository<Pembelian, Integer> {
    List<Pembelian> findByUser(User user);
}