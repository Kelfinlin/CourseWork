package deque;
import java.util.Iterator;
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T [] item;
    private int size;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
    }

    @Override
    public void addFirst(T t) {
        if (size == item.length) {
            resize(size * 2);
        }
        T [] a = (T[]) new Object[item.length];
        System.arraycopy(item, 0, a, 1, size);
        a[0] = t;
        item = a;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        item[size] = x;
        size = size + 1;
    }

    @Override
    public int size() {
        if (size <= 0) {
            return 0;
        }
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(item[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if ((size < item.length / 4) && (size > 4)) {
            resize(item.length / 4);
        }
        if (size > 0) {
            T[] a = (T[]) new Object[item.length];
            T returnStuff = item[0];
            System.arraycopy(item, 1, a, 0, item.length - 1);
            size--;
            item = a;
            return returnStuff;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if ((size < item.length / 4) && (size > 4)) {
            resize(item.length / 4);
        }
        if (size > 0) {
            T returnStuff = get(size - 1);
            item[size - 1] = null;
            size--;
            return returnStuff;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return item[index];
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            int counter = 0;
            if (size != ((Deque<Object>) o).size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                T compare = (T) ((Deque) o).get(i);
                if (item[i].equals(compare)) {
                    counter++;
                }
            }
            if (counter == size) {
                return true;
            }
        }
        return false;
    }

    private void resize(int capacity) {
        T[] a = (T[])  new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            a[i] = item[i];
        }
        item = a;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int inSize;
        ArrayDequeIterator() {
            inSize = 0;
        }
        public boolean hasNext() {
            return inSize < size();
        }
        public T next() {
            T returnItem = get(inSize);
            inSize++;
            return returnItem;
        }

    }
}
