
//Given an array A of positive integers, call a (contiguous, not necessarily dis
//tinct) subarray of A good if the number of different integers in that subarray i
//s exactly K. 
//
// (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.) 
//
// Return the number of good subarrays of A. 
//
// 
//
// Example 1: 
//
// 
//Input: A = [1,2,1,2,3], K = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1],
// [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
// 
//
// Example 2: 
//
// 
//Input: A = [1,2,1,3,4], K = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2
//,1,3], [1,3,4].
// 
//
// 
//
// Note: 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length 
// 1 <= K <= A.length 
// Related Topics Hash Table Two Pointers Sliding Window 
// 👍 1512 👎 26

package leetcode.editor.en;

// 2021-02-01 15:06:54
// Zeshi Yang
public class Leetcode0992SubarraysWithKDifferentIntegers{
    // Java: subarrays-with-k-different-integers
    public static void main(String[] args) {
        Solution sol = new Leetcode0992SubarraysWithKDifferentIntegers().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}