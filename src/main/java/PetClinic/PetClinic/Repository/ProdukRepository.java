package PetClinic.PetClinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import PetClinic.PetClinic.Model.Produk;

public interface ProdukRepository extends JpaRepository<Produk, Integer> {
}
