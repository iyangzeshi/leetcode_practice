//There are two sorted arrays nums1 and nums2 of size m and n respectively. 
//
// Find the median of the two sorted arrays. The overall run time complexity sho
//uld be O(log (m+n)). 
//
// You may assume nums1 and nums2 cannot be both empty. 
//
// Example 1: 
//
// 
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0
// 
//
// Example 2: 
//
// 
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//The median is (2 + 3)/2 = 2.5
// 
// Related Topics Array Binary Search Divide and Conquer 
// 👍 7301 👎 1144

package leetcode.editor.en;

// 2020-07-26 14:00:14
// Zeshi Yang
public class Leetcode0004MedianOfTwoSortedArrays {
	
	// Java: median-of-two-sorted-arrays
	public static void main(String[] args) {
		Solution sol = new Leetcode0004MedianOfTwoSortedArrays().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
// index:  0   1   2   3   4   5
//             L1  R1
// num1:   3   5 | 8   9               4 cut1: 左边有4个元素
// num2:   1   2   7 | 10  11  12      6 cut2: 左边有6个元素
//                 L2  R2
class Solution {
	   
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int cut1;
        int cut2;
        int cutL = 0;
        int cutR = nums1.length;
        while (cutL <= cutR) {
            cut1 = cutL + (cutR - cutL) / 2;
            cut2 = len / 2 - cut1;
            double L1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double L2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double R1 = cut1 == len1 ? Integer.MAX_VALUE : nums1[cut1];
            double R2 = cut2 == len2 ? Integer.MAX_VALUE : nums2[cut2];
            if (L1 > R2) {
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                cutL = cut1 + 1;
            } else {
                if (len % 2 == 0) {
                    L1 = (Math.max(L1, L2));
                    R1 = (Math.min(R1, R2));
                    return (L1 + R1) / 2;
                } else {
                    R1 = Math.min(R1, R2);
                    return R1;
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}