
//All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', a
//nd 'T', for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to 
//identify repeated sequences within the DNA. 
//
// Write a function to find all the 10-letter-long sequences (substrings) that o
//ccur more than once in a DNA molecule. 
//
// 
// Example 1: 
// Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//Output: ["AAAAACCCCC","CCCCCAAAAA"]
// Example 2: 
// Input: s = "AAAAAAAAAAAAA"
//Output: ["AAAAAAAAAA"]
// 
// 
// Constraints: 
//
// 
// 0 <= s.length <= 105 
// s[i] is 'A', 'C', 'G', or 'T'. 
// 
// Related Topics Hash Table Bit Manipulation 
// 👍 1108 👎 327

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 2021-02-06 20:48:39
// Jesse Yang
public class Leetcode0187RepeatedDnaSequences{
    // Java: repeated-dna-sequences
    public static void main(String[] args) {
        Solution sol = new Leetcode0187RepeatedDnaSequences().new Solution();
        // TO TEST
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> res = sol.findRepeatedDnaSequences(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<String> findRepeatedDnaSequences(String s) {
        int subLen = 10;
        int len = s.length();
        if (len <= subLen) {
            return new ArrayList<>();
        }
        
        // convert string to array of integers
        Map<Character, Integer> toInt = new HashMap<Character, Integer>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};
        int[] nums = new int[len];
        for (int i = 0; i < len; ++i) {
            nums[i] = toInt.get(s.charAt(i));
        }
        
        int bitmask = 0;
        Set<Integer> visited = new HashSet<>();
        Set<String> output = new HashSet<>();
        // iterate over all sequences of length subLen
        for (int start = 0; start < len - subLen + 1; start++) {
            if (start != 0) { // compute bitmask of the current sequence in O(1) time
                bitmask <<= 2;// left shift to free the last 2 bit
                bitmask |= nums[start + subLen - 1];// add base new 2-bits number in the last two bits
                bitmask &= ~(3 << 2 * subLen);// unset first two bits: 2L-bit and (2L + 1)-bit
            } else {// compute hash of the first sequence in O(subLen) time
                for (int i = 0; i < subLen; i++) {
                    bitmask <<= 2;
                    bitmask |= nums[i];
                }
            }
            // update output and hashset of visited sequences
            if (visited.contains(bitmask)) {
                output.add(s.substring(start, start + subLen));
            }
            visited.add(bitmask);
        }
        return new ArrayList<>(output);
    }
    
}

//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: HashSet 检测重复, T(n) = O(n * (n - l + 1)), S(n) = O(n * (n - l + 1))
// 16 ms,击败了76.35% 的Java用户, 47.7 MB,击败了53.10% 的Java用户
/*
直接用HashSet<String>来去重，记录每一个长度为L的slice的出现个数。 出现次数大于1的就要放到List里面
 */
class Solution1 {
    
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        int subLen = 10;
        Set<String> visited = new HashSet<>();
        Set<String> output = new HashSet<>();
        for (int i = 0; i <= len - subLen; i++) {
            String cur = s.substring(i, i + subLen);
            if (!visited.contains(cur)) {
                visited.add(cur);
            } else {
                output.add(cur);
            }
        }
        return new ArrayList<>(output);
    }
    
}

// Solution 2:Rabin-Karp : Constant-time Slice Using Rolling Hash
// T(n) = O(n), S(n) =  O(min(n - l), 4^l); l = 10
// 14 ms,击败了94.91% 的Java用户, 46 MB,击败了80.67% 的Java用户
/*
直接用HashSet<Integer>来去重，记录每一个长度为L的slice的出现个数。 出现次数大于1的就要放到List里面
因为ACGT可以分别用0，1，2，3表示，然后可以用4进制来表示，用10进制来计算这个长度L的slice的值。
知道一个长度为L的slice，下一个长度为L的slice是这个长度为L的slice往右边移动了一格，
所以O(1)可以利用上一步的值来计算这一步的值
 */
