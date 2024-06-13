
//There is a fence with n posts, each post can be painted with one of the k colo
//rs. 
//
// You have to paint all the posts such that no more than two adjacent fence pos
//ts have the same color. 
//
// Return the total number of ways you can paint the fence. 
//
// Note: 
//n and k are non-negative integers. 
//
// Example: 
//
// 
//Input: n = 3, k = 2
//Output: 6
//Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
//
//            post1  post2  post3      
// -----      -----  -----  -----       
//   1         c1     c1     c2 
//   2         c1     c2     c1 
//   3         c1     c2     c2 
//   4         c2     c1     c1  
//   5         c2     c1     c2
//   6         c2     c2     c1
// 
// Related Topics Dynamic Programming 
// 👍 795 👎 256

package leetcode.editor.en;

// 2020-11-09 14:09:02
// Jesse Yang
public class Leetcode0276PaintFence{
    // Java: paint-fence
    public static void main(String[] args) {
        Solution sol = new Leetcode0276PaintFence().new Solution();
        // TO TEST
        int n = 2;
        int k = 1;
        int res = sol.numWays(n ,k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numWays(int n, int k) {
        // corner case
        if (n == 0 | k == 0) {
            return 0;
        }
        
        int preSame = 0; // # of methods that satisfy color[prevHouse] == color[prevHouse - 1]
        int preDiff = k; // # of methods that satisfy color[prevHouse] == color[prevHouse - 1]
        int curSame = 0;
        int curDiff = k;
        for (int i = 1; i < n; i++) {
            curSame = preDiff;
            curDiff = (preSame + preDiff) * (k - 1);
            preSame = curSame;
            preDiff = curDiff;
        }
        return curSame + curDiff;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*
 思路分析
 分为两种情况讨论
     same数组表示从头开始涂到当前房子，而且当前房子和前面房子颜色一样的方案个数
     diff数组表示从头开始涂到当前房子，而且当前房子和前面房子颜色不一样的方案个数
     结尾的时候，两个加起来就行了
 */
// Solution 1: DFS with memory, T(n) = O(n), S(n) = O(n)
class Solution1 {
    public int numWays(int n, int k) {
        // corner case
        if (n == 0 | k == 0) {
            return 0;
        }
        Integer[] same = new Integer[n];
        Integer[] diff = new Integer[n];
        return dfs(true, n, k, same, diff) + dfs(false, n, k, same, diff);
    }
    
    /**
     *
     * @param isSame: whether current color is same as color of previous house
     * @param n: number of house
     * @param k: number of colors
     * @param same: number of color from house 0 to current house and satisfy nums[i - 1] == nums[i]
     * @param diff: number of color from house 0 to current house and satisfy nums[i - 1] ！= nums[i]
     * @return: number of ways to paint from house 0 to current house according to satisfy isSame
     */
    private int dfs(boolean isSame, int n, int k, Integer[] same, Integer[] diff) {
        // base case
        if (n == 0) {
            if(isSame) {
                return 0;
            } else {
                return k;
            }
        }
        int res;
        if (isSame) {
            if (same[n] != null) {
                return same[n];
            }
            res = dfs(false, n - 1, k, same, diff);
            same[n] = res;
        } else {
            if (diff[n] != null) {
                return diff[n];
            }
            res = dfs(true, n - 1, k, same, diff) * (k - 1) + dfs(false, n - 1, k, same, diff);
            diff[n] = res;
        }
        return res;
    }
}

// Solution 2_1: DP with 1D array, T(n) = O(n), S(n) = O(n)
class Solution2_1 {
    public int numWays(int n, int k) {
        // corner case
        if (n == 0 | k == 0) {
            return 0;
        }
        
        int[] same = new int[n];// # of methods that satisfy color[house - 1] == color[house]
        int[] diff = new int[n];// # of methods that satisfy color[house - 1] != color[house]
        same[0] = 0;
        diff[0] = k;
        for (int i = 1; i < n; i++) {
            same[i] = diff[i - 1];
            diff[i] = same[i - 1] * (k - 1) + diff[i - 1] * (k - 1);
        }
        return same[n - 1] + diff[n - 1];
    }
}

// Solution 2_2: DP with int, T(n) = O(n), S(n) = O(1)
class Solution2_2 {
    public int numWays(int n, int k) {
        // corner case
        if (n == 0 | k == 0) {
            return 0;
        }
        
        int preSame = 0; // # of methods that satisfy color[prevHouse] == color[prevHouse - 1]
        int preDiff = k; // # of methods that satisfy color[prevHouse] == color[prevHouse - 1]
        int curSame = 0;
        int curDiff = k;
        for (int i = 1; i < n; i++) {
            curSame = preDiff;
            curDiff = (preSame + preDiff) * (k - 1);
            preSame = curSame;
            preDiff = curDiff;
        }
        return curSame + curDiff;
    }
}
}