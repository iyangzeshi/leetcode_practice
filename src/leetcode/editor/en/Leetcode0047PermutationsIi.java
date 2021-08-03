//Given a collection of numbers that might contain duplicates, return all possib
//le unique permutations. 
//
// Example: 
//
// 
//Input: [1,1,2]
//Output:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//]
// 
// Related Topics Backtracking 
// 👍 1970 👎 61

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
// 2020-07-26 14:19:27
// Zeshi Yang
public class Leetcode0047PermutationsIi{
    // Java: permutations-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0047PermutationsIi().new Solution();
        // TO TEST
        int[] nums = {0, 0, 1, 2};
        List<List<Integer>> res = sol.permuteUnique(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        Map<Integer, Integer> countMap = new HashMap<>(); //key; num, value: count of this number
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        List<Integer> path = new ArrayList<>();
        dfs(path, nums.length, countMap, res);
        return res;
    }
    
    private void dfs(List<Integer> path, int len, Map<Integer, Integer> countMap,
            List<List<Integer>> res) {
        if (path.size() == len) {
            res.add(new ArrayList<>(path));
            return;
        }
    
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (count == 0) {
                continue;
            }
            // add this number into the current path
            path.add(num);
            countMap.put(num, count - 1);
        
            // continue the dfs exploration
            dfs(path, len, countMap, res);
        
            // set back
            path.remove(path.size() - 1);
            countMap.put(num, count);
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/**
 * 如果直接写permutation II的话，用Solution 2, 更好解释;
 * 如果先写的permutation I再写permutation II，用Solution 1比较好，需要改的code改的很少
 */
 
 
// Solution 1: dfs,每一层用HashSet去重, 第1类搜索树, T(n) = O(n!), S(n) = (n * n!)
// 1 ms,击败了99.37% 的Java用户, 39.9 MB,击败了36.51% 的Java用户
class Solution1 {
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        // corner case
        if (nums == null) {
            return null;
        }
        
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, path, res);
        return res;
    }
    
    private void dfs(int index, int[] nums, List<Integer> path, List<List<Integer>> res) {
        // base case
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        // general case
        // set用来放第1类搜索树的第level层要加的元素，同一层，不能加duplicate的元素
        Set<Integer> set = new HashSet<>(); // followup增加的地方
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            if (!set.contains(nums[index])) { // 注意这里索引是index，不是i, followup增加的地方
                set.add(nums[index]); // followup增加的地方
                path.add(nums[index]); // 注意这里索引是index，不是i, followup增加的地方
                dfs(index + 1, nums, path, res);
                path.remove(path.size() - 1);
            }
            swap(nums, i, index);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}

// Solution 2: dfs, map<Integer, Integer> 每个值和它出现的次数
// 2 ms,击败了55.74% 的Java用户,39.8 MB,击败了24.35% 的Java用户
// 每次走到一个Node之后，从HashMap里面找值（map本身的key不可能重复）
class Solution2 {
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        Map<Integer, Integer> countMap = new HashMap<>(); //key; num, value: count of this number
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        List<Integer> path = new ArrayList<>();
        dfs(path, nums.length, countMap, res);
        return res;
    }
    
    private void dfs(List<Integer> path, int len, Map<Integer, Integer> countMap,
            List<List<Integer>> res) {
        if (path.size() == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (count == 0) {
                continue;
            }
            // add this number into the current path
            path.add(num);
            countMap.put(num, count - 1);
            
            // continue the dfs exploration
            dfs(path, len, countMap, res);
            
            // set back
            path.remove(path.size() - 1);
            countMap.put(num, count);
        }
    }
    
}

}