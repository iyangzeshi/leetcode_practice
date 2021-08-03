
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
// Zeshi Yang
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
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if(n <= 0) return res;
        Map<Character, Character> map = new HashMap<>();
        map.put('1','1');
        map.put('0','0');
        map.put('9','6');
        map.put('6','9');
        map.put('8','8');
        
        helper(res,0, n-1, map, new char[n]);
        return res;
    }
    
    private void helper(List<String> res, int start, int end, Map<Character,Character> map, char[] arr){
        if(start > end){
            res.add(String.valueOf(arr));
            return;
        }
        
        if(start == end){
            for(char ch: map.keySet()){
                if(ch == '6' || ch == '9'){
                    continue;
                }
                arr[start] = ch;
                res.add(String.valueOf(arr));
            }
            return;
        }
        // 对每个 在map中的 ch 先在 整个数的左右两边 加上 对称的数 在往里call。 如果位置都被填满了 那么保存该数字。
        //然后在尝试下一个pair  顺序是先填外面 在填里面。
        for(char ch: map.keySet()){
            if(start == 0 && ch == '0'){
                continue;
            }
            arr[start] = ch;
            arr[end] = map.get(ch);
            helper(res, start+1, end-1, map,arr);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
