//You have a number of envelopes with widths and heights given as a pair of inte
//gers (w, h). One envelope can fit into another if and only if both the width and
// height of one envelope is greater than the width and height of the other envelo
//pe. 
//
// What is the maximum number of envelopes can you Russian doll? (put one inside
// other) 
//
// Note: 
//Rotation is not allowed. 
//
// Example: 
//
// 
// 
//Input: [[5,4],[6,4],[6,7],[2,3]]
//Output: 3 
//Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] 
//=> [5,4] => [6,7]).
// 
// 
// Related Topics Binary Search Dynamic Programming 
// 👍 1437 👎 47

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 2020-12-13 16:05:43
// Zeshi Yang
public class Leetcode0354RussianDollEnvelopes{
    // Java: russian-doll-envelopes
    public static void main(String[] args) {
        Solution sol = new Leetcode0354RussianDollEnvelopes().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
1. 这道题目其实是LC300最长递增子序列(Longes Increasing Subsequence，简写为 LIS)的一个变种，
    因为很显然，每次合法的嵌套是大的套小的，相当于找一个最长递增的子序列，其长度就是最多能嵌套的信封个数。
2. 先对宽度 w 进行升序排序，如果遇到 w 相同的情况，则按照高度 h 降序排序。
    之后把所有的 h 作为一个数组，在这个数组上计算 LIS 的长度就是答案。
3. 这个解法的关键在于，对于宽度 w 相同的数对，要对其高度 h 进行降序排序。
    因为两个宽度相同的信封不能相互包含的，逆序排序保证在 w 相同的数对中最多只选取一个。
 */
class Solution {
    // time = O(nlogn), space = O(1)
    public int maxEnvelopes(int[][] envelopes) {
        // corner case
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null
                || envelopes[0].length == 0) {
            return 0;
        }
    
        int row = envelopes.length;
        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
    
        int[] height = new int[row];
        for (int i = 0; i < row; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }
    
    // LC300: Longest Increasing Subsequence
    private int lengthOfLIS(int[] height) {
        List<Integer> buffer = new ArrayList<>();
        
        for (int n : height) {
            int idx = getIndex(buffer, n);
            if (idx < buffer.size()) {
                buffer.set(idx, n);
            } else {
                buffer.add(n);
            }
        }
        return buffer.size();
    }
    
    private int getIndex(List<Integer> buffer, int target) {
        int start = 0;
        int end = buffer.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (buffer.get(mid) == target) {
                return mid;
            }
            if (buffer.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}