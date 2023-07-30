package hw2;

import java.util.Arrays;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    int N;
    int T;
    PercolationFactory pf;
    int[] count;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        count = new int[T];
        this.N = N;
        this.T = T;
        this.pf = pf;
        simulate();
    }

    private void simulate() {
        for (int i = 0; i < T; ++i) {
            int cnt = 0;
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);

                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    ++cnt;
                }
            }
            count[i] = cnt;
        }
    }

    public double mean() {
        return  StdStats.mean(count);
    }

    public double stddev() {
        return StdStats.stddev(count);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
