//Design a class to find the kth largest element in a stream. Note that it is th
//e kth largest element in the sorted order, not the kth distinct element. 
//
// Your KthLargest class will have a constructor which accepts an integer k and 
//an integer array nums, which contains initial elements from the stream. For each
// call to the method KthLargest.add, return the element representing the kth larg
//est element in the stream. 
//
// Example: 
//
// 
//int k = 3;
//int[] arr = [4,5,8,2];
//KthLargest kthLargest = new KthLargest(3, arr);
//kthLargest.add(3);   // returns 4
//kthLargest.add(5);   // returns 5
//kthLargest.add(10);  // returns 5
//kthLargest.add(9);   // returns 8
//kthLargest.add(4);   // returns 8
// 
//
// Note: 
//You may assume that nums' length ≥ k-1 and k ≥ 1. 
// Related Topics Heap 
// 👍 851 👎 448

package leetcode.editor.en;

import java.util.PriorityQueue;
// 2020-09-08 18:23:56
// Zeshi Yang
public class Leetcode0703KthLargestElementInAStream{
    // Java: kth-largest-element-in-a-stream
    public static void main(String[] args) {
        int k = 1;
        int[] nums = {};
        KthLargest sol = new Leetcode0703KthLargestElementInAStream().new KthLargest(k, nums);
        // TO TEST
        System.out.println(sol.add(-3));
        System.out.println(sol.add(-2));
        System.out.println(sol.add(-4));
        System.out.println(sol.add(0));
        System.out.println(sol.add(4));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class KthLargest {
	
	PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // store the largest K elements
	int k;
	
	public KthLargest(int k, int[] nums) {
		this.k = k;
		for(int num: nums) {
			if (minHeap.size() < k) {
				minHeap.offer(num);
			} else {
				if (num > minHeap.peek()) {
					minHeap.poll();
					minHeap.offer(num);
				}
			}
		}
	}
	
	public int add(int val) {
		if (minHeap.size() < k) {
			minHeap.offer(val);
		} else {
			if (val > minHeap.peek()) {
				minHeap.offer(val);
				minHeap.poll();
			}
		}
		return minHeap.peek();
	}
}

/*
  Your KthLargest object will be instantiated and called as such:
  KthLargest obj = new KthLargest(k, nums);
  int param_1 = obj.add(val);
 */
//leetcode submit region end(Prohibit modification and deletion)

}