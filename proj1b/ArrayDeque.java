public class ArrayDeque<T> implements Deque<T> {
    private int size;

    private int nextFirst;

    private int nextLast;

    private T[] arr;

    private void resize(int s) {
        T[] tmp = (T[]) new Object[s];

        for (int i = (nextFirst + 1) % arr.length, j = 0, k = 0; k < size; i = (i + 1) % arr.length, ++j, ++k) {
            tmp[j] = arr[i];
        }
        arr = tmp;
        nextFirst = arr.length - 1;
        nextLast = size;
    }

    public ArrayDeque() {
        nextFirst = 0;
        nextLast = 0;
        size = 0;
        arr = (T[]) new Object[8];
    }

    @Override
    public void addFirst(T item) {
        if (size == arr.length) {
            resize(2 * arr.length);
        }

        if (nextFirst == nextLast && size == 0) {
            nextLast = (nextLast + 1) % arr.length;
        }

        arr[nextFirst] = item;
        nextFirst = nextFirst == 0 ? arr.length - 1 : nextFirst - 1;
        ++size;
    }

    @Override
    public void addLast(T item) {
        if (size == arr.length) {
            resize(2 * arr.length);
        }

        if (nextFirst == nextLast && size == 0) {
            nextFirst = nextFirst == 0 ? arr.length - 1 : nextFirst - 1;
        }

        arr[nextLast] = item;
        nextLast = (nextLast + 1) % arr.length;
        ++size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = (nextFirst + 1) % arr.length, j = 0; j < size; i = (i + 1) % arr.length, j++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (size <= 0.25 * arr.length) {
            resize((int) (arr.length * 0.5));
        }

        T res = arr[(nextFirst + 1) % arr.length];
        arr[(nextFirst + 1) % arr.length] = null;
        nextFirst = (nextFirst + 1) % arr.length;
        --size;
        return res;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (size <= 0.25 * arr.length) {
            resize((int) (arr.length * 0.5));
        }

        int last = nextLast == 0 ? arr.length - 1 : nextLast - 1;
        T res = arr[last];
        arr[last] = null;
        nextLast = last;
        --size;
        return res;
    }

    @Override
    public T get(int index) {
        return arr[(nextFirst + index + 1) % arr.length];
    }

}
