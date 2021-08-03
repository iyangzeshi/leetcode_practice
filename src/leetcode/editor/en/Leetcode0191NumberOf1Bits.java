//Write a function that takes an unsigned integer and return the number of '1' b
//its it has (also known as the Hamming weight). 
//
// 
//
// Example 1: 
//
// 
//Input: 00000000000000000000000000001011
//Output: 3
//Explanation: The input binary string 00000000000000000000000000001011 has a to
//tal of three '1' bits.
// 
//
// Example 2: 
//
// 
//Input: 00000000000000000000000010000000
//Output: 1
//Explanation: The input binary string 00000000000000000000000010000000 has a to
//tal of one '1' bit.
// 
//
// Example 3: 
//
// 
//Input: 11111111111111111111111111111101
//Output: 31
//Explanation: The input binary string 11111111111111111111111111111101 has a to
//tal of thirty one '1' bits. 
//
// 
//
// Note: 
//
// 
// Note that in some languages such as Java, there is no unsigned integer type. 
//In this case, the input will be given as signed integer type and should not affe
//ct your implementation, as the internal binary representation of the integer is 
//the same whether it is signed or unsigned. 
// In Java, the compiler represents the signed integers using 2's complement not
//ation. Therefore, in Example 3 above the input represents the signed integer -3.
// 
// 
//
// 
//
// Follow up: 
//
// If this function is called many times, how would you optimize it? 
// Related Topics Bit Manipulation 
// 👍 912 👎 531

package leetcode.editor.en;

// 2020-08-04 11:52:56
// Zeshi Yang
public class Leetcode0191NumberOf1Bits{
    // Java: number-of-1-bits
    public static void main(String[] args) {
        Solution sol = new Leetcode0191NumberOf1Bits().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        //Solution 1: <<
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask << i) != 0) { // 不能改成 if ((n & mask << i) == 1)
                count++;
            }
        }
        return count;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

//Solution 1: <<
public class Solution1 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        //Solution 1: <<
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask << i) != 0) { // 不能改成 if ((n & mask << i) == 1)
                count++;
            }
        }
        return count;

    }
}

//Solution 2: >>>
public class Solution2 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        //Solution 2: >>>
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n >>> i & mask) == 1) {
                count++;
            }
        }
        return count;

    }
}

// Solution 3:
public class Solution3 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // S3:
        int count = 0;
        while ( n != 0 ){
            if ((n & 1) != 0) {
                count++;
            }
            n >>>= 1;
        }
        return count;
    }
}
}