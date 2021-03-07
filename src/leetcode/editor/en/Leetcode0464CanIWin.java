//In the "100 game," two players take turns adding, to a running total, any inte
//ger from 1..10. The player who first causes the running total to reach or exceed
// 100 wins. 
//
// What if we change the game so that players cannot re-use integers? 
//
// For example, two players might take turns drawing from a common pool of numbe
//rs of 1..15 without replacement until they reach a total >= 100. 
//
// Given an integer maxChoosableInteger and another integer curSum, determ
//ine if the first player to move can force a win, assuming both players play opti
//mally. 
//
// You can always assume that maxChoosableInteger will not be larger than 20 and
// curSum will not be larger than 300.
// 
//
// Example
// 
//Input:
//maxChoosableInteger = 10
//curSum = 11
//
//Output:
//false
//
//Explanation:
//No matter which integer the first player choose, the first player will lose.
//The first player can choose an integer from 1 up to 10.
//If the first player choose 1, the second player can only choose integers from 
//2 up to 10.
//The second player will win by choosing 10 and get a total = 11, which is >= de
//siredTotal.
//Same with other integers chosen by the first player, the second player will al
//ways win.
// 
// Related Topics Dynamic Programming Minimax

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Leetcode464CanIWin
 * @Description:
 * @Author: Zeshi(Jesse) Yang
 * @Date: 2020/06/30 周二 19:12
 */
public class Leetcode0464CanIWin {
	
	// Java: can-i-win
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0464CanIWin().new Solution();
		// TO TEST
		boolean res = sol.canIWin(10, 40);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	// DFS with pruning
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// corner case
		if (maxChoosableInteger <= 0 || maxChoosableInteger > 20 || desiredTotal < 0
				|| desiredTotal > 300) {
			throw new IllegalArgumentException();
		}
		// if (1+2+3+...+maxChoosableInteger) 仍小于curSum, 不可能true
		int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
		if (sum < desiredTotal) {
			return false;
		}
		// boolean[] pool，表示1，2，3...maxChoosableInteger中的某个数是否被用过(true/false)
		/* 因为题目说assume that maxChoosableInteger will not be larger than 20，
		所以可以把boolean pool的每个位置转化成bit操作，把数字pool看成是二进制，
		数字 i 是available，就把第 i 位bit赋值成1这样才能够用mem进行Pruning*/
		
		boolean[] pool = new boolean[maxChoosableInteger + 1];
		Arrays.fill(pool, true);
		Map<String, Boolean> mem = new HashMap<>();
		return dfs(pool, 0, desiredTotal, maxChoosableInteger, mem);
	}
	
	/**
	 * @param pool:               if number i is available, the pool[i] is true
	 * @param curSum:             the current sum
	 * @param desiredTotal:       desired total number
	 * @return: true if win
	 */
	private boolean dfs(boolean[] pool, int curSum, int desiredTotal, int maxChoosableInteger,
			Map<String, Boolean> mem) {
		// corner case, received curSum from system instead of competitor
		if (desiredTotal == 0) {
			return true;
		}
		String str = Arrays.toString(pool);
		// lookup the DP form
		if (mem.containsKey(str)) {
			return mem.get(str);
		}
		// base case
		if (curSum >= desiredTotal) {
			mem.put(str, false);
			return false;
		}
		
		for (int i = 1; i <= maxChoosableInteger; i++) {
			if (pool[i]) {
				pool[i] = false;
				boolean res = dfs(pool, curSum + i, desiredTotal, maxChoosableInteger, mem);
				pool[i] = true; // backtracking
				if (!res) {
					mem.put(str, true);
					return true;
				}
			}
		}
		mem.put(str, false);
		return false;
	}
}

//leetcode submit region end(Prohibit modification and deletion)
class Solution1_1 {
	
