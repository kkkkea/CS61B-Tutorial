package hw2;
import java.util.Arrays;

public class Percolation {
    private final boolean[][] mat;
    private final DisjointSet set;

    private int size;

    private static class DisjointSet {
        int[] parents;
        int[] size;

        public DisjointSet(int N) {
            parents = new int[N];
            size = new int[N];
            Arrays.fill(parents, -1);
            Arrays.fill(size, 1);
        }

        public void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            if (size[p1] > size[p2]) {
                parents[p2] = p1;
                size[p1] += size[p2];
            } else  {
                parents[p1] = p2;
                size[p2] += size[p1];
            }
        }

        public boolean isConnect(int x, int y) {
            return find(x) == find(y);
        }

        private int find(int x) {
            if (parents[x] == -1) {
                return x;
            }
            int p = find(parents[x]);
            parents[x] = p;
            return p;
        }
    }

    public Percolation(int N) {
        mat = new boolean[N][N];
        set = new DisjointSet(N * N);
    }

    public void open(int row, int col) {
        if (row < 0 || row >= mat.length || col < 0 || col >= mat.length) {
            throw new IndexOutOfBoundsException();
        }

        mat[row][col] = true;
        if (row - 1 >= 0 && mat[row - 1][col]) {
            set.union(row * mat.length + col, (row - 1) * mat.length + col);
        }

        if (row + 1 < mat.length && mat[row + 1][col]) {
            set.union(row * mat.length + col, (row + 1) * mat.length + col);
        }

        if (col - 1 >= 0 && mat[row][col - 1]) {
            set.union(row * mat.length + col, row * mat.length + col - 1);
        }

        if (col + 1 < mat.length && mat[row][col + 1]) {
            set.union(row * mat.length + col, row * mat.length + col + 1);
        }

        ++size;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= mat.length || col < 0 || col >= mat.length) {
            throw new IndexOutOfBoundsException();
        }

        return mat[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= mat.length || col < 0 || col >= mat.length) {
            throw new IndexOutOfBoundsException();
        }
        return this.isOpen(row, col) && set.find(row * mat.length + col) < mat.length;
    }

    public int numberOfOpenSites() {
        return size;
    }

    public boolean percolates() {
        int top = 0;
        int bottom = (int) (Math.pow(mat.length, 2) - 1);

        for (int i = 0; i < mat.length; ++i) {
            if (isOpen(0, i)) {
                top = i;
                break;
            }
        }

        for (int i = 0; i < mat.length; ++i) {
            if (isOpen(mat.length - 1, i)) {
                bottom = (mat.length - 1) * mat.length + i;
                break;
            }
        }

        return set.isConnect(top, bottom);
    }
}
