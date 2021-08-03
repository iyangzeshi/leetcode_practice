//There are n cities numbered from 0 to n-1 and n-1 roads such that there is onl
//y one way to travel between two different cities (this network form a tree). Las
//t year, The ministry of transport decided to orient the roads in one direction b
//ecause they are too narrow. 
//
// Roads are represented by connections where connections[i] = [a, b] represents
// a road from city a to b. 
//
// This year, there will be a big event in the capital (city 0), and many people
// want to travel to this city. 
//
// Your task consists of reorienting some roads such that each city can visit th
//e city 0. Return the minimum number of edges changed. 
//
// It's guaranteed that each city can reach the city 0 after reorder. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
//Output: 3
//Explanation: Change the direction of edges show in red such that each node can
// reach the node 0 (capital). 
//
// Example 2: 
//
// 
//
// 
//Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
//Output: 2
//Explanation: Change the direction of edges show in red such that each node can
// reach the node 0 (capital). 
//
// Example 3: 
//
// 
//Input: n = 3, connections = [[1,0],[2,0]]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 2 <= n <= 5 * 10^4 
// connections.length == n-1 
// connections[i].length == 2 
// 0 <= connections[i][0], connections[i][1] <= n-1 
// connections[i][0] != connections[i][1] 
// 
// Related Topics Tree Depth-first Search 
// 👍 594 👎 15

package leetcode.editor.en;

// 2021-03-16 16:50:23
// Zeshi Yang
public class Leetcode1466ReorderRoutesToMakeAllPathsLeadToTheCityZero{
    // Java: reorder-routes-to-make-all-paths-lead-to-the-city-zero
    public static void main(String[] args) {
        Solution sol = new Leetcode1466ReorderRoutesToMakeAllPathsLeadToTheCityZero().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minReorder(int n, int[][] connections) {
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}