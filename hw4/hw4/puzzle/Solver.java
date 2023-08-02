package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private static class Node implements Comparable<Node> {
        private final WorldState state;
        private final Node pre;
        private final int dis;

        public Node(WorldState state, Node pre, int dis) {
            this.state = state;
            this.pre = pre;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return (state.estimatedDistanceToGoal() + dis) - (o.state.estimatedDistanceToGoal() + o.dis);
        }
    }

    private MinPQ<Node> pq;
    private List<WorldState> set;
    private int count;

    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        set = new LinkedList<>();
        count = 0;

        pq.insert(new Node(initial, null, 0));

        Node node = AStar();

        while (node != null) {
            ++count;
            set.add(node.state);
            node = node.pre;
        }
        Collections.reverse(set);
    }

    private Node AStar() {
        Node node = null;
        Set<String> strings = new HashSet<>();
        
        while (!pq.isEmpty()) {
            node = pq.delMin();
            strings.add(node.state.toString());
            if (node.state.estimatedDistanceToGoal() == 0) {
                break;
            }
            for (WorldState state : node.state.neighbors()) {
                if (!strings.contains(state.toString())) {
                    strings.add(state.toString());
                    pq.insert(new Node(state, node, 1 + node.dis));
                }
            }
        }

        return node;
    }

    public int moves() {
        return count;
    }

    public Iterable<WorldState> solution() {
        return set;
    }
}
