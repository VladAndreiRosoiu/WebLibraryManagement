package ro.var.libmngmt.models.user;


import ro.var.libmngmt.models.BorrowInfo;
import ro.var.libmngmt.models.book.Genre;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity()
@DiscriminatorValue("ROLE_USER")
public class Client extends User {

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "borrow_user",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_borrow_info")})
    private List<BorrowInfo> borrowInfoList;

    public Client() {
    }

    public Client(int id, String firstName, String lastName, String username, String password, String email, LocalDate registeredOn, List<BorrowInfo> borrowInfoList) {
        super(id, firstName, lastName, username, password, email, registeredOn);
        this.borrowInfoList = borrowInfoList;
    }

    public List<BorrowInfo> getBorrowHistory() {
        return borrowInfoList;
    }

    public void setBorrowHistory(List<BorrowInfo> borrowInfoList) {
        this.borrowInfoList = borrowInfoList;
    }
}
