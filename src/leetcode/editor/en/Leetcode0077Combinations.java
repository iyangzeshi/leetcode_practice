//Given two integers n and k, return all possible combinations of k numbers out 
//of 1 ... n. 
//
// Example: 
//
// 
//Input: n = 4, k = 2
//Output:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// 
// Related Topics Backtracking 
// 👍 1504 👎 67

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:16:26
// Jesse Yang
public class Leetcode0077Combinations{
    // Java: combinations
    public static void main(String[] args) {
        Solution sol = new Leetcode0077Combinations().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2_1: BFS 第1类搜索树
class Solution {
    public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		//corner case
		if (n <= 0 || k > n) {
			return res;
		}
		
		Queue<List<Integer>> queue = new LinkedList<>();
	    queue.offer(new ArrayList<>());
	    for (int i = 1; i <= n; i++) {
		    int size = queue.size();
		    while (size-- > 0) {
			    List<Integer> list1 = queue.poll();
			    if (list1.size() == k) {
				    res.add(list1);
			    } else {
				    queue.add(list1);
				    List<Integer> list2 = new ArrayList<>(list1);
				    list2.add(i);
				    queue.add(list2);
			    }
		    }
	    }
		while (!queue.isEmpty()) {
			List<Integer> list = queue.poll();
			if (list.size() == k) {
				res.add(list);
			}
		}
		return res;
    }
	
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: DFS 第1类搜索树
class Solution1_1 {
    public List<List<Integer>> combine(int n, int k) {
        // S1: 第n层，每个元素长度都是n的情况
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k > n) {
            return result;
        }

        getResult(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void getResult(int n, int k, int index, List<Integer> list, List<List<Integer>> result) {
        //base case
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i <= n; i++) {
            list.add(i);
            getResult(n, k, i + 1,list, result);
            list.remove(list.size() - 1);
        }
    }
}

// Solution 1_2: DFS 第2类搜索树
class Solution1_2 {
    public List<List<Integer>> combine(int n, int k) {

        // 看每个数字要不要加进去
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k > n) {
            return result;
        }

        getResult(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void getResult(int n, int k, int level, List<Integer> list, List<List<Integer>> result) {
        //base case
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (level > n) {
            return;
        }

        // case 1: add num at level
        list.add(level);
        getResult(n, k, level + 1, list, result);

        //wall
        //remove the added number when backtracking to the upper level
        list.remove(list.size() - 1);

        // case 2: not add num at level
        getResult(n, k, level + 1, list, result);
    }
}

// Solution 2_1 BFS, 第1类搜索树
class Solution2_1 {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if (n <= 0 || k > n) {
			return res;
		}
		
		Queue<List<Integer>> queue = new LinkedList<>();
		for (int i = 1; i <= n - k + 1; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(i);
			queue.offer(list);
		}
		
		while (!queue.isEmpty()) {
			List<Integer> list = queue.poll();
			if (list.size() == k) {
				res.add(list);
			} else {
				int num = list.get(list.size() - 1);
				for (int i = num + 1; i <= n; i++) {
					List<Integer> newList = new ArrayList<>(list);
					newList.add(i);
					queue.offer(newList);
				}
			}
		}
		return res;
	}
	
}

// Solution 2_2 BFS, 第2类搜索树
class Solution2_2 {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		//corner case
		if (n <= 0 || k > n) {
			return res;
		}
		
		Queue<List<Integer>> queue = new LinkedList<>();
		queue.offer(new ArrayList<>());
		for (int i = 1; i <= n; i++) {
			int size = queue.size();
			while (size-- > 0) {
				List<Integer> list1 = queue.poll();
				if (list1.size() == k) {
					res.add(list1);
				} else {
					queue.add(list1);
					List<Integer> list2 = new ArrayList<>(list1);
					list2.add(i);
					queue.add(list2);
				}
			}
		}
		while (!queue.isEmpty()) {
			List<Integer> list = queue.poll();
			if (list.size() == k) {
				res.add(list);
			}
		}
		return res;
	}
	
}
}