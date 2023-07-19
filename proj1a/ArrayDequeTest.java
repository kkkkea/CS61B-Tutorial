import org.junit.Test;
import org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void simpleTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.printDeque();
    }
}
