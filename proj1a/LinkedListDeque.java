import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

public class LinkedListDeque<T> {
    private static class Node<T> {
        private T item;
        private Node<T> pre;
        private Node<T> next;

        public Node() {
            this(null, null, null);
        }

        public Node(T item, Node<T> pre, Node<T> next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    private int size;

    private final Node<T> sentinel;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node<>();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(T... items) {
        this.size = 0;
        this.sentinel = new Node<>();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        for (int i = 0; i < items.length; ++i) {
            this.addLast(items[i]);
        }
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        Node<T> tmp = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.pre = tmp;
        sentinel.next = tmp;
        ++size;
    }

    public void addLast(T item) {
        Node<T> tmp = new Node<>(item, sentinel.pre, sentinel);
        sentinel.pre.next = tmp;
        sentinel.pre = tmp;
        ++size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Node<T> cur = this.sentinel.next;
        while (cur != sentinel) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T res = sentinel.next.item;

        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        --size;
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T res = sentinel.pre.item;

        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        --size;
        return res;
    }

    public T get(int index) {
        Node<T> cur = sentinel.next;

        while (index != 0 && cur != sentinel) {
            cur = cur.next;
            index--;
        }

        return cur.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node<T> node, int index) {
        if (index < 0 || node == sentinel) {
            return null;
        }

        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
}
