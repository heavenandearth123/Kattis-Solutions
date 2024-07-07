import java.util.*;
import java.io.*;

public class almostunionfind1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nextLine;

        while((nextLine = br.readLine()) != null) {
            String NM [] = nextLine.split(" ");
            int n = Integer.parseInt(NM[0]);
            int m = Integer.parseInt(NM[1]);

            UnionFind disjointSet = new UnionFind(n);

            for (int i = 0; i < m; i++) {
                String operation [] = br.readLine().split(" ");
                int command = Integer.parseInt(operation[0]);

                if (command == 1) {
                    int p = Integer.parseInt(operation[1]);
                    int q = Integer.parseInt(operation[2]);

                    disjointSet.unionSet(p, q);
                
                }

                if (command == 2) {
                    int p = Integer.parseInt(operation[1]);
                    int q = Integer.parseInt(operation[2]);
                
                    disjointSet.moveSet(p, q);
                }

                if (command == 3) {
                    int p = Integer.parseInt(operation[1]);
                    int x = disjointSet.findSet(p);

                    String output = disjointSet.child[x].size() + " " + disjointSet.sum[x];

                    System.out.println(output);
                }
            }
        }
    }
}

class UnionFind {                                              
    public long[] sum;
    public HashSet<Integer>[] child;
    public int[] parent;
  
    public UnionFind(int N) {
        child = new HashSet[N+1];
        sum = new long[N+1];
        parent = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            child[i] = new HashSet<>();
            child[i].add(i);
            sum[i] = i;
            parent[i] = i;
        }
    }
  
    public int findSet(int i) { 
        return parent[i];
    }
  
    public Boolean isSameSet(int i, int j) { 
        return findSet(i) == findSet(j);
    }
  
    public void unionSet(int i, int j) { 
        if (!isSameSet(i, j)) { 
            int x = findSet(i);
            int y = findSet(j);
            
            if (child[x].size() < child[y].size()) {
                int temp = x;
                x = y;
                y = temp;
            }
            
            for (int k: child[y]) {
                parent[k] = x;
                child[x].add(k);
            }

            child[y].clear();
            sum[x] += sum[y];
            sum[y] = 0;
            
        }
    }

    public void moveSet(int i, int j) { 
        if (!isSameSet(i, j)) { 
            int x = findSet(i);
            int y = findSet(j);

            parent[i] = y;
            child[y].add(i);   
            child[x].remove(i);
            sum[y] += i;
            sum[x] -= i;
            
        } 
    } 
}

  
