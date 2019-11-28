package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.*;
import java.util.*;

/**
 * The type Prefix matches.
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    /**
     * Instantiates a new Prefix matches.
     *
     * @param trie the trie
     */
    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    /**
     * Load int.
     *
     * @param strings the strings
     * @return the int
     */
    public int load(String... strings) {

        int totalResult = 0;
        for (int i = 0; i < strings.length; i++) {
            String[] newStr = strings[i].split(" ");
            for (String el : newStr) {
                if (el.length() > 2) {
                    totalResult++;
                    this.trie.add(new Tuple(el, el.length()));
                }
            }
        }
        return totalResult;
    }

    /**
     * Contains boolean.
     *
     * @param word the word
     * @return the boolean
     */
    public boolean contains(String word) {
        return this.trie.contains(word);
    }

    /**
     * Delete boolean.
     *
     * @param word the word
     * @return the boolean
     */
    public boolean delete(String word) {
        return this.trie.delete(word);
    }

    /**
     * Words with prefix iterable.
     *
     * @param pref the pref
     * @return the iterable
     */
    public Iterable<String> wordsWithPrefix(String pref) {
        return this.trie.wordsWithPrefix(pref);
    }

    /**
     * Words with prefix iterable.
     *
     * @param pref the pref
     * @param k    the k
     * @return the iterable
     */
    public Iterable<String> wordsWithPrefix(String pref, int k) {
        List<String> newList = new ArrayList<>();
        Iterable<String> lst = this.trie.wordsWithPrefix(pref);
        for (String e : lst) {
            newList.add(e);
        }

        Collections.sort(newList, (o1, o2) -> {
            return  (o1.length() - o2.length());
        });

        List<String> fin = new ArrayList<>();

        for (int i= 0; i <= k; i++) {
            fin.add(newList.get(i));
        }
        return fin;

    }
}
