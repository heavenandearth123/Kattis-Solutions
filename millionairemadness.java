import java.util.*;
import java.io.*;

public class millionairemadness {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String mn [] = br.readLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);

        int [][] grid = new int [m][n];

        for (int i = 0; i < m; i++) {
            String [] row = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(row[j]);
            }
        }

        boolean [][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
               visited[i][j] = false; 
            }
        }

        int ladder = 0;

        int [] adjacentRow = {1, -1, 0, 0};
        int [] adjacentColumn = {0, 0, 1, -1};

        PriorityQueue <cell> pq = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.getHeight()));

        pq.add(new cell(0, 0, grid[0][0], 0));

        while (!pq.isEmpty()) {
            cell front = pq.poll();

            int row = front.row;
            int col = front.col;
            int value = front.value;
            int height = front.height;

            if (!visited[m-1][n-1]) {
                visited[row][col] = true;
                ladder = Math.max(ladder, height);

                for (int i = 0; i < 4; i++) {
                    int nextRow = row + adjacentRow[i];
                    int nextColumn = col + adjacentColumn[i];
                    
                    if (isNotOutOfBounds(nextRow, nextColumn, grid.length, grid[0].length) && !visited[nextRow][nextColumn]) {
                        int nextValue = grid[nextRow][nextColumn];
                        int nextHeight = nextValue - value;
                        
                        pq.add(new cell(nextRow, nextColumn, nextValue, nextHeight));
                    }
                }
            } else {
                break;
            }
        }
        System.out.println(ladder);
    }

    public static boolean isNotOutOfBounds(int r, int c, int totalR, int totalC) {
        return r >= 0 && r < totalR && c >= 0 && c < totalC;
    }
}

class cell { 
    public int row; 
    public int col; 
    public int value;
    public int height;

    public cell(int r, int c, int v, int h) {
        row = r;
        col = c;
        value = v;
        height = h;
    }

    public int getRow() { 
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public int getHeight() {
        return height;
    }
}