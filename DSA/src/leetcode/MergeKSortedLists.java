package leetcode;

import leetcode.utils.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

    public static ListNode mergeKLists(ListNode[] lists) {
        int nodesCount = lists.length;
        if (nodesCount == 0) return null;
        ListNode newHead = null;
        ListNode newTail = null;
        while (true) {
            ListNode lowestNode = null;
            int targetNode = 0;
            for (int i = 0; i <= nodesCount - 1; i++) {
                if (lists[i] == null) continue; // skip
                if (lowestNode == null || lists[i].val < lowestNode.val) {
                    lowestNode = lists[i];
                    targetNode = i;
                }
            }

            if (lowestNode == null) {
                break; // if there is no lowestNode, mean nothing left in the lists
            } else {
                lists[targetNode] = lists[targetNode].next; // move to target node to its next node
                if (newHead == null) { // for the first time, we need to set a head and tail
                    newHead = lowestNode;
                    newTail = lowestNode;
                } else { // add the node to LL and move tail
                    newTail.next = lowestNode;
                    newTail = newTail.next;
                }
            }
        }
        return newHead;
    }

    public static ListNode mergeKLists2(ListNode[] lists) {
        int nodesCount = lists.length;
        if (nodesCount == 0) return null;
        ListNode newHead = null;
        ListNode newTail = null;

        PriorityQueue<ListNode> pQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));

        // add head of each LL to PriorityQueue
        for (ListNode node : lists) {
            if (node != null) pQueue.offer(node);
        }

        while(!pQueue.isEmpty()) {
            ListNode smallestNode = pQueue.poll();
            if (newHead == null) {
                newHead = smallestNode;
                newTail = smallestNode;
            } else {
                newTail.next = smallestNode;
                newTail = newTail.next;
            }
            if (smallestNode.next != null) {
                pQueue.add(smallestNode.next);
            }
            newTail.next = null; // cut the relation from the old node
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode a111 = new ListNode(5);
        ListNode a11 = new ListNode(4, a111);
        ListNode a1 = new ListNode(1, a11);

        ListNode a222 = new ListNode(4);
        ListNode a22 = new ListNode(3, a222);
        ListNode a2 = new ListNode(1, a22);

        ListNode a33 = new ListNode(6);
        ListNode a3 = new ListNode(2, a33);

//        ListNode result = mergeKLists(new ListNode[] {a1, a2, a3});
//        while(result != null) {
//            System.out.print(result.val + "->");
//            result = result.next;
//        }

        ListNode result = mergeKLists2(new ListNode[] {a1, a2, a3});
        while(result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
    }
}
