//Given a string, sort it in decreasing order based on the frequency of characte
//rs. 
//
// Example 1:
// 
//Input:
//"tree"
//
//Output:
//"eert"
//
//Explanation:
//'e' appears twice while 'r' and 't' both appear once.
//So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid a
//nswer.
// 
// 
//
// Example 2:
// 
//Input:
//"cccaaa"
//
//Output:
//"cccaaa"
//
//Explanation:
//Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
//Note that "cacaca" is incorrect, as the same characters must be together.
// 
// 
//
// Example 3:
// 
//Input:
//"Aabb"
//
//Output:
//"bbAa"
//
//Explanation:
//"bbaA" is also a valid answer, but "Aabb" is incorrect.
//Note that 'A' and 'a' are treated as two different characters.
// 
// Related Topics Hash Table Heap 
// 👍 1825 👎 133

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2020-09-25 17:11:17
// Zeshi Yang
public class Leetcode0451SortCharactersByFrequency {
	
	// Java: sort-characters-by-frequency
	public static void main(String[] args) {
		Solution sol = new Leetcode0451SortCharactersByFrequency().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public String frequencySort(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] charS = s.toCharArray();
        Map<Character, Integer> charCount = new HashMap<>();
        for (char ch : charS) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }
        List<Character> listS = new ArrayList<>(charCount.keySet());
        listS.sort((o1, o2) -> {
            if (!charCount.get(o1).equals(charCount.get(o2))) {
                return charCount.get(o2) - charCount.get(o1);
            } else {
                return Character.compare(o1, o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Character ch : listS) {
            int count = 0;
            int freq = charCount.get(ch);
            while (count++ < freq) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: 统计所有字母的频率，按照频率sort char array, T(n) = O(nlog(n)), S(n) = O(n), n是单词的长度
// 13 ms,击败了53.66% 的Java用户, 40 MB,击败了62.37% 的Java用户
class Solution1 {
    
    public String frequencySort(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] charS = s.toCharArray();
        Map<Character, Integer> charCount = new HashMap<>();
        for (char ch : charS) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }
        List<Character> listS = new ArrayList<>(charCount.keySet());
        listS.sort(new Comparator<>() {
            @Override
            public int compare(Character o1, Character o2) {
                if (!charCount.get(o1).equals(charCount.get(o2))) {
                    return charCount.get(o2) - charCount.get(o1);
                } else {
                    return Character.compare(o1, o2);
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Character ch : listS) {
            int count = 0;
            int freq = charCount.get(ch);
            while (count++ < freq) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    
}

// Solution 2: 统计所有字母的频率，设置一个数组，每个频率设置一个List，(n) = O(n+k*log(k)), S(n) = O(n),
// n是单词的长度, 出现k次的字母的个数最多
// 9 ms,击败了89.40% 的Java用户, 39.8 MB,击败了71.59% 的Java用户
class Solution2 {
    
    public String frequencySort(String s) {
        // corner case
        if (s.length() <= 1) {
            return s;
        }
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        List<Character>[] bucket = new List[s.length() + 1]; // buckets[freq]存出现了这个次数的char
        for (char key : charCount.keySet()) {
            int freq = charCount.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (char ch : bucket[pos]) {
                    for (int i = 0; i < pos; i++) {
                        sb.append(ch);
                    }
                }
            }
        }
        return sb.toString();
    }
    
}
}