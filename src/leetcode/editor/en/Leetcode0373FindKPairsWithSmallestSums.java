//You are given two integer arrays nums1 and nums2 sorted in ascending order and
// an integer k. 
//
// Define a pair (u,v) which consists of one element from the first array and on
//e element from the second array. 
//
// Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums. 
//
// Example 1: 
//
// 
//Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//Output: [[1,2],[1,4],[1,6]] 
//Explanation: The first 3 pairs are returned from the sequence: 
//             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6] 
//
// Example 2: 
//
// 
//Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//Output: [1,1],[1,1]
//Explanation: The first 2 pairs are returned from the sequence: 
//             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3] 
//
// Example 3: 
//
// 
//Input: nums1 = [1,2], nums2 = [3], k = 3
//Output: [1,3],[2,3]
//Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
// 
// Related Topics Heap 
// 👍 1394 👎 101

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
// 2020-07-27 12:51:43
// Zeshi Yang
public class Leetcode0373FindKPairsWithSmallestSums{
    // Java: find-k-pairs-with-smallest-sums
    public static void main(String[] args) {
        Solution sol = new Leetcode0373FindKPairsWithSmallestSums().new Solution();
        // TO TEST
        int[] nums1 = {1,2,4,5,6};
        int[] nums2 = {3,5,7,9};
        int k = 3;
        List<List<Integer>> res = sol.kSmallestPairs(nums1, nums2, k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    // time = O(klog(min(m + n, k))), space = O(min(m + n, k))
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return res;
        }
        
        int len1 = nums1.length, len2 = nums2.length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(
                (c1, c2) -> nums1[c1.i] + nums2[c1.j] - (nums1[c2.i] + nums2[c2.j]));
        HashSet<Cell> visited = new HashSet<>();
        minHeap.offer(new Cell(0, 0));
        
        while (k-- > 0) {
            if (minHeap.isEmpty()) {
                break;
            }
            Cell cur = minHeap.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[cur.i]);
            list.add(nums2[cur.j]);
            res.add(list);
            if (cur.i < len1 - 1) {
                Cell rCell = new Cell(cur.i + 1, cur.j);
                if (visited.add(rCell)) {
                    minHeap.offer(rCell);
                }
            }
            if (cur.j < len2 - 1) {
                Cell dCell = new Cell(cur.i, cur.j + 1);
                if (visited.add(dCell)) {
                    minHeap.offer(dCell);
                }
            }
        }
        return res;
    }
    
    private class Cell {
        
        private int i;
        private int j;
        
        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
        @Override
        public int hashCode() {
            return i + 31 * j;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (o instanceof Cell) {
                Cell that = (Cell) o;
                return this.i == that.i && this.j == that.j;
            } else {
                return false;
            }
        }
        
    }
    
}

//leetcode submit region end(Prohibit modification and deletion)
/*
面试的时候，看情况，时间多的话，用Solution 2，容易讲清楚
时间少的话，用Solution 1，code思路一样，solution 2 wrap了一下
 */
// Solution 1: heap 存index = index1 * len2 + index2
class Solution1 {
    
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new LinkedList<>();
        // corner case
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return res;
        }
        // general case
        int len1 = nums1.length;
        int len2 = nums2.length;
        // heap to store the index of the sum from nums1 and nums2;, sum = num1 * len2 + num2
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((sum1, sum2) ->
                (nums1[sum1 / len2] + nums2[sum1 % len2]) - (nums1[sum2 / len2] + nums2[sum2
                        % len2]));
        minHeap.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (k-- > 0 && !minHeap.isEmpty()) {
            int num = minHeap.poll();
            int index1 = num / len2;
            int index2 = num % len2;
            List<Integer> list = new LinkedList<>();
            list.add(nums1[index1]);
            list.add(nums2[index2]);
            res.add(list);
            int neighbor = (index1 + 1) * len2 + index2;
            if (index1 + 1 < len1 && !visited.contains(neighbor)) {
                minHeap.offer(neighbor);
                visited.add(neighbor);
            }
            neighbor = index1 * len2 + index2 + 1;
            if (index2 + 1 < len2 && !visited.contains(neighbor)) {
                minHeap.offer(neighbor);
                visited.add(neighbor);
            }
        }
        return res;
    }
    
}

// Solution 2: heap存 Cell
class Solution2 {
    
    // time = O(klog(min(m + n, k))), space = O(min(m + n, k))
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return res;
        }
        
        int len1 = nums1.length, len2 = nums2.length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(
                (c1, c2) -> nums1[c1.i] + nums2[c1.j] - (nums1[c2.i] + nums2[c2.j]));
        HashSet<Cell> visited = new HashSet<>();
        minHeap.offer(new Cell(0, 0));
        
        while (k-- > 0) {
            if (minHeap.isEmpty()) {
                break;
            }
            Cell cur = minHeap.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[cur.i]);
            list.add(nums2[cur.j]);
            res.add(list);
            if (cur.i < len1 - 1) {
                Cell rCell = new Cell(cur.i + 1, cur.j);
                if (visited.add(rCell)) {
                    minHeap.offer(rCell);
                }
            }
            if (cur.j < len2 - 1) {
                Cell dCell = new Cell(cur.i, cur.j + 1);
                if (visited.add(dCell)) {
                    minHeap.offer(dCell);
                }
            }
        }
        return res;
    }
    
    private class Cell {
        
        private int i;
        private int j;
        
        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
        @Override
        public int hashCode() {
            return i + 31 * j;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (o instanceof Cell) {
                Cell that = (Cell) o;
                return this.i == that.i && this.j == that.j;
            } else {
                return false;
            }
        }
        
    }
    
}


}