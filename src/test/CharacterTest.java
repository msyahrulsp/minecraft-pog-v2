import com.aetherwars.model.Character;
import org.junit.Test;
import static org.junit.Assert.*;
import com.aetherwars.model.*;
public class CharacterTest {
    @Test
    public void testCharacter() {
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        assertNotNull(c);
    }
    @Test
    public void testGetType(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        assertEquals(Type.OVERWORLD, c.getType());
    }
    @Test
    public void testGetAttack(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        assertEquals(1, c.getAttack());
    }
    @Test
    public void testGetHealth(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        assertEquals(1, c.getHealth());
    }
    @Test
    public void testGetAttUp(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        assertEquals(1, c.getAttUp());
    }
    @Test
    public void testGetHealthUp(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        assertEquals(1, c.getHealthUp());
    }
    @Test
    public void testSetType(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        c.setType(Type.END);
        assertEquals(Type.END, c.getType());
    }
    @Test
    public void testSetAttack(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        c.setAttack(2);
        assertEquals(2, c.getAttack());
    }
    @Test
    public void testSetHealth(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        c.setHealth(2);
        assertEquals(2, c.getHealth());
    }
    @Test
    public void testSetAttUp(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        c.setAttUp(2);
        assertEquals(2, c.getAttUp());
    }
    @Test
    public void testSetHealthUp(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        c.setHealthUp(2);
        assertEquals(2, c.getHealthUp());
    }
    @Test
    public void testGetLvl(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        assertEquals(1, c.getLvl());
    }
    @Test
    public void testSetLvl(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        c.setLvl(2);
        assertEquals(2, c.getLvl());
    }
    @Test
    public void testGetExp(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        assertEquals(0, c.getExp());
    }
    @Test
    public void testSetExp(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        c.setExp(2);
        assertEquals(2, c.getExp());
    }
    @Test
    public void testAddExp(){
        Character c = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        int lvlxpcap = c.getLvl() * 2 - 1;
        c.addExp(lvlxpcap);
        assertEquals(2, c.getLvl());
    }
}
