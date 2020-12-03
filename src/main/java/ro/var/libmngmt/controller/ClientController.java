package ro.var.libmngmt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.var.libmngmt.exceptions.BookNotFoundEx;
import ro.var.libmngmt.exceptions.UserNotFoundEx;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.user.Client;
import ro.var.libmngmt.repository.AuthorRepository;
import ro.var.libmngmt.repository.BookRepository;
import ro.var.libmngmt.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {


    @Autowired
    BookRepository bookRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AuthorRepository authorRepository;

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
    public String getBorrowHistory(@PathVariable("id") Integer id, Model borrowHistory) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            borrowHistory.addAttribute("borrowHistory", clientOptional.get().getBorrowHistory());
            return "viewBorrowHistory";
        } else {
            throw new UserNotFoundEx("User with id " + id + "could not be found!");
        }

    }

    @GetMapping("/homepage/search")
    public String searchBooks() {
        return "searchBook";
    }

    @GetMapping("/homepage/searchByAuthor")
    public String searchByAuthor() {
        return "searchByAuthor";
    }

    @RequestMapping("/homepage/searchByAuthor")
    public String getSearchByAuthor(Model bookModel, @Param("keyword") String keyword){
        List<Integer> authors = authorRepository.findByName(keyword);
        List<Book> bookList = bookRepository.findAllById(authors);
        bookModel.addAttribute("books", bookList);
        return "displayBooksForClient";
    }

    @GetMapping("/homepage/searchByTitle")
    public String searchByTitle() {
        return null;
    }

    @GetMapping("/homepage/searchByISBN")
    public String searchByISBN() {
        return null;
    }

    @GetMapping("/homepage/searchByGenre")
    public String searchByGenre() {
        return null;
    }
}
