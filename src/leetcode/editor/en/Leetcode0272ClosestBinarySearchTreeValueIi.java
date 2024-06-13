//Given a non-empty binary search tree and a target value, find k values in the 
//BST that are closest to the target. 
//
// Note: 
//
// 
// Given target value is a floating point. 
// You may assume k is always valid, that is: k ≤ total nodes. 
// You are guaranteed to have only one unique set of k values in the BST that ar
//e closest to the target. 
// 
//
// Example: 
//
// 
//Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
//
//    4
//   / \
//  2   5
// / \
//1   3
//
//Output: [4,3] 
//
// Follow up: 
//Assume that the BST is balanced, could you solve it in less than O(n) runtime 
//(where n = total nodes)? 
// Related Topics Stack Tree 
// 👍 615 👎 20

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-14 11:14:06
// Jesse Yang
public class Leetcode0272ClosestBinarySearchTreeValueIi{
    // Java: closest-binary-search-tree-value-ii
    public static void main(String[] args) {
        String str = "4,2,5,1,3,null";
        TreeNode root = TreeGenerator.deserialize(str);
        /*TreeNode node44 = new TreeNode(44);
        TreeNode node38 = new TreeNode(38, null, node44);
        root.right.right.left = node38;*/
        TreeDrawer.draw(root);
        double target = 2;
        int k = 1;
        Solution sol = new Leetcode0272ClosestBinarySearchTreeValueIi().new Solution();
        // TO TEST
        List<Integer> res = sol.closestKValues(root, target, k);
        System.out.printf("找离 %.2f 最近的 %d 个数字:", target, k);
        System.out.println(res);
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
/*
step1 : initialize left and right stack
step2: find the closest number between top of left and right stack,
step3: move the stack between 2 stacks, whose top is closest


初始化left stack和right stack
从上到下到target的这条路径，(如果tree里面不存在target，就是到包含离target最近的那2个点的路径）
<= target的部分，放在left stack里面
> target的部分，放在right stack里面


left每次往左走一格
right每次往右边走一格
谁跟接近target，就update 移动哪个stack

 */
class Solution {
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        initialize2Stacks(root, left, right, target);
        while (k-- > 0) {
            if (!left.isEmpty() && !right.isEmpty()) {
                int leftNum = left.peek().val;
                int rightNum = right.peek().val;
                if (target - leftNum < rightNum - target) {
                    res.add(leftNum);
                    popNextLeft(left);
                } else {
                    res.add(rightNum);
                    popNextRight(right);
                }
            } else if (!left.isEmpty()) {
                int leftNum = popNextLeft(left);
                res.add(leftNum);
            } else {
                int rightNum = popNextRight(right);
                res.add(rightNum);
            }
        }
        return res;
        
    }
    
    private void initialize2Stacks(TreeNode cur, Stack<TreeNode> left, Stack<TreeNode> right,
            double target) {
        while (cur != null) {
            if (cur.val <= target) {
                left.push(cur);
                cur = cur.right;
            } else {
                right.push(cur);
                cur = cur.left;
            }
        }
    }
    
    private int popNextLeft(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        int res = top.val;
        TreeNode cur = top.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return res;
    }
    
    private int popNextRight(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        int res = top.val;
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 2*/

// Solution 1: in order traversal to get a increasing array + binary search, T(n) = O(n + k) = O(n)
// 1 ms,击败了87.23% 的Java用户, 38.9 MB,击败了94.13% 的Java用户
class Solution1 {
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        int len = list.size();
        
        int index = binarySearch(list, target);
        List<Integer> res = new ArrayList<>();
        
        int left = index;
        int right = index + 1;
        while (k -- > 0) {
            if (left >= 0 && right < len) {
                int leftNum = list.get(left);
                int rightNum = list.get(right);
                if (target - leftNum < rightNum - target) {
                    res.add(leftNum);
                    left--;
                } else {
                    res.add(rightNum);
                    right++;
                }
            } else if (left == -1) {
                int rightNum = list.get(right);
                res.add(rightNum);
                right++;
            } else {
                int leftNum = list.get(left);
                res.add(leftNum);
                left--;
            }
        }
        return res;
    }
    
    private int binarySearch(List<Integer> list, double target) {
        int len = list.size();
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cur = list.get(mid);
            if (Math.abs(target - cur) < Math.pow(10, -5)) {
                return mid;
            } else if (target < cur) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right == -1 ? 0 : right;
    }
    
    // head recursion or divide & conquer
    private void inOrder(TreeNode cur, List<Integer> list) {
        // base case
        if (cur == null) {
            return;
        }
        inOrder(cur.left, list);
        list.add(cur.val);
        inOrder(cur.right, list);
    }
    
}

// Solution 2: using stack, T(n) = O(log(n) + k)
// 1 ms,击败了87.23% 的Java用户, 39.3 MB,击败了46.79% 的Java用户
/*
step1: initialize left and right stack
step2: find the closest number between top of left and right stack,
step3: move the stack between 2 stacks, whose top is closest


初始化left stack和right stack
从上到下,到target的这条路径，
(如果tree里面不存在target，就是到包含离target最近的那2个点的路径）
<= target的部分，放在left stack里面
> target的部分，放在right stack里面


left每次往左走一格
right每次往右边走一格
谁跟接近target，就update 移动哪个stack

 */
class Solution2 {
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        initialize2Stacks(root, left, right, target);
        while (k-- > 0) {
            if (!left.isEmpty() && !right.isEmpty()) {
                int leftNum = left.peek().val;
                int rightNum = right.peek().val;
                if (target - leftNum < rightNum - target) {
                    res.add(leftNum);
                    popNextLeft(left);
                } else {
                    res.add(rightNum);
                    popNextRight(right);
                }
            } else if (!left.isEmpty()) {
                int leftNum = popNextLeft(left);
                res.add(leftNum);
            } else {
                int rightNum = popNextRight(right);
                res.add(rightNum);
            }
        }
        return res;
        
    }
    
    /*
    初始化left stack和right stack
    left里面的每个点，表示这个点的和这个点的左子树，所以是存向右走的都>= target的路径
    right里面的每个点，表示这个点的和这个点的右子树，所以是存向左走的都 < target的路径
    从上到下,到target的这条路径，
    (如果tree里面不存在target，就是到包含离target最近的那2个点的路径）
    <= target的部分，放在left stack里面
    > target的部分，放在right stack里面
     */
    private void initialize2Stacks(TreeNode cur, Stack<TreeNode> left, Stack<TreeNode> right,
            double target) {
        while (cur != null) {
            if (cur.val <= target) {
                left.push(cur);
                cur = cur.right;
            } else {
                right.push(cur);
                cur = cur.left;
            }
        }
    }
    
    /*
    找到pop出来的值top之后，要找到比top小的最大值。
    step 1: get top by stack.pop
    step 2: let the cur = top.left, stack.push(cur)
    step 3: continually push(cur.right) and update cur = cur.right;
     */
    private int popNextLeft(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        int res = top.val;
        TreeNode cur = top.left; // 先向左走一格
        while (cur != null) { // 再继续一直向右边走
            stack.push(cur);
            cur = cur.right;
        }
        return res;
    }
    
    private int popNextRight(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        int res = top.val;
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return res;
    }
}

}