//Implement a trie with insert, search, and startsWith methods. 
//
// Example: 
//
// 
//Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // returns true
//trie.search("app");     // returns false
//trie.startsWith("app"); // returns true
//trie.insert("app");   
//trie.search("app");     // returns true
// 
//
// Note: 
//
// 
// You may assume that all inputs are consist of lowercase letters a-z. 
// All inputs are guaranteed to be non-empty strings. 
// 
// Related Topics Design Trie 
// 👍 3205 👎 50

package leetcode.editor.en;

// 2020-07-16 15:04:20
// Zeshi Yang
public class Leetcode0208ImplementTriePrefixTree{
    // Java: implement-trie-prefix-tree
    public static void main(String[] args) {
	    Trie sol = new Leetcode0208ImplementTriePrefixTree().new Trie();
        // TO TEST

        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class TrieNode {
    final int alphaSize = 26;
    boolean isLeaf = false;
    char c;
    TrieNode[] children = new TrieNode[26];
    public TrieNode() {

    }

    public TrieNode (char c) {
    	this.c = c;
    }
}
class Trie {
	private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
	    for (int i = 0; i < word.length(); i++) {
		    int idx = word.charAt(i) - 'a';
		    if (cur.children[idx] == null) {
		    	cur.children[idx] = new TrieNode(word.charAt(i));
		    }
		    cur = cur.children[idx];
	    }
	    cur.isLeaf = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
	    for (int i = 0; i < word.length(); i++) {
		    int idx = word.charAt(i) - 'a';
		    if (cur.children[idx] == null) {
				return false;
		    }
		    cur = cur.children[idx];
	    }
	    return cur.isLeaf;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
	    for (int i = 0; i < prefix.length(); i++) {
		    int idx = prefix.charAt(i) - 'a';
		    if (cur.children[idx] == null) {
			    return false;
		    }
		    cur = cur.children[idx];
	    }
	    return true;
    }
}


/*
  Your Trie object will be instantiated and called as such:
  Trie obj = new Trie();
  obj.insert(word);
  boolean param_2 = obj.search(word);
  boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}