	// DFS, Time Limit Exceeded
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// corner case
		if (maxChoosableInteger <= 0 || maxChoosableInteger > 20 || desiredTotal < 0
				|| desiredTotal > 300) {
			throw new IllegalArgumentException();
		}
		// if (1+2+3+...+maxChoosableInteger) 仍小于curSum, 不可能true
		int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
		if (sum < desiredTotal) {
			return false;
		}
		// boolean[] pool，表示1，2，3...maxChoosableInteger中的某个数是否被用过(true/false)
		// 因为题目说assume that maxChoosableInteger will not be larger than 20，
		// 所以可以用比特操作，这样才能够用mem进行Pruning
		
		boolean[] pool = new boolean[maxChoosableInteger + 1];
		Arrays.fill(pool, true);
		return dfs(pool, 0, desiredTotal, maxChoosableInteger);
	}
	
	/**
	 * @param pool:               if number i is available, the pool[i] is true
	 * @param curSum:             the current sum
	 * @param desiredTotal:       desired total number
	 * @return: true if win
	 */
	private boolean dfs(boolean[] pool, int curSum, int desiredTotal,
			int maxChoosableInteger) {
		// corner case, received curSum from system instead of competitor
		if (desiredTotal == 0) {
			return true;
		}
		// base case
		if (curSum >= desiredTotal) {
			return false;
		}
		
		for (int i = 1; i <= maxChoosableInteger; i++) {
			if (pool[i]) {
				pool[i] = false;
			/*if (curSum >= desiredTotal) {
				return true;
			}*/
				boolean res = dfs(pool, curSum + i, desiredTotal, maxChoosableInteger);
				pool[i] = true; // backtracking
				if (!res) {
					return true;
				}
			}
		}
		return false;
	}
}

class Solution1_2 {
	
	// DFS with pruning
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// corner case
		if (maxChoosableInteger <= 0 || maxChoosableInteger > 20 || desiredTotal < 0
				|| desiredTotal > 300) {
			throw new IllegalArgumentException();
		}
		// if (1+2+3+...+maxChoosableInteger) 仍小于curSum, 不可能true
		int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
		if (sum < desiredTotal) {
			return false;
		}
		// boolean[] pool，表示1，2，3...maxChoosableInteger中的某个数是否被用过(true/false)
	/* 因为题目说assume that maxChoosableInteger will not be larger than 20，
	所以可以把boolean pool的每个位置转化成bit操作，把数字pool看成是二进制，
	数字 i 是available，就把第 i 位bit赋值成1这样才能够用mem进行Pruning*/
		
		boolean[] pool = new boolean[maxChoosableInteger + 1];
		Arrays.fill(pool, true);
		Map<String, Boolean> mem = new HashMap<>();
		return dfs(pool, 0, desiredTotal, maxChoosableInteger, mem);
	}
	
	/**
	 * @param pool:               if number i is available, the pool[i] is true
	 * @param curSum:             the current sum
	 * @param desiredTotal:       desired total number
	 * @return: true if win
	 */
	private boolean dfs(boolean[] pool, int curSum, int desiredTotal, int maxChoosableInteger,
			Map<String, Boolean> mem) {
		// corner case, received curSum from system instead of competitor
		if (desiredTotal == 0) {
			return true;
		}
		String str = Arrays.toString(pool);
		// lookup the DP form
		if (mem.containsKey(str)) {
			return mem.get(str);
		}
		// base case
		if (curSum >= desiredTotal) {
			mem.put(str, false);
			return false;
		}
		
		for (int i = 1; i <= maxChoosableInteger; i++) {
			if (pool[i]) {
				pool[i] = false;
				boolean res = dfs(pool, curSum + i, desiredTotal, maxChoosableInteger, mem);
				pool[i] = true; // backtracking
				if (!res) {
					mem.put(str, true);
					return true;
				}
			}
		}
		mem.put(str, false);
		return false;
	}
}

class Solution1_3 {

