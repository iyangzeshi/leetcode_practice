//Given a non-empty array of integers, return the k most frequent elements. 
//
// Example 1: 
//
// 
//Input: nums = [1,1,1,2,2,3], k = 2
//Output: [1,2]
// 
//
// 
// Example 2: 
//
// 
//Input: nums = [1], k = 1
//Output: [1] 
// 
//
// Note: 
//
// 
// You may assume k is always valid, 1 ≤ k ≤ number of unique elements. 
// Your algorithm's time complexity must be better than O(n log n), where n is t
//he array's size. 
// It's guaranteed that the answer is unique, in other words the set of the top 
//k frequent elements is unique. 
// You can return the answer in any order. 
// 
// Related Topics Hash Table Heap 
// 👍 3337 👎 217

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
// 2020-07-26 11:59:33
// Zeshi Yang
public class Leetcode0347TopKFrequentElements{
    // Java: top-k-frequent-elements
    public static void main(String[] args) {
        Solution sol = new Leetcode0347TopKFrequentElements().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        // corner cases
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int i : nums) {
            if (!count.containsKey(i)) {
                count.put(i, 1);
            } else {
                count.put(i, count.get(i) + 1);
            }
        }
        
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                Comparator.comparingInt(count::get)); // min heap
        
        for (int i : count.keySet()) {
            if (heap.size() < k) {
                heap.add(i);
            } else if (count.get(i) > count.get(heap.peek())) {
                heap.poll();
                heap.add(i);
            } else {
                continue;
            }
        }
        
        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }
        Collections.reverse(result);

        /*
         while (heap.isEmpty() == false) {
         result.add(0, heap.poll());新来的数字加在头上也可以
         }
         */
        return result;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}