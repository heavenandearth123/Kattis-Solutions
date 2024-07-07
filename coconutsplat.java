import java.util.*;
import java.io.*;

public class coconutsplat {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sAndN [] = br.readLine().split(" ");
        int noOfSyllabus = Integer.parseInt(sAndN[0]);
        int noOfPlayers = Integer.parseInt(sAndN[1]);

        LinkedList <Hand> hands = new LinkedList<>();
        for (int i = 0; i<noOfPlayers; i++){
            int player = i+1;
            String handType = "folded";
            hands.add(new Hand (player, handType));
        }

        int noOfHands = noOfPlayers*2;

        while (noOfHands>1) {

            for (int i = 0; i<noOfSyllabus-1; i++){
                Hand frontOfList = hands.removeFirst();
                hands.addLast(frontOfList);
            }
    
            Hand firstHand = hands.getFirst();
            int firstHandPlayer = firstHand.getPlayer();
            String firstHandType = firstHand.getHandType();
            if (firstHandType == "folded") {
                hands.removeFirst();
                String handtype = "fist";
                hands.addFirst(new Hand(firstHandPlayer, handtype));
                hands.addFirst(new Hand(firstHandPlayer, handtype));
            } else if (firstHandType == "fist") {
                hands.removeFirst();
                String handtype = "palmdown";
                hands.add(new Hand(firstHandPlayer, handtype));
            } else if (firstHandType == "palmdown") {
                hands.removeFirst();
                noOfHands--;
            } else {
                break;
            }
        }
        System.out.println(hands.getFirst().getPlayer());
    }
}


class Hand {
    private int player; 
    private String handType; 

    public Hand(int p, String ht){
        player = p;
        handType = ht;
    }

    public int getPlayer() {
        return player;
    }

    public String getHandType() {
        return handType;
    }
}