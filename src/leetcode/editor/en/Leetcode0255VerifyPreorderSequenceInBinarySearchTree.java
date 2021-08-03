//Given an array of numbers, verify whether it is the correct preorder traversal
// sequence of a binary search tree. 
//
// You may assume each number in the sequence is unique. 
//
// Consider the following binary search tree: 
//
// 
//     5
//    / \
//   2   6
//  / \
// 1   3 
//
// Example 1: 
//
// 
//Input: [5,2,6,1,3]
//Output: false 
//
// Example 2: 
//
// 
//Input: [5,2,1,3,6]
//Output: true 
//
// Follow up: 
//Could you do it using only constant space complexity? 
// Related Topics Stack Tree 
// 👍 607 👎 52

package leetcode.editor.en;

import java.util.Stack;

// 2020-08-13 20:04:08
// Zeshi Yang
public class Leetcode0255VerifyPreorderSequenceInBinarySearchTree{
    // Java: verify-preorder-sequence-in-binary-search-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0255VerifyPreorderSequenceInBinarySearchTree().new Solution();
        // TO TEST
        int[] preorder = {2,3,1};
        boolean res = sol.verifyPreorder(preorder);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        // corner case
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        // general case
        Stack<Integer> stack = new Stack<>();
        int lowerBound = Integer.MIN_VALUE;

        for (int num: preorder) {
            if (num < lowerBound) {
                return false;
            }
            while (!stack.isEmpty() && num > stack.peek()) {
                lowerBound = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


// Solution 1: 527 ms, O(n^2) brute force, from right subtree, every element is larger than root
class Solution1 {
    public boolean verifyPreorder(int[] preorder) {
        // corner case
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        // general case
        int len = preorder.length;
        for (int i = 0; i < len; i++) {
            int cur = preorder[i];
            boolean reachRight = false;
            for (int j = i + 1; j < len; j++) {
                if (preorder[j] < preorder[i]) {
                    if (reachRight) {
                        return false;
                    }
                } else {
                    reachRight = true;
                }
            }
        }
        return true;
    }
}


// Solution 2: 15 ms, O(n^2), average O(n), recursion
class Solution2 {
    public boolean verifyPreorder(int[] preorder) {
        // corner case
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        // general case
        int len = preorder.length;
        return verifyPreorder(0, len - 1, null, null, preorder);
    }

    private boolean verifyPreorder(int start, int end, Integer lowerBound, Integer upperBound, int[] nums) {
        int len = nums.length;
        // base case
        if (start > end || start == len) {
            return true;
        }
        if (lowerBound != null && nums[start] < lowerBound) {
            return false;
        }
        if (upperBound != null && nums[start] > upperBound) {
            return false;
        }
        if (start == end) {
            return true;
        }
//        Integer rightStart = end + 1; // the start index of right subtree
//        for (int i = start; i <= end; i++) {
//            if (lowerBound != null && nums[i] < lowerBound) {
//                return false;
//            }
//            if (upperBound != null && nums[i] > upperBound) {
//                return false;
//            }
//            if(nums[i] > nums[start]) {
//                rightStart = i;
//                break;
//            }
//        }
        // 简化成下面一个版本
        int left = start;
        int right = end;
        while (left <= right) { // 找到第一个比nums[start]要大的数字
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[start]) {
                right = mid - 1;
            } else { // nums[mid] < nums[start]
                left = mid + 1;
            }
        }
        int rightStart = left;
        boolean leftRes = verifyPreorder(start + 1, rightStart - 1, lowerBound, nums[start], nums);
        boolean rightRes = verifyPreorder(rightStart, end, nums[start], upperBound, nums);
        return leftRes && rightRes;
    }
}

// Solution 3: stack
/*
用stack来模拟pre-order traversal BST的过程
stack是一个从bottom到top降序的
    新的元素cur，如果比top小，直接push
    新的元素cur，如果比top大，stack.pop()直到top > cur，吧lowerBound更新成最后一个pop出去的元素
 */
class Solution3 {
    public boolean verifyPreorder(int[] preorder) {
        // corner case
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        // general case
        Stack<Integer> stack = new Stack<>();
        int lowerBound = Integer.MIN_VALUE;

        for (int num: preorder) {
            if (num < lowerBound) {
                return false;
            }
            while (!stack.isEmpty() && num > stack.peek()) {
                lowerBound = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }

}

}