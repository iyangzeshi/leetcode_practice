//Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND o
//f all numbers in this range, inclusive. 
//
// Example 1: 
//
// 
//Input: [5,7]
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: [0,1]
//Output: 0 Related Topics Bit Manipulation 
// 👍 1060 👎 125

package leetcode.editor.en;

// 2020-08-05 18:58:53
// Zeshi Yang
public class Leetcode0201BitwiseAndOfNumbersRange{
    // Java: bitwise-and-of-numbers-range
    public static void main(String[] args) {
        Solution sol = new Leetcode0201BitwiseAndOfNumbersRange().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        // corner case

        // general case
        int cnt = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            cnt++;
        }
        return m << cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}