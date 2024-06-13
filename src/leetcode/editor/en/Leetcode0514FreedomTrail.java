//In the video game Fallout 4, the quest "Road to Freedom" requires players to r
//each a metal dial called the "Freedom Trail Ring", and use the dial to spell a s
//pecific keyword in order to open the door. 
//
// Given a string ring, which represents the code engraved on the outer ring and
// another string key, which represents the keyword needs to be spelled. You need 
//to find the minimum number of steps in order to spell all the characters in the 
//keyword. 
//
// Initially, the first character of the ring is aligned at 12:00 direction. You
// need to spell all the characters in the string key one by one by rotating the r
//ing clockwise or anticlockwise to make each character of the string key aligned 
//at 12:00 direction and then by pressing the center button. 
//
// At the stage of rotating the ring to spell the key character key[i]: 
//
// 
// You can rotate the ring clockwise or anticlockwise one place, which counts as
// 1 step. The final purpose of the rotation is to align one of the string ring's 
//characters at the 12:00 direction, where this character must equal to the charac
//ter key[i]. 
// If the character key[i] has been aligned at the 12:00 direction, you need to 
//press the center button to spell, which also counts as 1 step. After the pressin
//graph, you could begin to spell the next character in the key (next stage), otherwis
//e, you've finished all the spelling. 
// 
//
// Example: 
//
//
// 
//
// 
//Input: ring = "godding", key = "gd"
//Output: 4
//Explanation:
//For the first key character 'graph', since it is already in place, we just need 1
//step to spell this character. 
//For the second key character 'd', we need to rotate the ring "godding" anticlo
//ckwise by two steps to make it become "ddinggo".
//Also, we need 1 more step for spelling.
//So the final output is 4.
// 
//
// Note: 
//
// 
// Length of both ring and key will be in range 1 to 100. 
// There are only lowercase letters in both strings and might be some duplcate c
//haracters in both strings. 
// It's guaranteed that string key could always be spelled by rotating the strin
//graph ring.
// 
// Related Topics Divide and Conquer Dynamic Programming Depth-first Search 
// 👍 465 👎 26

package leetcode.editor.en;

// 2020-11-08 17:08:18
// Jesse Yang
public class Leetcode0514FreedomTrail {
	
	// Java: freedom-trail
	public static void main(String[] args) {
		Solution sol = new Leetcode0514FreedomTrail().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
// S2: DP
// time = O(m^2 * n), space = O(m * n)
class Solution {
    
    public int findRotateSteps(String ring, String key) {
        // corner case
        if (ring == null || ring.length() == 0 || key == null || key.length() == 0) {
            return 0;
        }
        int ringLen = ring.length();
        int keyLen = key.length();
        int[][] dp = new int[keyLen][ringLen];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < keyLen; i++) {
            min = Integer.MAX_VALUE; // 注意这里要重置min，否则下面取min就无法实现count到下一个i的min
            for (int j = 0; j < ringLen; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (key.charAt(i) == ring.charAt(j)) {
                    if (i == 0) {
                        dp[i][j] = getDist(0, j, ringLen) + 1;
                    } else {
                        for (int k = 0; k < ringLen; k++) {
                            if (dp[i - 1][k] != Integer.MAX_VALUE) {
                                dp[i][j] = Math.min(dp[i][j],
                                        dp[i - 1][k] + getDist(k, j, ringLen) + 1);
                            }
                        }
                    }
                }
                min = Math.min(dp[i][j], min);
            }
        }
        return min;
    }
    
    private int getDist(int start, int end, int ringLen) {
        return Math.min((end - start + ringLen) % ringLen, (start - end + ringLen) % ringLen);
    } // 注意：只要一旦出现有环，计算距离或者index时，一定要记得与环长取余！ idx = (idx + len) % len
    
}
//leetcode submit region end(Prohibit modification and deletion)

}