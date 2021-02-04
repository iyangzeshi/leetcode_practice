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

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

// 2020-07-26 14:19:39
// Zeshi Yang
public class Leetcode0416PartitionEqualSubsetSum {

	// Java: partition-equal-subset-sum
    public static void main(String[] args) {
        Random random = new Random();

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
        System.out.println("succeed");
    }

//leetcode submit region begin(Prohibit modification and deletion)
// 这个题目几个，提高运行效率的关键点
// 1. decreasing sort，把大的元素放在前面，这样能比较快到达target
// 2. pruning,剪枝
// 3. DFS过程中重复元素去重
class Solution {

    // time = O(n * k), space = O(k)
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1]; // dp[i]表示是否存在某些元素，使得这些元素的加合 = i
        dp[0] = true; // 空状态默认初始为true，表示空状态默认也是可以平均分割的
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int num : nums) {
//            list.clear();
//            for (int j = 0; j < target; j++) {
//                if (dp[j]) {
//                    list.add(j);
//                }
//            }
//            for (Integer k : list) {
//                if (k + num < target) {
//                    dp[k + num] = true;
//                } else if (k + num == target) {
//                    return true;
//                }
//            }
//        }
        // 上面的注释部分可以优化成下面
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
            if (dp[target]) {
                return true;
            }
        }
        return false;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1_1: Time Limit Exceeded, DFS, 第1类搜索树
class Solution1_1 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
//        nums = IntStream.of(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        decreasingOrder(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        return dfs(0, target, nums);
    }

    private boolean dfs(int idx, int remainSum, int[] nums) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (remainSum < 0 || idx == len) {
            return false;
        }

        // general case
        for (int i = idx; i < len; i++) {
            if (dfs(i + 1, remainSum - nums[i], nums)) {
                return true;
            }
        }
        return false;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }


}

// Solution 1_2: 2ms, DFS,第1类搜索树，去重
class Solution1_2 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        return dfs(0, target, nums);
    }

    /**
     *
     * @param idx: index
     * @param remainSum: remaining sum need to be achieved by left nums[idx] tp nums[end]
     * @param nums: given array
     * @return: boolean
     */
    private boolean dfs(int idx, int remainSum, int[] nums) {
        if (remainSum < 0) {
            return false;
        }
        // base case
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (idx == len) {
            return false;
        }

        // general case
        for (int i = idx; i < len; i++) {
            if (dfs(i + 1, remainSum - nums[i], nums)) {
                return true;
            }
            int j = i;
            while (j < len && nums[j] == nums[i]) {
                j++;
            }
            i = j - 1;
        }
        return false;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

// Solution 1_3: 3ms, DFS,第1类搜索树，pruning with Boolean[][]
class Solution1_3 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int len = nums.length;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        Boolean[][] visited = new Boolean[len + 1][target + 1];
        return dfs(0, target, nums, visited);
    }

    /**
     *
     * @param idx: index
     * @param remainSum: remaining sum need to be achieved by left nums[idx] tp nums[end]
     * @param nums: given array
     * @param visited : visited[i][j]表示从nums[i]开始往后面任意取元素求和的结果能不能为j，如果是就true；否则false
     * @return: boolean
     */
    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] visited) {
        if (remainSum < 0) {
            return false;
        }
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        // base case
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (idx == len) {
            return false;
        }

        // general case
        for (int i = idx; i < len; i++) {
            if (dfs(i + 1, remainSum - nums[i], nums, visited)) {
                visited[idx][remainSum] = true;
                return true;
            }
        }
        visited[idx][remainSum] = false;
        return false;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

// Solution 1_4: 2ms, DFS,第1类搜索树，去重 + pruning with Boolean[][]
class Solution1_4 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int len = nums.length;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        Boolean[][] visited = new Boolean[len + 1][target + 1];
        return dfs(0, target, nums, visited);
    }

    /**
     *
     * @param visited : visited[i][j]表示从nums[i]开始往后面任意取元素求和的结果能不能为j，如果是就true；否则false
     */
    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] visited) {
        if (remainSum < 0) {
            return false;
        }
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        // base case
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (idx == len) {
            return false;
        }

        // general case
        for (int i = idx; i < len; i++) {
            if (dfs(i + 1, remainSum - nums[i], nums, visited)) {
                visited[idx][remainSum] = true;
                return true;
            }
            int j = i + 1;
            while (j < len && nums[j] == nums[i]) {
                j++;
            }
            i = j - 1;
        }
        visited[idx][remainSum] = false;
        return false;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

// Solution 1_5: 1ms, DFS，第1类搜索树，backtracking，不明白break的地方
class Solution1_5 {
    
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        /*nums = IntStream.of(nums).boxed().sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue).toArray();*/
        decreasingOrder(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        return dfs(0, target, nums);
    }
    
    private boolean dfs(int idx, int remainSum, int[] nums) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (remainSum < 0 || idx == len) {
            return false;
        }
        
        // general case
        if (remainSum - nums[idx] < 0) {
            return false;
        }
        for (int i = idx; i < len; i++) {
            if (dfs(i + 1, remainSum - nums[i], nums)) {
                return true;
            }
        }
        return false;
    }
    
    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    
}

