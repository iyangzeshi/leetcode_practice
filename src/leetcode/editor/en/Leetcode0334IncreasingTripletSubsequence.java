//Given an unsorted array return whether an increasing subsequence of length 3 e
//xists or not in the array. 
//
// Formally the function should: 
//
// Return true if there exists i, j, k 
//such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false
//. 
//
// Note: Your algorithm should run in O(n) time complexity and O(1) space comple
//xity. 
//
// 
// Example 1: 
//
// 
//Input: [1,2,3,4,5]
//Output: true
// 
//
// 
// Example 2: 
//
// 
//Input: [5,4,3,2,1]
//Output: false
// 
// 
// 👍 1594 👎 133

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

// 2020-07-25 14:02:51
// Zeshi Yang
public class Leetcode0334IncreasingTripletSubsequence {

	// Java: increasing-triplet-subsequence
	public static void main(String[] args) {
		Solution sol = new Leetcode0334IncreasingTripletSubsequence().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean increasingTriplet(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }

        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int n : nums) {
            int third = n;
            if (third > second) {
                return true;
            } else if (third > first && third < second) {
                second = third;
            } else if (third < first) {
                first = third;
            }
        }
        return false;
    }


    // 找到index中，>= target值的索引
    private int replaceIdx(List<Integer> buffer, int target) {
        int len = buffer.size();
        // corner case
        if (len == 0) {
            return 0;
        }
        if (buffer.get(len - 1) < target) {
            return len;
        }

        // general case
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (buffer.get(mid) == target) {
                return mid;
            } else if (buffer.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;

    }

}

//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {

    public boolean increasingTriplet(int[] nums) {
        List<Integer> buffer = new ArrayList<>();
        for (int num : nums) {
            int idx = replaceIdx(buffer, num);
            if (idx < buffer.size()) {
                buffer.set(idx, num);
            } else {
                buffer.add(num);
                if (buffer.size() >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    // 找到index中，>= target值的索引
    private int replaceIdx(List<Integer> buffer, int target) {
        int len = buffer.size();
        // corner case
        if (len == 0) {
            return 0;
        }
        if (buffer.get(len - 1) < target) {
            return len;
        }

        // general case
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (buffer.get(mid) == target) {
                return mid;
            } else if (buffer.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;

    }

}
class Solution2 {

    public boolean increasingTriplet(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }

        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int n : nums) {
            int third = n;
            if (third > second) {
                return true;
            } else if (third > first && third < second) {
                second = third;
            } else if (third < first) {
                first = third;
            }
        }
        return false;
    }


    // 找到index中，>= target值的索引
    private int replaceIdx(List<Integer> buffer, int target) {
        int len = buffer.size();
        // corner case
        if (len == 0) {
            return 0;
        }
        if (buffer.get(len - 1) < target) {
            return len;
        }

        // general case
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (buffer.get(mid) == target) {
                return mid;
            } else if (buffer.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;

    }

}


}