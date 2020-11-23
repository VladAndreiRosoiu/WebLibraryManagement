package ro.var.libmngmt.models.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
