/**
Given an array arr of positive integers sorted in a strictly increasing order, 
and an integer k. 

 Return the kᵗʰ positive integer that is missing from this array. 

 
 Example 1: 

 
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5ᵗʰ
 missing positive integer is 9.
 

 Example 2: 

 
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2ⁿᵈ missing 
positive integer is 6.
 

 
 Constraints: 

 
 1 <= arr.length <= 1000 
 1 <= arr[i] <= 1000 
 1 <= k <= 1000 
 arr[i] < arr[j] for 1 <= i < j <= arr.length 
 

 
 Follow up: 

 Could you solve this problem in less than O(n) complexity? 

 Related Topics Array Binary Search 👍 6895 👎 483

*/
package leetcode.editor.en;

// 2024-11-22 14:17:40
// Jesse Yang
public class Leetcode1539KthMissingPositiveNumber{
    // Java: kth-missing-positive-number
    public static void main(String[] args) {
        Solution sol = new Leetcode1539KthMissingPositiveNumber().new Solution();
        // TO TEST
        int[] nums = {1,3,5,7};
        int k = 2;
        System.out.println(sol.findKthPositive(nums, k));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthPositive(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        if (k < nums[0]) {
            return k;
        }
        int totalMissing = nums[len - 1]- len;
        /*if (k > totalMissing) {
            throw new IllegalArgumentException("not enough missing numbers");
        }*/
        if (k > totalMissing) {
            return nums[len - 1] + k - totalMissing;
        }
        int left = 0;
        int right = len - 1;
        k -= nums[0] - 1;
        while (left  + 1 < right) {
            int mid = left + (right - left) / 2;
            int diff = countMissing(nums, left, mid);
            if (diff < k) {
                left = mid;
                k -= diff;
            } else {
                right = mid;
            }
        }
        
        return nums[left] + k;
    }
    
    
    private int countMissing(int[] nums, int left, int right) { // 2, 4
        return nums[right] - nums[left] - (right - left); // 1
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}