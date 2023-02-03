//Median is the middle value in an ordered integer list. If the size of the list
// is even, there is no middle value. So the median is the mean of the two middle 
//value. 
//For example,
//
// [2,3,4], the median is 3 
//
// [2,3], the median is (2 + 3) / 2 = 2.5 
//
// Design a data structure that supports the following two operations: 
//
// 
// void addNum(int num) - Add a integer number from the data stream to the data 
//structure. 
// double findMedian() - Return the median of all elements so far. 
// 
//
// 
//
// Example: 
//
// 
//addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2
// 
//
// 
//
// Follow up: 
//
// 
// If all integer numbers from the stream are between 0 and 100, how would you o
//ptimize it? 
// If 99% of all integer numbers from the stream are between 0 and 100, how woul
//d you optimize it? 
// 
// Related Topics Heap Design 
// 👍 2697 👎 51

package leetcode.editor.en;

import java.util.Collections;
import java.util.PriorityQueue;
// 2020-07-26 14:00:36
// Zeshi Yang
public class Leetcode0295FindMedianFromDataStream{
    // Java: find-median-from-data-stream
    public static void main(String[] args) {
        MedianFinder sol = new Leetcode0295FindMedianFromDataStream().new MedianFinder();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {
    
    PriorityQueue<Integer> max = new PriorityQueue<>(); // larger
    PriorityQueue<Integer> min = new PriorityQueue<>(Collections.reverseOrder()); // smaller
    int size = 0;
    
    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
    
    }
    
    public void addNum(int num) {
        size++;
        if (max.size() == 0 || max.peek() < num) {
            max.offer(num);
            if (max.size() - min.size() > 1) {
                min.offer(max.poll());
            }
        } else {
            min.offer(num);
            if (min.size() > max.size()) {
                max.offer(min.poll());
            }
        }
    }
    
    public double findMedian() {
        if (size % 2 == 0) {
            return (max.peek() + min.peek()) / 2.0;
        } else {
            return (double) max.peek();
        }
    }
    
}

/*
  Your MedianFinder object will be instantiated and called as such:
  MedianFinder obj = new MedianFinder();
  obj.addNum(num);
  double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}