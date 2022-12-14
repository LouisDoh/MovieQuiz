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

	final private String FILM_NOT_FOUND = "No film found matching details: ";
	final private String ACTOR_NOT_FOUND = "No actor found matching details: ";
	final private String CAT_NOT_FOUND = "No category found matching details: ";

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
		return actorRepo.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException(ACTOR_NOT_FOUND + actorID));
	}

	@PutMapping("/putActor/{actorID}")
	public ResponseEntity<Actor> updateActor(@PathVariable(value="actorID") int actorID,
											 @RequestBody Actor actorDetails) {
		Actor actor = actorRepo.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException(ACTOR_NOT_FOUND + actorID));

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
	public Actor deleteActor(@RequestBody int actorID) {
		Actor actor = actorRepo.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException(ACTOR_NOT_FOUND + actorID));

		actorRepo.deleteById(actorID);
		return actor;
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
		Category foundCat = catRepo.findByName(cat)
				.orElseThrow(() -> new ResourceAccessException(CAT_NOT_FOUND+": "+cat));
		return foundCat.getFilms();
	}

	@GetMapping("/films/{title}")
	public @ResponseBody
	Film getFilm(@PathVariable String title) {
		return filmRepo.findByTitle(title)
				.orElseThrow(() -> new ResourceAccessException(FILM_NOT_FOUND + title));
	}

	@GetMapping("/films/cats/{title}")
	public @ResponseBody
	Iterable<Category> getCatsOfFilm(@PathVariable String title) {
		Film selectedFilm = filmRepo.findByTitle(title)
				.orElseThrow(() -> new ResourceAccessException(FILM_NOT_FOUND + title));
		return selectedFilm.getCategories();
	}

	@GetMapping("/actors/in/{title}")
	public @ResponseBody
	Iterable<Actor> getActorsInFilm(@PathVariable String title) {
		Film selectedFilm = filmRepo.findByTitle(title)
				.orElseThrow(() -> new ResourceAccessException(FILM_NOT_FOUND + title));
		return selectedFilm.getActors();
	}

	@GetMapping("/actors/manyIn/{title}")
	public @ResponseBody
	int noOfActorsInFilm(@PathVariable String title) {
		Film selectedFilm = filmRepo.findByTitle(title)
				.orElseThrow(() -> new ResourceAccessException(FILM_NOT_FOUND + title));
		return selectedFilm.getActors().size();
	}

	@GetMapping("/actors/byName/{name}")
	public @ResponseBody
	Iterable<Actor> actorsWithName(@PathVariable String name) {
		return actorRepo.actorsWithName(name);
	}

	@GetMapping("/test")
	public @ResponseBody
	FilmCategory test() {
		Film film = filmRepo.findById(1)
				.orElseThrow(() -> new ResourceAccessException("blah"));
		Category cat = catRepo.findById(1)
				.orElseThrow(() -> new ResourceAccessException("blah"));

		FilmCategory filmCat = new FilmCategory(film,cat);

		return filmCat;
	}

}
