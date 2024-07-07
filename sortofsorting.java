import java.util.*;
import java.io.*;

public class sortofsorting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder(); 
        while (n != 0){ 
            String names [] = new String [n];
            for (int i = 0; i<n; i++){
                String name = br.readLine();
                names [i] = name;
            }

            Arrays.sort(names, Comparator.comparing((string -> string.substring(0,2))));

            for (int i = 0; i<n; i++){
                ans.append(names[i] + " ");
            }
            n = Integer.parseInt(br.readLine());
            ans.append("\n");
        }

        System.out.println(ans);
    }
}


    

