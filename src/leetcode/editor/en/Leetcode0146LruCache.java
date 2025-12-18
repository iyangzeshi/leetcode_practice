/**
Design a data structure that follows the constraints of a Least Recently Used (
LRU) cache. 

 Implement the LRUCache class: 

 
 LRUCache(int capacity) Initialize the LRU cache with positive size capacity. 
 int get(int key) Return the value of the key if the key exists, otherwise 
return -1. 
 void put(int key, int value) Update the value of the key if the key exists. 
Otherwise, add the key-value pair to the cache. If the number of keys exceeds the 
capacity from this operation, evict the least recently used key. 
 

 The functions get and put must each run in O(1) average time complexity. 

 
 Example 1: 

 
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 

 
 Constraints: 

 
 1 <= capacity <= 3000 
 0 <= key <= 10⁴ 
 0 <= value <= 10⁵ 
 At most 2 * 10⁵ calls will be made to get and put. 
 

 Related Topics Hash Table Linked List Design Doubly-Linked List 👍 22645 👎 120
1

*/
package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2020-09-09 17:16:07
// Jesse Yang
public class Leetcode0146LruCache {
    // Java: lru-cache
    public static void main(String[] args) {
        int capacity = 2;
        LRUCache cache = new Leetcode0146LruCache().new LRUCache(capacity);
        // TO TEST
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        cache.get(2);
        cache.get(1);
        cache.get(3);
        cache.get(4);
        cache.put(4, 4);
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution: HashMap + customized Double Linked List, 思路更清楚版本
// T(n) = O(1), S(n) = O(n)
/*

推理过程，
1. 因为是Cache，需要用O(1)时间拿到数据，所以必须用HashMap
2. 因为要evict最老的数据，数据需要保持相信，要用到相信数据结构，而且删除点的时间复杂度要小，所以用Double LinkedList

using the double LinkedList and HashMap to implement the function
HashMap {key: int; value: Node}
Node {
    int key;
    int val;
}

head <-> node1 <-> node2 <-> ... <-> node5 <-> tail
最新的node放在head
1.
step 1: get method: HashMap查询key是否存在
    case 1.1 : exist
        (1)存在则将节点移动到LinkedList头部（因为它最近被用到了）；
        (2)返回value
    case 1.2 not exist 返回-1 // 没查到
    
step 2. put method: HashMap查询key是否存在
    case 2.1 存在
            (1) 更新节点值；
            (2) 并且将节点移动到头部（被用到）；
    case 2.2 不存在
        加入最新的key value pair to head of Double linked list和 Map
        缓存空间如果不够用
            LRU从Double LinkedList和Map中淘汰最老的key value pair
*/

class Node {
    
    /* these fields, to do private, add getter & setter */
    final int key;
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

class LRUCache {
    
    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head; // dummy head
    private final Node tail; // dummy tail
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        node.disconnectNeighbor();
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        Node node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = value;
            node.disconnectNeighbor();
        } else {
            node = new Node(key, value);
            map.put(key, node);
            if (map.size() > capacity) { // remove last one to get the place for new node
                Node lastNode = tail.prev;
                lastNode.disconnectNeighbor();
                map.remove(lastNode.key);
            }
        }
        addToHead(node);
    }
    
    private void addToHead(Node node) {
        // move node to the head place
        Node following = head.next;
        head.next = node;
        node.next = following;
        node.prev = head;
        following.prev = node;
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}