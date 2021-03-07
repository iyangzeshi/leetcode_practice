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

import java.util.*;
// 2020-07-26 14:19:27
// Zeshi Yang
public class Leetcode0047PermutationsIi{
    // Java: permutations-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0047PermutationsIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // corner case
        if(nums == null) {
            return null;
        }

        // general case
        ArrayList<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        DFS(nums, 0, list, result);
        return result;
    }

    private void DFS(int[] nums, int level, ArrayList<Integer> list, List<List<Integer>> result) {
        // base case
        if (level == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // general case
        // set用来放第1类搜索树的第level层要加的元素，同一层，不能加duplicate的元素
        Set<Integer> set = new HashSet<>();
        for (int i  = level; i < nums.length; i++) {
            swap(nums, i, level);
            if (set.add(nums[level])) { // 注意这里索引是level，不是i
                list.add(nums[level]); // 注意这里索引是level，不是i
                DFS(nums, level + 1, list, result);
                list.remove(list.size() - 1);
            }
            swap(nums, i, level);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}