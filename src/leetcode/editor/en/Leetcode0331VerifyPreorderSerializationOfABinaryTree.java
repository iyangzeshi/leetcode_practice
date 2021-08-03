//One way to serialize a binary tree is to use pre-order traversal. When we enco
//unter a non-null node, we record the node's value. If it is a null node, we reco
//rd using a sentinel value such as #. 
//
// 
//     _9_
//    /   \
//   3     2
//  / \   / \
// 4   1  #  6
/// \ / \   / \
//# # # #   # #
// 
//
// For example, the above binary tree can be serialized to the string "9,3,4,#,#
//,1,#,#,2,#,6,#,#", where # represents a null node. 
//
// Given a string of comma separated values, verify whether it is a correct preo
//rder traversal serialization of a binary tree. Find an algorithm without reconst
//ructing the tree. 
//
// Each comma separated value in the string must be either an integer or a chara
//cter '#' representing null pointer. 
//
// You may assume that the input format is always valid, for example it could ne
//ver contain two consecutive commas such as "1,,3". 
//
// Example 1: 
//
// 
//Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
//Output: true 
//
// Example 2: 
//
// 
//Input: "1,#"
//Output: false
// 
//
// Example 3: 
//
// 
//Input: "9,#,#,1"
//Output: false Related Topics Stack 
// 👍 767 👎 48

package leetcode.editor.en;

// 2020-08-13 22:29:49
// Zeshi Yang
public class Leetcode0331VerifyPreorderSerializationOfABinaryTree {

	// Java: verify-preorder-serialization-of-a-binary-tree
	public static void main(String[] args) {
		Solution sol = new Leetcode0331VerifyPreorderSerializationOfABinaryTree().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
/*
node的个数 + 1 = #的个数
证明： 数学归纳法
令Δ = #的个数 - node的个数
在任何地方，都不可能处发生Δ >= 1，只有在最后的情况下，才可能有Δ = 1
最后判断Δ == 1

 */
class Solution {

    public boolean isValidSerialization(String preorder) {
        // corner case
        if (preorder == null || preorder.length() == 0) {
            return true;
        }

        String[] str = preorder.split(",");
        int delta = 0; // num of # - num of nodes, always < 1, until at the end, it is 1.

        for (String s : str) {
            if (s.equals("#")) {
                delta++;
            } else {
                if (delta >= 1) { // 不放在上面的if原因是最后一个点遍历完，delta = 1.
                    return false;
                }
                delta--;
            }
        }
        return delta == 1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}