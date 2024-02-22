//You are given a list of bombs. The range of a bomb is defined as the area 
//where its effect can be felt. This area is in the shape of a circle with the center 
//as the location of the bomb. 
//
// The bombs are represented by a 0-indexed 2D integer array bombs where bombs[
//i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the 
//location of the iᵗʰ bomb, whereas ri denotes the radius of its range. 
//
// You may choose to detonate a single bomb. When a bomb is detonated, it will 
//detonate all bombs that lie in its range. These bombs will further detonate the 
//bombs that lie in their ranges. 
//
// Given the list of bombs, return the maximum number of bombs that can be 
//detonated if you are allowed to detonate only one bomb. 
//
// 
// Example 1: 
// 
// 
//Input: bombs = [[2,1,3],[6,1,4]]
//Output: 2
//Explanation:
//The above figure shows the positions and ranges of the 2 bombs.
//If we detonate the left bomb, the right bomb will not be affected.
//But if we detonate the right bomb, both bombs will be detonated.
//So the maximum bombs that can be detonated is max(1, 2) = 2.
// 
//
// Example 2: 
// 
// 
//Input: bombs = [[1,1,5],[10,10,5]]
//Output: 1
//Explanation:
//Detonating either bomb will not detonate the other bomb, so the maximum 
//number of bombs that can be detonated is 1.
// 
//
// Example 3: 
// 
// 
//Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
//Output: 5
//Explanation:
//The best bomb to detonate is bomb 0 because:
//- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
//- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
//- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
//Thus all 5 bombs are detonated.
// 
//
// 
// Constraints: 
//
// 
// 1 <= bombs.length <= 100 
// bombs[i].length == 3 
// 1 <= xi, yi, ri <= 10⁵ 
// 
//
// Related Topics Array Math Depth-First Search Breadth-First Search Graph 
//Geometry 👍 2920 👎 144

package leetcode.editor.en;

import java.util.Arrays;

// 2024-02-21 21:50:16
// Zeshi(Jesse) Yang
public class Leetcode2101DetonateTheMaximumBombs{
    // Java: detonate-the-maximum-bombs
    public static void main(String[] args) {
        Solution sol = new Leetcode2101DetonateTheMaximumBombs().new Solution();
        // TO TEST
        int[][] bombs = {
                {1,1,100000},
                {100000,100000,1}/*,
                {3,4,2},
                {4,5,3},
                {5,6,4}*/
        };
        int res = sol.maximumDetonation(bombs);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Union-Find to check connected T(n) = O(n^2lg(n)), S(n) = O(n)
class Solution {
    public int maximumDetonation(int[][] bombs) {
        int len = bombs.length;
        // corner case
        if (len == 1) {
            return len;
        }
        UnionFind unionFind = new UnionFind(len);
        int curMax = 1;
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                long distSquare = (long) Math.pow(bombs[i][0] - bombs[j][0], 2)
                        + (long) Math.pow(bombs[i][1] - bombs[j][1], 2);
                if (distSquare <= (long) Math.pow(bombs[i][2], 2)
                        || distSquare <= (long) Math.pow(bombs[j][2], 2)){
                    curMax = Math.max(curMax, unionFind.union(i, j));
                }
            }
        }
        return curMax;
    }
    
    final class UnionFind{
        private int[] parent;
        private int[] size;
        
        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.size = new int[n];
            Arrays.fill(this.size, 1);
        }
        
        private int findRoot(int p) {
            // base case
            if (parent[p] == p) {
                return p;
            }
            
            // general case
            parent[p] = findRoot(parent[p]);
            return parent[p];
        }
        
        public boolean find(int p, int q) {
            return findRoot(p) == findRoot(q);
        }
        
        public int union(int p, int q) {
            int rootP = findRoot(p);
            int rootQ = findRoot(q);
            if (rootP == rootQ) {
                return size[p];
            }
            
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
                return size[rootQ];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
                return size[rootP];
            }
            
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}