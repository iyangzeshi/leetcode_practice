//We define the Perfect Number is a positive integer that is equal to the sum of
// all its positive divisors except itself. 
// 
//Now, given an integer n, write a function that returns true when it is a perfe
//ct number and false when it is not.
// 
//
// Example: 
// 
//Input: 28
//Output: True
//Explanation: 28 = 1 + 2 + 4 + 7 + 14
// 
// 
//
// Note:
//The input number n will not exceed 100,000,000. (1e8)
// Related Topics Math 
// 👍 292 👎 637

package leetcode.editor.en;

// 2020-09-10 20:59:31
// Jesse Yang
public class Leetcode0507PerfectNumber{
    // Java: perfect-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0507PerfectNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
	public boolean checkPerfectNumber(int num) {
		if (num <= 0) {
			return false;
		}
		int sum = 0;
		for (int i = 1; i * i <= num; i++) {
			if (num % i == 0) {
				sum += i;
				if (i * i != num) {
					sum += num / i;
				}
				
			}
		}
		return sum - num == num;
	}
}

//leetcode submit region end(Prohibit modification and deletion)

}