//Given a collection of intervals, find the minimum number of intervals you need
// to remove to make the rest of the intervals non-overlapping. 
//
// 
// 
//
// 
//
// Example 1: 
//
// 
//Input: [[1,2],[2,3],[3,4],[1,3]]
//Output: 1
//Explanation: [1,3] can be removed and the rest of intervals are non-overlappin
//graph.
// 
//
// Example 2: 
//
// 
//Input: [[1,2],[1,2],[1,2]]
//Output: 2
//Explanation: You need to remove two [1,2] to make the rest of intervals non-ov
//erlapping.
// 
//
// Example 3: 
//
// 
//Input: [[1,2],[2,3]]
//Output: 0
//Explanation: You don't need to remove any of the intervals since they're alrea
//dy non-overlapping.
// 
//
// 
//
// Note: 
//
// 
// You may assume the interval's end point is always bigger than its start point
//. 
// Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap
// each other. 
// 
// Related Topics Greedy 
// 👍 1738 👎 49

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Comparator;

// 2020-12-22 20:50:08
// Jesse Yang
public class Leetcode0435NonOverlappingIntervals{
    // Java: non-overlapping-intervals
    public static void main(String[] args) {
        Solution sol = new Leetcode0435NonOverlappingIntervals().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2:直接按照右端点排序，T(n) = O(nlog(n)), S(n) = O(n)
// 3 ms,击败了58.96% 的Java用户, 39.5 MB,击败了12.78% 的Java用户
class Solution {
    
    public int eraseOverlapIntervals(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 || intervals[0] == null
                || intervals[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1])); // O(nlogn)
        
        int count = 0;
        int curEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (curEnd > intervals[i][0]) {
                count++;
            } else {
                curEnd = intervals[i][1];
            }
        }
        return count;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 按照左端点排序，遍历有重叠的时候，谁的右端点小，保留谁 T(n) = O(nlog(n)), S(n) = O(n)
// 1 ms,击败了100.00% 的Java用户, 38.9 MB,击败了64.22% 的Java用户
class Solution1 {
    
    public int eraseOverlapIntervals(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 || intervals[0] == null
                || intervals[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0])); // O(nlogn)
        
        int count = 0;
        int curEnd = intervals[0][1];
        int[] curInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (curInterval[1] <= interval[0]) { // no overlapping
                curInterval = interval;
            } else {
                if (curInterval[1] > interval[1]) {
                    curInterval = interval;
                }
                count++;
            }
        }
        return count;
    }
    
}

// Solution 2:直接按照右端点排序，T(n) = O(nlog(n)), S(n) = O(n)
// 3 ms,击败了58.96% 的Java用户, 39.5 MB,击败了12.78% 的Java用户
/*
本题的特殊之处在于要求先对“右端点”排序，然后再从左到右扫，
遇到左右端点有overlap的时候，就把两者中的后者删去，
    因为如果cur interval和后面有重叠，前面重叠的interval和后面不一定有重叠，
        所以这个时候肯定保留重叠两个中的前者
    如果cur和interval和后面没有重叠，前面重叠的interval和后面一定没有重叠，
        这个时候保留谁都一样
    综上所述，重叠的时候，保留前者，删除后者
否则无overlap时，更新interval右边界
 */
class Solution2 {
    
    public int eraseOverlapIntervals(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 || intervals[0] == null
                || intervals[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1])); // O(nlogn)
        
        int count = 0;
        int curEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (curEnd > intervals[i][0]) {
                count++;
            } else {
                curEnd = intervals[i][1];
            }
        }
        return count;
    }
    
}
}