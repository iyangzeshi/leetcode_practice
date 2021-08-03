//Implement pow(x, n), which calculates x raised to the power n (xn). 
//
// Example 1: 
//
// 
//Input: 2.00000, 10
//Output: 1024.00000
// 
//
// Example 2: 
//
// 
//Input: 2.10000, 3
//Output: 9.26100
// 
//
// Example 3: 
//
// 
//Input: 2.00000, -2
//Output: 0.25000
//Explanation: 2-2 = 1/22 = 1/4 = 0.25
// 
//
// Note: 
//
// 
// -100.0 < x < 100.0 
// n is a 32-bit signed integer, within the range [−231, 231 − 1] 
// 
// Related Topics Math Binary Search 
// 👍 1623 👎 3154

package leetcode.editor.en;

// 2020-08-04 11:23:07
// Zeshi Yang
public class Leetcode0050PowxN{
    // Java: powx-n
    public static void main(String[] args) {
        Solution sol = new Leetcode0050PowxN().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public double myPow(double x, int n) {
        // corner cases
        if (n == 0) { // 0 to the power 0
            return 1;
        } else if (n == 1) {
            return x;
        }
        
        if (x == 0) {
            return 0;
        } else if (x == 1) {
            return 1;
        } else if (x == -1) {
            return (n % 2 == 0 ? 1 : -1);
        }
        
        if (n == Integer.MIN_VALUE) {
            return Math.abs(x) > 1 ? 0 : Integer.MAX_VALUE;
        }
        
        if (n < 0) {
            return 1.0 / myPow(x, -n);
        }
        
        double temp = myPow(x, n / 2);
        
        return n % 2 == 0 ? temp * temp : temp * temp * x;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}