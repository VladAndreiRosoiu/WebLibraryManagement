package ro.var.libmngmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.repository.AuthorRepository;
import ro.var.libmngmt.repository.BookRepository;
import ro.var.libmngmt.repository.ClientRepository;
import ro.var.libmngmt.repository.GenreRepository;

import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/homepage")
    public String getConsole() {
        return "homepage";
    }

    @GetMapping("/homepage/showbooks")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "displayBooksForClient";
    }

    @GetMapping("/homepage/viewbookdetails/{id}")
    public String getBookDetails(@PathVariable("id") Integer id, Model authorModel, Model genreModel) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            authorModel.addAttribute("authors", authorRepository.findAllById(authorRepository.getAuthorId(id)));
            genreModel.addAttribute("genres", genreRepository.findAllById(genreRepository.getGenreId(id)));
            return "viewBookDetails";
        } else {
            throw new IndexOutOfBoundsException("Book not found!");
        }
    }

    @GetMapping("/homepage/viewclientdetails")
    public String getClientDetails(Model clientModel) {
        SecurityContext sc = SecurityContextHolder.getContext();
        clientModel.addAttribute("client", clientRepository.getClient(sc.getAuthentication().getName()));
        return "viewClientDetails";
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
