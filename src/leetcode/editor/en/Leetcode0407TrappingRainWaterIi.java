//Given an m x n matrix of positive integers representing the height of each uni
//t cell in a 2D elevation map, compute the volume of water it is able to trap aft
//er raining. 
//
// Example: 
//
// 
//Given the following 3x6 height map:
//[
//  [1,4,3,1,3,2],
//  [3,2,1,3,2,4],
//  [2,3,3,2,3,1]
//]
//
//Return 4.
// 
//
// 
//
// The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,
//3,3,2,3,1]] before the rain. 
//
// 
//
// 
//
// After the rain, water is trapped between the blocks. The total volume of wate
//r trapped is 4. 
//
// 
// Constraints: 
//
// 
// 1 <= m, n <= 110 
// 0 <= heightMap[i][j] <= 20000 
// 
// Related Topics Heap Breadth-first Search 
// 👍 1504 👎 34

package leetcode.editor.en;

import java.util.PriorityQueue;

// 2020-10-18 21:21:32
// Jesse Yang
public class Leetcode0407TrappingRainWaterIi {
	
	// Java: trapping-rain-water-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0407TrappingRainWaterIi().new Solution();
		// TO TEST
		int[][] heightMap = {
				{12, 13, 1, 12},
				{13, 4, 13, 12},
				{13, 8, 10, 12},
				{12, 13, 12, 12},
				{13, 13, 13, 13}
		};
		int res = sol.trapRainWater(heightMap);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
// BFS, T(m, n) = O(m * n * log(mn))
// 必须从外面开始往里面做BFS找围墙的最小值，因为有可能是北京火锅那种形状
/*
木桶原理，从最低的外边界开始往里面走，看它的邻居能装多少税
我们再想想，怎么样可以成功的装进去水呢，是不是周围的高度都应该比当前的高度高，形成一个凹槽才能装水，
而且装水量取决于周围最小的那个高度，有点像木桶原理的感觉，那么为了模拟这种方法，我们采用模拟海平面上升的方法来做，
 */
class Solution {
	
	private final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}}; // 上下左右
	
	public int trapRainWater(int[][] heightMap) {
		// corner case
		if (heightMap == null || heightMap.length == 0 || heightMap[0] == null
				|| heightMap[0].length == 0) {
			return 0;
		}
		int rows = heightMap.length;
		int cols = heightMap[0].length;
		PriorityQueue<Cell> minHeap = new PriorityQueue<>();
		boolean[][] visited = new boolean[rows][cols];
		traversePeriphery(heightMap, minHeap, visited);
		int water = 0;
		while (!minHeap.isEmpty()) {
			Cell cur = minHeap.poll();
			for (int[] dir : DIRECTIONS) {
				int row = cur.row + dir[0];
				int col = cur.col + dir[1];
				if (row > 0 && row < rows - 1 && col > 0 && col < cols - 1
						&& !visited[row][col]) {
					visited[row][col] = true;
					int waterDelta = Math.max(0, cur.height - heightMap[row][col]);
					water += waterDelta;
					minHeap.offer(new Cell(row, col, Math.max(cur.height, heightMap[row][col])));
				}
			}
		}
		return water;
	}
	
	private void traversePeriphery(int[][] heightMap, PriorityQueue<Cell> minHeap,
			boolean[][] visited) {
		int rows = heightMap.length;
		int cols = heightMap[0].length;
		// traverse up and down periphery
		for (int i = 0; i < cols; i++) {
			visited[0][i] = true;
			visited[rows - 1][i] = true;
			Cell cell = new Cell(0, i, heightMap[0][i]);
			minHeap.offer(cell);
			minHeap.offer(new Cell(rows - 1, i, heightMap[rows - 1][i]));
		}
		// traverse left and right periphery
		for (int i = 1; i < rows - 1; i++) {
			visited[i][0] = true;
			visited[i][cols - 1] = true;
			minHeap.offer(new Cell(i, 0, heightMap[i][0]));
			minHeap.offer(new Cell(i, cols - 1, heightMap[i][cols - 1]));
		}
	}
	
	private class Cell implements Comparable<Cell> {
		int row;
		int col;
		
		int height;
		
		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
		@Override
		public int compareTo(Cell o) {
			return Integer.compare(this.height, o.height);
		}
		
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}