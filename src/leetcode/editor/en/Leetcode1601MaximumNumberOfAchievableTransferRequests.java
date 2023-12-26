//We have n buildings numbered from 0 to n - 1. Each building has a number of 
//employees. It's transfer season, and some employees want to change the building 
//they reside in. 
//
// You are given an array requests where requests[i] = [fromi, toi] represents 
//an employee's request to transfer from building fromi to building toi. 
//
// All buildings are full, so a list of requests is achievable only if for each 
//building, the net change in employee transfers is zero. This means the number 
//of employees leaving is equal to the number of employees moving in. For example 
//if n = 3 and two employees are leaving building 0, one is leaving building 1, and 
//one is leaving building 2, there should be two employees moving to building 0, 
//one employee moving to building 1, and one employee moving to building 2. 
//
// Return the maximum number of achievable requests. 
//
// 
// Example 1: 
// 
// 
//Input: n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
//Output: 5
//Explantion: Let's see the requests:
//From building 0 we have employees x and y and both want to move to building 1.
//
//From building 1 we have employees a and b and they want to move to buildings 2
// and 0 respectively.
//From building 2 we have employee z and they want to move to building 0.
//From building 3 we have employee c and they want to move to building 4.
//From building 4 we don't have any requests.
//We can achieve the requests of users x and b by swapping their places.
//We can achieve the requests of users y, a and z by swapping the places in the 
//3 buildings.
// 
//
// Example 2: 
// 
// 
//Input: n = 3, requests = [[0,0],[1,2],[2,1]]
//Output: 3
//Explantion: Let's see the requests:
//From building 0 we have employee x and they want to stay in the same building 
//0.
//From building 1 we have employee y and they want to move to building 2.
//From building 2 we have employee z and they want to move to building 1.
//We can achieve all the requests. 
//
// Example 3: 
//
// 
//Input: n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 20 
// 1 <= requests.length <= 16 
// requests[i].length == 2 
// 0 <= fromi, toi < n 
// 
//
// Related Topics Array Backtracking Bit Manipulation Enumeration 👍 1369 👎 71

package leetcode.editor.en;

