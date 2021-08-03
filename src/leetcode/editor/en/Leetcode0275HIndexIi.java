//Given an array of citations sorted in ascending order (each citation is a non-
//negative integer) of a researcher, write a function to compute the researcher's 
//h-index. 
//
// According to the definition of h-index on Wikipedia: "A scientist has index h
// if h of his/her size papers have at least h citations each, and the other size − h pa
//pers have no more than h citations each." 
//
// Example: 
//
// 
//Input: citations = [0,1,3,5,6]
//Output: 3 
//Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each o
//f them had 
//             received 0, 1, 3, 5, 6 citations respectively. 
//             Since the researcher has 3 papers with at least 3 citations each 
//and the remaining 
//             two with no more than 3 citations each, her h-index is 3. 
//
// Note: 
//
// If there are several possible values for h, the maximum one is taken as the h
//-index. 
//
// Follow up: 
//
// 
// This is a follow up problem to H-Index, where citations is now guaranteed to 
//be sorted in ascending order. 
// Could you solve it in logarithmic time complexity? 
// 
// Related Topics Binary Search 
// 👍 444 👎 708

package leetcode.editor.en;

// 2020-07-25 12:35:13
// Zeshi Yang
public class Leetcode0275HIndexIi{
    // Java: h-index-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0275HIndexIi().new Solution();
        // TO TEST
        int[] citations = {0,1,3,5,6};
        int res = sol.hIndex(citations);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int hIndex(int[] citations) {
        // corner case
        if (citations == null || citations.length == 0) {
            return 0;
        }
        // general case
        int len = citations.length;
        int left = 0;
        int right = len - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return len - left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}