//Given a list accounts, each element accounts[i] is a list of strings, where th
//e first element accounts[i][0] is a name, and the rest of the elements are email
//s representing emails of the account. 
//
// Now, we would like to merge these accounts. Two accounts definitely belong to
// the same person if there is some email that is common to both accounts. Note th
//at even if two accounts have the same name, they may belong to different people 
//as people could have the same name. A person can have any number of accounts ini
//tially, but all of their accounts definitely have the same name. 
//
// After merging the accounts, return the accounts in the following format: the 
//first element of each account is the name, and the rest of the elements are emai
//ls in sorted order. The accounts themselves can be returned in any order. 
//
// Example 1: 
// 
//Input: 
//accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnn
//ybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Ma
//ry", "mary@mail.com"]]
//Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.
//com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
//Explanation: 
//The first and third John's are the same person as they have the common email "
//johnsmith@mail.com".
//The second John and Mary are different people as none of their email addresses
// are used by other accounts.
//We could return these lists in any order, for example the answer [['Mary', 'ma
//ry@mail.com'], ['John', 'johnnybravo@mail.com'], 
//['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] wo
//uld still be accepted.
// 
// 
//
// Note:
// The length of accounts will be in the range [1, 1000]. 
// The length of accounts[i] will be in the range [1, 10]. 
// The length of accounts[i][j] will be in the range [1, 30]. 
// Related Topics Depth-first Search Union Find 
// 👍 1456 👎 296

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

// 2020-07-21 14:53:21
// Zeshi Yang
public class Leetcode0721AccountsMerge {

	// Java: accounts-merge
	public static void main(String[] args) {
		Solution sol = new Leetcode0721AccountsMerge().new Solution();
		// TO TEST
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));
        List<List<String>> res = sol.accountsMerge(accounts);
        for (List<String> list : res) {
            System.out.println(list);
        }
	}
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null) {
            throw new RuntimeException("no accounts");
        }
        // email-email bi-directional graph
        Map<String, List<String>> graph = buildGraph(accounts);
        Set<String> visited = new HashSet<>(); // store the visited emails.
        List<List<String>> res = new ArrayList<>();
        for (List<String> account : accounts) {
            ListIterator<String> emailIterator = account.listIterator();
            String name = emailIterator.next();
            String firstEmail = emailIterator.next();
            LinkedList<String> emails = new LinkedList<>();
            dfs(firstEmail, visited, graph, emails);
            if (!emails.isEmpty()) {
                Collections.sort(emails);
                emails.addFirst(name);
                res.add(emails);
            }
        }
        return res;
    }
    
    private void dfs(String vertex, Set<String> visited, Map<String, List<String>> graph,
            List<String> emails) {
        // base case
        if (visited.contains(vertex)) {
            return;
        }
        // general case
        emails.add(vertex);
        visited.add(vertex);
        
        for (String neighbor : graph.get(vertex)) {
            dfs(neighbor, visited, graph, emails);
        }
    }
    
    /*
    双向存图
    Time complexity = O(sum(a_i)), a_i is the length of the name i
     */
    private Map<String, List<String>> buildGraph(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> list : accounts) {
            ListIterator<String> listIterator = list.listIterator();
            String prev = null;
            listIterator.next();// 名字不需要存图
            while (listIterator.hasNext()) {
                String cur = listIterator.next();
                List<String> neighbors = graph.computeIfAbsent(cur, k -> new ArrayList<>());
                if (prev != null) {
                    neighbors.add(prev);
                    graph.get(prev).add(cur);
                }
                prev = cur;
            }
        }
        return graph;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: dfs,相邻的email之间建立双向图。T(m,n) = O(mn), m是accounts的个数，n是一个account里面的最大长度
// 28 ms,击败了90.01% 的Java用户, 44.5 MB,击败了49.74% 的Java用户
/*
相邻的email之间建立双向图。设置Set<String> visited存已经访问过的email
每次遇到一个新的name的List的时候，
    如果这个list的第一个email没有访问过，就dfs把这个email的所有通路走一遍，走到底，放到这个名字里面
    如果已经访问过了，这个account就可以跳过去了
 */
