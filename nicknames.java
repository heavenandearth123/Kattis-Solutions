import java.util.*;
import java.io.*;

public class nicknames {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int noOfNames = Integer.parseInt(br.readLine());

        BST tree = new BST();

        for (int i = 0; i < noOfNames; i++) {
            String name = br.readLine();
            tree.insert(name);
        }

        int noOfNicknames = Integer.parseInt(br.readLine());

        HashMap<String, Integer> queries = new HashMap<>();

        for (int i = 0; i < noOfNicknames; i++) {
            String nickname = br.readLine();

            if (queries.containsKey(nickname)) {
                writer.println(queries.get(nickname));
            } else {
                int count = 0;
                count = tree.search(tree.root, nickname);
                queries.put(nickname, count);

                writer.println(count);
            }
        }

        writer.flush();
    }
}

class BSTVertex {
    BSTVertex(String v) { key = v; parent = left = right = null; height = 0; }
    // all these attributes remain public to slightly simplify the code
    public BSTVertex parent, left, right;
    public String key;
    public int height; // will be used in lecture on AVL
    public int size; // will be used in lecture on AVL
}

class BST {
    public BSTVertex root;
  
    public BST() { root = null; }
  
    public int search(String v) {
        int count = 0;
        search(root, v);
        return count;
    }

    public int search(BSTVertex T, String v) {
        if (T == null) {
            return 0;
        }
    
        int count = 0;
        
        if (T.key.startsWith(v)) {
            count += 1;
            count += search(T.left, v);
            count += search(T.right, v);
        } else if (v.compareTo(T.key) < 0) {
            count += search(T.left, v);
        } else {
            count += search(T.right, v);
        }
    
        return count;
    }

    public void insert(String v) { root = insert(root, v); }

    public BSTVertex insert(BSTVertex T, String v) {
        if (T == null) {
            return new BSTVertex(v); 
        }
        if (T.key.compareTo(v) < 0) {
            T.right = insert(T.right, v);
            T.right.parent = T;
        } else {                                                 
            T.left = insert(T.left, v);                     
            T.left.parent = T;
        }

        T.height = Math.max(height(T.left), height(T.right)) + 1;

        int balance = balanceFactor(T);
        int balanceLeft = balanceFactor(T.left);
        int balanceRight = balanceFactor(T.right);

        // Left Left Case
        if (balance == 2 && (balanceLeft >= 0 && balanceLeft <= 1)) {
            T = rotateRight(T);
        }

        // Right Right Case
        if (balance == -2 && (balanceRight >= -1 && balanceRight <= 0)) {
            T = rotateLeft(T);
        }

        // Left Right Case
        if (balance == 2 && balanceLeft == -1) {
            T.left = rotateLeft(T.left);
            T = rotateRight(T);
        }

        // Right Left Case
        if (balance == -2 && balanceRight == 1) {
            T.right = rotateRight(T.right);
            T = rotateLeft(T);
        }
        
        return T;                                         
    }  

    public int balanceFactor(BSTVertex T) {
        if (T == null)
            return 0;
        return height(T.left) - height(T.right);
    }

    public int height(BSTVertex T) {
        if (T == null)
            return -1;
        return T.height;
    }

    public BSTVertex rotateLeft (BSTVertex T) {

        BSTVertex w = T.right;
        w.parent = T.parent;
        T.parent = w;
        T.right = w.left;
        if (w.left != null) {
            w.left.parent = T;
        }
        w.left = T;

        T.height = Math.max(height(T.left), height(T.right)) + 1;
        w.height = Math.max(height(w.left), height(w.right)) + 1;

        return w;

    }

    public BSTVertex rotateRight (BSTVertex T) {

        BSTVertex w = T.left;
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;
        if (w.right != null) {
            w.right.parent = T;
        }
        w.right = T;

        T.height = Math.max(height(T.left), height(T.right)) + 1;
        w.height = Math.max(height(w.left), height(w.right)) + 1;

        return w;

    }
}


