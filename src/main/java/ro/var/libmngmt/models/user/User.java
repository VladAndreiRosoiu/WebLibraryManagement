package ro.var.libmngmt.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class User {
    private int id;
    private String firstName;
    private String lastName;
    private String user;
    private String email;
    private UserType userType;
}