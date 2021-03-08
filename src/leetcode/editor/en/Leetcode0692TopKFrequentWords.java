//Given a non-empty list of words, return the k most frequent elements. 
// Your answer should be sorted by frequency from highest to lowest. If two word
//s have the same frequency, then the word with the lower alphabetical order comes
// first. 
//
// Example 1: 
// 
//Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//Output: ["i", "love"]
//Explanation: "i" and "love" are the two most frequent words.
//    Note that "i" comes before "love" due to a lower alphabetical order.
// 
// 
//
// Example 2: 
// 
//Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"]
//, k = 4
//Output: ["the", "is", "sunny", "day"]
//Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
//    with the number of occurrence being 4, 3, 2 and 1 respectively.
// 
// 
//
// Note: 
// 
// You may assume k is always valid, 1 ≤ k ≤ number of unique elements. 
// Input words contain only lowercase letters. 
// 
// 
//
// Follow up: 
// 
// Try to solve it in O(n log k) time and O(n) extra space. 
// 
// Related Topics Hash Table Heap Trie 
// 👍 1904 👎 146

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 11:56:03
// Zeshi Yang
public class Leetcode0692TopKFrequentWords{
    // Java: top-k-frequent-words
    public static void main(String[] args) {
        Solution sol = new Leetcode0692TopKFrequentWords().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();

        for (String word: words) {
            if (count.containsKey(word)) {
                count.put(word, count.get(word) + 1);
            }
            else {
                count.put(word, 1);
            }
        }
        PriorityQueue<String> heap = new PriorityQueue<>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                        w2.compareTo(w1) : count.get(w1) - count.get(w2)); // min heap

        for (String word: count.keySet()) { // keySet的返回类型是Set啊，为什么这里可以用String
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while(!heap.isEmpty()) {
            result.add(heap.poll());
        }
        Collections.reverse(result);
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}