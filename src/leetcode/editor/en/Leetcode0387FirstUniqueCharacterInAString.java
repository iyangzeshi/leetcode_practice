//Given a string, find the first non-repeating character in it and return its in
//dex. If it doesn't exist, return -1. 
//
// Examples: 
//
// 
//s = "leetcode"
//return 0.
//
//s = "loveleetcode"
//return 2.
// 
//
// 
//
// Note: You may assume the string contains only lowercase English letters. 
// Related Topics Hash Table String 
// 👍 2170 👎 120

package leetcode.editor.en;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 2020-09-15 16:54:43
// Zeshi Yang
public class Leetcode0387FirstUniqueCharacterInAString{
    // Java: first-unique-character-in-a-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0387FirstUniqueCharacterInAString().new Solution();
        // TO TEST
        String s = "amazon";
        int res = sol.firstUniqChar(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    /*
    key is Character, Node is corresponding Node
    if Node just appeared once
     */
    Map<Character, Node> charToIndex;
    DoubleList list;
    
    public int firstUniqChar(String s) {
        if (s.length() == 0) {
            return -1;
        }
        
        charToIndex = new HashMap<>();
        list = new DoubleList();
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!charToIndex.containsKey(ch)) {
                Node cur = new Node(i);
                // charToIndex.put(ch, new Node(i));
                // list.addTail(new Node(i));
                /*
                上面注释的写法是有问题的，HashMap存的Object是地址，你这两个Node虽然值一样，但是地址不一样，
                后面的删除肯定会出问题的，因为在hashMap.get得到的这个Node没有prev和next
                 */
                charToIndex.put(ch, cur);
                list.addTail(cur);
                
            } else if (charToIndex.get(ch).val != null) {
                list.remove(charToIndex.get(ch));
                charToIndex.put(ch, new Node(null)); // put value to null表示这个值是重复的
            }
        }
        return list.head.next.val;
    }
    
    private class Node {
        
        Integer val;
        Node prev;
        Node next;
        
        public Node() {
        }
        
        
        public Node(Integer val) {
            this.val = val;
        }
        
    }
    
    class DoubleList {
        
        Node head;
        Node tail;
        int size;
        
        public DoubleList() {
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
        }
        
        private void addTail(Node node) {
            if (tail == null) { // 你上面就没有用到add(null)的地方
                return;
            }
            node.next = tail;
            node.prev = tail.prev;
            node.prev.next = node;
            tail.prev = node;
            size++;
        }
        
        private void remove(Node node) {
            if (node == null) {
                return;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            size--;
        }
        
        public int size() {
            return this.size;
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: two passes, HashMap
// T(n) = O(n), S(n) = O(n)
// 23 ms,击败了33.80% 的Java用户, 39.3 MB,击败了77.12% 的Java用户
class Solution1 {
    
    public int firstUniqChar(String s) {
        Map<Character, Integer> charCount = new HashMap<>(); // key: char, value: count of it
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (charCount.get(ch) == 1) {
                return i;
            }
        }
        return -1;
    }
    
}

// Solution 2: one pass, HashMap + DoubleLinkedList
// T(n) = O(n), S(n) = O(n)
// 17 ms,击败了58.97% 的Java用户, :39.5 MB,击败了47.99% 的Java用户
/*
if input is stream of String, it works as well.
 */
class Solution2 {
    
    Map<Character, Node> charToIndex;
    DoubleList list;
    
    public int firstUniqChar(String s) {
        if (s.length() == 0) {
            return -1;
        }
        
        charToIndex = new HashMap<>();
        list = new DoubleList();
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!charToIndex.containsKey(ch)) {
                Node cur = new Node(i);
                // charToIndex.put(ch, new Node(i));
                // list.addTail(new Node(i));
                /*
                上面注释的写法是有问题的，HashMap存的Object是地址，你这两个Node虽然值一样，但是地址不一样，
                后面的删除肯定会出问题的，因为在hashMap.get得到的这个Node没有prev和next
                 */
                charToIndex.put(ch, cur);
                list.addTail(cur);
                
            } else if (charToIndex.get(ch).val != null) {
                list.remove(charToIndex.get(ch));
                charToIndex.put(ch, new Node(null)); // put value to null表示这个值是重复的
            }
        }
        return list.head.next.val;
    }
    
    private class Node {
        
        Integer val;
        Node prev;
        Node next;
        
        public Node(Integer val) {
            this.val = val;
        }
        
    }
    
    class DoubleList {
        
        Node head;
        Node tail;
        int size;
        
        public DoubleList() {
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
        }
        
        private void addTail(Node node) {
            if (tail == null) { // 你上面就没有用到add(null)的地方
                return;
            }
            node.next = tail;
            node.prev = tail.prev;
            node.prev.next = node;
            tail.prev = node;
            size++;
        }
        
        private void remove(Node node) {
            if (node == null) {
                return;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            size--;
        }
        
    }
    
}

// Solution 3: one pass, HashMap + Queue
// T(n) = O(n), S(n) = O(n)
class Solution3 {
    
    public int firstUniqChar(String s) {
        Queue<Integer> queue = new LinkedList<>();
        int n = 'z' - 'a' + 1;
        
        /*
        null: have not appeared,
        false: appeared once;
        true: appeared more than once
        */
        Boolean[] visited = new Boolean[n];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int idx = s.charAt(i) - 'a';
            if (visited[idx] == null) {
                queue.offer(i);
                visited[idx] = false;
            } else {
                visited[idx] = true;
                if (!queue.isEmpty() && visited[s.charAt(queue.peek()) - 'a']) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.peek();
    }
    
}

}