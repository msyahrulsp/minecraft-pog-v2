import org.junit.Test;
import static org.junit.Assert.*;
import com.aetherwars.model.*;
public class PotionTest {
    @Test
    public void testPotion() {
        Potion potion = new Potion();
        assertNotNull(potion);
    }
    @Test
    public void testGetAttackBuff(){
        Potion potion = new Potion();
        assertEquals(0, potion.getAttackBuff());
    }
    @Test
    public void testGetHealthBuff(){
        Potion potion = new Potion();
        assertEquals(0, potion.getHealthBuff());
    }
    @Test
    public void testSetAttackBuff(){
        Potion potion = new Potion();
        potion.setAttackBuff(1);
        assertEquals(1, potion.getAttackBuff());
    }
    @Test
    public void testSetHealthBuff(){
        Potion potion = new Potion();
        potion.setHealthBuff(1);
        assertEquals(1, potion.getHealthBuff());
    }

}
