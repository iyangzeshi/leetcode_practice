//Given a string containing digits from 2-9 inclusive, return all possible lette
//r combinations that the number could represent. 
//
// A mapping of digit to letters (just like on the telephone buttons) is given b
//elow. Note that 1 does not map to any letters. 
//
// 
//
// Example: 
//
// 
//Input: "23"
//Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// Note: 
//
// Although the above answer is in lexicographical order, your answer could be i
//n any order you want. 
// Related Topics String Backtracking

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: Leetcode17LetterCombinationsOfAPhoneNumber
 * Description:
 * Author: Jesse Yang
 * Date: 2020/06/23 周二 13:20
 */
public class Leetcode0017LetterCombinationsOfAPhoneNumber {
	
	// Java: letter-combinations-of-a-phone-number
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0017LetterCombinationsOfAPhoneNumber().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	public List<String> letterCombinations(String digits) {
		
		LinkedList<String> res = new LinkedList<>();
		// corner case
		if (digits == null || digits.length() == 0) {
			return res;
		}
		res.add("");
		Map<Character, String> phone = new HashMap<Character, String>() {{
			put('2', "abc");
			put('3', "def");
			put('4', "ghi");
			put('5', "jkl");
			put('6', "mno");
			put('7', "pqrs");
			put('8', "tuv");
			put('9', "wxyz");
		}};
		
		for (int i = 0; i < digits.length(); i++) {
			String list = phone.get(digits.charAt(i));
			int size = res.size();
			for (int j = 0; j < size; j++) {
				String cur = res.get(0);
				res.removeFirst();
				for (char ch : list.toCharArray()) {
					res.add(cur + ch);
				}
			}
		}
		return res;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

	
	/* followup:
	On traditional mobile keypads, each digit maps to a small set of letters. For example, '2' → "abc", '3' → "def", …, '9' → "wxyz"; '0' → space; '1' → no letters. You are given:

A list of lowercase English words, knownWords.
A digit string, phoneNumber, containing characters '0' - '9'.
A word matches if you can translate every digit of phoneNumber into one of its letters in order, so that the mapped letters spell the word exactly, with no extra or missing digits. Return all matching words from knownWords in any order.


Constraints:

1
1 ≤ knownWords.length ≤
10
5
10
5

Each knownWords[i] consists of lowercase English letters and spaces.
phoneNumber consists of digits '0' - '9'.
Example 1:

Input: knownWords = ["aa", "ab", "ba", "qq", "hello", "b"], phoneNumber = "1221"
Output: ["aa", "ab", "ba"]
Explanation: The number 1221 becomes 22 after removing both '1's. Digit '2' maps to a, b, or c, so the only two-letter words composed of those letters are aa, ab, and ba.

Example 2:

Input: knownWords = ["aa", "ab", "ba", "qq", "hello", "b"], phoneNumber = "22"
Output: ["aa", "ab", "ba"]

Example 3:

Input: knownWords = ["a b","abc","a c","ab "], phoneNumber = "202"
Output: ["a b","a c"]
*/
	
	class PhoneNumberWordMatching {
		
		public List<String> t9WordMatching(String[] knownWords, String phoneNumber) {
			
			// digit -> letters mapping
			Map<Character, String> map = new HashMap<>();
			map.put('2', "abc");
			map.put('3', "def");
			map.put('4', "ghi");
			map.put('5', "jkl");
			map.put('6', "mno");
			map.put('7', "pqrs");
			map.put('8', "tuv");
			map.put('9', "wxyz");
			map.put('0', " ");  // space
			map.put('1', "");   // no letters
			
			// remove '1'
			StringBuilder filtered = new StringBuilder();
			for (char ch : phoneNumber.toCharArray()) {
				if (ch != '1') {
					filtered.append(ch);
				}
			}
			
			String digits = filtered.toString();
			List<String> res = new ArrayList<>();
			
			for (String word : knownWords) {
				if (word.length() != digits.length()) { // 如果单词长度不等于 digit 数量，直接跳过。
					continue;
				}
				boolean match = true;
				for (int i = 0; i < digits.length(); i++) {
					char digit = digits.charAt(i);
					char letter = word.charAt(i);
					String possible = map.get(digit);
					if (possible.indexOf(letter) == -1) {
						match = false;
						break;
					}
				}
				if (match) {
					res.add(word);
				}
			}
			return res;
		}
		
	}
}
