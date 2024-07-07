import java.util.*;
import java.io.*;

public class dominos {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noOfTestCases = Integer.parseInt(br.readLine());

        for (int i = 0; i < noOfTestCases; i++) {
            String nm [] = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                adjList.add(new ArrayList<>());
            }

            for (int j = 0; j < m; j++) {
                String xy [] = br.readLine().split(" ");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);

                adjList.get(x-1).add(y-1);
            }

            boolean [] visited = new boolean[n];

            for (int j = 0; j < n; j++) {
                visited[j] = false;
            }

            Stack<Integer> toposort = new Stack<>();

            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    DFS_sort(j, visited, toposort, adjList);
                }
            }

            for (int j = 0; j < n; j++) {
                visited[j] = false;
            }

            int count = 0;

            while(!toposort.isEmpty()) {
                int v = toposort.pop();
                if (!visited[v]) {
                    DFS(v, visited, adjList);
                    count += 1;
                }
            } 

            System.out.println(count);
        }
    }

    public static void DFS_sort(int u, boolean [] visited, Stack<Integer> toposort, ArrayList<ArrayList<Integer>> adjList) {
        visited[u] = true;
        int noOfNeighbours = adjList.get(u).size();
        
        for (int i = 0; i < noOfNeighbours; i++) {
            int neighbour = adjList.get(u).get(i);
            if (!visited[neighbour]) {
                DFS_sort(neighbour, visited, toposort, adjList);
            }                   
        }
        toposort.push(u);    
    }

    public static void DFS(int u, boolean [] visited, ArrayList<ArrayList<Integer>> adjList) {
        visited[u] = true;
        int noOfNeighbours = adjList.get(u).size();
        
        for (int i = 0; i < noOfNeighbours; i++) {
            int neighbour = adjList.get(u).get(i);
            if (!visited[neighbour]) {
                DFS(neighbour, visited, adjList);
            }                   
        }  
    }
}
