package ua.edu.ucu;

public class Node {

    public Integer val;
    public Node[] next ;

    public Node(Integer val){
        this.val = val;
        this.next = new Node[26];
    }

    public Node() {
        this.val = null;
        this.next = new Node[26];
    }
}
