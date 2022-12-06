package APIComponents.MovieQuiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
	private FilmRepository filmRepo;
	private CategoryRepository catRepo;
	public MovieQuizApplication(ActorRepository myActorRepo, FilmRepository myFilmRepo,
								CategoryRepository myCatRepo) {
		this.actorRepo = myActorRepo;
		this.filmRepo = myFilmRepo;
		this.catRepo = myCatRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieQuizApplication.class, args);
	}

	@GetMapping("/allActors")
	public @ResponseBody
	Iterable<Actor> getAllActors() {
		return actorRepo.findAll();
	}

	@GetMapping("/getActor/{actorID}")
	public @ResponseBody
	Actor getActorByID(@PathVariable("actorID") int actorID) {
		Actor actor = actorRepo.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException("Actor ID doesn't exist in DB ; ; " + actorID));

		return actor;
	}

	@PutMapping("/putActor/{actorID}")
	public ResponseEntity<Actor> updateActor(@PathVariable(value="actorID") int actorID,
											 @RequestBody Actor actorDetails) {
		Actor actor = actorRepo.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException("Actor ID doesn't exist in DB ; ; " + actorID));

		actor.setFirstName(actorDetails.getFirstName());
		actor.setLastName(actorDetails.getLastName());

		final Actor updatedActor = actorRepo.save(actor);
		return ResponseEntity.ok(updatedActor);
	}

	@PostMapping("/addActor")
	public ResponseEntity<Actor> addActor(@RequestBody Actor newActor) {
		Actor toAdd = actorRepo.save(newActor);
		return ResponseEntity.ok(toAdd);
	}

	@DeleteMapping("/removeActor")
	public void deleteActor(@RequestBody int actorID) {
		Actor actor = actorRepo.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException("Actor ID doesn't exist in DB ; ; " + actorID));

		actorRepo.deleteById(actorID);
	}

	@GetMapping("/getPActors")
	public @ResponseBody Iterable<Actor> getActorsStartingWithP() {
		return actorRepo.findByNameStartingWithP();
	}

	@GetMapping("/allFilms")
	public @ResponseBody
	Iterable<Film> getAllFilms() {
		return filmRepo.findAll();
	}

	@GetMapping("/films/description/{match}")
	public @ResponseBody
	Iterable<Film> getFilmsAbout(@PathVariable String match) {
		return filmRepo.getFilmsAbout(" "+match+" ");
	}

	@GetMapping("/films/category/{cat}")
	public @ResponseBody
	Iterable<Film> getFilmsInCat(@PathVariable String cat) {
		Category foundCat = catRepo.findByName(cat);
		return foundCat.getFilms();
	}

	@GetMapping("/films/{title}")
	public @ResponseBody
	Film getFilm(@PathVariable String title) {
		return filmRepo.findByTitle(title);
	}

	@GetMapping("/films/title/{title}")
	public @ResponseBody
	Iterable<Category> getCatsOfFilm(@PathVariable String title) {
		Film selectedFilm = filmRepo.findByTitle(title);
		return selectedFilm.getCategories();
	}

	@GetMapping("/actors/in/{title}")
	public @ResponseBody
	Iterable<Actor> getActorsInFilm(@PathVariable String title) {
		Film selectedFilm = filmRepo.findByTitle(title);
		return selectedFilm.getActors();
	}

	@GetMapping("/actors/manyIn/{title}")
	public @ResponseBody
	int noOfActorsInFilm(@PathVariable String title) {
		Film selectedFilm = filmRepo.findByTitle(title);
		return selectedFilm.getActors().size();
	}

}


