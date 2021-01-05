package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.var.libmngmt.models.BorrowInfo;

public interface BorrowHistoryRepository extends JpaRepository<BorrowInfo, Integer> {

}
