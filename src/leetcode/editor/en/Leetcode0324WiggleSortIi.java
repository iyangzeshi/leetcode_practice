//Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2]
// < nums[3].... 
//
// Example 1: 
//
// 
//Input: nums = [1, 5, 1, 1, 6, 4]
//Output: One possible answer is [1, 4, 1, 5, 1, 6]. 
//
// Example 2: 
//
// 
//Input: nums = [1, 3, 2, 2, 3, 1]
//Output: One possible answer is [2, 3, 1, 3, 1, 2]. 
//
// Note: 
//You may assume all input has valid answer. 
//
// Follow Up: 
//Can you do it in O(n) time and/or in-place with O(1) extra space? 
// Related Topics Sort 
// 👍 1238 👎 618

package leetcode.editor.en;

import java.util.Arrays;

// 2020-12-30 18:54:20
// Jesse Yang
public class Leetcode0324WiggleSortIi{
    // Java: wiggle-sort-ii
    public static void main(String[] args) {
        /*Solution1 sol1 = new Leetcode0324WiggleSortIi().new Solution1();
        Solution2_1 sol2_1 = new Leetcode0324WiggleSortIi().new Solution2_1();
        Solution2_2 sol2_2 = new Leetcode0324WiggleSortIi().new Solution2_2();
    
        // TO TEST
        int len = 100000000;
        Random random = new Random();
        int[] nums1 = new int[len];
        int[] nums2_1 = new int[len];
        int[] nums2_2 = new int[len];
        for (int i = 0; i < len; i++) {
            nums1[i] = random.nextInt();
            nums2_1[i] = nums1[i];
            nums2_2[i] = nums1[i];
        }
        long time0 = System.currentTimeMillis();
        sol1.wiggleSort(nums2_1);
        long time1 = System.currentTimeMillis();
        sol2_1.wiggleSort(nums2_1);
        long time2_1 = System.currentTimeMillis();
        sol2_2.wiggleSort(nums2_2);
        long time2_2 = System.currentTimeMillis();
        
        System.out.println(time1 - time0 + "ms");
        System.out.println(time2_1 - time1 + "ms");
        System.out.println(time2_2 - time2_1 + "ms");*/
        Solution sol = new Leetcode0324WiggleSortIi().new Solution();
        int[] nums = {0,1,2,3,4,5};
        sol.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public void wiggleSort(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        int k = (len + 1) / 2;
        int middleLeft = findPosPartition(k, 0, len - 1, nums); // kth smallest value
        int middleRight = findPosPartition(k + 1, 0, len - 1, nums);
        /*
        将偶数index数字都设置成 <= middleLeft
            == middleLeft的部分都尽可能放在左边
        将奇数index数字都设置成 >= middleRight
            == middleRight的尽可能放在右边
         */
        int left = 0; // [0, left] is middleLeft, even pointer
        int right = (len % 2 == 0 ? len - 1 : len - 2); //[right, end] is middleRight, pdd pointer
        
        swap(nums, k - 1, left);
        swap(nums, k, right);
        
        int evenP = (len % 2 == 0 ? len - 2 : len - 1); // even pointer, <-
        int oddP = 1; // odd pointer, ->
        
        for (int i = 0; i < len; i++) {
            if (i > evenP && (i - evenP) % 2 == 0) {
                continue;
            }
            if (nums[i] < middleLeft) { // 比middleLeft小的数字，从右边往左边放
                swap(nums, i, evenP);
                evenP -= 2;
                i--;
            }
        }
        for (int i = 0; i < len; i++) {
            if (oddP > i && (oddP - i) % 2 == 0) {
                continue;
            }
            if (nums[i] > middleRight) { // 比middleRight大的数字，从左往右边放
                swap(nums, i, oddP);
                oddP += 2;
                i--;
            }
        }
        
        for (int i = oddP; i <= right; i += 2) {
            nums[i] = middleRight;
        }
        for (int i = left; i <= evenP; i += 2) {
            nums[i] = middleLeft;
        }
        
    }
    
    // using quick selection to find kth smallest value in nums and put it the right place
    // average T(n) = O(n), worst T(n) = O(n^2), average S(n) = O(lg(n)), worst S(n) = O(n)
    private int findPosPartition(int k, int left, int right, int[] nums) {
        boolean firstloop = true;
        while (true) {
            int pivotIndex = firstloop ? k - 1 : left + (int) (Math.random() * (right - left + 1));
            firstloop = false;
            int pivot = nums[pivotIndex];
            swap(nums, pivotIndex, right);
            
            //use less, equal, larger pointers, 3 pointers forward
            /*
             * [left, less) < pivot
             * [less, equal) = pivot
             * [equal, larger] remain to check
             * (larger, right - 1] > pivot
             */
            int less = left;
            int equal = left;
            int larger = right - 1;
            while (equal <= larger) {
                if (nums[equal] < pivot) {
                    swap(nums, less, equal);
                    equal++;
                    less++;
                } else if (nums[equal] == pivot) {
                    equal++;
                } else { // nums[equal] > pivot
                    swap(nums, equal, larger);
                    larger--;
                }
            }
            swap(nums, equal, right); // move the pivot from right to the real place
            // [left, less) < pivot,
            // [less, equal] == pivot,
            // (equal, right] > pivot
            // After operation, the target(pivot) 's index is equal;
            if (less <= k - 1 && k - 1 <= equal) {
                return nums[less];
            } else if (k - 1 > equal) {
                left = equal + 1;
            } else {
                right = less - 1;
            }
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// 面试的时候，先写Solution 1, 要follow up，要T(n) = O(n)解的时候，再写Solution 2_2


// Solution 1:T(n) = O(nlg(n)), S(n) = O(n)
// 3 ms,击败了86.77% 的Java用户, 42.1 MB,击败了41.11% 的Java用户
/*
用arrays.sort，建立一个新数组，然后从小到大，先赋值temp偶数位，再赋值temp奇数位，再把temp复制给nums
 */
class Solution1 {
    
    public void wiggleSort(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        Arrays.sort(nums);// O(nlg(n))
        int len = nums.length;
        int mid = (len - 1) / 2;
        int index = 0;
        int[] temp = new int[len];
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid - i];
            if (index + 1 < len) {
                temp[index + 1] = nums[len - 1 - i];
            }
            index += 2;
        }
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }
    
}

// Solution 2_1: quick selection to find middle value and then arrange the whole nums
// Average T(n) = O(n), worst case T(n) = O(n^2), S(n) = O(1)
// 4 ms,击败了50.59% 的Java用户, 42.6 MB,击败了23.21% 的Java用户
/*
使用quick selection，中位数对应的两个数字middleLeft和middleRight,
    分成3个point找middle left，一个 <,一个 =, 一个 >, unstable的方法
把所有比中位数小的数字从前往后放到奇数位置上
把所有比中位数大的数字，从后往前放到偶数位置上
 */
class Solution2_1 {
    
    public void wiggleSort(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        int k = (len - 1) / 2;
        int middleLeft = findPosPartition(k, 0, len - 1, nums);
        int middleRight = findPosPartition(k + 1, 0, len - 1, nums);
        /*
        将偶数index数字都设置成 <= middleLeft
            == middleLeft的部分都尽可能放在左边
        将奇数index数字都设置成 >= middleRight
            == middleRight的尽可能放在右边
         */
        int left = 0; // [0, left] is middleLeft, even pointer
        int right = (len % 2 == 0 ? len - 1 : len - 2); //[right, end] is middleRight, pdd pointer
        
        swap(nums, k, left);
        swap(nums, k + 1, right);
        
        int evenP = (len % 2 == 0 ? len - 2 : len - 1); // even pointer, <-
        int oddP = 1; // odd pointer, ->
        
        for (int i = 0; i < len; i++) {
            if (i > evenP && (i - evenP) % 2 == 0) {
                continue;
            }
            if (nums[i] < middleLeft) { // 比middleLeft小的数字，从右边往左边放
                swap(nums, i, evenP);
                evenP -= 2;
                i--;
            }
        }
        for (int i = 0; i < len; i++) {
            if (oddP > i && (oddP - i) % 2 == 0) {
                continue;
            }
            if (nums[i] > middleRight) { // 比middleRight大的数字，从左往右边放
                swap(nums, i, oddP);
                oddP += 2;
                i--;
            }
        }
        
        for (int i = oddP; i <= right; i += 2) {
            nums[i] = middleRight;
        }
        for (int i = left; i <= evenP; i += 2) {
            nums[i] = middleLeft;
        }
    }
    
    // using quick selection to find kth smallest(start from 0) value in nums
    // and put it the right place
    // average T(n) = O(n), worst T(n) = O(n^2), average S(n) = O(lg(n)), worst S(n) = O(n)
    private int findPosPartition(int k, int left, int right, int[] nums) {
        boolean firstloop = true;
        while (true) {
            int pivotIndex = firstloop ? k : left + (int) (Math.random() * (right - left + 1));
            firstloop = false;
            int pivot = nums[pivotIndex];
            swap(nums, pivotIndex, right);
            
            //use less, equal, larger pointers, 3 pointers forward
            /*
             * [left, less) < pivot
             * [less, equal) = pivot
             * [equal, larger] remain to check
             * (larger, right - 1] > pivot
             */
            int less = left;
            int equal = left;
            int larger = right - 1;
            while (equal <= larger) {
                if (nums[equal] < pivot) {
                    swap(nums, less, equal);
                    equal++;
                    less++;
                } else if (nums[equal] == pivot) {
                    equal++;
                } else { // nums[equal] > pivot
                    swap(nums, equal, larger);
                    larger--;
                }
            }
            swap(nums, equal, right); // move the pivot from right to the real place
            // [left, less) < pivot,
            // [less, equal] == pivot,
            // (equal, right] > pivot
            // After operation, the target(pivot) 's index is equal;
            if (less <= k && k <= equal) {
                return nums[less];
            } else if (k > equal) {
                left = equal + 1;
            } else {
                right = less - 1;
            }
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// Solution 2_2: quick selection to find middle value and then arrange the whole nums
// Average T(n) = O(n), worst case T(n) = O(n^2),S(n) = O(1)
// 5 ms,击败了45.56% 的Java用户, 42.6 MB,击败了23.21% 的Java用户
/*
使用quick selection，中位数对应的两个数字middleLeft和middleRight,
    分成3个pointer找middle left, 一个 < , 一个 =, 一个> stable的方法
把所有比中位数小的数字从前往后放到奇数位置上
把所有比中位数大的数字，从后往前放到偶数位置上
 */
class Solution2_2 {
    
    public void wiggleSort(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        int k = (len - 1) / 2;
        int middleLeft = findPosPartition(k, 0, len - 1, nums); // kth smallest value
        int middleRight = findPosPartition(k + 1, 0, len - 1, nums);
        /*
        将偶数index数字都设置成 <= middleLeft
            == middleLeft的部分都尽可能放在左边
        将奇数index数字都设置成 >= middleRight
            == middleRight的尽可能放在右边
         */
        int left = 0; // [0, left] is middleLeft, even pointer
        int right = (len % 2 == 0 ? len - 1 : len - 2); //[right, end] is middleRight, pdd pointer
        
        swap(nums, k, left);
        swap(nums, k + 1, right);
        
        int evenP = (len % 2 == 0 ? len - 2 : len - 1); // even pointer, <-
        int oddP = 1; // odd pointer, ->
        
        for (int i = 0; i < len; i++) {
            if (i > evenP && (i - evenP) % 2 == 0) {
                continue;
            }
            if (nums[i] < middleLeft) { // 比middleLeft小的数字，从右边往左边放
                swap(nums, i, evenP);
                evenP -= 2;
                i--;
            }
        }
        for (int i = 0; i < len; i++) {
            if (oddP > i && (oddP - i) % 2 == 0) {
                continue;
            }
            if (nums[i] > middleRight) { // 比middleRight大的数字，从左往右边放
                swap(nums, i, oddP);
                oddP += 2;
                i--;
            }
        }
        
        for (int i = oddP; i <= right; i += 2) {
            nums[i] = middleRight;
        }
        for (int i = left; i <= evenP; i += 2) {
            nums[i] = middleLeft;
        }
    }
    
    // using quick selection to find kth smallest(start from 0) value in nums
    // and put it the right place
    // average T(n) = O(n), worst T(n) = O(n^2), average S(n) = O(lg(n)), worst S(n) = O(n)
    private int findPosPartition(int k, int left, int right, int[] nums) {
        boolean firstloop = true;
        while (true) {
            int pivotIndex = firstloop ? k : left + (int) (Math.random() * (right - left + 1));
            firstloop = false;
            int pivot = nums[pivotIndex];
            swap(nums, pivotIndex, right);
        
            //use less, equal, larger pointers, 3 pointers forward
            /*
             * [left, less) < pivot
             * [less, equal) = pivot
             * [equal, larger) > pivot
             * [fast, right) remain to check
             */
            int less = left;
            int equal = left;
            int larger;
            for (larger = left; larger < right; larger++) {
                if (nums[larger] < pivot) {
                    // 只能按照这个顺序，或者逆顺序。不能先less和larger交换, 否则不满足equal >= less
                    swap(nums, equal, larger);
                    swap(nums, less, equal);
                    equal++;
                    less++;
                } else if (nums[larger] == pivot) {
                    swap(nums, equal, larger);
                    equal++;
                }
            }
            swap(nums, equal, right); // move the pivot from right to the real place
            // [left, less) < pivot,
            // [less, equal] == pivot,
            // (equal, right] > pivot
            // After operation, the target(pivot) 's index is equal;
            if (less <= k && k <= equal) {
                return nums[less];
            } else if (k > equal) {
                left = equal + 1;
            } else {
                right = less - 1;
            }
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

}