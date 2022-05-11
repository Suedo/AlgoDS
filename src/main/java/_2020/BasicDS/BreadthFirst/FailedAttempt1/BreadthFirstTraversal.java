package _2020.BasicDS.BreadthFirst.FailedAttempt1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class BreadthFirstTraversal {

    Graph g;
    HashMap<Node, Boolean> done;
    List<Node> vertices;

    BreadthFirstTraversal(Graph g) {
        this.g = g;
        done = new HashMap<>();
        vertices = g.getNodes();

        for (Node a : vertices) {
            done.putIfAbsent(a, false);
        }
    }

    private void process(Node root) {

        // process each neighbor, and then mark current root as processed
        for (Node neighbor : root.neighbors) {
            System.out.println(String.format("Processing: root: %s, neighbor: %s", root, neighbor));

            // System.out.println("done.get(b): " + done.get(neighbor));
            // already processed?
            if (done.get(neighbor)) {
                System.out.println(neighbor + " already processed, continue");
                continue;
            }

            // if this neighbor is ONLY reachable from current root, then there's nothing left to process
            if (neighbor.neighbors.size() <= 1) {
                System.out.println("Marking leaf node as done: " + neighbor);
                done.replace(neighbor, true);
            }
        }

        // All processing done for current root
        done.replace(root, true);
    }

    public void traverse() {

        vertices.stream()
                .peek(a -> System.out.println("Start processing: " + a))
                .map(node -> {
                    process(node);
                    return node;
                })
                .peek(a -> System.out.println("All done for Node :" + a + "\n"))
                .count();

        System.out.println("\n\n");
        System.out.println(done);
    }

    public static void main(String[] args) {

        try {

            Graph g = new Graph();

            Files.lines(Paths.get("src\\BasicDS\\BreadthFirst\\Files\\paths.txt"))
                    .map(line -> line.split(" ")) // Steam<String[]>
                    .peek(arr -> System.out.println(Arrays.toString(arr)))
                    .map(entry -> {
                        Node a = new Node(entry[0]);
                        Node b = new Node(entry[1]);
                        g.addEdge(a, b);
                        return entry;
                    }).count();

            g.printGraph();

            BreadthFirstTraversal breadthFirstTraversal = new BreadthFirstTraversal(g);
            breadthFirstTraversal.traverse();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
