import java.util.*;
import java.io.*;

public class kattissquest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long noOfCommands = Long.parseLong(br.readLine());

        TreeMap <Long, PriorityQueue<Long>> map = new TreeMap<>();
        
        for (int i = 0; i < noOfCommands; i++) {
            String CEG[] = br.readLine().split(" ");
            String command = CEG[0];

            if (command.equals("add")) {
                long E = Long.parseLong(CEG[1]);
                long G = Long.parseLong(CEG[2]);
                if (!map.containsKey(E)) {
                    map.put(E, new PriorityQueue<>(Collections.reverseOrder()));
                }
                map.get(E).offer(G);
            } else if (command.equals("query")) {
                long gold = 0;
                long X = Long.parseLong(CEG[1]);

                while (!map.isEmpty()) {
                    Long biggestE = map.floorKey(X);
                    if (biggestE == null) {
                        break;
                    }
                    gold += map.get(biggestE).peek();
                    map.get(biggestE).poll();

                    if (map.get(biggestE).isEmpty()) {
                        map.remove(biggestE);
                    }

                    X -= biggestE;
                } 
                System.out.println(gold);
            }
        }
    }
}