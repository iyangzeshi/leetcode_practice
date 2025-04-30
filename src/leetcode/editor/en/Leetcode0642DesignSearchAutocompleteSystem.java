//Design a search autocomplete system for a search engine. Users may input a sen
//tence (at least one word and end with a special character '#'). For each charact
//er they type except '#', you need to return the top 3 historical hot sentences t
//hat have prefix the same as the part of sentence already typed. Here are the spe
//cific rules: 
//
// 
// The hot degree for a sentence is defined as the number of times a user typed 
//the exactly same sentence before. 
// The returned top 3 hot sentences should be sorted by hot degree (The first is
// the hottest one). If several sentences have the same degree of hot, you need to
// use ASCII-code order (smaller one appears first). 
// If less than 3 hot sentences exist, then just return as many as you can. 
// When the input is a special character, it means the sentence ends, and in thi
//s case, you need to return an empty list. 
// 
//
// Your job is to implement the following functions: 
//
// The constructor function: 
//
// AutocompleteSystem(String[] sentences, int[] times): This is the constructor.
// The input is historical data. Sentences is a string array consists of previousl
//y typed sentences. Times is the corresponding times a sentence has been typed. Y
//our system should record these historical data. 
//
// Now, the user wants to input a new sentence. The following function will prov
//ide the next character the user types: 
//
// List<String> input(char c): The input c is the next character typed by the us
//er. The character will only be lower-case letters ('a' to 'z'), blank space (' '
//) or a special character ('#'). Also, the previously typed sentence should be re
//corded in your system. The output will be the top 3 historical hot sentences tha
//t have prefix the same as the part of sentence already typed. 
// 
//
// Example: 
//Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetc
//ode"], [5,3,2,2]) 
//The system have already tracked down the following sentences and their corresp
//onding times: 
//"i love you" : 5 times 
//"island" : 3 times 
//"ironman" : 2 times 
//"i love leetcode" : 2 times 
//Now, the user begins another search: 
// 
//Operation: input('i') 
//Output: ["i love you", "island","i love leetcode"] 
//Explanation: 
//There are four sentences that have prefix "i". Among them, "ironman" and "i lo
//ve leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII
// code 114, "i love leetcode" should be in front of "ironman". Also we only need 
//to output top 3 hot sentences, so "ironman" will be ignored. 
// 
//Operation: input(' ') 
//Output: ["i love you","i love leetcode"] 
//Explanation: 
//There are only two sentences that have prefix "i ". 
// 
//Operation: input('a') 
//Output: [] 
//Explanation: 
//There are no sentences that have prefix "i a". 
// 
//Operation: input('#') 
//Output: [] 
//Explanation: 
//The user finished the input, the sentence "i a" should be saved as a historica
//l sentence in system. And the following input will be counted as a new search. 
// 
//
// Note: 
//
// 
// The input sentence will always start with a letter and end with '#', and only
// one blank space will exist between two words. 
// The number of complete sentences that to be searched won't exceed 100. The le
//ngth of each sentence including those in the historical data won't exceed 100. 
// Please use double-quote instead of single-quote when you write test cases eve
//n for a character input. 
// Please remember to RESET your class variables declared in class AutocompleteS
//ystem, as static/class variables are persisted across multiple test cases. Pleas
//e see here for more details. 
// 
//
// 
// Related Topics Design Trie 
// 👍 1004 👎 78
package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