class Solution2 {
    
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        int subLen = 10;
        if (len <= subLen) {
            return new ArrayList<>();
        }
        
        // rolling hash parameters: base base
        int base = 4;
        // 在长度为L的一段区间内，最右边的点，移到最左边, 一共移动了L - 1步
        int numWithBase = (int) Math.pow(base, subLen - 1);
        
        // convert string to array of integers
        Map<Character, Integer> DNAtoNum = new
                HashMap<Character, Integer>() {{
                    put('A', 0);
                    put('C', 1);
                    put('G', 2);
                    put('T', 3);
                }};
        int[] nums = new int[len];
        for (int i = 0; i < len; ++i) {
            nums[i] = DNAtoNum.get(s.charAt(i));
        }
        
        int hashValue = 0;
        Set<Integer> visited = new HashSet<>();
        Set<String> output = new HashSet<>();
        // iterate over all sequences of length subLen
        for (int start = 0; start < len - subLen + 1; start++) {
            if (start != 0) { // compute hash of the current sequence in O(1) time
                // hashValue = (hashValue - nums[start - 1] * numWithBase) * base + nums[start +
                // subLen - 1];
                hashValue =
                        (hashValue % (numWithBase)) * base + nums[start + subLen - 1]; // 或者用上面一行，等价
            } else { // compute hash of the first sequence in O(subLen) time
                for (int i = 0; i < subLen; ++i) {
                    hashValue = hashValue * base + nums[i];
                }
            }
            // update output and hashset of visited sequences
            if (visited.contains(hashValue)) {
                output.add(s.substring(start, start + subLen));
            }
            visited.add(hashValue);
        }
        return new ArrayList<>(output);
    }
    
}

// Solution 3: Bit Manipulation, T(n) = O(n), S(n) =  O(min(n - l), 4^l); l = 10
// 14 ms,击败了94.91% 的Java用户, 45.1 MB,击败了86.27% 的Java用户
/*
直接用HashSet<Integer>来去重，记录每一个长度为L的slice的出现个数。 出现次数大于1的就要放到List里面
因为ACGT可以分别用0，1，2，3表示，然后可以用4进制来表示，直接用2进制来计算这个长度L的slice的值
知道一个长度为L的slice，下一个长度为L的slice是这个长度为L的slice往右边移动了一格，
所以O(1)可以利用上一步的值来计算这一步的值
 */
class Solution3 {
    
    public List<String> findRepeatedDnaSequences(String s) {
        int subLen = 10;
        int len = s.length();
        if (len <= subLen) {
            return new ArrayList<>();
        }
        
        // convert string to array of integers
        Map<Character, Integer> toInt = new HashMap<Character, Integer>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};
        int[] nums = new int[len];
        for (int i = 0; i < len; ++i) {
            nums[i] = toInt.get(s.charAt(i));
        }
        
        int bitmask = 0;
        Set<Integer> visited = new HashSet<>();
        Set<String> output = new HashSet<>();
        // iterate over all sequences of length subLen
        for (int start = 0; start < len - subLen + 1; start++) {
            if (start != 0) { // compute bitmask of the current sequence in O(1) time
                bitmask <<= 2;// left shift to free the last 2 bit
                bitmask |= nums[start + subLen - 1];// add base new 2-bits number in the last two bits
                bitmask &= ~(3 << 2 * subLen);// unset first two bits: 2L-bit and (2L + 1)-bit
            } else {// compute hash of the first sequence in O(subLen) time
                for (int i = 0; i < subLen; i++) {
                    bitmask <<= 2;
                    bitmask |= nums[i];
                }
            }
            // update output and hashset of visited sequences
            if (visited.contains(bitmask)) {
                output.add(s.substring(start, start + subLen));
            }
            visited.add(bitmask);
        }
        return new ArrayList<>(output);
    }
    
}
}