import java.util.*;
import java.io.*;

public class weakvertices {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nextLine;

        while((nextLine = Integer.parseInt(br.readLine())) != -1) {
            int n = nextLine;

            int [][] adjMatrix = new int [n][n];

            for (int i = 0; i < n; i++) {
                String [] row = br.readLine().split(" ");

                for (int j = 0; j < n; j++) {
                    adjMatrix[i][j] = Integer.parseInt(row[j]);
                }
            }

            Boolean [] taken = new Boolean [m][n];

            for (int i = 0; i < n; i++) {
                weakvertices[i] = false;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (i != j && j != k && i != k) {
                            if (adjMatrix[i][j] == 1 && adjMatrix[j][k] == 1 && adjMatrix[i][k] == 1) {
                                weakvertices[i] = true;
                                weakvertices[j] = true;
                                weakvertices[k] = true;
                            }
                        }
                    }
                }
            }
            String output = "";
            for (int i = 0; i < n; i++) {
                if (!weakvertices[i]) { 
                    output += i + " ";
                }
            }

            System.out.println(output);
        }
    }
}