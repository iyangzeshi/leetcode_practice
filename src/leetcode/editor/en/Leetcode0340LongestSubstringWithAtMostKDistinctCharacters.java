//Given a string, find the length of the longest substring T that contains at mo
//st k distinct characters. 
//
// Example 1: 
//
// 
// 
//Input: s = "eceba", k = 2
//Output: 3
//Explanation: T is "ece" which its length is 3. 
//
// 
// Example 2: 
//
// 
//Input: s = "aa", k = 1
//Output: 2
//Explanation: T is "aa" which its length is 2.
// 
// 
// Related Topics Hash Table String Sliding Window 
// 👍 1118 👎 42

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

// 2020-07-23 17:23:28
// Zeshi Yang
public class Leetcode0340LongestSubstringWithAtMostKDistinctCharacters {

	// Java: longest-substring-with-at-most-k-distinct-characters
	public static void main(String[] args) {
		Solution sol = new Leetcode0340LongestSubstringWithAtMostKDistinctCharacters().new Solution();
		// TO TEST
		String s = "abaccc";
		int k = 2;
		int res = sol.lengthOfLongestSubstringKDistinct(s, k);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {

	// HashMap + LinkedList 自己建立LinkedList
	private class Node {

		public int index; // ch字符在sliding window里最后一次出现的index
		public char ch;
		public Node prev, next;

		public Node(char ch, int index) {
			this.index = index;
			this.ch = ch;
		}
	}

	class MyQueue {

		public Node head, tail;
		public HashMap<Character, Node> map;
		public int start, size, k;

		public MyQueue(int k) {
			this.k = k;
			map = new HashMap<>();
			head = new Node('\0', 0);
			tail = new Node('\0', 0);
			head.next = tail;
			tail.prev = head;
			size = 0; // linkedList's size (except head and tail)
			start = 0;
		}

		public int getSize() {
			return size;
		}

		public void add(char ch, int idx) {
			Node node = new Node(ch, idx);
			if (map.containsKey(ch)) { // 删除LinkedList里面的ch原有的点，把ch + 最新的值的Node放在list末尾
				Node n = map.get(ch);
				remove(n);
			} else if (map.size() == k) { // 这个点没有在windows里面出现过，
				start = removeHeadNode() + 1;
			} else {
				size++;
			}
			size = idx - start + 1;
			map.put(ch, node);
			addToTail(node);
		}

		private int removeHeadNode() {
			map.remove(head.next.ch);
			int ret = head.next.index;
			Node next = head.next.next;
			head.next = next;
			next.prev = head;
			return ret;
		}

		private void addToTail(Node n) {
			Node prev = tail.prev;
			prev.next = n;
			n.prev = prev;
			n.next = tail;
			tail.prev = n;
		}

		private void remove(Node n) {
			Node prev = n.prev;
			Node next = n.next;
			prev.next = next;
			next.prev = prev;
		}
	}

	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || k == 0) {
			return 0;
		}

		int len = s.length(), max = 0;

		MyQueue queue = new MyQueue(k);

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			queue.add(c, i);

			max = Math.max(max, queue.getSize());
		}

		return max;
	}
}

//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {

	// 用count array去记录出现次数
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		// corner case
		if (s == null || k < 0 || s.length() * k == 0) {
			return 0;
		}
		int len = s.length();

		// general case
		// char and index of its last occurrence in the sliding window
		int[] count = new int[256];
		int num = 0; // # of different char
		int maxLen = 0; // lenth of longest substring
		int start = 0; // start of sliding window
		int end; // end fo sliding window
		for (end = 0; end < len; end++) {
			if (count[s.charAt(end)]++ == 0) {
				num++;
			}
			while (num > k) {
				if (--count[s.charAt(start++)] == 0) {
					num--;
				}
			}
			maxLen = Math.max(maxLen, end - start + 1);
		}
		return maxLen;
	}
}

class Solution2_1 {

