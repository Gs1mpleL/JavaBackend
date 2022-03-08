package Leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 反转链表的两种方法
 * @author wanfeng
 * @create 2022/3/6 9:47
 * @package Leetcode
 */
public class ReverseListNode {

     class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 迭代法  tmp保存cur的下一个节点，将cur指向上一个结点
     */
    public ListNode diedai(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    /**
     * 递归法
     */
    public ListNode digui(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = digui(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
