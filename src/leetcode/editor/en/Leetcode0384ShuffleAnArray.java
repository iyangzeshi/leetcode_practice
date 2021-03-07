//Shuffle a set of numbers without duplicates.
// 
//
// Example:
// 
//// Init an array with set 1, 2, and 3.
//int[] nums = {1,2,3};
//Solution solution = new Solution(nums);
//
//// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3]
// must equally likely to be returned.
//solution.shuffle();
//
//// Resets the array back to its original configuration [1,2,3].
//solution.reset();
//
//// Returns the random shuffling of array [1,2,3].
//solution.shuffle();
// 
// 👍 541 👎 1074

package leetcode.editor.en;

import java.util.*;
// 2020-08-05 23:01:55
// Zeshi Yang
public class Leetcode0384ShuffleAnArray{
    // Java: shuffle-an-array
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution sol = new Leetcode0384ShuffleAnArray().new Solution(nums);
        System.out.println(Arrays.toString(sol.shuffle()));
        System.out.println(Arrays.toString(sol.reset()));
        System.out.println(Arrays.toString(sol.shuffle()));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int[] array;
    private int[] original;
    private Random random;

    public Solution(int[] nums) {
        array = nums;
        original = nums.clone();
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = original.clone();
        return array;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        // corner case
        if (array == null || array.length == 0) {
            return null;
        }
        // general case
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int k = random.nextInt(i + 1); // [0, i]
            swap(k, i);
        }
        return array;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
/*
  Your Solution object will be instantiated and called as such:
  Solution obj = new Solution(nums);
  int[] param_1 = obj.reset();
  int[] param_2 = obj.shuffle();
 */
//leetcode submit region end(Prohibit modification and deletion)

}