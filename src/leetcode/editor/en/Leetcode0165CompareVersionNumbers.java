//Compare two version numbers version1 and version2. 
//If version1 > version2 return 1; if version1 < version2 return -1;otherwise re
//turn 0. 
//
// You may assume that the version strings are non-empty and contain only digits
// and the . character. 
// The . character does not represent a decimal point and is used to separate nu
//mber sequences. 
// For instance, 2.5 is not "two and a half" or "half way to version three", it 
//is the fifth second-level revision of the second first-level revision. 
// You may assume the default revision number for each level of a version number
// to be 0. For example, version number 3.4 has a revision number of 3 and 4 for i
//ts first and second level revision number. Its third and fourth level revision n
//umber are both 0. 
//
// 
//
// Example 1: 
// 
//Input: version1 = "0.1", version2 = "1.1"
//Output: -1 
//
// Example 2: 
// 
//Input: version1 = "1.0.1", version2 = "1"
//Output: 1 
//
// Example 3: 
// 
//Input: version1 = "7.5.2.4", version2 = "7.5.3"
//Output: -1 
//
// Example 4: 
// 
//Input: version1 = "1.01", version2 = "1.001"
//Output: 0
//Explanation: Ignoring leading zeroes, both “01” and “001" represent the same n
//umber “1” 
//
// Example 5: 
// 
//Input: version1 = "1.0", version2 = "1.0.0"
//Output: 0
//Explanation: The first version number does not have a third level revision num
//ber, which means its third level revision number is default to "0" 
//
// 
//
// Note: 
// 
// Version strings are composed of numeric strings separated by dots . and this 
//numeric strings may have leading zeroes. 
// Version strings do not start or end with dots, and they will not be two conse
//cutive dots. 
// Related Topics String 
// 👍 644 👎 1581

package leetcode.editor.en;

// 2020-09-10 13:36:43
// Zeshi Yang
public class Leetcode0165CompareVersionNumbers{
    // Java: compare-version-numbers
    public static void main(String[] args) {
        Solution sol = new Leetcode0165CompareVersionNumbers().new Solution();
        // TO TEST
        String version1 = "0.1";
        String version2 = "1.1";
        int res = sol.compareVersion(version1, version2);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] ver1Arr = version1.split("\\.");
        String[] ver2Arr = version2.split("\\.");
        int len1 = ver1Arr.length;
        int len2 = ver2Arr.length;
        int[] nums1 = new int[len1];
        int[] nums2 = new int[len2];
        for (int i = 0; i < len1; i++) {
            nums1[i] = Integer.parseInt(ver1Arr[i]);
        }
        for (int i = 0; i < len2; i++) {
            nums2[i] = Integer.parseInt(ver2Arr[i]);
        }
        int len = Math.min(len1, len2);
        for (int i = 0; i < len; i++) {
            int num1 = nums1[i];
            int num2 = nums2[i];
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }
        if (len1 < len2) {
            for (int i = len1; i < len2; i++) {
                if (nums2[i] != 0) {
                    return -1;
                }
            }
        } else {
            for (int i = len2; i < len1; i++) {
                if (nums1[i] != 0) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}