package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.var.libmngmt.models.user.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    Client getClient(String username);

    @Query(value = "SELECT id_book FROM borrowed_book_user WHERE id_user = ?1", nativeQuery = true)
    List<Integer> getBookId(Integer clientId);
}
