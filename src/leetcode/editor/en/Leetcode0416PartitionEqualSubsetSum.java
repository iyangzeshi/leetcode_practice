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
        Solution sol = new Leetcode0416PartitionEqualSubsetSum().new Solution();
        int[] nums = {14,9,8,4,3,2};
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

// Solution 1_1: DFS, 第1类搜索树
// Time Limit Exceeded
class Solution1_1 {
    
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
        return dfs(len - 1, target, nums);
    }
    
    private boolean dfs(int idx, int remainSum, int[] nums) {
        // base case
        if (remainSum < 0 || idx == -1) {
            return false;
        }
        if (remainSum == 0) {
            return true;
        }
        
        for (int i = idx; i >= 0; i--) {
            if (dfs(i - 1, remainSum - nums[i], nums)) {
                return true;
            }
        }
        return false;
    }
    
}

// Solution 1_2: DFS,第1类搜索树，去重（重复的数字）
// Time Limit Exceeded
class Solution1_2 {
    
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
        return dfs(len - 1, target, nums);
    }
    
    /**
     * @param idx:       index
     * @param remainSum: remaining sum need to be achieved by left nums[idx] tp nums[0]
     * @param nums:      given array
     * @return: boolean
     */
    private boolean dfs(int idx, int remainSum, int[] nums) {
        // base case
        if (idx == -1 || remainSum < 0) {
            return false;
        }
        if (remainSum == 0) {
            return true;
        }
        
        for (int i = idx; i >= 0; i--) {
            if (dfs(i - 1, remainSum - nums[i], nums)) {
                return true;
            }
            int j = i;
            while (j >= 0 && nums[j] == nums[i]) {
                j--;
            }
            i = j + 1;
        }
        return false;
    }
    
}

// Solution 1_3: DFS,第1类搜索树，pruning with Boolean[][]
// 204 ms,击败了6.67% 的Java用户, 48.8 MB,击败了31.35% 的Java用户
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
        Boolean[][] visited = new Boolean[len + 1][target + 1];
        return dfs(len - 1, target, nums, visited);
    }
    
    /**
     * @param idx: index
     * @param remainSum: remaining sum need to be achieved by left nums[idx] tp nums[end]
     * @param nums: given array
     * @param visited : visited[i][j]表示从nums[i]开始往前面任意取元素求和的结果能不能为j，如果是就true；否则false
     * @return: boolean
     */
    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] visited) {
        // base case
        if (remainSum < 0 || idx < 0) {
            return false;
        }
        if (remainSum == 0) {
            visited[idx][remainSum] = true;
            return true;
        }
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        
        for (int i = idx; i >= 0; i--) {
            if (dfs(i - 1, remainSum - nums[i], nums, visited)) {
                visited[idx][remainSum] = true;
                return true;
            }
        }
        visited[idx][remainSum] = false;
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
        if (remainSum < 0) {
            return false;
        }
        if (remainSum == 0) {
            return true;
        }
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        
        for (int i = idx; i >= 0; i--) {
            if (dfs(i - 1, remainSum - nums[i], nums, visited)) {
                visited[idx][remainSum] = true;
                return true;
            }
            int j = i + 1;
            while (j >= 0 && nums[j] == nums[i]) {
                j--;
            }
            i = j + 1;
        }
        visited[idx][remainSum] = false;
        return false;
    }
    
}

// Solution 2_1: DFS 第2类搜索树
// Time limit Exceeded
class Solution2_1 {
    
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
        return dfs(len - 1, target, nums);
    }
    
    private boolean dfs(int idx, int remainSum, int[] nums) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        if (idx < 0 || remainSum < 0) {
            return false;
        }
        
        return dfs(idx - 1, remainSum, nums) || dfs(idx - 1, remainSum - nums[idx], nums);
    }
    
}

//Solution 2_2: DFS, 第2类搜索树，去重（去重重复的数字）
// Time Limit Exceeded
class Solution2_2 {
    
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
        return dfs(len - 1, target, nums);
    }
    
    private boolean dfs(int idx, int remainSum, int[] nums) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        if (idx < 0 || remainSum < 0) {
            return false;
        }
        
        if (dfs(idx - 1, remainSum - nums[idx], nums)) {
            return true;
        }
        int j = idx - 1;
        while (j >= 0 && nums[j] == nums[idx]) {
            j--;
        }
        return dfs(j, remainSum, nums);
    }
    
}

// Solution 2_3: DFS 第2类搜索树， Pruning with Boolean[][]
// 54 ms,击败了28.34% 的Java用户, 49.2 MB,击败了24.22% 的Java用户
/*
第2类搜索树，
遇到每一个点的时候，都有两种情况，一种是选择这个点，还有一种是舍弃这个点
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
        Boolean[][] visited = new Boolean[len + 1][target + 1];
        return dfs(len - 1, target, nums, visited);
    }
    
    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] visited) {
        // base case
        if (idx < 0 || remainSum < 0) {
            return false;
        }
        if (remainSum == 0) {
            visited[idx][remainSum] = true;
            return true;
        }
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        
        int len = nums.length;
        if (idx == len) {
            return false;
        }
        
        visited[idx][remainSum] =
                dfs(idx - 1, remainSum, nums, visited) ||
                        dfs(idx - 1, remainSum - nums[idx], nums, visited);
        return visited[idx][remainSum];
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
        if (idx < 0 || remainSum < 0) {
            return false;
        }
        if (remainSum == 0) {
            visited[idx][remainSum] = true;
            return true;
        }
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        
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

//Solution 3_1: DP with for loop, T(n, k) = O(n * k), S(n, k) = O(n * k)
// 20 ms,击败了75.66% 的Java用户, 39.6 MB,击败了59.31% 的Java用户
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

// Solution 3_2: DP with rolling(reduced space complexity) T(n, k) = O(n * k), S(n, k) = O(k)
// 13 ms,击败了86.58% 的Java用户, 38.2 MB,击败了88.45% 的Java用户
// 如果换成注释部分，37 ms,击败了55.87% 的Java用户，38.7 MB,击败了78.37% 的Java用户
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
        
        int len = nums.length;
        Arrays.sort(nums);
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1]; // dp[i]表示是否存在某些元素，使得这些元素的加合 = i
        dp[0] = true; // 空状态默认初始为true，表示空状态默认也是可以平均分割的
        /*List<Integer> list = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            int num = nums[i];
            list.clear();
            for (int j = 0; j < target; j++) {
                if (dp[j]) {
                    list.add(j);
                }
            }
            for (Integer j : list) {
                if (j + num < target) {
                    dp[j + num] = true;
                } else if (j + num == target) {
                    return true;
                }
            }
        }*/
        // 上面的注释部分可以优化成下面
        for (int i = len - 1; i >= 0; i--) {
            int num = nums[i];
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
            if (dp[target]) {
                return true;
            }
        }
        return false;
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
                set.add(n + num);
            }
            if (set.contains(target)) {
                return true;
            }
        }
        return false;
    }
    
}

}