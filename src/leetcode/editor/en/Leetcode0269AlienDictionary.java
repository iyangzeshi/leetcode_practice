//There is a new alien language which uses the latin alphabet. However, the orde
//r among letters are unknown to you. You receive a list of non-empty words from t
//he dictionary, where words are sorted lexicographically by the rules of this new
// language. Derive the order of letters in this language. 
//
// Example 1: 
//
// 
//Input:
//[
//  "wrt",
//  "wrf",
//  "er",
//  "ett",
//  "rftt"
//]
//
//Output: "wertf"
// 
//
// Example 2: 
//
// 
//Input:
//[
//  "z",
//  "x"
//]
//
//Output: "zx"
// 
//
// Example 3: 
//
// 
//Input:
//[
//  "z",
//  "x",
//  "z"
//] 
//
//Output: "" 
//
//Explanation: The order is invalid, so return "".
// 
//
// Note: 
//
// 
// You may assume all letters are in lowercase. 
// If the order is invalid, return an empty string. 
// There may be multiple valid order of letters, return any one of them is fine.
// 
// 
// Related Topics Graph Topological Sort 
// 👍 1746 👎 343

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 2020-07-19 13:46:13
// Zeshi Yang
public class Leetcode0269AlienDictionary {

	// Java: alien-dictionary
	public static void main(String[] args) {
		Solution sol = new Leetcode0269AlienDictionary().new Solution();
		// TO TEST
		String[] words = {"wrtkj", "wrt"};
		String res = sol.alienOrder(words);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
// 就是找到DAG(Directed Acyclic Graphs)，然后实现TO(topological Ordering)
class Solution {
	// https://leetcode.com/problems/alien-dictionary/discuss/70115/3ms-Clean-Java-Solution-(DFS)
	private final int N = 26;
	public String alienOrder(String[] words) {
		boolean[][] adj = new boolean[N][N];
		int[] visited = new int[N];
		// visited: -1: not even existed
		//           0: unvisited
		//           1: visiting
		//           2: visited

		// 建图
		boolean hasPrefix = buildGraph(words, adj, visited);
		if (hasPrefix) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (visited[i] == 0) {                 // unvisited
				if(!dfs(adj, visited, sb, i)) return "";
			}
		}
		return sb.reverse().toString();
	}

	public boolean buildGraph(String[] words, boolean[][] adj, int[] visited) {
		Arrays.fill(visited, -1);                 // -1 = not even existed
		for (int i = 0; i < words.length; i++) {
			for (char c : words[i].toCharArray()) {
				visited[c - 'a'] = 0;
			}

			// words[]中相邻两辆相比 第一个不同的字母分别为c1, c2
			// c1 --> c2
			if (i > 0) {
				if (words[i - 1].length() > words[i].length() && words[i - 1].startsWith(words[i])) {
					return true;
				}
				String w1 = words[i - 1], w2 = words[i];
				int len = Math.min(w1.length(), w2.length());
				for (int j = 0; j < len; j++) {
					char c1 = w1.charAt(j), c2 = w2.charAt(j);
					if(c1 != c2) {
						adj[c1 - 'a'][c2 - 'a'] = true;
						break;
					}
				}
			}
		}
		return false;
	}
	
	public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
		visited[i] = 1;                            // 1 = visiting
		for (int j = 0; j < N; j++) {
			if (adj[i][j]) {                        // connected
				if (visited[j] == 1) {
					return false;  // 1 => 1, cycle
				}
				if (visited[j] == 0) {              // 0 = unvisited
					if (!dfs(adj, visited, sb, j)) {
						return false;
					}
				}
			}
		}
		visited[i] = 2;                           // 2 = visited
		sb.append((char) (i + 'a'));
		return true;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: DFS + HashMap记录edge
class Solution1_1 {
	// DFS + HashMap记录edge
	public String alienOrder(String[] words) {
		// corner case
		if (words == null || words.length == 0) {
			return "";
		}
		if (words.length == 1) {
			return words[0];
		}
		// 建图
		HashMap<Character, List<Character>> graph = new HashMap<>();
		// hasPrefix表示，word[i]是word[i - 1]的前缀的特殊例子（不包含本身），这种情况要return "";
		boolean hasPrefix = buildGraph(words, graph);
		if (hasPrefix) {
			return "";
		}
		HashMap<Character, Integer> statuses = new HashMap<>();
		StringBuilder path = new StringBuilder();

		// visited:
		//        null: unvisited
		//           1: visiting
		//           2: visited and have no cycle
		for (Character start : graph.keySet()) {
			if (containsCycle(start, graph, statuses, path)) {
				return "";
			}
		}

		return path.reverse().toString();
	}

	private boolean buildGraph(String[] words, HashMap<Character, List<Character>> graph) {
		for (String word: words) {
			for (char ch: word.toCharArray()) {
				if (!graph.containsKey(ch)) {
					graph.put(ch, new LinkedList<>());
				}
			}
		}
		for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];
			int len1 = word1.length();
			int len2 = word2.length();
			if (len1 > len2 && word1.startsWith(word2)) {
				return true;
			}

			int lenMin = Math.min(len1, len2);
			for (int j = 0; j < lenMin; j++) {
				char ch1 = word1.charAt(j);
				char ch2 = word2.charAt(j);
				if (ch1 != ch2) {
					if (!graph.get(ch1).contains(ch2)) {
						graph.get(ch1).add(ch2);
					}
					break;
				}
			}
		}
		return false;
	}

