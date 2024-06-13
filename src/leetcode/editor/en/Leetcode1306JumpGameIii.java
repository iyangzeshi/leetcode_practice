
//Given an array of non-negative integers arr, you are initially positioned at s
//tart index of the array. When you are at index i, you can jump to i + arr[i] or 
//i - arr[i], check if you can reach to any index with value 0. 
//
// Notice that you can not jump outside of the array at any time. 
//
// 
// Example 1: 
//
// 
//Input: arr = [4,2,3,0,3,1,2], start = 5
//Output: true
//Explanation: 
//All possible ways to reach at index 3 with value 0 are: 
//index 5 -> index 4 -> index 1 -> index 3 
//index 5 -> index 6 -> index 4 -> index 1 -> index 3 
// 
//
// Example 2: 
//
// 
//Input: arr = [4,2,3,0,3,1,2], start = 0
//Output: true 
//Explanation: 
//One possible way to reach at index 3 with value 0 is: 
//index 0 -> index 4 -> index 1 -> index 3
// 
//
// Example 3: 
//
// 
//Input: arr = [3,0,2,1,2], start = 2
//Output: false
//Explanation: There is no way to reach at index 1 with value 0.
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 5 * 104 
// 0 <= arr[i] < arr.length 
// 0 <= start < arr.length 
// 
// Related Topics Depth-first Search Breadth-first Search Recursion 
// 👍 995 👎 31

package leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

// 2021-02-06 20:47:52
// Jesse Yang
public class Leetcode1306JumpGameIii{
    // Java: jump-game-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode1306JumpGameIii().new Solution();
        // TO TEST
        int[] nums = {3,0,2,1,2};
        int start = 2;
        boolean res = sol.canReach(nums, start);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean canReach(int[] arr, int start) {
        // corner case
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        int skip = arr[start];
        arr[start] = -1;
        return canReach(arr, start + skip) || canReach(arr, start - skip);
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: BFS T(n) = O(n), S(n) = O(n)
// 10 ms,击败了12.95% 的Java用户, 47.1 MB,击败了70.18% 的Java用户
class Solution1 {
    
    public boolean canReach(int[] arr, int start) {
        // corner case
        if (arr == null) {
            return true;
        }
        
        int len = arr.length;
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (arr[index] == 0) {
                return true;
            }
            int left = index - arr[index];
            if (left >= 0 && !visited.contains(left)) {
                visited.add(left);
                queue.offer(left);
            }
            int right = index + arr[index];
            if (right < len && !visited.contains(right)) {
                visited.add(right);
                queue.offer(right);
            }
        }
        return false;
    }
    
}

// Solution 2: DFS: T(n) = O(n), S(n) = O(n)
// 1 ms,击败了89.81% 的Java用户, 51.2 MB,击败了50.13% 的Java用户
class Solution2 {
    
    public boolean canReach(int[] arr, int start) {
        // corner case
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        int skip = arr[start];
        arr[start] = -1;
        return canReach(arr, start + skip) || canReach(arr, start - skip);
    }
    
}
}