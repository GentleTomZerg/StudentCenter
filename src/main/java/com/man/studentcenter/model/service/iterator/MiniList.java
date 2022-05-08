package com.man.studentcenter.model.service.iterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MiniList<T> implements Iterable<T> {
    private Object[] items;
    private int size;

    public MiniList() {
        items = new Object[10];
        size = 0;
    }

    public void add(T item) {
        if (size == items.length) {
            T[] largerItems = (T[]) new Object[items.length + 5];
            System.arraycopy(items, 0, largerItems, 0, items.length);
            items = largerItems;
            largerItems = null;
        }
        items[size] = item;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            // Method 3
            // Finding whether during iteration if
            // there is next element or not
            @Override
            public boolean hasNext() {
                return currentIndex < items.length && items[currentIndex] != null;
            }

            // Method 4
            // Going to grab each car element by one by one
            // according to the index
            @Override
            public T next() {
                return (T) items[currentIndex++];
            }

            // Method 5
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        return it;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    public static void main(String[] args) {
        MiniList<String> letters = new MiniList<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");

        Iterator<String> letterIterator = letters.iterator();
        while (letterIterator.hasNext()) {
            System.out.println(letterIterator.next());
        }
    }
}
