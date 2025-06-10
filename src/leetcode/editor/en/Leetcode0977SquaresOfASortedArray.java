/**
Given an integer array nums sorted in non-decreasing order, return an array of 
the squares of each number sorted in non-decreasing order. 

 
 Example 1: 

 
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
 

 Example 2: 

 
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁴ <= nums[i] <= 10⁴ 
 nums is sorted in non-decreasing order. 
 

 
Follow up: Squaring each element and sorting the new array is very trivial, 
could you find an 
O(n) solution using a different approach?

 Related Topics Array Two Pointers Sorting 👍 9676 👎 252

*/
package leetcode.editor.en;

// 2025-05-27 22:50:19
// Jesse Yang
public class Leetcode0977SquaresOfASortedArray{
    // Java: squares-of-a-sorted-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0977SquaresOfASortedArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
since the nums array is ascending, so the square of the num is descending then ascending
    or only descending or only ascending
    
ideas: so, the largest square of number is either left most or right most, we can use the 2
pointers to traverse the nums
step 1: pointer left = 0 and right = len - 1, move from opposite directions
 */
class Solution {
    public int[] sortedSquares(int[] nums) {
        // corner case
        int len = nums.length;
        int left = 0;
        int right = nums.length - 1;
        
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            if (leftSquare < rightSquare) {
                right--;
                res[len - 1 - i] = rightSquare;
            } else { // leftSquare >= rightSquare
                left++;
                res[len - 1 - i] = leftSquare;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}