//Given an input string (s) and a pattern (p), implement regular expression matc
//hing with support for '.' and '*'. 
//
// 
//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.
// 
//
// The matching should cover the entire input string (not partial). 
//
// Note: 
//
// 
// s could be empty and contains only lowercase letters a-z. 
// p could be empty and contains only lowercase letters a-z, and characters like
// . or *. 
// 
//
// Example 1: 
//
// 
//Input:
//s = "aa"
//p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
// 
//
// Example 2: 
//
// 
//Input:
//s = "aa"
//p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, 
//by repeating 'a' once, it becomes "aa".
// 
//
// Example 3: 
//
// 
//Input:
//s = "ab"
//p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".
// 
//
// Example 4: 
//
// 
//Input:
//s = "aab"
//p = "c*a*b"
//Output: true
//Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, i
//t matches "aab".
// 
//
// Example 5: 
//
// 
//Input:
//s = "mississippi"
//p = "mis*is*p*."
//Output: false
// 
// Related Topics String Dynamic Programming Backtracking

package leetcode.editor.en;

/*
clarification: ".*" means "zero or more (*) of any character (.)".
 */
public class Leetcode0010RegularExpressionMatching {
	
	// Java: regular-expression-matching
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0010RegularExpressionMatching().new Solution();
		// TO TEST
		String s = "a";
		String p = ".*..a*";
		boolean res = sol.isMatch(s, p);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: Dynamic programming
// T(m, n) = O(m * n), S(m, n) = O(m * n)
/*
dp[i][j] means whether s[0, i) matches p[0, j) successfully

initialization：
    boolean[][] dp = new boolean[lenS + 1][lenP + 1]; all is false
    dp[0][0] = true;
    dp[0][j] = true: ∵ "[[a-z]|.]* can match "" empty string; if
        j is even;
        p[j-1] is '*';
        and dp[0][j-2] is true
        
1.p[j−1] is normal character,if s[i−1]==p[j−1]，then dp[i][j]=dp[i−1][j−1]，else match unsuccessfully
2.p[j−1] is '.'，then dp[i][j]=dp[i−1][j−1]
3.p[j−1] is '*'：
    (1) * match 0 previous character，then dp[i][j]=dp[i][j−2]
    (2) * match previous character，then dp[i][j]=dp[i−1][j]
 */
class Solution {
    
