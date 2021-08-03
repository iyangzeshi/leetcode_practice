//Implement a MyCalendarTwo class to store your events. A new event can be added
// if adding the event will not cause a triple booking. 
//
// Your class will have one method, book(int start, int end). Formally, this rep
//resents a booking on the half open interval [start, end), the range of real numb
//ers x such that start <= x < end. 
//
// A triple booking happens when three events have some non-empty intersection (
//ie., there is some time that is common to all 3 events.) 
//
// For each call to the method MyCalendar.book, return true if the event can be 
//added to the calendar successfully without causing a triple booking. Otherwise, 
//return false and do not add the event to the calendar. 
//Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCale
//ndar.book(start, end)
//
// Example 1: 
//
// 
//MyCalendar();
//MyCalendar.book(10, 20); // returns true
//MyCalendar.book(50, 60); // returns true
//MyCalendar.book(10, 40); // returns true
//MyCalendar.book(5, 15); // returns false
//MyCalendar.book(5, 10); // returns true
//MyCalendar.book(25, 55); // returns true
//Explanation: 
//The first two events can be booked.  The third event can be double booked.
//The fourth event (5, 15) can't be booked, because it would result in a triple 
//booking.
//The fifth event (5, 10) can be booked, as it does not use time 10 which is alr
//eady double booked.
//The sixth event (25, 55) can be booked, as the time in [25, 40) will be double
// booked with the third event;
//the time [40, 50) will be single booked, and the time [50, 55) will be double 
//booked with the second event.
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
// Related Topics Ordered Map 
// 👍 725 👎 96

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// 2021-01-12 01:35:30
// Zeshi Yang
public class Leetcode0731MyCalendarIi{
    // Java: my-calendar-ii
    public static void main(String[] args) {
        MyCalendarTwo calendar = new Leetcode0731MyCalendarIi().new MyCalendarTwo();
        // TO TEST
        int[][] testData = {{10,20},{50,60},{10,40},{5,15},{5,10},{25,55}};
        test(calendar, testData);
    }
    
    private static void test(MyCalendarTwo calendar, int[][] testData) {
        for (int[] interval: testData) {
            boolean res = calendar.book(interval[0], interval[1]);
            System.out.println(res);
        }
    }
//leetcode submit region begin(Prohibit modification and deletion)
public class MyCalendarTwo {
    
    private final List<int[]> calendar;
    private final List<int[]> overlaps;
    
    MyCalendarTwo() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int[] interval : overlaps) {
            if (Integer.compare(interval[0], end) * Integer.compare(interval[1], start) < 0) {
                return false;
            }
        }
        for (int[] interval : calendar) {
            if (interval[0] < end && start < interval[1]) {
                overlaps.add(new int[]{Math.max(start, interval[0]), Math.min(end, interval[1])});
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
    
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)

/*
面试的时候，好解释的话，用Solution 1
要最优解的话，用Solution 3
 */

// Solution 1: count boundaries
// T(n) = O(n^2), S(n) = O(n)
// 171 ms,击败了53.75% 的Java用户, 40.2 MB,击败了29.29% 的Java用户
class MyCalendarTwo1 {
    
    private final TreeMap<Integer, Integer> calendar;
    private final int limit;
    
    public MyCalendarTwo1() {
        calendar = new TreeMap<>();
        calendar.put(0, 0);
        limit = 3;
    }
    
    public boolean book(int start, int end) {
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);
        
        int active = 0;
        for (int times : calendar.values()) {
            active += times;
            if (active >= limit) {
                calendar.put(start, calendar.get(start) - 1);
                calendar.put(end, calendar.get(end) + 1);
                if (calendar.get(start) == 0) {
                    calendar.remove(start);
                }
                if (calendar.get(end) == 0) {
                    calendar.remove(end);
                }
                return false;
            }
        }
        return true;
    }
    
}

// Solution 2: count overlap，各维护一个表示overlap区间，和存在的calendar的数组
// T(n) = O(n^2  * log(k)), S(n) = O(n)
// 64 ms,击败了66.67% 的Java用户, 39.8 MB,击败了67.70% 的Java用户
/*
count overlap，各维护一个表示overlap区间，和存在的calendar的数组，
每次检查interval和overlap有没有重叠
    有重叠的话，就return false;
加入到calendar里面，update calendar数组
同时检查查interval和calendar有没有重叠，可能要更新overlap数组
return true;
 */
class MyCalendarTwo2 {
    
    private final List<int[]> calendar;
    private final List<int[]> overlaps;
    
    MyCalendarTwo2() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int[] interval : overlaps) {
            if (interval[0] < end && interval[1] < start) {
                return false;
            }
        }
        for (int[] interval : calendar) {
            if (interval[0] < end && start < interval[1]) {
                overlaps.add(new int[]{Math.max(start, interval[0]), Math.min(end, interval[1])});
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
    
}

// Solution 3: 最优解，increment the intersection regions
// T(n) = O(n), 因为每个地方的高度最多只有3, S(n) = O(n)
// 44 ms,击败了91.73% 的Java用户, 40.7 MB,击败了18.43% 的Java用户
/*
只有在每次加入一个区间要更新所有点的时候,才会到O(n^2)
Map key-point index, value -height, 表示从当前点key开始的高度height是多少,直到下一个点之前都不会变
 */
class MyCalendarTwo3 {
    
    private final TreeMap<Integer, Integer> calendar;
    private final int limit;
    
    public MyCalendarTwo3() {
        calendar = new TreeMap<>();
        calendar.put(0, 0);
        limit = 3;
    }
    
    public boolean book(int start, int end) {
        int maxHeight = 0;
        // pre-processing, update map-start
        int prevPoint = calendar.floorKey(start);
        int height = calendar.get(prevPoint) + 1;
        if (height >= limit) {
            return false;
        }
        maxHeight = Math.max(maxHeight, height);
        calendar.put(start, calendar.get(prevPoint) + 1);
        Integer cur = calendar.higherKey(start);
        
        while (cur != null && cur < end) {
            height = calendar.getOrDefault(cur, 0) + 1;
            maxHeight = Math.max(maxHeight, height);
            calendar.put(cur, height);
            if (maxHeight >= limit) {
                while (cur != null && cur >= start) {
                    calendar.put(cur, calendar.get(cur) - 1);
                    cur = calendar.lowerKey(cur);
                }
                return false;
            }
            cur = calendar.higherKey(cur);
        }
        // post processing, update map -end
        if (cur == null || cur > end) { // cur > end
            calendar.put(end, calendar.lowerEntry(end).getValue() - 1);
        }
        mergeSameHeight(start, end, cur);
        return true;
    }
    
    private void mergeSameHeight(int start, int end, Integer cur) {
        // if height at start and height at start's previous point is same, merge it
        if (start != 0 && calendar.lowerEntry(start).getValue().equals(calendar.get(start))) {
            calendar.remove(start);
        }
        
        // if height at end and height at end' next point is same, merge it
        if (cur != null && cur == end && calendar.get(end).equals(calendar.lowerEntry(end).getValue())) {
            calendar.remove(end);
        }
    }
    
}

}
