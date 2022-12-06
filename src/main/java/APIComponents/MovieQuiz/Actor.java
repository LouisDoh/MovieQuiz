package APIComponents.MovieQuiz;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="actor")
public class Actor {
    @Id
    @Column(name="actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int actorID;

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @JsonIgnore
    @ManyToMany(mappedBy = "actors")
    private List<Film> films;

    public Actor() {

    }

    public Actor(int ID, String first, String last) {
        this.actorID = ID;
        this.firstName = first;
        this.lastName = last;
    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
