//
//LeetCode wants to give one of its best employees the option to travel among size
//cities to collect algorithm problems. But all work and no play makes Jack a dull
// boy, you could take vacations in some particular cities and weeks. Your job is 
//to schedule the traveling to maximize the number of vacation days you could take
//, but there are certain rules and restrictions you need to follow.
// 
//
// Rules and restrictions: 
// 
// You can only travel among size cities, represented by indexes from 0 to size-1. Ini
//tially, you are in the city indexed 0 on Monday. 
// The cities are connected by flights. The flights are represented as a size*size mat
//rix (not necessary symmetrical), called flights representing the airline status 
//from the city i to the city j. If there is no flight from the city i to the city
// j, flights[i][j] = 0; Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for
// all i. 
// You totally have K weeks (each week has 7 days) to travel. You can only take 
//flights at most once per day and can only take flights on each week's Monday mor
//ning. Since flight time is so short, we don't consider the impact of flight time
//. 
// For each city, you can only have restricted vacation days in different weeks,
// given an size*K matrix called days representing this relationship. For the value o
//f days[i][j], it represents the maximum days you could take vacation in the city
// i in the week j. 
// 
// 
//
// You're given the flights matrix and days matrix, and you need to output the m
//aximum vacation days you could take during K weeks. 
//
// Example 1: 
// 
//Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
//Output: 12
//Explanation: Ans = 6 + 3 + 3 = 12. 
//One of the best strategies is:
//1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day
//. (Although you start at city 0, we could also fly to and start at other cities 
//since it is Monday.) 
//2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 day
//s.
//3rd week : stay at city 2, and play 3 days and work 4 days.
// 
// 
//
// Example 2: 
// 
//Input:flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
//Output: 3
//Explanation: Ans = 1 + 1 + 1 = 3. 
//Since there is no flights enable you to move to another city, you have to stay
// at city 0 for the whole 3 weeks. For each week, you only have one day to play a
//nd six days to work. So the maximum number of vacation days is 3.
// 
// 
//
// Example 3: 
// 
//Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
//Output: 21
//Explanation: Ans = 7 + 7 + 7 = 21 
//One of the best strategies is:
//1st week : stay at city 0, and play 7 days. 
//2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
//3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
// 
// 
//
//
// Note: 
// 
// size and K are positive integers, which are in the range of [1, 100].
// In the matrix flights, all the values are integers in the range of [0, 1]. 
// In the matrix days, all the values are integers in the range [0, 7]. 
// You could stay at a city beyond the number of vacation days, but you should weight
//ork on the extra days, which won't be counted as vacation days. 
// If you fly from the city A to the city B and take the vacation on that day, t
//he deduction towards vacation days will count towards the vacation days of city 
//B in that week. 
// We don't consider the impact of flight hours towards the calculation of vacat
//ion days. 
// 
// Related Topics Dynamic Programming 
// 👍 346 👎 54

package leetcode.editor.en;

import java.util.Arrays;

