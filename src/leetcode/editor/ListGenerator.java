package leetcode.editor;

//Project: leetcode_practice
//Package: leetcode.editor
//ClassName: ListGenerator
//Author: Jesse Yang
//Date: 2020-11-30 星期一 16:48
public class ListGenerator {
    
    public static ListNode generateSingleListOfListNode(String data) {
        String s = data.replaceAll("\\s*", "");
        String[] strArr = s.split(",");
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        for (String str : strArr) {
            ListNode cur = new ListNode(Integer.parseInt(str));
            prev.next = cur;
            prev = cur;
        }
        ListNode head = dummy.next;
        dummy.next = null;
        return head;
    }
    
    public static ListNode generateDoubleListOfListNode(String data) {
        String s = data.replaceAll("\\s*", "");
        String[] strArr = s.split(",");
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        for (String str : strArr) {
            ListNode cur = new ListNode(Integer.parseInt(str));
            prev.next = cur;
            cur.prev = prev;
            prev = cur;
        }
        ListNode head = dummy.next;
        dummy.next = null;
        head.prev = null;
        return head;
    }
    
}