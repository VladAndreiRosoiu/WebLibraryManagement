package ro.var.libmngmt.services;

import ro.var.libmngmt.models.user.User;

public class UserServiceImpl implements UserService{

    @Override
    public String getPassword(String password) {
        StringBuilder hashPass = new StringBuilder();
        char[] charArrPass = password.toCharArray();
        for (int i = 0; i < charArrPass.length; i++) {
            hashPass.append(Character.hashCode(charArrPass[i]));
            if (i % 2 == 0) {
                hashPass.append(Character.hashCode(charArrPass[i])).append("&").append(charArrPass[i]).append("^");
            } else {
                hashPass.append(Character.hashCode(charArrPass[i])).append("@").append(charArrPass[i]).append("(");
            }
            if (i % 2 == 1) {
                hashPass.append(Character.hashCode(charArrPass[i])).append("%").append(charArrPass[i]).append(")");
            } else {
                hashPass.append(Character.hashCode(charArrPass[i])).append("$").append(charArrPass[i]).append(";");
            }
        }
        return hashPass.toString();
    }

    @Override
    public User getUser(int id, String password) {
        return null;
    }

}
