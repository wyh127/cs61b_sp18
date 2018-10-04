public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int next_first;
    private int next_last;

    /** construction function */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        next_first = 0;
        next_last = 1;
    }

    /** mod plus and minus to build a circular ArrayDeque */
    public int plusOne(int x) {
        return (x+1) % items.length;
    }

    public int minusOne(int x) {
        return (x+items.length-1) % items.length;
    }

    /** add function*/
    public void addFirst(T item) {
        if(isFull()) {
            resize(2 * size);
        }

        items[next_first] = item;
        next_first = minusOne(next_first);
        size += 1;
    }

    public void addLast(T item) {
        if(isFull()) {
            resize(2 * size);
        }

        items[next_last] = item;
        next_last = plusOne(next_last);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** monitor the usage factor */
    public boolean isSparse() {
        return (items.length > 16 && size / items.length < 0.25);
    }

    public boolean isFull() {
        return size == items.length;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = plusOne(next_first); i != next_last; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
    }

    /** remove function */
    public T removeFirst() {
        next_first = plusOne(next_first);
        T res = items[next_first];
        items[minusOne(next_first)] = null;
        size -= 1;

        if(isSparse()) {
            resize(size/2);
        }
        return res;
    }

    public T removeLast() {
        next_last = minusOne(next_last);
        T res = items[next_last];
        items[plusOne(next_last)] = null;
        size -= 1;

        if(isSparse()) {
            resize(size/2);
        }
        return res;
    }

    /** using mod */
    public T get(int index) {
        return items[(plusOne(next_first) + index) % items.length];
    }

    /** resizing function */
    public void resize(int newSize) {
        T[] oldItems = items;
        int oldFirst = plusOne(next_first);
        int oldLast = minusOne(next_last);

        items = (T[]) new Object[newSize];
        next_first = 0;
        next_last = 1;

        for(int i = oldFirst; i != oldLast; i = (i+1) % oldItems.length) {
            items[next_last] = oldItems[i];
            next_last = plusOne(next_last);
        }

        items[next_last] = oldItems[oldLast];
        next_last = plusOne(next_last);
    }
}