// 2020-07-17 15:11:28
// Jesse Yang
public class Leetcode0642DesignSearchAutocompleteSystem{
    // Java: design-search-autocomplete-system
    public static void main(String[] args) {
        // TO TEST
        String[] sentences = {"i love you","island","iroman","i love leetcode"};
        int[] times = {5,3,2,2};
        AutocompleteSystem autocompleteSystem =
                new Leetcode0642DesignSearchAutocompleteSystem().
                        new AutocompleteSystem(sentences, times);
        System.out.println(autocompleteSystem.input('i'));
        System.out.println(autocompleteSystem.input(' '));
        System.out.println(autocompleteSystem.input('a'));
        System.out.println(autocompleteSystem.input('#'));
//        System.out.println(autocompleteSystem.input('i'));
//        System.out.println(autocompleteSystem.input(' '));
//        System.out.println(autocompleteSystem.input('a'));
//        System.out.println(autocompleteSystem.input('#'));
//        System.out.println(autocompleteSystem.input('i'));
//        System.out.println(autocompleteSystem.input(' '));
//        System.out.println(autocompleteSystem.input('a'));
//        System.out.println(autocompleteSystem.input('#'));
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
关键数据结构
Trie（前缀树）：Trie 是一种高效的字符串存储和搜索结构，适合用于前缀匹配。
哈希表：用来存储句子及其频率，便于快速更新和查找。
思路步骤
Trie 节点定义：

每个节点存储一个子节点的字典和一个句子-频率的字典，以便快速访问和更新句子的频率。
向 Trie 中插入句子：

遍历句子的每个字符，逐字符插入到 Trie 中。
在每个节点更新句子及其频率。
搜索前缀：

根据输入的前缀字符，遍历 Trie。
在每个节点收集与前缀匹配的句子，按频率和字典序排序，返回前3个结果。
处理输入：

每输入一个字符，更新当前前缀。
当输入字符为 # 时，将当前前缀作为一个完整句子存储到 Trie 中，并重置前缀。

搜索前缀的时间复杂度可以分为两个部分：

遍历 Trie 以找到前缀节点：这个操作的时间复杂度是 O(P)，其中 P 是前缀的长度。
从前缀节点开始收集所有匹配句子并排序：假设有 M 个句子与前缀匹配，每个句子的长度为 L。收集句子的时间复杂度是 O(M)，排序的时间复杂度是 O(M log M)。
因此，总的时间复杂度是 O(M log M)。
综合来看，搜索前缀的总时间复杂度是 O(P + M log M)，其中 P 是前缀的长度，M 是匹配的句子数量。

详细空间复杂度
Trie 树结构：

每个节点有一个指向26个字母和其他字符的指针数组，假设字符集为 C，空间复杂度为 O(N * L * C)。
哈希表 sentences：

每个节点都可能有一个存储句子及其频率的哈希表，在最坏情况下为 O(N * L)。
总结
综合以上分析，设计搜索自动完成系统的空间复杂度主要受句子的数量和长度的影响：

Trie 树结构：O(N * L * C)
哈希表 sentences：O(N * L)
因此，总的空间复杂度为 O(N * L * C)，其中 N 是句子的数量，L 是句子的平均长度，C 是字符集大小。
 */
class AutocompleteSystem {
    
    class TrieNode {
        
        public char ch;
        public TrieNode[] children;
        public HashMap<String, Integer> countMap; // key:word; value: count
        public boolean isLeaf;
        
        public TrieNode(char ch) {
            this.ch = ch;
            children = new TrieNode[27]; // a - z 和 space
            this.isLeaf = false;
            this.countMap = new HashMap<>(3);
        }
        
    }
    
    class Pair {
        
        public String str;
        public int count;
        
        public Pair(String str, int count) {
            this.str = str;
            this.count = count;
        }
        
    }
    
    private final TrieNode root;
    private TrieNode curNode;
    private StringBuilder path;
    private final HashMap<String, Integer> countBook; // global HashMap 存所有句子以及对应count
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        if (sentences == null || times == null || sentences.length != times.length) {
            throw new IllegalArgumentException("Not valid data");
        }
        root = new TrieNode('\0');
        curNode = root;
        path = new StringBuilder();
        countBook = new HashMap<>();
        int len = sentences.length;
        for (int i = 0; i < len; i++) {
            countBook.put(sentences[i], times[i]);
            insert(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        if (c == '#') { // 如果一个句子输入完毕
            curNode = root; // 回到起点，以备下一次的autocomplete
            String insertMe = path.toString();
            countBook.put(insertMe, countBook.getOrDefault(insertMe, 0) + 1);
            insert(insertMe, countBook.get(insertMe)); // 将输入后的变化更新到Trie中
            path = new StringBuilder();
            return new ArrayList<>(); // 输入结束，return an empty list
        }
        path.append(c);
        // 如果上一次的input就没有autocomplete
        // (上一轮curNode == null; 且返回空list)
        // 那么这一次输入，也会返回空list
        if (curNode == null) {
            return new ArrayList<>();
        }
        int index = (c >= 'a' && c <= 'z') ? c - 'a' : 26;
        curNode = curNode.children[index];
        if (curNode == null) {
            return new ArrayList<>();
        }
        // return输入到这个char时，top 3 sentences
        return getTop3String(curNode.countMap);
    }
    
    // 更新Trie
    private void insert(String sentence, int times) {
        TrieNode cur = root;
        for (char ch : sentence.toCharArray()) {
            int index = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode(ch);
            }
            TrieNode next = cur.children[index];
            next.countMap.put(sentence, times);
            List<Pair> top3 = getTop3Pair(next.countMap);
            next.countMap.clear();
            // countMap里put新的sentence-times; 清空countMap; put进新的top3 key-value
            for (Pair p : top3) {
                next.countMap.put(p.str, p.count);
            }
            cur = next;
        }
        cur.isLeaf = true;
    }
    
    // 将top3 pair转成top3 String(sentences)
    private List<String> getTop3String(HashMap<String, Integer> countMap) {
        return getTop3Pair(countMap)
                .stream()
                .map(p -> p.str)
                .collect(Collectors.toList());
        // https://www.geeksforgeeks.org/stream-in-java/
    }
    
    private List<Pair> getTop3Pair(HashMap<String, Integer> countMap) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                (a, b) ->
                        (a.count == b.count
                                ? a.str.compareTo(b.str)
                                : b.count - a.count));
        for (Map.Entry<String, Integer> e : countMap.entrySet()) {
            maxHeap.offer(new Pair(e.getKey(), e.getValue()));
        }
        List<Pair> res = new ArrayList<>();
        for (int i = 0; i < 3 && !maxHeap.isEmpty(); i++) { // maxHeap里可能少于3个
            res.add(maxHeap.poll());
        }
        return res;
    }
    
}
/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
//leetcode submit region end(Prohibit modification and deletion)
/*面试的时候，用Solution 2_2或者Solution 2_3 */

