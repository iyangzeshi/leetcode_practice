//A 2d grid map of m rows and n columns is initially filled with water. We may
//perform an addLand operation which turns the water at position (row, col) into a
//land. Given a list of positions to operate, count the number of islands after ea
//ch addLand operation. An island is surrounded by water and is formed by connecting
// adjacent lands horizontally or vertically. You may assume all four edges of t
//he grid are all surrounded by water. 
//
// Example: 
//
// 
//Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
//Output: [1,1,2,3]
// 
//
// Explanation: 
//
// Initially, the 2d grid grid is filled with water. (Assume 0 represents water 
//and 1 represents land). 
//
// 
//0 0 0
//0 0 0
//0 0 0
// 
//
// Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. 
//
// 
//1 0 0
//0 0 0   Number of islands = 1
//0 0 0
// 
//
// Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. 
//
// 
//1 1 0
//0 0 0   Number of islands = 1
//0 0 0
// 
//
// Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. 
//
// 
//1 1 0
//0 0 1   Number of islands = 2
//0 0 0
// 
//
// Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. 
//
// 
//1 1 0
//0 0 1   Number of islands = 3
//0 1 0
// 
//
// Follow up: 
//
// Can you do it in time complexity O(k log mn), where k is the length of the
//positions?
// Related Topics Union Find 
// 👍 839 👎 21

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// 2020-07-20 23:31:38
// Zeshi Yang
public class Leetcode0305NumberOfIslandsIi {

