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


/*
Solution 1:
直接按照右端点排序，从左到右遍历interval，遇到相邻的重复的interval时，保留前面的，删除后面的
T(n) = O(nlog(n)), S(n) = O(n)

证明：
本题的特殊之处在于要求先对“右端点”排序，然后再从左到右扫，
结论：遇到左右端点有overlap的时候，就把两者中的后者删去，
原因：数学归纳法，每到一个新的end point，都是得到[0， location]之间保留最多的不重复区间数量，设置为f(location)
    如果cur interval和next interval有重叠，需要考虑保留谁
        如果interval[cur] 和interval[cur+2] 也有重叠,即interval[cur].end > interval[cur + 2].start
            则interval[cur + 1] 和interval[cur + 2] 一定有重叠
            ∵（即interval[cur + 1].end > interval[cur].end > interval[cur + 2].start),
            此时考虑f(interval[cur+2].endpoint)的值，保留cur更好,
            ∵interval[cur+1]和interval[i]可能有重叠（ i <= cur - 1)
        如果interval[cur] 和interval[cur + 2] 没有重叠，
            即interval[cur].end < interval[cur + 2].start
            然后interval[cur].end < interval[cur + 1].end
            无法判断interval[cur + 1]和 interval[cur+2]是否有重叠
            如果保留cur:    f(interval[cur + 2].end) = f(interval[cur].end) + 1
            如果保留next:   f(interval[cur + 2].end)的值不确定，= f(interval[cur].end) + 1 or f(interval[cur]
            .end)
            为了保证f(interval[cur + 2].end)有更好的值，保留cur，删除next
    综上所述，有2个重叠interval的时候，保留前者，删除后者
所有保留下来的interval放在集合S里面，其中interval.end最大值设置为curEnd
每加进来一个和之前集合不重叠的区间，更新curEnd
 */
class Solution1 {
    
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
            if (curEnd > intervals[i][0]) { // there is overlap
                count++;
            } else {
                curEnd = intervals[i][1];
            }
        }
        return count;
    }
    
}

/* Solution 2: 按照左端点排序，遍历遇到重叠的时候，两个interval谁的右端点小，保留谁

证明：类似于solution 1: 设f(location) = [0，location]之间不重复区间的最大数量
设法让f(x)的值尽可能的大，
T(n) = O(nlog(n)), S(n) = O(n)
 */
class Solution2 {
    
    public int eraseOverlapIntervals(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 || intervals[0] == null
                || intervals[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0])); // O(nlogn)
        
        int count = 0; // the count of the removal
        int[] curInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (curInterval[1] <= interval[0]) { // no overlapping
                curInterval = interval;
            } else { // there is overlapping
                if (curInterval[1] > interval[1]) {
                    curInterval = interval;
                }
                count++;
            }
        }
        return count;
    }
    
}
}