// 2020-11-07 17:34:13
// Jesse Yang
public class Leetcode0568MaximumVacationDays{
    // Java: maximum-vacation-days
    public static void main(String[] args) {
        Solution sol = new Leetcode0568MaximumVacationDays().new Solution();
        // TO TEST
        int[][] flights = {{0,1,0},{0,0,0},{0,0,0}};
        int[][] days = {{0,0,7},{2,0,0},{7,7,7}};
        int res = sol.maxVacationDays(flights, days);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        // corner case
        if (flights == null || flights.length == 0 || flights[0] == null || flights[0].length == 0) {
            throw new IllegalArgumentException();
        }
        if (days == null || days.length == 0 || days[0] == null || days[0].length == 0) {
            return 0;
        }
        
        int city = flights.length;
        int week = days[0].length;
        int[] dp = new int[city];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < week; i++) {
            int[] temp = new int[city];
            Arrays.fill(temp, Integer.MIN_VALUE);
            for (int j = 0; j < city; j++) {
                for (int k = 0; k < city; k++) {
                    if (k == j || flights[k][j] == 1) {
                        temp[j] = Math.max(temp[j], dp[k] + days[j][i]);
                    }
                }
            }
            dp = temp;
        }
        int max = 0;
        for (int i : dp) {
            max = Math.max(i, max);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/*
这个题目主要有几个点容易漏掉，在算第j周的时候以第i个城市结尾的最大独家时间的时候，
分两种情况
    第j-1周就在第i个城市
    第j-1周在其他能到达i城市的城市（这个城市本身是能从起点reach到的，null就是这个城市不能从起点城市达到）
post processing的时候，在所有能到达的城市里面，选一个独家时间最长的（有些有可能是null)
 */
// Solution 1_1: DP with 2D array, T(n) = O(k * n^2), S(n) = O(nk), 48 ms, 41.3 MB
class Solution1_1 {
    public int maxVacationDays(int[][] flights, int[][] days) {
        // corner case
        if (flights == null || flights.length == 0 || flights[0] == null || flights[0].length == 0) {
            throw new IllegalArgumentException();
        }
        if (days == null || days.length == 0 || days[0] == null || days[0].length == 0) {
            return 0;
        }
        
        int cityNum = flights.length;
        int weekNum = days[0].length;
        /*
        dp[i][j] means travel in first j weeks and end at city i, the max vacation days he has
        if dp[i][j] is null, means we can not reach city i in week j
         */
        Integer[][] dp = new Integer[cityNum][weekNum];
        // initialization
        for (int i = 0; i < cityNum; i++) {
            if (i == 0 || flights[0][i] == 1) {
                dp[i][0] = days[i][0];
            }
        }
        
        // dynamic programming
        for (int j = 1; j < weekNum; j++) {
            for (int i = 0; i < cityNum; i++) {
                int curSumDays = Integer.MIN_VALUE;
                // from previous kth city to current i city
                for (int k = 0; k < cityNum; k++) {
                    // 不同城市之间需要坐飞机，呆在这个城市不需要坐飞机
                    if ((k == i || flights[k][i] == 1) && dp[k][j - 1] != null) {
                        curSumDays = Math.max(curSumDays, dp[k][j - 1] + days[i][j]);
                    }
                }
                if (curSumDays != Integer.MIN_VALUE) {
                    dp[i][j] = curSumDays;
                }
            }
        }
        
        // post processing, find max value on last week at different cities
        int maxVacationDays = 0;
        for (int i = 0; i < cityNum; i++) {
            if (dp[i][weekNum - 1] != null) {
                maxVacationDays = Math.max(maxVacationDays, dp[i][weekNum - 1]);
            }
        }
        return maxVacationDays;
    }
}

// Solution 1_2:DP with 1D array(rolling base), T(n) = O(k * n^2), S(n) = O(nk), 54 ms, 40.9 MB
class Solution1_2 {
    public int maxVacationDays(int[][] flights, int[][] days) {
        // corner case
        if (flights == null || flights.length == 0 || flights[0] == null || flights[0].length == 0) {
            throw new IllegalArgumentException();
        }
        if (days == null || days.length == 0 || days[0] == null || days[0].length == 0) {
            return 0;
        }
        
        int city = flights.length;
        int week = days[0].length;
        int[] dp = new int[city];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < week; i++) {
            int[] temp = new int[city];
            Arrays.fill(temp, Integer.MIN_VALUE);
            for (int j = 0; j < city; j++) {
                for (int k = 0; k < city; k++) {
                    if (k == j || flights[k][j] == 1) {
                        temp[j] = Math.max(temp[j], dp[k] + days[j][i]);
                    }
                }
            }
            dp = temp;
        }
        int max = 0;
        for (int i : dp) {
            max = Math.max(i, max);
        }
        return max;
    }
}
}