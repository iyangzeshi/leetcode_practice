//A city's skyline is the outer contour of the silhouette formed by all the buil
//dings in that city when viewed from a distance. Now suppose you are given the lo
//cations and height of all the buildings as shown on a cityscape photo (Figure A)
//, write a program to output the skyline formed by these buildings collectively (
//Figure B). 
// 
//
// The geometric information of each building is represented by a triplet of int
//egers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right 
//edge of the ith building, respectively, and Hi is its height. It is guaranteed t
//hat 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all 
//buildings are perfect rectangles grounded on an absolutely flat surface at heigh
//t 0. 
//
// For instance, the dimensions of all buildings in Figure A are recorded as: [ 
//[2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] . 
//
// The output is a list of "key points" (red dots in Figure B) in the format of 
//[ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key poin
//t is the left endpoint of a horizontal line segment. Note that the last key poin
//t, where the rightmost building ends, is merely used to mark the termination of 
//the skyline, and always has zero height. Also, the ground in between any two adj
//acent buildings should be considered part of the skyline contour. 
//
// For instance, the skyline in Figure B should be represented as:[ [2 10], [3 1
//5], [7 12], [12 0], [15 10], [20 8], [24, 0] ]. 
//
// Notes: 
//
// 
// The number of buildings in any input list is guaranteed to be in the range [0
//, 10000]. 
// The input list is already sorted in ascending order by the left x position Li
//. 
// The output list must be sorted by the x position. 
// There must be no consecutive horizontal lines of equal height in the output s
//kyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not accepta
//ble; the three lines of height 5 should be merged into one in the final output a
//s such: [...[2 3], [4 5], [12 7], ...] 
// 
// Related Topics Divide and Conquer Heap Binary Indexed Tree Segment Tree Line 
//Sweep 
// 👍 2083 👎 115

package leetcode.editor.en;

import java.util.*;
// 2020-07-27 20:52:29
// Zeshi Yang
public class Leetcode0218TheSkylineProblem{
    // Java: the-skyline-problem
    public static void main(String[] args) {
        Solution sol = new Leetcode0218TheSkylineProblem().new Solution();
        // TO TEST
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        List<List<Integer>> res = sol.getSkyline(buildings);
        System.out.println(res);
    }


//leetcode submit region begin(Prohibit modification and deletion)
private enum Directions {
    left,
    right
}
class Solution {
    // Solution 2_2: maxMap O(nlog(n))
    private class Node implements Comparable<Node> {

        int col;
        Directions dir;
        int height;

        public Node(int index, int height, Directions dir) {
            this.col = index;
            this.dir = dir;
            this.height = height;
        }