// Solution 1: by 算法哥 《算法加强doc》，写的不好
// 123 ms,击败了79.77% 的Java用户, 47.9 MB,击败了90.89% 的Java用户
class AutocompleteSystem1 {
    
    class TrieNode {
        
        public char ch;
        public TrieNode[] children;
        public HashMap<String, Integer> countMap; // key:word; value: count
        public boolean isLeaf;
        
        public TrieNode(char ch) {
            this.ch = ch;
            children = new TrieNode[27]; // a - z 和 space
            this.isLeaf = false;
            this.countMap = new HashMap<>(3);
        }
        
    }
    
    class Pair {
        
        public String str;
        public int count;
        
        public Pair(String str, int count) {
            this.str = str;
            this.count = count;
        }
        
    }
    
    private final TrieNode root;
    private TrieNode curNode;
    private StringBuilder path;
    private final HashMap<String, Integer> countBook; // global HashMap 存所有句子以及对应count
    
    public AutocompleteSystem1(String[] sentences, int[] times) {
        if (sentences == null || times == null || sentences.length != times.length) {
            throw new IllegalArgumentException("Not valid data");
        }
        root = new TrieNode('\0');
        curNode = root;
        path = new StringBuilder();
        countBook = new HashMap<>();
        int len = sentences.length;
        for (int i = 0; i < len; i++) {
            countBook.put(sentences[i], times[i]);
            insert(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        if (c == '#') { // 如果一个句子输入完毕
            curNode = root; // 回到起点，以备下一次的autocomplete
            String insertMe = path.toString();
            countBook.put(insertMe, countBook.getOrDefault(insertMe, 0) + 1);
            insert(insertMe, countBook.get(insertMe)); // 将输入后的变化更新到Trie中
            path = new StringBuilder();
            return new ArrayList<>(); // 输入结束，return an empty list
        }
        path.append(c);
        // 如果上一次的input就没有autocomplete
        // (上一轮curNode == null; 且返回空list)
        // 那么这一次输入，也会返回空list
        if (curNode == null) {
            return new ArrayList<>();
        }
        int index = (c >= 'a' && c <= 'z') ? c - 'a' : 26;
        curNode = curNode.children[index];
        if (curNode == null) {
            return new ArrayList<>();
        }
        // return输入到这个char时，top 3 sentences
        return getTop3String(curNode.countMap);
    }
    
    // 更新Trie
    private void insert(String sentence, int times) {
        TrieNode cur = root;
        for (char ch : sentence.toCharArray()) {
            int index = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode(ch);
            }
            TrieNode next = cur.children[index];
            next.countMap.put(sentence, times);
            List<Pair> top3 = getTop3Pair(next.countMap);
            next.countMap.clear();
            // countMap里put新的sentence-times; 清空countMap; put进新的top3 key-value
            for (Pair p : top3) {
                next.countMap.put(p.str, p.count);
            }
            cur = next;
        }
        cur.isLeaf = true;
    }
    
    // 将top3 pair转成top3 String(sentences)
    private List<String> getTop3String(HashMap<String, Integer> countMap) {
        return getTop3Pair(countMap)
                .stream()
                .map(p -> p.str)
                .collect(Collectors.toList());
        // https://www.geeksforgeeks.org/stream-in-java/
    }
    
    private List<Pair> getTop3Pair(HashMap<String, Integer> countMap) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                (a, b) ->
                        (a.count == b.count
                                ? a.str.compareTo(b.str)
                                : b.count - a.count));
        for (Map.Entry<String, Integer> e : countMap.entrySet()) {
            maxHeap.offer(new Pair(e.getKey(), e.getValue()));
        }
        List<Pair> res = new ArrayList<>();
        for (int i = 0; i < 3 && !maxHeap.isEmpty(); i++) { // maxHeap里可能少于3个
            res.add(maxHeap.poll());
        }
        return res;
    }
    
}

