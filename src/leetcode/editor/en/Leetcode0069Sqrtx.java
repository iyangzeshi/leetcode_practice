//Implement int sqrt(int x). 
//
// Compute and return the square root of x, where x is guaranteed to be a non-ne
//gative integer. 
//
// Since the return type is an integer, the decimal digits are truncated and onl
//y the integer part of the result is returned. 
//
// Example 1: 
//
// 
//Input: 4
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: 8
//Output: 2
//Explanation: The square root of 8 is 2.82842..., and since 
//             the decimal part is truncated, 2 is returned.
// 
// Related Topics Math Binary Search 
// 👍 1348 👎 1921

package leetcode.editor.en;

// 2020-07-24 23:15:06
// Jesse Yang
public class Leetcode0069Sqrtx {

	// Java: sqrtx
	public static void main(String[] args) {
		Solution sol = new Leetcode0069Sqrtx().new Solution();
		// TO TEST
        int x = 8;
        int res = sol.mySqrt(x);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int mySqrt(int x) {
        // corner case
        if (x < 0) {
            return -1;
        } else if (x < 1) {
            return 0;
        } else if (x < 4) {
            return 1;
        }

        // general case
        int left = 0;
        int right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid  == x / mid) { // 不要写成是mid * mid == x，因为有可能会溢出。而且不是整除的时候，就会有问题
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}