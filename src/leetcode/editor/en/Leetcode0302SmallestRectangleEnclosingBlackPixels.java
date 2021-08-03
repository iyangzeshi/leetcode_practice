//An image is represented by a binary matrix with 0 as a white pixel and 1 as a 
//black pixel. The black pixels are connected, i.e., there is only one black regio
//n. Pixels are connected horizontally and vertically. Given the location (x, y) o
//f one of the black pixels, return the area of the smallest (axis-aligned) rectan
//gle that encloses all black pixels. 
//
// Example: 
//
// 
//Input:
//[
//  '0010',
//  '0110',
//  '0100'
//]
//and x = 0, y = 2
//
//Output: 6
// 
// Related Topics Binary Search 
// 👍 200 👎 50

package leetcode.editor.en;

// 2020-07-25 14:26:27
// Zeshi Yang
public class Leetcode0302SmallestRectangleEnclosingBlackPixels {

	// Java: smallest-rectangle-enclosing-black-pixels
	public static void main(String[] args) {
		Solution sol = new Leetcode0302SmallestRectangleEnclosingBlackPixels().new Solution();
		// TO TEST
		char[][] image = {{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}};
		int x = 0;
		int y = 2;
		int res = sol.minArea(image, x, y);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int minArea(char[][] image, int x, int y) {
        // corner case
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
            return 0;
        }
        // general case
        int top = topBorder(image, x);
        int bottom = bottomBorder(image, x);
        int left = leftBorder(image, y);
        int right = rightBorder(image, y);
        return (bottom - top + 1) * (right - left + 1);
    }

    private int topBorder(char[][] image, int x) {
        int start = 0;
        int end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            boolean hasOne = hasOne(image, mid, -1);
            if (hasOne) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private int bottomBorder(char[][] image, int x) {
        int start = x;
        int end = image.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            boolean hasOne = hasOne(image, mid, -1);
            if (hasOne) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    private int leftBorder(char[][] image, int y) {
        int start = 0;
        int end = y;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            boolean hasOne = hasOne(image, -1, mid);
            if (hasOne) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private int rightBorder(char[][] image, int y) {
        int start = y;
        int end = image[0].length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            boolean hasOne = hasOne(image, -1, mid);
            if (hasOne) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    /* if x is -1, check whether there is 1 in yth column; if y is -1, check whether there is 1 in xth row*/
    private boolean hasOne(char[][] image, int x, int y) {
        if (x == -1) {
            int len = image.length;
            for (char[] chars : image) {
                if (chars[y] == '1') {
                    return true;
                }
            }
        }

        if (y == -1) {
            int len = image[0].length;
            for (int i = 0; i < len; i++) {
                if (image[x][i] == '1') {
                    return true;
                }
            }
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
}