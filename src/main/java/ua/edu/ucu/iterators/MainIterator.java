package ua.edu.ucu.iterators;
import ua.edu.ucu.queue.Queue;

import java.util.Iterator;

public class MainIterator implements Iterable<String> {

    private final Queue<String> queue;
    private final Iterator<String> iterator = new Iterator<String>() {
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public String next() {
            return (String) queue.dequeue();
        }
    };

    public MainIterator(final Queue<String> queue) {
        this.queue = queue;
    }

    public Iterator<String> iterator() {
        return iterator;
    }

}