class Solution1 {
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null) {
            throw new RuntimeException("no accounts");
        }
        // email-email bi-directional graph
        Map<String, List<String>> graph = buildGraph(accounts);
        Set<String> visited = new HashSet<>(); // store the visited emails.
        List<List<String>> res = new ArrayList<>();
        for (List<String> account : accounts) {
            ListIterator<String> emailIterator = account.listIterator();
            String name = emailIterator.next();
            String firstEmail = emailIterator.next();
            LinkedList<String> emails = new LinkedList<>();
            dfs(firstEmail, visited, graph, emails);
            if (!emails.isEmpty()) {
                Collections.sort(emails);
                emails.addFirst(name);
                res.add(emails);
            }
        }
        return res;
    }
    
    private void dfs(String vertex, Set<String> visited, Map<String, List<String>> graph,
            List<String> emails) {
        // base case
        if (visited.contains(vertex)) {
            return;
        }
        // general case
        emails.add(vertex);
        visited.add(vertex);
        
        for (String neighbor : graph.get(vertex)) {
            dfs(neighbor, visited, graph, emails);
        }
    }
    
    /*
    双向存图
    Time complexity = O(sum(a_i)), a_i is the length of the name i
     */
    private Map<String, List<String>> buildGraph(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> list : accounts) {
            ListIterator<String> listIterator = list.listIterator();
            String prev = null;
            listIterator.next();// 名字不需要存图
            while (listIterator.hasNext()) {
                String cur = listIterator.next();
                List<String> neighbors = graph.computeIfAbsent(cur, k -> new ArrayList<>());
                if (prev != null) {
                    neighbors.add(prev);
                    graph.get(prev).add(cur);
                }
                prev = cur;
            }
        }
        return graph;
    }
    
}

// Solution 2: Union-Find
// 29 ms,击败了87.73% 的Java用户, 45 MB,击败了44.28% 的Java用户
/*
HashMap<String, String> emailToName
HashMap<Integer, HashSet<String>> userToEmailMap
建立union find:
    union find,里面存的是user的编号
    每次遇到一个email的时候，如果出现过的话，就合并user
    如果是新的email的话，就不管。
遍历accounts，
    遇到每一个account，找到这个name的union的root，
    把这个name的email都加到这个HashMap的root的value里面
 */
class Solution2 {
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null) {
            throw new RuntimeException("no accounts");
        }
        
        int len = accounts.size();
        UnionFind uf = new UnionFind(len);
        
        Map<String, Integer> emailToUserMap = new HashMap<>(); // email to user#
        unionAccounts(uf, accounts, emailToUserMap);
        
        Map<Integer, HashSet<String>> userToEmail = new HashMap<>();
        buildAccounts(uf, accounts, userToEmail);
        
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, HashSet<String>> entry : userToEmail.entrySet()) {
            LinkedList<String> list = new LinkedList<>(entry.getValue());
            Collections.sort(list);
            list.addFirst(accounts.get(entry.getKey()).get(0));
            res.add(list);
        }
        return res;
    }
    
    private void unionAccounts(UnionFind uf, List<List<String>> accounts,
            Map<String, Integer> map) {
        int len = accounts.size();
        Iterator<List<String>> accountsIterator = accounts.iterator();
        for (int i = 0; i < len; i++) {
            List<String> account = accountsIterator.next();
            Iterator<String> emailIterator = account.iterator();
            emailIterator.next();
            while (emailIterator.hasNext()) {
                String email = emailIterator.next();
                Integer user = map.get(email);
                if (user == null) {
                    map.put(email, i);
                } else {
                    uf.union(user, i);
                }
            }
        }
    }
    
    private void buildAccounts(UnionFind uf, List<List<String>> accounts,
            Map<Integer, HashSet<String>> map) {
        int len = accounts.size();
        
        Iterator<List<String>> accountsIterator = accounts.iterator();
        for (int i = 0; i < len; i++) {
            int root = uf.getRoot(i);
            if (!map.containsKey(root)) {
                map.put(root, new HashSet<>());
            }
            List<String> account = accountsIterator.next();
            Iterator<String> emails = account.iterator();
            emails.next();
            while (emails.hasNext()) {
                map.get(root).add(emails.next());
            }
        }
    }
    
    class UnionFind {
        
        int[] parent;
        int[] size;
        
        public UnionFind(int capacity) {
            this.parent = new int[capacity];
            this.size = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                this.parent[i] = i;
                this.size[i] = 1;
            }
        }
        
        public void union(int user1, int user2) {
            int root1 = getRoot(user1);
            int root2 = getRoot(user2);
            
            if (size[root1] < size[root2]) {
                size[root2] += size[root1];
                parent[root1] = root2;
            } else {
                size[root1] += size[root2];
                parent[root2] = root1;
            }
        }
        
        public boolean find(int user1, int user2) {
            return getRoot(user1) == getRoot(user2);
        }
        
        public int getRoot(int user) {
            int cur = user;
            while (parent[cur] != cur) {
                parent[cur] = parent[parent[cur]];
                cur = parent[cur];
            }
            parent[user] = cur;
            return cur;
        }
    }
}

}