	// 有cycle的话，return true;否则false
	private boolean containsCycle(Character cur, HashMap<Character, List<Character>> graph,
			HashMap<Character, Integer> statuses, StringBuilder path) {
		// base case
		if (cur == null) {
			return false;
		}
		Integer status = statuses.get(cur);
		if (status != null) {
			if (status == 1) { // lookup the form
				return true;
			} else if (status == 2) { // look up the form
				return false;
			}
		}

		// general case
		statuses.put(cur, 1);
		for (Character next : graph.get(cur)) {
			if (containsCycle(next, graph, statuses, path)) {
				return true;
			}
		}
		statuses.put(cur, 2);
		path.append(cur);
		return false;
	}
}

// Solution 2: DFS + adjacent matrix
class Solution1_2 {
	// https://leetcode.com/problems/alien-dictionary/discuss/70115/3ms-Clean-Java-Solution-(DFS)
	private final int N = 26;
	public String alienOrder(String[] words) {
		boolean[][] adj = new boolean[N][N];
		int[] visited = new int[N];
		// visited: -1: not even existed
		//           0: unvisited
		//           1: visiting
		//           2: visited

		// 建图
		boolean hasPrefix = buildGraph(words, adj, visited);
		if (hasPrefix) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (visited[i] == 0) {                 // unvisited
				if(!dfs(adj, visited, sb, i)) return "";
			}
		}
		return sb.reverse().toString();
	}

	public boolean buildGraph(String[] words, boolean[][] adj, int[] visited) {
		Arrays.fill(visited, -1);                 // -1 = not even existed
		for (int i = 0; i < words.length; i++) {
			for (char c : words[i].toCharArray()) {
				visited[c - 'a'] = 0;
			}

			// words[]中相邻两辆相比 第一个不同的字母分别为c1, c2
			// c1 --> c2
			if (i > 0) {
				if (words[i - 1].length() > words[i].length() && words[i - 1].startsWith(words[i])) {
					return true;
				}
				String w1 = words[i - 1], w2 = words[i];
				int len = Math.min(w1.length(), w2.length());
				for (int j = 0; j < len; j++) {
					char c1 = w1.charAt(j), c2 = w2.charAt(j);
					if(c1 != c2) {
						adj[c1 - 'a'][c2 - 'a'] = true;
						break;
					}
				}
			}
		}
		return false;
	}
	
	public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
		visited[i] = 1; // 1 = visiting
		for (int j = 0; j < N; j++) {
			if (adj[i][j]) { // connected
				if (visited[j] == 1) {
					return false;  // 1 => 1, cycle
				}
				if (visited[j] == 0) { // 0 = unvisited
					if (!dfs(adj, visited, sb, j)) {
						return false;
					}
				}
			}
		}
		visited[i] = 2;// 2 = visited
		sb.append((char) (i + 'a'));
		return true;
	}

}

}