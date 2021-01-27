package leetcode.editor.en;

/*
 Program: leetcode_practice
 ClassName: temp
 Description:
 Author: Zeshi(Jesse) Yang
 Date: 2020-08-06 15:25
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Foo {
	
	public static void main(String[] args) {
		int[] nums1 = new int[] {1, 2};
		int[] nums2 = new int[] {1, 2};
		System.out.println(Arrays.equals(nums1, nums2));
		int[] nums = {1, 2, 5};
		Map<Integer, int[]> map = new HashMap<>();
		map.put(1, nums);
		nums[2] = 4;
		System.out.println(Arrays.toString(map.get(1)));
	}
	

}