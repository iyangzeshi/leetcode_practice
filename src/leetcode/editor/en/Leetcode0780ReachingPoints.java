//A move consists of taking a point (x, y) and transforming it to either (x, x+y
//) or (x+y, y). 
//
// Given a starting point (sx, sy) and a target point (tx, ty), return True if a
//nd only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty
//). Otherwise, return False. 
//
// 
//Examples:
//Input: sx = 1, sy = 1, tx = 3, ty = 5
//Output: True
//Explanation:
//One series of moves that transforms the starting point to the target is:
//(1, 1) -> (1, 2)
//(1, 2) -> (3, 2)
//(3, 2) -> (3, 5)
//
//Input: sx = 1, sy = 1, tx = 2, ty = 2
//Output: False
//
//Input: sx = 1, sy = 1, tx = 1, ty = 1
//Output: True
//
// 
//
// Note: 
//
// 
// sx, sy, tx, ty will all be integers in the range [1, 10^9]. 
// 
// Related Topics Math 
// 👍 657 👎 121

package leetcode.editor.en;

// 2021-01-27 00:45:53
// Jesse Yang
public class Leetcode0780ReachingPoints{
    // Java: reaching-points
    public static void main(String[] args) {
        Solution sol = new Leetcode0780ReachingPoints().new Solution();
        // TO TEST
        int sx = 1;
        int sy = 6;
        int tx = 11;
        int ty = 10;
        boolean res = sol.reachingPoints(sx, sy, tx, ty);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Math: current node has 2 children, but current node has only 1 parent.
// find further ancestor until reach start point or change subtraction turn
/*
这个起点往外面走的路径其实是一个tree，一个点可以有2个孩子。
但是知道了这个孩子之后，它的parent是确定的。
(x, y) -> (x, x+y) or (x+y, y) where x, y > 0, and x != x + y, and x + y != y
so there is no child like (x, y), where x = y except the start node

now for any node (a, b), analyse its parent
it parent should be the one between of (a, b - a) and (a - b, b),
and its parent's 2d coordinate shall be both > 0,
since b - a = -(a - b), they can not be >= simultaneously, so it only has 1 parent.

further analysis:
when tx > ty:
    its parent is (tx - ty, ty) where tx - ty >= sx,
    but we can continually find further parent,
    which means tx continually subtract ty until tx >= sy
    assuming tx continually subtract ty, tx - n * ty = sx, thus (tx - sx) % ty = 0,
        then it can reach the point(sx, sy).
    otherwise, tx shall continually subtract ty until tx < ty ( if until tx = ty, if will
        reach to the point(sx, sy)), so tx %= ty
when tx < ty : analysis is similar to above.
 */
class Solution {
    
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) {
                break;
            } else if (tx > ty) {
                if ((tx - sx) % ty == 0 && ty == sy) {
                    return true;
                }
                tx %= ty;
            } else { // when tx < ty, its parent is (tx, ty - tx) where ty - tx >= sy
                if (tx == sx && (ty - sy) % tx == 0) {
                    return true;
                }
                ty %= tx;
            }
        }
        return (tx == sx && ty == sy);
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: Math, find parent step by step
/*
这个起点往外面走的路径其实是一个tree，一个点可以有2个孩子。
但是知道了这个孩子之后，它的parent是确定的。
(x, y) -> (x, x+y) or (x+y, y) where x, y > 0, and x != x + y, and x + y != y
so there is no child like (x, y), where x = y except the start node

now for any node (a, b), analyse its parent
it parent should be the one between of (a, b - a) and (a - b, b),
and its parent's 2d coordinate shall be both > 0,
since b - a = -(a - b), they can not be >= simultaneously, so it only has 1 parent.
 */
class Solution1 {
    
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) {
                break;
            } else if (tx > ty) { // its parent is (tx - ty, ty) where tx - ty >= sx
                tx -= ty;
                if (tx == sx && ty == sy) {
                    return true;
                }
            } else { // when tx < ty, its parent is (tx, ty - tx) where ty - tx >= sy
                ty -= tx;
                if (tx == sx && ty == sy) {
                    return true;
                }
            }
        }
        return (tx == sx && ty == sy);
    }
    
}

// Solution 2: Math, find further ancestor until reach start point or change subtraction turn
// T(tx, ty) = O(log(max(tx,ty))), similar to Euclidean algorithm (辗转相除法), S(tx, ty) = O(1)
// 0 ms,击败了100.00% 的Java用户, 35.7 MB,击败了66.05% 的Java用户
/*
这个起点往外面走的路径其实是一个tree，一个点可以有2个孩子。
但是知道了这个孩子之后，它的parent是确定的。
(x, y) -> (x, x+y) or (x+y, y) where x, y > 0, and x != x + y, and x + y != y
so there is no child like (x, y), where x = y except the start node

now for any node (a, b), analyse its parent
it parent should be the one between of (a, b - a) and (a - b, b),
and its parent's 2d coordinate shall be both > 0,
since b - a = -(a - b), they can not be >= simultaneously, so it only has 1 parent.

further analysis:
when tx > ty:
    its parent is (tx - ty, ty) where tx - ty >= sx,
    but we can continually find further parent,
    which means tx continually subtract ty until tx >= sy
    assuming tx continually subtract ty, tx - n * ty = sx, thus (tx - sx) % ty = 0,
        then it can reach the point(sx, sy).
    otherwise, tx shall continually subtract ty until tx < ty ( if until tx = ty, if will
        reach to the point(sx, sy)), so tx %= ty
when tx < ty : analysis is similar to above.
 */
class Solution2 {
    
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) {
                break;
            } else if (tx > ty) {
                if ((tx - sx) % ty == 0 && ty == sy) {
                    return true;
                }
                tx %= ty;
            } else { // when tx < ty, its parent is (tx, ty - tx) where ty - tx >= sy
                if (tx == sx && (ty - sy) % tx == 0) {
                    return true;
                }
                ty %= tx;
            }
        }
        return (tx == sx && ty == sy);
    }
    
}
}