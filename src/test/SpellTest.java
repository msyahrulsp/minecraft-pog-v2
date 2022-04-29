import org.junit.Test;
import static org.junit.Assert.*;
import com.aetherwars.model.*;
public class SpellTest {
    @Test
    public void testSpell() {
        Spell spell = new Spell();
        assertNotNull(spell);
    }
    @Test
    public void testGetType(){
        Spell spell = new Spell();
        assertEquals(spellType.PTN, spell.getType());
    }
    @Test
    public void testSetType(){
        Spell spell = new Spell();
        spell.setType(spellType.LVL);
        assertEquals(spellType.LVL, spell.getType());
    }
}
