//Given an integer n, generate all structurally unique BST's (binary search tree
//s) that store values 1 ... n. 
//
// Example: 
//
// 
//Input: 3
//Output:
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//Explanation:
//The above output corresponds to the 5 unique BST's shown below:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// 
//
// 
// Constraints: 
//
// 
// 0 <= n <= 8 
// 
// Related Topics Dynamic Programming Tree 
// 👍 2177 👎 159

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeNode;

// 2020-07-08 17:59:08
public class Leetcode0095UniqueBinarySearchTreesIi {
    
    // Java: unique-binary-search-trees-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0095UniqueBinarySearchTreesIi().new Solution();
        // TO TEST
        int n = 3;
        List<TreeNode> res = sol.generateTrees(n);
        for (TreeNode node: res) {
            TreeDrawer.draw(node);
        }
    }
    
    
//leetcode submit region begin(Prohibit modification and deletion)
/*	class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}*/
class Solution {
    
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        return dfs(1, n);
    }
    
    private List<TreeNode> dfs(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = dfs(start, i - 1);
            List<TreeNode> right = dfs(i + 1, end);
            
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
    
// follow up 1, return the number of the targeted Lists
class Followup1 {
    
    private int dfs1(int diff, int[] dp) {
        // base case
        if (diff <= 0) {
            return 1;
        }
        // lookup the form
        if (dp[diff] != 0) {
            return dp[diff];
        }
        // general case
        int cnt = 0;
        for (int l = 0; l <= diff; l++) {
            int left = dfs1(l, dp);
            int right = dfs1(diff - l - 1, dp); // root 占用一个, left占用l + 1个,所以剩下diff + 1 - (l + 1)个
            cnt += left * right;
        }
        dp[diff] = cnt;
        return cnt;
    }
    
}

// follow up 2, if every TreeNode has 0 children of 2 children,
// return the targeted Lists
class Followup2 {
    
    private List<TreeNode> dfs2(int n) {
        List<TreeNode> res = new LinkedList<>();
        // base case
        if (n == 0) {
            return res;
        }
        if (n == 1) {
            res.add(new TreeNode());
            return new ArrayList<>(Arrays.asList(new TreeNode(1)));
        }
        for (int i = 0; i < n; i++) {
            List<TreeNode> left = dfs2(i);
            List<TreeNode> right = dfs2(n - i);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }
    
}
}