package PetClinic.PetClinic.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import PetClinic.PetClinic.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
