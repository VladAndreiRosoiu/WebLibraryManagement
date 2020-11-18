package ro.var.libmngmt.models.user;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Librarian extends User{
    public Librarian(int id, String firstName, String lastName, String username, String email) {
        super(id, firstName, lastName, username, email, Roles.ROLE_ADMIN);
    }
}
