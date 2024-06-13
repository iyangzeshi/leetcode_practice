//Design a data structure that supports all following operations in average O(1)
// time. 
//
// 
//
// 
// insert(val): Inserts an item val to the set if not already present. 
// remove(val): Removes an item val from the set if present. 
// getRandom: Returns a random element from the current set of elements (it's gu
//aranteed that at least one element exists when this method is called). Each elem
//ent must have the same probability of being returned. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert
//", "getRandom"]
//[[], [1], [2], [2], [], [1], [2], []]
//Output
//[null, true, false, true, 2, true, false, 2]
//
//Explanation
//RandomizedSet randomizedSet = new RandomizedSet();
//randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was insert
//ed successfully.
//randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
//randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contai
//ns [1,2].
//randomizedSet.getRandom(); // getRandom should return either 1 or 2 randomly.
//randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now cont
//ains [2].
//randomizedSet.insert(2); // 2 was already in the set, so return false.
//randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom
// always return 2.
// 
//
// 
// Constraints: 
//
// 
// -231 <= val <= 231 - 1 
// At most 105 calls will be made to insert, remove, and getRandom. 
// There will be at least one element in the data structure when getRandom is ca
//lled. 
// 
// Related Topics Array Hash Table Design 
// 👍 2712 👎 171

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 2020-09-12 17:20:45
// Jesse Yang
public class Leetcode0380InsertDeleteGetrandomO1{
    // Java: insert-delete-getrandom-o1
    public static void main(String[] args) {
        RandomizedSet sol = new Leetcode0380InsertDeleteGetrandomO1().new RandomizedSet();
        // TO TEST

        System.out.print(sol.remove(0) + " ");
        System.out.print(sol.remove(0) + " ");
        System.out.print(sol.insert(0) + " ");
        System.out.print(sol.getRandom() + " ");
        System.out.print(sol.remove(0) + " ");
        System.out.print(sol.insert(0) + " ");

    }
//leetcode submit region begin(Prohibit modification and deletion)
class RandomizedSet {

    private Random rand;
    private ArrayList<Integer> list;
    private Map<Integer, Integer> intToIndex;
    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        rand = new Random();
        list = new ArrayList<>();
        intToIndex = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified
     * element.
     */
    public boolean insert(int val) {
        if (intToIndex.containsKey(val)) {
            return false;
        }
        list.add(val);
        intToIndex.put(val, list.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!intToIndex.containsKey(val)) {
            return false;
        }
        int index = intToIndex.get(val);
        int size = list.size();
        int lastVal = list.get(size - 1);
        list.set(index, lastVal); // list.set和remove的顺序不能换,因为 corner case, val可能是最后一个值
        list.remove(size - 1);
        intToIndex.put(lastVal, index); // map.put和remove()的顺序不能换,因为 corner case, val可能是最后一个值
        intToIndex.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int randomIndex = rand.nextInt(list.size());
        return list.get(randomIndex);
    }
}

/*
  Your RandomizedSet object will be instantiated and called as such:
  RandomizedSet obj = new RandomizedSet();
  boolean param_1 = obj.insert(val);
  boolean param_2 = obj.remove(val);
  int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)

}