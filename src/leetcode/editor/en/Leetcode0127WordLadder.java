//Given two words (beginWord and endWord), and a dictionary's word list, find the
// length of shortest transformation sequence from beginWord to endWord, such that:
//
// 
// Only one letter can be changed at a time. 
// Each transformed word must exist in the word list. 
// 
//
// Note: 
//
// 
// Return 0 if there is no such transformation sequence. 
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
//Output: 5
//
//Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog
//" -> "cog",
//return its length 5.
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
//Output: 0
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
// 
//
// 
// 
// Related Topics Breadth-first Search

package leetcode.editor.en;

import java.util.*;

public class Leetcode0127WordLadder {
	
	// Java: word-ladder
	public static void main(String[] args) {
		Solution sol = new Leetcode0127WordLadder().new Solution();
		// TO TEST
		String beginWord = "hit";
		String endWord = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		List<String> wordList = new ArrayList<>(Arrays.asList(words));
		int result = sol.ladderLength(beginWord, endWord, wordList);
		System.out.println(result);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		// corner case
		if (beginWord == null || endWord == null || wordList == null) {
			return 0;
		}
		
		Set<String> wordSet = new HashSet<>(wordList);
		// corner case
		if (!wordSet.contains(endWord)) {
			return 0;
		}
		
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		beginSet.add(beginWord);
		endSet.add(endWord);
		
		return bfs(wordSet, beginSet, endSet);
	}
	
	private int bfs(Set<String> wordSet, Set<String> set1, Set<String> set2) {
		int minLen = 1;
		// set2 will be in the wordSet
		while (!set1.isEmpty() && !set2.isEmpty()) {
			// make set1 <= set2
			if (set1.size() > set2.size()) {
				Set<String> tempSet = set1;
				set1 = set2;
				set2 = tempSet;
			}
			Set<String> nextLevel = new HashSet<>();
			for (String cur : set1) {
				List<String> neighbors = getNeighbor(cur, set1, wordSet);
				for (String neighbor: neighbors) {
					if (set2.contains(neighbor)) {
						return minLen + 1;
					}
					nextLevel.add(neighbor);
				}
			}
			/*
			important, can not remove word immediately find neighbor;
			otherwise, begin set will never reach to end set
			 */
			wordSet.removeAll(set1);
			minLen++;
			set1 = nextLevel;
		}
		return 0;
	}
	
	private List<String> getNeighbor(String cur, Set<String> beginSet, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char ch = 'a'; ch <= 'z'; ch++) {
				chars[i] = ch;
				String str = String.valueOf(chars);
				if (ch != temp && dict.contains(str) && !beginSet.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: one directional BFS
// 63 ms,击败了63.93% 的Java用户, 41.2 MB,击败了59.83% 的Java用户
/*
 如果HashSet的contains和add时间复杂度是O(1),
    则T(n)= O(V + E) = O(k * n), S(n) = O(min(2^k, k * n)
 如果HashSet的contains和add时间复杂度是O(k),
    则T(n)= O(V + E) = O(min(k * 2^k, k^2 *n), S(n) = O(min(k * 2^k, k^2 * n)
 n: 字典里单词个数，k:每个单词的长度
*/
class Solution1 {
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> wordSet = new HashSet<>(wordList);
		// corner case
		if (wordList == null || !wordSet.contains(endWord)) {
			return 0;
		}
		
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		int minLen = 1; // the distance from begin to element polled
		while(!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				String cur = queue.poll();
				List<String> neighbors = getNeighbors(cur, wordSet);
				for (String neighbor: neighbors) {
					if (neighbor.equals(endWord)) {
						return minLen + 1;
					}
					queue.offer(neighbor);
					wordSet.remove(neighbor); // so that there is no duplicates
				}
			}
			minLen++;
		}
		return 0;
	}
	
	private List<String> getNeighbors(String cur, Set<String> wordSet) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String str = String.valueOf(chars);
				if (c != temp && wordSet.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
}

// Solution 2: bi-directional BFS
// 10 ms,击败了99.38% 的Java用户, 40.2 MB,击败了85.47% 的Java用户
/*
 如果HashSet的contains和add时间复杂度是O(1),
    则T(n)= O(V + E) = O(min(2^k, k *n), S(n) = O(min(2^k, k * n)
 如果HashSet的contains和add时间复杂度是O(k),
    则T(n)= O(V + E) = O(min(k * 2^k, k^2 *n), S(n) = O(min(k * 2^k, k^2 * n)
 n: 字典里单词个数，k:每个单词的长度
 126 ms,击败了38.94% 的Java用户, 111.6 MB,击败了5.63% 的Java用户
 
put begin word and end word into the begin set and end set respectively
which one is smaller, bfs grow that one first, until begin set reaches end end set

be careful:
	after find neighbor of one word, can not remove it from wordSet at once,
	it can only be removed after traverse this whole level;
	otherwise, begin set will never reach end set
*/
class Solution2 {
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		// corner case
		if (beginWord == null || endWord == null || wordList == null) {
			return 0;
		}
		
		HashSet<String> wordSet = new HashSet<>(wordList);
		// corner case
		if (!wordSet.contains(endWord)) {
			return 0;
		}
		
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		beginSet.add(beginWord);
		endSet.add(endWord);
		
		return bfs(wordSet, beginSet, endSet);
	}
	
	private int bfs(Set<String> wordSet, Set<String> beginSet, Set<String> endSet) {
		int minLen = 1;
		// endSet will be in the wordSet
		while (!beginSet.isEmpty() && !endSet.isEmpty()) {
			// make beginSet <= endSet
			if (beginSet.size() > endSet.size()) {
				Set<String> tempSet = beginSet;
				beginSet = endSet;
				endSet = tempSet;
			}
			HashSet<String> nextLevel = new HashSet<>();
			for (String cur : beginSet) {
				List<String> neighbors = getNeighbors(cur, beginSet, wordSet);
				for (String neighbor: neighbors) {
					if (endSet.contains(neighbor)) {
						return minLen + 1;
					}
					nextLevel.add(neighbor);
				}
			}
			/*
			important, neighbors can not be removed word immediately after we find them;
			otherwise, begin set will never reach to end set
			 */
			wordSet.removeAll(beginSet);
			minLen++;
			beginSet = nextLevel;
		}
		return 0;
	}
	
	private List<String> getNeighbors(String cur, Set<String> beginSet, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String str = String.valueOf(chars);
				if (c != temp && dict.contains(str) && !beginSet.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
	
}

}