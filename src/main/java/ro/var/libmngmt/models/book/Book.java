package ro.var.libmngmt.models.book;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {

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
//    private List<Author> authors;
//    private List<String> genres;
    @Column(name = "release_date")
    private LocalDate releaseDate;

}
