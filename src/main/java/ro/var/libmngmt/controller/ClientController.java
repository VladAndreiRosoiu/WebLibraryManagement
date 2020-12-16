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
import ro.var.libmngmt.service.LibraryService;


@Controller
public class ClientController {

    @Autowired
    LibraryService libraryService;

    @GetMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }

    @GetMapping("/homepage/showbooks")
    public String getBooks(Model bookModel) {
        bookModel.addAttribute("books", libraryService.getBooks());
        return "displayBooksForClient";
    }

    @GetMapping("/homepage/viewbookdetails/{id}")
    public String getBookDetails(@PathVariable("id") Integer id, Model authorModel, Model genreModel) {
        Book searchedBook = libraryService.findBookById(id);
        if (searchedBook != null) {
            authorModel.addAttribute("authors", searchedBook.getAuthors());
            genreModel.addAttribute("genres", searchedBook.getGenres());
            return "viewBookDetails";
        } else {
            throw new BookNotFoundEx("Book not found!");
        }
    }

    @GetMapping("/homepage/viewclientdetails")
    public String getClientDetails(Model clientModel) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Client client = libraryService.findClientByUsername(securityContext.getAuthentication().getName());
        if (client != null) {
            clientModel.addAttribute("client", libraryService.findClientByUsername(securityContext.getAuthentication().getName()));
            return "viewClientDetails";
        } else {
            throw new UserNotFoundEx("User not found!");
        }

    }

    @GetMapping("/homepage/borrowHistory/{id}")
    public String getBorrowHistory(@PathVariable("id") Integer id, Model borrowHistory) {
        Client client = libraryService.findClientById(id);
        if (client != null) {
            borrowHistory.addAttribute("borrowHistory", client.getBorrowHistory());
            return "viewBorrowHistory";
        } else {
            throw new UserNotFoundEx("User not found!");
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
    public String getSearchByAuthor(Model bookModel, @Param("keyword") String keyword) {
        bookModel.addAttribute("books", libraryService.findBooksByAuthor(keyword));
        return "displayBooksForClient";
    }

    @GetMapping("/homepage/searchByTitle")
    public String searchByTitle() {
        return "searchByTitle";
    }

    @RequestMapping("/homepage/searchByTitle")
    public String getSearchByTitle(Model bookModel, @Param("keyword") String keyword) {
        bookModel.addAttribute("books", libraryService.findBooksByTitle(keyword));
        return "displayBooksForClient";
    }

    @GetMapping("/homepage/searchByISBN")
    public String searchByISBN() {
        return "searchByISBN";
    }

    @RequestMapping("/homepage/searchByISBN")
    public String getSearchByISBN(Model bookModel, @Param("keyword") Long isbn) {
        if (libraryService.findBookByIsbn(isbn) != null) {
            bookModel.addAttribute("books", libraryService.findBookByIsbn(isbn));
            return "displayBooksForClient";
        } else {
            throw new BookNotFoundEx("Book not found!");
        }
    }

    @GetMapping("/homepage/searchByGenre")
    public String searchByGenre() {
        return "searchByGenre";
    }

    @RequestMapping("/homepage/searchByGenre")
    public String getSearchByGenre(Model bookModel, @Param("keyword") String keyword) {
        bookModel.addAttribute("books", libraryService.findBooksByGenre(keyword));
        return "displayBooksForClient";
    }

    @GetMapping("/homepage/returnBook")
    public String returnBook(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (libraryService.hasBookCurrentlyBorrowed(securityContext.getAuthentication().getName())){
            libraryService.returnBook(securityContext.getAuthentication().getName());
            System.out.println("Book returned");
            return "redirect:/homepage";
        }else{
            System.out.println("No book to return!");
            throw new BookNotFoundEx("You haven't borrowed any book yet!");
        }
    }

    @GetMapping("/homepage/borrowBook/{id}")
    public String borrowBook(@PathVariable("id") Integer id){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        libraryService.borrowBook(securityContext.getAuthentication().getName(), id);
        return "redirect:/homepage";
    }

}
