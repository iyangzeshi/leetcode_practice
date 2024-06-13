//Given an array of integers arr and an integer target. 
//
// You have to find two non-overlapping sub-arrays of arr each with sum equal ta
//rget. There can be multiple answers so you have to find an answer where the sum 
//of the lengths of the two sub-arrays is minimum. 
//
// Return the minimum sum of the lengths of the two required sub-arrays, or retu
//rn -1 if you cannot find such two sub-arrays. 
//
// 
// Example 1: 
//
// 
//Input: arr = [3,2,2,4,3], target = 3
//Output: 2
//Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their 
//lengths is 2.
// 
//
// Example 2: 
//
// 
//Input: arr = [7,3,4,7], target = 7
//Output: 2
//Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7]
//, [3,4] and [7]), but we will choose the first and third sub-arrays as the sum o
//f their lengths is 2.
// 
//
// Example 3: 
//
// 
//Input: arr = [4,3,2,6,2,3,4], target = 6
//Output: -1
//Explanation: We have only one sub-array of sum = 6.
// 
//
// Example 4: 
//
// 
//Input: arr = [5,5,4,4,5], target = 3
//Output: -1
//Explanation: We cannot find a sub-array of sum = 3.
// 
//
// Example 5: 
//
// 
//Input: arr = [3,1,1,1,5,1,2,1], target = 3
//Output: 3
//Explanation: Note that sub-arrays [1,2] and [2,1] cannot be an answer because 
//they overlap.
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 10^5 
// 1 <= arr[i] <= 1000 
// 1 <= target <= 10^8 
// 
// Related Topics Dynamic Programming 
// 👍 339 👎 17

package leetcode.editor.en;

import java.util.Arrays;

// 2020-07-24 14:52:42
// Jesse Yang
public class Leetcode1477FindTwoNonOverlappingSubArraysEachWithTargetSum{
    // Java: find-two-non-overlapping-sub-arrays-each-with-target-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode1477FindTwoNonOverlappingSubArraysEachWithTargetSum().new Solution();
        // TO TEST
        int[] arr = {2,2,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int target = 20;
        int res = sol.minSumOfLengths(arr, target);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // Jesse Yang's code
    public int minSumOfLengths(int[] arr, int target) {
        // corner case
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // general case
        int left = 0;
        int len = arr.length;
        int[] minLenSubArray = new int[len]; // min length of a valid subarray ends or before i
        Arrays.fill(minLenSubArray, Integer.MAX_VALUE);
        int sum = 0;
        int res = Integer.MAX_VALUE;
        int tmpMinLen = Integer.MAX_VALUE;

        for (int right = 0; right < len; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                if (left > 0 && minLenSubArray[left -1] != Integer.MAX_VALUE) {
                    res = Math.min(res, minLenSubArray[left - 1] + (right - left + 1));
                }
                tmpMinLen = Math.min(tmpMinLen, right - left + 1);
            }
            // when to update the minLenSubArray[right] either we find a better one or just use the previous
            minLenSubArray[right] = tmpMinLen;
        }

        return res == Integer.MAX_VALUE ? - 1 : res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}