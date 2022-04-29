import org.junit.Test;
import static org.junit.Assert.*;
import com.aetherwars.model.*;
public class CardTest {
    @Test
    public void nullCheck(){
        Card card = new Card();
        assertNotNull(card );
    }
    @Test
    public void getCardIDCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        assertEquals(card.getId(), 1);
    }
    @Test
    public void getCardNameCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        assertEquals(card.getName(), "test");
    }
    @Test
    public void getCardDescriptionCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        assertEquals(card.getDesc(), "test");
    }
    @Test
    public void getCardManaCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        assertEquals(card.getMana(), 1);
    }
    @Test
    public void getCardImageLocCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        assertEquals(card.getImageLoc(), "test");
    }
    @Test
    public void setCardIDCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        card.setId(2);
        assertEquals(card.getId(), 2);
    }
    @Test
    public void setCardNameCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        card.setName("test2");
        assertEquals(card.getName(), "test2");
    }
    @Test
    public void setCardDescriptionCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        card.setDesc("test2");
        assertEquals(card.getDesc(), "test2");
    }
    @Test
    public void setCardManaCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        card.setMana(2);
        assertEquals(card.getMana(), 2);
    }
    @Test
    public void setCardImageLocCheck(){
        Card card = new Card(1, "test", "test", 1, "test");
        card.setImageLoc("test2");
        assertEquals(card.getImageLoc(), "test2");
    }
}
