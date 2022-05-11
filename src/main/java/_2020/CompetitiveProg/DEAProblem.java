package dont.checkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

// courtesy Boosan
// HackerRank @ Masai
public class DEAProblem {
    
    //    public static void main(String args[]) {
    //        int graph[][] = new int[][]{
    //                {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
    //                {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
    //                {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1},
    //                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1},
    //                {1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1}};
    //        getFarmsSorted(graph.length, graph[0].length, graph);
    //    }
    
    static class IntWrapper {
        int c = 1;
    }
    
    private static void getFarmsSorted(int r, int c, int[][] graph) {
        List<Integer> farmSizes = new ArrayList<>();
        boolean visited[][] = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    IntWrapper count = new IntWrapper();
                    IntWrapper res = DFS(i, j, graph, visited, count);
                    farmSizes.add(res.c);
                }
            }
        }
        farmSizes.sort((a, b) -> a - b);
        System.out.println(farmSizes.size());
        Map<Integer, Integer> freqMap = new TreeMap<>();
        for (int f : farmSizes) {
            freqMap.put(f, freqMap.getOrDefault(f, 0) + 1);
        }
        for (Entry<Integer, Integer> e : freqMap.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
    
    private static IntWrapper DFS(int row, int column, int graph[][], boolean[][] visited, IntWrapper count) {
        int rowBoundry[] = new int[]{-1, 1, 0, 0};
        int columnBoundry[] = new int[]{0, 0, 1, -1};
        
        visited[row][column] = true;
        for (int i = 0; i < 4; i++) {
            if (isSafe(row + rowBoundry[i], column + columnBoundry[i], graph, visited)) {
                count.c++;
                DFS(row + rowBoundry[i], column + columnBoundry[i], graph, visited, count);
            }
        }
        return count;
    }
    
    private static boolean isSafe(int row, int column, int graph[][], boolean[][] visited) {
        return ((row < graph.length && row >= 0) && (column < graph[0].length && column >= 0) && !visited[row][column]
                && graph[row][column] == 1);
    }
    
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String init = r.readLine().trim();
            do {
                String[] rc = init.split(" +");
                int row = Integer.parseInt(rc[0]), col = Integer.parseInt(rc[1]);
                
                int graph[][] = new int[row][col];
                
                for (int i = 0; i < row; i++) {
                    String[] arr = r.readLine().trim().split(" +");
                    assert arr.length == col : "Too many columns to add";
                    for (int j = 0; j < col; j++) {
                        graph[i][j] = Integer.parseInt(arr[j]);
                    }
                }
                
                getFarmsSorted(graph.length, graph[0].length, graph);
            } while (!(init = r.readLine().trim()).equals("0 0"));
            
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}