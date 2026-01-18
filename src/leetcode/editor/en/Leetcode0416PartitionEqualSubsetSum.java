//Given a non-empty array containing only positive integers, find if the array c
//an be partitioned into two subsets such that the sum of elements in both subsets
// is equal. 
//
// Note: 
//
// 
// Each of the array element will not exceed 100. 
// The array size will not exceed 200. 
// 
//
// 
//
// Example 1: 
//
// 
//Input: [1, 5, 11, 5]
//
//Output: true
//
//Explanation: The array can be partitioned as [1, 5, 5] and [11].
// 
//
// 
//
// Example 2: 
//
// 
//Input: [1, 2, 3, 5]
//
//Output: false
//
//Explanation: The array cannot be partitioned into equal sum subsets.
// 
//
// 
// Related Topics Dynamic Programming 
// 👍 2703 👎 71

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 2020-07-26 14:19:39
// Jesse Yang
public class Leetcode0416PartitionEqualSubsetSum {

	// Java: partition-equal-subset-sum
    public static void main(String[] args) {
        /*Random random = new Random();

        // TO TEST
        for (int i = 0; i < 100; i++) {
            int len = random.nextInt(200);
            int[] nums = new int[len];
            for (int j = 0; j < len; j++) {
                nums[j] = random.nextInt(101);
            }
//            int[] nums = {1,2,3,4,6};
            Solution1_2 sol1_2 = new Leetcode0416PartitionEqualSubsetSum().new Solution1_2();
            Solution1_3 sol5 = new Leetcode0416PartitionEqualSubsetSum().new Solution1_3();
            boolean res1_2 = sol1_2.canPartition(nums);
            boolean res1_3 = sol5.canPartition(nums);
            if (res1_2 != res1_3) {
                System.out.println("Wrong sample: ");
                System.out.println(Arrays.toString(nums));
                return;
            }
        }
        System.out.println("succeed");*/
        Solution1_1 sol = new Leetcode0416PartitionEqualSubsetSum().new Solution1_1();
        int[] nums = {1,3,2};
        boolean res = sol.canPartition(nums);
        System.out.println(res);
    }

//leetcode submit region begin(Prohibit modification and deletion)
// 这个题目几个，提高运行效率的关键点
// 1. sort，把大的元素放在后面，这样能比较快到达target
// 2. pruning,剪枝
// 3. DFS过程中重复元素去重
class Solution {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 == 1) {
            return false; // odd number can not be spilt to equal parts
        }
        
        int len = nums.length;
        Arrays.sort(nums);
        int target = sum / 2;
        
        // dp[i][j]: if number in nums[i, len - 1] can be formed subsets to sum up to j
        boolean[][] dp = new boolean[len + 1][target + 1];
        
        // initialization
        for (int i = 0; i <= len; i++) {
            dp[i][0] = true;
        }
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 1; j <= target; j++) {
                if (j - nums[i] >= 0) { // whether choose current nums[i]
                    dp[i][j] = dp[i + 1][j - nums[i]] || dp[i + 1][j];
                }
            }
            if (dp[i][target]) {
                return true;
            }
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/* 面试的时候，用Solution 2_3 */

/*
Solution 1_3: DFS,第1类搜索树，pruning with Boolean[][]
相对这个nums排序，做DFS

每次从nums[0, idx]中取一个数字 nums[i]
再让idx = i
repeat这个过程

T(n, k) = O(n * k), S(n, k) = O(n * k)
 */
class Solution1_3 {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
    
        int len = nums.length;
        Arrays.sort(nums);
        int target = sum / 2;
        Boolean[][] memo = new Boolean[len + 1][target + 1];
        return dfs(len - 1, target, nums, memo);
    }
    
    /**
     * @param idx: index
     * @param remainSum: remaining sum needed to be achieved by left nums[0 to idx]
     * @param nums: given array
     * @param memo : memo[i][j]表示从nums[i]开始往前面任意取元素求和的结果能不能为j，如果是就true；否则false
     * @return: boolean
     */
    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] memo) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        if (remainSum < 0 || idx < 0) {
            return false;
        }
        if (memo[idx][remainSum] != null) {
            return memo[idx][remainSum];
        }
        // general case
        for (int i = idx; i >= 0; i--) {
            if (dfs(i - 1, remainSum - nums[i], nums, memo)) {
                memo[idx][remainSum] = true;
                return true;
            }
        }
        memo[idx][remainSum] = false;
        return false;
    }
    
}

// Solution 1_4: 结合了Solution 1_2 和1_3DFS,第1类搜索树，去重 + pruning with Boolean[][]
// 72 ms,击败了18.74% 的Java用户, 49 MB,击败了29.08% 的Java用户
class Solution1_4 {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        
        int len = nums.length;
        Arrays.sort(nums);
        int target = sum / 2;
        Boolean[][] visited = new Boolean[len + 1][target + 1];
        return dfs(len - 1, target, nums, visited);
    }
    
    /**
     * @param visited : visited[i][j]表示从nums[i]开始往前面任意取元素求和的结果能不能为j， 如果是就true；否则false
     */
    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] visited) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        if (remainSum < 0 || idx < 0) {
            return false;
        }
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        
        //general case
        for (int i = idx; i >= 0; i--) {
            if (dfs(i - 1, remainSum - nums[i], nums, visited)) {
                visited[idx][remainSum] = true;
                return true;
            }
            int j = i;
            while (j >= 0 && nums[j] == nums[i]) {
                j--;
            }
            i = j + 1;
        }
        visited[idx][remainSum] = false;
        return false;
    }
    
}

