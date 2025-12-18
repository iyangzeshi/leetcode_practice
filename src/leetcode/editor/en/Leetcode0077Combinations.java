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
/*
Solution 1_1: DFS 第1类搜索树,
核心思想： 第 i 层 都是从素有元素中取 i个元素的组合
第1层                           []
             /              |         \          \
第2层      [1]             [2]        [3]         [4]
          / | \          /    \         \
第3层  [1,2][1,3][1,4]   [2,3][2,4]    [3,4]

T(n, k) = O(C(n,k) * k) = O(n!/(k!(n−k)!) * k) =O(n!/((k - 1)!(n−k)!)), C表示组合数
S(n, k) = O(n + k) = O(n)
 */
class Solution {
	public List<List<Integer>> combine(int n, int k) {
		// S1: 第n层，每个元素长度都是n的情况
		List<List<Integer>> res = new ArrayList<>();
		if (n <= 0 || k > n) {
			return res;
		}
		
		dfs(1, new ArrayList<>(), res, n, k);
		return res;
	}
	
	private void dfs(int index, List<Integer> list, List<List<Integer>> res, int n, int k) {
		//base case
		if (list.size() == k) {
			res.add(new ArrayList<>(list));
			return;
		}
		
		for (int i = index; i <= n; i++) {
			list.add(i);
			dfs(i + 1, list, res, n, k);
			list.remove(list.size() - 1);
		}
	}
}
//leetcode submit region end(Prohibit modification and deletion)
/*
Solution 1_1: DFS 第1类搜索树,
核心思想： 第 i 层 都是从素有元素中取 i个元素的组合
第1层                           []
             /              |         \          \
第2层      [1]             [2]        [3]         [4]
          / | \          /    \         \
第3层  [1,2][1,3][1,4]   [2,3][2,4]    [3,4]

T(n, k) = O(C(n,k) * k) = O(n!/(k!(n−k)!) * k) =O(n!/((k - 1)!(n−k)!)), C表示组合数
S(n, k) = O(n + k) = O(n)
 */
class Solution1_1 {
    public List<List<Integer>> combine(int n, int k) {
        // S1: 第n层，每个元素长度都是n的情况
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k > n) {
            return res;
        }

        dfs(1, new ArrayList<>(), res, n, k);
        return res;
    }

    private void dfs(int index, List<Integer> list, List<List<Integer>> res, int n, int k) {
        //base case
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs(i + 1, list, res, n, k);
            list.remove(list.size() - 1);
        }
    }
}

/*
Solution 1_2: DFS 第2类搜索树
核心思想：每次遇到第i个数字的时候，都有加入List和不加入List 2种可能. List本身没有顺序要求
Subsets 决策树（以 nums = [1,2,3] 为例）
------------------------------------------

                 []
           /            \
         [1]            []
        /   \          /  \
    [1,2]  [1]      [2]   []
     / \    |       / \    |
[1,2,3][1,2][1,3][2,3][2][3]

T(n, k) = O(C(n,k) * k) = O(n!/(k!(n−k)!) * k) =O(n!/((k - 1)!(n−k)!)), C表示组合数
S(n, k) = O(n + k) = O(n)
 */
class Solution1_2 {
    public List<List<Integer>> combine(int n, int k) {

        // 看每个数字要不要加进去
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k > n) {
            return result;
        }

        dfs(1, new ArrayList<>(), result, n, k);
        return result;
    }

	// 对每个数字 level，都做“选 / 不选”的 2 种决策，生成所有选择 k 个数字的组合。
    private void dfs(int level, List<Integer> list, List<List<Integer>> res, int n, int k) {
        //base case
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (level > n) {
            return;
        }

        // case 1: add num at level
        list.add(level);
        dfs(level + 1, list, res, n, k);

        //wall
        //remove the added number when backtracking to the upper level
        list.remove(list.size() - 1);

        // case 2: not add num at level
        dfs(level + 1, list, res, n, k);
    }
}

/*
Solution 2_1 BFS, 第1类搜索树
核心思想： 第 i 层 都是从所有元素中取 i个元素的组合

第1层                           []
             /              |         \          \
第2层      [1]             [2]        [3]         [4]
          / | \          /    \         \
第3层  [1,2][1,3][1,4]   [2,3][2,4]    [3,4]
1. BFS 使用队列，队列中的每个 list 表示一个“部分组合”。
2. 每次扩展 list 时，只从 比当前最后一个数更大的数字 开始扩展（避免重复、保证升序）。
3. 当 list 长度达到 k 时加入结果。
T(n,k) = O(C(n,k) * k) = O(n!/(k!(n−k)!) * k) =O(n!/((k - 1)!(n−k)!))
S(n,k) = O(C(n,k) * k) = O(n!/(k!(n−k)!) * k) =O(n!/((k - 1)!(n−k)!))
 */
	
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

/*
Solution 2_2 BFS, 第2类搜索树
核心思想：每次遇到第i个数字的时候，都有加入List和不加入List 2种可能. List本身没有顺序要求
Subsets 决策树（以 nums = [1,2,3] 为例）
------------------------------------------

                 []
           /            \
         [1]            []
        /   \          /  \
    [1,2]  [1]      [2]   []
     / \    |       / \    |
[1,2,3][1,2][1,3][2,3][2][3]

T(n,k) = O(2^n * k) ∵ 每次有2种可能，进行了n次，所以是2^n，每次复制List的时间复杂度是(k)
S(n,k) = O(n * k) ∵ 每次有2种可能，进行了n次，所以是2^n，每次复制List的时间复杂度是(k)
 */
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
					queue.add(list1); // branch 1: 不加入List
					List<Integer> list2 = new ArrayList<>(list1); // branch 2: 加入List
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
