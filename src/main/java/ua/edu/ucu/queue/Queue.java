package ua.edu.ucu.queue;

import ua.edu.ucu.queue.immutable.ImmutableLinkedList;

public class Queue<T> {
    private ImmutableLinkedList list;

    public Queue() {
        list = new ImmutableLinkedList();
    }

    public Object peek() {
        if (!isEmpty()) {
            return list.getFirst();
        }
        return -1;
    }

    public Object dequeue() {
        if (!isEmpty()) {
            Object el = list.getFirst();
            list = list.removeFirst();
            return el;
        }
        return -1;
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public void enqueue(Object e) {
        list = list.addLast(e);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}