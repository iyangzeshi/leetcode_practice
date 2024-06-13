//Implement a MyCalendar class to store your events. A new event can be added if
// adding the event will not cause a double booking. 
//
// Your class will have the method, book(int start, int end). Formally, this rep
//resents a booking on the half open interval [start, end), the range of real numb
//ers x such that start <= x < end. 
//
// A double booking happens when two events have some non-empty intersection (ie
//., there is some time that is common to both events.) 
//
// For each call to the method MyCalendar.book, return true if the event can be 
//added to the calendar successfully without causing a double booking. Otherwise, 
//return false and do not add the event to the calendar. 
//Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCale
//ndar.book(start, end)
//
// Example 1: 
//
// 
//MyCalendar();
//MyCalendar.book(10, 20); // returns true
//MyCalendar.book(15, 25); // returns false
//MyCalendar.book(20, 30); // returns true
//Explanation: 
//The first event can be booked.  The second can't because time 15 is already bo
//oked by another event.
//The third event can be booked, as the first event takes every time less than 2
//0, but not including 20.
// 
//
// 
//
// Note: 
//
// 
// The number of calls to MyCalendar.book per test case will be at most 1000. 
// In calls to MyCalendar.book(start, end), start and end are integers in the ra
//nge [0, 10^9]. 
// 
//
// 
// Related Topics Array 
// 👍 911 👎 42

package leetcode.editor.en;

import java.util.TreeMap;
// 2021-01-11 16:25:39
// Jesse Yang
public class Leetcode0729MyCalendarI{
    // Java: my-calendar-i
    public static void main(String[] args) {
        MyCalendar calendar = new Leetcode0729MyCalendarI().new MyCalendar();
        // TO TEST
        int[][] testData = {{877997,881496},{964833,967071}};
        test(calendar, testData);
    }
    
    private static void test(MyCalendar calendar, int[][] testData) {
        for (int[] interval: testData) {
            boolean res = calendar.book(interval[0], interval[1]);
            System.out.println(res);
        }
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
// Solution: TreeMap to find 2 candidates which may overlap which current one
// T(n) = O(nlog(n))， S(n) = O(n)
// 2 ms,击败了71.65% 的Java用户, 40.1 MB,击败了29.35% 的Java用户
class MyCalendar {
    
    private final TreeMap<Integer, Integer> calendar;
    
    MyCalendar() {
        calendar = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
    
}

/*
  Your MyCalendar object will be instantiated and called as such:
  MyCalendar obj = new MyCalendar();
  boolean param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)

}