	// DFS with pruning，
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// corner case
		if (maxChoosableInteger <= 0 || maxChoosableInteger > 20 || desiredTotal < 0
				|| desiredTotal > 300) {
			throw new IllegalArgumentException();
		}
		// if (1+2+3+...+maxChoosableInteger) 仍小于curSum, 不可能true
		int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
		if (sum < desiredTotal) {
			return false;
		}
		// boolean[] pool，表示1，2，3...maxChoosableInteger中的某个数是否被用过(true/false)
		/* 因为题目说assume that maxChoosableInteger will not be larger than 20，
		所以可以把boolean pool的每个位置转化成bit操作，把数字pool看成是二进制，
		数字 i 是available，就把第 i 位bit赋值成1这样才能够用mem进行Pruning*/
		Boolean[] available = new Boolean[maxChoosableInteger + 1];
		Arrays.fill(available, true);
		ArrayList<Boolean> pool = new ArrayList<>(Arrays.asList(available));
		
		Map<ArrayList<Boolean>, Boolean> mem = new HashMap<>();
		return dfs(pool, 0, desiredTotal, maxChoosableInteger, mem);
	}
	
	/**
	 * @param pool:               if number i is available, the pool[i] is true
	 * @param curSum:             the current sum
	 * @param desiredTotal:       desired total number
	 * @return: true if win
	 */
	private boolean dfs(ArrayList<Boolean> pool, int curSum, int desiredTotal,
			int maxChoosableInteger,
			Map<ArrayList<Boolean>, Boolean> mem) {
		// corner case, received curSum from system instead of competitor
		if (desiredTotal == 0) {
			return true;
		}
		// lookup the DP form
		if (mem.containsKey(pool)) {
			return mem.get(pool);
		}
		// base case
		if (curSum >= desiredTotal) {
			mem.put(pool, false);
			return false;
		}
		
		for (int i = 1; i <= maxChoosableInteger; i++) {
			if (pool.get(i)) {
				pool.set(i, false);
				boolean res = dfs(new ArrayList<>(pool), curSum + i, desiredTotal,
						maxChoosableInteger, mem);
				pool.set(i, true);// backtracking
				if (!res) {
					mem.put(pool, true);
					return true;
				}
			}
		}
		mem.put(pool, false);
		return false;
	}
}

class Solution1_4 {
	
	// DFS with pruning
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// corner case
		if (maxChoosableInteger <= 0 || maxChoosableInteger > 20 || desiredTotal < 0
				|| desiredTotal > 300) {
			throw new IllegalArgumentException();
		}
		// if (1+2+3+...+maxChoosableInteger) 仍小于curSum, 不可能true
		int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
		if (sum < desiredTotal) {
			return false;
		}
		// boolean[] pool，表示1，2，3...maxChoosableInteger中的某个数是否被用过(true/false)
	/* 因为题目说assume that maxChoosableInteger will not be larger than 20，
	所以可以把boolean pool的每个位置转化成bit操作，把数字pool看成是二进制，
	数字 i 是available，就把第 i 位bit赋值成1这样才能够用mem进行Pruning*/
		
		int pool = (1 << maxChoosableInteger) - 1;
		Boolean[] mem = new Boolean[pool + 1];
		return dfs(pool, 0, desiredTotal, maxChoosableInteger, mem);
	}
	
	/**
	 * @param pool:               if number i is available, the pool[i] is true
	 * @param curSum:             the current sum
	 * @param desiredTotal:       desired total number
	 * @return: true if win
	 */
	private boolean dfs(int pool, int curSum, int desiredTotal, int maxChoosableInteger,
			Boolean[] mem) {
		// corner case, received curSum from system instead of competitor
		if (desiredTotal == 0) {
			return true;
		}
		// lookup the DP form
		if (mem[pool] != null) {
			return mem[pool];
		}
		// base case
		if (curSum >= desiredTotal) {
			mem[pool] = false;
			return false;
		}
		
		for (int i = 1; i <= maxChoosableInteger; i++) {
			int mask = 1 << (i - 1);
			if ((pool & mask) != 0) {
				int newPool = pool - mask; // newPool = pool ^ mask
				boolean res = dfs(newPool, curSum + i, desiredTotal, maxChoosableInteger, mem);
				if (!res) {
					mem[pool] = true;
					return true;
				}
			}
		}
		mem[pool] = false;
		return false;
	}
}
}