// Solution 2_1: by 算法哥 202002算法加强第22节课，上课代码改正版本
// 110 ms,击败了88.03% 的Java用户, 49.1 MB,击败了40.66% 的Java用户
class AutocompleteSystem2_1 {
    
    class TrieNode {
        
        public int num;
        public char ch;
        public TrieNode[] children;
        public boolean isWord; // 可以删除
        public HashMap<String, Integer> countMap;
        public List<String> candidates;
        
        public TrieNode(char val) {
            num = 3;
            this.ch = val;
            this.children = new TrieNode[27];
            this.isWord = false; // 可以删除
            this.countMap = new HashMap<>();
            candidates = new ArrayList<>();
        }
        
        public List<String> getTop3() {
            candidates.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (!countMap.get(o1).equals(countMap.get(o2))) {
                        return countMap.get(o2) - countMap.get(o1);
                    } else {
                        return o1.compareTo(o2);
                    }
                }
            });
            List<String> res = new ArrayList<>(3);
            for (int i = 0; i < num && i < candidates.size(); i++) {
                res.add(candidates.get(i));
            }
            return res;
        }
        
        public void addCount(String word, int addCount) {
            int count;
            if (countMap.containsKey(word)) {
                count = countMap.getOrDefault(word, 0) + addCount;
            } else {
                count = addCount;
                candidates.add(word);
            }
            countMap.put(word, count);
        }
        
    }
    
    class Trie {
        
        public TrieNode root;
        public TrieNode cur;
        public HashMap<String, Integer> countMap;// 可以删除
        
        public Trie() {
            this.root = new TrieNode('\0');
            this.cur = root;
            this.countMap = new HashMap<>(); // 可以删除
        }
        
        public void insert(String word, int count) {
            countMap.put(word, count);// 可以删除
            cur = root; // 每次都是从头开始插入，所以cur = root;
            for (char ch : word.toCharArray()) {
                int idx = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode(ch);
                }
                cur = cur.children[idx];
                cur.addCount(word, count);
            }
            cur.isWord = true; // 可以删除
            cur = root; // 插入单词之后，之后每次input检索单词之前，需要先把cur置到root
        }
        
        public void insert(String word) {
            int count = countMap.getOrDefault(word, 0);// 可以删除
            countMap.put(word, ++count);// 可以删除
            this.insert(word, 1);
        }
        
        public List<String> search(char ch) {
            if (cur == null) {
                return new ArrayList<>();
            }
            int idx = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
            TrieNode next = cur.children[idx];
            cur = next; // 这个要放在判断next == null之前，否则next == null的时候，cur不会变
            if (next == null) {
                return new ArrayList<>();
            }
            return cur.getTop3();
        }
        
    }
    
    private final Trie trie;
    StringBuilder path;
    
    public AutocompleteSystem2_1(String[] sentences, int[] times) {
        if (sentences == null || times == null || sentences.length != times.length) {
            throw new IllegalArgumentException("Not valid data");
        }
        this.trie = new Trie();
        int len = times.length;
        for (int i = 0; i < len; i++) {
            String word = sentences[i];
            int count = times[i];
            this.trie.insert(word, count);
        }
        path = new StringBuilder();
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            String word = path.toString();
            // path.delete(0, path.length()); // means deleted from [0, path.length())
            path.setLength(0);
            this.trie.insert(word);
            return new ArrayList<>();
        }
        path.append(c);
        return this.trie.search(c);
    }
    
}

