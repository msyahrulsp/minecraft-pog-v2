import org.junit.Test;
import static org.junit.Assert.*;
import com.aetherwars.model.*;

public class PlayerTest {
  @Test
  public void nullCheck() {
    CardHolder deck = new CardHolder();
    assertNotNull(deck);
  }
}