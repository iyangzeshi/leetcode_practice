//A frog is crossing a river. The river is divided into x units and at each unit
// there may or may not exist a stone. The frog can jump on a stone, but it must n
//ot jump into the water. 
//
// Given a list of stones' positions (in units) in sorted ascending order, deter
//mine if the frog is able to cross the river by landing on the last stone. Initia
//lly, the frog is on the first stone and assume the first jump must be 1 unit.
// 
//
// If the frog's last jump was k units, then its next jump must be either k - 1,
// k, or k + 1 units. Note that the frog can only jump in the forward direction. 
//
// Note:
// 
// The number of stones is ≥ 2 and is < 1,100. 
// Each stone's position will be a non-negative integer < 231. 
// The first stone's position is always 0. 
// 
// 
//
// Example 1:
// 
//[0,1,3,5,6,8,12,17]
//
//There are a total of 8 stones.
//The first stone at the 0th unit, second stone at the 1st unit,
//third stone at the 3rd unit, and so on...
//The last stone at the 17th unit.
//
//Return true. The frog can jump to the last stone by jumping 
//1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
//2 units to the 4th stone, then 3 units to the 6th stone, 
//4 units to the 7th stone, and 5 units to the 8th stone.
// 
// 
//
// Example 2:
// 
//[0,1,2,3,4,8,9,11]
//
//Return false. There is no way to jump to the last stone as 
//the gap between the 5th and 6th stone is too large.
// 
// Related Topics Dynamic Programming

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Leetcode403FrogJump
 * @Description:
 * @Author: Zeshi(Jesse) Yang
 * @Date: 2020/06/30 周二 17:09
 */
public class Leetcode0403FrogJump {
	
	// Java: frog-jump
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0403FrogJump().new Solution();
		// TO TEST
		int[] stones = new int[]{0,1,2,3,4,8,9,11};
		boolean res = sol.canCross(stones);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public boolean canCross(int[] stones) {
		// corner case
		if (stones == null || stones.length == 0) {
			return false;
		}
		if (stones[1] - stones[0] != 1) {
			return false;
		}
		
		Map<Integer, Boolean> memo[] = new HashMap[stones.length];
		for (int i = 0; i < stones.length; i++) {
			memo[i] = new HashMap<Integer, Boolean>();
		}
		
		return dfs(1, 1, stones, memo);
	}
	
	private boolean dfs(int index, int step, int[] stones, Map<Integer, Boolean>[] memo) {
		int len = stones.length;
		if (index >= stones.length) {
			return false;
		}
		Map<Integer, Boolean> map = memo[index];
		// base case
		if (map.containsKey(step)) {
			return map.get(step);
		}
		
		if (index == len - 1) {
			return true;
		}
		
		/*for (int i = step - 1; (i > 0) && (i <= step + 1); i++) {
			if (dfs(index + i, i, stones, memo)) {
				map.put(step, true);
				return true;
			}
		}*/
		for (int i = index + 1; i < len; i++) {
			int newStep = stones[i] - stones[index];
			if (newStep < step - 1) {
				continue;
			}
			if (newStep > step + 1) {
				break;
			}
			if (dfs(i, stones[i] - stones[index], stones, memo)) {
				map.put(step, true);
				return true;
			}
		}
		map.put(step, false);
		return false;
	}
}

//leetcode submit region end(Prohibit modification and deletion)
// DFS, Time Limit Exceeded
// T(n) = O(2^n), S(n) = O(n)
/*
This is a tree problem. Building the search tree from left to right
Every time frog reaching to a position, he can choose 3 positions for the next step.
 */
class Solution1_1 {
		
    public boolean canCross(int[] stones) {
        
        if (stones == null || stones.length <= 1) {
            return false;
        }
        if (stones[1] - stones[0] != 1) {
            return false;
        }
        
        return dfs(stones, 1, 1);
    }
    
    private boolean dfs(int[] stones, int idx, int k) {
        // HashMap<Integer, Boolean> map = mem[idx];
        // lookup form
/*        if(map.containsKey(k)) {
        return map.get(k);
    }*/
        
        int len = stones.length;
        
        // base case
        if (idx == len - 1) {
            return true;
        }
        if (idx > len - 1) {
            return false;
        }
        
        for (int i = idx + 1; i < len; i++) {
            int dis = stones[i] - stones[idx];
            if (dis < k - 1) {
                continue;
            }
            if (dis > k + 1) {
                break;
            }
            if (dfs(stones, i, dis)) {
                // map.put(k, true);
                return true;
            }
        }
        // map.put(k, false);
        return false;
    }
}

// DFS with pruning, time complexity T(n) = O(nk), S(n) = O(n)
/*
This is a tree problem. Building the search tree from left to right
Using a DFS search tree to solve the problem, every time when frog jump to the position,
there are 2 status (position, and step).
Creating a HashMap<Length, Boolean> map [river.length] to record when it can cross the river.

 */
class Solution1_2 {
    
    // DFS with pruning, time complexity: O(nk)
    public boolean canCross(int[] stones) {
        
        if (stones == null || stones.length <= 1) {
            return false;
        }
        if (stones[1] - stones[0] != 1) {
            return false;
        }
        HashMap<Integer, Boolean>[] mem = new HashMap[stones.length];
        for (int i = 0; i < stones.length; i++) {
            mem[i] = new HashMap<>();
        }
        
        return dfs(stones, 1, 1, mem);
    }
	
	/**
	 * @param stones
	 * @param idx
	 * @param k: reaching to stones[idx] with step k
	 * @param mem: HashMap array, contains stone index and steps reaching to this stone[index]
	 * @return: true or false
	 */
    private boolean dfs(int[] stones, int idx, int k, HashMap<Integer, Boolean>[] mem) {
        
        HashMap<Integer, Boolean> map = mem[idx];
        // lookup form
        if (map.containsKey(k)) {
            return map.get(k);
        }
        
        int len = stones.length;
        
        // base case
        if (idx == len - 1) {
            return true;
        }
        if (idx > len - 1) {
            return false;
        }
        
        for (int i = idx + 1; i < len; i++) {
            int dis = stones[i] - stones[idx];
            if (dis < k - 1) {
                continue;
            }
            if (dis > k + 1) {
                break;
            }
            if (dfs(stones, i, dis, mem)) {
                map.put(k, true);
                return true;
            }
        }
        map.put(k, false);
        return false;
    }
}
}