//Given an array nums and two types of queries where you should update the value
// of an index in the array, and retrieve the sum of a range in the array. 
//
// Implement the NumArray class: 
//
// 
// NumArray(int[] nums) initializes the object with the integer array nums. 
// void update(int index, int val) updates the value of nums[index] to be val. 
// int sumRange(int left, int right) returns the sum of the subarray nums[left, 
//right] (i.e., nums[left] + nums[left + 1], ..., nums[right]). 
// 
//
// 
// Example 1: 
//
// 
//Input
//["NumArray", "sumRange", "update", "sumRange"]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//Output
//[null, 9, null, 8]
//
//Explanation
//NumArray numArray = new NumArray([1, 3, 5]);
//numArray.sumRange(0, 2); // return 9 = sum([1,3,5])
//numArray.update(1, 2);   // nums = [1,2,5]
//numArray.sumRange(0, 2); // return 8 = sum([1,2,5])
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 3 * 104 
// -100 <= nums[i] <= 100 
// 0 <= index < nums.length 
// -100 <= val <= 100 
// 0 <= left <= right < nums.length 
// At most 3 * 104 calls will be made to update and sumRange. 
// 
// Related Topics Binary Indexed Tree Segment Tree 
// 👍 1761 👎 105

package leetcode.editor.en;

// 2021-04-07 21:02:02
// Zeshi Yang
public class Leetcode0307RangeSumQueryMutable{
    // Java: range-sum-query-mutable
    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray numArray = new Leetcode0307RangeSumQueryMutable().new NumArray(nums);
        // TO TEST
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }
//leetcode submit region begin(Prohibit modification and deletion)
// binary indexed tree
// T(n) = O(
// 60 ms,击败了97.88% 的Java用户, 70.3 MB,击败了60.50% 的Java用户
class NumArray {
    
    private final FenwickTree fenwickTree;
    private final int[] nums;
    
    public NumArray(int[] nums) {
        int len = nums.length;
        fenwickTree = new FenwickTree(len);
        this.nums = nums;
        
        for (int i = 0; i < len; i++) {
            fenwickTree.update(i + 1, nums[i]);
        }
    }
    // index begins from 0, so use index + 1
    public void update(int index, int val) {
        fenwickTree.update(index + 1, val - nums[index]);
        nums[index] = val;
    }
    
    public int sumRange(int left, int right) {
        return fenwickTree.rangeSum(left + 1, right + 1);
    }
    
}

/*
index of the fenwick tree is from 1 to n
 */
class FenwickTree {
    
    private final int[] sums;
    
    public FenwickTree(int n) {
        sums = new int[n + 1];
    }
    
    public int rangeSum(int from, int to) {
        return query(to) - query(from - 1);
    }
    
    public int query(int index) {
        int res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += sums[i];
        }
        return res;
    }
    
    public void update(int index, int delta) {
        int len = sums.length;
        for (int i = index; i < len; i += lowbit(i)) {
            sums[i] += delta;
        }
    }
    
    private int lowbit(int index) {
        return -index & index;
    }
    
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)

}