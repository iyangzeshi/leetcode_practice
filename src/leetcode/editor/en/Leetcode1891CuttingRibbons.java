/**
You are given an integer array ribbons, where ribbons[i] represents the length 
of the iᵗʰ ribbon, and an integer k. You may cut any of the ribbons into any 
number of segments of positive integer lengths, or perform no cuts at all. 

 
 For example, if you have a ribbon of length 4, you can: 
 

 
 Keep the ribbon of length 4, 
 Cut it into one ribbon of length 3 and one ribbon of length 1, 
 Cut it into two ribbons of length 2, 
 Cut it into one ribbon of length 2 and two ribbons of length 1, or 
 Cut it into four ribbons of length 1. 
 
 


 Your task is to determine the maximum length of ribbon, x, that allows you to 
cut at least k ribbons, each of length x. You can discard any leftover ribbon 
from the cuts. If it is impossible to cut k ribbons of the same length, return 0. 

 
 Example 1: 

 
Input: ribbons = [9,7,5], k = 3
Output: 5
Explanation:
- Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
- Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
- Keep the third ribbon as it is.
Now you have 3 ribbons of length 5. 

 Example 2: 

 
Input: ribbons = [7,5,9], k = 4
Output: 4
Explanation:
- Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
- Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
- Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
Now you have 4 ribbons of length 4.
 

 Example 3: 

 
Input: ribbons = [5,7,9], k = 22
Output: 0
Explanation: You cannot obtain k ribbons of the same positive integer length.
 

 
 Constraints: 

 
 1 <= ribbons.length <= 10⁵ 
 1 <= ribbons[i] <= 10⁵ 
 1 <= k <= 10⁹ 
 

 Related Topics Array Binary Search 👍 602 👎 53

*/
package leetcode.editor.en;

// 2025-01-04 21:45:39
// Jesse Yang
public class Leetcode1891CuttingRibbons{
    // Java: cutting-ribbons
    public static void main(String[] args) {
        Solution sol = new Leetcode1891CuttingRibbons().new Solution();
        // TO TEST
        int[] ribbons = {1,1,1,2};
        int k = 2;
        int res = sol.maxLength(ribbons, k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
T(n) = O(n * log(max(ribbons))),
S(n) = O(1)
二分查找流程：
step 1: 初始化左右边界：left = 1，right = max(ribbons)，其中 max(ribbons) 是彩带的最大长度。
step 2: 计算中间长度 mid，判断是否可以剪出至少 k 条长度为 mid 的彩带。
step 3: 如果可以，尝试更长的长度（left = mid）；否则，缩短长度（right = mid - 1）。
最终返回满足条件的最大长度。
 */
class Solution {
    
    public int maxLength(int[] ribbons, int k) {
        // Binary search bounds
        int left = 1;
        int right = 0;
        int res = 0;
        
        for (int ribbon : ribbons) {
            right = Math.max(right, ribbon);
        }
        
        // Perform binary search on the ribbon length
        while (left + 1 < right) { // TODO
            int mid = left + (right - left) / 2;
            if (canCut(ribbons, mid, k)) {
                res = mid;
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (canCut(ribbons, right, k)) {
            res = right;
        } else if (canCut(ribbons, left, k)){
            res = left;
        }
        return res;
    }
    
    // determine if it's possible to cut the ribbons into at least `k` pieces of the given `target`
    private boolean canCut(int[] ribbons, int target, int k) {
        int count = 0;
        if (target == 0) {
            return false;
        }
        for (int ribbon : ribbons) {
            // Number of pieces the current ribbon can contribute
            count += ribbon / target;
            // If the total reaches or exceeds `k`, we can stop early
            if (count >= k) {
                return true;
            }
        }
        // It's not possible to make the cut
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}