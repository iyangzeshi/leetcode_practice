
/**
Design a HashMap without using any built-in hash table libraries. 

 Implement the MyHashMap class: 

 
 MyHashMap() initializes the object with an empty map. 
 void put(int key, int value) inserts a (key, value) pair into the HashMap. If 
the key already exists in the map, update the corresponding value. 
 int get(int key) returns the value to which the specified key is mapped, or -1 
if this map contains no mapping for the key. 
 void remove(key) removes the key and its corresponding value if the map 
contains the mapping for the key. 
 

 
 Example 1: 

 
Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2
]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the 
existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 

 
 Constraints: 

 
 0 <= key, value <= 10⁶ 
 At most 10⁴ calls will be made to put, get, and remove. 
 

 Related Topics Array Hash Table Linked List Design Hash Function 👍 3957 👎 357


*/
package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 2023-03-08 22:59:24
// Jesse Yang
public class Leetcode0706DesignHashmap{
    // Java: design-hashmap
    public static void main(String[] args) {
	    MyHashMap sol = new Leetcode0706DesignHashmap().new MyHashMap();
        // TO TEST
        
        System.out.println();
	    HashMap<Integer, Integer> map = new HashMap<>();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MyHashMap {
	
	final int MAX_SIZE = 19997;
	List<int[]>[] list;
	
	public MyHashMap() {
		list = new List[MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++) {
			list[i] = new ArrayList<>();
		}
	}
	
	public void put(int key, int value) {
		int i = key % MAX_SIZE;
		int j = find(list[i], key);
		if (j == -1) {
			list[i].add(new int[]{key, value});
		} else {
			list[i].get(j)[1] = value;
		}
	}
	
	public int get(int key) {
		int i = key % MAX_SIZE;
		int j = find(list[i], key);
		return j == -1 ? -1 : list[i].get(j)[1];
	}
	
	public void remove(int key) {
		int i = key % MAX_SIZE;
		int j = find(list[i], key);
		if (j == -1) {
			return;
		}
		list[i].remove(j);
	}
	
	private int find(List<int[]> list, int key) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0] == key) {
				return i;
			}
		}
		return -1;
	}
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: 把Cell让HashMap的Key和Value可以是不同的数据类型，接近HashMap的原始做法，切没有rehashing
class MyHashMap1 {
	
	private static final double LOAD_FACTOR = 0.77d;
	private int MAX_SIZE = 256;
	private List<Cell<Integer, Integer>>[] items;
	private int size;
	
	public MyHashMap1() {
		this.items = (List<Cell<Integer, Integer>>[]) new LinkedList[MAX_SIZE];
		this.size = 0;;
	}
	
	public void put(int key, int value) {
		Cell<Integer, Integer> newCell = new Cell<>(key, value);
		int index = hashCodeOfKey(key);
		if (items[index] == null) {
			items[index] = new LinkedList<>();
		}
		List<Cell<Integer, Integer>> slot = items[index];
		for (Cell<Integer, Integer> cell: slot) {
			if (newCell.equals(cell)) {
				slot.remove(cell);
				this.size --;
				break;
			}
		}
		this.size++;
		slot.add(newCell);
		if (this.size > MAX_SIZE * LOAD_FACTOR) {
			rehashing();
		}
	}
	
	public int get(int key) {
		int index = hashCodeOfKey(key);
		if (items[index] == null) {
			return -1;
		}
		
		List<Cell<Integer, Integer>> slot = items[index];
		for (Cell<Integer, Integer> cell: slot) {
			if (cell.keyEquals(key)) {
				return cell.getVal();
			}
		}
		return -1;
		
	}
	
	public void remove(int key) {
		int index = hashCodeOfKey(key);
		if (items[index] == null) {
			return;
		}
		
		List<Cell<Integer, Integer>> slot = items[index];
		slot.remove(new Cell<>(key, -1));
	}
	
	private int hashCodeOfKey(int key) {
		return (Math.abs(Integer.hashCode(key)% MAX_SIZE));
	}
	
	private void rehashing() {
		MAX_SIZE *= 2;
		//noinspection unchecked
		List<Cell<Integer, Integer>>[] newItems = (List<Cell<Integer, Integer>>[]) new LinkedList[MAX_SIZE];
		
		for (List<Cell<Integer, Integer>> slot: items) {
			if (slot != null) {
				for (Cell<Integer, Integer> cell: slot) {
					int index = hashCodeOfKey(cell.getKey());
					if (newItems[index] == null) {
						newItems[index] = new LinkedList<Cell<Integer, Integer>>();
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
}

// Solution 2: 不用Cell，而是用长度为2的数组int[]来模拟一个Cell
class MyHashMap2 {
	final int N = 19997;
	List<int[]>[] list;
	public MyHashMap2() {
		list = new List[N];
		for(int i=0;i<N;i++){
			list[i] = new ArrayList<>();
		}
	}
	
	public void put(int key, int value) {
		int i = key%N;
		int j = find(list[i],key);
		if(j==-1) list[i].add(new int[]{key,value});
		else list[i].get(j)[1] = value;
	}
	
	public int get(int key) {
		int i = key%N;
		int j = find(list[i],key);
		return j==-1? -1:list[i].get(j)[1];
	}
	
	public void remove(int key) {
		int i = key%N;
		int j = find(list[i],key);
		if(j==-1) return;
		list[i].remove(j);
	}
	public int find(List<int[]> list, int key){
		for(int i=0;i<list.size();i++){
			if(list.get(i)[0]==key) return i;
		}
		return -1;
	}
}

