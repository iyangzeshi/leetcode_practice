//There are N children standing in a line. Each child is assigned a rating value
//. 
//
// You are giving candies to these children subjected to the following requireme
//nts: 
//
// 
// Each child must have at least one candy. 
// Children with a higher rating get more candies than their neighbors. 
// 
//
// What is the minimum candies you must give? 
//
// Example 1: 
//
// 
//Input: [1,0,2]
//Output: 5
//Explanation: You can allocate to the first, second and third child with 2, 1, 
//2 candies respectively.
// 
//
// Example 2: 
//
// 
//Input: [1,2,2]
//Output: 4
//Explanation: You can allocate to the first, second and third child with 1, 2, 
//1 candies respectively.
//             The third child gets 1 candy because it satisfies the above two c
//onditions.
// 
// Related Topics Greedy 
// 👍 1172 👎 175

package leetcode.editor.en;

// 2020-11-10 13:57:16
// Zeshi Yang
public class Leetcode0135Candy{
    // Java: candy
    public static void main(String[] args) {
        Solution sol = new Leetcode0135Candy().new Solution();
        // TO TEST
        int[] ratings = {1, 3, 2, 2, 1};
        int res = sol.candy(ratings);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int candy(int[] ratings) {
        
        int len = ratings.length;
        int curCandy = 1;
        int sum = 1;
        int peakIndex = 0;
        int peakValue = 1;
        for (int i = 1; i < len; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                if (ratings[i] > ratings[i - 1]) {
                    curCandy++;
                } else {
                    curCandy = 1;
                }
                sum += curCandy;
                peakIndex = i;
                peakValue = curCandy;
            } else {
                curCandy = 1;
                sum = sum + curCandy + (i - peakIndex - 1);
                if (peakValue <= i - peakIndex) {
                    sum += 1;
                }
            }
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: blute force, T(n) = O(n^2), S(n) = O(n)
/*
 每往右走一步，
    如果Ratings[i] > Ratings[i - 1]，
        curCandy += 1 (递增序列中，直接加1即可)，
        否则置1，
 然后就往左check到起点，
    如果Ratings[i] > Ratings[i + 1]并且candies[i] <= candies[i + 1],
        candies[i] = candies[i + 1] + 1
        否则不变
 */
class Solution1 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candies = new int[len];
        candies[0] = 1;
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
            for (int j = i - 1; j >= 0 ; j--) {
                if (ratings[j] > ratings[j + 1] && candies[j] <= candies[j + 1]) {
                    candies[j] = candies[j + 1] + 1;
                }
            }
        }
        int sum = 0;
        for (int candy: candies) {
            sum += candy;
        }
        return sum;
    }
}

// Solution 2: Greedy, T(n) = O(n), S(n) = O(n)
/*
 如果右边往右走到底，每走一步，如果右边比目前rating大， candy就 + 1，
 走到底之后，在原来的基础上往左边check，就check这一步往左走，左边所有的candies分配是否符合要求
 */
class Solution2 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candies = new int[len];
        candies[0] = 1;
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }
        int sum = 0;
        for (int candy: candies) {
            sum += candy;
        }
        return sum;
    }
}

// Solution 3: Greedy, Y(n) = O(n), S(n) = O(1)
/*
本题Greedy的策略是先给第一个小朋友发默认的1个糖果，然后从第二个小朋友开始分三种情况讨论：
    (1) Ratings[i] == Ratings[i - 1]，curCandy = 1 (相等时可取任意值，这里当然取最小值), 更新这个点设置为波峰
    (2) Ratings[i] >  Ratings[i - 1]， curCandy += 1 (递增序列中，直接加1即可)， 更新这个点为波峰
    (3)	Ratings[i] <  Ratings[i - 1]，这种情况最复杂，首先curCandy = 1取到最小，
        然后给前面所有递减序列中的小朋友各自都补发1个糖果(波峰位置不计入)，
            然后根据波峰是否 <= 波峰后面的那个值，来决定要不要给波峰补1
                波峰后面的那个值是i - peakIndex
 */
class Solution3 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int curCandy = 1;
        int sum = 1;
        int peakIndex = 0;
        int peakValue = 1;
        for (int i = 1; i < len; i++) {
            /*if (ratings[i] > ratings[i - 1]) {
                curCandy++;
                sum += curCandy;
                peakIndex = i;
                peakValue = curCandy;
            } else if (ratings[i] == ratings[i - 1]) {
                curCandy = 1;
                sum += curCandy;
                peakIndex = i;
                peakValue = curCandy;
            } else {
                curCandy = 1;
                if (peakValue <= i - peakIndex) {
                    sum = sum + curCandy + (i - peakIndex);
                } else {
                    sum = sum + curCandy + (i - peakIndex - 1);
                }
            }*/
            //上面代码简化之后，变成下面的，但是上面的代码更好理解
            if (ratings[i] >= ratings[i - 1]) {
                if (ratings[i] > ratings[i - 1]) {
                    curCandy++;
                } else {
                    curCandy = 1;
                }
                sum += curCandy;
                peakIndex = i;
                peakValue = curCandy;
            } else {
                curCandy = 1;
                sum = sum + curCandy + (i - peakIndex - 1);
                if (peakValue <= i - peakIndex) {
                    sum += 1;
                }
            }
        }
        return sum;
    }
}


}