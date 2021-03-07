//Given a signed 32-bit integer x, return x with its digits reversed. If reversi
//ng x causes the value to go outside the signed 32-bit integer range [-231, 231 -
// 1], then return 0. 
//
// Assume the environment does not allow you to store 64-bit integers (signed or
// unsigned). 
//
// 
// Example 1: 
// Input: x = 123
//Output: 321
// Example 2: 
// Input: x = -123
//Output: -321
// Example 3: 
// Input: x = 120
//Output: 21
// Example 4: 
// Input: x = 0
//Output: 0
// 
// 
// Constraints: 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics Math 
// 👍 4195 👎 6501

package leetcode.editor.en;

// 2021-01-14 16:36:00
// Zeshi Yang
public class Leetcode0007ReverseInteger{
    // Java: reverse-integer
    public static void main(String[] args) {
        Solution sol = new Leetcode0007ReverseInteger().new Solution();
        // TO TEST
        int x = 123;
        int res = sol.reverse(x);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        long res;
        // corner case
        if (x == 0) {
            return 0;
        } else if (x < 0) {
            res = -reverseLong(-x);
        } else {
            res = reverseLong(x);
        }
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int) res;
        }
    }
    
    private long reverseLong(long x) {
        long res = 0;
        while (x != 0) {
            res *= 10;
            long num = x % 10;
            x /= 10;
            res += num;
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1:
// 1 ms,击败了100.00% 的Java用户,36 MB,击败了82.73% 的Java用户
class Solution1 {
    public int reverse(int x) {
        // corner case
        if (x == 0 || x == Integer.MIN_VALUE) {
            return 0;
        } else if (x < 0) {
            return -reverse(-x);
        }
        int res = 0;
        while (x != 0) {
            if (res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            res *= 10;
            int num = x % 10;
            x /= 10;
            if (res >= Integer.MAX_VALUE - num) {
                return 0;
            }
            res += num;
        }
        return res;
    }
}

// Solution 2:
// 1 ms,击败了100.00% 的Java用户, 36.4 MB,击败了33.46% 的Java用户
class Solution2 {
    public int reverse(int x) {
        long res;
        // corner case
        if (x == 0) {
            return 0;
        } else if (x < 0) {
            res = -reverseLong(-x);
        } else {
            res = reverseLong(x);
        }
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int) res;
        }
    }
    
    private long reverseLong(long x) {
        long res = 0;
        while (x != 0) {
            res *= 10;
            long num = x % 10;
            x /= 10;
            res += num;
        }
        return res;
    }
    
}
}