package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.*;
import ua.edu.ucu.iterators.MainIterator;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {

        int totalResult = 0;
        for (int i = 0; i < strings.length; i++) {
            String[] newStr = strings[i].split(" ");
            for (String el : newStr) {
                if (el.length() > 2) {
                    totalResult++;
                    trie.add(new Tuple(el, el.length()));
                }
            }
        }
        return totalResult;
    }

    public boolean contains(String word) {
        return this.trie.contains(word);
    }

    public boolean delete(String word) {
        return this.trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return this.trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {

        List<String> newList = new ArrayList<>();
        Iterable<String> lst = wordsWithPrefix(pref);
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

//    public static void main(String[] args) {
//
//        PrefixMatches pm = new PrefixMatches(new RWayTrie());
//        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
//        System.out.println(pm.wordsWithPrefix("abc", 3));
//    }
}
