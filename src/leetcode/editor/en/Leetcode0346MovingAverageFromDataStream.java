/**
Given a stream of integers and a window size, calculate the moving average of 
all integers in the sliding window. 

 Implement the MovingAverage class: 

 
 MovingAverage(int size) Initializes the object with the size of the window 
size. 
 double next(int val) Returns the moving average of the last size values of the 
stream. 
 

 
 Example 1: 

 
Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 

 
 Constraints: 

 
 1 <= size <= 1000 
 -10⁵ <= val <= 10⁵ 
 At most 10⁴ calls will be made to next. 
 

 Related Topics Array Design Queue Data Stream 👍 1685 👎 181

*/
package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

// 2024-11-22 14:04:51
// Jesse Yang
public class Leetcode0346MovingAverageFromDataStream{
    // Java: moving-average-from-data-stream
    public static void main(String[] args) {
        int size = 3;
        MovingAverage sol = new Leetcode0346MovingAverageFromDataStream().new MovingAverage(size);
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MovingAverage {
    
    Queue<Integer> queue;
    int size;
    int sum;
    
    
    public MovingAverage(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
        this.sum = 0;
    }
    
    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        queue.offer(val);
        sum += val;
        return (double) sum / queue.size();
    }
    
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
//leetcode submit region end(Prohibit modification and deletion)

}