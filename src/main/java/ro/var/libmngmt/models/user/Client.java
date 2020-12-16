package ro.var.libmngmt.models.user;


import ro.var.libmngmt.models.BorrowHistory;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity()
@DiscriminatorValue("ROLE_USER")
public class Client extends User {

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private List<BorrowHistory> borrowHistory;

    public Client() {
    }

    public Client(int id, String firstName, String lastName, String username, String password, String email, LocalDate registeredOn, List<BorrowHistory> borrowHistory) {
        super(id, firstName, lastName, username, password, email, registeredOn);
        this.borrowHistory = borrowHistory;
    }

    public List<BorrowHistory> getBorrowHistory() {
        return borrowHistory;
    }

    public void setBorrowHistory(List<BorrowHistory> borrowHistory) {
        this.borrowHistory = borrowHistory;
    }
}
