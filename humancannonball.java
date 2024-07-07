import java.util.*;
import java.io.*;

public class humancannonball {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start_coord [] = br.readLine().split(" ");
        double start_x = Double.parseDouble(start_coord[0]);
        double start_y = Double.parseDouble(start_coord[1]);

        String end_coord [] = br.readLine().split(" ");
        double end_x = Double.parseDouble(end_coord[0]);
        double end_y = Double.parseDouble(end_coord[1]);

        int noOfCannons = Integer.parseInt(br.readLine());

        cannon cannons_coord [] = new cannon[noOfCannons];

        for(int i = 0; i < noOfCannons; i++) {
            String cannon [] = br.readLine().split(" ");
            double cannon_x = Double.parseDouble(cannon[0]);
            double cannon_y = Double.parseDouble(cannon[1]);

            cannons_coord[i] = new cannon(cannon_x, cannon_y);
        }

        double adjMatrix [][] = new double[noOfCannons+2][noOfCannons+2];

        double infinity = 1000000000;

        for (int i = 0; i < noOfCannons + 2; i++) {
            Arrays.fill(adjMatrix[i], 0);
        }

        double dist = Math.sqrt(Math.pow(start_x - end_x, 2) + Math.pow(start_y - end_y, 2));
        double time = dist/5;
        adjMatrix[0][1] = time;
        adjMatrix[1][0] = adjMatrix[0][1];

        for (int i = 0; i < noOfCannons; i++) {
            dist = Math.sqrt(Math.pow(start_x - cannons_coord[i].getX(), 2) + Math.pow(start_y - cannons_coord[i].getY(), 2));
            double time1 = dist / 5;
            double time2 = Math.abs((dist - 50) / 5) + 2;
            adjMatrix[0][i + 2] = time1;
            adjMatrix[i + 2][0] = Math.min(time1, time2);
        }

        for (int i = 0; i < noOfCannons; i++) {
            dist = Math.sqrt(Math.pow(end_x - cannons_coord[i].getX(), 2) + Math.pow(end_y - cannons_coord[i].getY(), 2));
            double time1 = dist / 5;
            double time2 = Math.abs((dist - 50) / 5) + 2;
            adjMatrix[1][i + 2] = time1;
            adjMatrix[i + 2][1] = Math.min(time1, time2);
        }

        for (int i = 0; i < noOfCannons; i++) {
            for (int j = 0; j < noOfCannons; j++) {
                if (i != j) {
                    dist = Math.sqrt(Math.pow(cannons_coord[i].getX() - cannons_coord[j].getX(), 2) + Math.pow(cannons_coord[i].getY() - cannons_coord[j].getY(), 2));
                    double time1 = dist / 5;
                    double time2 = Math.abs((dist - 50) / 5) + 2;
                    adjMatrix[i+2][j+2] = Math.min(time1, time2);
                }
            }
        }

        double[] totalTime = new double[noOfCannons + 2];
        Arrays.fill(totalTime, infinity);
        totalTime[0] = 0;

        for (int i = 0; i < noOfCannons + 2; i++) {
            for (int j = 0; j < noOfCannons + 2; j++) {
                for (int k = 0; k < noOfCannons + 2; k++) {
                    if (totalTime[j] + adjMatrix[j][k] < totalTime[k]) {
                        totalTime[k] = totalTime[j] + adjMatrix[j][k];
                    }
                }
            }
        }

        System.out.println(totalTime[1]);

    }
}

class cannon { 
    public double x; 
    public double y; 

    public cannon(double x_coord, double y_coord) {
        x = x_coord;
        y = y_coord;
    }

    public double getX() { 
        return x;
    }

    public double getY() {
        return y;
    }
}