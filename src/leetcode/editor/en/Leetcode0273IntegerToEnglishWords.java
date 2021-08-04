//Convert a non-negative integer to its english words representation. Given inpu
//t is guaranteed to be less than 231 - 1. 
//
// Example 1: 
//
// 
//Input: 123
//Output: "One Hundred Twenty Three"
// 
//
// Example 2: 
//
// 
//Input: 12345
//Output: "Twelve Thousand Three Hundred Forty Five" 
//
// Example 3: 
//
// 
//Input: 1234567
//Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven
//"
// 
//
// Example 4: 
//
// 
//Input: 1234567891
//Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven 
//Thousand Eight Hundred Ninety One"
// 
// Related Topics Math String 
// 👍 1130 👎 2959

package leetcode.editor.en;

// 2020-09-10 14:52:37
// Zeshi Yang
public class Leetcode0273IntegerToEnglishWords{
    // Java: integer-to-english-words
    public static void main(String[] args) {
        Solution sol = new Leetcode0273IntegerToEnglishWords().new Solution();
        // TO TEST
        System.out.println(Integer.MAX_VALUE);
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String oneDigit(int num) {
        switch(num) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default: return "";
        }
    }

    public String twoDigitsLessThan20(int num) {
        switch(num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
            default: return "";
        }
    }

    public String twoDigitsDivisibleBy10(int num) {
        switch(num) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
            default: return "";
        }
    }

    public String twoDigits(int num) {
        if (num == 0) {
            return "";
        } else if (num < 10) {
            return oneDigit(num);
        } else if (num < 20) {
            return twoDigitsLessThan20(num);
        } else {
            int tenner = num / 10;
            int rest = num - tenner * 10;
            if (rest != 0) {
                return twoDigitsDivisibleBy10(tenner) + " " + oneDigit(rest);
            } else {
                return twoDigitsDivisibleBy10(tenner);
            }
        }
    }

    public String threeDigits(int num) {
        int hundred = num / 100;
        int rest = num - hundred * 100;
        String res = "";
        if (hundred * rest != 0) {
            res = oneDigit(hundred) + " Hundred " + twoDigits(rest);
        } else if ((hundred == 0) && (rest != 0)) {
            res = twoDigits(rest);
        } else if ((hundred != 0) && (rest == 0)) {
            res = oneDigit(hundred) + " Hundred";
        }
        return res;
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        int billion = num / 1000000000;
        int leftNum = num - billion * 1000000000;
        int million = leftNum / 1000000;
        leftNum = leftNum - million * 1000000;
        int thousand = leftNum / 1000;
        leftNum = leftNum - thousand * 1000;
        int rest = leftNum;

        String result = "";
        if (billion != 0) {
            result = threeDigits(billion) + " Billion";
        }
        if (million != 0) {
            if (!result.isEmpty()) {
                result += " ";
            }
            result += threeDigits(million) + " Million";
        }
        if (thousand != 0) {
            if (!result.isEmpty()) {
                result += " ";
            }
            result += threeDigits(thousand) + " Thousand";
        }
        if (rest != 0) {
            if (!result.isEmpty()) {
                result += " ";
            }
            result += threeDigits(rest);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}