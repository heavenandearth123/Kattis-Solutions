import java.util.*;
import java.io.*;

public class joinstring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noOfStrings = Integer.parseInt(br.readLine());

        MyLinkedList words [] = new MyLinkedList[noOfStrings];
        for (int i = 0; i < noOfStrings; i++) {
            words[i] = new MyLinkedList(br.readLine());
        }

        if (noOfStrings == 1) {
            System.out.println(words[0].head.item);
        } else {
            int indexOfA = 0;
            int indexOfB = 0;
            for (int j = 0; j < noOfStrings - 1; j++) {
                String[] instructions = br.readLine().split(" ");
                indexOfA = Integer.parseInt(instructions[0]) - 1;
                indexOfB = Integer.parseInt(instructions[1]) - 1;

                MyLinkedList a = words[indexOfA];
                MyLinkedList b = words[indexOfB];

                a.tail.next = b.head;
                a.tail = b.tail;
            }

            StringBuilder sentence = new StringBuilder();
            ListNode cur = words[indexOfA].head;
            while (cur != null){ 
                sentence.append(cur.item); 
                cur = cur.next; 
            } 
 
            System.out.print(sentence);

        }
    }
}

class ListNode {
    public String item;
    public ListNode next;

    public ListNode(String val) {
        item = val;
        next = null;
    }
}

class MyLinkedList {
    public ListNode tail;
    public ListNode head;

    public MyLinkedList(String s) {
        head = new ListNode(s);
        tail = head;
    }
}
