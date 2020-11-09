package ro.var.libmngmt.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ClientController {

    @GetMapping("/client")
    public String clientPage() {
        return "client"; // searches for a users.html template page in resources/templates
    }
}
