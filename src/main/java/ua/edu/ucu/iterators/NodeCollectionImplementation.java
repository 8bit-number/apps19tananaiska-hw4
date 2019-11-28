package ua.edu.ucu.iterators;

import ua.edu.ucu.Node;
import ua.edu.ucu.queue.Queue;

import java.util.Arrays;
//import ua.edu.ucu.tries.RWayTrie.Node;


public class NodeCollectionImplementation implements NodeCollection{


    public Queue<String> queue;

    public NodeCollectionImplementation() {
        this.queue = new Queue<>();
    }


    @Override
    public void addNodeToTrie(Node node, String pref) {
        if (node.val != null) {
            this.queue.enqueue(pref);
        }
        for (int i = 0; i < node.next.length; i++) {
            Node child = node.next[i];
            if (child != null) {
                char letter = (char) (i + 'a');
                addNodeToTrie(node.next[i], pref + letter);
            }
        }
    }

//    public Queue<String> getQueue(){
//
//    }

    @Override
    public NodeIterator iterator() {
        System.out.println(Arrays.toString(this.queue.toArray()));
        return new NodeIteratoImplementation(this.queue);
    }

}
