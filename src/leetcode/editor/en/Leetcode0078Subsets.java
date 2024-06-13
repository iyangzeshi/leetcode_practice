//Given a set of distinct integers, nums, return all possible subsets (the power
// set). 
//
// Note: The solution set must not contain duplicate subsets. 
//
// Example: 
//
// 
//Input: nums = [1,2,3]
//Output:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics Array Backtracking Bit Manipulation 
// 👍 3935 👎 85

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 2020-07-26 12:08:12
// Jesse Yang
public class Leetcode0078Subsets {

	// Java: subsets
	public static void main(String[] args) {
		Solution sol = new Leetcode0078Subsets().new Solution();
		// TO TEST
        int[] nums = {1,2,3};
        List<List<Integer>> res = sol.subsets(nums);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
// Solution 3: 0ms, DFS 第1类搜索树, T(n) = O(2^n), S(n) = O(2^n)
// 1 ms,击败了61.94% 的Java用户, 40.8 MB,击败了5.99% 的Java用户
/*
 [a,b,c]
                          {}
 level0           {a}     {b}     {c}     1st can choose either a b c    i = 0, 1, 2
 level1       {a,b}{a,c}  {b,c}           2st can choose what's left      i = 1, 2
 level2   {a,b,c}                         i = 2
 null
*/
class Solution {
	
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		// corner case
		if (nums == null) {
			return result;
		}
		result.add(new ArrayList<>());
		dfs(0, new ArrayList<>(), nums, result);
		return result;
	}
	
	private void dfs(int idx, List<Integer> list, int[] nums, List<List<Integer>> result) {
		// base case
		if (idx == nums.length) {
			return;
		}
		
		for (int i = idx; i < nums.length; i++) {
			list.add(nums[i]);
			result.add(new ArrayList<>(list));
			dfs(i + 1, list, nums, result);
			// wall
			list.remove(list.size() - 1);
		}
	}
}

//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: 1ms, BFS 第1类搜索树
// 5 ms,击败了5.30% 的Java用户, 40.2 MB,击败了5.99% 的Java用户
/*
        [a,b,c]
                          {}
 level0           {a}     {b}     {c}     1st can choose either a b c    i = 0, 1, 2
 level1       {a,b}{a,c}  {b,c}           2st can choose what's left     i = 1, 2
 level2   {a,b,c}                         i = 2
 null
*/
class Solution1 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        //corner case
        if (nums == null) {
            return result;
        }
		
        int length = nums.length;
        Queue<ArrayList<Integer>> numberQueue = new LinkedList<>();
        Queue<ArrayList<Integer>> indexQueue = new LinkedList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        int size;
        numberQueue.offer(new ArrayList<>(temp));
        indexQueue.offer(new ArrayList<Integer>() {{
                             add(-1);
                         }}
        );

        while (!numberQueue.isEmpty()) {
            size = numberQueue.size();
            // cache目前这一层里面有多少个组合
            while (size > 0) {
                ArrayList<Integer> indexList = indexQueue.poll();
                int index = indexList.get(indexList.size() - 1);
                temp = numberQueue.poll();
                result.add(new ArrayList<>(temp));
                //找到poll出来的结果里面最后一个数字的index，下面的从index + 1的数字开始加
                for (int i = index + 1; i < length; i++) {
                    temp.add(nums[i]);
                    numberQueue.offer(new ArrayList<>(temp));
                    temp.remove(temp.size() - 1);

                    indexList.add(i);
                    indexQueue.offer(new ArrayList<>(indexList));
                    indexQueue.remove(indexQueue.size() - 1);
                }
                size--;
            }
        }
        return result;
    }

    private int indexOf(int[] nums, int num) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == num) {
                return i;
            }
        }
        return -1;
    }
}


