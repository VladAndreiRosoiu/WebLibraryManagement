package ro.var.libmngmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

//    @GetMapping("/homepage/viewbookdetails/{id}")
//    public String getBookDetails(@PathVariable("id") Integer id, Model authorModel, Model genreModel) {
//        Optional<Book> bookOptional = bookRepository.findById(id);
//        if (bookOptional.isPresent()) {
//            authorModel.addAttribute("authors",bookOptional.get().getAuthors());
//            genreModel.addAttribute("genres", bookOptional.get().getGenres());
//            return "viewBookDetails";
//        } else {
//            throw new BookNotFoundEx("Book not found!");
//        }
//    }

//    @GetMapping("/homepage/viewclientdetails")
//    public String getClientDetails(Model clientModel) {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        clientModel.addAttribute("client", libraryService.findClientByUsername(securityContext.getAuthentication().getName()));
//        return "viewClientDetails";
//    }
//
//    @GetMapping("/homepage/borrowHistory/{id}")
//    public String getBorrowHistory(@PathVariable("id") Integer id, Model borrowHistory) {
//        if (libraryService.findClientById(id)!=null) {
//            borrowHistory.addAttribute("borrowHistory", libraryService.findClientById(id).getBorrowHistory());
//            return "viewBorrowHistory";
//        } else {
//            throw new UserNotFoundEx("User with id " + id + "could not be found!");
//        }
//
//    }
//
//    @GetMapping("/homepage/search")
//    public String searchBooks() {
//        return "searchBook";
//    }
//
//    @GetMapping("/homepage/searchByAuthor")
//    public String searchByAuthor() {
//        return "searchByAuthor";
//    }
//
//    @RequestMapping("/homepage/searchByAuthor")
//    public String getSearchByAuthor(Model bookModel, @Param("keyword") String keyword){
//        List<Integer> authors = authorRepository.findByName(keyword);
//        List<Book> bookList = bookRepository.findAllById(authors);
//        bookModel.addAttribute("books", bookList);
//        return "displayBooksForClient";
//    }
//
//    @GetMapping("/homepage/searchByTitle")
//    public String searchByTitle() {
//        return null;
//    }
//
//    @GetMapping("/homepage/searchByISBN")
//    public String searchByISBN() {
//        return null;
//    }
//
//    @GetMapping("/homepage/searchByGenre")
//    public String searchByGenre() {
//        return null;
//    }

}
