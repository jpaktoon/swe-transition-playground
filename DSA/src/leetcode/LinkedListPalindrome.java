package leetcode;

class LinkedListPalindrome {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode y = head;
        int length = 0;
        while (y != null) {
            length++;
            y = y.next;
        }

        // even -> length / 2; e.g. 4 so the middle is index 2 (0,1,[2],3), no ignore index
        // odd -> (length / 2) +1; e.g. 5 so the middle is index 3 (0,1,{2},[3],4) and ignore index 2

        if (head == null) return true;
        ListNode x = new ListNode();
        ListNode current = head;

        int middle = 0;
        int ignoreIndex = -1;
        if (length % 2 == 0) {
            //even
            middle = length / 2; // no ignoreIndex
        } else {
            //odd
            middle = (length / 2) + 1;
            ignoreIndex = length / 2;
        }
        int index = 0;

        while (current != null) {
            // remove - when reach the target index
            if (index >= middle && x.next != null) {
                if (current.val == x.next.val) {
                    x.next = x.next.next; // remove if duplicated
                    current = current.next; // move next
                } else {
                    return false; // cannot remove mean it is not palindrome, so we don't need to do more loop
                }
            } else if (index < middle && index != ignoreIndex) { // insert - when not reach the target index but ignore the ignored index too
                ListNode temp = x.next;
                x.next = current;
                current = current.next;
                x.next.next = temp;
            } else {
                current = current.next;
            }
            index++;
        }
        return x.next == null;
    }

    public static void main(String[] args){
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(1);
        ListNode four = new ListNode(2);

        one.next = two;
        two.next = three;
        //three.next = four;

        isPalindrome(one);
    }
}
