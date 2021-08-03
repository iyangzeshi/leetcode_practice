//Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge 
//is a pair of nodes), write a function to check whether these edges make up a val
//id tree. 
//
// Example 1: 
//
// 
//Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
//Output: true 
//
// Example 2: 
//
// 
//Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
//Output: false 
//
// Note: you can assume that no duplicate edges will appear in edges. Since all 
//edges are undirected, [0,1] is the same as [1,0] and thus will not appear togeth
//er in edges. 
// Related Topics Depth-first Search Breadth-first Search Union Find Graph 
// 👍 1011 👎 35

package leetcode.editor.en;

// 2020-07-21 13:38:51
// Zeshi Yang
public class Leetcode0261GraphValidTree {

	// Java: graph-valid-tree
	public static void main(String[] args) {
		Solution sol = new Leetcode0261GraphValidTree().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
class UnionFind {

    private int numOfSets;
    private int[] parent;
    private int[] size;

    public UnionFind(int n) {
        numOfSets = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (size[rootP] < size[rootQ]) { // rootP -> rootQ
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else { // rootQ -> rootP
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        this.numOfSets--;
    }

    public boolean find(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    private int findRoot(int i) {
        int cur = i;
        while (cur != parent[cur]) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        parent[i] = cur;
        return cur;
    }

    public int getNumOfSets() {
        return numOfSets;
    }
}

class Solution {

    public boolean validTree(int n, int[][] edges) {
        // corner case
        if (edges == null || edges.length != n - 1) {
            return false;
        }

        // general case
        UnionFind uf = new UnionFind(n);

        for (int[] edge: edges) {
            int p = edge[0];
            int q = edge[1];
            if (!uf.find(p, q)) {
                uf.union(p, q);
            } else {
                return false;
            }
        }
        return uf.getNumOfSets() == 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}