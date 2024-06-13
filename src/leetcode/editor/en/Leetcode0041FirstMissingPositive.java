//Given an unsorted integer array, find the smallest missing positive integer. 
//
// Example 1: 
//
// 
//Input: [1,2,0]
//Output: 3
// 
//
// Example 2: 
//
// 
//Input: [3,4,-1,1]
//Output: 2
// 
//
// Example 3: 
//
// 
//Input: [7,8,9,11,12]
//Output: 1
// 
//
// Follow up: 
//
// Your algorithm should run in O(n) time and uses constant extra space. 
// Related Topics Array 
// 👍 4013 👎 840

package leetcode.editor.en;

// 2020-09-12 13:30:15
// Jesse Yang
public class Leetcode0041FirstMissingPositive{
    // Java: first-missing-positive
    public static void main(String[] args) {
        Solution sol = new Leetcode0041FirstMissingPositive().new Solution();
        // TO TEST
        int[] nums = {3, 4, -1, 1, 8};
        int res = sol.firstMissingPositive(nums);
        System.out.println(res);
    }

//leetcode submit region begin(Prohibit modification and deletion)
// Solution 1:
/* 创建一个数组arr,假设的index从1开始,设置数组的大小和nums的一样大.
 如果nums里面有i,就在arr里面第i个位置存i,
 在arr里第一个第i个位置存的数字不是i,这个位置就是最小的missing number
 想办法inplace操作,直接在nums上面操作*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        // traverse every number
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                // 第nums[i]个位置的下标是nums[i] - 1
                swap(nums, i, nums[i] - 1);
            }
        }

        // 找出第一个nums[i] != i + 1的位置
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: T(n) = O(n), S(n) = O(n)
/* 创建一个数组arr,假设的index从1开始,设置数组的大小和nums的一样大.
 如果nums里面有i,就在arr里面第i个位置存i,
 在arr里第一个第i个位置存的数字不是i,这个位置就是最小的missing number
 想办法inplace操作,直接在nums上面操作*/
class Solution1 {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        // traverse every number
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                // 第nums[i]个位置的下标是nums[i] - 1
                swap(nums, i, nums[i] - 1);
            }
        }

        // 找出第一个nums[i] != i + 1的位置
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// Solution 2: T(n) = O(n), S(n) = O(1)
/*同样的，我们先考虑如果可以有额外的空间该怎么做。
还是一样，对于 nums = [ 3 4 -1 1 8] ，我们创建一个等大的数组 a，初始化为 [ false，false，false，false，false ]。然后如果 nums 里有 1 就把，第一个位置 a [ 0 ] 改为 true。如果 nums 里有 m ，就把 a [ m - 1 ] 改为 true。看下具体的例子。
nums = [ 3 4 -1 1 8]
nums [ 0 ] 等于 3，更新 a [ false，false，true，false，false ]。
nums [ 1 ] 等于 4，更新 a [ false，false，true，true，false ] 。
nums [ 2 ] 等于 - 1，不是正数，忽略。
nums [ 3 ] 等于 1，更新 a [ true，false，true，true，false ] 。
nums [ 4 ] 等于 8，我们的 a 数组只能存 1 到 5，所以同样忽略。
然后遍历数组 a ，如果 a [ i ] != true。那么，我们就返回 i + 1。因为 a [ i ] 等于 true 就意味着 i + 1 存在。
问题又来了，其实我们没有额外空间，我们只能利用原来的数组 nums。
同样我们直接把 nums 用作数组 a。
但当我们更新的时候，如果直接把数组的数赋值成 true，那么原来的数字就没了。这里有个很巧妙的技巧。
考虑到我们真正关心的只有正数。开始 a 数组的初始化是 false，所以我们把正数当做 false，负数当成 true。如果我们想要把 nums [ i ] 赋值成 true，如果 nums [ i ] 是正数，我们直接取相反数作为标记就行，如果是负数就不用管了。这样做的好处就是，遍历数字的时候，我们只需要取绝对值，就是原来的数了。
当然这样又带来一个问题，我们取绝对值的话，之前的负数该怎么办？一取绝对值的话，就会造成干扰。简单粗暴些，我们把正数都放在前边，我们只考虑正数。负数和 0 就丢到最后，遍历的时候不去遍历就可以了。*/
class Solution2 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //将正数移到前边，并且得到正数的个数
        int k = positiveNumber(nums);
        for (int i = 0; i < k; i++) {
            //得到要标记的下标
            int index = Math.abs(nums[i]) - 1;
            if (index < k) {
                //判断要标记的位置的数是不是小于 0，不是小于 0 就取相反数
                int temp = Math.abs(nums[index]);
                nums[index] = temp < 0 ? temp : -temp;
            }
        }
        //找到第一个大于 0 的位置
        for (int i = 0; i < k; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return k + 1;
    }

    private int positiveNumber(int[] nums) {
        //解法一 把负数和 0 全部交换到最后
       /*int n = nums.length;
       for (int i = 0; i < n; i++) {
           while (nums[i] <= 0) {
               swap(nums, i, n - 1);
               n--;
               if (i == n) {
                   break;
               }
           }
       }
       return n;*/

        //解法二 用一个指针 p ，保证 p 之前的都是正数。遍历 nums，每遇到一个正数就把它交换到 p 指针的位置，并且 p 指针后移
        int n = nums.length;
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                swap(nums, i, p);
                p++;
            }
        }
        return p;

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
}