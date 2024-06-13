//Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find 
//the one that is missing from the array. 
//
// Example 1: 
//
// 
//Input: [3,0,1]
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: [9,6,4,2,3,5,7,0,1]
//Output: 8
// 
//
// Note: 
//Your algorithm should run in linear runtime complexity. Could you implement it
// using only constant extra space complexity? Related Topics Array Math Bit Manip
//ulation 
// 👍 1810 👎 2115

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
// 2020-07-26 11:56:22
// Jesse Yang
public class Leetcode0268MissingNumber{
    // Java: missing-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0268MissingNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int missingNumber(int[] nums) {
        /*
         S7: Bit Manipulation: A XOR A will be 0, so let the nums XOR the array
         */
        int target = nums.length;
        for (int i = 0; i < nums.length; i++) {
            target ^= i ^ nums[i];
        }
        return target;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// S1, S2：等比数列性质，是不是等比，或者符合方程y = kx + b, T(n) = O(nlog(n)), S(n) = O(n)
/*
 S1: sort → one pass → check array[fast] - array[fast] == 2
 return fast／array[fast] - 1

 S2: Sort→one pass→check value with index,
 index can be implemented with counter initialized with 0;
 */
class Solution1And2 {
    
    public int missingNumber(int[] nums) {
        
        Arrays.sort(nums);
        int length = nums.length;
        
        if (nums[nums.length - 1] != nums.length) {
            /*
             如果排序之后，最后一顶的值是num.length - 1（不等于nu.length), 说明前面的顺序都是对的，的数字就是最大值 +1,就是nums.length
             */
            return nums.length;
        } else if (nums[0] != 0) {
            return 0;
        }
        
        // If we get here, then the missing number is on the range (0, n)
        for (int i = 1; i < length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        
        return -1;
    }
    
}

// S3: Sort → binary search, check index and value, T(n) = O(nlog(n)), S(n) = O(n)
/*
sort and binary search
 */
class Solution3 {
    
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        if (nums[nums.length - 1] != nums.length) {
            return nums.length;
        } else if (nums[0] != 0) {
            return 0;
        }
        
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] != mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[left] == left ? right : left;
        
    }
    
}

// S4, S5:  HashSet && HashMap（没有必要）, T(n) = O(n), S(n) = O(n)
/*
 S5: HashSet <key = value of element>
 */
class Solution4And5 {
    public int missingNumber(int[] nums) {
        
        Set<Integer> numSet = new HashSet<>();
        for (int num: nums) {
            numSet.add(num);
        }

        int expectedNumCount = nums.length + 1;
        for (int i = 0; i < expectedNumCount; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return -1;

    }
}
// S6: Math, T(n) = O(n), S(n) = O(1)
/*
calculate the expected Sum of Arithmetic Sequence, then calculate the real sum.
the difference is the missing number
 */
class Solution6 {
    
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
    
}
// S7: bit operation,, T(n) = O(n), S(n) = O(1)
/*
any number that ^ itself will become 0
^ (and or) every number in nums array and ^ every number [0, n]
then every number in nums array will operated ^ twice
expect for the missing number which will be operated once.
So the result of ^ will be the missing number
 */
class Solution7 {
    public int missingNumber(int[] nums) {
        /*
         S7: Bit Manipulation: A XOR A will be 0, so let the nums XOR the array
         */
        int target = nums.length;
        for (int i = 0; i < nums.length; i++) {
            target ^= i ^ nums[i];
        }
        return target;
    }
}
}