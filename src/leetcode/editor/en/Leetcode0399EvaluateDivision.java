//Equations are given in the format A / B = k, where A and B are variables repre
//sented as strings, and k is a real number (floating point number). Given some qu
//eries, return the answers. If the answer does not exist, return -1.0. 
//
// Example: 
//Given a / b = 2.0, b / c = 3.0. 
//queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
//return [6.0, 0.5, -1.0, 1.0, -1.0 ]. 
//
// The input is: vector<pair<string, string>> equations, vector<double>& values,
// vector<pair<string, string>> queries , where equations.size() == values.size(),
// and the values are positive. This represents the equations. Return vector<doubl
//e>. 
//
// According to the example above: 
//
// 
//equations = [ ["a", "b"], ["b", "c"] ],
//values = [2.0, 3.0],
//queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
//
// 
//
// The input is always valid. You may assume that evaluating the queries will re
//sult in no division by zero and there is no contradiction. 
// Related Topics Union Find Graph 
// 👍 2270 👎 175

package leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 2020-07-21 14:15:19
// Jesse Yang
public class Leetcode0399EvaluateDivision {
    
    // Java: evaluate-division
    public static void main(String[] args) {
        
        // TO TEST
        String[][] equations = {{"a","b"},{"c","d"}, {"e","f"}, {"graph","h"}};
        double[] values = {4.5,2.3,8.9,0.44};
        String[][] queries = {{"a","c"},{"d","f"},{"h","e"}, {"b","e"},{"d","h"},{"graph","f"}, {"c","graph"}};
        testData(equations, values, queries);
    }
    
    private static void testData(String[][] equationsArr, double[] values, String[][] queriesArr) {
        List<List<String>> equations = new ArrayList<>();
        for (String[] equation: equationsArr) {
            equations.add(Arrays.asList(equation));
        }
        List<List<String>> queries = new ArrayList<>();
        for(String[] query: queriesArr) {
            queries.add(Arrays.asList(query));
        }
        
        Solution sol = new Leetcode0399EvaluateDivision().new Solution();
        double[] res = sol.calcEquation(equations, values, queries);
        printDoubleArr(res);
    }
    
    private static void printDoubleArr(double[] nums) {
        for (double num :nums) {
            System.out.printf("%.2f ,", num);
        }
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {
        int qLen = queries.size();
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        
        double[] results = new double[qLen];
        int i = 0;
        for (List<String> query: queries) {
            String dividend = query.get(0);
            String divisor = query.get(1);
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                results[i] = -1.0;
            } else if (dividend.equals(divisor)) {
                results[i] = 1.0;
            } else {
                results[i] = dfsEvaluate(graph, dividend, divisor, new HashSet<>());
            }
            i++;
        }
        
        return results;
    }
    
    // build bi-directional graph, every node store its neighbor and value(this / neighbor)
    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0);
            String divisor = equation.get(1);
            double quotient = values[i];
            
            if (!graph.containsKey(dividend)) {
                graph.put(dividend, new HashMap<>());
            }
            if (!graph.containsKey(divisor)) {
                graph.put(divisor, new HashMap<>());
            }
            
            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1 / quotient);
        }
        return graph;
    }
    
    /*
    bottom up, post order DFS
    return value of cur / target if cur can reach to target
    else return - 1
     */
    private double dfsEvaluate(Map<String, Map<String, Double>> graph, String cur, String target,
            Set<String> visited) {
        // base case - success case
        if (cur.equals(target)) {
            return 1d;
        }
        // base case- failure case, has been visited
        if (visited.contains(cur)) {
            return -1d;
        }
        
        visited.add(cur);
        double quotient = -1.0;
        Map<String, Double> neighbors = graph.get(cur);
        for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
            String neighbor = pair.getKey();
            Double val = pair.getValue();
            double subQuotient = dfsEvaluate(graph, neighbor, target, visited);
            if (subQuotient > 0) {
                quotient = subQuotient * val;
                break;
            }
        }
        
        // visited.remove(cur);
        return quotient;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/*
面试的时候用
Solution 1 BFS, 好解释;
如果要改进的话，用Solution 3 Union Find，时间空间复杂度好
*/

/*
Solution 1和2的思路，建立图形，
Map<String, Map<String, Integer>> 每个点是一个key，然后value是他的neighbor和this.val / neighbor.val的值组成的Map
然后做BFS或者DFS搜索就行了
如果从start开始能搜索到target，就把中间的路径全部累乘起来
如果搜索不到，就return -1
 */
