/**
Write an algorithm to determine if a number n is happy. 

 A happy number is a number defined by the following process: 

 
 Starting with any positive integer, replace the number by the sum of the 
squares of its digits. 
 Repeat the process until the number equals 1 (where it will stay), or it loops 
endlessly in a cycle which does not include 1. 
 Those numbers for which this process ends in 1 are happy. 
 

 Return true if n is a happy number, and false if not. 

 
 Example 1: 

 
Input: n = 19
Output: true
Explanation:
1² + 9² = 82
8² + 2² = 68
6² + 8² = 100
1² + 0² + 0² = 1
 

 Example 2: 

 
Input: n = 2
Output: false
 

 
 Constraints: 

 
 1 <= n <= 2³¹ - 1 
 

 Related Topics Hash Table Math Two Pointers 👍 11076 👎 1557

*/
package leetcode.editor.en;

// 2025-05-11 14:41:00
// Jesse Yang
public class Leetcode0202HappyNumber{
    // Java: happy-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0202HappyNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isHappy(int n) {
        // corner case
        if (n == 1) {
            return true;
        }
        
        int sum = 0;
        while (n != 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}