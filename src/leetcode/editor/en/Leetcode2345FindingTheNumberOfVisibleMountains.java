/**
You are given a 0-indexed 2D integer array peaks where peaks[i] = [xi, yi]
states that mountain i has a peak at coordinates (xi, yi). A mountain can be
described as a right-angled isosceles triangle, with its base along the x-axis and a
right angle at its peak. More formally, the gradients of ascending and descending
the mountain are 1 and -1 respectively.

 A mountain is considered visible if its peak does not lie within another
mountain (including the border of other mountains).

 Return the number of visible mountains.

 
 Example 1:
 
 
Input: peaks = [[2,2],[6,3],[5,4]]
Output: 2
Explanation: The diagram above shows the mountains.
- Mountain 0 is visible since its peak does not lie within another mountain or
its sides.
- Mountain 1 is not visible since its peak lies within the side of mountain 2.
- Mountain 2 is visible since its peak does not lie within another mountain or
its sides.
There are 2 mountains that are visible.

 Example 2:
 
 
Input: peaks = [[1,3],[1,3]]
Output: 0
Explanation: The diagram above shows the mountains (they completely overlap).
Both mountains are not visible since their peaks lie within each other.
 

 
 Constraints:

 
 1 <= peaks.length <= 10⁵
 peaks[i].length == 2
 1 <= xi, yi <= 10⁵
 

 Related Topics Array Stack Sorting Monotonic Stack 👍 157 👎 71

*/
package leetcode.editor.en;

import java.util.Arrays;
import java.util.Comparator;

// 2024-08-31 12:07:30
// Jesse Yang
public class Leetcode2345FindingTheNumberOfVisibleMountains{
    // Java: finding-the-number-of-visible-mountains
    public static void main(String[] args) {
        Solution sol = new Leetcode2345FindingTheNumberOfVisibleMountains().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
internal sort

since these are the right-angled isosceles triangles,
the height is half of the base length of triangle
the coverage condition if mountain 1 is covered by mountain 2 iff
left2 <= left1 && right1 <= right2, in order to meet mountain 2 first.

so, we can transfer the peaks array to the internals[baseLeft][baseRight] and
sort them as baseLeft increasingly and baseRight decreasingly

let the rightMost to mark the most right place that existed mountains can reach

everytime we find the  new mountain internal, just need to check whether it is covered by another
mountain
 */
class Solution {
    
    public int visibleMountains(int[][] peaks) {
        // corner case
        if (peaks == null || peaks.length == 0) {
            return 0;
        }
        
        int len = peaks.length;
        int[][] intervals = new int[len][2];
        for (int i = 0; i < len; i++) {
            intervals[i][0] = peaks[i][0] - peaks[i][1];
            intervals[i][1] = peaks[i][0] + peaks[i][1];
        }
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });
        
        int count = 0;// nums of the peaks seen
        int maxRight = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (intervals[i][1] > maxRight) {
                if ( i + 1 < len) {
                    if (Arrays.equals(intervals[i], intervals[i + 1])) {
                        count--;
                    }
                }
                count++;
                maxRight = intervals[i][1];
            }
        }
        
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}