//A group of two or more people wants to meet and minimize the total travel dist
//ance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of s
//omeone in the group. The distance is calculated using Manhattan Distance, where 
//distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|. 
//
// Example: 
//
// 
//Input: 
//
//1 - 0 - 0 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//
//Output: 6 
//
//Explanation: Given three people living at (0,0), (0,4), and (2,2):
//             The point (0,2) is an ideal meeting point, as the total travel di
//stance 
//             of 2+2+2=6 is minimal. So return 6. 
// Related Topics Math Sort

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode0296BestMeetingPoint {

	// Java: best-meeting-point
	public static void main(String[] args) {
		Solution sol = new Leetcode0296BestMeetingPoint().new Solution();
		// TO TEST
		int[][] matrix = new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
		int result = sol.minTotalDistance(matrix);
		System.out.println(result);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	// time = O(m * n), space = O（m * n)
	public int minTotalDistance(int[][] grid) {
		// corner case
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
		
		int rows = grid.length;
		int cols = grid[0].length;
		List<Integer> xList = new ArrayList<>();
		List<Integer> yList = new ArrayList<>();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 1) {
					xList.add(i);
				}
			}
		}
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (grid[j][i] == 1) {
					yList.add(i);
				}
			}
		}
		return getMD(xList) + getMD(yList);
	}
	
	private int getMD(List<Integer> list) {
		int i = 0;
		int j = list.size() - 1;
		int dist = 0;
		while (i < j) {
			dist += list.get(j--) - list.get(i++);
		}
		return dist;
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: BFS, time limit exceed.O(m^2 * n^2)
class Solution1 {

	private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

	public int minTotalDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return -1;
		}
		int rows = grid.length;
		int cols = grid[0].length;

		ArrayList<Integer> ones = get1(grid);

		Queue<Integer> queue = new LinkedList<>();
		int[][] distance = new int[rows][cols];
		for (int num : ones) {
			bfs(grid, queue, distance, num);
		}
		return getMin(distance);
	}

	private void bfs(int[][] grid, Queue<Integer> queue, int[][] distance, int num) {
		int rows = grid.length;
		int cols = grid[0].length;
		queue.offer(num);
		int minLen = 1;
		boolean[][] visited = new boolean[rows][cols];
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int cur = queue.poll();
				int row = cur / cols;
				int col = cur % cols;
				visited[row][col] = true;
				for (int[] dir : DIRECTIONS) {
					int i = row + dir[0];
					int j = col + dir[1];
					if (i >= 0 && i < rows && j >= 0 && j < cols && !visited[i][j]) {
						queue.offer(i * cols + j);
						visited[i][j] = true;
						distance[i][j] += minLen;
					}
				}
			}
			minLen++;
		}
	}

	private ArrayList<Integer> get1(int[][] matrix) {
		ArrayList<Integer> ones = new ArrayList<>();
		int rows = matrix.length;
		int cols = matrix[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 1) {
					ones.add(i * cols + j);
				}
			}
		}
		return ones;
	}

	private int getMin(int[][] matrix) {
		int min = Integer.MAX_VALUE;
		for (int[] i : matrix) {
			for (int j : i) {
				min = Math.min(j, min);
			}
		}
		return min;
	}
}

// Solution 2:数学解
// T(m,n) = O(mn), S(m,n) = O(mn)
/**
 * 数学解，遍历所有点，根据绝对值，找到x坐标和y坐标的中位数。
 * (xMedian, yMedian)这个中位数就是地址，找到之后和每个点算距离，相加.
 */
class Solution2_1 {
	
	public int minTotalDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return -1;
		}
		
		int rows = grid.length;
		int cols = grid[0].length;
		
		List<Integer> xList = new ArrayList<>();
		List<Integer> yList = new ArrayList<>();
		
		// 为什么traverse两遍：让xList和yList分别都是sorted的
		// 取中位数直接get(size / 2)就可以了
		fillXOrYList(grid, xList, true);
		fillXOrYList(grid, yList, false);
		if (xList.size() == 0) {
			return -1;
		}
		
		return medianDis(xList) + medianDis(yList);
	}
	
	private void fillXOrYList(int[][] grid, List<Integer> list, boolean XOrY) {
		int rows = grid.length;
		int cols = grid[0].length;
		if (XOrY) { // add to xList
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (grid[i][j] == 1) {
						list.add(i);
					}
				}
			}
		} else { // add to yList
			for (int j = 0; j < cols; j++) {
				for (int i = 0; i < rows; i++) {
					if (grid[i][j] == 1) {
						list.add(j);
					}
				}
			}
		}
		
	}
	
	private int medianDis(List<Integer> list) {
		int len = list.size();
		int median = list.get(len / 2);
		int sum = 0;
		for (int i : list) {
			sum += Math.abs(i - median);
		}
		return sum;
	}
}

// Solution 2_2:
// T(m,n) = O(mn), S(m,n) = O(mn)

/**
 * 算出横坐标的投影的xList，纵坐标投影给yList，变成一维的meeting问题，绝对值方法来解
 * 分别计算出两个List从结尾走到中间的距离，绝对值问题，两个list各自从两边往中间走
 */
class Solution2_2 {

	// time = O(m * n), space = O（m * n)
	public int minTotalDistance(int[][] grid) {
		// corner case
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}

		int rows = grid.length, cols = grid[0].length;
		List<Integer> xList = new ArrayList<>();
		List<Integer> yList = new ArrayList<>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 1) {
					xList.add(i);
				}
			}
		}
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (grid[j][i] == 1) {
					yList.add(i);
				}
			}
		}
		return getMD(xList) + getMD(yList);
	}

	private int getMD(List<Integer> list) {
		int i = 0;
		int j = list.size() - 1;
		int dist = 0;
		while (i < j) {
			dist += list.get(j--) - list.get(i++);
		}
		return dist;
	}

}

}