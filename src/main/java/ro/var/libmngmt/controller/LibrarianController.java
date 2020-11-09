package ro.var.libmngmt.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LibrarianController {
    @GetMapping("/client")
    public String librarianPage() {
        return "librarian"; // searches for a users.html template page in resources/templates
    }
}
