//The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibon
//acci sequence, such that each number is the sum of the two preceding ones, start
//ing from 0 and 1. That is, 
//
// 
//F(0) = 0,   F(1) = 1
//F(size) = F(size - 1) + F(size - 2), for size > 1.
// 
//
// Given size, calculate F(size).
//
// 
//
// Example 1: 
//
// 
//Input: 2
//Output: 1
//Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
// 
//
// Example 2: 
//
// 
//Input: 3
//Output: 2
//Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
// 
//
// Example 3: 
//
// 
//Input: 4
//Output: 3
//Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
// 
//
// 
//
// Note: 
//
// 0 ≤ size ≤ 30.
// Related Topics Array 
// 👍 658 👎 193

package leetcode.editor.en;

// 2020-07-26 12:37:26
// Jesse Yang
public class Leetcode0509FibonacciNumber{
    // Java: fibonacci-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0509FibonacciNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }

        int current = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: Recursion
// T(n) = O(2^n), S(n) = O(n)
class Solution1 {
    
    // Time complexity : O(2^n)
    //Space complexity : O(n)
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
    
}

// Solution 2: DP: Bottom-Up Solution using Memoization
// T(n) = O(n), S(n) = O(n)
class Solution2 {
    
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return memoize(n);
    }
    
    public int memoize(int N) {
        int[] cache = new int[N + 1];
        cache[1] = 1;
        
        for (int i = 2; i <= N; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[N];
    }
    
}

// Solution 3: DFS, Top-Down Solution using Memoization
// T(n) = O(n), S(n) = O(n)
class Solution3 {
    
    private Integer[] cache = new Integer[31];
    
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        cache[0] = 0;
        cache[1] = 1;
        return memoize(n);
    }
    
    public int memoize(int N) {
        if (cache[N] != null) {
            return cache[N];
        }
        cache[N] = memoize(N - 1) + memoize(N - 2);
        return memoize(N);
    }
    
}

// Solution 4: DP, Iterative Top-Down Solution, rooling base
// T(n) = O(n), S(n) = O(1)
class Solution4 {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        if (n == 2) {
            return 1;
        }

        int current = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
}
// DPS: 每次计算n/2项的结果，T(n) = O(log(n)), S(n) = O(log(n))
class Solution5 {
    int fib(int N) {
        if (N <= 1) {
            return N;
        }
        int[][] A = new int[][]{{1, 1}, {1, 0}};
        matrixPower(A, N - 1);
        
        return A[0][0];
    }
    
    void matrixPower(int[][] A, int N) {
        if (N <= 1) {
            return;
        }
        matrixPower(A, N / 2);
        multiply(A, A);
        
        int[][] B = new int[][]{{1, 1}, {1, 0}};
        if (N % 2 != 0) {
            multiply(A, B);
        }
    }
    
    void multiply(int[][] A, int[][] B) {
        int x = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        int y = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        int z = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        int w = A[1][0] * B[0][1] + A[1][1] * B[1][1];
        
        A[0][0] = x;
        A[0][1] = y;
        A[1][0] = z;
        A[1][1] = w;
    }
}

// Solution 6: Math
// T(n) = O(1), S(n) = O(1)
class Solution6 {
    
    public int fib(int n) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(Math.pow(goldenRatio, n) / Math.sqrt(5));
    }
    
}
}