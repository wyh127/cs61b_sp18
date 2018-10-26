package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // create a grid, 0 for block, 1 for open
    private int[][] grid;
    // record grid size
    private int size;
    // virtual head and tail
    private int head;
    private int tail;
    // record the # of open sites
    private int openSites;
    // create a WeightedQuickUnionUF class
    private WeightedQuickUnionUF weighted_QU;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if(N <= 0) {
            throw new java.lang.IllegalArgumentException("input should be a positive integer.");
        }
        else {
            grid = new int[N][N];
            size = N;
            openSites = 0;
            weighted_QU = new WeightedQuickUnionUF(N*N + 2);
            head = N*N;
            tail = N*N + 1;
        }
    }

    // validate the range of row and col input
    private void validate(int p) {
        if(p < 0 || p >= size) {
            throw new java.lang.IllegalArgumentException("index out of range");
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row);
        validate(col);

        if(!isOpen(row, col)) {
            grid[row][col] = 1;
            openSites += 1;
            connectNeighbours(row, col);
        }
    }

    // helper function, connect with neighbour open sites
    private void connectNeighbours(int row, int col) {
        int curr = row * size + col;

        if(row == 0) {
            weighted_QU.union(head, curr);
            if (isOpen(row + 1, col)) weighted_QU.union((row+1) * size + col, curr);
        }

        else if(row == size - 1) {
            weighted_QU.union(tail, curr);
            if (isOpen(row - 1, col)) weighted_QU.union((row-1) * size + col, curr);
        }

        else {
            if (isOpen(row - 1, col)) weighted_QU.union((row-1) * size + col, curr);
            if (isOpen(row + 1, col)) weighted_QU.union((row+1) * size + col, curr);
        }

        if (col > 0 && isOpen(row, col - 1)) weighted_QU.union(row * size + col - 1, curr);
        if (col < size-1 && isOpen(row, col + 1)) weighted_QU.union(row * size + col + 1, curr);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);

        return grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);

        return weighted_QU.connected(head, row*size+col);

    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return weighted_QU.connected(head, tail);

    }

    // use for unit testing (not required)

    /*
    public static void main(String[] args) {
        Percolation test = new Percolation(3);
        test.open(0, 2);
        test.open(1, 2);
        test.open(2, 2);
        test.open(2, 0);
        System.out.println(test.percolates());
        System.out.println(test.isFull(2, 0));
    }
     */

}
