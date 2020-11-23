package ro.var.libmngmt.models.book;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "book_title", unique = true)
    private String title;
    @Column(name = "isbn", unique = true)
    private long isbn;
    @Column(name = "stock")
    private int stock;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "id_book")},
            inverseJoinColumns = {@JoinColumn(name = "id_author")})
    private List<Author> authors;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genre",
            joinColumns = {@JoinColumn(name = "id_book")},
            inverseJoinColumns = {@JoinColumn(name = "id_genre")})
    private List<Genre> genres;
    @Column(name = "release_date")
    private LocalDate releaseDate;

    public Book() {
    }

    public Book(int id, String title, long isbn, int stock, List<Author> authors, List<Genre> genres, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.stock = stock;
        this.authors = authors;
        this.genres = genres;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                isbn == book.isbn &&
                stock == book.stock &&
                Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(genres, book.genres) &&
                Objects.equals(releaseDate, book.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, stock, authors, genres, releaseDate);
    }
}
