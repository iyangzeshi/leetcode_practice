/**
Given an array of integers nums and an integer target, return indices of the 
two numbers such that they add up to target. 

 You may assume that each input would have exactly one solution, and you may 
not use the same element twice. 

 You can return the answer in any order. 

 
 Example 1: 

 
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 

 Example 2: 

 
Input: nums = [3,2,4], target = 6
Output: [1,2]
 

 Example 3: 

 
Input: nums = [3,3], target = 6
Output: [0,1]
 

 
 Constraints: 

 
 2 <= nums.length <= 10⁴ 
 -10⁹ <= nums[i] <= 10⁹ 
 -10⁹ <= target <= 10⁹ 
 Only one valid answer exists. 
 

 
Follow-up: Can you come up with an algorithm that is less than 
O(n²)
 time complexity?

 Related Topics Array Hash Table 👍 65759 👎 2443

*/
package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


// 2020-07-26 13:50:13
// Jesse Yang
public class Leetcode0001TwoSum{
    // Java: two-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0001TwoSum().new Solution();
        // TO TEST
        int[] nums = {2,7,11,15,16,7,8};
        int target = 9;
        int[] res = sol.twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution: hashMap
// T(n) = O(n), S(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 39.2 MB,击败了48.80% 的Java用户
/*推理过程：
如果target符合要求的话。一定存在某个 i 和 j， 使得
    nums[i] + nums[j] = target. assuming (j > i)
所以一定有:
    target - nums[j] = nums[i]
联想到HashMap

解决方法：
step1: HashMap<Integer, Integer> number, value: index of number
    hashMap先加一个(0, 1)
step2: 然后遍历num数组，每遍历一个数字，看把cumsum[i]放到HashMap里面，
step2: 然后看target - nums[j] 在不在HashMap里面，在的话，就表示存在
        然后就返回return new int[]{map.get(target - nums[j]), j};
*/
class Solution {
    
    public int[] twoSum(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length < 2) {
            return null;
        }
        
        // general case
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}