// Solution 2_1: Time limit Exceeded, DFS 第2类搜索树
class Solution2_1 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        return dfs(0, target, nums);
    }

    private boolean dfs(int idx, int remainSum, int[] nums) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (idx == len || remainSum < 0 ) {
            return false;
        }

        // general case
        boolean res = dfs(idx + 1, remainSum, nums) || dfs(idx + 1, remainSum - nums[idx], nums);
        return res;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

// Solution 2_2: 45ms, DFS 第2类搜索树， Pruning with Boolean[][]
class Solution2_2 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int len = nums.length;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        Boolean[][] visited = new Boolean[len + 1][target + 1];
        return dfs(0, target, nums, visited);
    }

    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] visited) {
        if (remainSum < 0 ) {
            return false;
        }
        // base case
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (idx == len) {
            return false;
        }

        // general case
        visited[idx][remainSum] = dfs(idx + 1, remainSum, nums, visited) ||
                dfs(idx + 1, remainSum - nums[idx], nums, visited);
        return visited[idx][remainSum];
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

//Solution 2_3: 1ms, DFS, 第2类搜索树，去重
class Solution2_3 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
//        nums = IntStream.of(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        decreasingOrder(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        return dfs(0, target, nums);
    }

    private boolean dfs(int idx, int remainSum, int[] nums) {
        // base case
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (remainSum < 0 || idx == len) {
            return false;
        }

        // general case
        if (dfs(idx + 1, remainSum - nums[idx], nums)) {
            return true;
        }
        int j = idx + 1;
        while (j < len && nums[j] == nums[idx]) {
            j++;
        }
        return dfs(j, remainSum, nums);
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }


}

// Solution 2_4: 3ms, DFS, 第2类搜索树，去重 + Pruning
class Solution2_4 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int len = nums.length;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        Boolean[][] visited = new Boolean[len + 1][target + 1];
        return dfs(0, target, nums, visited);
    }

    private boolean dfs(int idx, int remainSum, int[] nums, Boolean[][] visited) {
        if (remainSum < 0 ) {
            return false;
        }
        // base case
        if (visited[idx][remainSum] != null) {
            return visited[idx][remainSum];
        }
        if (remainSum == 0) {
            return true;
        }
        int len = nums.length;
        if (idx == len) {
            return false;
        }

        // general case
        if (dfs(idx + 1, remainSum - nums[idx], nums,visited)) {
            visited[idx][remainSum] = true;
            return true;
        }
        int j = idx + 1;
        while (j < len && nums[j] == nums[idx]) {
            j++;
        }
        visited[idx][remainSum] = dfs(j, remainSum, nums,visited);
        return visited[idx][remainSum];
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

//Solution 3_1: 33ms, DP with for loop
class Solution3_1 {

    public boolean canPartition(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
            return false;
        }
        decreasingOrder(nums);
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 == 1) {
            return false;       // odd number can not be spilt to equal parts
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];     // dp[i][j]: if 0 to i in nums can reach sum of j

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j - nums[i - 1] >= 0) {     // choose current nums[i]
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                }
            }
            if (dp[nums.length][target]) {
                return true;
            }
        }
        return false;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

// Solution 3_2: 7ms, DP with rolling(reduced space complexity) for loop，注释部分是23ms
class Solution3_2 {

    // time = O(n * k), space = O(k)
    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1]; // dp[i]表示是否存在某些元素，使得这些元素的加合 = i
        dp[0] = true; // 空状态默认初始为true，表示空状态默认也是可以平均分割的
//        for (int num : nums) {
//            list.clear();
//            for (int i = 0; i < target; i++) {
//                if (dp[i]) {
//                    list.add(i);
//                }
//            }
//            for (Integer j : list) {
//                if (j + num < target) {
//                    dp[j + num] = true;
//                } else if (j + num == target) {
//                    return true;
//                }
//            }
//        }
        // 上面的注释部分可以优化成下面
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
            if (dp[target]) {
                return true;
            }
        }
        return false;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

// Solution 3_3: 48ms, DP with HashSet
class Solution3_3 {

    public boolean canPartition(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        decreasingOrder(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // general case
        int target = sum / 2;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        List<Integer> temp = new LinkedList<>();
        for (int num : nums) {
            temp.clear();
            for (int j : set) {
                temp.add(j + num);
            }
            set.addAll(temp);
            if (set.contains(target)) {
                return true;
            }
        }
        return false;
    }

    private void decreasingOrder(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

// Solution 4: 7ms, DFS but I do not understand break, beat 98%，不明白
class Solution4 {
    public boolean canPartition(int[] nums) {
        // stream api reverse order
        nums = IntStream.of(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        int result = 0;
        for (int num : nums) {
            result += num;
        }

        int half =  result / 2;
        if (half * 2 !=  result) {
            return false;
        }

        return dfs(nums, 0, 0, half);
    }

    private boolean dfs(int[] nums, int pos, int result, int target) {
        boolean found = false;
        for (int i = pos; i < nums.length; i++) {
            if ( result + nums[i] < target) {
                // If the current result is less than the target, still possible to find a solution, continue searching
                found |= dfs(nums, i + 1, result + nums[i], target);
            } else if (result + nums[i] == target) {
                // current result is equal to target, return true
                return true;
            } else {
                // current result is greater than the target, there is unnecessary searching, pruning
                break;
            }
            // Check if you find a solution
            if (found) {
                return true;
            }
        }
        // no solution, return false
        return false;
    }
}

}