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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 2020-07-19 13:46:13
// Jesse Yang
public class Leetcode0269AlienDictionary {

	// Java: alien-dictionary
	public static void main(String[] args) {
		Solution sol = new Leetcode0269AlienDictionary().new Solution();
		// TO TEST
		String[] words = {"wrt", "wrtkj"};
		String res = sol.alienOrder(words);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	public String alienOrder(String[] words) {
		// corner case
		if (words == null || words.length == 0) {
			return "";
		}
		if (words.length == 1) {
			return words[0];
		}
		
		// 建图
		Map<Character, Set<Character>> graph = new HashMap<>();
		
		// hasPrefix表示，word[i]是word[i - 1]的前缀的特殊例子（不包含本身），这种情况要return "";
		boolean hasPrefix = buildGraph(words, graph);
		if (hasPrefix) {
			return "";
		}
		Map<Character, Integer> statusMap = new HashMap<>();
		StringBuilder path = new StringBuilder();
		
		// visited:
		//        null: unvisited
		//           1: visiting
		//           2: visited and have no cycle
		for (Character start : graph.keySet()) {
			if (containsCycle(start, graph, statusMap, path)) {
				return "";
			}
		}
		return path.reverse().toString();
	}
	
	// return map<key - char, value - its followers>
	private boolean buildGraph(String[] words, Map<Character, Set<Character>> graph) {
		for (String word: words) {
			for (char ch: word.toCharArray()) {
				graph.putIfAbsent(ch, new HashSet<>());
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
			
			int minLen = Math.min(len1, len2);
			for (int j = 0; j < minLen; j++) {
				char ch1 = word1.charAt(j);
				char ch2 = word2.charAt(j);
				if (ch1 != ch2) {
					graph.get(ch1).add(ch2);
					break;
				}
			}
		}
		return false;
	}
	
	// 有cycle的话，return true;否则false
	private boolean containsCycle(Character cur, Map<Character, Set<Character>> graph,
			Map<Character, Integer> statusMap, StringBuilder path) {
		// base case
		if (cur == null) {
			return false;
		}
		Integer status = statusMap.get(cur);
		if (status != null) {
			if (status == 1) { // lookup the form
				return true;
			} else if (status == 2) { // look up the form
				return false;
			}
		}
		
		// general case
		statusMap.put(cur, 1);
		for (Character next : graph.get(cur)) {
			if (containsCycle(next, graph, statusMap, path)) {
				return true;
			}
		}
		statusMap.put(cur, 2);
		path.append(cur);
		return false;
	}
}
//leetcode submit region end(Prohibit modification and deletion)
/* 面试的时候，用Solution 1_2 */
// Solution 1: 用Set<Character>来记录edge

// Solution 1_1: DFS + HashMap<character, Set<Character>>记录edge
class Solution1_1 {
	
	public String alienOrder(String[] words) {
		// corner case
		if (words == null || words.length == 0) {
			return "";
		}
		if (words.length == 1) {
			return words[0];
		}
		
		// 建图
		Map<Character, Set<Character>> graph = new HashMap<>();
		
		// hasPrefix表示，word[i]是word[i - 1]的前缀的特殊例子（不包含本身），这种情况要return "";
		boolean hasPrefix = buildGraph(words, graph);
		if (hasPrefix) {
			return "";
		}
		Map<Character, Integer> statusMap = new HashMap<>();
		StringBuilder path = new StringBuilder();

		// visited:
		//        null: unvisited
		//           1: visiting
		//           2: visited and have no cycle
		for (Character start : graph.keySet()) {
			if (containsCycle(start, graph, statusMap, path)) {
				return "";
			}
		}
		return path.reverse().toString();
	}

	// return map<key - char, value - its followers>
	private boolean buildGraph(String[] words, Map<Character, Set<Character>> graph) {
		for (String word: words) {
			for (char ch: word.toCharArray()) {
				graph.putIfAbsent(ch, new HashSet<>());
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

			int minLen = Math.min(len1, len2);
			for (int j = 0; j < minLen; j++) {
				char ch1 = word1.charAt(j);
				char ch2 = word2.charAt(j);
				if (ch1 != ch2) {
					graph.get(ch1).add(ch2);
					break;
				}
			}
		}
		return false;
	}

	// 有cycle的话，return true;否则false
	private boolean containsCycle(Character cur, Map<Character, Set<Character>> graph,
			Map<Character, Integer> statusMap, StringBuilder path) {
		// base case
		if (cur == null) {
			return false;
		}
		Integer status = statusMap.get(cur);
		if (status != null) {
			if (status == 1) { // lookup the form
				return true;
			} else if (status == 2) { // look up the form
				return false;
			}
		}

		// general case
		statusMap.put(cur, 1);
		for (Character next : graph.get(cur)) {
			if (containsCycle(next, graph, statusMap, path)) {
				return true;
			}
		}
		statusMap.put(cur, 2);
		path.append(cur);
		return false;
	}
}

// Solution 1_2: DFS + Node class记录edge
class Solution1_2 {
	
	private static final int N = 'z' - 'a' + 1;
	
	public String alienOrder(String[] words) {
		// corner case
		if (words == null || words.length == 0) {
			return "";
		}
		if (words.length == 1) {
			return words[0];
		}
		
		Node[] nodes = new Node[N];
		// build graph, hasPrefix表示，word[i]是word[i - 1]的前缀的特殊例子（不包含本身），这种情况要return "";
		boolean hasPrefix = buildGraph(words, nodes);
		if (hasPrefix) {
			return "";
		}
		StringBuilder path = new StringBuilder();
		
		for (Node node: nodes) {
			if (node != null && containsCycle(node.ch, nodes, path)) {
				return "";
			}
		}
		return path.reverse().toString();
	}
	
	private boolean buildGraph(String[] words, Node[] nodes) {
		for (String word: words) {
			for (char ch: word.toCharArray()) {
				if (nodes[ch - 'a'] == null) {
					nodes[ch - 'a'] = new Node(ch);
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
			
			int minLen = Math.min(len1, len2);
			for (int j = 0; j < minLen; j++) {
				char ch1 = word1.charAt(j);
				char ch2 = word2.charAt(j);
				if (ch1 != ch2) {
					nodes[ch1 - 'a'].nexts.add(ch2);
					break;
				}
			}
		}
		return false;
	}
	
	// 有cycle的话，return true;否则false
	private boolean containsCycle(Character cur, Node[] nodes, StringBuilder path) {
		// base case
		if (cur == null) {
			return false;
		}
		Node node = nodes[cur - 'a'];
		Status status = node.status;
		if (status == Status.VISITING) { // lookup the form
			return true;
		} else if (status == Status.VISITED) { // look up the form
			return false;
		}
		
		// general case
		node.status = Status.VISITING;
		for (Character next : node.nexts) {
			if (containsCycle(next, nodes, path)) {
				return true;
			}
		}
		node.status = Status.VISITED;
		path.append(cur);
		return false;
	}
	
	class Node {
		
		private final char ch;
		Status status;
		Set<Character> nexts;
		
		public Node(char ch) {
			this.ch = ch;
			status = Status.INITIAL;
			nexts = new HashSet<>();
		}
		
	}
	
}

enum Status {
	INITIAL,
	VISITING,
	VISITED
}

// Solution 2: DFS + adjacent matrix来记录edge
/*
https://leetcode.com/problems/alien-dictionary/discuss/70115/3ms-Clean-Java-Solution-(DFS)
 */
class Solution2 {
	
	private static final int N = 'z' - 'a' + 1;
	
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
		StringBuilder path = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (visited[i] == 0) {                 // unvisited
				if (containsCycle(adj, visited, path, i)) {
					return "";
				}
			}
		}
		return path.reverse().toString();
	}
	
	// build graph adjacent, if adj[i][j] is true, means char i + 'a' is smaller than j + 'a'
	public boolean buildGraph(String[] words, boolean[][] adj, int[] visited) {
		Arrays.fill(visited, -1);                 // -1 = not even existed
		for (String word : words) {
			for (char c : word.toCharArray()) {
				visited[c - 'a'] = 0;
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
			
			int minLen = Math.min(len1, len2);
			for (int j = 0; j < minLen; j++) {
				char ch1 = word1.charAt(j);
				char ch2 = word2.charAt(j);
				if (ch1 != ch2) {
					adj[ch1 -'a'][ch2 - 'a'] = true;
					break;
				}
			}
		}
		return false;
	}
	
	public boolean containsCycle(boolean[][] adj, int[] visited, StringBuilder path, int index) {
		// base case
		if (visited[index] == 1) {
			return true;
		} else if (visited[index] == 2) {
			return false;
		}
		
		visited[index] = 1; // 1 = visiting
		for (int j = 0; j < N; j++) {
			if (adj[index][j]) { // connected
				if (containsCycle(adj, visited, path, j)) {
					return true;
				}
			}
		}
		visited[index] = 2; // 2 = visited
		path.append((char) (index + 'a'));
		return false;
	}

}

}