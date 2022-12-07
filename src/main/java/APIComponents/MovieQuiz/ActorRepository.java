package APIComponents.MovieQuiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActorRepository extends JpaRepository<Actor,Integer> {

    @Query(value = "SELECT * FROM actor WHERE first_name LIKE \"p%\"", nativeQuery = true)
    Iterable<Actor> findByNameStartingWithP();

    @Query(value = "SELECT * FROM actor WHERE first_name LIKE %:match% OR last_name LIKE %:match%",nativeQuery = true)
    Iterable<Actor> actorsWithName(String match);

}