// Solution 1: BFS
// assuming equations size n, queries size m, T(n, m) = O(n * m), S(n, m) = O(n)
// 1 ms,击败了89.33% 的Java用户, 38 MB,击败了49.23% 的Java用户
class Solution1 {
    
    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {
        int qLen = queries.size();
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        
        double[] results = new double[qLen];
        int i = 0;
        for (List<String> query: queries) {
            String dividend = query.get(0);
            String divisor = query.get(1);
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                results[i] = -1.0;
            } else if (dividend.equals(divisor)) {
                results[i] = 1.0;
            } else {
                results[i] = bfsFindQuotient(dividend, divisor, graph);
            }
            i++;
        }
        
        return results;
    }
    
    /** build bi-directional graph, every node store its neighbor and value(this / neighbor) */
    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0);
            String divisor = equation.get(1);
            double quotient = values[i];
            
            if (!graph.containsKey(dividend)) {
                graph.put(dividend, new HashMap<>());
            }
            if (!graph.containsKey(divisor)) {
                graph.put(divisor, new HashMap<>());
            }
            
            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1 / quotient);
        }
        return graph;
    }
    
    private double bfsFindQuotient(String start, String target,
            Map<String, Map<String, Double>> graph) {
        Queue<String> nodeQueue = new ArrayDeque<>();
        Queue<Double> valQueue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        nodeQueue.add(start);
        valQueue.add(1d);
        visited.add(start);
        while (!nodeQueue.isEmpty()) {
            String cur = nodeQueue.poll();
            Double quotient = valQueue.poll();
            Map<String, Double> neighbors = graph.get(cur);
            for (Map.Entry<String, Double> pair: neighbors.entrySet()) {
                String next = pair.getKey();
                Double val = pair.getValue();
                if (next.equals(target)) {
                    return quotient * val;
                }
                if (visited.contains(next)) {
                    continue;
                }
                visited.add(next);
                nodeQueue.offer(next);
                valQueue.offer(quotient * val);
            }
        }
        return -1d;
    }
    
    
}

// Solution 2: DFS
// Solution 2_1: top down DFS
// assuming equations size n, queries size m, T(n, m) = O(n * m), S(n, m) = O(n)
// 1 ms,击败了89.33% 的Java用户, 38 MB,击败了49.23% 的Java用户
class Solution2_1 {
    
    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {
        int qLen = queries.size();
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        
        double[] results = new double[qLen];
        int i = 0;
        for (List<String> query: queries) {
            String dividend = query.get(0);
            String divisor = query.get(1);
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                results[i] = -1.0;
            } else if (dividend.equals(divisor)) {
                results[i] = 1.0;
            } else {
                double[] quotient = {-1d};
                dfsEvaluate(graph, dividend, divisor, 1, new HashSet<>(), quotient);
                results[i] = quotient[0];
            }
            i++;
        }
        
        return results;
    }
    
    // build bi-directional graph, every node store its neighbor and value(this / neighbor)
    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0);
            String divisor = equation.get(1);
            double quotient = values[i];
            
            if (!graph.containsKey(dividend)) {
                graph.put(dividend, new HashMap<>());
            }
            if (!graph.containsKey(divisor)) {
                graph.put(divisor, new HashMap<>());
            }
            
            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1 / quotient);
        }
        return graph;
    }
    
    /*
    top down DFS
    accProduct是root.val / cur.val的值
    return true if find target, then set quotient[0] to the right value
    */
    private boolean dfsEvaluate(Map<String, Map<String, Double>> graph,
            String cur, String target, double accProduct, Set<String> visited, double[] quotient) {
        // base case
        if (cur.equals(target)) {
            quotient[0] = accProduct;
            return true;
        }
        
        visited.add(cur);
        boolean res = false;
        Map<String, Double> neighbors = graph.get(cur);
        for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
            String neighbor = pair.getKey();
            Double val = pair.getValue();
            if (visited.contains(neighbor)) {
                continue;
            }
            res = dfsEvaluate(graph, neighbor, target, accProduct * val, visited, quotient);
            if (res) {
                break;
            }
        }
        // visited.remove(cur);
        return res;
    }
    
}

// Solution 2_2: bottom up DFS
// assuming equations size n, queries size m, T(n, m) = O(n * m), S(n, m) = O(n)
// 1 ms,击败了89.33% 的Java用户, 38 MB,击败了49.23% 的Java用户
class Solution2_2 {
    
    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {
        int qLen = queries.size();
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        
        double[] results = new double[qLen];
        int i = 0;
        for (List<String> query: queries) {
            String dividend = query.get(0);
            String divisor = query.get(1);
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                results[i] = -1.0;
            } else if (dividend.equals(divisor)) {
                results[i] = 1.0;
            } else {
                results[i] = dfsEvaluate(graph, dividend, divisor, new HashSet<>());
            }
            i++;
        }
        