	// HashMap + LinkedList 自己建立LinkedList
	private class Node {

		public int index; // ch字符在sliding window里最后一次出现的index
		public char ch;
		public Node prev, next;

		public Node(char ch, int index) {
			this.index = index;
			this.ch = ch;
		}
	}

	class MyQueue {

		public Node head, tail;
		public HashMap<Character, Node> map;
		public int start, size, k;

		public MyQueue(int k) {
			this.k = k;
			map = new HashMap<>();
			head = new Node('\0', 0);
			tail = new Node('\0', 0);
			head.next = tail;
			tail.prev = head;
			size = 0; // linkedList's size (except head and tail)
			start = 0;
		}

		public int getSize() {
			return size;
		}

		public void add(char ch, int idx) {
			Node node = new Node(ch, idx);
			if (map.containsKey(ch)) { // 删除LinkedList里面的ch原有的点，把ch + 最新的值的Node放在list末尾
				Node n = map.get(ch);
				remove(n);
			} else if (map.size() == k) { // 这个点没有在windows里面出现过，
				start = removeHeadNode() + 1;
			} else {
				size++;
			}
			size = idx - start + 1;
			map.put(ch, node);
			addToTail(node);
		}

		private int removeHeadNode() {
			map.remove(head.next.ch);
			int ret = head.next.index;
			Node next = head.next.next;
			head.next = next;
			next.prev = head;
			return ret;
		}

		private void addToTail(Node n) {
			Node prev = tail.prev;
			prev.next = n;
			n.prev = prev;
			n.next = tail;
			tail.prev = n;
		}

		private void remove(Node n) {
			Node prev = n.prev;
			Node next = n.next;
			prev.next = next;
			next.prev = prev;
		}
	}

	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || k == 0) {
			return 0;
		}

		int len = s.length(), max = 0;

		MyQueue queue = new MyQueue(k);

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			queue.add(c, i);

			max = Math.max(max, queue.getSize());
		}

		return max;
	}
}

class Solution2_2 {

	// HashMap + LinkedList，用库函数现成的LinkedList
	private class Node {

		public int index; // ch字符在sliding window里最后一次出现的index
		public char ch;

		public Node(char ch, int index) {
			this.index = index;
			this.ch = ch;
		}
	}

	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		// corner case
		if (s == null || k < 0 || s.length() * k == 0) {
			return 0;
		}
		// general case
		int len = s.length();
		int left = 0;
		int right = 0;
		int maxLen = 0;
		LinkedList<Node> list = new LinkedList<>();
		Map<Character, Node> map = new HashMap<>();
		for (right = 0; right < len; right++) {
			char ch = s.charAt(right);
			Node node = new Node(ch, right);
			if (map.containsKey(ch)) {
				Node old = map.get(ch);
				list.remove(old);
				map.remove(ch);
			} else if (map.size() == k) {
				Node old = list.removeFirst();
				map.remove(old.ch);
				left = old.index + 1;
			}
			list.add(node);
			map.put(ch, node);
			maxLen = Math.max(maxLen, right - left + 1);
		}

		return maxLen;
	}
}

class Solution2_3 {

	// 用LinkedHashMap
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		// corner case
		if (s == null || k < 0 || s.length() * k == 0) {
			return 0;
		}
		// general case
		int len = s.length();
		int left = 0;
		int right = 0;
		int maxLen = 0;
		Map<Character, Integer> map = new LinkedHashMap<>();
		for (right = 0; right < len; right++) {
			char ch = s.charAt(right);
			if (map.containsKey(ch)) {
				map.remove(ch);
			} else if (map.size() == k) {
				Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
				Map.Entry<Character, Integer> leftMost = iterator.next();
				char old = leftMost.getKey();
				map.remove(old);
				left = leftMost.getValue() + 1;
			}
			map.put(ch, right);
			maxLen = Math.max(maxLen, right - left + 1);
		}

		return maxLen;
	}
}
}