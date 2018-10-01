
public class LinkedListDeque<T> {
    private class node {
        public T item;
        public node prev;
        public node next;

        public node(T i, node p, node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private node sentinel;

    public LinkedListDeque() {
        sentinel = new node(null, null, null);
        size = 0;
    }

    public void addFirst(T item) {
        if(isEmpty()) {
            sentinel.next = new node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        }
        else {
            sentinel.next = new node(item, sentinel, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T item) {
        if(isEmpty()) {
            sentinel.next = new node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        }
        else {
            sentinel.prev = new node(item, sentinel.prev, sentinel);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if(isEmpty()) {
            System.out.println("null");
        }
        else {
            node temp = sentinel;
            while(temp.next.item != null) {
                System.out.println(temp.next.item + " ");
                temp = temp.next;
            }
        }
    }

    public T removeFirst() {
        if(isEmpty()) return null;
        else {
            T first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return first;
        }

    }

    public T removeLast() {
        if(isEmpty()) return null;
        else {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last;
        }
    }

    public T get(int index) {
        if(isEmpty() || index > size-1) {
            return null;
        }

        int i = 0;
        node temp = sentinel;
        while(i <= index) {
            temp = temp.next;
            i++;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        if(isEmpty() || index > size-1) {
            return null;
        }

        if(index == 0) {
            return sentinel.next.item;
        }

        return getRecursive(index-1);
    }
}
