public class OffByN implements CharacterComparator {
    private final int N;

    public OffByN() {
        N = 1;
    }

    public OffByN(int x) {
        N = x;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == N;
    }
}
