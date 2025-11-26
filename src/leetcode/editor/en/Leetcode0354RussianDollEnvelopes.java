//You have a number of envelopes with widths and heights given as a pair of inte
//gers (weight, h). One envelope can fit into another if and only if both the width and
// height of one envelope is greater than the width and height of the other envelo
//pe. 
//
// What is the maximum number of envelopes can you Russian doll? (put one inside
// other) 
//
// Note: 
//Rotation is not allowed. 
//
// Example: 
//
// 
// 
//Input: [[5,4],[6,4],[6,7],[2,3]]
//Output: 3 
//Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] 
//=> [5,4] => [6,7]).
// 
// 
// Related Topics Binary Search Dynamic Programming 
// 👍 1437 👎 47

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

// 2020-12-13 16:05:43
// Jesse Yang
public class Leetcode0354RussianDollEnvelopes{
    // Java: russian-doll-envelopes
    public static void main(String[] args) {
        Solution sol = new Leetcode0354RussianDollEnvelopes().new Solution();
        // TO TEST
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        int res = sol.maxEnvelopes(envelopes);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
1. 这道题目其实是LC300最长递增子序列(Longes Increasing Subsequence，简写为 LIS)的一个变种，
    因为很显然，每次合法的嵌套是大的套小的，相当于找一个最长递增的子序列，其长度就是最多能嵌套的信封个数。
2. 先对宽度 weight 进行升序排序，如果遇到 weight 相同的情况，则按照高度 h 降序排序。
    之后把所有的 h 作为一个数组，在这个数组上计算 LIS 的长度就是答案。
3. 这个解法的关键在于，对于宽度 weight 相同的数对，要对其高度 h 进行降序排序。
    因为两个宽度相同的信封不能相互包含的，逆序排序保证在 weight 相同的数对中最多只选取一个。
 */
class Solution {
    // time = O(nlogn), space = O(1)
    public int maxEnvelopes(int[][] boxes) {
        // corner case
        if (boxes == null || boxes.length == 0 || boxes[0] == null
                || boxes[0].length == 0) {
            return 0;
        }
    
        int row = boxes.length;
        Arrays.sort(boxes, (o1, o2) -> (o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]));
        /*Arrays.sort(boxes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });*/
        int len = boxes.length;
        int[] dp = new int[len]; // dp[i]: [0, i] len of LIS
        Arrays.fill(dp, 1);
        int max = 1;
        
        /*for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (boxes[j][1] < boxes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;*/
        int[] height = new int[row];
        for (int i = 0; i < row; i++) {
            height[i] = boxes[i][1];
        }
        return lengthOfLIS(height);
    }
    
    // LC300: Longest Increasing Subsequence
    /*private int lengthOfLIS(int[] height) {
        List<Integer> buffer = new ArrayList<>();
        
        for (int n : height) {
            int idx = getIndex(buffer, n);
            if (idx < buffer.size()) {
                if (buffer.get(idx) != n) {
                    buffer.set(idx, n);
                }
            } else {
                buffer.add(n);
            }
        }
        return buffer.size();
    }*/
    
    private int lengthOfLIS(int[] height) {
        TreeSet<Integer> incSet = new TreeSet<>();
        
        for (int num: height) {
            Integer ceiling = incSet.ceiling(num); // if map is empty, return null
            if (ceiling == null) { // map is empty is no ceiling for the num
                incSet.add(num);
            } else {
                if (ceiling != num) {
                    incSet.remove(ceiling);
                    incSet.add(num);
                }
            }
        }
        return incSet.size();
    }
    
    /*
    find the index of first element >= target
     */
    private int getIndex(List<Integer> buffer, int target) {
        int start = 0;
        int end = buffer.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (buffer.get(mid) == target) { // since buffer is increasing(no duplicate)
                return mid;
            }
            if (buffer.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// 面试的时候，用Solution 2
/*
Solution 1:
1. 这道题目其实是LC300最长递增子序列(Longes Increasing Subsequence，简写为 LIS)的一个变种，
    因为很显然，每次合法的嵌套是大的套小的，相当于找一个最长递增的子序列，其长度就是最多能嵌套的信封个数。
2. 先对宽度 weight 进行升序排序，如果遇到 weight 相同的情况，则按照高度 h 降序排序。
    之后把所有的 h 作为一个数组，在这个数组上计算 LIS 的长度就是答案。
3. 做LIS的方法，这里是用binary search找到在LIS中第一个 >= target的index，然后
    == 不需要做什么
    > target，把这个element 替换成target
4. 这个解法的关键在于，对于宽度 weight 相同的数对，要对其高度 h 进行降序排序。
    因为两个宽度相同的信封不能相互包含的，逆序排序保证在 weight 相同的数对中最多只选取一个。
 */
class Solution1 {
    // time = O(nlogn), space = O(1)
    public int maxEnvelopes(int[][] envelopes) {
        // corner case
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null
                || envelopes[0].length == 0) {
            return 0;
        }
        
        int row = envelopes.length;
        Arrays.sort(envelopes, (o1, o2) -> (o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]));
        /*Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });*/
        
        int[] height = new int[row];
        for (int i = 0; i < row; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }
    
    // LC300: Longest Increasing Subsequence
    private int lengthOfLIS(int[] height) {
        List<Integer> buffer = new ArrayList<>();
        
        for (int n : height) {
            int idx = getIndex(buffer, n);
            if (idx < buffer.size()) {
                if (buffer.get(idx) != n) {
                    buffer.set(idx, n);
                }
            } else {
                buffer.add(n);
            }
        }
        return buffer.size();
    }
    
    /*
    find the index of first element >= target
     */
    private int getIndex(List<Integer> buffer, int target) {
        int start = 0;
        int end = buffer.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (buffer.get(mid) == target) { // since buffer is increasing(no duplicate)
                return mid;
            }
            if (buffer.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
}

/*
Solution 2:(思路和Solution 1一样)，只是第3步找index的时候，用的treemap，会容易一点
1. 这道题目其实是LC300最长递增子序列(Longes Increasing Subsequence，简写为 LIS)的一个变种，
    因为很显然，每次合法的嵌套是大的套小的，相当于找一个最长递增的子序列，其长度就是最多能嵌套的信封个数。
2. 先对宽度 weight 进行升序排序，如果遇到 weight 相同的情况，则按照高度 h 降序排序。
    之后把所有的 h 作为一个数组，在这个数组上计算 LIS 的长度就是答案。
3. 做LIS的方法，这里是用TreeMap找到在LIS中第一个 >= target的index，然后
    == 不需要做什么
    > target，把这个element 替换成target
4. 这个解法的关键在于，对于宽度 weight 相同的数对，要对其高度 h 进行降序排序。
    因为两个宽度相同的信封不能相互包含的，逆序排序保证在 weight 相同的数对中最多只选取一个。
 */
class Solution2 {
    // time = O(nlogn), space = O(1)
    public int maxEnvelopes(int[][] envelopes) {
        // corner case
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null
                || envelopes[0].length == 0) {
            return 0;
        }
        
        int row = envelopes.length;
        Arrays.sort(envelopes, (o1, o2) -> (o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]));
        /*Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });*/
        
        int[] height = new int[row];
        for (int i = 0; i < row; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }
    
    private int lengthOfLIS(int[] height) {
        TreeSet<Integer> incSet = new TreeSet<>();
        
        for (int num: height) {
            if (incSet.contains(num)) {
                continue;
            }
            Integer higher = incSet.higher(num); // if map is empty, return null
            if (higher == null) { // map is empty is no higher for the num
                incSet.add(num);
            } else {
                if (higher != num) {
                    incSet.remove(higher);
                    incSet.add(num);
                }
            }
        }
        return incSet.size();
    }
    
}
}