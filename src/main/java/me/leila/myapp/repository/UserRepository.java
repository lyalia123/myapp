package me.leila.myapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import me.leila.myapp.model.Usr;
public interface UserRepository extends JpaRepository<Usr, Long> {
    Optional<Usr> findByEmail(String Email);
}
