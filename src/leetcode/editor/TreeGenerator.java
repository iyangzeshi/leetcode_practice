package leetcode.editor;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Program: leetcode_practice
 * @ClassName: TreeGenerator
 * @Description:
 * @Author: Jesse Yang
 * @Date: 2020-07-09 17:13
 */
public class TreeGenerator { // LC449
	
	public static void main(String[] args) {
		String data = "1, 3, 5, null, 12 ,3, null";
		TreeNode root = deserialize(data);
		TreeDrawer.draw(root);
		System.out.println(serialize(root));
	}
	//Encodes a tree to a single string.
	public static String serialize(TreeNode root) {
		// corner case
		if (root == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();

			if (cur == null) {
				sb.append("null, ");
			} else {
				sb.append(cur.val).append(", ");
				queue.offer(cur.left);
				queue.offer(cur.right);
			}
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
		// try to compress as possible as it can
	}

	// Decodes your encoded data to tree.
	// 输入String， 输入TreeNode, 比如说是"2,1,3"，有子树是null的用null代替，比如"2,null,3"
	public static TreeNode deserialize(String data) {
		String str0 = data.replaceAll("\\s+", "");
		String str = str0.replace("#", "null");
		String[] ss = str.split(",");
		if (ss.length == 0) {
			throw new IllegalArgumentException();
		}
		if (ss.length == 1 && (ss[0].equals("null") || ss[0].equals(" null"))) {
			return null;
		}

		TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int i = 1;
		while (i < ss.length) {
			TreeNode cur = queue.poll();
			
			TreeNode left =
					ss[i].equals("null")/* || ss[i].equals(" null")*/ ? null : new TreeNode(Integer.parseInt(ss[i]));
			i++;
			TreeNode right =
					(i >= ss.length || ss[i].equals("null") /*|| ss[i].equals(" null")*/) ? null : new TreeNode(Integer.parseInt(ss[i]));

			cur.left = left;
			cur.right = right;

			if (left != null) {
				queue.offer(left);
			}
			if (right != null) {
				queue.offer(right);
			}

			i++;
		}

		return root;
	}
}