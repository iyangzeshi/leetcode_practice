//Given a string s, find the longest palindromic substring in s. You may assume 
//that the maximum length of s is 1000. 
//
// Example 1: 
//
// 
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: "cbbd"
//Output: "bb"
// 
// Related Topics String Dynamic Programming 
// 👍 7185 👎 543

package leetcode.editor.en;

// 2020-07-26 12:36:56
// Jesse Yang
public class Leetcode0005LongestPalindromicSubstring{
    // Java: longest-palindromic-substring
    public static void main(String[] args) {
        Solution sol = new Leetcode0005LongestPalindromicSubstring().new Solution();
        // TO TEST
        String s = "bb";
        String res = sol.longestPalindrome(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/** 面试的时候用 Solution 4*/
// Solution 4: Expand Around Center
// T(n) = O(n^2), S(n) = O(1)
// 13 ms,击败了95.73% 的Java用户, 38.8 MB,击败了82.36% 的Java用户
/*
用brute force的做法。在String里面遍历所有的substring，
开头和结尾都有n种情况，所以有n^2种substring
然后每个substring，都要遍历完这个substring本身，才能确定是不是palindrome，这里要O(n)
所以brute force时间复杂度是O(n^3)

但是在brute force的做法里面，我们发现有很多是重复计算的。
比如说substring(i, j) 和substring(i-1, j+1),前者substring(i, j) 的计算包括了substring(i-1, j+1)
如果能把这些重复计算避开就好了

发现从中间开始往两边扩展找palindrome比较好，不会有重复计算的地方。一共有2*n - 1个中间点
 */
class Solution {
	
	public String longestPalindrome(String s) {
		// corner case
		if (s == null || s.length() <= 1) {
			return s;
		}
		int len = s.length();
		int[] max = {1}; // 记录当前最大的palindrome的长度
		int[] start = {0}; // 记录当前最大palindrome的起点
		
		for (int i = 0; i < len; i++) {
			//当剩下的subString的最大长度比max[0]小的时候，就不需要比较了
			if (2 * Math.min(i + 1, len - i) < max[0]
				&& 2 * Math.min(i + 1, len - i - 1) < max[0]) {
				break;
			}
			findMax(s, i, i, max, start);
			findMax(s, i, i + 1, max, start);
		}
		// subString的区间是[left, right)
		return s.substring(start[0], start[0] + max[0]);
	}
	
	// find max length of palindrome centered at [left, right]
	private void findMax(String s, int left, int right, int[] max, int[] start) {
		int len = s.length();
		while (left >= 0 && right < len) {
			if (s.charAt(left) == s.charAt(right)) {
				left--;
				right++;
			} else {
				break;
			}
		}
		
		int dist = right - left - 1;
		
		if (dist > max[0]) {
			max[0] = dist;
			start[0] = left + 1;
		}
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)


// Solution 1: brute force
// T(n) = O(n^3), S(n) = O(1)
// Time Limit Exceeded,
/*
用brute force的做法。在String里面遍历所有的substring，
开头和结尾都有n种情况，所以有n^2种substring
然后每个substring，都要遍历完这个substring本身，才能确定是不是palindrome，这里要O(n)
所以brute force时间复杂度是O(n^3)
 */
class Solution1 {
    
    public String longestPalindrome(String s) {
        // corner case
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        int[] res = {0, 1}; // 记录当前最大palindrome的起点,和长度
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (isPalindrome(s, i, j)) {
                    if (j - i + 1 > res[1]) {
                        res[1] = j - i + 1;
                        res[0] = i;
                    }
                }
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }
    
    private boolean isPalindrome(String str, int i, int j) {
        int start = i;
        int end = j;
        while (start < end) { // <= 也可以
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
}

// Solution 2: DFS
// T(n) = O(n^2), S(n) = O(n^2)
// 734 ms,击败了6.70% 的Java用户, 49.8 MB,击败了5.95% 的Java用户
/*
isPal[i][j]: whether s.substring(i, j + 1) is palindrome
isPal[i][j] = isPal[i + 1][j - 1] if s[i] == s[j]
            = false
 */
class Solution2 {
    
    public String longestPalindrome(String s) {
        // corner case
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        int[] res = {0, 1}; // 记录当前最大palindrome的起点,和长度
        
        Integer[][] palLen = new Integer[len][len];
        longestPal(s, 0, len - 1, res, palLen);
        return s.substring(res[0], res[0] + res[1]);
    }
    
    // return the longest length of palindrome in the s[i, j]
    private int longestPal(String str, int i, int j, int[] res, Integer[][] palLen) {
        // base case
        if (palLen[i][j] != null) {
            return palLen[i][j];
        }
        if (i == j) {
            palLen[i][j] = 1;
            return palLen[i][j];
        }
        if (i + 1 == j) {
            boolean isPal = str.charAt(i) == str.charAt(j);
            if (isPal) {
                updatePal(i, j, res);
            }
            palLen[i][j] = isPal ? 2 : 1;
            return palLen[i][j];
        }
        
        // general case
        int res1 = 0, res2 = 0, res3 = 0;
        if (str.charAt(i) == str.charAt(j)) {
            res1 = longestPal(str, i + 1, j - 1, res, palLen);
            boolean isPal = res1 == j - i - 1;
            if (isPal) {
                res1 += 2;
                updatePal(i, j, res);
            }
        }
        res2 = longestPal(str, i + 1, j, res, palLen);
        res3 = longestPal(str, i, j - 1, res, palLen);
        palLen[i][j] = Math.max(res1, Math.max(res2, res3));
        return palLen[i][j];
    }
    
    private void updatePal(int i, int j, int[] res) {
        if (j - i + 1 > res[1]) {
            res[1] = j - i + 1;
            res[0] = i;
        }
    }
    
}

// Solution 3: DP
// Solution 3_1: DP: 按照从下到上铺isPal数组
// T(n) = O(n^2), S(n) = O(n^2)
// 166 ms,击败了37.53% 的Java用户, 42.4 MB,击败了29.94% 的Java用户
/*
isPal[i][j]: whether s.substring(i, j + 1) is palindrome
isPal[i][j] = isPal[i + 1][j - 1] if s[i] == s[j]
            = false
 */
class Solution3_1 {
    
    public String longestPalindrome(String s) {
        // corner case
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        int start = 0; // 记录当前最大palindrome的起点,
        int max = 1; // 记录当前最大palindrome的长度
        
        boolean[][] isPal = new boolean[len][len];
        
        // initialization
        for (int i = 0; i < len; i++) {
            isPal[i][i] = true;
        }
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j == i + 1) {
                        isPal[i][j] = true;
                    } else {
                        isPal[i][j] = isPal[i + 1][j - 1];
                    }
                    if (isPal[i][j] && j - i + 1 > max) {
                        max = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + max);
    }
    
    
}

// Solution 3: DP，沿着对角线，向斜向上铺isPal数组
// T(n) = O(n^2), S(n) = O(n^2)
// 190 ms,击败了34.13% 的Java用户, 43.1 MB,击败了20.78% 的Java用户
class Solution3_2 {
    
    public String longestPalindrome(String s) {
        // corner case
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        int max = 1; // 记录当前最大的palindrome的长度
        int start = 0; // 记录当前最大palindrome的起点
        boolean[][] isPal = new boolean[len][len];
        
        // initialization
        for (int i = 0; i < len; i++) {
            isPal[i][i] = true;
        }
        
        for (int size = 2; size <= len; size++) {
            for (int i = 0; i < len; i++) {
                if (i + size > len) {
                    break;
                }
                if (s.charAt(i) == s.charAt(i + size - 1)) {
                    if (size == 2) {
                        isPal[i][i + size - 1] = true;
                    } else {
                        isPal[i][i + size - 1] = isPal[i + 1][i + size - 2];
                    }
                    if (isPal[i][i + size - 1] && size > max) {
                        max = size;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + max);
    }
    
    
}

// Solution 4: Expand Around Center
// T(n) = O(n^2), S(n) = O(1)
// 13 ms,击败了95.73% 的Java用户, 38.8 MB,击败了82.36% 的Java用户
/*
用brute force的做法。在String里面遍历所有的substring，
开头和结尾都有n种情况，所以有n^2种substring
然后每个substring，都要遍历完这个substring本身，才能确定是不是palindrome，这里要O(n)
所以brute force时间复杂度是O(n^3)

但是在brute force的做法里面，我们发现有很多是重复计算的。
比如说substring(i, j) 和substring(i-1, j+1),前者substring(i, j) 的计算包括了substring(i-1, j+1)
如果能把这些重复计算避开就好了

发现从中间开始往两边扩展找palindrome比较好，不会有重复计算的地方。一共有2*n - 1个中间点
 */
class Solution4 {
    
    public String longestPalindrome(String s) {
        // corner case
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        int[] max = {1}; // 记录当前最大的palindrome的长度
        int[] start = {0}; // 记录当前最大palindrome的起点
        
        for (int i = 0; i < len; i++) {
            //当剩下的subString的最大长度比max[0]小的时候，就不需要比较了
            if (2 * Math.min(i + 1, len - i) < max[0]
                    && 2 * Math.min(i + 1, len - i - 1) < max[0]) {
                break;
            }
            findMax(s, i, i, max, start);
            findMax(s, i, i + 1, max, start);
        }
        // subString的区间是[left, right)
        return s.substring(start[0], start[0] + max[0]);
    }
    
    // find max length of palindrome centered at [left, right]
    private void findMax(String s, int left, int right, int[] max, int[] start) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        
        int dist = right - left - 1;
        
        if (dist > max[0]) {
            max[0] = dist;
            start[0] = left + 1;
        }
    }
    
}
}