package ro.var.libmngmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.book.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query(value = "SELECT id_genre FROM book_genre WHERE id_book = ?1", nativeQuery = true)
    List<Integer> getGenreId(Integer bookId);

    @Query(value = "SELECT * FROM genres WHERE genre_type LIKE %?1%", nativeQuery = true)
    List<Genre> findGenres(String keyword);
}
