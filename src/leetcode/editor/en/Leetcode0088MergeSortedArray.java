//Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one
// sorted array. 
//
// Note: 
//
// 
// The number of elements initialized in nums1 and nums2 are m and n respectivel
//y. 
// You may assume that nums1 has enough space (size that is equal to m + n) to h
//old additional elements from nums2. 
// 
//
// Example: 
//
// 
//Input:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//Output: [1,2,2,3,5,6]
// 
//
// 
// Constraints: 
//
// 
// -10^9 <= nums1[i], nums2[i] <= 10^9 
// nums1.length == m + n 
// nums2.length == n 
// 
// Related Topics Array Two Pointers 
// 👍 2301 👎 4223

package leetcode.editor.en;

// 2020-07-26 13:49:58
// Zeshi Yang
public class Leetcode0088MergeSortedArray{
    // Java: merge-sorted-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0088MergeSortedArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
两个指针，从后往前移动,防止overwrite
面试的时候，要clarify清楚
    nums1, nums2 == null 怎么办
    nums1的长度是不是比 m + n要大
 */
class Solution {
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null || m + n > nums1.length) {
            throw new IllegalArgumentException("not valid input");
        }
        
        int p = m + n - 1; // pointer
        int p1 = m - 1; // pointer 1
        int p2 = n - 1; // pointer 2
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                nums1[p--] = nums2[p2--];
            }
            // 可以简化成
            // nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}