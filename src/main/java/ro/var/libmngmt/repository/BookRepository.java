package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.var.libmngmt.models.book.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
