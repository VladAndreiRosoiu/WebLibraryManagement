package ro.var.libmngmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/welcome")
    public String welcomePage(){
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
