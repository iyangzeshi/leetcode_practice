/**
You may recall that an array arr is a mountain array if and only if: 

 
 arr.length >= 3 
 There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that: 
 
 arr[0] < arr[1] < ... < arr[i - 1] < arr[i] 
 arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 
 
 

 Given an integer array arr, return the length of the longest subarray, which 
is a mountain. Return 0 if there is no mountain subarray. 

 
 Example 1: 

 
Input: arr = [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 

 Example 2: 

 
Input: arr = [2,2,2]
Output: 0
Explanation: There is no mountain.
 

 
 Constraints: 

 
 1 <= arr.length <= 10⁴ 
 0 <= arr[i] <= 10⁴ 
 

 
 Follow up: 

 
 Can you solve it using only one pass? 
 Can you solve it in O(1) space? 
 

 Related Topics Array Two Pointers Dynamic Programming Enumeration 👍 2919 👎 86


*/
package leetcode.editor.en;

// 2025-06-09 18:28:23
// Jesse Yang
public class Leetcode0845LongestMountainInArray{
    // Java: longest-mountain-in-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0845LongestMountainInArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
Solution 2:
我们定义状态：
up: 当前递增阶段的长度（不包含当前点）；
down: 当前递减阶段的长度（不包含当前点）；
从头到尾扫一遍数组：
如果是递增（arr[i] > arr[i-1]），就更新 up；
如果是递减（arr[i] < arr[i-1]），就更新 down；
如果碰到平的（arr[i] == arr[i-1]），就重置 up 和 down；
只有当 up > 0 && down > 0 时，才算山脉，长度为 up + down + 1。

T(n) = O(n) 最坏情况每个元素只访问 1次
S(n) = O(1)
 */
class Solution {
    public int longestMountain(int[] arr) {
        // corner case skip
        
        int up = 0; // start of mountain
        int down = 0; // end of mountain
        int maxLen = 0;
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            if (arr[i] > arr[i - 1]) {
                if (down > 0) { // increasing slope for new mountain
                    up = 1;
                    down = 0;
                } else { // down == 0
                    up++;
                }
            } else if (arr[i] < arr[i - 1]) {
                if (up > 0) {
                    down++;
                    if (down > 0) {
                        maxLen = Math.max(maxLen, up + down + 1);
                    }
                } else { // up == 0, not a mountain
                    continue;
                }
            } else { // arr[i] == arr[i - 1]
                up = 0;
                down = 0;
            }
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/* Solution 1:
我们遍历整个数组，把每个可能作为“山顶”的位置都试一下：
枚举山顶 i，满足 arr[i-1] < arr[i] > arr[i+1]。
然后从这个山顶往左、往右分别扩展（找山的左右边界）。
用 (right - left + 1) 更新答案。

最坏情况每个元素只访问 2 次 → T(n) = O(n)

空间复杂度：S(n) = O(1)
 */

class Solution1 {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int maxLen = 0;
        
        for (int i = 1; i < n - 1; i++) {
            // 先判断是不是山顶
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                int left = i - 1;
                int right = i + 1;
                
                // 向左扩展
                while (left > 0 && arr[left - 1] < arr[left]) {
                    left--;
                }
                
                // 向右扩展
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }
                
                maxLen = Math.max(maxLen, right - left + 1);
                i = right; // 可选优化：跳过这段山脉
            }
        }
        
        return maxLen;
    }
    
}