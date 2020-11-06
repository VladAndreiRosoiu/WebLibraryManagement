package ro.var.libmngmt.models.user;

public class Librarian extends User {
    public Librarian(int id, String firstName, String lastName, String user, String email) {
        super(id, firstName, lastName, user, email, UserType.LIBRARIAN);
    }
}
