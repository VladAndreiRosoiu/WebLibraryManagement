package ro.var.libmngmt.models.book;
import ro.var.libmngmt.models.user.Client;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_user")
public class BorrowHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book")
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Client client;
//    @OneToOne
//    @JoinColumn(name = "borrowed_on")
//    private LocalDate borrowedOn;
//    @OneToOne
//    @JoinColumn(name = "returned_on")
//    private LocalDate returnedOn;


    public BorrowHistory() {
    }

    public BorrowHistory(Book book, Client client) {
        this.book = book;
        this.client = client;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
