package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.var.libmngmt.models.BorrowHistory;


public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Integer> {
}
