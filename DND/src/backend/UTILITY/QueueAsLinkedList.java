package backend.UTILITY;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class QueueAsLinkedList<T> {

    private List<T> list;

    public QueueAsLinkedList() {
        this.list = new LinkedList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enqueue(T element) {
        if(element == null)
            throw new NullPointerException();
        list.add(element);
    }


    public T dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();
        return list.remove(0);
    }

    public T peek() {
        if(isEmpty())
            throw new NoSuchElementException();
        return list.get(0);
    }


}
