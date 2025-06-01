package PetClinic.PetClinic.Repository;

import PetClinic.PetClinic.Model.Pembelian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PembelianRepository extends JpaRepository<Pembelian, Integer> {
}