package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.var.libmngmt.models.book.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = "SELECT id_author FROM book_author WHERE id_book = ?1", nativeQuery = true)
    List<Integer> getAuthorId(Integer bookId);

    @Query(value = "SELECT id FROM authors WHERE first_name LIKE ?1 OR last_name LIKE ?1;", nativeQuery = true)
    List<Integer> findByName(String keyword);
}
