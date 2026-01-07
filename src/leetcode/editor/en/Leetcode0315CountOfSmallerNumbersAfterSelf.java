
//You are given an integer array nums and you have to return a new counts array.
// The counts array has the property where counts[i] is the number of smaller elem
//ents to the right of nums[i]. 
//
// 
// Example 1: 
//
// 
//Input: nums = [5,2,6,1]
//Output: [2,1,1,0]
//Explanation:
//To the right of 5 there are 2 smaller elements (2 and 1).
//To the right of 2 there is only 1 smaller element (1).
//To the right of 6 there is 1 smaller element (1).
//To the right of 1 there is 0 smaller element.
// 
//
// Example 2: 
//
// 
//Input: nums = [-1]
//Output: [0]
// 
//
// Example 3: 
//
// 
//Input: nums = [-1,-1]
//Output: [0,0]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 
// Related Topics Binary Search Divide and Conquer Sort Binary Indexed Tree Segm
//ent Tree 
// 👍 3131 👎 100

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2021-02-04 17:09:51
// Jesse Yang
public class Leetcode0315CountOfSmallerNumbersAfterSelf{
    // Java: count-of-smaller-numbers-after-self
    public static void main(String[] args) {
        Solution sol = new Leetcode0315CountOfSmallerNumbersAfterSelf().new Solution();
        // TO TEST
        int[] nums = {-1};
        List<Integer> res = sol.countSmaller(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
merge sort
假设左半边已经sort好了，右半边也已经sort好了,对于在左半边的每个数字num，都找到右边有多少个数字比它小
part左半边[left, mid]的指针 i = left， 右半边[mid + 1, right]指针 j = mid + 1
两个指针都往右边走, 对每一个i，找到最大的j，使得nums[i] > nums[j],
这样[mid + 1, j]的数字都是比nums[i]要小的，

那么nums[i]对应的count += (j - mid)

.e.g
nums = [5, 2, 6, 1]
index= [0, 1, 2, 3]
res =

mergesort: [5,2]
5比2大，5对应的index 的count+1
res = [1,0,0,0]

mergesort: [6,1]
6比1大，6对应的index 的count+1
res = [1,0,1,0]

merge [2,5] 和 [1,6]
       l        r
left = 0, right = 2
nums[left] > nums[right]
res[left的index] += 1 +。 res[1]+= 1
res = [1,1,1,0]

left→一位, left = 1, right =1
nums[left] > nums[right]
res[left的index] += 1 +。 res[1]+= 1
res = [2,1,1,0]
 */
class Solution {
    
    class Pair {
        
        int val;
        int idx;
        
        Pair(int v, int i) {
            val = v;
            idx = i;
        }
        
    }
    
    Pair[] temp;
    
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        int[] counts = new int[len];
        
        Pair[] arr = new Pair[len];
        temp = new Pair[len];
        
        for (int i = 0; i < len; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        
        mergeSort(arr, 0, len - 1, counts);
        
        List<Integer> ans = new ArrayList<>();
        for (int count : counts) {
            ans.add(count);
        }
        return ans;
    }
    
    private void mergeSort(Pair[] arr, int left, int right, int[] count) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, count);
        mergeSort(arr, mid + 1, right, count);
        merge(arr, left, mid, right, count);
    }
    
    private void merge(Pair[] arr, int left, int mid, int right, int[] count) {
        int i = left;// pointer in left part
        int j = mid + 1; // pointer in right part
        int k = left; // pointer in the temp
        
        while (i <= mid && j <= right) {
            if (arr[i].val > arr[j].val) {
                // 右边元素更小，先放
                temp[k++] = arr[j++];
            } else { // arr[i].val <= arr[j].val && arr[i].val > arr[j - 1].val
                count[arr[i].idx] += (j - mid - 1);
                temp[k++] = arr[i++];
            }
        }
        // after while loop, i <= mid or j <= right
        while (i <= mid) {
            count[arr[i].idx] += (right - mid);
            temp[k++] = arr[i++];
        }
        
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
        for (int p = left; p <= right; p++) {
            arr[p] = temp[p];
        }
    }
    
}
    
//leetcode submit region end(Prohibit modification and deletion)

/*
Solution 1:merge sort
假设左半边已经sort好了，右半边也已经sort好了,对于在左半边的每个数字num，都找到右边有多少个数字比它小
part左半边[left, mid]的指针 i = left， 右半边[mid + 1, right]指针 j = mid + 1
两个指针都往右边走, 对每一个i，找到最大的j，使得nums[i] > nums[j],
这样[mid + 1, j]的数字都是比nums[i]要小的，

那么nums[i]对应的count += (j - mid)

.e.g
nums = [5, 2, 6, 1]
index= [0, 1, 2, 3]
res =

mergesort: [5,2]
5比2大，5对应的index 的count+1
res = [1,0,0,0]

mergesort: [6,1]
6比1大，6对应的index 的count+1
res = [1,0,1,0]

merge [2,5] 和 [1,6]
       l        r
left = 0, right = 2
nums[left] > nums[right]
res[left的index] += 1 +。 res[1]+= 1
res = [1,1,1,0]

left→一位, left = 1, right =1
nums[left] > nums[right]
res[left的index] += 1 +。 res[1]+= 1
res = [2,1,1,0]
 */
class Solution1 {
    
    class Pair {
        
        int val;
        int idx;
        
