package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.var.libmngmt.models.user.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    Client getClient(String name);
}
