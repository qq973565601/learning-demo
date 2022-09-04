package cn.lzx.letcode.easy;

/**
 * 2. 两数相加
 *
 * @author lzx
 * @since 2022/6/13
 */
public class Code002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int count = 0;
        int target = 0;
        int num = 0;
        ListNode l3 = new ListNode();
        ListNode a = l3;
        while (true) {
            if (l1 == null) {
                num = l2.val + count;
                target = num;
            } else if (l2 == null) {
                num = l1.val + count;
                target = num;
            } else {
                num = l1.val + l2.val + count;
                target = num;
            }
            count = 0;
            if (num >= 10) {
                target = num;
                num = num % 10;
                count = 1;
            }
            a.val = num;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            if (l1 == null && l2 == null) {
                if (target >= 10) {
                    a.next = new ListNode(1);
                }
                break;
            } else {
                a.next = new ListNode();
                a = a.next;
            }
        }
        return l3;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