    // Java: number-of-islands-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0305NumberOfIslandsIi().new Solution();
        // TO TEST
        int m = 3;
        int n = 3;
        int[][] positions = new int[][]{{0, 0}, {0, 1}, {1, 2}, {1, 2}};
        List<Integer> res = sol.numIslands2(m, n, positions);
        System.out.println(res);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		// corner case
		List<Integer> res = new LinkedList<>();
		UnionFind<Integer> uf = new UnionFind<>(m * n);
		for (int[] position : positions) {
			int row = position[0];
			int col = position[1];
			int idx = row * n + col;
			uf.addUnion(idx);
			for (int[] dir : DIRECTIONS) {
				int r = row + dir[0];
				int c = col + dir[1];
				int neighbor = r * n + c;
				if (r >= 0 && r < m && c >= 0 && c < n && uf.isUnion(neighbor)) {
					if (!uf.find(idx, neighbor)) {
						uf.union(idx, neighbor);
					}
				}
			}
			res.add(uf.getNumOfUnions());
		}
		return res;
	}
	
	public final class UnionFind<K> {
		
		private final Map<K, K> parent; // root -> root
		private final Map<K, Integer> size; // the size of subtree of Character
		private int numOfUnions;
		
		public UnionFind() {
			this(0);
		}
		
		public UnionFind(int n) {
			parent = new HashMap<>(n);
			size = new HashMap<>(n);
			numOfUnions = 0;
		}
		
		private K findRoot(K p) {
			// corner case, base case
			if (!parent.containsKey(p) || parent.get(p) == p) { // union 里面不存在这个点
				if (!parent.containsKey(p)) {
					parent.put(p, p);
					size.put(p, 1);
				}
				return p;
			}
			
			parent.put(p, findRoot(parent.get(p)));
			return parent.get(p);
		}
		
		public boolean find(K p, K q) {
			return findRoot(p) == findRoot(q);
		}
		
		public void union(K p, K q) {
			K rootP = findRoot(p);
			K rootQ = findRoot(q);
			if (rootP == rootQ) {
				return;
			}
			if (size.get(rootP) < size.get(rootQ)) { // p -> q
				parent.put(rootP, rootQ);
				size.put(rootQ, size.get(rootQ) + size.get(rootP));
			} else { // q -> p
				parent.put(rootQ, rootP);
				size.put(rootP, size.get(rootQ) + size.get(rootP));
			}
			this.numOfUnions--;
		}
		
		public void addUnion(K p) {
			if (!parent.containsKey(p)) {
				parent.put(p, p);
				size.put(p, 1);
				this.numOfUnions++;
			}
		}
		
		public boolean isUnion(K p) {
			return parent.containsKey(p);
		}
		
		public int getNumOfUnions() {
			return this.numOfUnions;
		}
		
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: Union find implemented by array, T(r, c, n) = O(
// 9 ms,击败了70.95% 的Java用户, 42.9 MB,击败了84.53% 的Java用户
class Solution1 {
	
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		// corner case
		List<Integer> res = new LinkedList<>();
		UnionFind uf = new UnionFind(m * n);
		for (int[] position : positions) {
			int row = position[0];
			int col = position[1];
			int idx = row * n + col;
			uf.addUnion(idx);
			for (int[] dir : DIRECTIONS) {
				int r = row + dir[0];
				int c = col + dir[1];
				int neighbor = r * n + c;
				if (r >= 0 && r < m && c >= 0 && c < n && uf.isUnion(neighbor)) {
					if (!uf.find(idx, neighbor)) {
						uf.union(idx, neighbor);
					}
				}
			}
			res.add(uf.getNumOfUnions());
		}
		return res;
	}
	
	class UnionFind {
		
		private int[] parent;
		private int[] size;
		private int numOfUnions;
		
		public UnionFind(int n) {
			this.parent = new int[n];
			Arrays.fill(parent, -1); // 有可能会在某个点重复设置为陆地， -1表示还没设置过为陆地
			this.size = new int[n];
			numOfUnions = 0;
		}
		
		private int findRoot(int p) {
			/*int cur = p;
			while (cur != parent[cur]) {
				parent[cur] = parent[parent[cur]];
				cur = parent[cur];
			}
			parent[p] = cur;
			return cur;*/
			// base case
			if (parent[p] == p) {
				return p;
			}
			
			parent[p] = findRoot(parent[p]);
			return parent[p];
		}
		
		public boolean find(int p, int q) {
			return findRoot(p) == findRoot(q);
		}
		
		public void union(int p, int q) {
			int rootP = findRoot(p);
			int rootQ = findRoot(q);
			if (size[rootP] < size[rootQ]) { // p -> q
				parent[rootP] = rootQ;
				size[rootQ] += size[rootP];
			} else { // q -> p
				parent[rootQ] = rootP;
				size[rootP] += size[rootQ];
			}
			this.numOfUnions--;
		}
		
		public void addUnion(int p) { // 有可能会在某个点重复设置为陆地， -1表示还没设置过为陆地
			if (parent[p] == -1) {
				parent[p] = p;
				size[p] = 1;
				this.numOfUnions++;
			}
		}
		
		private boolean isUnion(int p) {
			return size[p] > 0;
		}
		
		public int getNumOfUnions() {
			return this.numOfUnions;
		}
		
	}
	
}

// Solution 2: Union find implemented by hashMap
// 31 ms,击败了24.70% 的Java用户, 47.7 MB,击败了14.04% 的Java用户
class Solution2 {
	
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		// corner case
		List<Integer> res = new LinkedList<>();
		UnionFind<Integer> uf = new UnionFind<>(m * n);
		for (int[] position : positions) {
			int row = position[0];
			int col = position[1];
			int idx = row * n + col;
			uf.addUnion(idx);
			for (int[] dir : DIRECTIONS) {
				int r = row + dir[0];
				int c = col + dir[1];
				int neighbor = r * n + c;
				if (r >= 0 && r < m && c >= 0 && c < n && uf.isUnion(neighbor)) {
					if (!uf.find(idx, neighbor)) {
						uf.union(idx, neighbor);
					}
				}
			}
			res.add(uf.getNumOfUnions());
		}
		return res;
	}
	
	public final class UnionFind<K> {
		
		private final Map<K, K> parent; // root -> root
		private final Map<K, Integer> size; // the size of subtree of Character
		private int numOfUnions;
		
		public UnionFind() {
			this(0);
		}
		
		public UnionFind(int n) {
			parent = new HashMap<>(n);
			size = new HashMap<>(n);
			numOfUnions = 0;
		}
		
		private K findRoot(K p) {
			// corner case, base case
			if (!parent.containsKey(p) || parent.get(p) == p) { // union 里面不存在这个点
				if (!parent.containsKey(p)) {
					parent.put(p, p);
					size.put(p, 1);
				}
				return p;
			}
			
			parent.put(p, findRoot(parent.get(p)));
			return parent.get(p);
		}
		
		public boolean find(K p, K q) {
			return findRoot(p) == findRoot(q);
		}
		
		public void union(K p, K q) {
			K rootP = findRoot(p);
			K rootQ = findRoot(q);
			if (rootP == rootQ) {
				return;
			}
			if (size.get(rootP) < size.get(rootQ)) { // p -> q
				parent.put(rootP, rootQ);
				size.put(rootQ, size.get(rootQ) + size.get(rootP));
			} else { // q -> p
				parent.put(rootQ, rootP);
				size.put(rootP, size.get(rootQ) + size.get(rootP));
			}
			this.numOfUnions--;
		}
		
		public void addUnion(K p) {
			if (!parent.containsKey(p)) {
				parent.put(p, p);
				size.put(p, 1);
				this.numOfUnions++;
			}
		}
		
		public boolean isUnion(K p) {
			return parent.containsKey(p);
		}
		
		public int getNumOfUnions() {
			return this.numOfUnions;
		}
		
	}
	
}

}