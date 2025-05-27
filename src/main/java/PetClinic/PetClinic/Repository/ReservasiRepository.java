package PetClinic.PetClinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import PetClinic.PetClinic.Model.Reservasi;

public interface ReservasiRepository extends JpaRepository<Reservasi, Integer> {
}
