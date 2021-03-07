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
import java.util.Comparator;
import java.util.PriorityQueue;

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
        int[][] res = new int[K][2];
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(this::squareDistance));
        minHeap.addAll(Arrays.asList(points));
        for (int i = 0; i < K; i++) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    private int squareDistance(int[] index) {
        return index[0] * index[0] + index[1] * index[1];
    }
}

//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: traversal + minHeap, O(N + Klog(N)), 21 ms,击败了73.83% 的Java用户
class Solution1 {

    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(this::squareDistance));
        minHeap.addAll(Arrays.asList(points));
        for (int i = 0; i < K; i++) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    private int squareDistance(int[] index) {
        return index[0] * index[0] + index[1] * index[1];
    }
}

// Solution 2:traversal + minHeap, O(Nlog(K) + Klog(K)), 28 ms,击败了60.29% 的Java用户
class Solution2 {

    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        if (points.length <= 1) {
            return res;
        }
        PriorityQueue<int[]> maxHeap =
                new PriorityQueue<>((o1, o2) -> squareDistance(o2) - squareDistance(o1));
        for (int[] point : points) {
            if (maxHeap.size() < K) {
                maxHeap.offer(point);
            } else {
                if (squareDistance(maxHeap.peek()) > squareDistance(point)) {
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

    private int squareDistance(int[] index) {
        return index[0] * index[0] + index[1] * index[1];
    }
}

}