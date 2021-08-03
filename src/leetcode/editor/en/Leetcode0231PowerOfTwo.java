//Given an integer, write a function to determine if it is a power of two. 
//
// Example 1: 
//
// 
//Input: 1
//Output: true 
//Explanation: 20 = 1
// 
//
// Example 2: 
//
// 
//Input: 16
//Output: true
//Explanation: 24 = 16 
//
// Example 3: 
//
// 
//Input: 218
//Output: false 
// Related Topics Math Bit Manipulation 
// 👍 934 👎 198

package leetcode.editor.en;

// 2020-08-04 11:59:48
// Zeshi Yang
public class Leetcode0231PowerOfTwo{
    // Java: power-of-two
    public static void main(String[] args) {
        Solution sol = new Leetcode0231PowerOfTwo().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean isPowerOfTwo(int n) {
        // S3: Hamming weight
        if (n <= 0) {
            return false;
        } else if (n == 1) {
            return true;
        }
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0) {
                count++;
            }
            n >>>= 1;
        }
        return count == 1;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 2: recursion
class Solution2 {
    
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        } else if (n == 1) {
            return true;
        }
        if (n % 2 != 0) {
            return false;
        }
        return isPowerOfTwo(n / 2);
    }
    
}
// Solution 3: Hamming weight
class Solution3 {
    
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        } else if (n == 1) {
            return true;
        }
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0) {
                count++;
            }
            n >>>= 1;
        }
        return count == 1;
    }
    
}

// Solution 4: bit operation
class Solution4 {
    
    public boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }
    
}
}