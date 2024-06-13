//Given a sorted array and a target value, return the index if the target is fou
//nd. If not, return the index where it would be if it were inserted in order. 
//
// You may assume no duplicates in the array. 
//
// Example 1: 
//
// 
//Input: [1,3,5,6], 5
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: [1,3,5,6], 2
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: [1,3,5,6], 7
//Output: 4
// 
//
// Example 4: 
//
// 
//Input: [1,3,5,6], 0
//Output: 0
// 
// Related Topics Array Binary Search 
// 👍 2491 👎 258

package leetcode.editor.en;

// 2020-08-04 11:19:25
// Jesse Yang
public class Leetcode0035SearchInsertPosition{
    // Java: search-insert-position
    public static void main(String[] args) {
        Solution sol = new Leetcode0035SearchInsertPosition().new Solution();
        // TO TEST

        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int searchInsert(int[] nums, int target) {
        //corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}