package mockwb.consensus;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private T nextElement;  // Holds the next element for peeking
    private boolean hasPeeked;  // Tracks whether we've already peeked

    // Constructor
    public CustomIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        this.hasPeeked = false;
    }

    // Peek at the next element without advancing the iterator
    public T peek() {
        if (!hasPeeked) {
            if (!iterator.hasNext()) {
                throw new NoSuchElementException();
            }
            nextElement = iterator.next();
            hasPeeked = true;
        }
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasPeeked) {
            return iterator.next();
        }
        T result = nextElement;
        hasPeeked = false;  // Reset the peek flag after consuming
        nextElement = null;  // Clear the cached value
        return result;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

