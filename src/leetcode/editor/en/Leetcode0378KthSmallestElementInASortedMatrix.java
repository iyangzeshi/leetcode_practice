//Given a n x n matrix where each of the rows and columns are sorted in ascendin
//g order, find the kth smallest element in the matrix. 
//
// 
//Note that it is the kth smallest element in the sorted order, not the kth dist
//inct element.
// 
//
// Example:
// 
//matrix = [
//   [ 1,  5,  9],
//   [10, 11, 13],
//   [12, 13, 15]
//],
//k = 8,
//
//return 13.
// 
// 
//
// Note: 
//You may assume k is always valid, 1 ≤ k ≤ n2. Related Topics Binary Search Hea
//p 
// 👍 2438 👎 128

package leetcode.editor.en;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
// 2020-07-26 12:04:44
// Zeshi Yang
public class Leetcode0378KthSmallestElementInASortedMatrix{
    // Java: kth-smallest-element-in-a-sorted-matrix
    public static void main(String[] args) {
        Solution sol = new Leetcode0378KthSmallestElementInASortedMatrix().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int kthSmallest(int[][] matrix, int k) {
        
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n - 1][n - 1];
        while (start < end) {
            
            int mid = start + (end - start) / 2;
            int[] smallLargePair = {matrix[0][0], matrix[n - 1][n - 1]};
            
            int count = this.countLessEqual(matrix, mid, smallLargePair);
            
            if (count == k) {
                return smallLargePair[0];
            }
            if (count < k) {
                start = smallLargePair[1]; // search higher
            } else {
                end = smallLargePair[0]; // search lower
            }
        }
        return start;
    }
    
    private int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        
        int count = 0;
        int n = matrix.length, row = n - 1, col = 0;
        
        while (row >= 0 && col < n) {
            
            if (matrix[row][col] > mid) {
                
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
                
            } else {
                
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }
        
        return count;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: minHeap + creating a boolean matrix visited to record whether it is in the heap
class Solution1 {
    
    public int kthSmallest(int[][] matrix, int k) {
        
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return Integer.MAX_VALUE;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row;
        int col;
        
        //BFS
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));
        
        boolean[][] visited = new boolean[rows][cols];
        
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;
        for (int i = 0; i < k - 1; i++) {
            Cell cur = minHeap.poll();
            row = cur.row;
            col = cur.col;
            
            if (row + 1 < rows && !visited[row + 1][col]) {
                Cell cellDown = new Cell(row + 1, col, matrix[row + 1][col]);
                visited[row + 1][col] = true;
                minHeap.offer(cellDown);
            }
            if (col + 1 < cols && !visited[row][col + 1]) {
                Cell cellRight = new Cell(row, col + 1, matrix[row][col + 1]);
                visited[row][col + 1] = true;
                minHeap.offer(cellRight);
            }
            
        }
        return minHeap.peek().value;
    }
    
    // S1: creating a boolean matrix visited to record the if it is in the heap
    public class Cell {
        
        int value;
        int row;
        int col;
        
        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
        
    }
    
}

// Solution 2: minHeap + check whether hashSet visited  contains i * cols + j
class Solution2 {
    
    public int kthSmallest(int[][] matrix, int k) {
        
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        if (k > rows * cols) {
            return -1;
        }
        
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));
        minHeap.add(new Node(0, 0, matrix[0][0]));
        
        HashSet<Integer> visited = new HashSet<>();
        int row = 0;
        int col = 0;
        visited.add(row * cols + col);
        
        for (int i = 0; i < k - 1; i++) {
            Node cur = minHeap.poll();
            row = cur.row;
            col = cur.col;
            if (row + 1 < rows && !visited.contains((row + 1) * cols + col)) {
                visited.add((row + 1) * cols + col);
                minHeap.offer(new Node(row + 1, col, matrix[row + 1][col]));
            }
            if (col < cols && !visited.contains(row * cols + col + 1)) {
                visited.add(row * cols + col + 1);
                minHeap.offer(new Node(row, col + 1, matrix[row][col + 1]));
            }
            
        }
        return minHeap.peek().value;
        
    }
    
    // S2: check visited by i * cols + j
    private class Node {
        
        int value;
        int row;
        int col;
        
        public Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
        
    }
    
}

// Solution 3: creating a heap to store the index of the matrix，简化S2
class Solution3 {
    
    public int kthSmallest(int[][] matrix, int k) {
        
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return Integer.MAX_VALUE;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int index;
        int row;
        int col;
        
        //BFS
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(n -> matrix[n / cols][n % cols]));
        minHeap.offer(0);
        
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        
        for (int i = 0; i < k - 1; i++) {
            index = minHeap.poll();
            row = index / cols;
            col = index % cols;
            
            if (col + 1 < cols && !visited.contains((row) * cols + (col + 1))) {
                visited.add((row) * cols + (col + 1));
                minHeap.offer((row) * cols + (col + 1));
            }
            
            if (row + 1 < rows && !visited.contains((row + 1) * cols + (col))) {
                visited.add((row + 1) * cols + (col));
                minHeap.offer((row + 1) * cols + (col));
            }
            
        }
        index = minHeap.poll();
        row = index / cols;
        col = index % cols;
        return matrix[row][col];
    }
    
}

// Solution 4: binary search (把matrix看成是一个sorted array就行了）
class Solution4 {
    
    // Solution 4: binary search
    public int kthSmallest(int[][] matrix, int k) {
        
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n - 1][n - 1];
        while (start < end) {
            
            int mid = start + (end - start) / 2;
            int[] smallLargePair = {matrix[0][0], matrix[n - 1][n - 1]};
            
            int count = this.countLessEqual(matrix, mid, smallLargePair);
            
            if (count == k) {
                return smallLargePair[0];
            }
            if (count < k) {
                start = smallLargePair[1]; // search higher
            } else {
                end = smallLargePair[0]; // search lower
            }
        }
        return start;
    }
    
    private int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        
        int count = 0;
        int n = matrix.length, row = n - 1, col = 0;
        
        while (row >= 0 && col < n) {
            
            if (matrix[row][col] > mid) {
                
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
                
            } else {
                
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }
        
        return count;
    }
    
}
}