//Create a timebased key-value store class TimeMap, that supports two operations
//. 
//
// 1. set(string key, string value, int timestamp) 
//
// 
// Stores the key and value, along with the given timestamp. 
// 
//
// 2. get(string key, int timestamp) 
//
// 
// Returns a value such that set(key, value, timestamp_prev) was called previous
//ly, with timestamp_prev <= timestamp. 
// If there are multiple such values, it returns the one with the largest timest
//amp_prev. 
// If there are no values, it returns the empty string (""). 
// 
//
// 
//
// 
// Example 1: 
//
// 
//Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],
//["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
//Output: [null,null,"bar","bar",null,"bar2","bar2"]
//Explanation:   
//TimeMap kv;   
//kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with tim
//estamp = 1   
//kv.get("foo", 1);  // output "bar"   
//kv.get("foo", 3); // output "bar" since there is no value corresponding to foo
// at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar" 
//  
//kv.set("foo", "bar2", 4);   
//kv.get("foo", 4); // output "bar2"   
//kv.get("foo", 5); //output "bar2"   
//
// 
//
// 
// Example 2: 
//
// 
//Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs 
//= [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["
//love",20],["love",25]]
//Output: [null,null,null,"","high","high","low","low"]
// 
// 
// 
//
// 
//
// Note: 
//
// 
// All key/value strings are lowercase. 
// All key/value strings have length in the range [1, 100] 
// The timestamps for all TimeMap.set operations are strictly increasing. 
// 1 <= timestamp <= 10^7 
// TimeMap.set and TimeMap.get functions will be called a total of 120000 times 
//(combined) per test case. 
// 
// Related Topics Hash Table Binary Search 
// 👍 918 👎 118

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.util.Pair;

// 2020-11-09 12:32:14
// Zeshi Yang
public class Leetcode0981TimeBasedKeyValueStore {
	
	// Java: time-based-key-value-store
	public static void main(String[] args) {
		TimeMap2 sol = new Leetcode0981TimeBasedKeyValueStore().new TimeMap2();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class TimeMap {
    
    Map<String, List<Pair<Integer, String>>> map;
    
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>(); //初始化map
    }
    
    public void set(String key, String value, int timestamp) { //放 key
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>()); //没有 新建
        }
        
        map.get(key).add(new Pair<>(timestamp, value));// 拿出来塞进去
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";//没有返回空
        }
        
        List<Pair<Integer, String>> A = map.get(key); // 先拿出 list
        int i = Collections.binarySearch(A, new Pair<>(timestamp, "{"),
                Comparator.comparingInt(Pair::getKey));
        
        if (i >= 0) {// 被找到
            return A.get(i).getValue();
        } else if (i == -1) { // 没有
            return "";
        } else {// 这个值应该插入的地方。比当前 time stamp小的最大值
            return A.get(-i - 2).getValue();
        }
    }
}

/*
  Your TimeMap object will be instantiated and called as such:
  TimeMap obj = new TimeMap();
  obj.set(key,value,timestamp);
  String param_2 = obj.get(key,timestamp);
 */
//leetcode submit region end(Prohibit modification and deletion)
//Solution 1:129 ms,击败了67.70% 的Java用户, 114.3 MB,击败了68.13% 的Java用户
/**
 * 设计一个数据结构 可以设置 key value 和 时间戳。
 * 思路HashMap 里面存 List<Pair> pair 的结构是 Integer,
 * String integer 是 stamp， string 是value， get的话  通过k 拿到比 time stamp小的 最大的 val
 * search方法用binary search
*/
class TimeMap1 {
    
    Map<String, List<Pair<Integer, String>>> map;
    
    /** Initialize your data structure here. */
    public TimeMap1() {
        map = new HashMap<>(); //初始化map
    }
    
    public void set(String key, String value, int timestamp) { //放 key
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>()); //没有 新建
        }
        
        map.get(key).add(new Pair<>(timestamp, value));// 拿出来塞进去
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";//没有返回空
        }
        
        List<Pair<Integer, String>> A = map.get(key); // 先拿出 list
        int i = Collections.binarySearch(A, new Pair<>(timestamp, "{"),
                Comparator.comparingInt(Pair::getKey));
        
        if (i >= 0) {// 被找到
            return A.get(i).getValue();
        } else if (i == -1) { // 没有
            return "";
        } else {// 这个值应该插入的地方。比当前 time stamp小的最大值
            return A.get(-i - 2).getValue();
        }
    }
}

// Solution 2:
// 140 ms,击败了28.48% 的Java用户, 115.3 MB,击败了31.14% 的Java用户
/**
 * 设计一个数据结构 可以设置 key value 和 时间戳。
 * 思路HashMap 里面存 TreeMap<Integer, String>, key是时间戳，value是值
 * String integer 是 stamp， string 是value get的话  通过k 拿到 time stamp 最大的 val
 */
class TimeMap2 {
    
    private HashMap<String, TreeMap<Integer, String>> map;
    
    // Intialize your data structure
    public TimeMap2() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }
    
    // time = O(logn), space = O(n)
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("Ket is not existent!");
        }
        TreeMap<Integer, String> treeMap = map.get(key);
        Integer floorKey = treeMap.floorKey(timestamp);   // (250)  300    320     350
        if (floorKey == null) {
            return "";
        }
        return treeMap.get(floorKey); // value
    }
}
}