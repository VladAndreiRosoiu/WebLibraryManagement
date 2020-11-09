package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.var.libmngmt.models.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    int getUserId(String username); // where should I implement this method?
}
