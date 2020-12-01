package ro.var.libmngmt.models.user;


import ro.var.libmngmt.models.book.BorrowedBook;

import javax.persistence.*;
import java.util.List;

@Entity()
@DiscriminatorValue("ROLE_USER")
public class Client extends User {

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private List<BorrowedBook> borrowedBooks;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_book")
//    private BorrowedBook currentBorrowedBook;

    public Client() {
    }

    public Client(int id, String firstName, String lastName, String username, String password, String email) {
        super(id, firstName, lastName, username, password, email);
    }

}
