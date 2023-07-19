import org.junit.Test;
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
    public void getTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(3);
        deque.addFirst(8);
        deque.addFirst(2);
        deque.addLast(4);
        deque.addLast(6);
        deque.addLast(5);
        deque.addLast(4);

        int actual = deque.get(0);
        assertEquals(2, actual);
        int actual1 = deque.get(1);
        assertEquals(8, actual1);
    }
}
