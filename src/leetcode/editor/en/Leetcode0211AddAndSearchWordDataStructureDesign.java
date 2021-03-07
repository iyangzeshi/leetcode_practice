//Design a data structure that supports the following two operations: 
//
// 
//void addWord(word)
//bool search(word)
// 
//
// search(word) can search a literal word or a regular expression string contain
//ing only letters a-z or .. A . means it can represent any one letter. 
//
// Example: 
//
// 
//addWord("bad")
//addWord("dad")
//addWord("mad")
//search("pad") -> false
//search("bad") -> true
//search(".ad") -> true
//search("b..") -> true
// 
//
// Note: 
//You may assume that all words are consist of lowercase letters a-z. 
// Related Topics Backtracking Design Trie 
// 👍 1790 👎 91

package leetcode.editor.en;

// 2020-07-16 15:27:10
// Zeshi Yang
public class Leetcode0211AddAndSearchWordDataStructureDesign{
    // Java: add-and-search-word-data-structure-design
    public static void main(String[] args) {
        WordDictionary sol = new Leetcode0211AddAndSearchWordDataStructureDesign().new WordDictionary();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class TrieNode {
    public char ch;
    public TrieNode[] children;
    public boolean isLeaf = false;

    public TrieNode(char c){
        ch = c;
        children = new TrieNode[26];
    }
}

class WordDictionary {

    private TrieNode root = new TrieNode('\0');

    /** Initialize your data structure here. */
    public WordDictionary() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isLeaf = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(root, word.toCharArray(), 0);
    }

    private boolean dfs(TrieNode cur, char[] word, int index) {
        int len = word.length;
        // base case
        if (cur == null) return false;
        if (index == len) return cur.isLeaf;

        // general case
        char ch = word[index];
        if (ch != '.') {
            return dfs(cur.children[ch - 'a'], word, index + 1);
        } else {
            for (TrieNode next : cur.children) {
                if (dfs(next, word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}

/*
  Your WordDictionary object will be instantiated and called as such:
  WordDictionary obj = new WordDictionary();
  obj.addWord(word);
  boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

}