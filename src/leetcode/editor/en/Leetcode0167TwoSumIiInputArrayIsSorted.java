//Given an array of integers that is already sorted in ascending order, find two
// numbers such that they add up to a specific target number. 
//
// The function twoSum should return indices of the two numbers such that they a
//dd up to the target, where index1 must be less than index2. 
//
// Note: 
//
// 
// Your returned answers (both index1 and index2) are not zero-based. 
// You may assume that each input would have exactly one solution and you may no
//t use the same element twice. 
// 
//
// Example: 
//
// 
//Input: numbers = [2,7,11,15], target = 9
//Output: [1,2]
//Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2. 
// Related Topics Array Two Pointers Binary Search 
// 👍 1702 👎 614

package leetcode.editor.en;

// 2020-07-26 13:51:44
// Zeshi Yang
public class Leetcode0167TwoSumIiInputArrayIsSorted{
    // Java: two-sum-ii-input-array-is-sorted
    public static void main(String[] args) {
        Solution sol = new Leetcode0167TwoSumIiInputArrayIsSorted().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int[] twoSum(int[] numbers, int target) {
        // corner case
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        
        // general case
        int i = 0;
        int j = numbers.length - 1;
        int sum = numbers[i] + numbers[j];
        while (i < j) {
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
                sum = numbers[i] + numbers[j];
            } else {
                j--;
                sum = numbers[i] + numbers[j];
            }
        }
        return null;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}