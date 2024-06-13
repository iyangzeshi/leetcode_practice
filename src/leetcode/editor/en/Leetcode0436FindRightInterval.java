//You are given an array of intervals, where intervals[i] = [starti, endi] and e
//ach starti is unique. 
//
// The right interval for an interval i is an interval j such that startj >= end
//i and startj is minimized. 
//
// Return an array of right interval indices for each interval i. If no right in
//terval exists for interval i, then put -1 at index i. 
//
// 
// Example 1: 
//
// 
//Input: intervals = [[1,2]]
//Output: [-1]
//Explanation: There is only one interval in the collection, so it outputs -1.
// 
//
// Example 2: 
//
// 
//Input: intervals = [[3,4],[2,3],[1,2]]
//Output: [-1,0,1]
//Explanation: There is no right interval for [3,4].
//The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start t
//hat is >= end1 = 3.
//The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start t
//hat is >= end2 = 2.
// 
//
// Example 3: 
//
// 
//Input: intervals = [[1,4],[2,3],[3,4]]
//Output: [-1,2,-1]
//Explanation: There is no right interval for [1,4] and [3,4].
//The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start t
//hat is >= end1 = 3.
// 
//
// 
// Constraints: 
//
// 
// 1 <= intervals.length <= 2 * 104 
// intervals[i].length == 2 
// -106 <= starti <= endi <= 106 
// The start point of each interval is unique. 
// 
// Related Topics Binary Search 
// 👍 646 👎 184

package leetcode.editor.en;

import java.util.Comparator;
import java.util.TreeSet;
// 2020-12-22 18:43:51
// Jesse Yang
public class Leetcode0436FindRightInterval{
    // Java: find-right-interval
    public static void main(String[] args) {
        Solution sol = new Leetcode0436FindRightInterval().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
把所有的左边界的点放到TreeSet里面，然后按照值排序。
每次遇到一个interval的右边界点的时候，就把在TreeSet里面找比它大的start point，
    找得到的话就return idx
    找不到的话，就return -1
 */
class Solution {
    
    public int[] findRightInterval(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null
                || intervals[0].length == 0) {
            return new int[0];
        }
        
        TreeSet<Point> leftPointsTreeSet = new TreeSet<>(Comparator.comparingInt(o -> o.val)); // O(logn)
        int k = 0;
        for (int[] interval : intervals) { // O(n)
            leftPointsTreeSet.add(new Point(interval[0], k++));
        }
        
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) { // O(n)
            Point point = new Point(intervals[i][1], i);
            Point ceilingPoint = leftPointsTreeSet.ceiling(point); // O(logn)
            if (ceilingPoint == null) {
                res[i] = -1;
            } else {
                res[i] = ceilingPoint.idx;
            }
        }
        return res;
    }
    
    private class Point {
        
        private int val;
        private int idx;
        
        public Point(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
        
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}