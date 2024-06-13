//You are given an array of integers stones where stones[i] is the weight of the
// ith stone. 
//
// We are playing a game with the stones. On each turn, we choose any two stones
// and smash them together. Suppose the stones have weights x and y with x <= y. T
//he result of this smash is: 
//
// 
// If x == y, both stones are destroyed, and 
// If x != y, the stone of weight x is destroyed, and the stone of weight y has 
//new weight y - x. 
// 
//
// At the end of the game, there is at most one stone left. 
//
// Return the smallest possible weight of the left stone. If there are no stones
// left, return 0. 
//
// 
// Example 1: 
//
// 
//Input: stones = [2,7,4,1,8,1]
//Output: 1
//Explanation:
//We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
//we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
//we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
//we can combine 1 and 1 to get 0, so the array converts to [1], then that's the
// optimal value.
// 
//
// Example 2: 
//
// 
//Input: stones = [31,26,33,21,40]
//Output: 5
// 
//
// Example 3: 
//
// 
//Input: stones = [1,2]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= stones.length <= 30 
// 1 <= stones[i] <= 100 
// 
// Related Topics Dynamic Programming 
// 👍 1156 👎 47

package leetcode.editor.en;

import java.util.HashSet;
import java.util.Set;

// 2021-03-28 13:38:18
// Jesse Yang
public class Leetcode1049LastStoneWeightIi{
    // Java: last-stone-weight-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode1049LastStoneWeightIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int lastStoneWeightII(int[] stones) {
        
        // divide stone into two groups S1 and S2, S1 <= S2
        // S1 + S2 = totalWeight --> S2 - S1 = totalWeight - 2 * S1 && find smallest S2 - S1 >= 0 --> S1 as close as possible to totalWeight / 2;
        
        if (stones.length == 1) {
            return stones[0];
        }
        
        int totalWeight = 0;
        for (int w : stones) {
            totalWeight += w;
        }
        
        boolean[] dp = new boolean[totalWeight + 1];
        
        int curSumWeight = 0;
        for (int s : stones) {
            curSumWeight += s;
            dp[s] = true;
            for (int curWeight = curSumWeight; curWeight >= s; curWeight--) {
                if (dp[curSumWeight - curWeight]) {
                    dp[curWeight] = true;
                }
            }
        }
        
        int halfWeight = totalWeight / 2;
        
        for (int w = halfWeight; w >= 0; w--) {
            if (dp[w]) {
                return totalWeight - 2 * w;
            }
        }
        
        return 0;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1:
// 14 ms,击败了6.34% 的Java用户, 38.4 MB,击败了15.07% 的Java用户
class Solution1 {
    
    public int lastStoneWeightII(int[] stones) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int stone : stones) {
            Set<Integer> temp = new HashSet<>(set);
            set.clear();
            for (Integer w : temp) {
                set.add(w + stone);
                set.add(w - stone);
            }
        }
        int min = Integer.MAX_VALUE;
        for (Integer res : set) {
            if (res >= 0 && res < min) {
                min = res;
            }
        }
        return min;
    }
    
}

// Solution 2: DP
// 1 ms,击败了99.76% 的Java用户, 36.2 MB,击败了89.59% 的Java用户
class Solution2 {
    
    public int lastStoneWeightII(int[] stones) {
        
        // divide stone into two groups S1 and S2, S1 <= S2
        // S1 + S2 = totalWeight --> S2 - S1 = totalWeight - 2 * S1 && find smallest S2 - S1 >= 0 --> S1 as close as possible to totalWeight / 2;
        
        if (stones.length == 1) {
            return stones[0];
        }
        
        int totalWeight = 0;
        for (int w : stones) {
            totalWeight += w;
        }
        
        boolean[] dp = new boolean[totalWeight + 1];
        
        int curSumWeight = 0;
        for (int s : stones) {
            curSumWeight += s;
            dp[s] = true;
            for (int curWeight = curSumWeight; curWeight >= s; curWeight--) {
                if (dp[curSumWeight - curWeight]) {
                    dp[curWeight] = true;
                }
            }
        }
        
        int halfWeight = totalWeight / 2;
        
        for (int w = halfWeight; w >= 0; w--) {
            if (dp[w]) {
                return totalWeight - 2 * w;
            }
        }
        
        return 0;
    }
    
}
}