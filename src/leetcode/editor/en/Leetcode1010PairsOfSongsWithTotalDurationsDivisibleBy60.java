//You are given a list of songs where the iᵗʰ song has a duration of time[i] 
//seconds. 
//
// Return the number of pairs of songs for which their total duration in 
//seconds is divisible by 60. Formally, we want the number of indices i, j such that i < 
//j with (time[i] + time[j]) % 60 == 0. 
//
// 
// Example 1: 
//
// 
//Input: time = [30,20,150,100,40]
//Output: 3
//Explanation: Three pairs have a total duration divisible by 60:
//(time[0] = 30, time[2] = 150): total duration 180
//(time[1] = 20, time[3] = 100): total duration 120
//(time[1] = 20, time[4] = 40): total duration 60
// 
//
// Example 2: 
//
// 
//Input: time = [60,60,60]
//Output: 3
//Explanation: All three pairs have a total duration of 120, which is divisible 
//by 60.
// 
//
// 
// Constraints: 
//
// 
// 1 <= time.length <= 6 * 10⁴ 
// 1 <= time[i] <= 500 
// 
//
// Related Topics Array Hash Table Counting 👍 4151 👎 164

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2024-02-27 21:06:10
// Jesse Yang
public class Leetcode1010PairsOfSongsWithTotalDurationsDivisibleBy60{
    // Java: pairs-of-songs-with-total-durations-divisible-by-60
    public static void main(String[] args) {
        Solution sol = new Leetcode1010PairsOfSongsWithTotalDurationsDivisibleBy60().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n)
/*
if (time[i] + time[j]) % 60 == 0,
then time[i] % 60 + times[j] % 60 = 0 or 60
assuming hashMap to record the time[i]% 60 and its count
then traverse the every time duration, and find whether exist the (60 - times[i] % 60) % 60;
 */
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        // corner case
        
        // general case
        int base = 60;
        
        // key: remainder; value: count
        Map<Integer, Integer> reminderCount = new HashMap<>();
        int count = 0;
        for (int num : time) {
            num = num % base;
            if (reminderCount.containsKey((base - num) % base)) {
                count += reminderCount.get((base - num) % base);
            }
            reminderCount.put(num, reminderCount.getOrDefault(num, 0) + 1);
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}