package ro.var.libmngmt.models.book;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "genre_type", unique = true)
    private String genreType;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_genre",
            joinColumns = {@JoinColumn(name = "id_genre")},
            inverseJoinColumns = {@JoinColumn(name = "id_book")})
    private List<Book> books;

    public Genre() {
    }

    public Genre(int id, String genreType) {
        this.id = id;
        this.genreType = genreType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