// Solution 2_3: DFS 第2类搜索树， Pruning with Boolean[][]
// 54 ms,击败了28.34% 的Java用户, 49.2 MB,击败了24.22% 的Java用户
/*
第2类搜索树，
遇到每一个点的时候，都有两种情况，一种是选择这个点，还有一种是舍弃这个点
T(n, k) = O(n * k), S(n, k) = O(n * k)
 */
class Solution2_3 {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        
        int len = nums.length;
        Arrays.sort(nums);
        int target = sum / 2;
        Boolean[][] memo = new Boolean[len + 1][target + 1];
        return dfs(len - 1, target, nums, memo);
    }
    
    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] memo) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        if (idx < 0 || remainSum < 0) {
            return false;
        }
        if (memo[idx][remainSum] != null) {
            return memo[idx][remainSum];
        }
        
        int len = nums.length;
        if (idx == len) {
            return false;
        }
        
        memo[idx][remainSum]
            = dfs(idx - 1, remainSum, nums, memo)
            || dfs(idx - 1, remainSum - nums[idx], nums, memo);
        return memo[idx][remainSum];
    }
    
}

// Solution 2_4: DFS, 第2类搜索树，去重 + Pruning
// 11 ms,击败了88.31% 的Java用户, 49.2 MB,击败了24.22% 的Java用户
class Solution2_4 {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        
        int len = nums.length;
        int target = sum / 2;
        Boolean[][] visited = new Boolean[len + 1][target + 1];
        return dfs(len - 1, target, nums, visited);
    }
    
    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] visited) {
        if (remainSum == 0) {
            return true;
        }
        if (idx < 0 || remainSum < 0) {
            return false;
        }
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        // general case
        if (dfs(idx - 1, remainSum - nums[idx], nums, visited)) {
            visited[idx][remainSum] = true;
            return true;
        }
        int j = idx - 1;
        while (j >= 0 && nums[j] == nums[idx]) {
            j--;
        }
        visited[idx][remainSum] = dfs(j, remainSum, nums, visited);
        return visited[idx][remainSum];
    }
    
}

/*
Solution 3_1: DP with for loop,
dp[i][j]=true if the sum j can be formed by array elements in subset nums[0]..nums[i],
otherwise dp[i][j]=false
dp[i][j] |= dp[i - 1][j - num];

T(n, k) = O(n * k), S(n, k) = O(n * k)
* */
class Solution3_1 {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 == 1) {
            return false; // odd number can not be spilt to equal parts
        }
        
        int len = nums.length;
        // Arrays.sort(nums);
        int target = sum / 2;
        
        // dp[i][j] 表示是否能从 nums[0..i] 中选出若干数，使得它们的和为 j
        boolean[][] dp = new boolean[len][target + 1];
        
        // initialization: dp[i][0] = true（不选任何元素即可得到 0）
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        // 初始化第一行: 只有 nums[0] == j 时，dp[0][j] 才是 true
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        
        // recursive formula
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                // do not choose nums[i]
                dp[i][j] = dp[i - 1][j];
                // choose nums[i] (if j >= nums[i]）
                if (j >= nums[i]) {
                    dp[i][j] |= dp[i - 1][j - num];
                }
            }
            
        }
        return dp[len - 1][target];
    }
    
}

/*
 Solution 3_2: DP with rolling(reduced space complexity)

dp[i][j]=true if the sum j can be formed by array elements in subset nums[0]..nums[i],
otherwise dp[i][j]=false

dp[i][j] |= dp[i - 1][j - num];

loop for i
    loop for j
        dp[i][j] |= dp[i - 1][j - num];

now
loop for i
    loop for j
        dp[j] |= dp[j - num];
with rolling base, we can reuse the array
T(n, k) = O(n * k), S(n, k) = O(k)
*/
class Solution3_2 {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        
        // Arrays.sort(nums);
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1]; // dp[i]表示是否存在某些元素，使得这些元素的加合 = i
        dp[0] = true; // 空状态默认初始为true，表示空状态默认也是可以平均分割的
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }
    
}

// Solution 3_3: DP with HashSet
// 68 ms,击败了20.43% 的Java用户, 39.4 MB,击败了64.95% 的Java用户
class Solution3_3 {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        
        int len = nums.length;
        Arrays.sort(nums);
        int target = sum / 2;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int i = len - 1; i >= 0; i--) {
            int num = nums[i];
            List<Integer> temp = new ArrayList<>(set);
            for (int n : temp) {
                if (n + num <= target) {
                    set.add(n + num);
                }
            }
            if (set.contains(target)) {
                return true;
            }
        }
        return false;
    }
    
}

}
