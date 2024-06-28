package playground;

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
public class LRUCacheDoublyLinkedList {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    LRUCacheDoublyLinkedList(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } else {
            Node newNode = new Node(key, value);
            if (map.size() == capacity) {
                Node tail = removeTail();
                map.remove(tail.key);
            }
            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    private void addToHead(Node node) {
        Node next = head.next;
        node.prev = head;
        node.next = next;
        head.next = node;
        next.prev = node;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}
