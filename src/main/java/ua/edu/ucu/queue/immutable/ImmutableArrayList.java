package ua.edu.ucu.queue.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {

    private int len;
    private Object[] arr;

    protected ImmutableArrayList() {
        arr = new Object[0];
    }

    protected ImmutableArrayList(int cap) {
        this.len = cap;
        arr = new Object[this.len];
    }

    protected ImmutableArrayList(Object[] array) {
        this.len = array.length;
        arr = new Object[len];
        System.arraycopy(array, 0, arr, 0, this.len);
    }

    private boolean isValidIndex(int index) {
        return index < this.len && index >= 0;
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return addAll(this.len, new Object[]{e});
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(this.len, c);
    }


    public ImmutableArrayList addAll(int index, Object[] c) {
        int finalLength = this.len + c.length;
        ImmutableArrayList lst = new ImmutableArrayList(finalLength);
        int k = 0, j = 0;
        for (int i = 0; i < finalLength; i++) {
            if (i >= index && i <= index + c.length - 1) {
                lst.arr[i] = c[j];
                j++;
            } else {
                lst.arr[i] = this.arr[k];
                if (k < this.len) {
                    k++;
                }
            }
        }
        return lst;
    }

    public Object get(int index) {
        if (isValidIndex(index)) {
            return this.arr[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public ImmutableArrayList remove(int index) {
        if (isValidIndex(index)) {
            ImmutableArrayList lst = new ImmutableArrayList(this.len - 1);
            System.arraycopy(this.arr, 0, lst.arr, 0, index);
            System.arraycopy(this.arr,
                    index + 1, lst.arr, index, this.len - index - 1);
            return lst;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public ImmutableArrayList set(int index, Object e) {
        Object[] lst = Arrays.copyOf(this.arr, this.len);
        if (isValidIndex(index)) {
            lst[index] = e;
            return new ImmutableArrayList(lst);
        } else {
            throw new IndexOutOfBoundsException("Entered index out of range.");
        }

    }

    public int indexOf(Object e) {
        for (int i = 0; i < this.len; i++) {
            if (this.arr[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return this.len;
    }

    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    public boolean isEmpty() {
        return this.len == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(arr, len);
    }

    public String toString() {
        return Arrays.toString(arr);
    }
}