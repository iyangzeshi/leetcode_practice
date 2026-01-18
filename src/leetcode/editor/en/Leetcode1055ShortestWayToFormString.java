/**
A subsequence of a string is a new string that is formed from the original
string by deleting some (can be none) of the characters without disturbing the
relative positions of the remaining characters. (i.e., "ace" is a subsequence of
"abcde" while "aec" is not).

 Given two strings source and target, return the minimum number of subsequences
of source such that their concatenation equals target. If the task is
impossible, return -1.

 
 Example 1:

 
Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are
subsequences of source "abc".
 

 Example 2:

 
Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of
source string due to the character "d" in target string.
 

 Example 3:

 
Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 

 
 Constraints:

 
 1 <= source.length, target.length <= 1000
 source and target consist of lowercase English letters.
 

 Related Topics Two Pointers String Binary Search Greedy 👍 1332 👎 76

*/
package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collections;

// 2026-01-16 20:15:19
// Jesse Yang
public class Leetcode1055ShortestWayToFormString{
    // Java: shortest-way-to-form-string
    public static void main(String[] args) {
        Solution sol = new Leetcode1055ShortestWayToFormString().new Solution();
        // TO TEST
        String source = "abaabcab";
        String target = "abcbc";
        int res = sol.shortestWay(source, target);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int shortestWay(String source, String target) {
        int numCh = 'z' - 'a' + 1;
        int lenS = source.length();
        // Next occurrence of a character >= a given indexS
        int[][] nextOccur = new int[numCh][lenS];
        
        // Base Case
        for (int ch = 0; ch < numCh; ch++) {
            nextOccur[ch][lenS - 1] = -1;
        }
        nextOccur[source.charAt(lenS - 1) - 'a'][lenS - 1] = lenS - 1;
        
        // step 1: Fill using recurrence relation
        for (int ch = 0; ch < numCh; ch++) {
            for (int idx = lenS - 2; idx >= 0; idx--) {
                // if index of same char >= current one is the current one
                if (source.charAt(idx) == ch + 'a') {
                    nextOccur[ch][idx] = idx;
                } else {
                    nextOccur[ch][idx] = nextOccur[ch][idx + 1];
                }
            }
        }
        
        // Pointer to the current indexS in the source
        int indexS = 0;
        
        int count = 1;
        
        // step 2: Find all characters of target in source
        for (char ch : target.toCharArray()) {
            // If the character is not present in source
            if (nextOccur[ch - 'a'][0] == -1) {
                return -1;
            }
            
            // If we have reached the end of source, or the character is not in
            // source after source_iterator, loop back to beginning
            if (indexS == lenS || nextOccur[ch - 'a'][indexS] == -1) {
                count++;
                indexS = 0;
            }
            
            // Next occurrence of character in the source after source_iterator
            indexS = nextOccur[ch - 'a'][indexS] + 1;
        }
        
        // Return the number of times we need to iterate through the source
        return count;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/*
2 pointers
traverse the string source and match the related char in target repeatedly
until we finish the match target
or there is no matching char in source for certain char in target

T(m, n) = O(m*n)
S(m,n) = O(1)
 */
class Solution1_1 {
    
    public int shortestWay(String source, String target) {
        int lenS = source.length();
        int lenT = target.length();
        int i = 0;
        int j = 0;
        int res = 0;
        
        while (j < lenT) {
            int prevJ = j;
            i = 0;
            // traverse the source and match the related char in the target
            while (i < lenS && j < lenT) {
                if (source.charAt(i) == target.charAt(j)) {
                    j++;
                }
                i++;
            }
            
            if (j == prevJ) {
                return -1; // 一个字符都没匹配到
            }
            
            res++; // ✅ 用完一轮 source
        }
        
        return res;
    }
    
}
/*
2 pointers
for every char in target:
    find the matching char in source
    if can not find it, return -1
    if we can find it:
        if the new char beyond the leng of S,
            res++

T(m, n) = O(m*n)
S(m,n) = O(1)
 */
class Solution1_2 {
    
    public int shortestWay(String source, String target) {
        int i = 0;
        int j = 0;
        int lenS = source.length();
        int lenT = target.length();
        int res = 0;
        boolean reachEnd = true;
        while (j < lenT) {
            char charT = target.charAt(j);
            int indexI = i;
            while (i < lenS && source.charAt(i) != charT) {
                i++;
                if (i == lenS) {
                    i = 0;
                    reachEnd = true;
                }
                if (i == indexI) {
                    return -1;
                }
            }
            // source.charAt(i) == charT
            if (reachEnd) {
                res++;
                reachEnd = false;
            }
            i++;
            j++;
            if (i == lenS) {
                reachEnd = true;
                i = 0;
            }
        }
        return res;
    }
    
}
/* solution 3: binary search

给source的每个字母按照添加他们的index的list
比如source: abcbc
a: {0}
b: {1,3}
c: {2,4}
keep 一个current source中的上一个匹配的字母的位置index
每次要找target的字母ch 在source中的用source中的哪个位置的字母匹配的时候，就找
那个字母ch对应的list中的第一个 > index的位置，用那个字母匹配
T(m, n) = O(m + n * log(m))
S(m,n) = O(m)
m is size of source, n is size of target

 */
class Solution2 {
    
    public int shortestWay(String source, String target) {
        
        // List of indices for all characters in source
        ArrayList<Integer>[] charIndices = new ArrayList['z' - 'a' + 1];
        for (int i = 0; i < source.length(); i++) {
            char ch = source.charAt(i);
            if (charIndices[ch - 'a'] == null) {
                charIndices[ch - 'a'] = new ArrayList<>();
            }
            charIndices[ch - 'a'].add(i);
        }
        
        // Pointer for source, [0, i) has been used
        int i = 0;
        
        // Number of times we need to iterate through source
        int count = 1;
        
        // Find all characters of target in source
        for (char ch : target.toCharArray()) {
            // If the character is not in the source, return -1
            if (charIndices[ch - 'a'] == null) {
                return -1;
            }
            // Binary search to find the indexS of the character in source
            // next to the source iterator
            ArrayList<Integer> indices = charIndices[ch - 'a'];
            int indexS = Collections.binarySearch(indices, i);
            // If the indexS is negative, we need to find the next indexS
            // that is greater than the source iterator
            if (indexS < 0) {
                indexS = -indexS - 1;
            }
            // If we have reached the end of the list, we need to iterate
            // through source again, hence first indexS of character in source.
            if (indexS == indices.size()) {
                count++;
                i = indices.get(0) + 1;
            } else {
                i = indices.get(indexS) + 1;
            }
        }
        
        // Return the number of times we need to iterate through source
        return count;
    }
    
}
/* solution 4: 2D array

instead of log(n) search, we can precompute the index of nextOccur[][]
nextOccur[i][j]: means for char i, find the index of first element in source that index >= j
for every character char in the target
{
    Find its occurrence in the source ahead of sourceIterator
}

给source的每个字母按照添加他们的index的list
比如source: abcbc
a: {0}
b: {1,3}
c: {2,4}
keep 一个current source中的上一个匹配的字母的位置index
每次要找target的字母ch 在source中的用source中的哪个位置的字母匹配的时候，就找
那个字母ch对应的list中的第一个 > index的位置，用那个字母匹配
T(m, n) = O(m + n * log(m))
S(m,n) = O(m)
m is size of source, n is size of target

 */

class Solution3 {
    
    public int shortestWay(String source, String target) {
        int numCh = 'z' - 'a' + 1;
        int lenS = source.length();
        // Next occurrence of a character >= a given indexS
        int[][] nextOccur = new int[numCh][lenS];
        
        // Base Case
        for (int ch = 0; ch < numCh; ch++) {
            nextOccur[ch][lenS - 1] = -1;
        }
        nextOccur[source.charAt(lenS - 1) - 'a'][lenS - 1] = lenS - 1;
        
        // step 1: Fill using recurrence relation
        for (int ch = 0; ch < numCh; ch++) {
            for (int idx = lenS - 2; idx >= 0; idx--) {
                // if index of same char >= current one is the current one
                if (source.charAt(idx) == ch + 'a') {
                    nextOccur[ch][idx] = idx;
                } else {
                    nextOccur[ch][idx] = nextOccur[ch][idx + 1];
                }
            }
        }
        
        // Pointer to the current indexS in the source
        int indexS = 0;
        
        int count = 1;
        
        // step 2: Find all characters of target in source
        for (char ch : target.toCharArray()) {
            // If the character is not present in source
            if (nextOccur[ch - 'a'][0] == -1) {
                return -1;
            }
            
            // If we have reached the end of source, or the character is not in
            // source after source_iterator, loop back to beginning
            if (indexS == lenS || nextOccur[ch - 'a'][indexS] == -1) {
                count++;
                indexS = 0;
            }
            
            // Next occurrence of character in the source after source_iterator
            indexS = nextOccur[ch - 'a'][indexS] + 1;
        }
        
        // Return the number of times we need to iterate through the source
        return count;
    }
    
}
}
