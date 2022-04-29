import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import com.aetherwars.model.*;

public class PlayerTest {
  @Test
  public void nullTest() {
    Player player = new Player("kevin", 80, 1);
    assertNotNull(player);
  }

  @Test
  public void playerAttrTest() {
    Player player = new Player("kevin", 80, 1);
    assertEquals(player.getName(), "kevin");
  }

  @Test
  public void loadDeckTest() {
    Player player = new Player("kevin", 80, 1);
    try {
      player.getDeck().loadDeck();
    } catch (Exception e) {
      assert false;
    }
    assertEquals(player.getDeck().getSize(), 60);
  }

  @Test
  public void phaseTest() {
    Player player = new Player("kevin", 80, 1);
    try {
      player.getDeck().loadDeck();
      player.getCardToHand("first");
      assertEquals(player.getHand().getSize(), 3);
      assertEquals(player.getDeck().getSize(), 57);
      player.getCardToHand("add");
      assertEquals(player.getAddCard().getSize(), 3);
      assertEquals(player.getDeck().getSize(), 54);
      player.addCardToHand(1);
      assertEquals(player.getHand().getSize(), 4);
      player.throwCardFromHand((1));
      assertEquals(player.getHand().getSize(), 3);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  public void addThrowTest() {
    Player player = new Player("kevin", 80, 1);
    try {
      player.getDeck().loadDeck();
      player.getAddCard().addElmt(player.getDeck().getElmt(0));
    } catch (Exception e) {
      assert false;
    }
    assertEquals(player.getAddCard().getSize(), 1);

  }
}