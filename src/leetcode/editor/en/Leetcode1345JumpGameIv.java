
//Given an array of integers arr, you are initially positioned at the first inde
//x of the array. 
//
// In one step you can jump from index i to index: 
//
// 
// i + 1 where: i + 1 < arr.length. 
// i - 1 where: i - 1 >= 0. 
// j where: arr[i] == arr[j] and i != j. 
// 
//
// Return the minimum number of steps to reach the last index of the array. 
//
// Notice that you can not jump outside of the array at any time. 
//
// 
// Example 1: 
//
// 
//Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
//Output: 3
//Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that in
//dex 9 is the last index of the array.
// 
//
// Example 2: 
//
// 
//Input: arr = [7]
//Output: 0
//Explanation: Start index is the last index. You don't need to jump.
// 
//
// Example 3: 
//
// 
//Input: arr = [7,6,9,6,9,6,9,7]
//Output: 1
//Explanation: You can jump directly from index 0 to index 7 which is last index
// of the array.
// 
//
// Example 4: 
//
// 
//Input: arr = [6,1,9]
//Output: 2
// 
//
// Example 5: 
//
// 
//Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 5 * 104 
// -108 <= arr[i] <= 108 
// 
// Related Topics Breadth-first Search 
// 👍 552 👎 41

package leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 2021-02-08 16:17:08
// Zeshi Yang
public class Leetcode1345JumpGameIv{
    // Java: jump-game-iv
    public static void main(String[] args) {
        Solution sol = new Leetcode1345JumpGameIv().new Solution();
        // TO TEST
        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        int res = sol.minJumps(arr);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// BFS, T(n) = O(n), S(n) = O(n)
// 75 ms,击败了24.45% 的Java用户, 56.3 MB,击败了43.82% 的Java用户
class Solution {
    
    public int minJumps(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return 0;
        }
    
        Map<Integer, List<Integer>> valToIdx = mapValToIdx(arr, len); // map value to index
    
        HashSet<Integer> curs = new HashSet<>(); // store layers from start
        curs.add(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        visited.add(len - 1);
        int step = 0;
        
        HashSet<Integer> other = new HashSet<>(); // store layers from end
        other.add(len - 1);
        
        // when current layer exists
        while (!curs.isEmpty()) {
            // search from the side with fewer nodes
            if (curs.size() > other.size()) {
                HashSet<Integer> tmp = curs;
                curs = other;
                other = tmp;
            }
            
            HashSet<Integer> nexts = new HashSet<>();
            
            // iterate the layer
            for (int node : curs) {
                
                // check same value
                int val = arr[node];
                if (valToIdx.containsKey(val)) {
                    for (int child : valToIdx.get(val)) {
                        if (other.contains(child)) {
                            return step + 1;
                        }
                        if (!visited.contains(child)) {
                            visited.add(child);
                            nexts.add(child);
                        }
                    }
    
                    // clear the list to prevent redundant search
                    valToIdx.remove(val);
                }
                
                // check neighbors
                if (other.contains(node + 1) || other.contains(node - 1)) {
                    return step + 1;
                }
                
                if (node + 1 < len && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    nexts.add(node + 1);
                }
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    nexts.add(node - 1);
                }
            }
            
            curs = nexts;
            step++;
        }
        
        return -1;
    }
    
    private Map<Integer, List<Integer>> mapValToIdx(int[] arr, int len) {
        Map<Integer, List<Integer>> valToIdx = new HashMap<>();
        for (int i = 0; i < len; i++) {
            valToIdx.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }
        return valToIdx;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// 75 ms,击败了24.45% 的Java用户, 56.3 MB,击败了43.82% 的Java用户
class Solution1 {
    
    public int minJumps(int[] arr) {
        // corner case
        if (arr == null) {
            return 0;
        }
        
        int len = arr.length;
        Map<Integer, List<Integer>> valToIdx = mapValToIdx(arr); // map value to index
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int index = queue.poll();
                if (index == len - 1) {
                    return step;
                }
                // check same value
                int val = arr[index];
                if (valToIdx.containsKey(val)) {
                    List<Integer> child = valToIdx.get(val);
                    for (int neighbor : child) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                    valToIdx.remove(val); // clear the list to prevent redundant search
                }
                
                // traverse right
                int right = index + 1;
                if (right < len && !visited.contains(right)) {
                    visited.add(right);
                    queue.offer(right);
                }
                
                // traverse left
                int left = index - 1;
                if (left >= 0 && !visited.contains(left)) {
                    visited.add(left);
                    queue.offer(left);
                }
                
            }
            step++;
        }
        return step;
    }
    
    private Map<Integer, List<Integer>> mapValToIdx(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int len = arr.length;
        for (int i = len - 1; i >= 0; i--) {
            int val = arr[i];
            map.computeIfAbsent(val, k -> new ArrayList<>()).add(i);
        }
        return map;
    }
    
}

// Solution 2： bidirectional BFS, T(n) = O(n), S(n) = O(n)
// 75 ms,击败了24.45% 的Java用户, 58.9 MB,击败了30.47% 的Java用户
class Solution2 {
        
        public int minJumps(int[] arr) {
            int len = arr.length;
            if (len <= 1) {
                return 0;
            }
            
            Map<Integer, List<Integer>> valToIdx = mapValToIdx(arr, len); // map value to index
            
            HashSet<Integer> curs = new HashSet<>(); // store layers from start
            curs.add(0);
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            visited.add(len - 1);
            int step = 0;
            
            HashSet<Integer> other = new HashSet<>(); // store layers from end
            other.add(len - 1);
            
            // when current layer exists
            while (!curs.isEmpty()) {
                // search from the side with fewer nodes
                if (curs.size() > other.size()) {
                    HashSet<Integer> tmp = curs;
                    curs = other;
                    other = tmp;
                }
                
                HashSet<Integer> nexts = new HashSet<>();
                
                // iterate the layer
                for (int node : curs) {
                    
                    // check same value
                    int val = arr[node];
                    if (valToIdx.containsKey(val)) {
                        for (int child : valToIdx.get(val)) {
                            if (other.contains(child)) {
                                return step + 1;
                            }
                            if (!visited.contains(child)) {
                                visited.add(child);
                                nexts.add(child);
                            }
                        }
                        
                        // clear the list to prevent redundant search
                        valToIdx.remove(val);
                    }
                    
                    // check neighbors
                    if (other.contains(node + 1) || other.contains(node - 1)) {
                        return step + 1;
                    }
                    
                    if (node + 1 < len && !visited.contains(node + 1)) {
                        visited.add(node + 1);
                        nexts.add(node + 1);
                    }
                    if (node - 1 >= 0 && !visited.contains(node - 1)) {
                        visited.add(node - 1);
                        nexts.add(node - 1);
                    }
                }
                
                curs = nexts;
                step++;
            }
            
            return -1;
        }
        
        private Map<Integer, List<Integer>> mapValToIdx(int[] arr, int len) {
            Map<Integer, List<Integer>> valToIdx = new HashMap<>();
            for (int i = 0; i < len; i++) {
                valToIdx.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
            }
            return valToIdx;
        }
        
    }
}
