//Given two words (beginWord and endWord), and a dictionary's word list, find al
//l shortest transformation sequence(s) from beginWord to endWord, such that:
//
//
// Only one letter can be changed at a time
// Each transformed word must exist in the word list. Note that beginWord is not
// a transformed word.
//
//
// Note:
//
//
// Return an empty list if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.
//
//
// Example 1:
//
//
//Input:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//Output:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
//
//
// Example 2:
//
//
//Input:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//Output: []
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible trans
//formation.
//
//
//
//
// Related Topics Array String Backtracking Breadth-first Search

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Leetcode0126WordLadderIi {
	// Java: word-ladder-ii
	
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0126WordLadderIi().new Solution();
		// TO TEST
		
		String beginWord = "hot";
		String endWord = "dog";
		String[] words = {"hot","dog"};
		List<String> wordList = new ArrayList<>(Arrays.asList(words));
		List<List<String>> result = sol.findLadders(beginWord, endWord, wordList);
		Set<String> set = new HashSet<>();
		for (List<String> list: result) {
			set.add(list.toString());
		}
		System.out.println(Arrays.deepToString(set.toArray()));
	}
	

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	public List<List<String>> findLadders(String beginWord, String endWord,
			List<String> wordList) {
		List<List<String>> paths = new ArrayList<>();
		Set<String> wordSet = new HashSet<>(wordList);
		// corner case
		if (wordList == null || !wordSet.contains(endWord)) {
			return paths;
		}
		Map<String, List<String>> graph = new HashMap<>(); // key - word, value - its neighbors
		boolean met = buildGraph(beginWord, endWord, wordSet, graph);
		List<String> list = new ArrayList<>();
		list.add(endWord);
		if (met) {
			dfsBuildPaths(endWord, beginWord, list, paths, graph);
		}
		return paths;
	}
	
	private boolean buildGraph(String beginWord, String endWord, Set<String> wordSet,
			Map<String, List<String>> graph) {
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		
		boolean met = false; // whether queue meets end
		// BFS, elements in next level will be deleted from wordSet after traversing this level
		while (!queue.isEmpty()) {
			Set<String> nextLevel = new HashSet<>();
			int size = queue.size();
			while (size-- > 0) {
				String cur = queue.poll();
				List<String> neighbors = getNeighbors(cur, wordSet);
				for (String str: neighbors) {
					if (str.equals(endWord)) {
						met = true;
					}
					graph.computeIfAbsent(str, k -> new ArrayList<>()).add(cur);
					if (!nextLevel.contains(str)) {
						queue.offer(str);
						nextLevel.add(str);
					}
				}
			}
			wordSet.removeAll(nextLevel);
			if (met) {
				return met;
			}
		}
		return met;
	}
	
	private List<String> getNeighbors(String cur, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String str = String.valueOf(chars);
				if (c != temp && dict.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
	
	private void dfsBuildPaths(String cur, String target, List<String> list,
			List<List<String>> paths, Map<String, List<String>> graph) {
		// base case
		if (cur.equals(target)) {
			List<String> copy = new ArrayList<>(list);
			Collections.reverse(copy);
			paths.add(copy);
			return;
		}
		
		List<String> nextLevel = graph.get(cur);
		
		for (String str : nextLevel) {
			list.add(str);
			dfsBuildPaths(str, target, list, paths, graph);
			list.remove(list.size() - 1);
		}
	}
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: one directional BFS
// 63 ms,击败了81.49% 的Java用户, 43.3 MB,击败了82.27% 的Java用户
/*
 如果HashSet的contains和add时间复杂度是O(1),
    则T(n)= O(V + E) = O(min(2^k, k *n), S(n) = O(min(2^k, k * n)
 如果HashSet的contains和add时间复杂度是O(k),
    则T(n)= O(V + E) = O(min(k * 2^k, k^2 *n), S(n) = O(min(k * 2^k, k^2 * n)
 n: 字典里单词个数，k:每个单词的长度
*/
class Solution1 {
	
	public List<List<String>> findLadders(String beginWord, String endWord,
			List<String> wordList) {
		List<List<String>> paths = new ArrayList<>();
		Set<String> wordSet = new HashSet<>(wordList);
		// corner case
		if (wordList == null || !wordSet.contains(endWord)) {
			return paths;
		}
		Map<String, List<String>> graph = new HashMap<>(); // key - word, value - its neighbors
		boolean met = buildGraph(beginWord, endWord, wordSet, graph);
		List<String> list = new ArrayList<>();
		list.add(endWord);
		if (met) {
			dfsBuildPaths(endWord, beginWord, list, paths, graph);
		}
		return paths;
	}
	
	private boolean buildGraph(String beginWord, String endWord, Set<String> wordSet,
			Map<String, List<String>> graph) {
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		
		boolean met = false; // whether queue meets end
		// BFS, elements in next level will be deleted from wordSet after traversing this level
		while (!queue.isEmpty()) {
			Set<String> nextLevel = new HashSet<>();
			int size = queue.size();
			while (size-- > 0) {
				String cur = queue.poll();
				List<String> neighbors = getNeighbors(cur, wordSet);
				for (String str: neighbors) {
					if (str.equals(endWord)) {
						met = true;
					}
					graph.computeIfAbsent(str, k -> new ArrayList<>()).add(cur);
					if (!nextLevel.contains(str)) {
						queue.offer(str);
						nextLevel.add(str);
					}
				}
			}
			wordSet.removeAll(nextLevel);
			if (met) {
				return met;
			}
		}
		return met;
	}
	
	private List<String> getNeighbors(String cur, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String str = String.valueOf(chars);
				if (c != temp && dict.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
	
	private void dfsBuildPaths(String cur, String target, List<String> list,
			List<List<String>> paths, Map<String, List<String>> graph) {
		// base case
		if (cur.equals(target)) {
			List<String> copy = new ArrayList<>(list);
			Collections.reverse(copy);
			paths.add(copy);
			return;
		}
		
		List<String> nextLevel = graph.get(cur);
		
		for (String str : nextLevel) {
			list.add(str);
			dfsBuildPaths(str, target, list, paths, graph);
			list.remove(list.size() - 1);
		}
	}
}

// Solution 2: bidirectional BFS
// 14 ms,击败了97.35% 的Java用户, 41.1 MB,击败了89.41% 的Java用户
/*
 如果HashSet的contains和add时间复杂度是O(1),
    则T(n)= O(V + E) = O(min(2^k, k *n), S(n) = O(min(2^k, k * n)
 如果HashSet的contains和add时间复杂度是O(k),
    则T(n)= O(V + E) = O(min(k * 2^k, k^2 *n), S(n) = O(min(k * 2^k, k^2 * n)
 n: 字典里单词个数，k:每个单词的长度
*/
class Solution2 {
	
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> paths = new ArrayList<>();
		Set<String> wordSet = new HashSet<>(wordList);
		// corner case
		if (wordList == null || !wordSet.contains(endWord)) {
			return paths;
		}
		
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		endSet.add(endWord);
		beginSet.add(beginWord);
		Map<String, List<String>> beginGraph = new HashMap<>();
		Map<String, List<String>> endGraph = new HashMap<>();
		
		Set<String> metLevel = getMetLevel(beginSet, beginGraph, endSet, endGraph, wordSet);
		
		for (String cur : metLevel) {
			List<List<String>> curToBegin = dfsBuildPath(cur, beginWord, new ArrayList<>(),
					new ArrayList<>(), beginGraph);
			List<List<String>> curToEnd = dfsBuildPath(cur, endWord, new ArrayList<>(),
					new ArrayList<>(), endGraph);
			connectPaths(paths, cur, curToBegin, curToEnd);
		}
		return paths;
	}
	
	
	// BFS to get met level, 永远是从set1往set2走，make sure set1 <= set2
	private Set<String> getMetLevel(Set<String> set1, Map<String, List<String>> graph1,
			Set<String> set2, Map<String, List<String>> graph2, Set<String> wordSet) {
		Set<String> metLevel = new HashSet<>();
		boolean met = false;
		// elements in this level will be deleted from wordSet after traversing this level
		while (!set1.isEmpty() && !set2.isEmpty()) {
			// make set1 <= set2, may exchange set1, set2; graph1, graph2
			if (set1.size() > set2.size()) {
				Set<String> tempSet = set1;
				set1 = set2;
				set2 = tempSet;
				Map<String, List<String>> tempGraph = graph1;
				graph1 = graph2;
				graph2 = tempGraph;
			}
			Set<String> nextLevel = new HashSet<>();
			for (String cur : set1) {
				List<String> neighbors = getNeighbors(cur, wordSet, set1);
				for (String str : neighbors) {
					if (set2.contains(str)) {
						met = true;
						metLevel.add(str);
					}
					nextLevel.add(str);
					graph1.computeIfAbsent(str, k -> new ArrayList<>()).add(cur);
				}
				
			}
			wordSet.removeAll(set1);
			set1 = nextLevel;
			if (met) {
				return metLevel;
			}
		}
		return metLevel;
	}
	
	/**
	 * return the list of neighbors of the cur which are in the dict and 1 distance
	 */
	private List<String> getNeighbors(String cur, Set<String> dict, Set<String> set1) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String str = String.valueOf(chars);
				if (c != temp && dict.contains(str) && !set1.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
	
	/**
	 * using dfs to find all possible paths from cur to target
	 * @param paths List<List<String>> paths
	 */
	private List<List<String>> dfsBuildPath(String cur, String target,
			List<String> list, List<List<String>> paths, Map<String, List<String>> graph) {
		// base case
		if (cur.equals(target)) {
			List<String> copy = new ArrayList<>(list);
			paths.add(copy);
			return paths;
		}
		
		List<String> nextLevel = graph.get(cur);
		for (String str : nextLevel) {
			list.add(str);
			dfsBuildPath(str, target, list, paths, graph);
			list.remove(list.size() - 1);
		}
		return paths;
	}
	
	private void connectPaths(List<List<String>> paths, String cur, List<List<String>> curToBegin,
			List<List<String>> curToEnd) {
		for (List<String> path1 : curToBegin) {
			Collections.reverse(path1);
			for (List<String> path2 : curToEnd) {
				List<String> temp = new ArrayList<>(path1);
				temp.add(cur);
				temp.addAll(path2);
				paths.add(temp);
			}
		}
	}
	
	
}

}