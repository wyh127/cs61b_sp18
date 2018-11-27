package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{

    private int N;
    private int BLANK = 0;
    private int[][] board;

    public Board(int[][] tiles) {
        N = tiles.length;
        board = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if(i >= N || i < 0 || j >= N || j < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        else {
            return board[i][j];
        }
    }

    public int size() {
        return N;
    }


    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }

        int[][] ili1li1 = new int[hug][hug];

        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }

        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }

        return neighbors;
    }


    public int hamming() {
        int hamming = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] != i*N+j+1 && board[i][j] != BLANK) {
                    hamming++;
                }
            }
        }

        return hamming;
    }


    public int manhattan() {
        int manhattan = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int dx = Math.abs(i - (board[i][j]-1)/N);
                int dy = Math.abs(j - Math.floorMod(board[i][j]-1, N));
                manhattan = manhattan + dx + dy;
            }
        }

        return manhattan;
    }


    public int estimatedDistanceToGoal() {
        return manhattan();
    }


    public boolean equals(Object y) {
        if(y == null || y.getClass() != this.getClass()) return false;

        if(y == this) return true;

        Board bd = (Board) y;

        if(this.size() != bd.size()) return false;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(this.tileAt(i, j) != bd.tileAt(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
