//Given four lists A, B, C, D of integer values, compute how many tuples (i, j, 
//k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero. 
//
// To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ 
//N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guar
//anteed to be at most 231 - 1. 
//
// Example: 
//
// 
//Input:
//A = [ 1, 2]
//B = [-2,-1]
//C = [-1, 2]
//D = [ 0, 2]
//
//Output:
//2
//
//Explanation:
//The two tuples are:
//1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
//2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
// 
//
// 
// Related Topics Hash Table Binary Search 
// 👍 1210 👎 72

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 13:59:10
// Zeshi Yang
public class Leetcode0454FourSumIi{
    // Java: 4sum-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0454FourSumIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
    /*
    HashMap存两个数组之和，如AB。然后计算两个数组之和，如CD。时间复杂度为：O(n^2)+O(n^2),得到O(n^2).
    我们以存AB两数组之和为例。首先求出A和B任意两数之和sumAB，以sumAB为key，sumAB出现的次数为value，存入hashmap中。
    然后计算C和D中任意两数之和的相反数sumCD，在hashmap中查找是否存在key为sumCD。
    算法时间复杂度为O(n2)。
     */
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        // Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int numA : A) {
            for (int numB : B) {
                int sumAB = numA + numB;
                if (map.containsKey(sumAB)) {
                    map.put(sumAB, map.get(sumAB) + 1);
                } else {
                    map.put(sumAB, 1);
                }
            }
        }
    
        for (int numC : C) {
            for (int numD : D) {
                int sumCD = -(numC + numD);
                if (map.containsKey(sumCD)) {
                    res += map.get(sumCD);
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}