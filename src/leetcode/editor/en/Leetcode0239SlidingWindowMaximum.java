//Given an array nums, there is a sliding window of size k which is moving from 
//the very left of the array to the very right. You can only see the k numbers in 
//the window. Each time the sliding window moves right by one position. Return the
// max sliding window. 
//
// Follow up: 
//Could you solve it in linear time? 
//
// Example: 
//
// 
//Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
//Output: [3,3,5,5,6,7] 
//Explanation: 
//
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics Heap Sliding Window 
// 👍 3526 👎 170

package leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;

// 2020-07-26 14:05:02
// Zeshi Yang
public class Leetcode0239SlidingWindowMaximum{
    // Java: sliding-window-maximum
    public static void main(String[] args) {
        Solution sol = new Leetcode0239SlidingWindowMaximum().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        // corner case
        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        
        // general case
        int[] left = new int[len];
        left[0] = nums[0];
        int[] right = new int[len];
        right[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            // to get left array
            if (i % k == 0) {
                left[i] = nums[i];
            }
            else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
            
            // to get right array
            int j = len - 1 - i;
            if ((j + 1) % k == 0) {
                right[j] = nums[j];
            }
            else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }
        
        int[] res = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++) {
            res[i] = Math.max(left[i + k - 1], right[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1:  deque里面存非递增序列, T(n) = O(n), S(n) = O(k)
// 29 ms,击败了61.48% 的Java用户, 54 MB,击败了48.61% 的Java用户
//
/*
直觉
如何优化时间复杂度呢？首先想到的是使用堆，因为在最大堆中 heap[0] 永远是最大的元素。
在大小为 k 的堆中插入一个元素消耗 log(k) 时间，因此算法的时间复杂度为 O(n * log(k))。
能否得到只要 O(size) 的算法？
我们可以使用双向队列，该数据结构可以从两端以常数时间压入/弹出元素。
存储双向队列的索引比存储元素更方便，因为两者都能在数组解析中使用。
算法
处理前 k 个元素，初始化双向队列。
遍历整个数组。在每一步 :
清理双向队列 :
  - 只保留当前滑动窗口中有的元素的索引。
  - 移除比当前元素小的所有元素，它们不可能是最大的。
将当前元素添加到双向队列中。
将 deque[0] 添加到输出中。
返回输出数组。
*/
class Solution1 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // corner case
        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        // general case
        Deque<Integer> deque = new ArrayDeque<>(); // to store the value instead of index
        int[] res = new int [len - k + 1];
        
        // initialization
        for(int i = 0; i < k; i++) {
            if (deque.isEmpty()) {
                deque.offer(nums[i]);
                continue;
            }

            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);
        }
        res[0] = deque.peekFirst();

        for (int i = k; i < len; i++) {
            if (nums[i - k] == deque.peekFirst()) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
}

// Solution 2: dynamic programming, T(n) = O(n), S(n) = O(n)
// 9 ms,击败了98.05% 的Java用户, 53.3 MB,击败了71.71% 的Java用户
/*
public int[] maxSlidingWindow(int[] nums, int k)
将array风格，每k个一组，最后一组可以少于k个。
建立left数组和right数组
left[i]表示从i所在的组的头一个位置到当前i位置的最大值，
right[i]同理
窗口中的最大值为max(right[i], left[j])
 */
class Solution2 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // corner case
        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        // general case
        int[] left = new int[len];
        left[0] = nums[0];
        int[] right = new int[len];
        right[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            // to get left array
            if (i % k == 0) {
                left[i] = nums[i];
            }
            else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            // to get right array
            int j = len - 1 - i;
            if ((j + 1) % k == 0) {
                right[j] = nums[j];
            }
            else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        int[] res = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++) {
            res[i] = Math.max(left[i + k - 1], right[i]);
        }
        return res;
    }
}
}