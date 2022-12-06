package APIComponents.MovieQuiz;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ScenarioScope
public class ShowSingleActorStepDef {
    @Autowired
    private ActorRepository actorRepo;
    private FilmRepository filmRepo;
    private CategoryRepository catRepo;
    MovieQuizApplication app = new MovieQuizApplication(actorRepo, filmRepo, catRepo);

//    Mockito class attrs
//    private ActorRepository actorRepo = mock(ActorRepository.class);
//    private FilmRepository filmRepo = mock(FilmRepository.class);
//    private CategoryRepository catRepo = mock(CategoryRepository.class);

    private int actorID;
    private Actor chosenActor;

    @Given("An actor exists with ID {int}")
    public void an_actor_exists_with_ID(int id) {
        this.actorID = id;
        this.actorRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Actor doesn't exist"));
    }

//    Mockito version
//    @When("I request that actor's details")
//    public void i_request_that_actors_details() {
//        when(this.actorRepo.findById(this.actorID)).thenReturn(Optional.of(new Actor(this.actorID, "PENELOPE", "GUINESS")));
//        this.chosenActor = this.actorRepo.findById(this.actorID).orElseThrow(() -> new ResourceAccessException("actor doesn't exist"));
//    }

    @When("I request that actor's details")
    public void i_request_that_actor_s_details() {
        this.chosenActor = this.actorRepo.findById(this.actorID)
                .orElseThrow(() -> new ResourceAccessException("Actor doesn't exist in DB"));
    }

    @Then("The webpage should show the actor's {string} and {string}")
    public void the_webpage_should_show_the_actor_s_firstName_and_lastName(String first, String last){
        String correctDetails = first+" "+last;
        String testDetails = "";

        testDetails += this.chosenActor.getFirstName()+" ";
        testDetails += this.chosenActor.getLastName();

        Assertions.assertEquals(correctDetails,testDetails,"Actor's details incorrect");
    }

}
