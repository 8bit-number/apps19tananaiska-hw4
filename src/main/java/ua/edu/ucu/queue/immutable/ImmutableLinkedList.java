package ua.edu.ucu.queue.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {

    private int len;
    private Node head;

    public ImmutableLinkedList() {
        head = null;
        len = 0;
    }

    public ImmutableLinkedList(Object[] c) {
        if (c.length != 0) {
            head = new Node(c[0]);
            Node currentNode = head;
            for (int i = 1; i < c.length; i++) {
                currentNode.next = new Node(c[i]);
                currentNode = currentNode.next;
            }
        }
        len = c.length;
    }

    private static class Node {
        private Object data;
        private Node next;

        private Node(Object value) {
            this.data = value;
            this.next = null;
        }

        private Node(Object value, Object nex) {
            this.data = value;
            this.next = (Node) nex;
        }
    }

    private Node getNode(int index) {
        Node current = head;
        for (int counter = 0; counter < index; counter++) {
            current = current.next;
        }
        return current;
    }

    private void isValidIndex(int index) {
        if (index < 0 || index > this.len) {
            throw new IndexOutOfBoundsException();
        }
    }


    private ImmutableLinkedList copy() {
        if (len == 0) {
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList lst = new ImmutableLinkedList();
        lst.len = len;
        lst.head = new Node(head.data);
        Node currentNode = head.next;
        Node currentAddingNode = lst.head;
        while (currentNode != null) {
            currentAddingNode.next = new Node(currentNode.data);
            currentAddingNode = currentAddingNode.next;
            currentNode = currentNode.next;
        }
        return lst;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(len, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        isValidIndex(index);
        ImmutableLinkedList newList = copy();

        if (index == 0) {
            newList.head = new Node(e, newList.head);
        } else {
            Node before = newList.getNode(index - 1);
            before.next = new Node(e, before.next);
        }
        newList.len = len + 1;
        return newList;
    }


    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(len, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        isValidIndex(index);
        ImmutableLinkedList lst = copy();
        Object[] listed = lst.toArray();
        Object[] finalLst = new Object[this.len + c.length];
        int k = 0, j = 0;
        for (int i = 0; i < this.len + c.length; i++) {
            if (i >= index && i <= index + c.length - 1) {
                finalLst[i] = c[j];
                j++;
            } else {
                finalLst[i] = listed[k];
                if (k < this.len) {
                    k++;
                }
            }
        }
        ImmutableLinkedList fin = new ImmutableLinkedList(finalLst);
        fin.len = this.len + c.length;
        return fin;
    }

    @Override
    public Object get(int index) {
        isValidIndex(index);
        return getNode(index).data;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        isValidIndex(index);
        if (!isEmpty()) {
            ImmutableLinkedList newTemp = copy();
            if (index == 0) {
                newTemp.head = newTemp.head.next;
            } else {
                Node front = newTemp.getNode(index - 1);
                front.next = front.next.next;
            }
            newTemp.len--;
            return newTemp;
        }
        return clear();

    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        isValidIndex(index);
        ImmutableLinkedList newTemp = copy();
        newTemp.getNode(index).data = e;
        return newTemp;
    }

    @Override
    public int indexOf(Object e) {
        Node prev = this.head;
        int index = 0;
        while (prev != null) {
            if (prev.data.equals(e)) {
                return index;
            } else {
                prev = prev.next;
                index++;
            }
        }
        return -1;
    }

    public Object getFirst() {
        return get(0);

    }

    public Object getLast() {
        return get(this.len - 1);
    }

    public ImmutableLinkedList addFirst(Object c) {
        return add(0, c);
    }

    public ImmutableLinkedList addLast(Object c) {
        return add(c);
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(this.len - 1);
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.len == 0;
    }

    @Override
    public Object[] toArray() {
        if (head == null) {
            return new Object[0];
        } else {
            Object[] arr = new Object[len];
            Node curr = head;
            int i = 0;
            while (curr != null) {
                arr[i] = curr.data;
                curr = curr.next;
                i++;
            }
            return arr;
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "";
        } else {
            Node curr = head;
            Object[] arr = new Object[len];
            int i = 0;
            while (curr != null) {
                arr[i] = curr.data;
                curr = curr.next;
                i++;
            }
            return Arrays.toString(arr);
        }
    }
}
