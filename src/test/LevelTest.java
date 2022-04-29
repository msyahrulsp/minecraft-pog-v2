import org.junit.Test;
import static org.junit.Assert.*;
import com.aetherwars.model.*;
public class LevelTest {
    @Test
    public void testLevel() {
        Level level = new Level("LVLUP");
        assertNotNull(level);
    }
    @Test
    public void testGetModifierType() {
        Level level = new Level("LVLUP");
        assertEquals("LVLUP", level.getModifierType() );
    }
    @Test
    public void testSetModifierType() {
        Level level = new Level("LVLUP");
        level.setModifierType("LVLDOWN");
        assertEquals("LVLDOWN", level.getModifierType());
    }
}
