//Given a collection of distinct integers, return all possible permutations. 
//
// Example: 
//
// 
//Input: [1,2,3]
//Output:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// 
// Related Topics Backtracking 
// 👍 3984 👎 105

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 2020-07-26 12:19:13
// Jesse Yang
public class Leetcode0046Permutations{
    // Java: permutations
    public static void main(String[] args) {
        Solution sol = new Leetcode0046Permutations().new Solution();
        // TO TEST
        
        System.out.println();
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<List<Integer>> permute(int[] nums) {
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
/** 面试的时候，用Solution 1_1, 通用的方法，可以直接用到permutation II里面 */

// Solution 1_1: using swap to avoid duplicate 第1类搜索树, T(n) = O(n!), S(n) = (n * n!)
// 1 ms,击败了93.75% 的Java用户, 38.7 MB,击败了99.64% 的Java用户
/*
divided array into 2 parts, visited and visiting
every time, we can choose a number(any number) from visiting and add it to the visiting
using dfs
List<Integer> path is the current path.
 */
class Solution1_1 {
    
    public List<List<Integer>> permute(int[] nums) {
        // corner case
        if (nums == null) {
            return null;
        }
    
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, path, res);
        return res;
    }
    
    /*
    path is current path from top,
     */
    private void dfs(int index, int[] nums, List<Integer> path, List<List<Integer>> res) {
        // base case
        if (path.size() == nums.length) { // 或者用index == nums.length
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            // 注意，索引是index，不是i。第index层，加第index个元素
            path.add(nums[index]);
            dfs(index + 1, nums, path, res);
            path.remove(path.size() - 1);
            swap(nums, i, index);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}

// Solution 1_2: using swap to avoid duplicate 第1类搜索树, T(n) = O(n!), S(n) = (n * n!)
// 0 ms,击败了100.00% 的Java用户, 39.1 MB,击败了71.95% 的Java用户
/*
divided array into 2 parts, visited and visiting
every time, we can choose a number(any number) from visiting and add it to the visiting
using dfs
nums[0, index) is visited, is also the current path
 */
class Solution1_2 {
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //corner case
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        int index = 0;
        dfs(index, nums, res);
        return res;
    }
    
    // dfs
    /*
    nums[0, index] is in the visited
     */
    private void dfs(int index, int[] nums, List<List<Integer>> res) {
        // base case
        if (index == nums.length - 1) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) {
                temp.add(num);
            }
            res.add(temp);
            return;
        }
        
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            dfs(index + 1, nums, res);
            swap(nums, index, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}


// Solution 2: using HashSet to avoid duplicate, T(n) = O(n^(n + 1)), S(n) = (n!)
// 2 ms,击败了52.35% 的Java用户, 39.2 MB,击败了71.95% 的Java用户
class Solution2 {
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //corner case
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        List<Integer> path = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        dfs(nums, set, path, res);
        return res;
        
    }
    
    private void dfs(int[] nums, Set<Integer> set, List<Integer> path,
            List<List<Integer>> result) {
        // base case
        if (path.size() == nums.length) { // 或者用index == nums.length
            result.add(new ArrayList<>(path));
            return;
        }
    
        for (int num : nums) {
            if (set.contains(num)) {
                continue;
            }
            path.add(num);
            set.add(num);
            dfs(nums, set, path, result);
            path.remove(path.size() - 1);
            set.remove(num);
        }
    }
    
}

// Solution 3: dfs, T(n) = O(n^(n + 1)), S(n) = (n!)
// 3 ms,击败了15.21% 的Java用户, 39.7 MB,击败了8.87% 的Java用户
/*
map<Integer, Integer> 每个值和它出现的次数
初始化好HashMap,用来存number和它出现的次数，作为candidate
每次从candidate里面取一个数字放到path里面

备注：这是通用的方法，可以直接用到permutation II里面
 */
class Solution3 {
    
    public List<List<Integer>> permute(int[] nums) {
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