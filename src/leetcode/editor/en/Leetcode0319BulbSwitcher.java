/**
There are n bulbs that are initially off. You first turn on all the bulbs, then 
you turn off every second bulb. 

 On the third round, you toggle every third bulb (turning on if it's off or 
turning off if it's on). For the iᵗʰ round, you toggle every i bulb. For the nᵗʰ 
round, you only toggle the last bulb. 

 Return the number of bulbs that are on after n rounds. 

 
 Example 1: 
 
 
Input: n = 3
Output: 1
Explanation: At first, the three bulbs are [off, off, off].
After the first round, the three bulbs are [on, on, on].
After the second round, the three bulbs are [on, off, on].
After the third round, the three bulbs are [on, off, off]. 
So you should return 1 because there is only one bulb is on. 

 Example 2: 

 
Input: n = 0
Output: 0
 

 Example 3: 

 
Input: n = 1
Output: 1
 

 
 Constraints: 

 
 0 <= n <= 10⁹ 
 

 👍 2936 👎 3228

*/
package leetcode.editor.en;

// 2026-02-17 21:35:01
// Jesse Yang
public class Leetcode0319BulbSwitcher{
    // Java: bulb-switcher
    public static void main(String[] args) {
        Solution sol = new Leetcode0319BulbSwitcher().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
灯泡 i 会在：
第 1 轮
第 2 轮（如果 i % 2 == 0）
第 3 轮（如果 i % 3 == 0）
…
👉 本质上是：
灯泡 i 会被操作 = i 的因子个数
比如：
6 的因子：1,2,3,6 → 被操作 4 次
9 的因子：1,3,9 → 被操作 3 次
🎯 关键数学点
一个数：
如果因子个数是 偶数 → 最后是关
如果因子个数是 奇数 → 最后是开

所以最终等于是这个1-n中有多少个数字它的因子数是奇数（完全平方数）
 */
class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
