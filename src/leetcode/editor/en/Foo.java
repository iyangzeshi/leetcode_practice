package leetcode.editor.en;

/*
 Program: leetcode_practice
 ClassName: temp
 Description:
 Author: Zeshi(Jesse) Yang
 Date: 2020-08-06 15:25
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foo {
	
	public static void main(String[] args) {
		Solution sol = new Foo().new Solution();
		int rideDuration = 90;
		List<Integer> songDurations = new ArrayList<>(Arrays.asList(1, 10, 25, 35, 60));
		List<Integer> res = sol.findSongs(rideDuration, songDurations);
		System.out.println(res);
	}
/*
T(n) = O(n), S(n) = O(n)
 */
class Solution {
	
	public List<Integer> findSongs(int rideDuration, List<Integer> songDurations) {
		// Write your code here
		int size = songDurations.size();
		List<Integer> pair = new ArrayList<>();
		int target = rideDuration - 30;
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			int duration = songDurations.get(i);
			if (map.containsKey(target - duration)) { // 当前pair符合要求
				if (!pair.isEmpty()) { // 前面也已经有符合要求的解了
					int curMax = Math.max(duration, target - duration);
					int max = Math.max(pair.get(0), pair.get(1));
					if (curMax > max) {
						pair.clear();
						pair.addAll(Arrays.asList(target - duration, duration));
						res.clear();
						res.addAll(Arrays.asList(map.get(pair.get(0)), i));
					}
				} else {
					pair.clear();
					pair.addAll(Arrays.asList(target - duration, duration));
					res.clear();
					res.addAll(Arrays.asList(map.get(pair.get(0)), i));
				}
			}
			if (!map.containsKey(duration)) {
				map.put(duration, i);
			}
		}
		if (res.isEmpty()) {
			res.add(-1);
			res.add(-1);
		}
		return res;
	}
	
}

}