package APIComponents.MovieQuiz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ActorTest {

    @Test
    public void test_constructor() {
        Actor testActor = new Actor(1,"PENELOPE","GUINESS");
        Assertions.assertEquals(1,testActor.getActorID(),"Actor has wrong ID.");
    }
}
