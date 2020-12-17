package ro.var.libmngmt.service;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.var.libmngmt.models.BorrowHistory;
import ro.var.libmngmt.models.book.Author;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.book.Genre;
import ro.var.libmngmt.models.user.Client;
import ro.var.libmngmt.repository.AuthorRepository;
import ro.var.libmngmt.repository.BookRepository;
import ro.var.libmngmt.repository.BorrowHistoryRepository;
import ro.var.libmngmt.repository.ClientRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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
        List<Book> bookList = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            for (Author author : book.getAuthors()) {
                if (author.getFirstName().equalsIgnoreCase(keyword) || author.getLastName().equalsIgnoreCase(keyword)) {
                    bookList.add(book);
                }
            }
        }
        return bookList;
    }

    public List<Book> findBooksByTitle(String keyword) {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getTitle().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByGenre(String keyword) {
        List<Book> bookList = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            for (Genre genre : book.getGenres()) {
                if (genre.getGenreType().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))) {
                    bookList.add(book);
                }
            }
        }
        return bookList;
    }

    public Book findBookByIsbn(long isbn) {
        return bookRepository.findAll().stream().filter(book -> book.getIsbn() == isbn).findFirst().orElse(null);
    }

    public boolean hasBookCurrentlyBorrowed(String username) {
        Client client = clientRepository.findClientByUsername(username);
        for (BorrowHistory borrowHistory : client.getBorrowHistory()) {
            if (borrowHistory.getReturnedOn() == null) {
                return true;
            }
        }
        return false;
    }

    public void returnBook(String username) {
        Client client = clientRepository.findClientByUsername(username);
        for (BorrowHistory borrowHistory : client.getBorrowHistory()) {
            if (borrowHistory.getReturnedOn() == null) {
                borrowHistory.setReturnedOn(LocalDate.now());
                int currentStock = borrowHistory.getBook().getStock();
                borrowHistory.getBook().setStock(++currentStock);
                borrowHistoryRepository.save(borrowHistory);
            }
        }
    }

    public boolean borrowBook(String username, int bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<BorrowHistory> borrowHistoryOptional = clientRepository.findClientByUsername(username)
                .getBorrowHistory()
                .stream()
                .filter(borrowHistory1 -> borrowHistory1.getReturnedOn() == null)
                .findFirst();
        Client client = clientRepository.findClientByUsername(username);
        if (!borrowHistoryOptional.isPresent() && bookOptional.isPresent()) {
            borrowHistoryRepository.save(new BorrowHistory(bookOptional.get(), client, LocalDate.now()));
            return true;
        } else {
            System.out.println("ALREADY HAVE BORROWED BOOK");
            return false;
        }
    }

}
