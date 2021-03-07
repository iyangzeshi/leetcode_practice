//Given a collection of intervals, merge all overlapping intervals. 
//
// Example 1: 
//
// 
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// 
//
// Example 2: 
//
// 
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping. 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Array Sort 
// 👍 4405 👎 299

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 2020-07-27 17:10:48
// Zeshi Yang
public class Leetcode0056MergeIntervals{
    // Java: merge-intervals
    public static void main(String[] args) {
        Solution sol = new Leetcode0056MergeIntervals().new Solution();
        // TO TEST
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = sol.merge(intervals);
        System.out.println(Arrays.deepToString(res));
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution : sort by left points
class Solution {
    public int[][] merge(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 ||
                intervals[0] == null || intervals[0].length == 0) {
            return new int[][]{};
        }
        // general case
        Arrays.sort(intervals, Comparator.comparingInt(n -> n[0]));
        List<int[]> res = new ArrayList<>();
        int[] prev = intervals[0]; // to record previous interval's right border
        for (int[] interval: intervals) {
            if (prev[1] >= interval[0]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                res.add(prev);
                prev = interval.clone();
            }

        }
        res.add(prev);
        return res.toArray(new int[][]{new int[res.size()]});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}