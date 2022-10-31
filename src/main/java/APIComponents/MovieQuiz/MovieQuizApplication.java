package APIComponents.MovieQuiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class MovieQuizApplication {

	@Autowired
	private ActorRepository actorRepo;
	public MovieQuizApplication(ActorRepository myActorRepo) {
		this.actorRepo = myActorRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieQuizApplication.class, args);
	}

	@GetMapping("/allActors")
	public @ResponseBody
	Iterable<Actor> getAllActors() {
		return actorRepo.findAll();
	}

	@GetMapping("/{actorID}")
	public @ResponseBody
	Actor getActorByID(@PathVariable("actorID") int actorID) {
		Actor actor = actorRepo.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException("Actor ID doesn't exist in DB ; ; " + actorID));

		return actor;
	}

	@PutMapping("/putActor/{id}")
	public ResponseEntity<Actor> updateActor(@PathVariable(value="id") int actorID,
											 @RequestBody Actor actorDetails) {
		Actor actor = actorRepo.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException("Actor ID doesn't exist in DB ; ; " + actorID));

		actorDetails.setActorID(actorDetails.getActorID());
		actorDetails.setFirstName(actorDetails.getFirstName());
		actorDetails.setLastName(actorDetails.getLastName());

		final Actor updatedActor = actorRepo.save(actorDetails);
		return ResponseEntity.ok(updatedActor);
	}

}
