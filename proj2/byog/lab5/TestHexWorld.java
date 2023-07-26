package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestHexWorld {
    @Test
    public void TestHexLine() {
        HexWorld hexWorld = new HexWorld();
        hexWorld.addHexagon(3);
    }
}
