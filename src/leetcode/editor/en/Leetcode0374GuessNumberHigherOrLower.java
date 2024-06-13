//We are playing the Guess Game. The game is as follows: 
//
// I pick a number from 1 to n. You have to guess which number I picked. 
//
// Every time you guess wrong, I'll tell you whether the number is higher or low
//er. 
//
// You call a pre-defined API guess(int num) which returns 3 possible results (-
//1, 1, or 0): 
//
// 
//-1 : My number is lower
// 1 : My number is higher
// 0 : Congrats! You got it!
// 
//
// Example : 
//
// 
// 
//Input: n = 10, pick = 6
//Output: 6
// 
// 
// Related Topics Binary Search 
// 👍 427 👎 1769

package leetcode.editor.en;

// 2020-08-04 12:13:14
// Jesse Yang
public class Leetcode0374GuessNumberHigherOrLower {
    int n = 10;
    int pick = 6;
	// Java: guess-number-higher-or-lower
	public static void main(String[] args) {
		Solution sol = new Leetcode0374GuessNumberHigherOrLower().new Solution();
		// TO TEST

		System.out.println();
	}
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Forward declaration of guess API.
 *
 * return -1 if num is lower than the guess number 1 if num is higher than the guess number
 * otherwise return 0 int guess(int num);
 */

public class Solution extends GuessGame {

    public int guessNumber(int n) {
        //corner case
        if (n <= 0) {
            return -1;
        }
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = guess(mid);
            if (result == 0) {
                return mid;
            } else if (result == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
class GuessGame {
    int guess (int num) {
        return Integer.compare(n, num);
    }
}
}