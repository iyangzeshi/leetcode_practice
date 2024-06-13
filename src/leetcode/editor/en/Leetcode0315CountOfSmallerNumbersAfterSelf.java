
//You are given an integer array nums and you have to return a new counts array.
// The counts array has the property where counts[i] is the number of smaller elem
//ents to the right of nums[i]. 
//
// 
// Example 1: 
//
// 
//Input: nums = [5,2,6,1]
//Output: [2,1,1,0]
//Explanation:
//To the right of 5 there are 2 smaller elements (2 and 1).
//To the right of 2 there is only 1 smaller element (1).
//To the right of 6 there is 1 smaller element (1).
//To the right of 1 there is 0 smaller element.
// 
//
// Example 2: 
//
// 
//Input: nums = [-1]
//Output: [0]
// 
//
// Example 3: 
//
// 
//Input: nums = [-1,-1]
//Output: [0,0]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 
// Related Topics Binary Search Divide and Conquer Sort Binary Indexed Tree Segm
//ent Tree 
// 👍 3131 👎 100

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2021-02-04 17:09:51
// Jesse Yang
public class Leetcode0315CountOfSmallerNumbersAfterSelf{
    // Java: count-of-smaller-numbers-after-self
    public static void main(String[] args) {
        Solution sol = new Leetcode0315CountOfSmallerNumbersAfterSelf().new Solution();
        // TO TEST
        int[] nums = {-1};
        List<Integer> res = sol.countSmaller(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Node {
        int val;
        int count;
        int left_count;
        Node left;
        Node right;
        public Node(int val) { this.val = val; this.count = 1; }
        public int less_or_equal() { return count + left_count; }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0) return ans;
        int n = nums.length;
        Node root = new Node(nums[n - 1]);
        ans.add(0);
        for (int i = n - 2; i >= 0; --i)
            ans.add(insert(root, nums[i]));
        Collections.reverse(ans);
        return ans;
    }
    
    private int insert(Node root, int val) {
        if (root.val == val) {
            ++root.count;
            return root.left_count;
        } else if (val < root.val) {
            ++root.left_count;
            if (root.left == null) {
                root.left = new Node(val);
                return 0;
            }
            return insert(root.left, val);
        } else  {
            if (root.right == null) {
                root.right = new Node(val);
                return root.less_or_equal();
            }
            return root.less_or_equal() + insert(root.right, val);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: binary index tree (Fenwick tree) T(n) = O(nlog(n)), S(n) = O(k)
// k is the number of different numbers in the nums
// 56 ms,击败了33.39% 的Java用户, 54.9 MB,击败了20.98% 的Java用户
/*
binary index tree
先排序，得到数组sortedArray,

用HashMap<Integer, Integer> key: rank, value: count来记录每个数字是第几大rank

FenwickTree里面设置数组，记录rank和这个rank对应的数字，到当前位置出现的次数

对于nums中的每个数字，找到这个数字的rank，把这个rank的count++，然后找到0 ~ rank - 1出现次数求和
 */
class Solution1 {
    
    public class FenwickTree {
        
        private final int[] sums;
        
        public FenwickTree(int n) {
            sums = new int[n + 1];
        }
        
        /*
        return sum from array[1] to array[index], T(n) = O(lg(n))
         */
        public int query(int index) {
            int res = 0;
            for (int i = index; i > 0 ; i -= lowbit(i)) { // 边界是 i > 0
                res += sums[i];
            }
            return res;
        }
        
        /*
        add delta to array[index], T(n) = O(lg(n))
         */
        public void update(int index, int delta) {
            int len = sums.length;
            for (int i = index; i < len; i+=lowbit(i)) { // 与查询相反, 边界是 i < len
                sums[i] += delta;
            }
        }
        
        /*
        return the lowest 1, T(n) = O(1)
         */
        private int lowbit(int index) {
            return index & -index;
        }
        
    }
    
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        // write the number and its rank into hashMap
        for (int i = 0; i < sorted.length; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                ranks.put(sorted[i], ++rank);
            }
        }
        
        FenwickTree tree = new FenwickTree(ranks.size());
        // amount of number smaller than num for every num in nums
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            rank = ranks.get(num);
            int count = tree.query(rank - 1);
            res.add(count);
            tree.update(rank, 1);
        }
        
        Collections.reverse(res);
        return res;
    }
    
}
}