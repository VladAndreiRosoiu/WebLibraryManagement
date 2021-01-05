package ro.var.libmngmt.models.user;


import ro.var.libmngmt.models.BorrowInfo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity()
@DiscriminatorValue("ROLE_USER")
public class Client extends User {

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
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
