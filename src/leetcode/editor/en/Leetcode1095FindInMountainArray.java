//(This problem is an interactive problem.) 
//
// You may recall that an array arr is a mountain array if and only if: 
//
// 
// arr.length >= 3 
// There exists some i with 0 < i < arr.length - 1 such that: 
// 
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i] 
// arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 
// 
// 
//
// Given a mountain array mountainArr, return the minimum index such that 
//mountainArr.get(index) == target. If such an index does not exist, return -1. 
//
// You cannot access the mountain array directly. You may only access the array 
//using a MountainArray interface: 
//
// 
// MountainArray.get(k) returns the element of the array at index k (0-indexed).
// 
// MountainArray.length() returns the length of the array. 
// 
//
// Submissions making more than 100 calls to MountainArray.get will be judged 
//Wrong Answer. Also, any solutions that attempt to circumvent the judge will 
//result in disqualification. 
//
// 
// Example 1: 
//
// 
//Input: array = [1,2,3,4,5,3,1], target = 3
//Output: 2
//Explanation: 3 exists in the array, at index=2 and index=5. Return the 
//minimum index, which is 2. 
//
// Example 2: 
//
// 
//Input: array = [0,1,2,4,2,1], target = 3
//Output: -1
//Explanation: 3 does not exist in the array, so we return -1.
// 
//
// 
// Constraints: 
//
// 
// 3 <= mountain_arr.length() <= 10⁴ 
// 0 <= target <= 10⁹ 
// 0 <= mountain_arr.get(index) <= 10⁹ 
// 
//
// Related Topics Array Binary Search Interactive 👍 3094 👎 126

package leetcode.editor.en;

// 2023-12-26 00:22:07
// Jesse Yang
public class Leetcode1095FindInMountainArray{
    // Java: find-in-mountain-array
    public static void main(String[] args) {
        Solution sol = new Leetcode1095FindInMountainArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
    
interface MountainArray {
    
    public int get(int index);
    
    public int length();
    
}
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
// T(n) = O(lgn), S(n) = O(lgn)
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        int res = 0;
        int left = 0;
        int right = len - 1;
        
        // find peak, peak is always in [left, right]
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid;
            } else { // mountainArr.get(mid) > mountainArr.get(mid + 1)
                right = mid;
            }
        }
        
        int peakIdx = (mountainArr.get(left) > mountainArr.get(right) ? left : right);
        
        // find target in increasing part
        left = 0;
        right = peakIdx;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // find target in decreasing part
        left = peakIdx;
        right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
        
    }
}


//leetcode submit region end(Prohibit modification and deletion)

}