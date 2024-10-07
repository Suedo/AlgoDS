import org.junit.jupiter.api.Test;

import java.util.*;

public class Stacks {

    /*
    Stack
        ArrayDeque.push
        ArrayDeque.pop
    Queue
        ArrayDeque.offer : add to end
        ArrayDeque.poll : remove from front
     */

    public <T> void dfs(Map<T, List<T>> graph, T start) {
        Set<T> visited = new HashSet<>();
        Deque<T> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            final T node = stack.pop();
            if (visited.contains(node)) continue;
            visited.add(node);

            var neighbors = graph.getOrDefault(node, new ArrayList<>()); // can reverse to mimic recursion/adj list order
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) stack.push(neighbor);
            }
        }
    }

    public <T> void bfs(Map<T, List<T>> graph, T start) {
        Set<T> visited = new HashSet<>();
        Deque<T> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            final T node = queue.poll();
            if (visited.contains(node)) continue;
            visited.add(node);

            final List<T> neighbors = graph.getOrDefault(node, new ArrayList<>());
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) queue.offer(neighbor);
            }
        }
    }

    // recursive dfs
    public boolean dfsHasPath(Map<Integer, List<Integer>> graph, int current, int end, Set<Integer> visited) {
        if (current == end) return true;
        if (!visited.add(current)) return false; // if already visited, dont recurse on it, else mark as visited

        return graph.getOrDefault(current, new ArrayList<>()).stream()
                .anyMatch(neighbor -> dfsHasPath(graph, neighbor, end, visited));
    }


    @Test
    void pathCheck() {
        //Integer[][] edges = {{0, 1}, {1, 2}, {2, 0}}; // 0,2
        Integer[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};

        final Map<Integer, List<Integer>> graph = getAdjList(edges, false);
        var res = dfsHasPath(graph, 0, 5, new HashSet<>());
        System.out.println(res);
    }


    // recursive dfs
    public void getAllPaths(Map<Integer, List<Integer>> graph, int current, int end, List<Integer> path, List<List<Integer>> result) {
        path.add(current);
        if (current == end) {
            result.add(new ArrayList<>(path)); // save a copy of the current path
        } else {
            graph.getOrDefault(current, new ArrayList<>())
                    .forEach(neighbor -> getAllPaths(graph, neighbor, end, path, result));
        }
        path.removeLast(); // backtrack by removing the last added node
    }


    @Test
    void getAllPathsTest() {
        //Integer[][] edges = {{0, 1}, {1, 2}, {2, 0}}; // 0,2
        //Integer[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};

        int[][] adjArr = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        final Map<Integer, List<Integer>> graph = getAdjListMapFromArray(adjArr);
        List<List<Integer>> res = new ArrayList<>();
        getAllPaths(graph, 0, 4, new ArrayList<>(), res);
        res.forEach(System.out::println);
    }

    /*
        Finding connected components of a graph
        --------------------------------------------------
        initialize visited_set as empty
        initialize components as empty list

        for each node in graph:
            if node is not in visited_set:
                component = new []
                perform DFS or BFS starting from node, add all visited nodes to component
                add component to components list

        return components
     */
    public void findAllConnectedComponents(Map<Integer, List<Integer>> graph) {
        List<Integer> visited = new ArrayList<>();
        List<List<Integer>> components = new ArrayList<>();
        //List<Integer> maxComponent = new ArrayList<>();

        for (Integer node : graph.keySet()) {
            if (!visited.contains(node)) {
                var component = new ArrayList<Integer>();
                dfsCC(graph, node, visited, component);
                components.add(component);
                //maxComponent = component.size() > maxComponent.size() ? component : maxComponent;
            }
        }

        System.out.println("Connected Cmponents:");
        components.forEach(System.out::println);

        //System.out.println("Max Component: " + maxComponent);
    }


    public void dfsCC(Map<Integer, List<Integer>> graph, Integer node, List<Integer> visited, List<Integer> component) {
        visited.add(node);
        component.add(node);

        for (Integer neighbor : graph.getOrDefault(node, List.of())) {
            if (!visited.contains(neighbor)) dfsCC(graph, neighbor, visited, component);
        }
    }

    @Test
    void ConnectedComponentsTest() {
        //Integer[][] edges = {{0,1},{0,2},{1,2},{3,4},{3,5}};
        Integer[][] edges = {{1, 4}, {4, 5}, {1, 2}, {1, 3}, {2, 3}, {3, 5}, {3, 6}, {7, 9}, {7, 8}, {10, 11}, {10, 12}};
        final Map<Integer, List<Integer>> graph = getAdjList(edges, true);
        findAllConnectedComponents(graph);
    }


    public static void recursiveDfsPrint(Map<Integer, List<Integer>> graph, Integer current, ArrayDeque<Integer> visited) {
        visited.push(current);
        graph.getOrDefault(current, List.of()).stream()
                .filter(neighbor -> !visited.contains(neighbor))
                .forEach(neighbor -> {
                    System.out.println("in: " + neighbor + ", visited: " + visited);
                    recursiveDfsPrint(graph, neighbor, visited);
                    System.out.println("back: " + neighbor);
                });
    }


    @Test
    void recursiveDFSPrintCheck() {
        Integer[][] edges = {{1, 2}, {2, 4}, {4, 1}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 5}, {3, 6}};
        final Map<Integer, List<Integer>> graph = getAdjList(edges, false);

        // start dfs
        System.out.println("DFS Traversal:");
        ArrayDeque<Integer> visited = new ArrayDeque<>();
        recursiveDfsPrint(graph, 1, visited);
        System.out.println(":: " + visited);

        System.out.println("Stack printed in reverse order (FIFO), via queue:");
        while (!visited.isEmpty()) System.out.println(visited.pollLast());
    }

    @Test
    void findStonglyConnectedComponents() {

        Integer[][] edges = {{1, 2}, {2, 4}, {4, 1}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 5}, {3, 6}};
        final Map<Integer, List<Integer>> graph = getAdjList(edges, false);

        final Integer[][] reversed = Arrays.stream(edges)
                .map(edge -> new Integer[]{edge[1], edge[0]}) // reverse
                .toArray(Integer[][]::new);

        final Map<Integer, List<Integer>> transposedGraph = getAdjList(reversed, false);

        var stack = new ArrayDeque<Integer>();
        var visited = new HashSet<Integer>();
        dfsSCC(graph, 1, visited, stack);

        System.out.println("visit order: " + stack);

        visited.clear();

        while (!stack.isEmpty()) {
            var node = stack.pop();
            if (!visited.contains(node)) {
                var component = new ArrayDeque<Integer>();
                dfsSCC(transposedGraph, node, visited, component);
                System.out.println(String.format("Node: %s, SCC: %s", node, component));
            }
        }
    }

    public static void dfsSCC(Map<Integer, List<Integer>> graph, Integer current, Set<Integer> visited, ArrayDeque<Integer> stack) {
        visited.add(current);
        graph.getOrDefault(current, List.of()).stream()
                .filter(neighbor -> !visited.contains(neighbor))
                .forEach(neighbor -> {
                    dfsSCC(graph, neighbor, visited, stack);
                    // backtrack
                });
        // When a node is fully explored, i.e. no more neighbors to visit, push it onto a stack
        // this ensures reverse finishing time order when popping the stack.
        // the node with which the recursion starts is at the top of the stack, and will be pop()-ed first
        // and the first node to be fully explored, is at the bottom, and will be pop()-ed last
        stack.push(current);
    }


    // utilities
    public Map<Integer, List<Integer>> getAdjListMapFromArray(int[][] adjArr) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < adjArr.length; i++) {
            adjList.put(i, Arrays.stream(adjArr[i]).boxed().toList());
        }
        return adjList;
    }


    public <T> Map<T, List<T>> getAdjList(T[][] edges, boolean undirected) {
        Map<T, List<T>> adjList = new HashMap<>();
        for (T[] edge : edges) {
            var from = edge[0];
            var to = edge[1];
            adjList.computeIfAbsent(from, any -> new ArrayList<>()).add(to);
            if (undirected) adjList.computeIfAbsent(to, any -> new ArrayList<>()).add(from);
        }
        System.out.println("Adjacency List:");
        adjList.forEach((node, neighbors) -> System.out.println(node + " -> " + neighbors));
        return adjList;
    }

}
