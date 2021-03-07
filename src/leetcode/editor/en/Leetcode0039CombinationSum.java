//Given a set of candidate numbers (candidates) (without duplicates) and a targe
//t number (target), find all unique combinations in candidates where the candidat
//e numbers sums to target. 
//
// The same repeated number may be chosen from candidates unlimited number of ti
//mes. 
//
// Note: 
//
// 
// All numbers (including target) will be positive integers. 
// The solution set must not contain duplicate combinations. 
// 
//
// Example 1: 
//
// 
//Input: candidates = [2,3,6,7], target = 7,
//A solution set is:
//[
//  [7],
//  [2,2,3]
//]
// 
//
// Example 2: 
//
// 
//Input: candidates = [2,3,5], target = 8,
//A solution set is:
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//]
// 
//
// 
// Constraints: 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// Each element of candidate is unique. 
// 1 <= target <= 500 
// 
// Related Topics Array Backtracking 
// 👍 3918 👎 119

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// 2020-07-26 12:17:20
// Zeshi Yang
public class Leetcode0039CombinationSum{
    // Java: combination-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0039CombinationSum().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: BFS 第1类搜索树
class Solution {
    
    //Zeshi Yang's code
    // BFS 第1类搜索树
    // if sum == target, size--;答案里加上这个答案
    // if sum > target， size--; continue
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // corner case
        if (candidates == null) {
            return result;
        }
        
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        Queue<Integer> sums = new LinkedList<>(); // 用这个来储存list的求和
        ArrayList<Integer> temp = new ArrayList<>();
        
        int size = 0;
        int sum = 0;
        int length = candidates.length;
        
        queue.offer(new ArrayList<>(temp));
        sums.add(0);
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size > 0) {
                temp = queue.poll();
                sum = sums.poll();
                
                if (sum == target) {
                    size--;
                    result.add(new ArrayList<>(temp));
                } else if (sum > target) {
                    size--;
                } else {
                    int index = 0;
                    if (temp.size() == 0) {
                        index = 0;
                    } else {
                        int lastNumOfTemp = temp.get(temp.size() - 1);
                        index = indexOf(candidates, lastNumOfTemp);
                    }
                    
                    for (int i = index; i < length; i++) {
                        sum += candidates[i];
                        
                        sums.add(sum);
                        temp.add(candidates[i]);
                        queue.add(new ArrayList<>(temp));
                        
                        temp.remove(temp.size() - 1);
                        sum -= candidates[i];
                    }
                    size--;
                }
            }
        }
        
        return result;
    }
    
    private int indexOf(int[] array, int num) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] == num) {
                return i;
            }
        }
        return -1;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)


 // Solution 1: BFS 第1类搜索树
/*
 if sum == target, size--;答案里加上这个答案
 if sum > target， size--; continue
 */
class Solution1 {
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // corner case
        if (candidates == null) {
            return result;
        }
        
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        Queue<Integer> sums = new LinkedList<>(); // 用这个来储存list的求和
        ArrayList<Integer> temp = new ArrayList<>();
        
        int size = 0;
        int sum = 0;
        int length = candidates.length;
        
        queue.offer(new ArrayList<>(temp));
        sums.add(0);
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size > 0) {
                temp = queue.poll();
                sum = sums.poll();
                
                if (sum == target) {
                    size--;
                    result.add(new ArrayList<>(temp));
                } else if (sum > target) {
                    size--;
                    continue;
                } else {
                    int index = 0;
                    if (temp.size() == 0) {
                        index = 0;
                    } else {
                        int lastNumOfTemp = temp.get(temp.size() - 1);
                        index = indexOf(candidates, lastNumOfTemp);
                    }
                    
                    for (int i = index; i < length; i++) {
                        sum += candidates[i];
                        
                        sums.add(sum);
                        temp.add(candidates[i]);
                        queue.add(new ArrayList<>(temp));
                        
                        temp.remove(temp.size() - 1);
                        sum -= candidates[i];
                    }
                    size--;
                }
            }
        }
        
        return result;
    }
    
    private int indexOf(int[] array, int num) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] == num) {
                return i;
            }
        }
        return -1;
    }
}

// Solution 2: DFS 第1类搜索树
/*
 第2类搜索树应该做不了，因为不知道总共有多少层
 第2类搜索树的原理是，在第n层，判断第n个元素要不要加入到list里面
*/
class Solution2 {
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //corner case
        if (candidates == null) {
            return result;
        }
        
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        int level = 0;
        int index = 0;
        getResult(index, sum, target, candidates, list, result);
        return result;
    }
    
    private void getResult(int index, int sum, int target, int[] candidates, List<Integer> list,
            List<List<Integer>> result) {
        //corner case
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        int length = candidates.length;
        
        for (int i = index; i < length; i++) {
            list.add(candidates[i]);
            sum += candidates[i];
            getResult(i, sum, target, candidates, list, result);
            sum -= candidates[i];
            // 或者上面3行改成
            // getResult(i, sum + candidates[i], target, candidates, list, result);
            list.remove(list.size() - 1);
        }
    }
}
}