//Given an array of meeting time intervals consisting of start and end times [[s
//1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings. 
//
// Example 1: 
//
// 
//Input: [[0,30],[5,10],[15,20]]
//Output: false
// 
//
// Example 2: 
//
// 
//Input: [[7,10],[2,4]]
//Output: true
// 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Sort 
// 👍 644 👎 39

package leetcode.editor.en;

import java.util.*;
// 2020-09-08 22:06:08
// Zeshi Yang
public class Leetcode0252MeetingRooms{
    // Java: meeting-rooms
    public static void main(String[] args) {
        Solution sol = new Leetcode0252MeetingRooms().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int len = intervals.length;
        for (int i = 0; i < len - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}