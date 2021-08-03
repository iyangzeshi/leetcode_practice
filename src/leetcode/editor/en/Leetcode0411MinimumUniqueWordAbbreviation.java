//A string such as "word" contains the following abbreviations:
//
// 
//["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", 
//"w1r1", "1o2", "2r1", "3d", "w3", "4"]
// 
//
// Given a target string and a set of strings in a dictionary, find an abbreviat
//ion of this target string with the smallest possible length such that it does no
//t conflict with abbreviations of the strings in the dictionary. 
//
// Each number or letter in the abbreviation is considered length = 1. For examp
//le, the abbreviation "a32bc" has length = 4. 
//
// Examples: 
//
// 
//"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
//
//"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap
//3", "a3e", "2p2", "3le", "3l1").
// 
//
// 
// Constraints: 
//
// 
// In the case of multiple answers as shown in the second example below, you may
// return any one of them. 
// Assume length of target string = m, and dictionary size = n. You may assume t
//hat m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20. 
// 
// Related Topics Backtracking Bit Manipulation 
// 👍 135 👎 127

package leetcode.editor.en;

// 2020-10-19 18:01:36
// Zeshi Yang
public class Leetcode0411MinimumUniqueWordAbbreviation{
    // Java: minimum-unique-word-abbreviation
    public static void main(String[] args) {
        Solution sol = new Leetcode0411MinimumUniqueWordAbbreviation().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    private int minLen;
    private int res;
    
    public String minAbbreviation(String target, String[] dictionary) {
        // corner case
        if (target == null || target.length() == 0) {
            return "";
        }
        if (dictionary == null || dictionary.length == 0) {
            return String.valueOf(target.length());
        }
        
        int len = target.length();
        int dictSize = dictionary.length;
        DictPool pool = new DictPool(dictSize);
    
        for (String word : dictionary) {
            if (word.length() == len) {
                pool.add(getMask(target, word));
            }
        }
        
        minLen = len;
        res = (1 << len) - 1; // get binary number with all filled by 1 and length of len
        helper(target, 0, 0, 0, pool);
        return convertMask(target, res);
    }
    
    class DictPool {
        
        public int[] masks; // same : 1, diff: 0
        public int size;
        
        public DictPool(int len) {
            masks = new int[len];
            size = 0;
        }
        
        public void add(int mask) {
            masks[size++] = mask;
        }
        
        public void remove(int idx) {
            int temp = masks[size - 1];
            masks[size - 1] = masks[idx]; // 把删除元素与数组尾部元素交换后，在尾部删除
            masks[idx] = temp;
            size--;
        }
        
        public void setSize(int size) {
            this.size = size;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
    }
    
    /**
     * @param t, String t, length of len
     * @param s, String s, length of len
     * @return: return a binary number of length len.
     * if s and t at index i is same,
     * set binary number at index i as 1; other wise 0.
     */
    private int getMask(String t, String s) {
        int mask = 0;
        int len = t.length();
        
        for (int i = 0; i < len; i++) {
            mask <<= 1;
            if (s.charAt(i) == t.charAt(i)) {
                mask += 1;
            }
        }
        return mask;
    }
    
    private void helper(String target, int idx, int path, int curLen, DictPool pool) {
        if (curLen >= minLen) {
            return;
        }
        
        int len = target.length();
        if (pool.isEmpty()) {
            if (idx < len) {
                curLen++;
            }
            if (curLen < minLen) {
                minLen = curLen;
                path = path << (len - idx);
                res = path;
            }
            return;
        }
    
        if (idx >= len) {
            return;
        }
        
        // add a number -- 0
        for (int i = idx; i < len; i++) {
            if (curLen == 0 || ((path & 1) == 1)) {
                helper(target, i + 1, path << (i + 1 - idx), curLen + 1, pool);
            }
        }
        
        // add a char -- 1
        int i = 0, curSize = pool.size;
        while (i < pool.size) {
            int mask = pool.masks[i];
            int offset = len - 1 - idx;
            if ((mask & (1 << offset)) == 0) {
                pool.remove(i);
            } else {
                i++;
            }
        }
        
        helper(target, idx + 1, (path << 1) + 1, curLen + 1, pool);
        pool.setSize(curSize);
    }
    
    private String convertMask(String target, int res) {
        StringBuilder sb = new StringBuilder();
        int len = target.length();
        int m = 1 << (len - 1), count = 0;
        
        for (int i = 0; i < len; i++) {
            if ((res & m) == 0) {
                count++;
            } else {
                if (count > 0) {
                    sb.append(count);
                }
                count = 0;
                sb.append(target.charAt(i));
            }
            m >>= 1;
        }
        if (count > 0) {
            sb.append(count);
        }
        return sb.toString();
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}