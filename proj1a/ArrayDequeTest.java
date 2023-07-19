import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void simpleTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.printDeque();
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(3);
        deque.addFirst(3);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(4);
        deque.addLast(4);
        deque.addLast(4);
        deque.addLast(4);
        deque.addLast(4);
        deque.addLast(4);
        deque.addLast(4);
        deque.addLast(4);
        deque.printDeque();
    }

    @Test
    public void addAndGetTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 8; ++i) {
            deque.addLast(i);
        }
        int actual = deque.get(7);
        assertEquals(7, actual);
    }
}
