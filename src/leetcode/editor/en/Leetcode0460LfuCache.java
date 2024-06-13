//Design and implement a data structure for Least Frequently Used (LFU) cache. I
//t should support the following operations: get and put. 
//
// get(key) - Get the value (will always be positive) of the key if the key exis
//ts in the cache, otherwise return -1. 
//put(key, value) - Set or insert the value if the key is not already present. W
//hen the cache reaches its capacity, it should invalidate the least frequently us
//ed item before inserting a new item. For the purpose of this problem, when there
// is a tie (i.e., two or more keys that have the same frequency), the least recen
//tly used key would be evicted. 
//
// Note that the number of times an item is used is the number of calls to the graph
//et and put functions for that item since it was inserted. This number is set to 
//zero when the item is removed. 
//
// 
//
// Follow up: 
//Could you do both operations in O(1) time complexity? 
//
// 
//
// Example: 
//
// 
//LFUCache cache = new LFUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.get(3);       // returns 3.
//cache.put(4, 4);    // evicts key 1.
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4
// 
//
// 
// Related Topics Design 
// 👍 1377 👎 124

package leetcode.editor.en;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// 2020-07-24 13:46:17
// Jesse Yang
public class Leetcode0460LfuCache{
    // Java: lfu-cache
    public static void main(String[] args) {
        int capacity = 3;
        LFUCache2 lfuCache = new Leetcode0460LfuCache().new LFUCache2(capacity);
        // TO TEST
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.put(3, 3);
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(4));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(1));
        lfuCache.put(3, 3);
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*class LFUCache {

    public LFUCache(int capacity) {

    }

    public int get(int key) {
        return 0;
    }

    public void put(int key, int value) {

    }
}*/
class LFUCache {
    
    private final Map<Integer, Integer> keyToVal;
    private final Map<Integer, Integer> countMap; // value -count
    private final Map<Integer, Set<Integer>> lists; // value- freq LinkedHashSet
    private int capacity;
    private int min;
    
    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        countMap = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
        this.capacity = capacity;
        min = 0;
    }
    
    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        int count = countMap.get(key);
        countMap.put(key, count + 1); //increase freq
        //delete old freq
        lists.get(count).remove(key);
        if (count == min && lists.get(count).isEmpty()) {
            min++;
        }
        lists.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);
        return keyToVal.get(key);
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);//modify value
            get(key);
            return;
        }
        
        if (keyToVal.size() >= capacity) {
            int evit = lists.get(min).iterator().next(); //
            lists.get(min).remove(evit);
            keyToVal.remove(evit);
        }
        keyToVal.put(key, value);
        countMap.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
    
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 1 */


// Solution 1: double LinkedList, T(n) = O(1), S(n) = O(n)
// 16 ms,击败了88.20% 的Java用户, 50 MB,击败了44.02% 的Java用户
class LFUCache1 {
    
    private final Map<Integer, Node> keyToNode; // key-node pair
    private final Map<Integer, NodeList> freqMap; //freq To doubleLinkedList Of Node map
    private final int capacity;
    private int minFreq; // 追踪频率最小的那个Node List，空间不够放的时候，从这个List里面删除Node
    private int size;
    
    public LFUCache1(int capacity) {
        keyToNode = new HashMap<>();
        freqMap = new HashMap<>();
        this.capacity = capacity;
        minFreq = 0;
        this.size = 0;
    }
    
    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }
        
        Node node = keyToNode.get(key);
        
        int prevFreq = node.freq;
        deleteNode(node, prevFreq);
        
        node.freq++;
        int curFreq = node.freq;
        
        // add current Node to current node list and may update the freqMap
        addNode(node, curFreq);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        // 如果这个Node已经有了，更新频率和freMap就行了
        if (keyToNode.get(key) != null) {
            keyToNode.get(key).val = value;
            get(key);
            return;
        }
        // 如果这个key还不存在的话，删掉频率最小最早用的那一个，然后把点加进去
        if (size == capacity) {
            deleteTail();
            size--;
        }
        minFreq = 1;
        Node newNode = new Node(key, value);
        keyToNode.put(key, newNode);
        
        NodeList curList = freqMap.computeIfAbsent(1, k -> new NodeList());
        curList.addHead(newNode);
        this.size++;
    }
    
    /**
     * delete node from the preFreq node List
     */
    private void deleteNode(Node node, int prevFreq) {
        NodeList prevList = freqMap.get(prevFreq);
        prevList.remove(node);
        if (prevList.isEmpty()) {
            freqMap.remove(prevFreq);
            if (prevFreq == minFreq) {
                minFreq++;
            }
        }
    }
    
    /**
     * add Node to the curFreq node list
     */
    private void addNode(Node node, int curFreq) {
        if (!freqMap.containsKey(curFreq)) {
            freqMap.put(curFreq, new NodeList());
        }
        NodeList curList = freqMap.get(curFreq);
        curList.addHead(node);
    }
    
    /**
     * delete last node in the least used node list
     */
    private void deleteTail() {
        NodeList prevList = freqMap.get(minFreq);
        Node deleted = prevList.removeTail();
        keyToNode.remove(deleted.key);
        if (prevList.isEmpty()) {
            freqMap.remove(minFreq);
        }
    }
    
    class Node {
        
        private final int key;
        private int val;
        private int freq;
        Node prev;
        Node next;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
            this.prev = null;
            this.next = null;
        }
    }
    
    class NodeList {
        
        private final Node head;
        private final Node tail;
        private int size;
        
        public NodeList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }
        
        public void addHead(Node node) {
            Node following = head.next;
            head.next = node;
            node.next = following;
            node.prev = head;
            following.prev = node;
            size++;
        }
        
        public void remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
            size--;
        }
        
        public Node removeTail() {
            if (size == 0) {
                throw new IllegalArgumentException("size is 0, can not be deleted");
            }
            Node cur = tail.prev;
            remove(tail.prev);
            return cur;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
    }
}

// Solution 2: 3 HashSet and LinkedHashSet, T(n) = O(1), S(n) = O(n)
// 23 ms,击败了44.95% 的Java用户, 53.3 MB,击败了30.43% 的Java用户
class LFUCache2 {
    
    private final Map<Integer, Integer> keyToVal;
    private final Map<Integer, Integer> countMap; // value -count
    private final Map<Integer, Set<Integer>> lists; // value- freq LinkedHashSet
    private int capacity;
    private int min;
    
    public LFUCache2(int capacity) {
        keyToVal = new HashMap<>();
        countMap = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
        this.capacity = capacity;
        min = 0;
    }
    
    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        int count = countMap.get(key);
        countMap.put(key, count + 1); //increase freq
        //delete old freq
        lists.get(count).remove(key);
        if (count == min && lists.get(count).isEmpty()) {
            min++;
        }
        lists.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);
        return keyToVal.get(key);
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);//modify value
            get(key);
            return;
        }
        
        if (keyToVal.size() >= capacity) {
            int evit = lists.get(min).iterator().next(); //
            lists.get(min).remove(evit);
            keyToVal.remove(evit);
        }
        keyToVal.put(key, value);
        countMap.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
    
}
}