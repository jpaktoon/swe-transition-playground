package leetcode;

public class CircularQueue {

    int[] buffer;
    int firstIndex;
    int lastIndex;
    int currentSize;

    public CircularQueue(int k) {
        buffer = new int[k];
        firstIndex = -1;
        lastIndex = -1;
        currentSize = 0;
    }

    public boolean enQueue(int value) {
        if (!isFull()) {
            if (isEmpty()) {
                buffer[0] = value;
                firstIndex = 0;
                lastIndex = 0;
                currentSize = 1;
            } else {
                // [-, 1, 2, 3] => enQueue 4
                lastIndex++;
                if (lastIndex >= buffer.length) lastIndex = 0; // circle
                currentSize++;
                buffer[lastIndex] = value;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean deQueue() {
        if (!isEmpty()) {
            firstIndex++;
            currentSize--;
            if (firstIndex >= buffer.length) firstIndex = 0; // circle
            emptyQueue();
            return true;
        } else {
            return false;
        }
    }

    private void emptyQueue() {
        if (isEmpty()) {
            firstIndex = -1;
            lastIndex = -1;
            currentSize = 0;
        }
    }

    public int Front() {
        return isEmpty() ? -1 : buffer[firstIndex];
    }

    public int Rear() {
        return isEmpty() ? -1 : buffer[lastIndex];
    }

    public boolean isEmpty() {
        return currentSize <= 0;
    }

    public boolean isFull() {
        return currentSize >= buffer.length;
    }
}
