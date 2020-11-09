package ro.var.libmngmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ro.var.libmngmt.models.user.Client;
import ro.var.libmngmt.models.user.Librarian;
import ro.var.libmngmt.models.user.User;
import ro.var.libmngmt.repository.UserRepository;
import ro.var.libmngmt.services.UserService;
import ro.var.libmngmt.services.UserServiceImpl;

public class LoginController {

    @Autowired
    private UserRepository userRepository;
    private final UserService userService = new UserServiceImpl();

    @GetMapping("/welcome")
    public String getUsers() {
        return "welcome"; // searches for a users.html template page in resources/templates
    }

    @PostMapping
    public String doLogin(String username, String password) {
        User user = userService.getUser(userRepository.getUserId(username), userService.getPassword(password));
        if (user instanceof Client) {
            return "redirect:/client";
        } else if (user instanceof Librarian) {
            return "redirect:/librarian";
        } else {
            return "redirect:/welcome";
        }
    }
}
