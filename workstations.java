import java.util.*;
import java.io.*;

public class workstations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nAndM [] = br.readLine().split(" ");
        int n = Integer.parseInt(nAndM[0]);
        int m = Integer.parseInt(nAndM[1]);

        ArrayList<researcher> rTimings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String rTiming [] = br.readLine().split(" ");
            int arr = Integer.parseInt(rTiming[0]);
            int stay = Integer.parseInt(rTiming[1]);
            int leave = arr+stay;
            
            rTimings.add(new researcher(arr, stay, leave));
        }

        Collections.sort(rTimings, Comparator.comparingInt(a -> a.getArr()));

        PriorityQueue<Integer> using = new PriorityQueue<>();

        int unlockSaved = 0;

        for (int i = 0; i < n; i++) {
            int arr = rTimings.get(i).getArr();
            int leave = rTimings.get(i).getLeave();


            if (using.isEmpty()) {
                using.add(leave);
            }

            else {
                while (!using.isEmpty() && using.peek()+m < arr) {
                    using.poll();
                }

                if (!using.isEmpty() && using.peek() <= arr && using.peek()+m >= arr) {
                    using.poll();

                    unlockSaved += 1;
                }

                using.add(leave);
            }
        }

        System.out.println(unlockSaved);
    }
}


class researcher {
    private int arr;
    private int stay;
    private int leave;

    public researcher(int a, int s, int l) {
        arr = a;
        stay = s;
        leave = l;
    }

    public int getArr() {
        return arr;
    }

    public int getStay() {
        return stay;
    }

    public int getLeave() {
        return leave;
    }
}





