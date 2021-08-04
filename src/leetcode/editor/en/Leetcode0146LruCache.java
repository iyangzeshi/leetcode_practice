//Design and implement a data structure for Least Recently Used (LRU) cache. It 
//should support the following operations: get and put. 
//
// get(key) - Get the value (will always be positive) of the key if the key exis
//ts in the cache, otherwise return -1. 
//put(key, value) - Set or insert the value if the key is not already present. W
//hen the cache reached its capacity, it should invalidate the least recently used
// item before inserting a new item. 
//
// The cache is initialized with a positive capacity. 
//
// Follow up: 
//Could you do both operations in O(1) time complexity? 
//
// Example: 
//
// 
//LRUCache cache = new LRUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.put(4, 4);    // evicts key 1
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4
// 
//
// 
// Related Topics Design 
// 👍 6498 👎 275

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2020-09-09 17:16:07
// Zeshi Yang
public class Leetcode0146LruCache {
    // Java: lru-cache
    public static void main(String[] args) {
        int capacity = 2;
        LRUCache cache = new Leetcode0146LruCache().new LRUCache(capacity);
        // TO TEST
        cache.put(2, 1);
        cache.put(2, 2);
        cache.get(2);
        cache.put(1, 1);
        cache.put(4, 1);
        cache.get(2);
        /*cache.get(1);
        cache.get(3);
        cache.get(4);
        cache.put(4, 4);*/
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution: HashMap + customized Double Linked List, 思路更清楚版本
// T(n) = O(1), S(n) = O(n)
class LRUCache {
    
    private final int capacity;
    private final Map<Integer, Leetcode0146LruCache.Node> map;
    private final Leetcode0146LruCache.Node head; // dummy head
    private final Leetcode0146LruCache.Node tail; // dummy tail
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Leetcode0146LruCache.Node(0, 0);
        tail = new Leetcode0146LruCache.Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Leetcode0146LruCache.Node node = map.get(key);
        node.disconnectNeighbor();
        AddToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        Leetcode0146LruCache.Node node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = value;
            node.disconnectNeighbor();
            AddToHead(node);
        } else {
            node = new Leetcode0146LruCache.Node(key, value);
            map.put(key, node);
            AddToHead(node);
            if (map.size() > capacity) { // remove last one to get the place for new node
                Leetcode0146LruCache.Node lastNode = tail.prev;
                lastNode.disconnectNeighbor();
                map.remove(lastNode.key);
            }
        }
    }
    
    private void AddToHead(Leetcode0146LruCache.Node node) {
        // move node to first place
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }
    
}

class Node {
    
    /* these fields, to do private, add getter & setter */
    int key;
    int val; // value paired to key
    Node prev;
    Node next;
    
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
    
    public void disconnectNeighbor() {
        /*
        这里是否为null不需要check，因为只在有邻居的时候discount，但是最好加上
        但是在工作中，其他地方可能也会用到这个Node，此时的node有可能是没有前后邻居的
        unit test的时候，要充分考虑各种corner case
         */
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        prev = null;
        next = null;
    }
}

// Your LRUCache object will be instantiated and called as such:
// LRUCache obj = new LRUCache(capacity);
// int param_1 = obj.get(key);
// obj.put(key,value);
//leetcode submit region end(Prohibit modification and deletion)

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

}