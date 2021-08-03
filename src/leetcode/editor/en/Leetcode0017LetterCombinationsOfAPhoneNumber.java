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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: Leetcode17LetterCombinationsOfAPhoneNumber
 * Description:
 * Author: Zeshi(Jesse) Yang
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
		Map<Character, String> phone = new HashMap<>() {{
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

}