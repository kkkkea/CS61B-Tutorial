// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;
        ++fillCount;
        last = (last + 1) % capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw  new RuntimeException("Ring Buffer Underflow");
        }

        T res = rb[first];
        rb[first] = null;
        --fillCount;
        first = (first + 1) % capacity;
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw  new RuntimeException("Ring Buffer Underflow");
        }

        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.

    private class BufferIterator implements Iterator<T> {
        private int index;
        private int step;

        public BufferIterator() {
            index = first;
            step = 0;
        }

        @Override
        public boolean hasNext() {
            return step < fillCount;
        }

        @Override
        public T next() {
            ++step;
            T res =  rb[index];
            index = (index + 1) % capacity;
            return res;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    public static void main(String[] args) {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        for (int i = 0; i < 10; ++i) {
            arb.enqueue(i);
        }

        Iterator<Integer> itr = arb.new BufferIterator();

        /*while (itr.hasNext()) {
            System.out.println(itr.next());
        }*/

        for (int ele : arb) {
            System.out.println(ele);
        }
    }
}
