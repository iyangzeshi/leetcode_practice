//Given an integer n, return an array ans of length n + 1 such that for each i (
//0 <= i <= n), ans[i] is the number of 1's in the binary representation of i. 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: [0,1,1]
//Explanation:
//0 --> 0
//1 --> 1
//2 --> 10
// 
//
// Example 2: 
//
// 
//Input: n = 5
//Output: [0,1,1,2,1,2]
//Explanation:
//0 --> 0
//1 --> 1
//2 --> 10
//3 --> 11
//4 --> 100
//5 --> 101
// 
//
// 
// Constraints: 
//
// 
// 0 <= n <= 10⁵ 
// 
//
// 
// Follow up: 
//
// 
// It is very easy to come up with a solution with a runtime of O(n log n). Can 
//you do it in linear time O(n) and possibly in a single pass? 
// Can you do it without using any built-in function (i.e., like __builtin_
//popcount in C++)? 
// 
//
// Related Topics Dynamic Programming Bit Manipulation 👍 10732 👎 495

package leetcode.editor.en;

// 2023-12-26 15:50:12
// Jesse Yang
public class Leetcode0338CountingBits{
    // Java: counting-bits
    public static void main(String[] args) {
        Solution sol = new Leetcode0338CountingBits().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// 辗转相除法： T(n) = O(n * lg(n)), S(n) = O(n)
class Solution {
    
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int quotient = i / 2;
            int remainder = i % 2;
            res[i] = res[quotient] + remainder;
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 辗转相除法： T(n) = O(n * lg(n)), S(n) = O(n)
class Solution1 {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int count = 0;
            int temp = i;
            while (temp != 0) {
                int remainder = temp % 2;
                if (remainder == 1) {
                    count++;
                }
                temp /= 2;
            }
            res[i] = count;
        }
        return res;
    }
}

// Solution 2: 辗转相除法 + DP： T(n) = O(n), S(n) = O(n)
class Solution2 {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int quotient = i / 2;
            int remainder = i % 2;
            res[i] = res[quotient] + remainder;
        }
        return res;
    }
}
}