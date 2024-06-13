//Given n points on a 2D plane, find the maximum number of points that lie on th
//e same straight line. 
//
// Example 1: 
//
// 
//Input: [[1,1],[2,2],[3,3]]
//Output: 3
//Explanation:
//^
//|
//|        o
//|     o
//|  o  
//+------------->
//0  1  2  3  4
// 
//
// Example 2: 
//
// 
//Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//Output: 4
//Explanation:
//^
//|
//|  o
//|     o        o
//|        o
//|  o        o
//+------------------->
//0  1  2  3  4  5  6
// 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Hash Table Math 
// 👍 936 👎 2072

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

// 2020-09-10 23:14:09
// Jesse Yang
public class Leetcode0149MaxPointsOnALine{
    // Java: max-points-on-a-line
    public static void main(String[] args) {
        Solution sol = new Leetcode0149MaxPointsOnALine().new Solution();
        // TO TEST
        Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 1);
        Pair<Integer, Integer> pair2 = new Pair<>(1, 1);
        map.put(pair1, 1);
        map.put(pair2, 2);
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int maxPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; ++i) {
    
            // key: Pair<Integer, Integer> - slope, value: count
            Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
            int duplicate = 1;
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    ++duplicate;
                    continue;
                }
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int d = gcd(dx, dy);
                Pair<Integer, Integer> pair = new Pair<>(dx / d, dy / d);
                map.put(pair, map.getOrDefault(pair, 0) + 1);
            }
            res = Math.max(res, duplicate);
            for (Map.Entry<Pair<Integer, Integer>, Integer> e : map.entrySet()) {
                res = Math.max(res, e.getValue() + duplicate);
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: T(n) = O(n^3), S(n) = O(1)
// 13 ms,击败了47.36% 的Java用户, 38.9 MB,击败了45.88% 的Java用户
/*
确定一条线需要2个点
确定了第1个点有n种可能，剩下的每个点都有可能和它连线组成一条指向，第2个点有(n-1)种可能
确定了直线之后，要对所有的point遍历一遍，看哪些point在这个直线上，记录在这个直线上的点的个数
 */
class Solution1 {

    public int maxPoints(int[][] points) {
        Arrays.sort(points);
        int res = 0;
        int len = points.length;
        for (int i = 0; i < len; ++i) {
            int duplicate = 1;
            for (int j = i + 1; j < len; ++j) {
                int count = 0;
                long x1 = points[i][0];
                long y1 = points[i][1];
                long x2 = points[j][0];
                long y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    ++duplicate;
                    continue;
                }
                for (int[] point : points) {
                    int x3 = point[0];
                    int y3 = point[1];
                    if (x1 * y2 + x2 * y3 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3 == 0) {
                        ++count;
                    }
                }
                res = Math.max(res, count);
            }
            res = Math.max(res, duplicate);
        }
        return res;
    }
}

// Solution 2:
// T(n) = O(n^2), S(n) = O(1)
// 8 ms,击败了70.17% 的Java用户, 38.5 MB,击败了63.05% 的Java用户
/*
确定了一个点之后，看其他点到这个点的这个点的斜率
然后看有多少对这样的点构成的斜率相同
每次都要统计局部的最多斜率相同的点数，然后还需要统计相同点的个数，局部最大值就是1+ maxP + overLap)
然后更新全局最大值
 */
class Solution2 {
    
    public int maxPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; ++i) {
            // key: Pair<Integer, Integer> - slope, value: count
            Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
            int duplicate = 1;
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    ++duplicate;
                    continue;
                }
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int d = gcd(dx, dy);
                Pair<Integer, Integer> slope = new Pair<>(dx / d, dy / d);
                map.put(slope, map.getOrDefault(slope, 0) + 1);
            }
            res = Math.max(res, duplicate);
            for (Map.Entry<Pair<Integer, Integer>, Integer> e : map.entrySet()) {
                res = Math.max(res, e.getValue() + duplicate);
            }
        }
        return res;
    }
    
    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}

}