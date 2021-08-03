//Given a set of non-overlapping intervals, insert a new interval into the inter
//vals (merge if necessary). 
//
// You may assume that the intervals were initially sorted according to their st
//art times. 
//
// Example 1: 
//
// 
//Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
//Output: [[1,5],[6,9]]
// 
//
// Example 2: 
//
// 
//Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//Output: [[1,2],[3,10],[12,16]]
//Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10]. 
//
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Array Sort 
// 👍 1798 👎 190

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.ListIterator;

// 2020-09-08 19:34:31
// Zeshi Yang
public class Leetcode0057InsertInterval{
    // Java: insert-interval
    public static void main(String[] args) {
        Solution sol = new Leetcode0057InsertInterval().new Solution();
        // TO TEST
        int[][] intervals = {{1, 5}};
        int[] newInterval = {0, 0};
        // int[][] res = sol.insert(intervals, newInterval);
        // System.out.println(Arrays.deepToString(res));
        String str = "b";
        System.out.println(str.compareTo("null"));
    }

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> res = new LinkedList<>();
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        boolean addNewInterval = true;
        for (int[] interval: intervals) {
            if (interval[1] < newInterval[0]) { // the interval is in front of the newInterval
                res.add(interval);
            } else if (interval[0] > newInterval[1]) { // the interval is behind the newInterval
                if (addNewInterval) {
                    res.add(newInterval);
                    addNewInterval = false;
                }
                res.add(interval);
            } else { // has overlap between interval and newInterval
                // newInterval[0] = Math.min(interval[0], newInterval[0]);
                // newInterval[1] = Math.max(interval[1], newInterval[1]);
                newInterval = new int[]{
                        Math.min(interval[0], newInterval[0]),
                        Math.max(interval[1], newInterval[1])
                };
            }
        }
        if (addNewInterval || newInterval[0] > intervals[intervals.length - 1][1]) {
            res.add(newInterval);
        }
        int size = res.size();
        int[][] ans = new int[size][2];
        ListIterator<int[]> iter = res.listIterator();
        for (int i = 0; i < size; i++) {
            ans[i] = iter.next();
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

}