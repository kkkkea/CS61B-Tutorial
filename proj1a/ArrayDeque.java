public class ArrayDeque<T> {
    private int size;

    private int first;

    private int last;

    private T[] arr;

    private void resize(int s) {
        T[] tmp = (T[]) new Object[s];

        for (int i = first, j = 0; (i % arr.length) <= last; ++i, ++j) {
            tmp[j] = arr[i];
        }
        arr = tmp;
        first = 0;
        last = size - 1;
    }

    public ArrayDeque() {
        first = 0;
        size = 0;
        arr = (T[]) new Object[8];
    }

    public void addFirst(T item) {
        if (size == arr.length) {
            resize(2 * arr.length);
        }

        first = first == 0 ? arr.length - 1 : first - 1;
        arr[first] = item;
        ++size;
    }

    public void addLast(T item) {
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        last = (last + 1) % arr.length;
        arr[last] = item;
        ++size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = first; (i % arr.length) <= last; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size <= 0.25 * arr.length) {
            resize((int) (arr.length * 0.5));
        }

        T res = arr[first];
        arr[first] = null;
        first = (first + 1) % arr.length;
        --size;
        return res;
    }

    public T removeLast() {
        if (size <= 0.25 * arr.length) {
            resize((int) (arr.length * 0.5));
        }

        T res = arr[last];
        arr[last] = null;
        last = last == 0 ? arr.length - 1 : last - 1;
        --size;
        return res;
    }

    public T get(int index) {
        return arr[(first + index) % arr.length];
    }

}
