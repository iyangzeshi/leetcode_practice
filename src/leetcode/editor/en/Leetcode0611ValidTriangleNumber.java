//Given an integer array nums, return the number of triplets chosen from the arr
//ay that can make triangles if we take them as side lengths of a triangle. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,2,3,4]
//Output: 3
//Explanation: Valid combinations are: 
//2,3,4 (using the first 2)
//2,3,4 (using the second 2)
//2,2,3
// 
//
// Example 2: 
//
// 
//Input: nums = [4,2,3,4]
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 1000 
// 
// Related Topics Array 
// 👍 1395 👎 106

package leetcode.editor.en;

import java.util.Arrays;

// 2021-05-02 16:51:30
// Zeshi Yang
public class Leetcode0611ValidTriangleNumber{
    // Java: valid-triangle-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0611ValidTriangleNumber().new Solution();
        // TO TEST
        int[] nums = {2,2,3,4};
        int res = sol.triangleNumber(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            int edge1 = nums[i];
            if (edge1 == 0) {
                continue;
            }
            int k = i + 2;
            for (int j = i + 1; j < len - 1; j++) {
                int edge2 = nums[j];
                while (k < len && edge1 + edge2 > nums[k]) {
                    k++;
                }
                count += k - j - 1;
            }
        }
        return count;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/** 面试的时候，用Solution 3*/

// Solution 1: brute force, traverse whole 3 edge combinations
// T(n) = O(n^3), S(n) = S(lg(n))
// 1672 ms,击败了5.07% 的Java用户, 38.2 MB,击败了70.60% 的Java用户
class Solution1 {
    
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums); // quick sort, S(n) = log(n)
        
        int count = 0;
        for (int i = 0; i < len - 2; i++) {
            int edge1 = nums[i];
            if (edge1 == 0) {
                continue;
            }
            for (int j = i + 1; j < len - 1; j++) {
                int edge2 = nums[j];
                for (int k = j + 1; k < len; k++) {
                    int edge3 = nums[k];
                    if (edge1 + edge2 > edge3) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
}

// Solution 2: 如果知道前面2个边，可以用binary search的方法找最后1条边的范围
// T(n) = O(n^2 * lg(n)), S(n) = S(lg(n))
// 420 ms,击败了7.52% 的Java用户, 38.5 MB,击败了28.88% 的Java用户
class Solution2 {
    
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            int edge1 = nums[i];
            for (int j = i + 1; j < len - 1 && nums[i] != 0; j++) {
                int edge2 = nums[j];
                int k = binarySearch(j + 1, len - 1, edge1 + edge2, nums);
                count += k - j;
            }
        }
        return count;
    }
    
    // find the index of smallest number that < target
    private int binarySearch(int left, int right, int target, int[] nums) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else { // nums[mid] < target
                left = mid + 1;
            }
        }
        return right;
    }
    
}

// Solution 3: 2 pointers, linear scan
// T(n) = O(n^2), S(n) = S(1)
// 38 ms,击败了29.76% 的Java用户, 38.5 MB,击败了28.88% 的Java用户
/*
edge1: nums[i]
edge2: nums[j];
edge3: nums[k]
step 1: 确定edge1
step 2: j > i, edge2和edge3要满足 edge1 + edge2 > edge3，双指针交替往后移动就行了
step 3: 每次确定了edge2之后，edge3往后移动一直走到edge1 + edge2 > edge3的最后一个。 count += k - j
step 4: edge2往后移动一格，重复step 3
 
 */
class Solution3 {
    
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            int edge1 = nums[i];
            if (edge1 == 0) {
                continue;
            }
            int k = i + 2;
            for (int j = i + 1; j < len - 1; j++) {
                int edge2 = nums[j];
                while (k < len && edge1 + edge2 > nums[k]) {
                    k++;
                }
                count += k - j - 1;
            }
        }
        return count;
    }
    
}
}