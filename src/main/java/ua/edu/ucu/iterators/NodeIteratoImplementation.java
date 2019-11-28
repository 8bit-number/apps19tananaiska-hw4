package ua.edu.ucu.iterators;

import ua.edu.ucu.Node;
import ua.edu.ucu.queue.Queue;

public class NodeIteratoImplementation implements NodeIterator {


    private Queue<String> nodes;
    private int position;

    public NodeIteratoImplementation(Queue<String> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean hasNext() {
        return (nodes.isEmpty());
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            return nodes.dequeue();
        }
        return null;
    }
}
