/**
 There are n gas stations along a circular route, where the amount of gas at the
 iᵗʰ station is gas[i].
 
 You have a car with an unlimited gas tank and it costs cost[i] of gas to
 travel from the iᵗʰ station to its next (i + 1)ᵗʰ station. You begin the journey with
 an empty tank at one of the gas stations.
 
 Given two integer arrays gas and cost, return the starting gas station's index
 if you can travel around the circuit once in the clockwise direction, otherwise
 return -1. If there exists a solution, it is guaranteed to be unique.
 
 
 Example 1:
 
 
 Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 Output: 3
 Explanation:
 Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 =
 4
 Travel to station 4. Your tank = 4 - 1 + 5 = 8
 Travel to station 0. Your tank = 8 - 2 + 1 = 7
 Travel to station 1. Your tank = 7 - 3 + 2 = 6
 Travel to station 2. Your tank = 6 - 4 + 3 = 5
 Travel to station 3. The cost is 5. Your gas is just enough to travel back to
 station 3.
 Therefore, return 3 as the starting index.
 
 
 Example 2:
 
 
 Input: gas = [2,3,4], cost = [3,4,3]
 Output: -1
 Explanation:
 You can't start at station 0 or 1, as there is not enough gas to travel to the
 next station.
 Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 Travel to station 0. Your tank = 4 - 3 + 2 = 3
 Travel to station 1. Your tank = 3 - 3 + 3 = 3
 You cannot travel back to station 2, as it requires 4 unit of gas but you only
 have 3.
 Therefore, you can't travel around the circuit once no matter where you start.
 
 
 
 Constraints:
 
 
 n == gas.length == cost.length
 1 <= n <= 10⁵
 0 <= gas[i], cost[i] <= 10⁴
 The input is generated such that the answer is unique.
 
 
 Related Topics Array Greedy 👍 13225 👎 1358
 
 */

package leetcode.editor.en;

// 2020-07-25 19:11:06
// Jesse Yang
public class Leetcode0134GasStation{
    // Java: gas-station
    public static void main(String[] args) {
        Solution sol = new Leetcode0134GasStation().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // corner case
        if (gas == null || cost == null) {
            return -1;
        }
        
        // general case
        int len = gas.length;
        int left = len;
        int right = -1;
        int sum = 0; // sum from [left, right]
        while (right < left - 1) { // 出循环的时候，right = left - 1
            if (sum >= 0) {
                sum += gas[right + 1] - cost[right + 1];
                right++;
            } else {
                left--;
                sum += gas[left] - cost[left];
            }
        }
        return sum < 0 ? -1 : left % len;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 2 pointers, T(n) = O(n), S(n) = O(n)
// Solution 1_1:双指针，左闭右开
// 执行耗时:0 ms,击败了100.00% 的Java用户, 内存消耗:38.9 MB,击败了85.59% 的Java用户
class Solution1_1 {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // corner case
        if (gas == null || cost == null) {
            return -1;
        }
        
        // general case
        int len = gas.length;
        int left = len;
        int right = 0;
        int sum = 0; // sum from [left, right)
        while (right < left) { // 出循环的时候，right = left
            if (sum >= 0) {
                sum += gas[right] - cost[right];
                right++;
            } else {
                left--;
                sum += gas[left] - cost[left];
            }
        }
        return sum < 0 ? -1 : left % len;
    }
    
}

// Solution 1_2:双指针，左闭右闭
// 执行耗时:0 ms,击败了100.00% 的Java用户, 内存消耗:39.1 MB,击败了61.90% 的Java用户
class Solution1_2 {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // corner case
        if (gas == null || cost == null) {
            return -1;
        }
        
        // general case
        int len = gas.length;
        int left = len;
        int right = -1;
        int sum = 0; // sum from [left, right]
        while (right < left - 1) { // 出循环的时候，right = left - 1
            if (sum >= 0) {
                sum += gas[right + 1] - cost[right + 1];
                right++;
            } else {
                left--;
                sum += gas[left] - cost[left];
            }
        }
        return sum < 0 ? -1 : left % len;
    }
    
}
}
