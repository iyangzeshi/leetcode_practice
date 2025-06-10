/**
There are n different online courses numbered from 1 to n. You are given an 
array courses where courses[i] = [durationi, lastDayi] indicate that the iᵗʰ course 
should be taken continuously for durationi days and must be finished before or 
on lastDayi. 

 You will start on the 1ˢᵗ day and you cannot take two or more courses 
simultaneously. 

 Return the maximum number of courses that you can take. 

 
 Example 1: 

 
Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
Output: 3
Explanation: 
There are totally 4 courses, but you can take 3 courses at most:
First, take the 1ˢᵗ course, it costs 100 days so you will finish it on the 100ᵗʰ
 day, and ready to take the next course on the 101ˢᵗ day.
Second, take the 3ʳᵈ course, it costs 1000 days so you will finish it on the 110
0ᵗʰ day, and ready to take the next course on the 1101ˢᵗ day. 
Third, take the 2ⁿᵈ course, it costs 200 days so you will finish it on the 1300ᵗ
ʰ day. 
The 4ᵗʰ course cannot be taken now, since you will finish it on the 3300ᵗʰ day, 
which exceeds the closed date.
 

 Example 2: 

 
Input: courses = [[1,2]]
Output: 1
 

 Example 3: 

 
Input: courses = [[3,2],[4,3]]
Output: 0
 

 
 Constraints: 

 
 1 <= courses.length <= 10⁴ 
 1 <= durationi, lastDayi <= 10⁴ 
 

 Related Topics Array Greedy Sorting Heap (Priority Queue) 👍 3886 👎 102

*/
package leetcode.editor.en;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

// 2025-05-29 01:35:24
// Jesse Yang
public class Leetcode0630CourseScheduleIii{
    // Java: course-schedule-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0630CourseScheduleIii().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]); // sort by lastDay
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
        int time = 0;
        
        for (int[] course : courses) {
            int duration = course[0], lastDay = course[1];
            time += duration;
            pq.offer(duration);
            if (time > lastDay) {
                time -= pq.poll(); // remove longest
            }
        }
        
        return pq.size(); // number of courses taken
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}