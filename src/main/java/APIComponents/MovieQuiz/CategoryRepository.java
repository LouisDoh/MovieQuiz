package APIComponents.MovieQuiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

    @Query(value="SELECT * FROM category WHERE name = :name",nativeQuery = true)
    Optional<Category> findByName(String name);
}
