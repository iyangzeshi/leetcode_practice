//Design and implement a TwoSum class. It should support the following operation
//s: add and find. 
//
// add - Add the number to an internal data structure. 
//find - Find if there exists any pair of numbers which sum is equal to the valu
//e. 
//
// Example 1: 
//
// 
//add(1); add(3); add(5);
//find(4) -> true
//find(7) -> false
// 
//
// Example 2: 
//
// 
//add(3); add(1); add(2);
//find(3) -> true
//find(6) -> false 
// Related Topics Hash Table Design 
// 👍 279 👎 226

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
// 2020-07-26 13:52:03
// Jesse Yang
public class Leetcode0170TwoSumIiiDataStructureDesign{
    // Java: two-sum-iii-data-structure-design
    public static void main(String[] args) {
        TwoSum sol = new Leetcode0170TwoSumIiiDataStructureDesign().new TwoSum();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class TwoSum {
    
    private HashMap<Integer, Integer> map;
    
    /**
     * Initialize your data structure here.
     */
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }
    
    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int complement = value - entry.getKey();
            if (complement != entry.getKey()) { // 因为有可能 num + num = value
                if (map.containsKey(complement)) {
                    return true;
                }
            } else {
                if (entry.getValue() > 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
/*
  Your TwoSum object will be instantiated and called as such:
  TwoSum obj = new TwoSum();
  obj.add(number);
  boolean param_2 = obj.find(value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}