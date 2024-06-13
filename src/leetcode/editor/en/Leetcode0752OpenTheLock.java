//
//You have a lock in front of you with 4 circular wheels. Each wheel has 10 slot
//s: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freel
//y and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each
// move consists of turning one wheel one slot.
// 
//The lock initially starts at '0000', a string representing the state of the 4 
//wheels.
// 
//You are given a list of deadends dead ends, meaning if the lock displays any o
//f these codes, the wheels of the lock will stop turning and you will be unable t
//o open it.
// 
//Given a target representing the value of the wheels that will unlock the lock,
// return the minimum total number of turns required to open the lock, or -1 if it
// is impossible.
// 
//
// Example 1: 
// 
//Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//Output: 6
//Explanation:
//A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "12
//01" -> "1202" -> "0202".
//Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would
// be invalid,
//because the wheels of the lock become stuck after the display becomes the dead
// end "0102".
// 
// 
//
// Example 2: 
// 
//Input: deadends = ["8888"], target = "0009"
//Output: 1
//Explanation:
//We can turn the last wheel in reverse to move from "0000" -> "0009".
// 
// 
//
// Example 3: 
// 
//Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], t
//arget = "8888"
//Output: -1
//Explanation:
//We can't reach the target without getting stuck.
// 
// 
//
// Example 4: 
// 
//Input: deadends = ["0000"], target = "8888"
//Output: -1
// 
// 
//
// Note: 
// 
// The length of deadends will be in the range [1, 500]. 
// target will not be in the list deadends. 
// Every string in deadends and the string target will be a string of 4 digits f
//rom the 10,000 possibilities '0000' to '9999'. 
// 
// Related Topics Breadth-first Search

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
  *@ClassName: Leetcode752OpenTheLock
  *@Description:
  *@Author: Jesse Yang
  *@Date: 2020/06/22 周一 18:17
  */
public class Leetcode0752OpenTheLock {
    // Java: open-the-lock
    public static void main(String[] args) {
        Solution sol = new Leetcode0752OpenTheLock().new Solution();
        // TO TEST
        String[] deadends = {"0000"};
        String target = "8888";
        int result = sol.openLock(deadends, target);
        System.out.println(result);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    private final int[] DIRECTIONS = new int[]{-1, 1};
    
    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        // corner case
        if (deadSet.contains("0000")) {
            return -1;
        }
        Queue<char[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        queue.offer("0000".toCharArray());
        int minLen = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                char[] cur = queue.poll();
                List<char[]> nexts = convert(cur, deadSet, visited);
                for(char[] next: nexts) {
                    String nextString = String.valueOf(next);
                    if (nextString.equals(target)) {
                        return minLen + 1;
                    }
                    queue.offer(next);
                    visited.add(nextString);
                }
            }
            minLen++;
        }
        return -1;
    }
    
    private List<char[]> convert(char[] cur, Set<String> deadSet, Set<String> visited) {
        List<char[]> nexts = new ArrayList<>();
        for (int i = 0; i < cur.length; i++) {
            int temp = cur[i] - '0';
            for (int diff: DIRECTIONS) {
                int neighbor = (temp + diff + 10) % 10;
                cur[i] = (char) (neighbor + '0');
                String neighborString = String.valueOf(cur);
                if(!deadSet.contains(neighborString) && !visited.contains(neighborString)) {
                    nexts.add(cur.clone());
                    visited.add(neighborString);
                }
            }
            cur[i] = (char) (temp + '0');
        }
        return nexts;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}