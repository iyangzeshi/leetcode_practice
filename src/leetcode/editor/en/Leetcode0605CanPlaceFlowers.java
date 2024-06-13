//You have a long flowerbed in which some of the plots are planted, and some are
// not. However, flowers cannot be planted in adjacent plots. 
//
// Given an integer array flowerbed containing 0's and 1's, where 0 means empty 
//and 1 means not empty, and an integer n, return if n new flowers can be planted 
//in the flowerbed without violating the no-adjacent-flowers rule. 
//
// 
// Example 1: 
// Input: flowerbed = [1,0,0,0,1], n = 1
//Output: true
// Example 2: 
// Input: flowerbed = [1,0,0,0,1], n = 2
//Output: false
// 
// 
// Constraints: 
//
// 
// 1 <= flowerbed.length <= 2 * 104 
// flowerbed[i] is 0 or 1. 
// There are no two adjacent flowers in flowerbed. 
// 0 <= n <= flowerbed.length 
// 
// Related Topics Array Greedy 
// 👍 1401 👎 460

package leetcode.editor.en;

// 2021-04-21 17:01:59
// Jesse Yang
public class Leetcode0605CanPlaceFlowers{
    // Java: can-place-flowers
    public static void main(String[] args) {
        Solution sol = new Leetcode0605CanPlaceFlowers().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// greedy, T(n) = O(n), S(n) = O(1)
/*
用greedy的做法
假设当前位置是i，如果i-1, i, i+1都是空着的，这个位置就可以放花，否则不可以
 */
class Solution {
    
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0;
        int count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 &&
                    (i == 0 || flowerbed[i - 1] == 0) &&
                    (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}