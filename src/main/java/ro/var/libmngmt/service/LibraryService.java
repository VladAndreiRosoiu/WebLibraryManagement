package ro.var.libmngmt.service;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.var.libmngmt.models.BorrowInfo;
import ro.var.libmngmt.models.book.Author;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.book.Genre;
import ro.var.libmngmt.models.user.Client;
import ro.var.libmngmt.repository.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    BorrowHistoryRepository borrowHistoryRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    Transaction transaction;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client findClientByUsername(String username) {
        return clientRepository.findClientByUsername(username);
    }

    public Client findClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<Book> findBooksByAuthor(String keyword) {
        List<Book> books = new ArrayList<>();
        for(Author author:authorRepository.findAuthorsByName(keyword)){
            books.addAll(author.getBooks());
        }
        return books;
    }

    public List<Book> findBooksByTitle(String keyword) {
        return bookRepository.findBooksByTitle(keyword);
    }

    public List<Book> findBooksByGenre(String keyword) {
        HashSet<Book> books = new HashSet<>();
        for(Genre genre : genreRepository.findGenres(keyword)){
            books.addAll(genre.getBooks());
        }
        return new ArrayList<>(books);
    }

    public Book findBookByIsbn(long isbn) {
        System.out.println(isbn);
        return bookRepository.findByISBN(isbn);
    }

    public boolean hasBookCurrentlyBorrowed(String username) {
        Client client = clientRepository.findClientByUsername(username);
        for (BorrowInfo borrowInfo : client.getBorrowHistory()) {
            if (borrowInfo.getReturnedOn() == null) {
                return true;
            }
        }
        return false;
    }

    public void returnBook(String username) {
        Client client = clientRepository.findClientByUsername(username);
        for (BorrowInfo borrowInfo : client.getBorrowHistory()) {
            if (borrowInfo.getReturnedOn() == null) {
                borrowInfo.setReturnedOn(LocalDate.now());
                int currentStock = borrowInfo.getBook().getStock();
                borrowInfo.getBook().setStock(++currentStock);
                borrowHistoryRepository.save(borrowInfo);
            }
        }
    }

    public boolean borrowBook(String username, int bookId) {
        if (!hasBookCurrentlyBorrowed(username)) {
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            Optional<BorrowInfo> borrowHistoryOptional = clientRepository.findClientByUsername(username)
                    .getBorrowHistory()
                    .stream()
                    .filter(borrowInfo1 -> borrowInfo1.getReturnedOn() == null)
                    .findFirst();
            Client client = clientRepository.findClientByUsername(username);
            if (!borrowHistoryOptional.isPresent() && bookOptional.isPresent()) {
                borrowHistoryRepository.save(new BorrowInfo(bookOptional.get(), client, LocalDate.now()));
            }
            return true;
        }else {
            System.out.println("ALREADY HAVE BORROWED BOOK");
            return false;
        }
    }

}
