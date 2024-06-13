//Given an integer n, return the number of trailing zeroes in n!. 
//
// Example 1: 
//
// 
//Input: 3
//Output: 0
//Explanation: 3! = 6, no trailing zero. 
//
// Example 2: 
//
// 
//Input: 5
//Output: 1
//Explanation: 5! = 120, one trailing zero. 
//
// Note: Your solution should be in logarithmic time complexity. 
// Related Topics Math 
// 👍 894 👎 1137

package leetcode.editor.en;

// 2020-08-04 11:46:10
// Jesse Yang
public class Leetcode0172FactorialTrailingZeroes{
    // Java: factorial-trailing-zeroes
    public static void main(String[] args) {
        Solution sol = new Leetcode0172FactorialTrailingZeroes().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trailingZeroes(int n) {
        // 题目让找末尾有几个0，末尾的0一定可以分解成2 * 5。
        // 阶乘的质因数里面，明显5的数字比2的数字多很多
        // 找到阶乘的质因数里面有多少个5，末尾就有多少个0
        // 25，125这种数字有多个质因数5，所以除以5直到0，看有多少个5的质因数

        // Solution 1: while循环，看每一层有多少个5个的倍数，再把n/5直到循环结束
        // corner case
        if (n < 0) {
            return -1;
        }
        int result = 0;
        while (n > 0) {
            result += n / 5;
            n /= 5;
        }
        return result;
    }
}
// 解析 https://leetcode.wang/leetcode-172-Factorial-Trailing-Zeroes.html
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: while循环，看每一层有多少个5个的倍数，再把n/5直到循环结束
class Solution1 {
    public int trailingZeroes(int n) {
        // 题目让找末尾有几个0，末尾的0一定可以分解成2 * 5。
        // 阶乘的质因数里面，明显5的数字比2的数字多很多
        // 找到阶乘的质因数里面有多少个5，末尾就有多少个0
        // 25，125这种数字有多个质因数5，所以除以5直到0，看有多少个5的质因数

        // Solution 1: while循环，看每一层有多少个5个的倍数，再把n/5直到循环结束
        // corner case
        if (n < 0) {
            return -1;
        }
        int result = 0;
        while (n > 0) {
            result += n / 5;
            n /= 5;
        }
        return result;
    }
}

// Solution 2: 先办事再recursion
class Solution2 {
    public int trailingZeroes(int n) {
        // 题目让找末尾有几个0，末尾的0一定可以分解成2 * 5。
        // 阶乘的质因数里面，明显5的数字比2的数字多很多
        // 找到阶乘的质因数里面有多少个5，末尾就有多少个0
        // 25，125这种数字有多个质因数5，所以除以5直到0，看有多少个5的质因数

        // S2: 先办事再recursion
        // corner case
        if (n < 0) {
            return -1;
        }
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
}