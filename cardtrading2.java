import java.util.*;
import java.io.*;

public class cardtrading2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String NTK[] = br.readLine().split(" ");
        int N = Integer.parseInt(NTK[0]);
        int T = Integer.parseInt(NTK[1]);
        int K = Integer.parseInt(NTK[2]);
        // read NTK 

        int count [] = new int [T];
        String cards[] = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int cardNumber = Integer.parseInt(cards[i]);
            count[cardNumber-1]++; // index of the cardNumber is alr the cardNumber-1
        }

        ArrayList<Prices> prices = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            String[] buyingNSelling = br.readLine().split(" ");
            int buyingPrice = Integer.parseInt(buyingNSelling[0]);
            int sellingPrice = Integer.parseInt(buyingNSelling[1]);
            int cardNumber = i+1;

            prices.add(new Prices(cardNumber, buyingPrice, sellingPrice));
        }

        long oppCost [] = new long [T];
        for (int i = 0; i<T; i++){
            //int cardNumber == prices.get(i).getCardNumber();
            if (count[i] == 0){
                oppCost[i] = prices.get(i).getBuyingPrice() * 2;
            } else if (count[i] == 1) {
                oppCost[i] = prices.get(i).getBuyingPrice() + prices.get(i).getSellingPrice();
            } else {
                oppCost[i] = prices.get(i).getSellingPrice() *2;
            }       
        }  

        Arrays.sort(oppCost);

        long costK [] = Arrays.copyOfRange(oppCost,0, K);

        long profit = 0;
        //for (int i = 0; i < T; i++) {
          //  Prices price = prices.get(i);
            //profit += count[i] * price.getSellingPrice();
        //}
        for (Prices price : prices) {
            int cardNumber = price.getCardNumber();
            profit += count[cardNumber-1] * price.getSellingPrice();
        }

        for (int i = 0; i < K; i++) {
            profit -= costK[i];
        }
    System.out.println(profit);
    }

    //private static Prices getPrice(ArrayList<Prices> prices, int cardNumber) {
      //  for (Prices price : prices) {
        //    if (price.getCardNumber() == cardNumber) {
          //      return price;
            //}
        //}
        //return null; 
    //}

    //private static int count(int[] arr, int value) {
      //  int count = 0;
        //for (int i : arr) {
          //  if (i == value) {
            //    count++;
            //}
        //}
        //return count;
    //}
}

class Prices {
    private int cardNumber;
    private long buyingPrice;
    private long sellingPrice;

    public Prices(int c, long b, long s) {
        cardNumber = c;
        buyingPrice = b;
        sellingPrice = s;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public long getBuyingPrice() {
        return buyingPrice;
    }

    public long getSellingPrice() {
        return sellingPrice;
    }
}
