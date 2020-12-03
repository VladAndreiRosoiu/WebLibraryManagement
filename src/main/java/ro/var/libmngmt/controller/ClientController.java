package ro.var.libmngmt.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.var.libmngmt.exceptions.BookNotFoundEx;
import ro.var.libmngmt.models.BorrowHistory;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.user.Client;
import ro.var.libmngmt.repository.*;

import java.util.Optional;

@Controller
public class ClientController {


    @Autowired
    BookRepository bookRepository;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }

    @GetMapping("/homepage/showbooks")
    public String getBooks(Model bookModel) {
        bookModel.addAttribute("books", bookRepository.findAll());
        return "displayBooksForClient";
    }

    @GetMapping("/homepage/viewbookdetails/{id}")
    public String getBookDetails(@PathVariable("id") Integer id, Model authorModel, Model genreModel) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            authorModel.addAttribute("authors", bookOpt.get().getAuthors());
            genreModel.addAttribute("genres", bookOpt.get().getGenres());
            return "viewBookDetails";
        } else {
            throw new BookNotFoundEx("Book not found!");
        }
    }

    @GetMapping("/homepage/viewclientdetails")
    public String getClientDetails(Model clientModel) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        clientModel.addAttribute("client", clientRepository.getClient(securityContext.getAuthentication().getName()));
        return "viewClientDetails";
    }

    @GetMapping("/homepage/borrowHistory/{id}")
    public String getBorrowHistory(@PathVariable("id") Integer id ,Model borrowHistory) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()){
            borrowHistory.addAttribute("borrowHistory", clientOptional.get().getBorrowHistory());
            return "viewBorrowHistory";
        }
        for (BorrowHistory borrowHistoryObject : clientOptional.get().getBorrowHistory()) {
            System.out.println(borrowHistoryObject.getBook().getTitle());
            System.out.println(borrowHistoryObject.getBorrowedOn());
            System.out.println(borrowHistoryObject.getReturnedOn());
        }
        return null;
    }

    @GetMapping("/homepage/search")
    public String searchBooks() {
        return null;
    }

    @PostMapping("/homepage/search")
    public String doSearchBook() {
        return null;
    }

}