// Solution 2_2: by 算法哥 202002算法加强第22节课，上课代码改正之后，优化的版本（删除了不必要的东西）
// 110 ms,击败了88.03% 的Java用户, 49.3 MB,击败了37.91% 的Java用户
/*
每个TrieNode里面都有List和HashMap
 */
class AutocompleteSystem2_2 {
    
    class TrieNode {
        
        public int num;
        public char ch;
        public TrieNode[] children;
        public HashMap<String, Integer> countMap;
        public List<String> candidates;
        
        public TrieNode(char val) {
            num = 3;
            this.ch = val;
            this.children = new TrieNode[27];
            this.countMap = new HashMap<>();
            candidates = new ArrayList<>();
        }
        
        public List<String> getTop3() {
            candidates.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (!countMap.get(o1).equals(countMap.get(o2))) {
                        return countMap.get(o2) - countMap.get(o1);
                    } else {
                        return o1.compareTo(o2);
                    }
                }
            });
            List<String> res = new ArrayList<>(3);
            for (int i = 0; i < num && i < candidates.size(); i++) {
                res.add(candidates.get(i));
            }
            return res;
        }
        
        public void addCount(String word, int addCount) {
            int count;
            if (countMap.containsKey(word)) {
                count = countMap.getOrDefault(word, 0) + addCount;
            } else {
                count = addCount;
                candidates.add(word);
            }
            countMap.put(word, count);
        }
        
    }
    
    class Trie {
        
        public TrieNode root;
        public TrieNode cur;
        
        public Trie() {
            this.root = new TrieNode('\0');
            this.cur = root;
        }
        
        public void insert(String word, int count) {
            cur = root; // 每次都是从头开始插入，所以cur = root;
            for (char ch : word.toCharArray()) {
                int idx = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode(ch);
                }
                cur = cur.children[idx];
                cur.addCount(word, count);
            }
            cur = root; // 插入单词之后，之后每次input检索单词之前，需要先把cur置到root
        }
        
        public void insert(String word) {
            this.insert(word, 1);
        }
        
        public List<String> search(char ch) {
            if (cur == null) { // to be deleted
                return new ArrayList<>();
            }
            int idx = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
            TrieNode next = cur.children[idx];
            cur = next; // 这个要放在判断next == null之前，否则next == null的时候，cur不会变
            if (next == null) {
                return new ArrayList<>();
            }
            return cur.getTop3();
        }
        
    }
    
    private final Trie trie;
    StringBuilder path;
    
    public AutocompleteSystem2_2(String[] sentences, int[] times) {
        if (sentences == null || times == null || sentences.length != times.length) {
            throw new IllegalArgumentException("Not valid data");
        }
        this.trie = new Trie();
        int len = times.length;
        for (int i = 0; i < len; i++) {
            String word = sentences[i];
            int count = times[i];
            this.trie.insert(word, count);
        }
        path = new StringBuilder();
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            String word = path.toString();
            path.setLength(0);
            this.trie.insert(word);
            return new ArrayList<>();
        }
        path.append(c);
        return this.trie.search(c);
    }
    
}