// Solution 2: 1ms, BFS 第2类搜索树
// 3 ms,击败了5.30% 的Java用户, 40.8 MB,击败了5.99% 的Java用户
/*
 [a,b,c]
                                      {}
 level 0				    {a}                         {}
 level 1          {a, b}          {a}            {b}	    {}
 level 2  {a, b, c}   {a, b}  {a,c}   {a}  {b, c}  {b}  {c} {}
 null
*/
class Solution2 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        // corner case
        if (nums == null) {
            return res;
        }
        int len = nums.length;
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        int level = 0;

        queue.offer(new ArrayList<>(temp));
        // 或者用 (ArrayList) temp.clone()， temp.clone()是shallow copy,返回成Object，再强制转换成ArrayList

        while (level < len) {

            int size = queue.size();

            while (size > 0) {
                temp = queue.poll();
                queue.offer(new ArrayList<>(temp));
                temp.add(nums[level]);
                queue.offer(new ArrayList<>(temp));
                size--;
            }
            level++;
        }
        return (List) queue;
    }
}


// Solution 3: 0ms, DFS 第1类搜索树
// 1 ms,击败了61.94% 的Java用户, 40.8 MB,击败了5.99% 的Java用户
/*
 [a,b,c]
                          {}
 level0           {a}     {b}     {c}     1st can choose either a b c    i = 0, 1, 2
 level1       {a,b}{a,c}  {b,c}           2st can choose what's left      i = 1, 2
 level2   {a,b,c}                         i = 2
 null
*/
class Solution3 {

    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
		// corner case
        if (nums == null) {
            return result;
        }
        result.add(new ArrayList<>());
        dfs(0, new ArrayList<>(), nums, result);
        return result;
    }

    private void dfs(int idx, List<Integer> list, int[] nums, List<List<Integer>> result) {
        // base case
		if (idx == nums.length) {
            return;
        }
		
        for (int i = idx; i < nums.length; i++) {
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            dfs(i + 1, list, nums, result);
            // wall
            list.remove(list.size() - 1);
        }
    }
}


// Solution 4_1: 0ms, DFS 第2类搜索树
// 1 ms,击败了61.94% 的Java用户, 38.9 MB,击败了97.07% 的Java用户
/*
 [a,b,c]
                                      {}
 level 0				    {a}                         {}
 level 1          {a, b}          {a}            {b}	    {}
 level 2  {a, b, c}   {a, b}  {a,c}   {a}  {b, c}  {b}  {c} {}
 null
*/
class Solution4_1 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // corner case
        if (nums == null) {
            return result;
        }
        dfs(0, nums, new ArrayList<>(), result);

        return result;
    }

    private void dfs(int idx, int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // case 1: add num at level
	    list.add(nums[idx]);
	    dfs(idx + 1, nums, list, result);
	    list.remove(list.size() - 1);
	
	    // case 2: not add character at index
	    dfs(idx + 1, nums, list, result);
    }
}

// Solution 4_2: 0ms, DFS 第2类搜索树
//先加右边也要backtracking
// 0 ms,击败了100.00% 的Java用户,39.3 MB,击败了45.56% 的Java用户
// [a,b,c]

/*
                                      {}
 level 0				     {}                     {a}
 level 1                {}      {b}            {a}	        {a,b}
 level 2             {}  {c}  {b}{b,c}      {a} {a, c}  {a, b} {a, b, c}
 null
*/
class Solution4_2 {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // corner case
        if (nums == null) {
            return result;
        }
        dfs(0, nums, new ArrayList<>(), result);

        return result;

    }

    private void dfs(int level, int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (level == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // case 2: not add num at level
        dfs(level + 1, nums, list, result);
	    list.add(nums[level]);
	
	    // case 1: add character at index
        dfs(level + 1, nums, list, result);

        // wall
        // remove the added character when backtracking to the upper level
        // backtracking，不能用list.remove(level)，因为有可能出现{a, b}，level = 3的情况
        list.remove(list.size() - 1);
    }
}

}