import java.util.*;

public class t9spelling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int no_of_input = sc.nextInt();
        sc.nextLine();
        String letters[] = new String[256];
        letters ['a'] = "2";
        letters ['b'] = "22";
        letters ['c'] = "222";
        letters ['d'] = "3";
        letters ['e'] = "33";
        letters ['f'] = "333";
        letters ['g'] = "4";
        letters ['h'] = "44";
        letters ['i'] = "444";
        letters ['j'] = "5";
        letters ['k'] = "55";
        letters ['l'] = "555";
        letters ['m'] = "6";
        letters ['n'] = "66";
        letters ['o'] = "666";
        letters ['p'] = "7";
        letters ['q'] = "77";
        letters ['r'] = "777";
        letters ['s'] = "7777";
        letters ['t'] = "8";
        letters ['u'] = "88";
        letters ['v'] = "888";
        letters ['w'] = "9";
        letters ['x'] = "99";
        letters ['y'] = "999";
        letters ['z'] = "9999";
        letters [' '] = "0";
        for (int words = 1; words <= no_of_input; words++){
            String word = sc.nextLine();
            int lengthOfWord = word.length();
            String result = "Case #" + words +": ";
            for (int c = 0; c < lengthOfWord; c++){
                char letter = word.charAt(c);
                String keypress = letters[letter];
                if (keypress.charAt(0) == result.charAt(result.length()-1)){
                    result += " ";
                } 
                result += keypress;
            }
            System.out.println(result);
        }
    }
}
