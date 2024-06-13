
//Given an array A of positive integers, call a (contiguous, not necessarily dis
//tinct) subarray of A good if the number of different integers in that subarray i
//s exactly K. 
//
// (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.) 
//
// Return the number of good subarrays of A. 
//
// 
//
// Example 1: 
//
// 
//Input: A = [1,2,1,2,3], K = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1],
// [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
// 
//
// Example 2: 
//
// 
//Input: A = [1,2,1,3,4], K = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2
//,1,3], [1,3,4].
// 
//
// 
//
// Note: 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length 
// 1 <= K <= A.length 
// Related Topics Hash Table Two Pointers Sliding Window 
// 👍 1512 👎 26

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2021-02-01 15:06:54
// Jesse Yang
public class Leetcode0992SubarraysWithKDifferentIntegers{
    // Java: subarrays-with-k-different-integers
    public static void main(String[] args) {
        Solution sol = new Leetcode0992SubarraysWithKDifferentIntegers().new Solution();
        // TO TEST
        int[] A= {1,2};
        // int[] B = Arrays.copyOfRange(A, 60, A.length);
        int K = 1;
        int res = sol.subarraysWithKDistinct(A, K);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int subarraysWithKDistinct(int[] A, int K) {
        int len = A.length;
        int res = 0;
        // corner case
        if (K == 1) {
            int consecutive = 1;
            int num = A[0];
            for (int i = 1; i < len; i++) {
                int next = A[i];
                if (next == num) {
                    consecutive++;
                } else {
                    res += (consecutive + 1) * consecutive / 2;
                    consecutive = 1;
                }
                num = next;
            }
            res += (consecutive + 1) * consecutive / 2;
            return res;
        }
        
        int end1 = 0;
        Map<Integer, Integer> window1 = new HashMap<>(); // k - 1 different integer to count
        // initialize
        // [start, end1)
        while (end1 < len && (window1.size() < K - 1 || window1.containsKey(A[end1]))) { //
            int num = A[end1];
            window1.put(num, window1.getOrDefault(num, 0) + 1);
            end1++;
        }
        if (end1 == len) {
            return 0;
        }
        // [start, end2]
        // k different integer count
        Map<Integer, Integer> window2 = new HashMap<>(window1);
        int end2 = end1;
        while (end2 < len && (window2.size() < K || window2.containsKey(A[end2]))) {
            int num = A[end2];
            window2.put(num, window2.getOrDefault(num, 0) + 1);
            end2++;
        }
        int start = 0;
        for (; start < len; start++) {
            if (start == 0) {
                res += end2 - end1;
                continue;
            }
            int num = A[start - 1];
            window1.put(num, window1.get(num) - 1);
            if (window1.get(num) == 0) {
                window1.remove(num);
            }
            window2.put(num, window2.get(num) - 1);
            if (window2.get(num) == 0) {
                window2.remove(num);
            }
            while (end1 < len && (window1.size() < K - 1 || window1.containsKey(A[end1]))) {
                int next = A[end1];
                window1.put(next, window1.getOrDefault(next, 0) + 1);
                end1++;
            }
            while (end2 < len && (window2.size() < K || window2.containsKey(A[end2]))) {
                int next = A[end2];
                window2.put(next, window2.getOrDefault(next, 0) + 1);
                end2++;
            }
            if (window1.size() == K - 1 && window2.size() == K) {
                res += end2 - end1;
            } else if (window1.size() == K - 1 && window2.size() == K - 1) {
                return res;
            }
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 我自己的code
// sliding window, T(n) = O(n), S(n) = O(n)
// 54 ms,击败了19.17% 的Java用户, 41.5 MB,击败了94.95% 的Java用户
/*
题目要求出，所有有k个不同元素的区间的个数
当一个区间的起点固定的时候，它的终点可能是不固定的，终点可能是在某个范围区间内都可行。
因此，对于某个起点，我们要找到他所有终点的取值范围。
它的取值范围就是从当前七点开始，有k - 1个不同元素的最大区间的终点， 到有k个不同元素的最大区间的终点
    for example:
        比如说1，2，1，2，3
        k = 2
        当起点是start = 0的时候，end可以是1，2，3都可行
所以题目就变成了3个指针的问题
第1个指针是start
第2个指针是end1, [start, end1]中有k - 1个不同的元素
第3个指针是end2，[start, end2]中有k个不同的元素
以start为起点，有k个不同元素的区间个数是end2 - end1个
每个区间用HashMap统计
 */
class Solution1 {
    
    public int subarraysWithKDistinct(int[] A, int K) {
        int len = A.length;
        int res = 0;
        // corner case
        if (K == 1) {
            int consecutive = 1;
            int num = A[0];
            for (int i = 1; i < len; i++) {
                int next = A[i];
                if (next == num) {
                    consecutive++;
                } else {
                    res += (consecutive + 1) * consecutive / 2;
                    consecutive = 1;
                }
                num = next;
            }
            res += (consecutive + 1) * consecutive / 2;
            return res;
        }
        
        int end1 = 0;
        Map<Integer, Integer> window1 = new HashMap<>(); // k - 1 different integer to count
        // initialize
        // [start, end1)
        while (end1 < len && (window1.size() < K - 1 || window1.containsKey(A[end1]))) { //
            int num = A[end1];
            window1.put(num, window1.getOrDefault(num, 0) + 1);
            end1++;
        }
        if (end1 == len) {
            return 0;
        }
        // [start, end2]
        // k different integer count
        Map<Integer, Integer> window2 = new HashMap<>(window1);
        int end2 = end1;
        while (end2 < len && (window2.size() < K || window2.containsKey(A[end2]))) {
            int num = A[end2];
            window2.put(num, window2.getOrDefault(num, 0) + 1);
            end2++;
        }
        int start = 0;
        for (; start < len; start++) {
            if (start == 0) {
                res += end2 - end1;
                continue;
            }
            int num = A[start - 1];
            window1.put(num, window1.get(num) - 1);
            if (window1.get(num) == 0) {
                window1.remove(num);
            }
            window2.put(num, window2.get(num) - 1);
            if (window2.get(num) == 0) {
                window2.remove(num);
            }
            while (end1 < len && (window1.size() < K - 1 || window1.containsKey(A[end1]))) {
                int next = A[end1];
                window1.put(next, window1.getOrDefault(next, 0) + 1);
                end1++;
            }
            while (end2 < len && (window2.size() < K || window2.containsKey(A[end2]))) {
                int next = A[end2];
                window2.put(next, window2.getOrDefault(next, 0) + 1);
                end2++;
            }
            if (window1.size() == K - 1 && window2.size() == K) {
                res += end2 - end1;
            } else if (window1.size() == K - 1 && window2.size() == K - 1) {
                return res;
            }
        }
        return res;
    }
    
}

// Solution 2: sliding window, T(n) = O(n), S(n) = O(n)
// 50 ms,击败了32.29% 的Java用户, 44.2 MB,击败了37.25% 的Java用户
/*
题目要求出，所有有k个不同元素的区间的个数
当一个区间的起点固定的时候，它的终点可能是不固定的，终点可能是在某个范围区间内都可行。
因此，对于某个终点点，我们要找到他所有起点的取值范围。
它的取值范围就是从当前七点开始，有k - 1个不同元素的最大区间的终点， 到有k个不同元素的最大区间的终点
    for example:
        比如说1，2，1，2，3
        k = 2
        当起点是right = 3的时候，left可以是0, 1, 2都可行
所以题目就变成了3个指针的问题
第1个指针是right
第2个指针是left1, [left1, right]中有k个不同的元素
第3个指针是left2, [left2, right]中有k - 1个不同的元素
以right为终点，有k个不同元素的区间个数是left2 - left1个
每个区间设置成一个window class，用HashMap统计
固定右端点的好处是，避免了K = 1时候的corner case的额外计算，而且在while循环添加值删除值的时候更加方便
 */
class Solution2 {
    
    public int subarraysWithKDistinct(int[] A, int K) {
        Window window1 = new Window(); // largest window ends with current location with size K - 1
        Window window2 = new Window(); // largest window ends with current location with size K
        int res = 0;
        int left1 = 0;
        int left2 = 0;
        
        for (int val : A) {
            window1.add(val);
            window2.add(val);
            
            while (window1.different() > K) {
                window1.remove(A[left1++]);
            }
            while (window2.different() >= K) {
                window2.remove(A[left2++]);
            }
            res += left2 - left1;
        }
        
        return res;
    }
    
    class Window {
        
        Map<Integer, Integer> count;
        int nonzero;
        
        Window() {
            count = new HashMap<>();
            nonzero = 0;
        }
        
        void add(int x) {
            count.put(x, count.getOrDefault(x, 0) + 1);
            if (count.get(x) == 1) {
                nonzero++;
            }
        }
        
        void remove(int x) {
            count.put(x, count.get(x) - 1);
            if (count.get(x) == 0) {
                nonzero--;
            }
        }
        
        int different() {
            return nonzero;
        }
        
    }
    
}
}