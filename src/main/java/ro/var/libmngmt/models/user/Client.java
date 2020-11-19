package ro.var.libmngmt.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ro.var.libmngmt.models.book.BorrowedBook;

import javax.persistence.Entity;
import java.util.List;


@Getter
@Setter
@ToString
@Entity
public class Client extends User {

//    private List<BorrowedBook> borrowedBooks;
//    private BorrowedBook currentBorrowedBook;
//
//    public Client(int id, String firstName, String lastName, String username, String email, List<BorrowedBook> borrowedBooks, BorrowedBook currentBorrowedBook) {
//        super(id, firstName, lastName, username, email, Roles.ROLE_USER);
//        this.borrowedBooks = borrowedBooks;
//        this.currentBorrowedBook = currentBorrowedBook;
//    }


    public Client() {
    }

    public Client(int id, String firstName, String lastName, String username, String email, Roles role) {
        super(id, firstName, lastName, username, email, role);
    }
}