        return results;
    }
    
    // build bi-directional graph, every node store its neighbor and value(this / neighbor)
    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0);
            String divisor = equation.get(1);
            double quotient = values[i];
            
            if (!graph.containsKey(dividend)) {
                graph.put(dividend, new HashMap<>());
            }
            if (!graph.containsKey(divisor)) {
                graph.put(divisor, new HashMap<>());
            }
            
            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1 / quotient);
        }
        return graph;
    }
    
    /*
    bottom up, post order DFS
    return value of cur / target if cur can reach to target
    else return - 1
     */
    private double dfsEvaluate(Map<String, Map<String, Double>> graph, String cur, String target,
            Set<String> visited) {
        // base case - success case
        if (cur.equals(target)) {
            return 1d;
        }
        // base case- failure case, has been visited
        if (visited.contains(cur)) {
            return -1d;
        }
        
        visited.add(cur);
        double quotient = -1.0;
        Map<String, Double> neighbors = graph.get(cur);
        for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
            String neighbor = pair.getKey();
            Double val = pair.getValue();
            double subQuotient = dfsEvaluate(graph, neighbor, target, visited);
            if (subQuotient > 0) {
                quotient = subQuotient * val;
                break;
            }
        }
        
        // visited.remove(cur);
        return quotient;
    }
    
}

// Solution 3: union find
// assuming equations size n, queries size m, T(n, m) = O((n + m) * lg(n)), S(n, m) = O(n)
// 0 ms,击败了100.00% 的Java用户, 38.1 MB,击败了37.47% 的Java用户
/*
union find，Vertex来表示build graph的时候，让当前点的node的val 设置为 this.val / parent.val的值
每个Vertex设置好name, parent, val, size
两个要计算的点
    不属于一个union的时候，return -1
    属于一个union，就把dividend.val / divider.val
 */
class Solution3 {
    
    class Vertex {
        
        String name;
        Vertex parent;
        double val; // this.value / parent.value
        int size;
        
        public Vertex(String name) {
            this.name = name;
            parent = this;
            val = 1.0;
            size = 1;
        }
        
    }
    
    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {
        int len = queries.size();
        double[] res = new double[len];
        Map<String, Vertex> map = new HashMap<>(); // 存放某个点——它的Vertex
        
        buildGraph(equations, values, map);
        
        int i = 0;
        for (List<String> query: queries) {
            String dividend = query.get(0);
            String divider = query.get(1);
            if (!map.containsKey(dividend) || !map.containsKey(divider)) {
                res[i] = -1;
            } else {
                Vertex v1 = map.get(dividend);
                Vertex v2 = map.get(divider);
                if (find(v1, v2)) {
                    res[i] = division(v1, v2);
                } else {
                    res[i] = -1;
                }
            }
            i++;
        }
        return res;
    }
    
    private void buildGraph(List<List<String>> equations, double[] values, Map<String, Vertex> map) {
        Iterator<List<String>> equation = equations.iterator();
        for (int i = 0; i < equations.size(); i++) {
            List<String> list = equation.next();
            
            String dividend = list.get(0);
            String divider = list.get(1);
            double value = values[i];
            if (!map.containsKey(dividend)) {
                map.put(dividend, new Vertex(dividend));
            }
            if (!map.containsKey(divider)) {
                map.put(divider, new Vertex(divider));
            }
            
            Vertex v1 = map.get(dividend);
            Vertex v2 = map.get(divider);
            if (!find(v1, v2)) {
                union(v1, v2, value);
            }
        }
    }
    
    // v1.val / v2.val = quotient
    private void union(Vertex v1, Vertex v2, double quotient) {
        Vertex root1 = findRoot(v1);
        Vertex root2 = findRoot(v2);
        if (root1.size < root2.size) { // root1 -> root2
            root1.parent = root2.parent;
            root2.size += root1.size;
            /*
            每个点都是child指向parent，也就是被除数指向除数
            两个union连起来之后，root1指向root2，
            ∴ quotient = v1.val * root1.val ÷ v2.val
            ∴ root1.val = 1 / v1.val * v2.val
             */
            root1.val = quotient * v2.val / v1.val;
        } else { // root2 -> root1
            root2.parent = root1.parent;
            root1.size += root2.size;
            /*
            同理
            quotient = v1.val ÷ root2.val ÷ v2.val
            ∴ root2.val = v1.val ÷ quotient ÷ v2.val
             */
            root2.val = v1.val / quotient / v2.val;
        }
    }
    
    private boolean find(Vertex v1, Vertex v2) {
        return findRoot(v1) == findRoot(v2);
    }
    
    private Vertex findRoot(Vertex v) {
        Vertex cur = v;
        double value = 1;
        while (cur != cur.parent) {
            cur.val *= cur.parent.val;
            value *= cur.val;
            cur.parent = cur.parent.parent;
            cur = cur.parent;
        }
        v.parent = cur;
        v.val = value;
        return cur;
    }
    
    private double division(Vertex dividend, Vertex divider) {
        return dividend.val / divider.val;
    }
    
}

}