package ua.edu.ucu.tries;


import java.util.*;

import ua.edu.ucu.Node;
import ua.edu.ucu.iterators.*;


public class RWayTrie implements Trie {
    private Node root;
    private int wordsNumber;

    public Integer get(String word) {
        Node x = get(root, word, 0);
        if (x == null) return null;
        return x.val;
    }

    private Node get(Node x, String key, int d) { // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c - 'a'], key, d + 1);
    }

    @Override
    public void add(Tuple word) {
        String key = word.term;
        int val = word.weight;
        root = put(root, key, val, 0);
        this.wordsNumber++;
    }

    private Node put(Node x, String key, Integer val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        x.next[c - 'a'] = put(x.next[c - 'a'], key, val, d + 1);
        return x;
    }

    @Override
    public boolean contains(String word) {
        return get(word) != null;
    }

    @Override
    public boolean delete(String word) {
        if (contains(word)) {
            root = delete(root, word, 0);
            if (root != null) {
                this.wordsNumber--;
                return true;
            }
        }
        return false;
    }

    public Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c - 'a'] = delete(x.next[c - 'a'], key, d + 1);

        }
        if (x.val != null) return x;

        for (char c = 0; c < 26; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        NodeCollection ncoll = new NodeCollectionImplementation();
        ncoll.addNodeToTrie(get(root, pref, 0), pref);
        List<String> lst = new ArrayList<>();
        NodeIterator nodeIterator = ncoll.iterator();
        while (!nodeIterator.hasNext()) {
            Object n = nodeIterator.next();
            lst.add((String) n);
        }
        return lst;
    }


    @Override
    public int size() {
        return this.wordsNumber;
    }

//    public static void main(String[] args) {
//        RWayTrie rr = new RWayTrie();
//        rr.add(new Tuple("aaa", 3));
//        rr.add(new Tuple("bb", 2));
//        rr.add(new Tuple("aab", 3));
//        rr.add(new Tuple("aaabb", 5));
//        Iterable<String> x = rr.wordsWithPrefix("aa");
//        System.out.println(x);
////        for (String i : x){
////            System.out.println((String) i);
////        }
//
//    }
//
}
