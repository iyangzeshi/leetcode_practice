
/**
If the depth of a tree is smaller than 5, then this tree can be represented by 
an array of three-digit integers. For each integer in this array: 

 
 The hundreds digit represents the depth d of this node where 1 <= d <= 4. 
 The tens digit represents the position p of this node in the level it belongs 
to where 1 <= p <= 8. The position is the same as that in a full binary tree. 
 The units digit represents the value v of this node where 0 <= v <= 9. 
 

 Given an array of ascending three-digit integers nums representing a binary 
tree with a depth smaller than 5, return the sum of all paths from the root 
towards the leaves. 

 It is guaranteed that the given array represents a valid connected binary tree.
 

 
 Example 1: 
 
 
Input: nums = [113,215,221]
Output: 12
Explanation: The tree that the list represents is shown.
The path sum is (3 + 5) + (3 + 1) = 12.
 

 Example 2: 
 
 
Input: nums = [113,221]
Output: 4
Explanation: The tree that the list represents is shown. 
The path sum is (3 + 1) = 4.
 

 
 Constraints: 

 
 1 <= nums.length <= 15 
 110 <= nums[i] <= 489 
 nums represents a valid binary tree with depth less than 5. 
 

 Related Topics Array Tree Depth-First Search Binary Tree 👍 319 👎 407

*/
package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2023-02-10 15:33:03
// Jesse Yang
public class Leetcode0666PathSumIv{
    // Java: path-sum-iv
    public static void main(String[] args) {
        Solution sol = new Leetcode0666PathSumIv().new Solution();
        // TO TEST
	    int[] nums = {111,217,221,315,415};
	    int res = sol.pathSum(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n)
/*
step 1: build tree
	parent node[abc], child node [xyz], then
	a + 1 = x,
	2*b - 1 = y or 2*b = y
	build tree by its own index
step 2: calculate path sum
 */
class Solution {
    public int pathSum(int[] nums) {
		// corner case
	    if (nums == null || nums.length == 0) {
			return 0;
	    }
		
	    TreeNode root = buildTree(nums);
		int[] pathSum = {0};
		getPathSum(root, pathSum, 0);
		return pathSum[0];
    }
	
	private TreeNode buildTree(int[] nums) {
		int len = nums.length;
		Map<Integer, TreeNode> idxToTreeNode = new HashMap<>();// key: depth * 10 + position
		TreeNode root = new TreeNode(nums[0] % 10);
		idxToTreeNode.put(11, root);
        
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            int depth = num / 100;
            int pos = num / 10 % 10;
            int val = num % 10;
            TreeNode cur = new TreeNode(val);
            idxToTreeNode.put(depth * 10 + pos, cur);
            
            int parentDep = depth - 1;
            int parentPos = (pos + 1) / 2;
            TreeNode parent = idxToTreeNode.get(parentDep * 10 + parentPos);
            if (pos % 2 == 1) { // left child
                parent.left = cur;
            } else { // right child
                parent.right = cur;
            }
        }
		return root;
	}
	
	/**
	 *
	 * @param cur: current node
	 * @param pathSum: sum of global path sum from root to the current leave node
	 * @param path: the sum of the root until the curret node
	 */
	private void getPathSum(TreeNode cur, int[] pathSum, int path) {
		// base case
		if (cur.left == null && cur.right == null) {
			path += cur.val;
			pathSum[0] += path;
			return;
		}
		
		if (cur.left != null) {
			getPathSum(cur.left, pathSum, path + cur.val);
		}
		if (cur.right != null) {
			getPathSum(cur.right, pathSum, path + cur.val);
		}
	}
	
}

class TreeNode {
	
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int val) {
		this.val = val;
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)

}