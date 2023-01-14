//You are a product manager and currently leading a team to develop a new produc
//t. Unfortunately, the latest version of your product fails the quality check. Si
//nce each version is developed based on the previous version, all the versions af
//ter a bad version are also bad. 
//
// Suppose you have n versions [1, 2, ..., n] and you want to find out the first
// bad one, which causes all the following ones to be bad. 
//
// You are given an API bool isBadVersion(version) which will return whether ver
//sion is bad. Implement a function to find the first bad version. You should mini
//mize the number of calls to the API. 
//
// Example: 
//
// 
//Given n = 5, and version = 4 is the first bad version.
//
//call isBadVersion(3) -> false
//call isBadVersion(5) -> true
//call isBadVersion(4) -> true
//
//Then 4 is the first bad version. 
// 
// Related Topics Binary Search 
// 👍 1486 👎 684

package leetcode.editor.en;

// 2020-08-04 12:05:45
// Zeshi Yang
public class Leetcode0278FirstBadVersion{
    // Java: first-bad-version
    public static void main(String[] args) {
        Solution sol = new Leetcode0278FirstBadVersion().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    
public class Solution extends VersionControl {
    
    public int firstBadVersion(int n) {
        //corner case
        if (n <= 0) {
            return n;
        }
        if (n == 1) {
            return isBadVersion(n) ? 1 : 0;
        }
        
        int left = 1;
        int right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return isBadVersion(left) ? left : right;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
class VersionControl {
    boolean isBadVersion(int n) {
        return false;
    }
}

//模板1：出循环时，left + 1 = right
public class Solution1 extends VersionControl {
	
	public int firstBadVersion(int n) {
		//corner case
		if (n <= 0) {
			return n;
		}
		if (n == 1) {
			return isBadVersion(n) ? 1 : 0;
		}
		
		int left = 1;
		int right = n;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (isBadVersion(mid)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return isBadVersion(left) ? left : right;
	}
	
}

// 模板3：出循环时right + 1 = left
public class Solution2 extends VersionControl {
	public int firstBadVersion(int n) {
		// corner case
		if (n < 0) {
			return n;
		}
		
		if (n == 1) {
			return isBadVersion(n) ? 1 : 0;
		}
		
		int left = 1;
		int right = n;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (isBadVersion(mid)) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left <= n ? left : -1;
	}
}
}