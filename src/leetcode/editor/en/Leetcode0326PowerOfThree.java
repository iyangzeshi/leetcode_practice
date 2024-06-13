//Given an integer, write a function to determine if it is a power of three. 
//
// Example 1: 
//
// 
//Input: 27
//Output: true
// 
//
// Example 2: 
//
// 
//Input: 0
//Output: false 
//
// Example 3: 
//
// 
//Input: 9
//Output: true 
//
// Example 4: 
//
// 
//Input: 45
//Output: false 
//
// Follow up: 
//Could you do it without using any loop / recursion? Related Topics Math 
// 👍 533 👎 1481

package leetcode.editor.en;

// 2020-08-04 12:09:46
// Jesse Yang
public class Leetcode0326PowerOfThree{
    // Java: power-of-three
    public static void main(String[] args) {
        Solution sol = new Leetcode0326PowerOfThree().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        } else if (n == 1) {
            return true;
        } else if (n % 3 != 0) {
            return false;
        }
        return isPowerOfThree(n / 3);
        
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}