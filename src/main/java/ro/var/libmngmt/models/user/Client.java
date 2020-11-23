package ro.var.libmngmt.models.user;

import ro.var.libmngmt.models.book.BorrowedBook;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity(name = "client")
@DiscriminatorValue("ROLE_USER")
public class Client extends User {

//    private List<BorrowedBook> borrowedBooks;
//    private BorrowedBook currentBorrowedBook;

    public Client() {
    }

    public Client(int id, String firstName, String lastName, String username, String password, String email) {
        super(id, firstName, lastName, username, password, email, Roles.ROLE_USER);
    }
}
