package _2023.misc;

public class AddTwoNumbers {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {this.val = val;}

        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0, carry = 0;
        ListNode result = new ListNode(sum, new ListNode());
        ListNode copy = result;


        while (true) {
            sum = carry;
            if (l1 != null) {
                System.out.print(String.format("l1 = %d, %s : ", l1.val, l1.next));
                sum = sum + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                System.out.print(String.format("l2 = %d, %s : ", l2.val, l2.next));
                sum = sum + l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            result.val = sum % 10;
            result.next = new ListNode();
            System.out.println(String.format("r = %d, %s : ", result.val, result.next));
            if (l1 == null && l2 == null) {
                if (carry > 0) {
                    result.next.val = carry;
                } else {
                    result.next = null;
                }
                break;
            }
            result = result.next;
        }
        return copy;
    }
}
