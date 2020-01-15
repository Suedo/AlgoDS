package BasicDS.BreadthFirst.FailedAttempt1;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a failed implementation.
 * Once a node, say X, gets added to the neighbors list of another node, say Y, it does not update itself
 * The same node X, as part of the primary node list of the graph, updates its neighbors,
 * but that update is not reflected to X in the neighbors list of Y.
 * Thus, after a number of edge additions, X may have 4-5 neighbors in it's own list,
 * but the X in neighbors list of Y does not see that update.
 */
public class Node {
    Integer id;
    List<Node> neighbors;

    public void connect(Node b){
        if(this.neighbors.indexOf(b) < 0) this.neighbors.add(b);
        else System.err.println("Node is already present:" + b);
    }

    Node(String id){
        this.id = Integer.parseInt(id);
        neighbors = new ArrayList<>();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Node)) return false;
        return this.id == ((Node) other).id;
    }

    @Override
    public String toString() {
        return "Node:" + id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}