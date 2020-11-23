package ro.var.libmngmt.models.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity()
@DiscriminatorValue("ROLE_ADMIN")
public class Librarian extends User {

    public Librarian() {
    }

    public Librarian(int id, String firstName, String lastName, String username, String password, String email) {
        super(id, firstName, lastName, username, password, email);
    }
}
