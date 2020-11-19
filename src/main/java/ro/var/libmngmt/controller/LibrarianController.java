package ro.var.libmngmt.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LibrarianController {

    @GetMapping("/console")
    public String getConsole( ) {
        SecurityContext sc = SecurityContextHolder.getContext();
        System.out.println("Logged User: "+sc.getAuthentication().getName());
        return "console";
    }
}
