/**
Given an array of integers citations where citations[i] is the number of 
citations a researcher received for their iᵗʰ paper, return the researcher's h-index. 

 According to the definition of h-index on Wikipedia: The h-index is defined as 
the maximum value of h such that the given researcher has published at least h 
papers that have each been cited at least h times. 

 
 Example 1: 

 
Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of 
them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the 
remaining two with no more than 3 citations each, their h-index is 3.
 

 Example 2: 

 
Input: citations = [1,3,1]
Output: 1
 

 
 Constraints: 

 
 n == citations.length 
 1 <= n <= 5000 
 0 <= citations[i] <= 1000 
 

 👍 1865 👎 892

*/
package leetcode.editor.en;

// 2026-02-11 16:52:05
// Jesse Yang
public class Leetcode0274HIndex{
    // Java: h-index
    public static void main(String[] args) {
        Solution sol = new Leetcode0274HIndex().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
Any citation larger than n can be replaced by n and the h-index will not change after the replacement
H 指数的定义是：有 h 篇论文分别被引用了至少 h 次。
关键发现： 如果一篇论文的引用次数超过了总论文数 n，那么它对 H 指数的贡献与引用次数恰好等于 n 是一样的。
策略： 我们不需要对引用次数进行精确排序，只需要统计每个引用次数出现的频率。

T(n) = O(n), S(n) = O(n)
 */
class Solution {
    
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] papers = new int[len + 1];
        // counting papers for each citation number
        for (int citation : citations) {
            papers[Math.min(len, citation)]++;
        }
        // finding the h-index
        int k = len;
        for (int s = papers[len]; s < k; s += papers[k]) {
            k--;
        }
        return k;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