// 2023-12-25 22:07:43
// Jesse Yang
public class Leetcode1601MaximumNumberOfAchievableTransferRequests{
    // Java: maximum-number-of-achievable-transfer-requests
    public static void main(String[] args) {
        Solution sol = new Leetcode1601MaximumNumberOfAchievableTransferRequests().new Solution();
        // TO TEST
        int n = 3;
        int[][] request = {
                {0, 0},
                {1, 2},
                // {2, 2},
                // {0, 2},
                {2, 1},
                // {1, 1},
                // {1, 2}
        };
        
        System.out.println(sol.maximumRequests(n, request));
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 1: DFS 第1类搜索树
/*
 [a,b,c]
                          {}
 level0           {a}     {b}     {c}     1st can choose either a b c    i = 0, 1, 2
 level1       {a,b}{a,c}  {b,c}           2st can choose what's left      i = 1, 2
 level2   {a,b,c}                         i = 2
 null
*/
class Solution {
    public int maximumRequests(int n, int[][] requests) {
        int[] buildings = new int[n];
        int[] res = new int[1];
        for (int i = 0; i < requests.length; i++) {
            int[] request = requests[i];
            int from = request[0];
            int to = request[1];
            buildings[from]--;
            buildings[to]++;
            
            dfs(i, 0, requests, buildings, res);
            
            buildings[from]++;
            buildings[to]--;
        }
        return res[0];
    }
    
    private void dfs(int idx, int level, int[][] requests, int[] buildings, int[] res) {
        int len = requests.length;
        // corner case
        if (checkBuildings(buildings)) {
            res[0] = Math.max(res[0], level + 1);
        }
        
        // general case
        for (int i = idx + 1; i < len; i++) {
            int from = requests[i][0];
            int to = requests[i][1];
            buildings[from]--;
            buildings[to]++;
            
            dfs(i, level + 1, requests, buildings, res);
            
            buildings[from]++;
            buildings[to]--;
        }
        
    }
    
    private boolean checkBuildings(int[] buildings) {
        for (int building : buildings) {
            if (building != 0) {
                return false;
            }
        }
        return true;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS 第一类搜索树 T(n) = O(2^n), S(n) = O(n)
/*
 [a,b,c]
                          {}
 level0           {a}     {b}     {c}     1st can choose either a b c    i = 0, 1, 2
 level1       {a,b}{a,c}  {b,c}           2st can choose what's left      i = 1, 2
 level2   {a,b,c}                         i = 2
 null
*/
class Solution1_1 {
    public int maximumRequests(int n, int[][] requests) {
        int[] buildings = new int[n];
        int[] res = new int[1];
        for (int i = 0; i < requests.length; i++) {
            int[] request = requests[i];
            int from = request[0];
            int to = request[1];
            buildings[from]--;
            buildings[to]++;
            
            dfs(i, 0, requests, buildings, res);
            
            buildings[from]++;
            buildings[to]--;
        }
        return res[0];
    }
    
    private void dfs(int idx, int level, int[][] requests, int[] buildings, int[] res) {
        int len = requests.length;
        // corner case
        if (checkBuildings(buildings)) {
            res[0] = Math.max(res[0], level + 1);
        }
        
        // general case
        for (int i = idx + 1; i < len; i++) {
            int from = requests[i][0];
            int to = requests[i][1];
            buildings[from]--;
            buildings[to]++;
            
            dfs(i, level + 1, requests, buildings, res);
            
            buildings[from]++;
            buildings[to]--;
        }
        
    }
    
    private boolean checkBuildings(int[] buildings) {
        for (int building : buildings) {
            if (building != 0) {
                return false;
            }
        }
        return true;
    }
    
}

// Solution 2: DFS + 状态压缩, T(n) = O(2^n), S(n) = O(n)
/*
设置一个数字，数字从0到request的长度。这个数字化成二进制，用1来表达执行这个request，0来表达不执行这个request
 */
class Solution2_1 {
    public int maximumRequests(int n, int[][] requests) {
        int len = requests.length;
        int res = 0;
        
        for (int i = 0; i < (1 << len); i++) {
            if (check(i, n, requests)) {
                res = Math.max(res, Integer.bitCount(i));
            }
        }
        return res;
        
    }
    
    private boolean check(int state, int n, int[][] requests) {
        int len = requests.length;
        int[] buildings = new int[n];
        for (int i = 0; i < len; i++) {
            if ((state >> i & 1) == 1) {
                int from = requests[i][0];
                int to = requests[i][1];
                buildings[from]--;
                buildings[to]++;
            }
        }
        for (int i = 0; i < n; i++) {
            if(buildings[i] != 0) {
                return false;
            }
        }
        return true;
    }
    
}

// Solution 2_2: DFS + 状态压缩 + Ghosper's hack 不要求掌握，面试不要写这个
// T(n) = O(2^n), S(n) = O(n)

class Solution2_2 {
    // time = O(2^m * m), space = O(n)
    public int maximumRequests(int n, int[][] requests) {
        int len = requests.length;
        for (int i = len; i >= 0; i--) {
            int state = (1 << i) - 1;
            while (state < 1 << len) {
                if (check(state, n, requests)) return i;
                
                // Ghosper's hack
                int c = state & -state;
                int r = state + c;
                state = (((state ^ r) >> 2) / c) | r;
            }
        }
        return 0;
    }
    
    private boolean check(int state, int n, int[][] r) {
        int len = r.length;
        int[] b = new int[n];
        for (int i = 0; i < len; i++) {
            if ((state >> i & 1) == 1) {
                int x = r[i][0], y = r[i][1];
                b[x]--;
                b[y]++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (b[i] != 0) return false;
        }
        return true;
    }
    
}


}