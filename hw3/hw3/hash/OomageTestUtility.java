package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
        int min = N/50;
        int max = (int) (N/2.5);

        HashMap<Integer, Integer> tmp = new HashMap<>();

        for(Oomage o: oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if(tmp.containsKey(bucketNum)) {
                tmp.put(bucketNum, tmp.get(bucketNum)+1);
            }
            else {
                tmp.put(bucketNum, 1);
            }
        }

        Set<Integer> keys = tmp.keySet();

        for(int key: keys) {
            if(tmp.get(key) > max || tmp.get(key) < min) {
                return false;
            }
        }

        return true;
    }
}
