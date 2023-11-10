//In the universe Earth C-137, Rick discovered a special form of magnetic force 
//between two balls if they are put in his new invented basket. Rick has n empty 
//baskets, the iᵗʰ basket is at position[i], Morty has m balls and needs to 
//distribute the balls into the baskets such that the minimum magnetic force between any 
//two balls is maximum. 
//
// Rick stated that magnetic force between two different balls at positions x 
//and y is |x - y|. 
//
// Given the integer array position and the integer m. Return the required 
//force. 
//
// 
// Example 1: 
// 
// 
//Input: position = [1,2,3,4,7], m = 3
//Output: 3
//Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the 
//magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We 
//cannot achieve a larger minimum magnetic force than 3.
// 
//
// Example 2: 
//
// 
//Input: position = [5,4,3,2,1,1000000000], m = 2
//Output: 999999999
//Explanation: We can use baskets 1 and 1000000000.
// 
//
// 
// Constraints: 
//
// 
// n == position.length 
// 2 <= n <= 10⁵ 
// 1 <= position[i] <= 10⁹ 
// All integers in position are distinct. 
// 2 <= m <= position.length 
// 
//
// Related Topics Array Binary Search Sorting 👍 2018 👎 108

package leetcode.editor.en;

import java.util.Arrays;

// 2023-11-09 22:32:08
// Jesse Yang
public class Leetcode1552MagneticForceBetweenTwoBalls{
    // Java: magnetic-force-between-two-balls
    public static void main(String[] args) {
        Solution sol = new Leetcode1552MagneticForceBetweenTwoBalls().new Solution();
        // TO TEST
        int[] position = {1,2,3,4,7};
        int m = 3;
        int res = sol.maxDistance(position, m);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
binary search

the max distance of min distance between any balls must be
>= (right basket - left basket) / (No balls - 1)
assuming targeting distance is dis
We do the binary search for the dis from [0, (right basket - left basket) / (No balls - 1)]

number of basket is n
number of the balls is m
S(n, m) = O(n * log(n))
T(n, m) = O(n * log(n))
*/
class Solution {
    public int maxDistance(int[] position, int m) {
        // corner case
        if (position == null) {
            return -1;
        }
        
        Arrays.sort(position);
        int n = position.length;
        int left = 0; // min boundary for the dis
        int right = (position[n - 1] - position[0]) / (m - 1); // max boundary for the dis
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid, m, position)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
    
    // greedy algorithm
    private boolean check(int dis, int m, int[] position) {
        int prev = position[0];
        int n = position.length;
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (position[i] - prev >= dis) {
                prev = position[i];
                count++;
            }
            if (count >= m) {
                return true;
            }
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}