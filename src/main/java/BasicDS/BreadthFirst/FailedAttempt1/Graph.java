package BasicDS.BreadthFirst.FailedAttempt1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph {

    private List<Node> nodeList = new ArrayList<>();

    public List<Node> getNodes() {
        return nodeList;
    }

    public void addEdge(Node a, Node b){

        // node a
        if (nodeList.indexOf(a) < 0) {
            nodeList.add(a);
        }
        nodeList.get(nodeList.indexOf(a)).connect(b);

        // node b
        if (nodeList.indexOf(b) < 0) {
            nodeList.add(b);
        }
        nodeList.get(nodeList.indexOf(b)).connect(a);

    }

    public void printGraph() {
        for(Node a: nodeList) {
            String a_neighbors = a.neighbors.stream().map(n -> String.valueOf(n.id)).collect(Collectors.joining(","));
            String result = String.format("Node: %d >> %s",a.id, a_neighbors);
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
//        try {
//
//            Graph g = new Graph();
//
//            BasicDS.BreadthFirst.Files.lines(Paths.get("C:\\Users\\somjit.nag\\Desktop\\compProg\\src\\BasicDS.BreadthFirst.Files\\paths.txt"))
//                    .map(line -> line.split(" ")) // Steam<String[]>
//                    .peek(arr -> System.out.println(Arrays.toString(arr)))
//                    .map(entry -> {
//                        Node a = new Node(entry[0]);
//                        Node b = new Node(entry[1]);
//                        g.addEdge(a,b);
//                        return entry;
//                    }).count();
//
//            g.printGraph();
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
