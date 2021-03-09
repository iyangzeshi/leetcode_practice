//Median is the middle value in an ordered integer list. If the size of the list
// is even, there is no middle value. So the median is the mean of the two middle 
//value. 
//Examples:
//
// [2,3,4] , the median is 3 
//
// [2,3], the median is (2 + 3) / 2 = 2.5 
//
// Given an array nums, there is a sliding window of size k which is moving from
// the very left of the array to the very right. You can only see the k numbers in
// the window. Each time the sliding window moves right by one position. Your job 
//is to output the median array for each window in the original array. 
//
// For example, 
//Given nums = [1,3,-1,-3,5,3,6,7], and k = 3. 
//
// 
//Window position                Median
//---------------               -----
//[1  3  -1] -3  5  3  6  7       1
// 1 [3  -1  -3] 5  3  6  7       -1
// 1  3 [-1  -3  5] 3  6  7       -1
// 1  3  -1 [-3  5  3] 6  7       3
// 1  3  -1  -3 [5  3  6] 7       5
// 1  3  -1  -3  5 [3  6  7]      6
// 
//
// Therefore, return the median sliding window as [1,-1,-1,3,5,6]. 
//
// Note: 
//You may assume k is always valid, ie: k is always smaller than input array's s
//ize for non-empty array. 
//Answers within 10^-5 of the actual value will be accepted as correct. 
// Related Topics Sliding Window 
// 👍 1132 👎 91

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

// 2020-11-16 16:11:43
// Zeshi Yang
public class Leetcode0480SlidingWindowMedian{
    // Java: sliding-window-median
    public static void main(String[] args) {
        Solution sol = new Leetcode0480SlidingWindowMedian().new Solution();
        // TO TEST
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        double[] res = sol.medianSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] res = new double[len - k + 1];
        // 解决duplicate元素存入TreeSet的问题 → 按照index先后顺序来作为大小关系比较
        /* increasing comparator according to the number corresponding to index
         or index order if number is same */
        Comparator<Integer> comparator =
                (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        
        TreeSet<Integer> left = new TreeSet<>(comparator); // increasing treeMap
        TreeSet<Integer> right = new TreeSet<>(comparator); // decreasing treeMap
        
        for (int i = 0; i < len; i++) {
            if (i >= k) {
                if (!left.remove(i - k)) {
                    right.remove(i - k);
                }
            }
            right.add(i);
            left.add(right.pollFirst());
            
            if (i >= k - 1) {
                res[i - k + 1] = getMedian(left, right, nums, k);
            }
        }
        return res;
    }
    
    private double getMedian(TreeSet<Integer> left, TreeSet<Integer> right, int[] nums, int k) {
        while (left.size() >= right.size() + 2) {
            right.add(left.pollLast());
        }
        
        return k % 2 == 1 ? nums[left.last()] :
                ((double) nums[left.last()] + nums[right.first()]) / 2;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/** 面试的时候，用Solution 2_2 */

// Solution 1: insertion sort, T(n, k) = O((n - k + 1) * k), S(n, k) = O(k)
// 80 ms,击败了38.52% 的Java用户, 40.7 MB,击败了97.95% 的Java用户
class Solution1 {
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] res = new double[len - k + 1];
        int[] window = initialWindow(nums, k);
        for (int i = k; i <= len; i++) {
            res[i - k] = ((double) window[k / 2] + window[(k - 1) / 2]) / 2;
            if (i == len) {
                break;
            }
            remove(window, nums[i - k]);
            insert(window, nums[i]);
        }
        
        return res;
    }
    
    // T(k) = O(klog(k))
    private int[] initialWindow(int[] nums, int k) {
        int[] window = new int[k];
        System.arraycopy(nums, 0, window, 0, k);
        Arrays.sort(window);
        return window;
    }
    
    // T(k) = O(k)
    private void remove(int[] window, int num) {
        int len = window.length;
        boolean findNum = false;
        for (int i = 0; i < len; i++) {
            if (findNum) {
                window[i - 1] = window[i];
            }
            if (!findNum && window[i] == num) {
                findNum = true;
            }
        }
        window[len - 1] = Integer.MAX_VALUE;
    }
    
    // T(k) = O(k)
    private void insert(int[] window, int num) {
        int len = window.length;
        for (int i = len - 1; i >= 1; i--) {
            if (window[i - 1] <= num) {
                window[i] = num;
                return;
            } else {
                window[i] = window[i - 1];
            }
        }
        window[0] = num;
    }
    
}

