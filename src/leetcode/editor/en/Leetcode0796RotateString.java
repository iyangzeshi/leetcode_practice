//We are given two strings, A and B. 
//
// A shift on A consists of taking string A and moving the leftmost character to
// the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' af
//ter one shift on A. Return True if and only if A can become B after some number 
//of shifts on A. 
//
// 
//Example 1:
//Input: A = 'abcde', B = 'cdeab'
//Output: true
//
//Example 2:
//Input: A = 'abcde', B = 'abced'
//Output: false
// 
//
// Note: 
//
// 
// A and B will have length at most 100. 
// 
// 👍 732 👎 52

package leetcode.editor.en;

import java.util.Arrays;

// 2020-07-26 12:30:34
// Zeshi Yang
public class Leetcode0796RotateString{
    // Java: rotate-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0796RotateString().new Solution();
        // TO TEST
        String A = "abcdf";
        String B = "cdeab";
        boolean res = sol.rotateString(A, B);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.length() == 0) {
            return true;
        }
        
        int len = A.length();
        search:
        for (int delta = 0; delta < len; delta++) {
            for (int i = 0; i < len; i++) {
                if (A.charAt((delta + i) % len) != B.charAt(i)) {
                    continue search;
                }
            }
            return true;
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: compare char by char by circle
// T(n) = O(n^2), S(n) = O(1), where n is the length of A.
/*
More specifically, say we rotate A by delta.
Then, instead of A[0], A[1], A[2], ..., we have A[delta], A[delta+1], A[delta+2], ...;
and we should check that A[delta] == B[0], A[delta+1] == B[1], A[delta+2] == B[2], etc.
 */
class Solution1 {
    
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.length() == 0) {
            return true;
        }
        
        int len = A.length();
        search:
        for (int delta = 0; delta < len; delta++) {
            for (int i = 0; i < len; i++) {
                if (A.charAt((delta + i) % len) != B.charAt(i)) {
                    continue search;
                }
            }
            return true;
        }
        return false;
    }
    
}

// Solution 2: concatenate 2 A into a new String, and see whether it has B, whether A+A.contains(B)
// T(n) = O(n^2), S(n) = O(1), where n is the length of A.
class Solution2 {
    
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
    
}


// Solution 3: KMP algorithm, 扩展知识，不需要掌握
// T(n) = O(n), S(n) = O(n), where n is the length of A.
class Solution4 {
    
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.length() == 0) {
            return true;
        }
        
        int len = A.length();
        //Compute shift table
        int[] shifts = new int[len + 1];
        Arrays.fill(shifts, 1);
        int left = -1;
        for (int right = 0; right < len; ++right) {
            while (left >= 0 && (B.charAt(left) != B.charAt(right))) {
                left -= shifts[left];
            }
            shifts[right + 1] = right - left++;
        }
        
        //Find match of B in A+A
        int matchLen = 0;
        for (char c : (A + A).toCharArray()) {
            while (matchLen >= 0 && B.charAt(matchLen) != c) {
                matchLen -= shifts[matchLen];
            }
            if (++matchLen == len) {
                return true;
            }
        }
        return false;
    }
    
}
}