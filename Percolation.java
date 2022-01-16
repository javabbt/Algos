import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    /**
     * NxN array representing the open and closed sites.
     */
    private final boolean[][] open;
    private int numberOfOpenSites;

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    private final int N;
    private final WeightedQuickUnionUF pathComponents;
    /**
     * Boolean array indexed by the path components as represented by the values
     * in the pathComponents union find object.
     *
     * As path components merge, some index components become redundant.
     * However, given i currently represents a path component, then
     * baseAttached[i] is true if the path includes an element in the bottom
     * row, and false otherwise.
     */
    private final boolean[] baseAttached;

    /**
     * Initialises the percolation structure of size parmN.
     *
     * @param parmN the dimension of the grid
     *              <dt><b>Precondition:</b>
     *              <dd>parmN is positive
     */
    public Percolation(final int parmN) {

        N = parmN;
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        int j;
        open = new boolean[N + 1][N + 1];
        baseAttached = new boolean[N * N + 1];

        for (int i = N * (N - 1) + 1; i <= N * N; i++) {
            baseAttached[i] = true;
        }

        pathComponents = new WeightedQuickUnionUF(N * N + 1);

        for (j = 1; j <= N; j++) {

            makeUnion(0, project(1, j));
        }
    }

    /**
     * .
     * Converts row and column into single integer representation
     *
     * @param i the row number of the site
     * @param j the column number of the site
     * @return integer representation of the site
     */
    private int project(final int i, final int j) {
        return j + (i - 1) * N;

    }

    /**
     * .
     * Determines whether row and column numbers represent a valid site.
     *
     * @param i the row number
     * @param j the column number
     * @return true if row and column represent a valid site, false otherwise
     */
    private boolean inRange(final int i, final int j) {

        return i > 0 && j > 0 && i <= N && j <= N;
    }

    /**
     * .
     * Calls the union method of UnionFind and updates the baseAttachedArray
     *
     * @param i the row number
     * @param j the column number
     */
    private void makeUnion(final int i, final int j) {
        if (baseAttached[pathComponents.find(i)]) {
            baseAttached[pathComponents.find(j)] = true;
        } else if (baseAttached[pathComponents.find(j)]) {
            baseAttached[pathComponents.find(i)] = true;
        }
        pathComponents.union(i, j);
    }

    /**
     * .
     * Set the site to be open, update union find object
     *
     * @param i the row number of the site
     * @param j the column number of the site
     * @throws IndexOutOfBoundsException if parameters do not represent a valid
     *                                   site.
     */
    public void open(int i, int j) {

        if (!inRange(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(i, j)) {
            return;
        }

        open[i][j] = true;

        if (inRange(i - 1, j) && isOpen(i - 1, j)) {
            makeUnion(project(i, j), project(i - 1, j));
        }

        // UFTopOnly.union(project(i, j), project(i - 1, j));
        if (inRange(i + 1, j) && isOpen(i + 1, j)) {
            makeUnion(project(i, j), project(i + 1, j));
            // UFTopOnly.union(project(i, j), project(i + 1, j));
        }

        if (inRange(i, j - 1) && isOpen(i, j - 1)) {
            makeUnion(project(i, j), project(i, j - 1));
            // UFTopOnly.union(project(i, j), project(i, j - 1));
        }

        if (inRange(i, j + 1) && isOpen(i, j + 1)) {
            makeUnion(project(i, j), project(i, j + 1));
            // UFTopOnly.union(project(i, j), project(i, j + 1));
        }
        numberOfOpenSites++;

    }

    /**
     * .
     * Determines if a site is open
     *
     * @param i the row number of the site
     * @param j the column number of the site
     * @return true if site is open, false otherwise
     * @throws IndexOutOfBoundsException i,j do not represent a valid site
     */
    public boolean isOpen(final int i, final int j) {

        if (inRange(i, j)) {
            return open[i][j];
        } else {
            throw new java.lang.IndexOutOfBoundsException();
        }

    }

    /**
     * .
     * Determines if a site is full
     *
     * @param i the row number of the site
     * @param j the column number of the site
     * @return true if site is open, false otherwise
     * @exception IndexOutOfBoundsException i,j do not represent a valid site
     */
    public boolean isFull(final int i, final int j) {
        if (inRange(i, j)) {
            if (isOpen(i, j)) {
                return pathComponents.find(0) == pathComponents.find(project(i, j));
            } else {
                return false;
            }
        } else {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    /**
     * .
     * Determines if the system percolates
     *
     * @return true if system percolates, false otherwise
     *
     */
    public boolean percolates() {

        if (N > 1) {

            return baseAttached[pathComponents.find(0)];

        }

        return isOpen(1, 1);

    }

    public static void main(String[] args) {
        int N = 1;
        int i, j, count = 0;
        Percolation p = new Percolation(N);
        while (!p.percolates()) {
            i = 1 + StdRandom.uniform(N);
            j = 1 + StdRandom.uniform(N);

            if (!p.isOpen(i, j)) {
                p.open(i, j);
                count++;
            }
        }
        StdOut.print(count);
    }
}
