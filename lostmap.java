import java.util.*;
import java.io.*;

public class lostmap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noOfVillages = Integer.parseInt(br.readLine());

        ArrayList < IntegerTriple > edgeList = new ArrayList < IntegerTriple >();

        for (int i = 0; i < noOfVillages; i++) {
            String [] r = br.readLine().split(" ");

            for (int j = 0; j < noOfVillages; j++) {
                int row = i;
                int column = j; 
                int edge = Integer.parseInt(r[j]);

                if (column > row) { // Consider only upper triangular matrix to avoid duplicates
                    edgeList.add(new IntegerTriple(edge, row, column));
                }
            }
        }

        Collections.sort(edgeList);

        UnionFind UF = new UnionFind(noOfVillages); // all V are disjoint sets at the beginning

        for (int i = 0; i < edgeList.size(); i++) { // process all edges, O(E)
            IntegerTriple e = edgeList.get(i);
            int u = e.second(), v = e.third(), w = e.first(); // note that we have re-ordered the integer triple
            
            if (!UF.isSameSet(u, v)) { // if no cycle

                System.out.println((u + 1) + " " + (v + 1));
                UF.unionSet(u, v); // link these two vertices
            }
        }
    }
}


class IntegerTriple implements Comparable<IntegerTriple> {
    public Integer _first, _second, _third;
  
    public IntegerTriple(Integer f, Integer s, Integer t) {
        _first = f;
        _second = s;
        _third = t;
    }
  
    public int compareTo(IntegerTriple o) {
        if (!this.first().equals(o.first()))
            return this.first() - o.first();
        else if (!this.second().equals(o.second()))
            return this.second() - o.second();
        else
            return this.third() - o.third();
    }
  
    Integer first() { return _first; }
    Integer second() { return _second; }
    Integer third() { return _third; }
  
    public String toString() { return first() + " " + second() + " " + third(); }
}

class UnionFind {
    public int[] p;
    public int[] rank;
    public int[] setSize;
    public int numSets;
  
    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        setSize = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            setSize[i] = 1;
        }
    }
  
    public int findSet(int i) { 
        if (p[i] == i) return i;
        else {
            p[i] = findSet(p[i]);
            return p[i]; 
        }  
    }
  
    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  
    public void unionSet(int i, int j) { 
        if (!isSameSet(i, j)) { 
            numSets--; 
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) { 
                p[y] = x; 
                setSize[x] = setSize[x] + setSize[y]; 
            }
            else { 
                p[x] = y; 
                setSize[y] = setSize[x] + setSize[y];
                if (rank[x] == rank[y]) 
                    rank[y] = rank[y]+1; 
            } 
        } 
    }
  
    public int numDisjointSets() { return numSets; }
  
    public int sizeOfSet(int i) { return setSize[findSet(i)]; }
}
  