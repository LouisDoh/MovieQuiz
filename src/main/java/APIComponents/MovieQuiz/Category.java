package APIComponents.MovieQuiz;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category")
public class Category {

    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int catID;

    @Column(name="name")
    String catName;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<Film> films;

    public Category() {

    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<Film> getFilms() {
        return films;
    }
}
