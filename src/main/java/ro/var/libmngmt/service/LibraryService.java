package ro.var.libmngmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.var.libmngmt.models.Library;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.user.Client;
import ro.var.libmngmt.repository.AuthorRepository;
import ro.var.libmngmt.repository.BookRepository;
import ro.var.libmngmt.repository.ClientRepository;
import java.util.*;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AuthorRepository authorRepository;

    private final Library library = new Library();

    public LibraryService() {
    }

    public Library getLibrary() {
        return library;
    }

    public List<Client> getClients(){
        return library.getClients();
    }

    public Set<Book> getBooks(){
        return library.getBookStock().keySet();
    }
}
