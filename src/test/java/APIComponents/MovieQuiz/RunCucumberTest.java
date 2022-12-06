package APIComponents.MovieQuiz;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.plugin.Plugin;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
features = "src/test/resources/Cucumber",
glue = "")
public class RunCucumberTest {

}
