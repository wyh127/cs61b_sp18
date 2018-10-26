package hw2;

import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {

    // record the percolating ratio
    private double[] stats;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("input error");
        }

        stats = new double[T];

        for(int i = 0; i < T; i++) {
            // create a percolation class
            Percolation perc = pf.make(N);
            // simulation using discrete uniform distribution
            while(!perc.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                perc.open(row, col);
            }
            // the percolating ratio
            stats[i] = perc.numberOfOpenSites() / (N*N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);

    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(stats.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(stats.length);
    }
}
