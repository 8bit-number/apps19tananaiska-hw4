package ua.edu.ucu.iterators;

import ua.edu.ucu.Node;
import ua.edu.ucu.iterators.*;

public interface NodeCollection {

    public void addNodeToTrie(Node node, String pref);

    public NodeIterator iterator();
}
