package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;

public class Solver {

    private ArrayList<WorldState> solution;

    public Solver(WorldState initial) {

        solution = new ArrayList<>();
        MinPQ<SearchNode> pq = new MinPQ<>();

        pq.insert(new SearchNode(initial, 0, null));

        while(!pq.min().state.isGoal()) {
            SearchNode tmp = pq.delMin();

            for(WorldState s: tmp.getState().neighbors()) {
                // corner case: when prev is null
                if(tmp.getPrev() == null || !s.equals(tmp.getPrev().getState())) {
                    pq.insert(new SearchNode(s, tmp.moves+1, tmp));
                }
            }
        }

        SearchNode tmp = pq.min();
        while(tmp != null) {
            solution.add(0, tmp.getState());
            tmp = tmp.getPrev();
        }
    }

    public int moves() {
        return solution.size()-1;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }


    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode prev;

        private SearchNode(WorldState w, int m, SearchNode s) {
            state = w;
            moves = m;
            prev = s;
        }

        public WorldState getState() {
            return state;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrev() {
            return prev;
        }

        @Override
        public int compareTo(SearchNode o) {
            return (moves + state.estimatedDistanceToGoal()) - (o.moves + o.state.estimatedDistanceToGoal());
        }
    }
}