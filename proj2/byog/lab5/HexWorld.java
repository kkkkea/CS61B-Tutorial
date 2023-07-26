package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public void addHexagon(int size) {
        int maxLen = 3 * size - 2;

        int i = 0;
        int j = size;
        while (i < 2 * size) {
            System.out.println(getHexSingleLine(maxLen, j));
            if (i < size - 1) {
                j += 2;
            } else if (i >= size) {
                j -= 2;
            }
            ++i;
        }
    }

    private String getHexSingleLine(int maxLen, int num) {
        char[] chars = new char[maxLen];

        int sp = (maxLen - num) / 2;
        for (int i = 0; i < maxLen; ++i) {
            if (i >= sp && i < sp + num) {
                chars[i] = 'a';
            } else  {
                chars[i] = ' ';
            }

        }
        return new String(chars);
    }

    public static void main(String[] args) {
        HexWorld hexWorld = new HexWorld();
        System.out.println(hexWorld.getHexSingleLine(7, 3));
    }
}