    public boolean isMatch(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int lenS = sChars.length;
        int lenP = pChars.length;
        // initialization
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        dp[0][0] = true;
        for (int j = 2; j <= lenP; j += 2) {
            if (pChars[j - 2] != '*' && pChars[j - 1] == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= lenS; i++) { // i start from 0
            for (int j = 1; j <= lenP; j++) { // j start from 1
                char ch = pChars[j - 1];
                if ('a' <= ch && ch <= 'z') { // normal character
                    if (sChars[i - 1] == pChars[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else if (pChars[j - 1] == '.') { // is '.'
                    dp[i][j] = dp[i - 1][j - 1];
                } else { // ch == '*'
                    if (j > 1) {
                        dp[i][j] |= dp[i][j - 2];   //不看
                    }
                    if (j > 1 && (sChars[i - 1] == pChars[j - 2] || pChars[j - 2] == '.')) {
                        dp[i][j] |= dp[i - 1][j];    //看
                    }
                }
            }
        }
        return dp[lenS][lenP];
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

/**面试的时候，用Solution 1_2， 需要pruning的话用Solution 1_3(最优解） */

/*
Solution 1的3中方法的思想 用s去匹配p,
base case: 如果p已经走到最后len了， return s有没有走到最后
general case:
    如果p走到的char的后面是*, 从当前字符(*前面的)char判断
        如果当前字符char是 '.'，可以匹配任意字符多个任意字符, s遍历往后走，p往后面走2格
        如果前面的char是普通字符，
            如果当前位置都能匹配的话，p和s都往后匹配一个
            不能匹配的话，就false
    如果走到的char的后面不是*，当前char,
        如果s和p当前能匹配，就往后各走1格
        否则return false
    可走到的char的后面没有字符了，就是也就是最后一个字符（当前char不可能是 *, 因为*会被跳过)
        如果s和p当前能匹配，就往后各走1格
        否则return false
 */


// Solution 1_1: DFS without pruning
// T(m, n) =  O((m + n) * 2^n), S(m, n) = O(m * n)
// 27 ms,击败了30.98% 的Java用户, 37.7 MB,击败了90.83% 的Java用户
/*
base case: 如果p已经走到最后len了， return s有没有走到最后
general case:
    如果p走到的char的后面是*, 从当前字符(*前面的)char判断
        如果当前字符char是 '.'，可以匹配任意字符多个任意字符, s遍历往后走，p往后面走2格
        如果前面的char是普通字符，
            如果当前位置都能匹配的话，p和s都往后匹配一个
            不能匹配的话，就false
    如果走到的char的后面不是*，当前char,
        如果s和p当前能匹配，就往后各走1格
        否则return false
    可走到的char的后面没有字符了，就是也就是最后一个字符（当前char不可能是 *, 因为*会被跳过)
        如果s和p当前能匹配，就往后各走1格
        否则return false
*/
class Solution1_1 {
    
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        return dfs(s, 0, p, 0);
        
    }
    
    /**
     * 因为 * 可以匹配0到任意一个 * 前面的字符，所以检测的是p[idxP + 1] 是不是 *
     *
     * @param s:    target String
     * @param idxS: current idx in the String s
     * @param p:    given regular expression
     * @param idxP: current idx in the String p
     * @return: whether matched
     */
    private boolean dfs(String s, int idxS, String p, int idxP) {
        /*
         * base case
         * 这里的corner case是从idxP == lenP角度去出发的
         * 如果这个时候s也走到了最后，就return true, 否则return false
         *
         * 否则如果从idxS == lenS角度去出发会很麻烦，如果idxS == lenS
         * 如果idxP == lenP，return true;
         * 但是如果idxP != lenP, 结果也有可能是true，比如说P的结尾是 * 的情况下
         *
         */
        int lenS = s.length();
        int lenP = p.length();
        // base case
        if (idxP == lenP) {
            return idxS == lenS;
        }
        
        if (idxP == lenP - 1) { // p[idxP]是最后一个字符，后面没有字符了
            // part A
            if (idxS < lenS) {
                if (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS)) {
                    return dfs(s, idxS + 1, p, idxP + 1);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else { // p[idxP]不是最后一个字符
            if (p.charAt(idxP + 1) != '*') { // p[idxP]后面跟的字符不是 *
                // part B
                if (idxS < lenS) {
                    if (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS)) {
                        return dfs(s, idxS + 1, p, idxP + 1);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else { //  (p.charAt(idxP + 1) == '*'， p接下来跟的字符是 *，
                // part C
                int i = idxS - 1; // the first match try is [empty, *] instead of [s[idx], *]
                // here we match the [], a, aa, aaa, aaa... in the String s
                while (i < lenS &&
                        (i < idxS || p.charAt(idxP) == '.' || s.charAt(i) == p.charAt(idxP))) {
                    if (dfs(s, i + 1, p, idxP + 2)) {
                        return true;
                    }
                    i++;
                }
                return false;
            }
        }
        
    }
    
}

// Solution 1_2: DFS without pruning, Solution 1_1合并讲话后的版本
// T(m, n) =  O((m + n) * 2^n), S(m, n) = O(m * n)
// 26 ms,击败了31.34% 的Java用户, 37.8 MB,击败了85.83% 的Java用户
/*
base case: 如果p已经走到最后len了， return s有没有走到最后
general case:
    如果p走到的char的后面是*, 从当前字符(*前面的)char判断
        如果当前字符char是 '.'，可以匹配任意字符多个任意字符, s遍历往后走，p往后面走2格
        如果前面的char是普通字符，
            如果当前位置都能匹配的话，p和s都往后匹配一个
            不能匹配的话，就false
    如果走到的char的后面不是*，当前char,
        如果s和p当前能匹配，就往后各走1格
        否则return false
    可走到的char的后面没有字符了，就是也就是最后一个字符（当前char不可能是 *, 因为*会被跳过)
        如果s和p当前能匹配，就往后各走1格
        否则return false
*/
class Solution1_2 {
    
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        return dfs(s, 0, p, 0);
        
    }
    
    /**
     * dfs used to check whether s[idxS, lenS) is matched with the p[idxP, lenP)
     */
    // 因为 * 可以匹配0到任意一个 * 前面的字符，所以检测的是p[idxP + 1] 是不是 *
    private boolean dfs(String s, int idxS, String p, int idxP) {
        /*
         * base case
         * 这里的corner case是从idxP == lenP角度去出发的
         * 如果这个时候s也走到了最后，就return true, 否则return false
         *
         * 否则如果从idxS == lenS角度去出发会很麻烦，如果idxS == lenS
         * 如果idxP == lenP，return true;
         * 但是如果idxP != lenP, 结果也有可能是true，比如说P的结尾是 * 的情况下
         *
         */
        int lenS = s.length();
        int lenP = p.length();
        // base case
        if (idxP == lenP) {
            return idxS == lenS;
        }
        /*
        正常的逻辑是
        if (idxP == lenP - 1) { // p[idxP]是最后一个字符，后面没有字符了
            // part A
            
        } else {
            if (p.charAt(idxP + 1) != '*') { // p[idxP]不是最后一个字符，而且后面跟的字符不是 *
                // part B
            } else { // p[idxP]不是最后一个字符，而且后面跟的字符是 *
                // part C
            }
        }
        分析过程的本身代码应该是这样子的
        然后发现part A 和 B是一样的，可以合并
         */
        if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // case 1: not "*" // ?
            if(idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
                return dfs(s, idxS + 1, p, idxP + 1);
            } else {
                return false;
            }
        } else { // case 2: "*"
            int i = idxS - 1; // the first match try is [empty, *] instead of [s[idx], *]
            // here we match the [], a, aa, aaa, aaa... in the String s
            while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || s.charAt(i) == p.charAt(idxP))) {
                if (dfs(s, i + 1, p, idxP + 2)) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }
    // time complexity: O((m + n) * 2^n)
}

// Solution 1_3: DFS with pruning
// T(m, n) = O(m * n), S(m, n) = O(m * n)
// 1 ms,击败了100.00% 的Java用户, 39.1 MB,击败了49.51% 的Java用户
/*
base case: 如果p已经走到最后len了， return s有没有走到最后
general case:
    如果p走到的char的后面是*, 从当前字符(*前面的)char判断
        如果当前字符char是 '.'，可以匹配任意字符多个任意字符, s遍历往后走，p往后面走2格
        如果前面的char是普通字符，
            如果当前位置都能匹配的话，p和s都往后匹配一个
            不能匹配的话，就false
    如果走到的char的后面不是*，当前char,
        如果s和p当前能匹配，就往后各走1格
        否则return false
    可走到的char的后面没有字符了，就是也就是最后一个字符（当前char不可能是 *, 因为*会被跳过)
        如果s和p当前能匹配，就往后各走1格
        否则return false
*/
class Solution1_3 {
    
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        return dfs(s, 0, p, 0, new Boolean[s.length() + 1][p.length() + 1]);
        
    }
    
    /**
     * description: dfs used to check whether s[idxS, lenS) is matched with the p[idxP, lenP)
     * @param memo: the 2D array which stores whether s[idxS, lenS) matches p[idxP, lenP)
     */
    // 因为 * 可以匹配0到任意一个 * 前面的字符，所以检测的是p[idxP + 1] 是不是 *
    private boolean dfs(String s, int idxS, String p, int idxP, Boolean[][] memo) {
        
        int lenS = s.length();
        int lenP = p.length();
        // base case
        if (memo[idxS][idxP] != null) { // lookup the form
            return memo[idxS][idxP];
        }
        if (idxP == lenP) {
            memo[idxS][idxP] = (idxS == lenS);
            return memo[idxS][idxP];
        }
        
        if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // case 1: not "*" // ?
            if (idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
                memo[idxS][idxP] = dfs(s, idxS + 1, p, idxP + 1, memo);
                return memo[idxS][idxP];
            } else {
                memo[idxS][idxP] = false; // fill in the form
                return false;
            }
        } else { // case 2: "*"
            int i = idxS - 1; // the first match try is [empty, *] instead of [s[idx], *]
            // here we match the [], a, aa, aaa, aaa... in the String s
            while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || s.charAt(i) == p
                    .charAt(idxP))) {
                if (dfs(s, i + 1, p, idxP + 2, memo)) {
                    memo[idxS][idxP] = true; // fill in the form
                    return true;
                }
                i++;
            }
            memo[idxS][idxP] = false; // fill in the form
            return false;
        }
    }
    // time complexity: O(m * n)
}

// Solution 2: Dynamic programming
// T(m, n) = O(m * n), S(m, n) = O(m * n)
// 2 ms,击败了89.91% 的Java用户, 37.7 MB,击败了88.03% 的Java用户
/*
dp[i][j] means whether s[0, i) matches p[0, j) successfully

initialization：
    boolean[][] dp = new boolean[lenS + 1][lenP + 1]; all is false
    dp[0][0] = true;
    dp[0][j] = true: ∵ "[[a-z]|.]* can match "" empty string; if
        j is even;
        p[j-1] is '*';
        and dp[0][j-2] is true
        
1.p[j−1] is normal character,if s[i−1]==p[j−1]，then dp[i][j]=dp[i−1][j−1]，else match unsuccessfully
2.p[j−1] is '.'，then dp[i][j]=dp[i−1][j−1]
3.p[j−1] is '*'：
    (1) * match 0 previous character，then dp[i][j]=dp[i][j−2]
    (2) * match previous character，then dp[i][j]=dp[i−1][j]
 */
class Solution2_1 {
    
    public boolean isMatch(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int lenS = sChars.length;
        int lenP = pChars.length;
        // initialization
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        dp[0][0] = true;
        for (int j = 2; j <= lenP; j += 2) {
            if (pChars[j - 2] != '*' && pChars[j - 1] == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }
        
        // state transaction function
        for (int i = 1; i <= lenS; i++) { // i start from 0
            for (int j = 1; j <= lenP; j++) { // j start from 1
                char ch = pChars[j - 1];
                if ('a' <= ch && ch <= 'z') { // normal character
                    if (sChars[i - 1] == pChars[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else if (pChars[j - 1] == '.') { // is '.'
                    dp[i][j] = dp[i - 1][j - 1];
                } else { // ch == '*'
                    if (j > 1) {
                        dp[i][j] |= dp[i][j - 2]; // * match 0 character
                    }
                    if (j > 1 && (sChars[i - 1] == pChars[j - 2] || pChars[j - 2] == '.')) {
                        // match 1 more character, this will repeat
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }
        return dp[lenS][lenP];
    }
    
}

// Solution 2_2: Dynamic programming
// T(m, n) = O(m * n), S(m, n) = O(m * n)
// 2 ms,击败了89.91% 的Java用户, 37.7 MB,击败了88.03% 的Java用户
/*
dp[i][j] means s[i ~ end] can match p[j ~ end]
 initialization：
    boolean[][] dp = new boolean[lenS + 1][lenP + 1]; all is false
    dp[lenS][lenP] = true;
 state transition function:
    if p[j] is * , continue;
    else
        if p next (j + 1) is not *:
            dp[i][j] = dp[i + 1][j + 1] if s[i] matches p[j]
        else : p next (j + 1) is  *
            dp[i][j] = true if p[j ~ j+1] match s[i ~ t), (s <= t, t < len), and dp[t][j+2] = true;

*/
class Solution2_2 {
    
    /*
    boolean dp[i][j] means whether s[i, lenS) matches p[j, lenP)
    dp[i][j] =
     */
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        dp[lenS][lenP] = true;
        
        int k = lenP - 2;
        // base case, char * 可以匹配 empty string ""
        while (k >= 0) {
            if (p.charAt(k + 1) == '*') {
                dp[lenS][k] = true;
            } else {
                break;
            }
            k -= 2;
        }
        
        for (int i = lenS - 1; i >= 0; i--) {
            for (int j = lenP - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    continue;
                }
                
                if (j + 1 >= lenP || p.charAt(j + 1) != '*') { // 非*情况
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                } else { // next char p[j + 1] is * case
                    int idx = i; // 因为dp[i][j]是左闭区间
                    while (idx <= lenS &&
                            (idx == i || p.charAt(j) == '.' || s.charAt(idx - 1) == p.charAt(j))) {
                        if (dp[idx][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        idx++;
                    }
                }
            }
        }
        
        return dp[0][0];
        
    }
}
}