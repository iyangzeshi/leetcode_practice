//We have n jobs, where every job is scheduled to be done from startTime[i] to e
//ndTime[i], obtaining a profit of profit[i]. 
//
// You're given the startTime, endTime and profit arrays, return the maximum pro
//fit you can take such that there are no two jobs in the subset with overlapping 
//time range. 
//
// If you choose a job that ends at time X you will be able to start another job
// that starts at time X. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
//Output: 120
//Explanation: The subset chosen is the first and fourth job. 
//Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
// 
//
// Example 2: 
//
// 
//
// 
//Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70
//,60]
//Output: 150
//Explanation: The subset chosen is the first, fourth and fifth job. 
//Profit obtained 150 = 20 + 70 + 60.
// 
//
// Example 3: 
//
// 
//
// 
//Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
//Output: 6
// 
//
// 
// Constraints: 
//
// 
// 1 <= startTime.length == endTime.length == profit.length <= 5 * 104 
// 1 <= startTime[i] < endTime[i] <= 109 
// 1 <= profit[i] <= 104 
// 
// Related Topics Binary Search Dynamic Programming Sort 
// 👍 1128 👎 12

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

// 2021-03-20 23:45:54
// Zeshi Yang
public class Leetcode1235MaximumProfitInJobScheduling{
    // Java: maximum-profit-in-job-scheduling
    public static void main(String[] args) {
        Solution sol = new Leetcode1235MaximumProfitInJobScheduling().new Solution();
        // TO TEST
        int[] startTime = {1, 2, 3, 3};
        int[] endTime = {3, 4, 5, 6};
        int[] profit = {50, 10, 40, 70};
        int maxProfit = sol.jobScheduling(startTime, endTime, profit);
        System.out.println(maxProfit);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            if (cur > dp.lastEntry().getValue()) {
                dp.put(job[1], cur);
            }
        }
        return dp.lastEntry().getValue();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 2*/

// Solution 1: binary search + dfs pruning, T(n) = O(nlog(n), S(n) = O(n)
// 27 ms,击败了61.06% 的Java用户, 49 MB,击败了41.75% 的Java用户
/*
思想：
dfs，每次遇到一个Job都分成两种情况，一个是这个job保留，或者舍弃。
    case 1: 如果保留，找到前面第一个不和这个job重叠的job再开始搜索
    case 2: 如果舍弃，找到前面一个job开始搜索

Steps:
    step1: 设置好Job class,把全部数据放到Job数组里面,按照end排序
    step2: 对于每一个job,找到jobs数组中的最后一个而且不和当前job重叠的job,把结果返回成prev
    step3: 对于每个job分为两种情况, maxProfit[i] = max(maxProfit[i-1], profit[i] + maxProfit[pre[i]]);
 */
class Solution1 {
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        Job[] jobs = new Job[len];
        for (int i = 0; i < len; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, Comparator.comparingInt(o -> o.end));
        int[] prevNonOverlap = getPrevNonOverLap(jobs);
        Integer[] memo = new Integer[len];
        return getMaxProfit(jobs, len - 1, prevNonOverlap, memo);
    }
    
    /**
     * @param jobs given jobs
     * @return int[] prev, prev[i] means index of the last interval(ordered by end) that has no
     * overlap with jobs[i]
     */
    private int[] getPrevNonOverLap(Job[] jobs) {
        int len = jobs.length;
        int[] prev = new int[len];
        prev[0] = -1;
        for (int i = 1; i < len; i++) {
            // find the last index that <= jobs.[i].start
            int left = -1;
            int right = i - 1;
            
            // corner case
            if (jobs[right].end <= jobs[i].start) {
                prev[i] = right;
                continue;
            }
            
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (jobs[mid].end <= jobs[i].start) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            prev[i] = left;
        }
        return prev;
    }
    
    /**
     *
     * @param jobs given job array
     * @param cur index of current job
     * @param prevNonOverlap the nearest previous job that has no overlap with current job
     * @param preMaxProfit preMaxProfit[i]: max profit array that ends with i-th job (may not
     *                     include)
     * @return max profit that ends with current job (may not include)
     */
    private int getMaxProfit(Job[] jobs, int cur, int[] prevNonOverlap, Integer[] preMaxProfit) {
        // base case
        if (cur < 0) {
            return 0;
        } else if (preMaxProfit[cur] != null) {
            return preMaxProfit[cur];
        }
        
        int prev = prevNonOverlap[cur];
        int res = Math
                .max(getMaxProfit(jobs, prev, prevNonOverlap, preMaxProfit) + jobs[cur].profit,
                        getMaxProfit(jobs, cur - 1, prevNonOverlap, preMaxProfit));
        preMaxProfit[cur] = res;
        return res;
    }
    
}

class Job implements Comparable<Job> {
    
    int start;
    int end;
    int profit;
    
    public Job(int start, int end, int profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }
    
    @Override
    public int compareTo(Job other) {
        if (this.end != other.end) {
            return this.end - other.end;
        } else if (this.start != other.start) {
            return this.start - other.start;
        } else {
            return this.profit - other.profit;
        }
    }
    
}

// Solution 2: Sol1的简化版code TreeMap(由binary search简化) + DP
// T(n) = O(nlog(n), S(n) = O(n)
// 39 ms,击败了41.41% 的Java用户, 49.3 MB,击败了36.77% 的Java用户
/*

思想：
dfs，每次遇到一个Job都分成两种情况，一个是这个job保留，或者舍弃。
    case 1: 如果保留，找到前面第一个不和这个job重叠的job再开始搜索
    case 2: 如果舍弃，找到前面一个job开始搜索
    
steps:
1. Sort jobs by the end time
2. DP[i] is max profit when the end time is endTime[i]
3. Only update DP[i] if DP[startTime[i]] + profit[i] is larger than the previous DP
   (use a tree map to store mapping between end time and corresponding
    maximum profit)
4. Return the last DP
 */
class Solution2 {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        int[][] jobs = new int[len][3];
        for (int i = 0; i < len; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1])); // sort by end time
        TreeMap<Integer, Integer> dp = new TreeMap<>(); // key - endTime, value - profit
        dp.put(0, 0); // key: end time, value: max profit ends with current time(may not include)
        for (int[] job : jobs) {
            int cur = dp.floorEntry(job[0]).getValue() + job[2]; // choose current job
            if (cur > dp.lastEntry().getValue()) { // do not choose current job
                dp.put(job[1], cur);
            }
        }
        return dp.lastEntry().getValue();
    }
}
}