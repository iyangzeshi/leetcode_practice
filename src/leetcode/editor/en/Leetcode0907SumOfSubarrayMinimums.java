//Given an array of integers A, find the sum of min(B), where B ranges over ever
//y (contiguous) subarray of A. 
//
// Since the answer may be large, return the answer modulo 10^9 + 7. 
//
// 
//
// Example 1: 
//
// 
//Input: [3,1,2,4]
//Output: 17
//Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [
//1,2,4], [3,1,2,4]. 
//Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17. 
//
// 
//
// Note: 
//
// 
// 1 <= A.length <= 30000 
// 1 <= A[i] <= 30000 
// 
//
// 
// 
// 
//
// 
// Example 1: 
//
// 
//Input: arr = [3,1,2,4]
//Output: 17
//Explanation: 
//Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,
//2,4]. 
//Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//Sum is 17.
// 
//
// Example 2: 
//
// 
//Input: arr = [11,81,94,43,3]
//Output: 444
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 3 * 104 
// 1 <= arr[i] <= 3 * 104 
// 
// Related Topics Array Stack 
// 👍 1700 👎 111

package leetcode.editor.en;

import java.util.Stack;
// 2021-01-13 17:47:44
// Jesse Yang
public class Leetcode0907SumOfSubarrayMinimums{
    // Java: sum-of-subarray-minimums
    public static void main(String[] args) {
        Solution sol = new Leetcode0907SumOfSubarrayMinimums().new Solution();
        // TO TEST
        int[] arr = {3,1,2,4};
        int res = sol.sumSubarrayMins(arr);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007; // 1e9 + 7
        Stack<Integer> stackNum = new Stack<>(); // keep a increasing stack from bottom
        Stack<Integer> stackWeight = new Stack<>();
        int weightedSum = 0; // weighted sum of stackNum and stackWeight
        int res = 0;
        for (int num: arr) {
            int weight = 0;
            while (!stackNum.isEmpty() &&stackNum.peek() >= num) {
                int prevNum = stackNum.pop();
                int prevWeight = stackWeight.pop();
                weightedSum -=  prevWeight * prevNum;
                weight += prevWeight;
            }
            weight += 1;
            stackNum.push(num);
            stackWeight.push(weight);
            weightedSum += weight * num;
            res += weightedSum;
            res %= MOD;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: keep a increasing stack to record the min value of all subarray ends in current value
// T(n) = O(n), S(n) = O(n)
// 23 ms,击败了77.55% 的Java用户, 46.9 MB,击败了42.77% 的Java用户
/*
when comes in a new number arr[i]
pop all the numbers >= arr[i],
after all the pop, and push the arr[i] to the stack
now, the nums in stack is min value of the sub array whose start = 0 to i, end is i;
and using another stackWeight to maintain the weight of num in stack
stackWeight[i] is the weight of the stackNum[i]
    when calculating the min value in the all subarray ends in the i

update the weightedSum to record the weighted sum of the stackNum and stackWeight instead of
calculating from scratch, which means the sum of the min number in all sub arrays ends in the i

res += weightedSum;

举例， arr 是 3, 1, 2, 4

每次进来一个新的数字，把它pop的所有元素的weight求和，再加上它自己的权重1，就是这个新数字的weight

进来3的时候；
    stackNum    3
    stackWeight 1
    以3结尾的所有subarray的最小值有3， 3出现了1次
    weightedSum = 3 * 1 = 3;表示以3结尾的所有subarray的最小值的求和是3
    res = res + weightedSum = 0 + 3 = 3;

进来1的时候:
    stackNum    1
    stackWeight 1 + 1 = 2
    以1结尾的所有sub array的最小值，1出现了1次
    weightedSum = 3 - 3 * 1 + 1 * 2 = 2;表示以3结尾的所有subarray的最小值的求和是2
    res = res + weightedSum = 3 + 2 = 5;

进来 2的时候：
    stackNum    1, 2
    stackWeight 2, 1
    以1结尾的所有sub array的最小值，1出现了2次,2出现了1次
    weightedSum = 2  + 2 * 1 = 4;表示以2结尾的所有subarray的最小值的求和是4
    res = res + weightedSum = 5 + 4 = 9;
    
进来4的时候：
    stackNum    1, 2, 4
    stackWeight 2, 1, 1
    以1结尾的所有sub array的最小值，1出现了2次,2出现了1次
    weightedSum = 4 + 4 * 1 = 8;表示以4结尾的所有subarray的最小值的求和是4
    res = res + weightedSum = 9 + 4 = 13;
 */
class Solution1 {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007; // 1e9 + 7
        Stack<Integer> stackNum = new Stack<>(); // keep a increasing stack from bottom
        Stack<Integer> stackWeight = new Stack<>();
        int weightedSum = 0; // weighted sum of stackNum and stackWeight
        int res = 0;
        for (int num: arr) {
            int weight = 0;
            while (!stackNum.isEmpty() &&stackNum.peek() >= num) {
                int prevNum = stackNum.pop();
                int prevWeight = stackWeight.pop();
                weightedSum -=  prevWeight * prevNum;
                weight += prevWeight;
            }
            weight += 1;
            stackNum.push(num);
            stackWeight.push(weight);
            weightedSum += weight * num;
            res += weightedSum;
            res %= MOD;
        }
        return res;
    }
}

// Solution 2:
// T(n) = O(n), S(n) = O(n)
// 37 ms,击败了29.68% 的Java用户, 46.4 MB,击败了58.19% 的Java用户
/*
      arr:   3  1  2  4
      计算每一个数字成为min的次数
      如果次数分别为 x,y,z,k, 那么最后答案为 3x + 1y + 2z + 4k
      
      假设现在有一个subarray [....., 1, ......]
      如果 1 是这个 subarray 的 min，
      那么 包含1的左半段最小值要是1，包含1的右半段最小值要是1
      
      现在就看包含1的左半段 且 min == 1 有几个
      包含1的右半段 且 min == 1 有几个
      ==》两个数乘一下 就得出 min 为 1 的 subarray 一共有几个
      
      几个怎么算？
           7  0  3  5  [1]  2  4
           在 1 的左边 和 1 连续的且比 1 大的数字的个数
           比如这个例子中，1左边连续比1大的数字个数有2个 (5和3)
                        1右边连续比1大的数字个数有2个 (2和4)
           ==> 加上1自己，1左半边有2+1=3个， 右半边有2+1=3个，
           ==> 所以 1 为 min 的subarray 个数为 3 * 3 = 9 个
           
      所以用两个数组记录一下个数 (包含自己以及连续相邻比自己大的)
                    7  0  5  3  1  2  4
      leftCount     1  2  1  2  3  1  1
     rightCount     1  6  1  1  3  2  1
        product     1  12 1  2  9  2  1
    final answer: 1*7 + 12*0 + 1*5 + 2*3 + 9*1 + 2*2 + 1*4
      
      获得leftCount和rightCount 用单调递增(可以相等)stack来做, stack里面放idx,但比较的是idx对应的值
      leftCount从左往右扫
      rightCount从右往左扫
      
      arr： 3 1 2 4
      leftCount[ 1, 1+1, 1, 1]
      stack
      -----------------------------------
      | (idx:0|3)
      -----------------------------------
      -----------------------------------
      | (idx:1|1)
      -----------------------------------
      -----------------------------------
      | (idx:1|1) (idx:2|2)
      -----------------------------------
      -----------------------------------
      | (idx:1|1) (idx:2|2) (idx:3|4)
      -----------------------------------
      
    */
class Solution2 {
    
    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int MOD = 1_000_000_007;
        int len = arr.length;
        
        int[] leftCount = new int[len];
        int[] rightCount = new int[len];
        
        Stack<Integer> stack = new Stack<>(); // 存idx, 但是放进stack之前，比较栈顶idx对应的值的大小
        //count length of continuous bigger number at the left side
        for (int i = 0; i < leftCount.length; i++) {
            leftCount[i] = 1;
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                leftCount[i] += leftCount[stack.peek()];
                stack.pop();
            }
            stack.push(i);
        }
        stack.clear();
        //count length of continuous bigger number at the right side
        for (int i = rightCount.length - 1; i >= 0; i--) {
            rightCount[i] = 1;
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                rightCount[i] += rightCount[stack.peek()];
                stack.pop();
            }
            stack.push(i);
        }
        
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            long partialSum = ((long)arr[i] * ((long) leftCount[i] * rightCount[i])) % MOD;
            sum += partialSum;
            sum = sum % MOD;
        }
        return (int)sum;
    }
}
}