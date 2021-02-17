//Count the number of prime numbers less than a non-negative number, n. 
//
// Example: 
//
// 
//Input: 10
//Output: 4
//Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
// 
// Related Topics Hash Table Math 
// 👍 2245 👎 644

package leetcode.editor.en;

import java.util.*;
// 2020-09-10 19:24:26
// Zeshi Yang
public class Leetcode0204CountPrimes{
    // Java: count-primes
    public static void main(String[] args) {
        // TO TEST
        int n = 10000000;
        testSolution1(n);
        testSolution2(n);
        testSolution3(n);
    }
    
    private static void testSolution1(int n) {
        Solution1 sol = new Leetcode0204CountPrimes().new Solution1();
        System.out.print("test normal way" + ": ");
        long startTime = System.currentTimeMillis();   //获取开始时间
        int res = sol.countPrimes(n);  //测试的代码段
        printOutRes(startTime, res);
    }
    
    private static void testSolution2(int n) {
        Solution2 sol = new Leetcode0204CountPrimes().new Solution2();
        System.out.print("test the sieve of Eratosthenes" + ": ");
        long startTime = System.currentTimeMillis();   //获取开始时间
        int res = sol.countPrimes(n);  //测试的代码段
        printOutRes(startTime, res);
    }
    
    private static void testSolution3(int n) {
        Solution3 sol = new Leetcode0204CountPrimes().new Solution3();
        System.out.print("test the sieve of Euler" + ": ");
        long startTime = System.currentTimeMillis();   //获取开始时间
        int res = sol.countPrimes(n);  //测试的代码段
        printOutRes(startTime, res);
    }
    
    private static void printOutRes(long startTime, int res) {
        System.out.println(res);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("time:" + (endTime - startTime) + "ms");
        System.out.println();
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        List<Integer> primeList = new ArrayList<>();
        primeList.add(2);
        int count = 1;
        for (int num = 3; num < n; num++) {
            boolean isPrime = true; // whether num is prime
            int sqrt = (int) Math.sqrt(num);
            for (Integer prime : primeList) {
                if (prime > sqrt) {
                    break;
                }
                if (num % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
                primeList.add(num);
            }
        }
        return count;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/* 一般情况下，用Solution 1就可以了，最优解是Solution 3, The sieve of Euler欧拉素数筛 */

// Solution 1: normal way, T(n) = O(n * sqrt(n)), S(n) = O(1)
// 554 ms,击败了7.35% 的Java用户, 35.7 MB,击败了94.00% 的Java用户
class Solution1 {
    
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isPrime(int i) {
        int sqrt = (int) Math.sqrt(i);
        for (int j = 2; j <= sqrt; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
    
}

// Solution 2: The sieve of Eratosthenes
// 10 ms,击败了97.00% 的Java用户, 37.4 MB,击败了74.17% 的Java用户
class Solution2 {
    
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        // 默认所有的元素值都会设置为false，boolean初始值为false
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;
        for (int i = 2; i * i < n; i++) {
            if (!notPrime[i]) {
                // 如果i是一个质数， 将i的倍数设置为非质数,
                //j += i相当于i的3倍，4倍……
                for (int j = 2 * i; j < n; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        // 统计质数的个数
        int result = 0;
        for (boolean notPri : notPrime) {
            if (!notPri) {
                result++;
            }
        }
        return result;
    }
    
}

// Solution 3: The sieve of Euler
// 362 ms,击败了10.98% 的Java用户, 44.8 MB,击败了5.27% 的Java用户
class Solution3 {
    
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        List<Integer> primeList = new ArrayList<>();
        primeList.add(2);
        int count = 1;
        for (int num = 3; num < n; num++) {
            boolean isPrime = true; // whether num is prime
            int sqrt = (int) Math.sqrt(num);
            for (int prime : primeList) {
                if (prime > sqrt) {
                    break;
                }
                if (num % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
                primeList.add(num);
            }
        }
        return count;
    }
    
}
}