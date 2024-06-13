//There is a brick wall in front of you. The wall is rectangular and has several
// rows of bricks. The bricks have the same height but different width. You want t
//o draw a vertical line from the top to the bottom and cross the least bricks. 
//
// The brick wall is represented by a list of rows. Each row is a list of intege
//rs representing the width of each brick in this row from left to right. 
//
// If your line go through the edge of a brick, then the brick is not considered
// as crossed. You need to find out how to draw the line to cross the least bricks
// and return the number of crossed bricks. 
//
// You cannot draw a line just along one of the two vertical edges of the wall, 
//in which case the line will obviously cross no bricks. 
//
// 
//
// Example: 
//
// 
//Input: [[1,2,2,1],
//        [3,1,2],
//        [1,3,2],
//        [2,4],
//        [3,1,2],
//        [1,3,1,1]]
//
//Output: 2
//
//Explanation: 
//
// 
//
// 
//
// Note: 
//
// 
// The width sum of bricks in different rows are the same and won't exceed INT_M
//AX. 
// The number of bricks in each row is in range [1,10,000]. The height of wall i
//s in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000. 
// 
// Related Topics Hash Table 
// 👍 1010 👎 53

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2020-11-24 18:32:42
// Jesse Yang
public class Leetcode0554BrickWall{
    // Java: brick-wall
    public static void main(String[] args) {
        Solution sol = new Leetcode0554BrickWall().new Solution();
        // TO TEST
        List<List<Integer>> wall = new ArrayList<>();
        wall.add(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(2);
            add(1);
        }});
        wall.add(new ArrayList<Integer>() {{
            add(3);
            add(1);
            add(2);
        }});
        wall.add(new ArrayList<Integer>() {{
            add(1);
            add(3);
            add(2);
        }});
        wall.add(new ArrayList<Integer>() {{
            add(2);
            add(4);
        }});
        wall.add(new ArrayList<Integer>() {{
            add(3);
            add(1);
            add(2);
        }});
        wall.add(new ArrayList<Integer>() {{
            add(1);
            add(3);
            add(1);
            add(1);
        }});
        int res = sol.leastBricks(wall);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(m) = O(m)
class Solution {
    
    public int leastBricks(List<List<Integer>> wall) {
        //corner case
	    if (wall == null || wall.size() == 0 || wall.get(0) == null || wall.get(0).size() == 0) {
			return 0;
	    }
		
		// general case
	    Map<Integer, Integer> map = new HashMap<>();
		int max = 0; // the max number of cut lines
	    for (List<Integer> list : wall) {
			int len = 0;
			// every row has same length, bricks can not be put in the right edge
		    for (int i = 0; i < list.size() - 1; i++) {
				len += list.get(i);
				map.put(len, map.getOrDefault(len, 0) + 1);
				max = Math.max(map.get(len), max);
		    }
	    }
		return wall.size() - max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}