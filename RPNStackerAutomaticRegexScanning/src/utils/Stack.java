package utils;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack<E> {

    private LinkedList list;

    public Stack() {
        list = new LinkedList<E>();
    }

    public void push(E element) {
        list.addFirst(element);
    }

    public E pop() throws NoSuchElementException {
        return (E) list.removeFirst();
    }
}
