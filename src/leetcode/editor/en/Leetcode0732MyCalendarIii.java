//A k-booking happens when k events have some non-empty intersection (i.e., ther
//e is some time that is common to all k events.) 
//
// You are given some events [start, end), after each given event, return an int
//eger k representing the maximum k-booking between all the previous events. 
//
// Implement the MyCalendarThree class: 
//
// 
// MyCalendarThree() Initializes the object. 
// int book(int start, int end) Returns an integer k representing the largest in
//teger such that there exists a k-booking in the calendar. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
//[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
//Output
//[null, 1, 1, 2, 3, 3, 3]
//
//Explanation
//MyCalendarThree myCalendarThree = new MyCalendarThree();
//myCalendarThree.book(10, 20); // return 1, The first event can be booked and i
//s disjoint, so the maximum k-booking is a 1-booking.
//myCalendarThree.book(50, 60); // return 1, The second event can be booked and 
//is disjoint, so the maximum k-booking is a 1-booking.
//myCalendarThree.book(10, 40); // return 2, The third event [10, 40) intersects
// the first event, and the maximum k-booking is a 2-booking.
//myCalendarThree.book(5, 15); // return 3, The remaining events cause the maxim
//um K-booking to be only a 3-booking.
//myCalendarThree.book(5, 10); // return 3
//myCalendarThree.book(25, 55); // return 3
// 
//
// 
// Constraints: 
//
// 
// 0 <= start < end <= 109 
// At most 400 calls will be made to book. 
// 
// Related Topics Segment Tree Ordered Map 
// 👍 424 👎 104

package leetcode.editor.en;

import java.util.TreeMap;

// 2021-01-09 13:14:05
// Zeshi Yang
public class Leetcode0732MyCalendarIii{
    // Java: my-calendar-iii
    public static void main(String[] args) {
        MyCalendarThree calendar = new Leetcode0732MyCalendarIii().new MyCalendarThree();
        // TO TEST
        System.out.println(calendar.book(10, 20));
        System.out.println(calendar.book(50, 60));
        System.out.println(calendar.book(10, 40));
        System.out.println(calendar.book(5, 15));
        System.out.println(calendar.book(5, 10));
        System.out.println(calendar.book(25, 55));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MyCalendarThree {
    
    SegmentTree segmentTree;
    
    public MyCalendarThree() {
        segmentTree = new SegmentTree(0, 1000000000);
    }
    
    public int book(int start, int end) {
        segmentTree.add(start, end, 1);
        return segmentTree.getMax();
    }
    
    class SegmentTree {
        
        TreeNode root;
        
        public SegmentTree(int left, int right) {
            root = new TreeNode(left, right);
        }
        
        public void add(int start, int end, int val) {
           TreeNode event = new TreeNode(start, end);
            add(root, event, val);
        }
        
        private void add(TreeNode cur, TreeNode event, int val) {
            if (cur == null) {
                return;
            }
            /*
              If current node's range lies completely in update query range.
             */
            if (cur.inside(event)) {
                cur.booked += val;
                cur.savedRes += val;
            }
            /*
              If current node's range overlaps with update range, follow the same approach as
              above simple update.
             */
            if (cur.intersect(event)) {
                // Recur for left and right children.
                int mid = (cur.start + cur.end) / 2;
                if (cur.left == null) {
                    cur.left = new TreeNode(cur.start, mid);
                }
                add(cur.left, event, val);
                if (cur.right == null) {
                    cur.right = new TreeNode(mid, cur.end);
                }
                add(cur.right, event, val);
                // Update current node using results of left and right calls.
                cur.savedRes = Math.max(cur.left.savedRes, cur.right.savedRes) + cur.booked;
            }
        }
        
        public int getMax() {
            return root.savedRes;
        }
        
        /**
         * find maximum for nums[i] (start <= i <= end) is not required. so i did not implement
         * it.
         */
        public int get(int start, int right) {
            return 0; // TODO
        }
    
    }
    
    class TreeNode {
        
        int start;
        int end;
        TreeNode left = null;
        TreeNode right = null;
        /**
         * How much number is added to this interval(node)
         */
        int booked = 0;
        /**
         * The maximum number in this interval(node).
         */
        int savedRes = 0;
        
        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public boolean inside(TreeNode event) {
            return event.start <= start && end <= event.end;
        }
        
        public boolean intersect(TreeNode event) {
            return !inside(event) && end > event.start && event.end > start;
        }
        
    }
    
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)
// 讲解，没有java code: https://zxi.mytechroad.com/blog/geometry/732-my-calendar-iii/

// Solution 1: count boundaries
// T(n) = O(n^2), S(n) = O(n)
// 126 ms,击败了27.33% 的Java用户, 39.5 MB,击败了82.89% 的Java用户
class MyCalendarThree1 {
    
    TreeMap<Integer, Integer> delta;
    
    public MyCalendarThree1() {
        delta = new TreeMap<>();
    }
    
    public int book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);
        
        int active = 0;
        int res = 0;
        for (int d : delta.values()) {
            active += d;
            if (active > res) {
                res = active;
            }
        }
        return res;
    }
    
}

