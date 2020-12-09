package ro.var.libmngmt.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.var.libmngmt.models.book.Author;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.user.Client;
import ro.var.libmngmt.repository.BookRepository;
import ro.var.libmngmt.repository.ClientRepository;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ClientRepository clientRepository;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<Client> getClients(){return clientRepository.findAll();}

    public Client findClientByUsername(String username){
        return clientRepository.findClientByUsername(username);
    }

    public Client findClientById(int id){
        return clientRepository.findById(id).orElse(null);
    }

    public List<Book> findBooksByAuthor(String keyword){
        List<Book> books = new ArrayList<>();
        for(Book book:bookRepository.findAll()){
            for (Author author:book.getAuthors()){
                if (author.getFirstName().equalsIgnoreCase(keyword) || author.getLastName().equalsIgnoreCase(keyword)){
                    books.add(book);
                }
            }
        }
        return books;
    }

    public List<Book> findBooksByTitle(String keyword){
        List<Book> books = new ArrayList<>();
        for(Book book:bookRepository.findAll()){
            if (book.getTitle().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))){
                books.add(book);
            }
        }
        return books;
    }
}
