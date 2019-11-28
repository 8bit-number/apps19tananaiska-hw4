package ua.edu.ucu.tries;


import java.util.*;

import ua.edu.ucu.Node;
import ua.edu.ucu.iterators.*;


/**
 * The class, representing the R way trie.
 */
public class RWayTrie implements Trie {
    private Node root;
    private int wordsNumber;

    /**
     * Get the weight of the word.
     *
     * @param word the word
     * @return the integer
     */
    public Integer get(String word) {
        Node x = get(root, word, 0);
        if (x == null) return null;
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
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
        char c = key.charAt(d);
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

    /**
     * Delete node.
     *
     * @param x   the x
     * @param key the key
     * @param d   the d
     * @return the node
     */
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
        if (pref.length() > 2) {
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
        return null;
    }

    @Override
    public int size() {
        return this.wordsNumber;
    }
}
