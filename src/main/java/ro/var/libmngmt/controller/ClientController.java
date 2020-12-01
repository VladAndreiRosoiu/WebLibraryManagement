package ro.var.libmngmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.var.libmngmt.exceptions.BookNotFoundEx;
import ro.var.libmngmt.models.book.*;
import ro.var.libmngmt.models.user.Client;
import ro.var.libmngmt.repository.*;

import java.util.Optional;

@Controller
//@RequestMapping("/homepage") -- check if works only with rest controllers
public class ClientController {

//    add service package
//    private final BookService bookService;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    BorrowHistoryRepository borrowHistoryRepository;

    @GetMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }

    @GetMapping("/homepage/showbooks")
    public String getBooks(Model bookModel) {
        bookModel.addAttribute("books", bookRepository.findAll());
        for (Client client : clientRepository.findAll()){
            System.out.println(client.getId()+ ", " + client.getFirstName() + " " + client.getLastName() + " " + client.getEmail());
        }
        for (Book book : bookRepository.findAll()) {
            System.out.println("Book -> " + book.getTitle());
            System.out.println("Authors : ");
            for (Author author : book.getAuthors()) {
                System.out.println("Author -> " + author.getFirstName() + " " + author.getLastName() + "!");
            }
            System.out.println("Genres : ");
            for (Genre genre : book.getGenres()) {
                System.out.println("Genre -> " + genre.getGenreType() + "!");
            }
        }
        for (BorrowedBook borrowedBook : borrowHistoryRepository.findAll()) {
            System.out.println(borrowedBook.getBook().getTitle() + ", borrowed by "  + borrowedBook.getClient().getFirstName() + " " + borrowedBook.getClient().getLastName());
        }

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
            throw new BookNotFoundEx("Book not found!");
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
