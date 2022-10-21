package APIComponents.MovieQuiz;

import javax.persistence.*;

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

        public Actor(int actorID, String firstName, String lastName) {
            this.actorID = actorID;
            this.firstName = firstName;
            this.lastName = lastName;
        }
}
