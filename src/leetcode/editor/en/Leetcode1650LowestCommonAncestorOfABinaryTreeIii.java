//Given two nodes of a binary tree p and q, return their lowest common ancestor 
//(LCA). 
//
// Each node will have a reference to its parent node. The definition for Node i
//s below: 
//
// 
//class Node {
//    public int val;
//    public Node left;
//    public Node right;
//    public Node parent;
//}
// 
//
// According to the definition of LCA on Wikipedia: "The lowest common ancestor 
//of two nodes p and q in a tree T is the lowest node that has both p and q as des
//cendants (where we allow a node to be a descendant of itself)." 
//
// 
// Example 1: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//Output: 3
//Explanation: The LCA of nodes 5 and 1 is 3.
// 
//
// Example 2: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//Output: 5
//Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of
// itself according to the LCA definition.
// 
//
// Example 3: 
//
// 
//Input: root = [1,2], p = 1, q = 2
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [2, 105]. 
// -109 <= Node.val <= 109 
// All Node.val are unique. 
// p != q 
// p and q exist in the tree. 
// 
// Related Topics Tree 
// 👍 26 👎 3

package leetcode.editor.en;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// 2020-12-01 19:23:41
// Zeshi Yang
public class Leetcode1650LowestCommonAncestorOfABinaryTreeIii {
	
	// Java: lowest-common-ancestor-of-a-binary-tree-iii
	public static void main(String[] args) {
		Solution sol = new Leetcode1650LowestCommonAncestorOfABinaryTreeIii().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
// Definition for a Node.
class Node {
    
    public int val;
    public Node left;
    public Node right;
    public Node parent;
    
    public Node(int val) {
        this.val = val;
    }
    
    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
    public Node(int val, Node left, Node right, Node parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
//leetcode submit region begin(Prohibit modification and deletion)
// Definition for a Node.
//class Node {
//     public int val;
//     public Node left;
//     public Node right;
//     public Node parent;
// };
//Soluion 1: T(n) = O(n), S(n) = O(1)
// 20 ms,击败了95.80% 的Java用户, 39.6 MB,击败了85.71% 的Java用户
/*
 * 先计算A和B的长度，让长的那个减到和短的那个一样的长度，
 * 然后同时往前走，重合的时候就是intersection
 * 如果没有重合点，就return null（其实也是两者都走到null的地方，也是重合的）
 */
class Solution {
	public Node lowestCommonAncestor(Node p, Node q) {
		int pDepth = getDepth(p);
		int qDepth = getDepth(q);
		return getLCA(p, pDepth, q, qDepth);
	}
	
	private int getDepth(Node cur) {
		int depth = 0;
		while (cur != null) {
			depth++;
			cur = cur.parent;
		}
		return depth;
	}
	
	// make sure pDepth >= qDepth
	private Node getLCA(Node p, int pDepth, Node q, int qDepth) {
		if (pDepth < qDepth) {
			return getLCA(q, qDepth, p, pDepth);
		}
		
		while (pDepth > qDepth) {
			p = p.parent;
			pDepth--;
		}
		
		while (p != q) {
			p = p.parent;
			q = q.parent;
		}
		return p;
	}
}

//leetcode submit region end(Prohibit modification and deletion)
// 题目和Leetcode 160非常相似
//Soluion 1: T(n) = O(n), S(n) = O(1)
// 20 ms,击败了95.80% 的Java用户, 39.6 MB,击败了85.71% 的Java用户
/*
 * 先计算A和B的长度，让长的那个减到和短的那个一样的长度，
 * 然后同时往前走，重合的时候就是intersection
 * 如果没有重合点，就return null（其实也是两者都走到null的地方，也是重合的）
 */
class Solution1 {
    
    public Node lowestCommonAncestor(Node p, Node q) {
        int pDepth = getDepth(p);
        int qDepth = getDepth(q);
        return getLCA(p, pDepth, q, qDepth);
    }
    
    private int getDepth(Node cur) {
        int depth = 0;
        while (cur != null) {
            depth++;
            cur = cur.parent;
        }
        return depth;
    }
    
    // make sure pDepth >= qDepth
    private Node getLCA(Node p, int pDepth, Node q, int qDepth) {
        if (pDepth < qDepth) {
            return getLCA(q, qDepth, p, pDepth);
        }
        
        while (pDepth > qDepth) {
            p = p.parent;
            pDepth--;
        }
        
        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }
    
}

//Solution 2: T(n) = O(n), S(n) = O(1)
// 20 ms,击败了95.80% 的Java用户, 40.4 MB,击败了19.83% 的Java用户
/*走完
path1: p -> LCA -> root -> q -> LCA,
path2: q -> LCA -> root -> p -> LCA
2条path的长度是一样的
*/
class Solution2 {
    
    public Node lowestCommonAncestor(Node p, Node q) {
        Node pHead = p;
        Node qHead = q;
        while (p != q) {
            p = (p != null ? p.parent: qHead);
            q = (q != null ? q.parent: pHead);
        }
        return p;
    }
}

// Solution 3: 2个stack push到root，T(n) = O(n), S(n) = O(n)
// 23 ms,击败了17.31% 的Java用户, 40.1 MB,击败了36.13% 的Java用户
/**
 * 2个stack先push，都push到root，再一个个pop出来，到第一个pop出来不一样的地方的前一个就是
 */
class Solution3 {
    public Node lowestCommonAncestor(Node p, Node q) {
        Stack<Node> stackP = pushToStackTillRoot(p);
        Node curP;
        
        Stack<Node> stackQ = pushToStackTillRoot(q);
        Node curQ;
        
        Node prev = stackP.peek();
        while(!stackP.isEmpty() && !stackQ.isEmpty()) {
            curP = stackP.pop();
            curQ = stackQ.pop();
            if(curP != curQ) {
                return prev;
            }
            prev = curQ;
        }
        return prev;
    }
    
    private Stack<Node> pushToStackTillRoot(Node p) {
        Stack<Node> stackP = new Stack<>();
        Node curP = p;
        while (curP != null) {
            stackP.push(curP);
            curP = curP.parent;
        }
        return stackP;
    }
}

// Solution 4: 用HashSet去重，T(n) = O(n), S(n) = O(n)
// 20 ms,击败了95.80% 的Java用户, 39.8 MB,击败了77.31% 的Java用户
class Solution4 {
    
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> visited = new HashSet<>();
        while (p != null || q != null) {
            if (!visited.contains(p)) {
                if (p != null) {
                    visited.add(p);
                    p = p.parent;
                }
            } else {
                return p;
            }
            
            if (!visited.contains(q)) {
                if (q != null) {
                    visited.add(q);
                    q = q.parent;
                }
            } else {
                return q;
            }
        }
        return null;
    }
}

}