// Solution 2_3:
/*
和Solution 2_2的区别： countMap只需要生成一个，这里是在Trie里面生成
 */
class AutocompleteSystem2_3 {
    
    class Trie {
        
        class TrieNode {
            
            public int num;
            public char ch;
            public TrieNode[] children;
            public List<String> candidates;
            
            public TrieNode(char val) {
                num = 3;
                this.ch = val;
                this.children = new TrieNode[27];
                candidates = new ArrayList<>();
            }
            
            public List<String> getTop3() {
                candidates.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if (!countMap.get(o1).equals(countMap.get(o2))) {
                            return countMap.get(o2) - countMap.get(o1);
                        } else {
                            return o1.compareTo(o2);
                        }
                    }
                });
                List<String> res = new ArrayList<>(3);
                for (int i = 0; i < num && i < candidates.size(); i++) {
                    res.add(candidates.get(i));
                }
                return res;
            }
            
            public void addCandidate(String word) {
                if (!candidates.contains(word)) {
                    candidates.add(word);
                }
            }
            
        }
        
        public TrieNode root;
        public TrieNode cur;
        public HashMap<String, Integer> countMap;// 可以删除
        
        public Trie() {
            this.root = new TrieNode('\0');
            this.cur = root;
            this.countMap = new HashMap<>(); // 可以删除
        }
        
        public void insert(String word, int count) {
            countMap.put(word, countMap.getOrDefault(word, 0) + count);// 可以删除
            cur = root; // 每次都是从头开始插入，所以cur = root;
            for (char ch : word.toCharArray()) {
                int idx = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode(ch);
                }
                cur = cur.children[idx];
                cur.addCandidate(word);
            }
            cur = root; // 插入单词之后，之后每次input检索单词之前，需要先把cur置到root
        }
        
        public void insert(String word) {
//            int count = countMap.getOrDefault(word, 0);// 可以删除
//            countMap.put(word, ++count);// 可以删除
            this.insert(word, 1);
        }
        
        public List<String> search(char ch) {
            if (cur == null) { // to be deleted
                return new ArrayList<>();
            }
            int idx = (ch >= 'a' && ch <= 'z') ? ch - 'a' : 26;
            TrieNode next = cur.children[idx];
            cur = next; // 这个要放在判断next == null之前，否则next == null的时候，cur不会变
            if (next == null) {
                return new ArrayList<>();
            }
            return cur.getTop3();
        }
        
    }
    
    private final Trie trie;
    StringBuilder path;
    
    public AutocompleteSystem2_3(String[] sentences, int[] times) {
        if (sentences == null || times == null || sentences.length != times.length) {
            throw new IllegalArgumentException("Not valid data");
        }
        this.trie = new Trie();
        int len = times.length;
        for (int i = 0; i < len; i++) {
            String word = sentences[i];
            int count = times[i];
            this.trie.insert(word, count);
        }
        path = new StringBuilder();
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            String word = path.toString();
//            path.delete(0, path.length()); // means deleted from [0, path.length())
            path.setLength(0);
            this.trie.insert(word);
            return new ArrayList<>();
        }
        path.append(c);
        return this.trie.search(c);
    }
    
}
}