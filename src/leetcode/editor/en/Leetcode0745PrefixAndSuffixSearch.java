/**
Design a special dictionary that searches the words in it by a prefix and a 
suffix. 

 Implement the WordFilter class: 

 
 WordFilter(string[] words) Initializes the object with the words in the 
dictionary. 
 f(string pref, string suff) Returns the index of the word in the dictionary, 
which has the prefix pref and the suffix suff. If there is more than one valid 
index, return the largest of them. If there is no such word in the dictionary, 
return -1. 
 

 
 Example 1: 

 
Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]
Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = 
"a" and suffix = "e".
 

 
 Constraints: 

 
 1 <= words.length <= 10⁴ 
 1 <= words[i].length <= 7 
 1 <= pref.length, suff.length <= 7 
 words[i], pref and suff consist of lowercase English letters only. 
 At most 10⁴ calls will be made to the function f. 
 

 Related Topics Hash Table String Design Trie 👍 2254 👎 481

*/
package leetcode.editor.en;

// 2024-03-22 17:13:25
// Jesse Yang
public class Leetcode0745PrefixAndSuffixSearch{
    // Java: prefix-and-suffix-search
    public static void main(String[] args) {
        WordFilter sol = new Leetcode0745PrefixAndSuffixSearch().new WordFilter(null);
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
1. insert trie => build trie from the diven dictionary
2. query => deal with the given queries based on the prefix/suffix
 */
class WordFilter {

    public WordFilter(String[] words) {
        
    }
    
    public int f(String pref, String suff) {
        return -1;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */
//leetcode submit region end(Prohibit modification and deletion)
/*
常见的字符串算法
1. prefix-trie -> 可持久化数据结构, segment tree
2. KMP
3. String Hash
4. Manchester
 */
}