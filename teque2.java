import java.util.*;
import java.io.*;

public class teque2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int noOfOperations = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> front = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> back = new HashMap<Integer, Integer>();
        int frontFilled = 0;
        int backFilled = 0;
        int frontHead = -1;
        int frontTail = 0;
        int backHead = -1;
        int backTail = 0;

        for (int n = 0; n<noOfOperations; n++){
            String OpAndi [] = br.readLine().split(" ");
            String Op = OpAndi[0];
            int i = Integer.parseInt(OpAndi[1]);

            if (Op.equals("push_front")) {
                front.put(frontHead, i);
                frontHead -= 1; 
                frontFilled += 1;

                if (frontFilled > backFilled +1) {
                    int lastOfFront = front.get(frontTail-1);
                    back.put(backHead, lastOfFront);
                    frontTail -=1; 
                    backHead -= 1;
                    frontFilled -= 1;
                    backFilled += 1;
                }    
            }

            if (Op.equals("push_back")) {
                back.put(backTail, i);
                backTail += 1;
                backFilled += 1;

                if (backFilled > frontFilled) {
                    int firstOfBack = back.get(backHead+1);
                    front.put(frontTail, firstOfBack);
                    frontTail += 1;
                    
                    backHead += 1;
                    backFilled -= 1;
                    frontFilled += 1;
                }
            }

            if (Op.equals("push_middle")) {
                front.put(frontTail, i);
                frontTail += 1; 
                frontFilled += 1;

                if (frontFilled > backFilled +1) {
                    int lastOfFront = front.get(frontTail-1);
                    back.put(backHead, lastOfFront);
                    frontTail -=1; 
                    backHead -= 1;
                    frontFilled -= 1;
                    backFilled += 1;
                }    
            }

            if (Op.equals("get")) {
                if (i < frontFilled) {
                    writer.println(front.get(i+frontHead+1));
                } else {
                    writer.println(back.get(i-frontFilled+backHead+1));
                }
            }
        }

        writer.flush();
    }
}

