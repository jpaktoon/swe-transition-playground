package test.datastructures.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.datastructures.linkedlist.LinkedList;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    @Test
    public void testfindMiddleNode_Odd(){
        LinkedList normal = new LinkedList(5);
        normal.append(6);
        normal.append(7);
        normal.append(8);
        normal.append(9);

        assertEquals(7, normal.findMiddleNode().getValue());
    }

    @Test
    public void testfindMiddleNode_Even(){
        LinkedList normal = new LinkedList(5);
        normal.append(6);
        normal.append(7);
        normal.append(8);

        assertEquals(7, normal.findMiddleNode().getValue());
    }

    @Test
    public void testfindMiddleNode_One(){
        LinkedList normal = new LinkedList(5);
        assertEquals(5, normal.findMiddleNode().getValue());
    }

    @Test
    public void testfindMiddleNode_None(){
        LinkedList normal = new LinkedList(5);
        normal.removeLast();
        assertEquals(null, normal.findMiddleNode());
    }
}
