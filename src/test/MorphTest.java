import org.junit.Test;
import static org.junit.Assert.*;
import com.aetherwars.model.*;
public class MorphTest {
    @Test
    public void testMorph() {
        Morph morph = new Morph();
        assertNotNull(morph);
    }
    @Test
    public void testGetTarget(){
        Morph morph = new Morph();
        assertEquals(0, morph.getTarget());
    }
    @Test
    public void testSetTarget(){
        Morph morph = new Morph();
        morph.setTarget(1);
        assertEquals(1, morph.getTarget());
    }
}
