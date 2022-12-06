package APIComponents.MovieQuiz;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = MovieQuizApplication.class)

public class CucumberContextConfig {
}
