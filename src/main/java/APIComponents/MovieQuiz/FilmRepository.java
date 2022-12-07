package APIComponents.MovieQuiz;

import com.mysql.cj.jdbc.IterateBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film,Integer>{

    @Query(value="SELECT * FROM film WHERE description LIKE %:match%",nativeQuery = true)
    Iterable<Film> getFilmsAbout(String match);

    @Query(value="SELECT * FROM film WHERE title = :match",nativeQuery = true)
    Optional<Film> findByTitle(String match);

}
