package APIComponents.MovieQuiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="film")
public class Film {

    @Id
    @Column(name="film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int filmID;

    @Column(name="title")
    String title;

    @Column(name="description")
    String desc;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="film_category",
            joinColumns=@JoinColumn(name="film_id",referencedColumnName="film_id"),
            inverseJoinColumns=@JoinColumn(name="category_id",referencedColumnName="category_id"))
    private List<Category> categories;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns=@JoinColumn(name="film_id",referencedColumnName ="film_id"),
            inverseJoinColumns=@JoinColumn(name="actor_id",referencedColumnName="actor_id"))
    private List<Actor> actors;

    public Film() {

    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Actor> getActors() {
        return actors;
    }
}