        @Override
        public int compareTo(Node o) {
            if (this.col != o.col) {
                return this.col - o.col;
            } else if (this.dir != o.dir){
                return this.dir.compareTo(o.dir);
            } else if (this.dir == Directions.left) {
                return o.height - this.height;
            } else { // this.dir == Directions.right
                return this.height - o.height;
            }
        }
    }


    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (buildings == null || buildings.length == 0 ||
                buildings[0]== null || buildings[0].length == 0) {
            return res;
        }
        // general case
        List<Node> list = new ArrayList<>();
        for (int[] building: buildings) {
            list.add(new Node(building[0], building[2], Directions.left));
            list.add(new Node(building[1], building[2], Directions.right));
        }
        Collections.sort(list); // nlog(n))
        int maxHeight = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>(); // key: height, value: occurrences
        // pre-processing: 给TreeMap里面加入高度为0
        map.put(0,1);
        for (Node node: list) { // n个点
            int col = node.col;
            int height = node.height;

            if (node.dir == Directions.left) {
                map.put(height,map.getOrDefault(height, 0) + 1); // O(log(n))
//                if (height > maxHeight) {
//                    maxHeight = height; // maxHeight = map.lastKey();
//                    res.add(Arrays.asList(col, maxHeight));
//                }
            } else { // node.dir == Directions.right
                if (map.get(height) == 1) {
                    map.remove(height); // O(log(n))
                } else {
                    map.put(height, map.get(height) - 1);
                }

                maxHeight = map.lastKey();
//                if (height > maxHeight) {
//                    res.add(Arrays.asList(col, maxHeight));
//                }
            }
            if (height > maxHeight) { // 由上面两个注释部分合并
                maxHeight = map.lastKey();
                res.add(Arrays.asList(col, maxHeight));
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 2_2 */
// Solution 0: brute force traverse all points.
// O(n * m), m is the farthest border of the building
// O(n * m), m is the farthest border of the building， array's length out of bounds
class Solution0 {
    // Solution 0: 遍历所有buildings，每个位置上面画上最高的点 O(n^2)
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (buildings == null || buildings.length == 0 ||
                buildings[0] == null || buildings[0].length == 0) {
            return res;
        }
        // general case
        int rightBorder = 0;
        for (int[] building: buildings) { // O(n)
            rightBorder = Math.max(rightBorder, building[1] + 1);
        }
        int[] skyline = new int[rightBorder];
        for (int[] building: buildings) { // O(n^2)
            int left = building[0];
            int right = building[1] - 1;
            int height = building[2];
            for (int i = left; i <= right; i++) {
                skyline[i] = Math.max(skyline[i], height);
            }
        }
        int prev = 0;
        for (int i = 0; i < rightBorder; i++) { // O(n)
            if (prev != skyline[i]) {
                res.add(Arrays.asList(i, skyline[i]));
                prev = skyline[i];
            }
        }
        return res;
    }
}

// 1.	每从interval进来一个值：
//      a)	如果比当前pool里面的最大值大，就更新max，把max放到pool里面，然后画新的轮廓(index, max)
//      b)	如果不比最大值大，把这个值放到pool里面
//2.	每从interval出去一个值：
//      a)	如果出去的值就是当前的max值，就把max从pool里面删除，然后更新max（pool里面新的最大值），画新的轮廓(index, max)
//      b)	如果比当前pool里面的max最大值小，把这个值从pool里面拿出来。
// 坐标小的优先⟹left优先⟹{(两个都是left时：高的优先; 两个都是right时，低的优先)}
// Solution 1: maxHeap O(n^2)
class Solution1 {
    // Solution 1: maxHeap O(n^2)
    private class Node implements Comparable<Node> {

        int col;
        Directions dir;
        int height;

        public Node(int index, int height, Directions dir) {
            this.col = index;
            this.dir = dir;
            this.height = height;
        }

        @Override
        public int compareTo(Node o) {
            if (this.col != o.col) {
                return this.col - o.col;
            } else if (this.dir != o.dir){
                return this.dir.compareTo(o.dir);
            } else if (this.dir == Directions.left) {
                return o.height - this.height;
            } else { // this.dir == Directions.right
                return this.height - o.height;
            }
        }
    }


    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (buildings == null || buildings.length == 0 ||
                buildings[0] == null || buildings[0].length == 0) {
            return res;
        }
        // general case
        List<Node> list = new ArrayList<>();
        for (int[] building: buildings) {
            list.add(new Node(building[0], building[2], Directions.left));
            list.add(new Node(building[1], building[2], Directions.right));
        }
        Collections.sort(list); // nlog(n))
        int maxHeight = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.add(0);
        for (Node node: list) { // n个点
            int col = node.col;
            int height = node.height;

            if (node.dir == Directions.left) {
                maxHeap.offer(height); // O(log(n))
//                if (height > maxHeight) {
//                    maxHeight = maxHeap.peek();
//                    res.add(Arrays.asList(col, maxHeight));
//                }
            } else { // node.dir == Directions.right
                maxHeap.remove(height); // O(n)
                maxHeight = maxHeap.peek();
//                if (height > maxHeight) {
//                    res.add(Arrays.asList(col, maxHeight));
//                }
            }
            if (height > maxHeight) { // 由上面两个注释部分合并
                maxHeight = maxHeap.peek();
                res.add(Arrays.asList(col, maxHeight));
            }
        }
        return res;
    }
}

// Solution 2_1: TreeSet O(nlog(n))
class Solution2_1 {

    class IntegerComparator implements Comparator<Integer> {

        List<Node> list;

        public IntegerComparator(List<Node> list) {
            this.list = list;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if (list.get(o1).height != list.get(o2).height) {
                return list.get(o1).height - list.get(o2).height; // height小的放前面
            } else {
                return o1 - o2;
            }
        }
    }
    
