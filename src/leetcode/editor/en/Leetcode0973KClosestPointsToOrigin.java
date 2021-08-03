//We have a list of points on the plane. Find the K closest points to the origin
// (0, 0). 
//
// (Here, the distance between two points on a plane is the Euclidean distance.)
// 
//
// You may return the answer in any order. The answer is guaranteed to be unique
// (except for the order that it is in.) 
//
// 
//
// 
// Example 1: 
//
// 
//Input: points = [[1,3],[-2,2]], K = 1
//Output: [[-2,2]]
//Explanation: 
//The distance between (1, 3) and the origin is sqrt(10).
//The distance between (-2, 2) and the origin is sqrt(8).
//Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
//We only want the closest K = 1 points from the origin, so the answer is just [
//[-2,2]].
// 
//
// 
// Example 2: 
//
// 
//Input: points = [[3,3],[5,-1],[-2,4]], K = 2
//Output: [[3,3],[-2,4]]
//(The answer [[-2,4],[3,3]] would also be accepted.)
// 
//
// 
//
// Note: 
//
// 
// 1 <= K <= points.length <= 10000 
// -10000 < points[i][0] < 10000 
// -10000 < points[i][1] < 10000 
// 
// 
// Related Topics Divide and Conquer Heap Sort 
// 👍 2087 👎 119

package leetcode.editor.en;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

// 2020-09-08 18:49:29
// Zeshi Yang
public class Leetcode0973KClosestPointsToOrigin{
    // Java: k-closest-points-to-origin
    public static void main(String[] args) {
        Solution sol = new Leetcode0973KClosestPointsToOrigin().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int[][] kClosest(int[][] points, int K) {
        // corner case
        if (points == null || points.length < K) {
            return points;
        }
        findPosPartition (K, 0, points.length - 1, points);
        return Arrays.copyOfRange(points, 0, K);
        
    }
    
    private int[] findPosPartition (int k, int start, int end, int[][] points){
        int len = points.length;
        int pivotIndex = start + (int) (Math.random() * (end - start + 1));
        int[] pivot = points[pivotIndex];
        swap(points, pivotIndex, end);
        
        /*
        每个while循环开始之前
        [start, left) < pivotValue
        (right, end - 1] > = pivotValue
         */
        int left = start; // start pointer
        int right = end - 1; // end pointer
        
        while (left <= right) {
            if (dist(points[left]) < dist(pivot)) {
                left++;
            } else if (dist(points[right]) >= dist(pivot)) {
                //maybe duplicate
                right--;
            } else {
                // array[leftI] > pivotValue && points[right] < pivotValue
                swap(points, left++, right--);
            }
        }
        swap(points, left, end);
        
        if (left == k) {
            return points[left];
        } else if (left < k) {
            return findPosPartition(k, start, left - 1, points);
        } else {
            return findPosPartition(k, left + 1, end, points);
        }
    }
    
    private void swap(int[][] array, int i, int j){
        int[] temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: just sort all the points
// T(n, k) = O(n*log(n)), S(n,k) = O(lg(n))
// 9 ms,击败了84.00% 的Java用户, 47.7 MB,击败了45.90% 的Java用户
class Solution1 {
    
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        int[] dists = new int[len];
        for (int i = 0; i < len; ++i) {
            dists[i] = dist(points[i]); // 里面的每一个值都是距离平方
        }
        
        Arrays.sort(dists); // quick sort
        int distK = dists[K - 1];
        
        int[][] ans = new int[K][2];
        int t = 0;
        for (int[] point : points) {
            if (dist(point) <= distK) {
                ans[t++] = point;
            }
        }
        return ans;
    }
    
    public int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
}

// Solution 2: traverse all the points and use heap

// Solution 2_1: traversal + minHeap
// T(n, k) = O(n*log(n)), S(n,k) = O(n)
// 25 ms,击败了53.21% 的Java用户,48 MB,击败了26.25% 的Java用户
class Solution2_1 {
    
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>((o1, o2) -> dist(o1) - dist(o2));
        minHeap.addAll(Arrays.asList(points));
        for (int i = 0; i < K; i++) {
            res[i] = minHeap.poll();
        }
        return res;
    }
    
    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
}

// Solution 2_2:traversal + maxHeap
// T(n, k) = O(n*log(k)), S(n,k) = O(k)
// 25 ms,击败了53.21% 的Java用户,47.3 MB,击败了79.48% 的Java用户
class Solution2_2 {
    
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        if (points.length <= 1) {
            return res;
        }
        PriorityQueue<int[]> maxHeap =
                new PriorityQueue<>((o1, o2) -> dist(o2) - dist(o1));
        /*PriorityQueue<int[]> maxHeap =
                new PriorityQueue<>(Comparator.comparingInt(this::squareDistance).reversed());*/
        
        for (int[] point : points) {
            if (maxHeap.size() < K) {
                maxHeap.offer(point);
            } else {
                if (dist(maxHeap.peek()) > dist(point)) {
                    maxHeap.poll();
                    maxHeap.offer(point);
                }
            }
        }
        for (int i = K - 1; i >= 0; i--) {
            res[i] = maxHeap.poll();
        }
        return res;
    }
    
    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
}

// Solution 3: quick selection
// worst: T(n, k) = O(n^2), average: T(n, k) = O(n), S(n,k) = O(1)
class Solution3 {
    
    int[][] points;
    
    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        select(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }
    
    public void select(int i, int j, int K) {
        if (i >= j) {
            return;
        }
        int k = ThreadLocalRandom.current().nextInt(i, j);
        swap(i, k);
        
        int mid = partition(i, j);
        int leftLength = mid - i + 1;
        if (K < leftLength) {
            select(i, mid - 1, K);
        } else if (K > leftLength) {
            select(mid + 1, j, K - leftLength);
        }
    }
    
    public int partition(int i, int j) {
        int oi = i;
        int pivot = dist(i);
        i++;
        
        while (true) {
            while (i < j && dist(i) < pivot) {
                i++;
            }
            while (i <= j && dist(j) > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(i, j);
        }
        swap(oi, j);
        return j;
    }
    
    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }
    
    public void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }
    
}
}