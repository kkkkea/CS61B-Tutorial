package hw3.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        int[] buckets = new int[M];
        Arrays.fill(buckets, 0);

        for (Oomage e : oomages) {
            int bucket = (e.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucket]++;
        }

        int low  = N / 50;
        int high = (int) (N / 2.5);
        for (int e : buckets) {
            if (e < low || e > high) {
                return false;
            }
        }

        return true;
    }
}
