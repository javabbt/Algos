import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int T;
    private final double[] results;

    /**
     * Performs T independent computational experiments on an N-by-N grid
     *
     * @param N the dimension of the grid
     * @param T the number of experiments to be performed
     *          <dt><b>Precondition:</b>
     *          <dd>N,T are positive
     */
    public PercolationStats(final int N, final int T) {
        this.T = T;
        int i;
        int j;
        int count;
        int tests = 0;
        Percolation percolation;
        results = new double[T];

        while (tests < T) {
            count = 0;
            percolation = new Percolation(N);
            while (!percolation.percolates()) {
                i = 1 + StdRandom.uniform(N);
                j = 1 + StdRandom.uniform(N);

                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    count++;
                }

            }
            results[tests] = (double) count / (N * N);
            tests++;
        }

    }

    /**
     * Returns the sample mean of percolation threshold
     *
     * @return the sample mean of percolation threshold
     */
    public double mean() {

        return StdStats.mean(results);
    }

    /**
     * Returns sample standard deviation of percolation threshold
     *
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(results);
    }

    /**
     * Returns half the range of the 95% confidence interval
     *
     * @return half the range of the 95% confidence interval
     */
    private double halfInterval() {
        return 1.96 * stddev() / Math.sqrt(T);
    }

    /**
     * Returns the lower bound of the 95% confidence interval
     *
     * @return the lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - halfInterval();
    }

    /**
     * Returns the upper bound of the 95% confidence interval
     *
     * @return the upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + halfInterval();
    }

    /**
     * Test client.
     *
     * @param args unused
     */
    public static void main(String[] args) {

        PercolationStats percolationStats;
        percolationStats = new PercolationStats(30, 1);

        StdOut.print(percolationStats.mean());
        StdOut.print("\n");
        StdOut.print(percolationStats.stddev());
        StdOut.print("\n");
        StdOut.print(percolationStats.confidenceLo());
        StdOut.print("\n");
        StdOut.print(percolationStats.confidenceHi());

    }

}
