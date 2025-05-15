package PetClinic.PetClinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import PetClinic.PetClinic.Model.Hewan;

public interface HewanRepository extends JpaRepository<Hewan, Long> {
}