// Solution 2: increment the intersection regions
// T(n) = O(n^2), S(n) = O(n)
// 41 ms,击败了85.56% 的Java用户, 40.1 MB,,击败了17.11% 的Java用户
class MyCalendarThree2 {
    
    private TreeMap<Integer, Integer> map; // point(start, end) to height
    private int maxHeight;
    
    public MyCalendarThree2() {
        map = new TreeMap<>();
        map.put(0, 0);
        maxHeight = 0;
    }
    
    public int book(int start, int end) {
        // pre-processing, update map-start
        int prevPoint = map.floorKey(start);
        int height = map.get(prevPoint) + 1;
        maxHeight = Math.max(maxHeight, height);
        map.put(start, map.get(prevPoint) + 1);
        
        Integer cur = map.higherKey(start);
        
        while (cur != null && cur < end) {
            height = map.getOrDefault(cur, 0) + 1;
            maxHeight = Math.max(maxHeight, height);
            map.put(cur, height);
            cur = map.higherKey(cur);
        }
    
        // post processing, update map -end
        if (cur == null || cur > end) { // cur > end
            map.put(end, map.lowerEntry(end).getValue() - 1);
        }
        mergeSameHeight(start, end, cur);
        return maxHeight;
    }
    
    private void mergeSameHeight(int start, int end, Integer cur) {
        // if height at start and height at start's previous point is same, merge it
        if (start != 0 && map.lowerEntry(start).getValue().equals(map.get(start))) {
            map.remove(start);
        }
        
        // if height at end and height at end' next point is same, merge it
        if (cur != null && cur == end && map.get(end).equals(map.lowerEntry(end).getValue())) {
            map.remove(end);
        }
    }
    
}


// Solution 3: segment tree
class MyCalendarThree3 {
    
    TreeNode root;
    
    public MyCalendarThree3() {
        root = new TreeNode(0);
    }
    
    public int book(int start, int end) {
        return build(start, end - 1, root, 0, 1000000000);
    }
    
    //left, right代表范围
    private int build(int start, int end, TreeNode node, int left, int right) {
        if (start == left && end == right && node.left == null && node.right == null) {//后面的暂时不用
            node.val++;
            return node.val;
        }
        if (node.left == null) {
            node.left = new TreeNode(node.val);
            node.right = new TreeNode(node.val);
        }
        int m = (left + right) / 2;
        if (m >= end) {
            node.val = Math.max(node.val, build(start, end, node.left, left, m));
        } else if (m < start) {
            node.val = Math.max(node.val, build(start, end, node.right, m + 1, right));
        } else {
            int v = Math.max(build(start, m, node.left, left, m), build(m + 1, end, node.right, m + 1, right));
            node.val = Math.max(node.val, v);
        }
        return node.val;
    }
    
    class TreeNode {
        
        int val;//区间最大
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
        
    }
    
}
}