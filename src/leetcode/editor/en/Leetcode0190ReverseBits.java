//Reverse bits of a given 32 bits unsigned integer. 
//
// 
//
// Example 1: 
//
// 
//Input: 00000010100101000001111010011100
//Output: 00111001011110000010100101000000
//Explanation: The input binary string 00000010100101000001111010011100 represen
//ts the unsigned integer 43261596, so return 964176192 which its binary represent
//ation is 00111001011110000010100101000000.
// 
//
// Example 2: 
//
// 
//Input: 11111111111111111111111111111101
//Output: 10111111111111111111111111111111
//Explanation: The input binary string 11111111111111111111111111111101 represen
//ts the unsigned integer 4294967293, so return 3221225471 which its binary repres
//entation is 10111111111111111111111111111111. 
//
// 
//
// Note: 
//
// 
// Note that in some languages such as Java, there is no unsigned integer type. 
//In this case, both input and output will be given as signed integer type and sho
//uld not affect your implementation, as the internal binary representation of the
// integer is the same whether it is signed or unsigned. 
// In Java, the compiler represents the signed integers using 2's complement not
//ation. Therefore, in Example 2 above the input represents the signed integer -3 
//and the output represents the signed integer -1073741825. 
// 
//
// 
//
// Follow up: 
//
// If this function is called many times, how would you optimize it? 
// Related Topics Bit Manipulation 
// 👍 1171 👎 421

package leetcode.editor.en;

// 2020-08-04 11:49:28
// Jesse Yang
public class Leetcode0190ReverseBits{
    // Java: reverse-bits
    public static void main(String[] args) {
        Solution sol = new Leetcode0190ReverseBits().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    // you need treat n as an unsigned value
    // Solution 2: reverse it from 2 edges until the middle
    public int reverseBits(int n) {
        //corner case
        if (n == 0 || n == Math.pow(2, 31) - 1) {
            return n;
        }

        for (int i = 0; i < 16; i++) {
            int left = 1 & (n >> (31 - i));//
            int right = 1 & (n >> i); // >>> 和>>都可以
            if (left != right) { // XOR
                n ^= 1 << (31 - i);
                n ^= 1 << i;
            }
        }
        return n;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: reverse it by ergodic the number
public class Solution1 {
    // you need treat n as an unsigned value
    // Solution 1: reverse it by ergodic the number
    public int reverseBits(int n) {
        //corner case
        if (n == 0 || n == Math.pow(2, 31) - 1) {
            return n;
        }

        int result = 0;

        for (int i = 0; i < 32; i++) {
            int mask = (n >>> i) & 1;
            if (mask == 1) {
                result |= (mask << 31 - i);
            }
        }
        return result;
    }
}

// Solution 2: reverse it from 2 edges until the middle
public class Solution2 {
    // you need treat n as an unsigned value
    // Solution 2: reverse it from 2 edges until the middle
    public int reverseBits(int n) {
        //corner case
        if (n == 0 || n == Math.pow(2, 31) - 1) {
            return n;
        }

        for (int i = 0; i < 16; i++) {
            int left = 1 & (n >> (31 - i));//
            int right = 1 & (n >> i); // >>> 和>>都可以
            if (left != right) { // XOR
                n ^= 1 << (31 - i);
                n ^= 1 << i;
            }
        }
        return n;

    }
}

}