    private class Node implements Comparable<Node> {
        int num;
        int col;
        int height;
        Directions dir;

        public Node(int num, int col, int height, Directions dir) {
            this.num = num;
            this.col = col;
            this.height = height;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node o) {
            if (this.col != o.col) {
                return this.col - o.col;
            } else if (this.dir != o.dir){
                return this.dir.compareTo(o.dir);
            } else if (this.dir == Directions.left) {
                return o.height - this.height;
            } else { // this.dir == Directions.right
                return this.height - o.height;
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (buildings == null || buildings.length == 0 ||
                buildings[0] == null || buildings[0].length == 0) {
            return res;
        }
        // general case
        int len = buildings.length;
        List<Node> list = new ArrayList<>();
        List<Node> leftPoints = new ArrayList<>();
//        leftPoints.add(new Node(0, 0, 0, Directions.left));
        for (int i = 0; i < len; i++) {
            list.add(new Node(i, buildings[i][0], buildings[i][2], Directions.left));
            list.add(new Node(i, buildings[i][1], buildings[i][2], Directions.right));
            leftPoints.add(new Node(i, buildings[i][0], buildings[i][2], Directions.left));
        }
        Collections.sort(list); // nlog(n))
        int maxHeight = 0;
        TreeSet<Integer> set = new TreeSet<>(new IntegerComparator(leftPoints));
//        set.add(0);
        for (Node node: list) { // n个点
            int num = node.num;
            int col = node.col;
            int height = node.height;

            if (node.dir == Directions.left) {
                set.add(num); // O(log(n))
                if (height > maxHeight) {
                    maxHeight = height;
                    res.add(Arrays.asList(col, maxHeight));
                }
            } else { // node.dir == Directions.right
                set.remove(num); // O(log(n))
                if (set.size() == 0) {
                    maxHeight = 0;
                } else {
                    maxHeight = leftPoints.get(set.last()).height;
                }
//                maxHeight = leftPoints.get(set.last()).height;
                if (height > maxHeight) {
                    res.add(Arrays.asList(col, maxHeight));
                }
            }
        }
        return res;
    }
}

// Solution 2_2: TreeMap O(nlog(n))
class Solution2_2 {

    private class Node implements Comparable<Node> {

        int col;
        Directions dir;
        int height;

        public Node(int index, int height, Directions dir) {
            this.col = index;
            this.dir = dir;
            this.height = height;
        }

        @Override
        public int compareTo(Node o) {
            if (this.col != o.col) {
                return this.col - o.col;
            } else if (this.dir != o.dir){
                return this.dir.compareTo(o.dir);
            } else if (this.dir == Directions.left) {
                return o.height - this.height;
            } else { // this.dir == Directions.right
                return this.height - o.height;
            }
        }
    }


    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (buildings == null || buildings.length == 0 ||
                buildings[0]== null || buildings[0].length == 0) {
            return res;
        }
        // general case
        List<Node> list = new ArrayList<>();
        for (int[] building: buildings) {
            list.add(new Node(building[0], building[2], Directions.left));
            list.add(new Node(building[1], building[2], Directions.right));
        }
        Collections.sort(list); // nlog(n))
        int maxHeight = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>(); // key: height, value: occurrences
        // pre-processing: 给TreeMap里面加入高度为0
        map.put(0,1);
        for (Node node: list) { // n个点
            int col = node.col;
            int height = node.height;

            if (node.dir == Directions.left) {
                map.put(height,map.getOrDefault(height, 0) + 1); // O(log(n))
//                if (height > maxHeight) {
//                    maxHeight = height; // maxHeight = map.lastKey();
//                    res.add(Arrays.asList(col, maxHeight));
//                }
            } else { // node.dir == Directions.right
                if (map.get(height) == 1) {
                    map.remove(height); // O(log(n))
                } else {
                    map.put(height, map.get(height) - 1);
                }

                maxHeight = map.lastKey();
//                if (height > maxHeight) {
//                    res.add(Arrays.asList(col, maxHeight));
//                }
            }
            if (height > maxHeight) { // 由上面两个注释部分合并
                maxHeight = map.lastKey();
                res.add(Arrays.asList(col, maxHeight));
            }
        }
        return res;
    }
}

}