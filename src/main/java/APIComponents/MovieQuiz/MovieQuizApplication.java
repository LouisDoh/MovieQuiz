package APIComponents.MovieQuiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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

}
