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
// Jesse Yang
public class Leetcode0273IntegerToEnglishWords{
    // Java: integer-to-english-words
    public static void main(String[] args) {
        Solution sol = new Leetcode0273IntegerToEnglishWords().new Solution();
        // TO TEST
        int num = 1234567891;
        String res = sol.numberToWords(num);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
in English counting, every 3 digits will carry another units, (thousand, million, billion)
so we can transfer the question to the 3 digits number + units(thousand, million, billion)
in 3 digits number, there are several cases:
    <= 20, do the hashMap to mimic the operation
    >= 20, calculate the number in the ten place
    >= 100, calculate the number in the hundred place
 */
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
        int rest = num % 100;
        String res = "";
        if (hundred * rest != 0) { // hundred and rest are both 0
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
        int leftNum = num % 1000000000;
        int million = leftNum / 1000000;
        leftNum = leftNum % 1000000;
        int thousand = leftNum / 1000;
        leftNum = leftNum % 1000;
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

// Solution 1: Categorical discussions
/*
in English counting, every 3 digits will carry another units, (thousand, million, billion)
so we can transfer the question to the 3 digits number + units(thousand, million, billion)
in 3 digits number, there are several cases:
    <= 20, do the hashMap to mimic the operation
    >= 20, calculate the number in the ten place
    >= 100, calculate the number in the hundred place
 */
class Solution1 {
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
        if (hundred * rest != 0) { // hundred and rest are both 0
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

// Solution 2: Categorical discussions with recursion
/*
in English counting, every 3 digits will carry another units, (thousand, million, billion)
so we can transfer the question to the 3 digits number + units(thousand, million, billion)
in 3 digits number, there are several cases:
    <= 20, do the hashMap to mimic the operation
    >= 20, calculate the number in the ten place
    >= 100, calculate the number in the hundred place
 */
class Solution2 {
    
    private final String[] LESS_THAN_20 = {
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
    };
    
    private final String[] TENS = {
            "",
            "Ten",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };
    
    private final String[] ThousandsCarry = {
            "",
            "Thousand",
            "Million",
            "Billion"
    };
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        
        int i = 0;
        StringBuilder words = new StringBuilder();
        while (num > 0) {
            if (num % 1000 != 0) {
                words.insert(0, helper(num % 1000) + ThousandsCarry[i] + " ");
            }
            num /= 1000;
            i++;
        }
        
        return words.toString().trim();
    }
    
    private String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
    
}
}