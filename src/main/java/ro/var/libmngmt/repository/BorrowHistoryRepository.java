package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.var.libmngmt.models.book.BorrowHistory;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Integer> {
}