        Pair(int v, int i) {
            val = v;
            idx = i;
        }
        
    }
    
    Pair[] temp;
    
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        int[] counts = new int[len];
        
        Pair[] arr = new Pair[len];
        temp = new Pair[len];
        
        for (int i = 0; i < len; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        
        mergeSort(arr, 0, len - 1, counts);
        
        List<Integer> ans = new ArrayList<>();
        for (int count : counts) {
            ans.add(count);
        }
        return ans;
    }
    
    private void mergeSort(Pair[] arr, int left, int right, int[] count) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, count);
        mergeSort(arr, mid + 1, right, count);
        merge(arr, left, mid, right, count);
    }
    
    private void merge(Pair[] arr, int left, int mid, int right, int[] count) {
        int i = left;// pointer in left part
        int j = mid + 1; // pointer in right part
        int k = left; // pointer in the temp
        
        while (i <= mid && j <= right) {
            if (arr[i].val > arr[j].val) {
                // 右边元素更小，先放
                temp[k++] = arr[j++];
            } else { // arr[i].val <= arr[j].val && arr[i].val > arr[j - 1].val
                count[arr[i].idx] += (j - mid - 1);
                temp[k++] = arr[i++];
            }
        }
        // after while loop, i <= mid or j <= right
        while (i <= mid) {
            count[arr[i].idx] += (right - mid);
            temp[k++] = arr[i++];
        }
        
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
        for (int p = left; p <= right; p++) {
            arr[p] = temp[p];
        }
    }
    
}
// Solution 2: binary index tree (Fenwick tree) T(n) = O(nlog(n)), S(n) = O(k)
// k is the number of different numbers in the nums
// 56 ms,击败了33.39% 的Java用户, 54.9 MB,击败了20.98% 的Java用户
/*
binary index tree
step 1: 先排序，得到数组sortedArray,把他们按照rank数字排序，分别是[1, 2, ...n]
    用HashMap<Integer, Integer> key: rank, value: count来记录每个数字是第几大rank

step 2: 把这个问题转化成update和query 前缀和的问题
    FenwickTree里面设置数组tree，tree[i]表示[i−lowbit(i)+1, i]这个区间内出现的数字的合，到当前位置出现的次数
update(index, delta)，表示让rank = index的这个数，增加他出现的次数delta次

对于nums中的每个数字，找到这个数字的rank，把这个rank的count++，然后找到0 ~ rank - 1出现次数求和
 */
class Solution2 {
    
    public class FenwickTree {
        
        private final int[] sums;
        
        public FenwickTree(int n) {
            sums = new int[n + 1];
        }
        
        /*
        return sum from array[1] to array[index], T(n) = O(lg(n))
         */
        public int query(int index) {
            int res = 0;
            for (int i = index; i > 0 ; i -= lowbit(i)) { // 边界是 i > 0
                res += sums[i];
            }
            return res;
        }
        
        /*
        add delta to array[index], T(n) = O(lg(n))
         */
        public void update(int index, int delta) {
            int len = sums.length;
            for (int i = index; i < len; i+=lowbit(i)) { // 与查询相反, 边界是 i < len
                sums[i] += delta;
            }
        }
        
        /*
        return the lowest 1, T(n) = O(1)
         */
        private int lowbit(int index) {
            return index & -index;
        }
        
    }
    
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        // write the number and its rank into hashMap
        for (int i = 0; i < sorted.length; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                ranks.put(sorted[i], ++rank);
            }
        }
        
        FenwickTree tree = new FenwickTree(ranks.size());
        // amount of number smaller than num for every num in nums
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            rank = ranks.get(num);
            int count = tree.query(rank - 1);
            res.add(count);
            tree.update(rank, 1);
        }
        
        Collections.reverse(res);
        return res;
    }
    
}

/*
Solution 3: Order statistic Tree
设置一个class Node,作为BST的node节点
这棵 BST 每个节点都记着：
“我左边一共有多少个数” left_count
当前这个value出现次数 count


最终，插入路径上累加的值
就是「右侧比当前数小的数量」
设置一个method, insert(root, node)
return：在以这个root开始的tree中，在已插入的数中，有多少个 < val
每当你插入一个新数：
    case 1: 向右走 → 当前节点和左子树全部计数
    case 2: 向左走 → 什么都不算
 */
class Solution3 {
    
    class Node {
        
        int val;
        int count;
        int leftCount;
        Node left;
        Node right;
        
        public Node(int val) {
            this.val = val;
            this.count = 1;
        }
        
        public int lessOrEqual() {
            return count + leftCount;
        }
        
    }
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        int n = nums.length;
        Node root = new Node(nums[n - 1]);
        ans.add(0);
        for (int i = n - 2; i >= 0; --i) {
            ans.add(insert(root, nums[i]));
        }
        Collections.reverse(ans);
        return ans;
    }
    
    /*
    返回：在以这个root开始的tree中，在已插入的数中，有多少个 < val
     */
    private int insert(Node root, int val) {
        if (root.val == val) {
            root.count++;
            return root.leftCount;
        } else if (val < root.val) {
            root.leftCount++;
            if (root.left == null) {
                root.left = new Node(val);
                return 0;
            }
            return insert(root.left, val);
        } else {
            if (root.right == null) {
                root.right = new Node(val);
                return root.lessOrEqual();
            }
            return root.lessOrEqual() + insert(root.right, val);
        }
    }
    
}
}
