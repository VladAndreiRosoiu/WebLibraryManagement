package ro.var.libmngmt.services;


import ro.var.libmngmt.models.user.User;

public interface UserService {

    String getPassword(String password);

    User getUser(int id, String password);

}
