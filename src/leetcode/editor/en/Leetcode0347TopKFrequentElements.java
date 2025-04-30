/*
Given a non-empty array of integers, return the k most frequent elements.

 Example 1:

 
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
 

 
 Example 2:

 
Input: nums = [1], k = 1
Output: [1]
 

 Note:

 
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 It's guaranteed that the answer is unique, in other words the set of the top
k frequent elements is unique.
 You can return the answer in any order.
 
 Related Topics Hash Table Heap
 👍 3337 👎 217
*/

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
// 2020-07-26 11:59:33
// Jesse Yang
public class Leetcode0347TopKFrequentElements{
    // Java: top-k-frequent-elements
    public static void main(String[] args) {
        Solution sol = new Leetcode0347TopKFrequentElements().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
step 1: create a hashMap (countMap) to calculate the number and frequency,
T(n, k) = O(n)， S(n, k) = O(n)
step 2: create a minHeap to traverse the countMap to filter top k frequent elements,
T(n, k) = O(n log(k)), S(n, k) = O(k)

complexity analysis: T(n, k) = O(n log(k)), S(n, k) = O(n)
 */
class Solution {
    
    public int[] topKFrequent(int[] nums, int k) {
        // corner cases
        if (nums == null || nums.length == 0 || k <= 0) {
            return null;
        }
        
        // countMap the frequency of the number
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i : nums) {
            if (!countMap.containsKey(i)) {
                countMap.put(i, 1);
            } else {
                countMap.put(i, countMap.get(i) + 1);
            }
        }
        
        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>((o1, o2) -> (countMap.get(o1) - countMap.get(o2))); // min minHeap
        for (int i : countMap.keySet()) {
            if (minHeap.size() < k) {
                minHeap.add(i);
            } else if (countMap.get(i) > countMap.get(minHeap.peek())) {
                minHeap.poll();
                minHeap.offer(i);
            }
        }
        
        int[] top = new int[k];
        for (int i = 0; i < k; i++) {
            top[k - 1 - i] = minHeap.poll();
        }
        
        return top;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}