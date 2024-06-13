//You are given a binary tree in which each node contains an integer value. 
//
// Find the number of paths that sum to a given value. 
//
// The path does not need to start or end at the root or a leaf, but it must go 
//downwards
//(traveling only from parent nodes to child nodes). 
//
// The tree has no more than 1,000 nodes and the values are in the range -1,000,
//000 to 1,000,000.
//
// Example:
// 
//root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//      10
//     /  \
//    5   -3
//   / \    \
//  3   2   11
// / \   \
//3  -2   1
//
//Return 3. The paths that sum to 8 are:
//
//1.  5 -> 3
//2.  5 -> 2 -> 1
//3. -3 -> 11
// 
// Related Topics Tree 
// 👍 3528 👎 302

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
import leetcode.editor.TreeNode;

// 2020-07-26 13:08:30
// Jesse Yang
public class Leetcode0437PathSumIii{
    // Java: path-sum-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0437PathSumIii().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> h = new HashMap<>();
        int[] count = {0};
        preorder(root, 0, sum, count, h);
        return count[0];
    }
    
    /**
     *
     * @param curSum: sum from root to current node
     * @param target: target sum
     * @param count: count of result
     * @param preSum: preSum map, record the key - preSum (in the path from root to current node)
     *              , and value - its count
     */
    private void preorder(TreeNode node, int curSum, int target, int[] count, Map<Integer, Integer> preSum) {
        if (node == null)
            return;
        
        // current prefix sum
        curSum += node.val;
        
        // here is the sum we're looking for
        if (curSum == target)
            count[0]++;
        
        // add the count of sum ends with current node
        count[0] += preSum.getOrDefault(curSum - target, 0);
        
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        
        // process left subtree
        preorder(node.left, curSum, target, count, preSum);
        // process right subtree
        preorder(node.right, curSum, target, count, preSum);
        
        /*
         remove the current sum from the hashmap
         in order not to use it during the parallel subtree processing
        */
        preSum.put(curSum, preSum.get(curSum) - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: T(n) = O(n^2), S(n) = O(n)
// 21 ms,击败了37.70% 的Java用户, 38.7 MB,击败了73.84% 的Java用户
/*
以每个点为起点，找以当前点为root的subtree里面，从root开始的path sum = target的个数
把所有的结果求和
 */
class Solution1 {
    
    public int pathSum(TreeNode root, int sum) {
        //corner case
        if (root == null) {
            return 0;
        }
        int fromCurrent = pathSumFrom(root, sum);
        
        int fromLeft = pathSum(root.left, sum);
        int fromRight = pathSum(root.right, sum);
        
        return fromCurrent + fromLeft + fromRight;
    }
    
    private int pathSumFrom(TreeNode root, int sum) {
        //base case
        if (root == null) {
            return 0;
        }
        //general case
        /*if (root.val == sum) {
            return 1 + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
        } else {
            return pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
        }*/
        //上面的可以简写成下面一行
        return (root.val == sum ? 1: 0) + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
    }
}

// Solution 2: T(n) = O(n), S(n) = O(n)
// 2 ms,击败了100.00% 的Java用户, 38.7 MB,击败了60.31% 的Java用户
/*
HashMap存每个点到root的sum的path，
这样计算path sum的时候，
不用从每个点开始重新遍历。直接用preSum减就行了
 */
class Solution2 {
    
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> h = new HashMap<>();
        int[] count = {0};
        preorder(root, 0, sum, count, h);
        return count[0];
    }
    
    /**
     *
     * @param curSum: sum from root to current node
     * @param target: target sum
     * @param count: count of result
     * @param preSum: preSum map, record the key - preSum (in the path from root to current node)
     *              , and value - its count
     */
    private void preorder(TreeNode node, int curSum, int target, int[] count, Map<Integer, Integer> preSum) {
        if (node == null)
            return;
        
        // current prefix sum
        curSum += node.val;
        
        // here is the sum we're looking for
        if (curSum == target)
            count[0]++;
        
        // add the count of sum ends with current node
        count[0] += preSum.getOrDefault(curSum - target, 0);
        
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        
        // process left subtree
        preorder(node.left, curSum, target, count, preSum);
        // process right subtree
        preorder(node.right, curSum, target, count, preSum);
        
        /*
         remove the current sum from the hashmap
         in order not to use it during the parallel subtree processing
        */
        preSum.put(curSum, preSum.get(curSum) - 1);
    }
}

}