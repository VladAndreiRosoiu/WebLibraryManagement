package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.var.libmngmt.models.book.Author;
import ro.var.libmngmt.models.book.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT id FROM book_author WHERE id_author = ?1;", nativeQuery = true)
    List<Integer> findBooksByAuthor(String keyword);
}
