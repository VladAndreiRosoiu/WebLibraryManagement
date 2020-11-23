package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.var.libmngmt.models.book.BorrowedBook;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
}
