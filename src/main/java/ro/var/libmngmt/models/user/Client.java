package ro.var.libmngmt.models.user;


import javax.persistence.*;
import java.util.List;

@Entity()
@DiscriminatorValue("ROLE_USER")
public class Client extends User {

//    private List<BorrowedBook> borrowedBooks;
//    private BorrowedBook currentBorrowedBook;

    public Client() {
    }

    public Client(int id, String firstName, String lastName, String username, String password, String email) {
        super(id, firstName, lastName, username, password, email);
    }

}
