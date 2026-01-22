
//A strobogrammatic number is a number that looks the same when rotated 180 degr
//ees (looked at upside down). 
//
// Find all strobogrammatic numbers that are of length = n. 
//
// Example: 
//
// 
//Input:  n = 2
//Output: ["11","69","88","96"]
// 
// Related Topics Math Recursion 
// 👍 482 👎 135

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2020-11-09 14:13:50
// Jesse Yang
public class Leetcode0247StrobogrammaticNumberIi{
    // Java: strobogrammatic-number-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0247StrobogrammaticNumberIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    //time 5^(n/2)     space O 4 = O 1
    Map<Character, Character> map; // rotate map
    
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        map = new HashMap<>();
        map.put('1', '1');
        map.put('0', '0');
        map.put('9', '6');
        map.put('6', '9');
        map.put('8', '8');
        
        dfs(0, n - 1, new char[n], res);
        return res;
    }
    
    private void dfs(int left, int right, char[] arr, List<String> res) {
        if (left > right) {
            res.add(String.valueOf(arr));
            return;
        }
        
        // 对每个 在map中的 ch 先在 整个数的左右两边 加上 对称的数 在往里call。 如果位置都被填满了 那么保存该数字。
        //然后在尝试下一个pair  顺序是先填外面 在填里面。
        for (char ch : map.keySet()) {
            if (arr.length > 1 && left == 0 && ch == '0') {
                continue;
            }
            if (left == right && map.get(ch) != ch) {
                continue;
            }
            arr[left] = ch;
            arr[right] = map.get(ch);
            dfs(left + 1, right - 1, arr, res);
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
