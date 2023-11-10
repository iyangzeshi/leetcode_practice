
/**
Design a HashSet without using any built-in hash table libraries. 

 Implement MyHashSet class: 

 
 void add(key) Inserts the value key into the HashSet. 
 bool contains(key) Returns whether the value key exists in the HashSet or not. 

 void remove(key) Removes the value key in the HashSet. If key does not exist 
in the HashSet, do nothing. 
 

 
 Example 1: 

 
Input
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove",
 "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
Output
[null, null, null, true, false, null, true, null, false]

Explanation
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // return True
myHashSet.contains(3); // return False, (not found)
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // return True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // return False, (already removed) 

 
 Constraints: 

 
 0 <= key <= 10⁶ 
 At most 10⁴ calls will be made to add, remove, and contains. 
 

 Related Topics Array Hash Table Linked List Design Hash Function 👍 2604 👎 235


*/
package leetcode.editor.en;

import java.util.LinkedList;
import java.util.List;

// 2023-03-08 22:05:13
// Jesse Yang
public class Leetcode0705DesignHashset{
    // Java: design-hashset
    public static void main(String[] args) {
	    MyHashSet1 sol = new Leetcode0705DesignHashset().new MyHashSet1();
        // TO TEST
	    for (int i = 0; i < 20; i++) {
		    sol.add(i);
		    System.out.println(sol.MAX_SIZE);
	    }
     
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MyHashSet {
	int MAX_SIZE = 1001;
	List<Integer>[] list;
	public MyHashSet() {
		list = new List[MAX_SIZE];
		for(int i=0;i< MAX_SIZE;i++){
			list[i] = new LinkedList<>();
		}
	}
	
	public void add(int key) {
		int index = key% MAX_SIZE;
		int j = find(list[index],key);
		if(j==-1) list[index].add(key);
	}
	
	public void remove(int key) {
		int index = key% MAX_SIZE;
		int j = find(list[index],key);
		if(j!=-1) list[index].remove(j);
	}
	
	public boolean contains(int key) {
		int index = key% MAX_SIZE;
		int j = find(list[index],key);
		return j!=-1;
	}
	public int find(List<Integer> list,int key){
		for(int i=0;i<list.size();i++){
			if(list.get(i)==key) return i;
		}
		return -1;
	}
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 把Key和Value用Cell包起来，实现rehashing
class MyHashSet1 {
	
	private static final double LOAD_FACTOR = 0.77d;
	private int MAX_SIZE = 256;
	private List<Cell<Integer, Integer>>[] items;
	private int size;
	
	public MyHashSet1() {
		this.items = (List<Cell<Integer, Integer>>[]) new LinkedList[MAX_SIZE];
		this.size = 0;;
	}
	
	public void add(int key) {
		Cell<Integer, Integer> newCell = new Cell<>(key, 0);
		int index = hashCodeOfKey(key);
		if (items[index] == null) {
			items[index] = new LinkedList<>();
		}
		List<Cell<Integer, Integer>> slot = items[index];
		for (Cell<Integer, Integer> cell: slot) {
			if (newCell.equals(cell)) { // there is duplicate
				slot.remove(cell);
				this.size--;
				break;
			}
		}
		this.size++;
		slot.add(newCell);
		if (this.size > MAX_SIZE * LOAD_FACTOR) {
			rehashing();
		}
	}
	
	public void remove(int key) {
		Cell<Integer, Integer> newCell = new Cell<>(key, 0);
		int index = hashCodeOfKey(key);
		if (items[index] == null) {
			return;
		}
		List<Cell<Integer, Integer>> slot = items[index];
		slot.remove(newCell);
	}
	
	public boolean contains(int key) {
		Cell<Integer, Integer> newCell = new Cell<>(key, 0);
		int index = hashCodeOfKey(key);
		List<Cell<Integer, Integer>> slot = items[index];
		if (slot == null) {
			return false;
		}
		for (Cell<Integer, Integer> cell: slot) {
			if (newCell.equals(cell)) {
				return true;
			}
		}
		return false;
	}
	
	private int hashCodeOfKey(int key) {
		return Math.abs(Integer.hashCode(key) % MAX_SIZE);
	}
	
	private void rehashing() {
		MAX_SIZE *= 2;
		
		//noinspection unchecked
		List<Cell<Integer, Integer>>[] newItems =
			(List<Cell<Integer, Integer>> []) new LinkedList[MAX_SIZE];
		for (List<Cell<Integer, Integer>> slot : items) {
			if (slot != null) {
				for (Cell<Integer, Integer> cell: slot) {
					int index = hashCodeOfKey(cell.getKey());
					if (newItems[index] == null) {
						newItems[index] = new LinkedList<>();
					}
					newItems[index].add(cell);
				}
			}
		}
		this.items = newItems;
	}
}

class Cell<K, V> {
	private final K key;
	private V val;
	
	public Cell(K key, V val) {
		this.key = key;
		this.val = val;
	}
	
	@Override
	public int hashCode() {
		return this.key == null ? 0 : this.key.hashCode();
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) { // object address is same
			return true;
		}
		
		if (o instanceof Cell<?, ?>) {
			Cell<K, V> that = (Cell<K, V>) o;
			
			if (key == null) {
				return that.key == null;
			} else {
				return key.equals(that.key);
			}
		} else {
			return false;
		}
	}
	
	public boolean keyEquals(K key) {
		if (this.key == key) {
			return true;
		} else {
			return this.key.equals(key);
		}
	}
	
	public K getKey() {
		return this.key;
	}
	
	public V getVal() {
		return this.val;
	}
	public void setKey(V val) {
		this.val = val;
	}
}

// Solution 2: 不需要用Cell把Key和Value包起来，也不需要rehashing
class MyHashSet2 {
	int MAX_SIZE = 1001;
	List<Integer>[] list;
	public MyHashSet2() {
		list = new List[MAX_SIZE];
		for(int i=0;i< MAX_SIZE;i++){
			list[i] = new LinkedList<>();
		}
	}
	
	public void add(int key) {
		int index = key% MAX_SIZE;
		int j = find(list[index],key);
		if(j==-1) list[index].add(key);
	}
	
	public void remove(int key) {
		int index = key% MAX_SIZE;
		int j = find(list[index],key);
		if(j!=-1) list[index].remove(j);
	}
	
	public boolean contains(int key) {
		int index = key% MAX_SIZE;
		int j = find(list[index],key);
		return j!=-1;
	}
	public int find(List<Integer> list,int key){
		for(int i=0;i<list.size();i++){
			if(list.get(i)==key) return i;
		}
		return -1;
	}
}

}
