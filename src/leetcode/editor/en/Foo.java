package leetcode.editor.en;

/*
 Program: leetcode_practice
 ClassName: temp
 Description:
 Author: Zeshi(Jesse) Yang
 Date: 2020-08-06 15:25
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Foo {
	
	public static void main(String[] args) {
		Solution sol = new Foo().new Solution();
        // TO TEST
        int[] nums1 = {1,2,4,5,6};
        int[] nums2 = {3,5,7,9};
        int k = 1;
		List<List<Integer>> res = sol.kSmallestPairs(nums1, nums2, k);
        System.out.println(res);
	}
	
	
class Solution {
	
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> res = new LinkedList<>();
		// corner case
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
			return res;
		}
		// general case
		int len1 = nums1.length;
		int len2 = nums2.length;
		// heap to store the index of the sum from nums1 and nums2;, sum = num1 * len2 + num2
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((prod1, prod2) ->
				(nums1[prod1 / len2] * nums2[prod1 % len2]) - (nums1[prod2 / len2] * nums2[prod2
						% len2]));
		minHeap.offer(0);
		Set<Integer> visited = new HashSet<>();
		visited.add(0);
		while (k-- > 0 && !minHeap.isEmpty()) {
			int num = minHeap.poll();
			int index1 = num / len2;
			int index2 = num % len2;
			List<Integer> list = new ArrayList<>();
			list.add(nums1[index1]);
			list.add(nums2[index2]);
			res.add(list);
			
			int neighbor = (index1 + 1) * len2 + index2;
			if (index1 + 1 < len1 && !visited.contains(neighbor)) {
				minHeap.offer(neighbor);
				visited.add(neighbor);
			}
			neighbor = index1 * len2 + index2 + 1;
			if (index2 + 1 < len2 && !visited.contains(neighbor)) {
				minHeap.offer(neighbor);
				visited.add(neighbor);
			}
		}
		return res;
	}
	
}

}