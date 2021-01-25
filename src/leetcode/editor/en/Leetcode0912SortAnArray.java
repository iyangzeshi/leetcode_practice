//Given an array of integers nums, sort the array in ascending order. 
//
// 
// Example 1: 
// Input: nums = [5,2,3,1]
//Output: [1,2,3,5]
// Example 2: 
// Input: nums = [5,1,1,2,0,0]
//Output: [0,0,1,1,2,5]
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 50000 
// -50000 <= nums[i] <= 50000 
// 
// 👍 563 👎 310

package leetcode.editor.en;

import java.util.Arrays;
import java.util.PriorityQueue;

// 2020-09-26 16:06:34
// Zeshi Yang
public class Leetcode0912SortAnArray{
    // Java: sort-an-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0912SortAnArray().new Solution();
        // TO TEST
        int[] nums = {-2, 3 ,-5};
        sol.sortArray(nums);
        System.out.println(Arrays.toString(nums));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int[] sortArray(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        quickSort(0, len - 1, nums);
        return nums;
    }
    
    private void quickSort(int start, int end, int[] nums) {
        // base case
        if (start >= end) {
            return;
        }
        
        int pivotIndex = start + (int) (Math.random() * (end - start + 1)); // in [start, end]
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, end);
        
        /*
        每个while循环开始之前
        [start, left) < pivotValue
        (right, end - 1] > = pivotValue
         */
        int left = start; // start pointer
        int right = end - 1; // end pointer
        
        while (left <= right) {
            if (nums[left] < pivot) {
                left++;
            } else if (nums[right] >= pivot) {
                //maybe duplicate
                right--;
            } else {
                // array[leftI] > pivotValue && nums[right] < pivotValue
                swap(nums, left++, right--);
            }
        }
        swap(nums, left, end);
        
        
        quickSort(start, left, nums);
        quickSort(left + 1, end, nums);
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)


// Solution 1: merge sort with helper array
// T(n) = O(nlog(n)), S(n) = O(n)
// 5 ms,击败了66.26% 的Java用户, 46.5 MB,击败了59.44% 的Java用户
class Solution1 {
    
    public int[] sortArray(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int len = nums.length;
        int[] helper = new int[len];
        mergeSort(0, len - 1, nums, helper);
        return nums;
    }
    
    // merge sort the nums[start] to nums[end]
    private void mergeSort(int start, int end, int[] nums, int[] helper) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(start, mid, nums, helper);
        mergeSort(mid + 1, end, nums, helper);
        merge(start, mid, end, nums, helper);
    }
    
    // merge robFrom nums[start]- nums[mid], and nums[mid + 1] to nums[end]
    private void merge(int start, int mid, int end, int[] nums, int[] helper) {
        int index = start;
        int left = start;
        int right = mid + 1;
        while (left <= mid && right <= end) {
            if (nums[left] < nums[right]) {
                helper[index++] = nums[left++];
            } else {
                helper[index++] = nums[right++];
            }
        }
        // left will reach to mid + 1 or right will reach to end + 1, only need to sort
        
        while (left <= mid) {
            helper[index++] = nums[left++];
        }
        // option 1
        /*System.arraycopy(helper, start, nums, start, right - start);
        return;*/
        //option 2
        while (right <= end) {
            helper[index++] = nums[right++];
        }
        System.arraycopy(helper, start, nums, start, end - start + 1);
    }
}

// Solution 2: quick sort
// T(n) = O(nlog(n)), S(n) = O(n)
// 7 ms,击败了33.56% 的Java用户, 46.8 MB,击败了27.99% 的Java用户
class Solution2 {
    
    public int[] sortArray(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        quickSort(0, len - 1, nums);
        return nums;
    }
    
    private void quickSort(int start, int end, int[] nums) {
        // base case
        if (start >= end) {
            return;
        }
        
        int pivotIndex = start + (int) (Math.random() * (end - start + 1)); // in [start, end]
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, end);
    
        /*
        每个while循环开始之前
        [start, left) < pivotValue
        (right, end - 1] > = pivotValue
         */
        int left = start; // start pointer
        int right = end - 1; // end pointer
        
        while (left <= right) {
            if (nums[left] < pivot) {
                left++;
            } else if (nums[right] >= pivot) {
                //maybe duplicate
                right--;
            } else {
                // array[leftI] > pivotValue && nums[right] < pivotValue
                swap(nums, left++, right--);
            }
        }
        swap(nums, left, end);
        
        quickSort(start, left, nums);
        quickSort(left + 1, end, nums);
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
}

// Solution 3: min heap
// T(n) = O(nlog(n)), S(n) = O(n)
// 17 ms,击败了15.72% 的Java用户, 48 MB,击败了14.59% 的Java用户
class Solution3 {
    
    public int[] sortArray(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : nums) {
            minHeap.offer(n);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = minHeap.poll();
        }
        return nums;
    }
    
}
}