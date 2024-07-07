import java.util.*;
import java.io.*;

public class islands {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nextLine;
        while((nextLine = br.readLine()) != null) {
            String rc [] = nextLine.split(" ");
            int r = Integer.parseInt(rc[0]);
            int c = Integer.parseInt(rc[1]);

            String [][] grid = new String [r][c];

            for (int i = 0; i < r; i++) {
                String [] row = br.readLine().split("");

                for (int j = 0; j < c; j++) {
                    grid[i][j] = row[j];
                }
            }

            boolean [][] visited = new boolean[r][c];

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                   visited[i][j] = false; 
                }
            }

            int count = 0;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                   if (grid[i][j].equals("L") && visited[i][j] == false) {
                    count += 1;
                    DFS(i , j, grid, visited);
                   }
                }
            }

            System.out.println(count);
        }
    }

    public static void DFS(int cellR, int cellC, String[][] grid, boolean[][] visited) {
        visited[cellR][cellC] = true;

        int [] adjacent_row = {1, -1, 0, 0};
        int [] adjacent_column = {0, 0, 1, -1};
        
        for (int i = 0; i < 4; i++) {
            int next_row = cellR + adjacent_row[i];
            int next_column = cellC + adjacent_column[i];
            
            if (isNotOutOfBounds(next_row, next_column, grid.length, grid[0].length)) {
                String next_cell = grid[next_row][next_column];
                
                if (next_cell.equals("C") || next_cell.equals("L")) {

                    if (!visited[next_row][next_column]) {
                        DFS(next_row, next_column, grid, visited);
                    }
                }
            }
        }
    }

    public static boolean isNotOutOfBounds(int r, int c, int totalR, int totalC) {
        return r >= 0 && r < totalR && c >= 0 && c < totalC;
    }
}