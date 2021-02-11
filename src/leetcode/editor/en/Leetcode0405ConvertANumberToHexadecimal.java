//
//Given an integer, write an algorithm to convert it to hexadecimal. For negativ
//e integer, two’s complement method is used.
// 
//
// Note:
// 
// All letters in hexadecimal (a-f) must be in lowercase. 
// The hexadecimal string must not contain extra leading 0s. If the number is ze
//ro, it is represented by a single zero character '0'; otherwise, the first chara
//cter in the hexadecimal string will not be the zero character. 
// The given number is guaranteed to fit within the range of a 32-bit signed int
//eger. 
// You must not use any method provided by the library which converts/formats th
//e number to hex directly. 
// 
// 
//
// Example 1:
// 
//Input:
//26
//
//Output:
//"1a"
// 
// 
//
// Example 2:
// 
//Input:
//-1
//
//Output:
//"ffffffff"
// 
// Related Topics Bit Manipulation 
// 👍 527 👎 124

package leetcode.editor.en;

// 2020-12-15 15:29:38
// Zeshi Yang
public class Leetcode0405ConvertANumberToHexadecimal{
    // Java: convert-a-number-to-hexadecimal
    public static void main(String[] args) {
        Solution sol = new Leetcode0405ConvertANumberToHexadecimal().new Solution();
        // TO TEST
        int num = -2;
        String res = sol.toHex(num);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public String toHex(int num) {
        char[] hexs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder answer = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        while (num != 0) {
            // answer.append(hexs[(16 + num % 16) % 16]);
            answer.append(hexs[num & 0xf]); // 或者num & 0xf, 0b1111 0b表示二进制, oxf, 0x表示16进制
            num = num >>> 4; // 不带符号为右移
        }
        return answer.reverse().toString();
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}