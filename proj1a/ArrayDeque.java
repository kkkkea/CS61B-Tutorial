public class ArrayDeque<T> {
    private int size;

    private int nextFirst;

    private int nextLast;

    private T[] arr;

    private void resize(int s) {
        T[] tmp = (T[]) new Object[s];

        for (int i = nextFirst + 1, j = 0; (i % arr.length) < nextLast; ++i, ++j) {
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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = (nextFirst + 1) % arr.length, j = 0; j < size; i = (i + 1) % arr.length, j++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

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

    public T get(int index) {
        return arr[(nextFirst + index + 1) % arr.length];
    }

}
