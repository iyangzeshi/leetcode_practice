//We are given a list schedule of employees, which represents the working time f
//or each employee. 
//
// Each employee has a list of non-overlapping Intervals, and these intervals ar
//e in sorted order. 
//
// Return the list of finite intervals representing common, positive-length free
// time for all employees, also in sorted order. 
//
// (Even though we are representing Intervals in the form [x, y], the objects in
//side are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, 
//schedule[0][0].end = 2, and schedule[0][0][0] is not defined). Also, we wouldn't
// include intervals like [5, 5] in our answer, as they have zero length. 
//
// 
// Example 1: 
//
// 
//Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
//Output: [[3,4]]
//Explanation: There are a total of three employees, and all common
//free time intervals would be [-inf, 1], [3, 4], [10, inf].
//We discard any intervals that contain inf as they aren't finite.
// 
//
// Example 2: 
//
// 
//Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
//Output: [[5,6],[7,9]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= schedule.length , schedule[i].length <= 50 
// 0 <= schedule[i].start < schedule[i].end <= 10^8 
// 
// Related Topics Heap Greedy 
// 👍 586 👎 46

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 2020-09-08 22:13:36
// Zeshi Yang
public class Leetcode0759EmployeeFreeTime{
    // Java: employee-free-time
    public static void main(String[] args) {
        Solution sol = new Leetcode0759EmployeeFreeTime().new Solution();
        // TO TEST
        List<Interval> person1 = new LinkedList<>();
        List<Interval> person2 = new LinkedList<>();

        person1.add(new Interval(1,2));
        person1.add(new Interval(5,6));
        person2.add(new Interval(1,3));
        person2.add(new Interval(4,10));
        List<List<Interval>> schedule = new LinkedList<>();
        schedule.add(person1);
        schedule.add(person2);
        List<Interval> spareTimeInterval = sol.employeeFreeTime(schedule);
        System.out.println(spareTimeInterval);
    }
//leetcode submit region begin(Prohibit modification and deletion)

enum Terminal {
    START,
    END
}

class Point implements Comparable<Point> {

    public int time;
    Terminal terminal;

    public Point(int time, Terminal terminal) {
        this.time = time;
        this.terminal = terminal;
    }

    @Override
    public int compareTo(Point point) {
        if (point == null) {
            return -1;
        } else {
            if (this.time != point.time) {
                return Integer.compare(this.time, point.time);
            } else {
                return this.terminal.compareTo(point.terminal);
            }
        }
    }
}

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        LinkedList<Interval> res = new LinkedList<>();
        // corner case
        if (schedule == null || schedule.size() == 0) {
            return res;
        }

        List<Point> sortedPointList = buildList(schedule);
        int count = 0;
        int preCount = 0;
        int start = Integer.MIN_VALUE;
        res.addFirst(new Interval(start, 0));
        for (Point point: sortedPointList) {
            int time = point.time;
            Terminal terminal = point.terminal;
            if (terminal == Terminal.START) {
                count++;
            } else { // terminal == Terminal.END
                count--;
            }
            if (count == 0) {
                res.add(new Interval(time, 0));
            } else if (count == 1 && preCount == 0) {
                res.getLast().end = time;
            }
            preCount = count;
        }
        res.removeFirst();
        res.removeLast();
        return res;
    }

    private List<Point> buildList(List<List<Interval>> schedule) {
        ArrayList<Point> pointList = new ArrayList<>();
        for (List<Interval> interval: schedule) {
            for (Interval i: interval) {
                Point startPoint = new Point(i.start, Terminal.START);
                Point endPoint = new Point(i.end, Terminal.END);
                pointList.add(startPoint);
                pointList.add(endPoint);
            }
        }
        Collections.sort(pointList);
        return pointList;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
// Definition for an Interval.
static class Interval {
    
    public int start;
    public int end;
    
    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
    
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
    
}

}