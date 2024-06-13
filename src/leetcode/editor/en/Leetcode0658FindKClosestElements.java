//Given a sorted array arr, two integers k and x, find the k closest elements to
// x in the array. The result should also be sorted in ascending order. If there i
//s a tie, the smaller elements are always preferred. 
//
// 
// Example 1: 
// Input: arr = [1,2,3,4,5], k = 4, x = 3
//Output: [1,2,3,4]
// Example 2: 
// Input: arr = [1,2,3,4,5], k = 4, x = -1
//Output: [1,2,3,4]
// 
// 
// Constraints: 
//
// 
// 1 <= k <= arr.length 
// 1 <= arr.length <= 10^4 
// Absolute value of elements in the array and x will not exceed 104 
// 
// Related Topics Binary Search 
// 👍 1415 👎 246

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
// 2020-08-04 12:36:59
// Jesse Yang
public class Leetcode0658FindKClosestElements{
    // Java: find-k-closest-elements
    public static void main(String[] args) {
        Solution sol = new Leetcode0658FindKClosestElements().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        if (arr == null || arr.length == 0 || k <= 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        int left = 0;
        int right = arr.length - 1;
        /*
         the target is in the (left, right]
         */
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
            
        }
        for (int i = 0; i < k; i++) {
            if (left >= 0 && right <= arr.length - 1) {
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    left--;
                } else {
                    right++;
                }
            } else if (left < 0) {
                right++;
            } else {
                left--;
            }
        }
        int start = left < 0 ? 0 : left + 1;
        for (int i = 0; i < k; i++) {
            result.add(arr[start + i]);
        }
        return result;
        
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}