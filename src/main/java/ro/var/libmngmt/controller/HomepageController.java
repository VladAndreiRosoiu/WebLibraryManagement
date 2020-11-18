package ro.var.libmngmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.var.libmngmt.repository.BookRepository;

@Controller
public class HomepageController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/homepage")
    public String getConsole( ) {
        SecurityContext sc = SecurityContextHolder.getContext();
        System.out.println("Logged User: "+sc.getAuthentication().getName());
        System.out.println("Logged User: "+sc.getAuthentication().getCredentials());
        System.out.println("Logged User: "+sc.getAuthentication().getDetails().toString());
        System.out.println(sc.getAuthentication().getAuthorities());
        return "homepage";
    }

    @GetMapping("/homepage/showbooks")
    public String getBooks(Model model ) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }


}
