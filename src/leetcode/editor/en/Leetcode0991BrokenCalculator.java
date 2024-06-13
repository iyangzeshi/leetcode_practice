//On a broken calculator that has a number showing on its display, we can perfor
//m two operations: 
//
// 
// Double: Multiply the number on the display by 2, or; 
// Decrement: Subtract 1 from the number on the display. 
// 
//
// Initially, the calculator is displaying the number X. 
//
// Return the minimum number of operations needed to display the number Y. 
//
// 
//
// Example 1: 
//
// 
//Input: X = 2, Y = 3
//Output: 2
//Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
// 
//
// Example 2: 
//
// 
//Input: X = 5, Y = 8
//Output: 2
//Explanation: Use decrement and then double {5 -> 4 -> 8}.
// 
//
// Example 3: 
//
// 
//Input: X = 3, Y = 10
//Output: 3
//Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.
// 
//
// Example 4: 
//
// 
//Input: X = 1024, Y = 1
//Output: 1023
//Explanation: Use decrement operations 1023 times.
// 
//
// 
//
// Note: 
//
// 
// 1 <= X <= 10^9 
// 1 <= Y <= 10^9 
// Related Topics Math Greedy 
// 👍 843 👎 129

package leetcode.editor.en;

// 2021-03-10 20:51:16
// Jesse Yang
public class Leetcode0991BrokenCalculator{
    // Java: broken-calculator
    public static void main(String[] args) {
        Solution sol = new Leetcode0991BrokenCalculator().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(lg(Y/X)), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 35.4 MB,击败了92.13% 的Java用户
/*
The motivation for this is that it turns out we always greedily divide by 2
greedy, only two ways to transform the number, X-- or X *= 2,

Instead of multiplying by 2 or subtracting 1 from X,
we could divide by 2 (when Y is even) or add 1 to Y.

    case1: Y is even,
        then if we perform 2 additions and one division,
        we could instead perform one division and one addition for less operations
        [(Y + 1 + 1) / 2 vs Y/2 + 1].
            (Y + 1 + 1) / 2 —— 3 operations
            Y/2 + 1 —— 2 operations
            so

    case 2: Y is odd,
        then if we perform 3 additions and one division,
        we could instead perform 1 addition, 1 division,
        and 1 addition for less operations
        [(Y + 1 + 1 + 1) / 2 vs (Y+1) / 2 + 1].
            (Y + 1 + 1 + 1) / 2 —— 4 operations
            (Y + 1) / 2 + 1 —— 3 operations
        so, the (Y + 1) / 2 + 1 is better

so, the best way to restore Y to X, is
    case 1: Y is odd, Y++
    case 2: Y is even, Y /= 2
 */
class Solution {
    // transform from X to Y
    public int brokenCalc(int X, int Y) {
        int res = 0;
        while (Y > X) {
            res++;
            if (Y % 2 == 1) {
                Y++;
            } else {
                Y /= 2;
            }
        }
        return res + X - Y;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}