// Solution 2_1: 2 TreeSet, T(n, k) = O((n - k + 1) * log(k)), S(n) = O(k)
// 26 ms,击败了83.22% 的Java用户, 42.3 MB,击败了18.21% 的Java用户
/*
left and right TreeSet is used to store the index of num
    such that is comparator is according to nums[index]
    left.size = right.size() or right.size() + 1
    left and right TreeSet's comparator are both increasing
    
    每次来一个index，都要先判断需要放在left里面，还是放在right里面
    删除window前面的那个值，直接在两个里面都删除一遍就好了
 */
class Solution2_1 {
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] res = new double[len - k + 1];
        /*
        TreeSet里面存数字num的index，让这些数字num升序排序，如果数字大小一样，按照index顺序排序
         */
        Comparator<Integer> comparator =
                (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        // left.size() == right.size() || left.size() == right.size() + 1 is always true
        TreeSet<Integer> left = new TreeSet<>(comparator);
        TreeSet<Integer> right = new TreeSet<>(comparator);
        // initialize the left and right
        initializeLeftAndRight(nums, k, left, right);
        
        for (int i = k; i < len; i++) {
            res[i - k] = getMedian(nums, left, right);
            remove(i - k, left, right);
            insert(nums, i, left, right);
        }
        res[len - k] = getMedian(nums, left, right);
        return res;
    }
    
    private void initializeLeftAndRight(int[] nums, int k, TreeSet<Integer> left,
            TreeSet<Integer> right) {
        for (int i = 0; i < k; i++) {
            insert(nums, i, left, right);
        }
    }
    
    private void insert(int[] nums, int index, TreeSet<Integer> left, TreeSet<Integer> right) {
        left.add(index);
        adjustLeftAndRight(left, right);
    }
    
    /*
    adjust left and right such that
        left.size() == right.size() || left.size() == right.size() + 1 is always true
        and num[index] for every index in left <= right
     */
    private void adjustLeftAndRight(TreeSet<Integer> left, TreeSet<Integer> right) {
        if (left.size() > right.size() + 1 || !left.isEmpty() && !right.isEmpty()
                && left.last() > right.first()) {
            right.add(left.pollLast());
        }
        if (left.size() < right.size()) {
            left.add(right.pollFirst());
        }
    }
    
    private double getMedian(int[] nums, TreeSet<Integer> left, TreeSet<Integer> right) {
        double median;
        // left.size() == right.size() || left.size() == right.size() + 1 is always true
        if (left.size() > right.size()) {
            median = nums[left.last()];
        } else {
            median = ((double) nums[left.last()] + nums[right.first()]) / 2;
        }
        return left.size() > right.size() ?  nums[left.last()] :
                ((double) nums[left.last()] + nums[right.first()]) / 2;
    }
    
    private void remove(int index, TreeSet<Integer> left, TreeSet<Integer> right) {
        if (!left.remove(index)) {
            right.remove(index);
        }
        adjustLeftAndRight(left, right);
    }
    
}

// Solution 2_2: T(n, k) = O((n - k + 1) * log(k)), S(n) = O(k)
// 23 ms,击败了91.51% 的Java用户, 41 MB,击败了85.11% 的Java用户
/*
上面代码简化版，所有的调整都放在getMedian里面了

    left and right TreeSet is used to store the index of num
    such that is comparator is according to nums[index]
    left.size = right.size() or right.size() + 1
    left and right TreeSet's comparator are both increasing
    
    每次来一个index，不用判断放在left里面还是right里面，直接先放到right里面。
    如果left和right的size不满足要求，再调整就好了
    删除window前面的那个值，直接在两个里面都检查删除一遍就好了
 */
class Solution2_2 {
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] res = new double[len - k + 1];
        // 解决duplicate元素存入TreeSet的问题 → 按照index先后顺序来作为大小关系比较
        /* increasing comparator according to the number corresponding to index
         or index order if number is same */
        /*Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (nums[a] != nums[b]) {
                    return Integer.compare(nums[a], nums[b]);
                }
                return a - b;
            }
        };*/
        // 上面的Comparator可以讲话写成如下
        Comparator<Integer> comparator =
                (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        
        TreeSet<Integer> left = new TreeSet<>(comparator); // increasing treeMap
        TreeSet<Integer> right = new TreeSet<>(comparator); // decreasing treeMap
        
        for (int i = 0; i < len; i++) {
            if (i >= k) {
                if (!left.remove(i - k)) {
                    right.remove(i - k);
                }
            }
            right.add(i);
            left.add(right.pollFirst());
            
            if (i >= k - 1) {
                res[i - k + 1] = getMedian(left, right, nums, k);
            }
        }
        return res;
    }
    
    private double getMedian(TreeSet<Integer> left, TreeSet<Integer> right, int[] nums, int k) {
        while (left.size() >= right.size() + 2) {
            right.add(left.pollLast());
        }
        
        return k % 2 == 1 ? nums[left.last()] :
                ((double) nums[left.last()] + nums[right.first()]) / 2;
    }
    
}

}