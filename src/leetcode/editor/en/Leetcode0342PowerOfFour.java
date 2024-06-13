//Given an integer (signed 32 bits), write a function to check whether it is a p
//ower of 4. 
//
// Example 1: 
//
// 
//Input: 16
//Output: true
// 
//
// 
// Example 2: 
//
// 
//Input: 5
//Output: false 
// 
//
// Follow up: Could you solve it without loops/recursion? Related Topics Bit Man
//ipulation 
// 👍 612 👎 228

package leetcode.editor.en;

// 2020-08-04 12:10:46
// Jesse Yang
public class Leetcode0342PowerOfFour{
    // Java: power-of-four
    public static void main(String[] args) {
        Solution sol = new Leetcode0342PowerOfFour().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean isPowerOfFour(int num) {
        if (num <= 0) {
            return false;
        } else if (num == 1) {
            return true;
        } else if (num % 4 != 0) {
            return false;
        }
        return isPowerOfFour(num / 4);
        
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}