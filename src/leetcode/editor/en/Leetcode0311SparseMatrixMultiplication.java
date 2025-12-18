/**
Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the 
result of mat1 x mat2. You may assume that multiplication is always possible. 

 
 Example 1: 
 
 
Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
Output: [[7,0,0],[-7,0,3]]
 

 Example 2: 

 
Input: mat1 = [[0]], mat2 = [[0]]
Output: [[0]]
 

 
 Constraints: 

 
 m == mat1.length 
 k == mat1[i].length == mat2.length 
 n == mat2[i].length 
 1 <= m, n, k <= 100 
 -100 <= mat1[i][j], mat2[i][j] <= 100 
 

 Related Topics Array Hash Table Matrix 👍 1120 👎 373

*/
package leetcode.editor.en;

import java.util.ArrayList;
import javafx.util.Pair;

// 2025-12-11 01:23:33
// Jesse Yang
public class Leetcode0311SparseMatrixMultiplication{
    // Java: sparse-matrix-multiplication
    public static void main(String[] args) {
        Solution sol = new Leetcode0311SparseMatrixMultiplication().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
Solution 2: Approach 2: List of Lists
res[i][j] = Σ mat1[i][k] * mat2[k][j], k = 0..n-1
T(l,m,n) = O(l*m*n)
S(l,m,n) = O(l*n)
 */
class Solution {
    
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;
        
        // Store the non-zero values of each matrix.
        ArrayList<ArrayList<Pair<Integer, Integer>>> A = compressMatrix(mat1);
        ArrayList<ArrayList<Pair<Integer, Integer>>> B = compressMatrix(mat2);
        
        int[][] ans = new int[m][n];
        
        for (int mat1Row = 0; mat1Row < m; ++mat1Row) {
            // Iterate on all current 'row' non-zero elements of mat1.
            for (Pair mat1Element : A.get(mat1Row)) {
                int element1 = (int) mat1Element.getKey();
                int mat1Col = (int) mat1Element.getValue();
                
                // Multiply and add all non-zero elements of mat2
                // where the row is equal to col of current element of mat1.
                for (Pair mat2Element : B.get(mat1Col)) {
                    int element2 = (int) mat2Element.getKey();
                    int mat2Col = (int) mat2Element.getValue();
                    ans[mat1Row][mat2Col] += element1 * element2;
                }
            }
        }
        
        return ans;
    }
    
    private ArrayList<ArrayList<Pair<Integer, Integer>>> compressMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        ArrayList<ArrayList<Pair<Integer, Integer>>> compressedMatrix = new ArrayList<>();
        
        for (int row = 0; row < rows; ++row) {
            ArrayList<Pair<Integer, Integer>> currRow = new ArrayList<>();
            for (int col = 0; col < cols; ++col) {
                if (matrix[row][col] != 0) {
                    currRow.add(new Pair(matrix[row][col], col));
                }
            }
            compressedMatrix.add(currRow);
        }
        return compressedMatrix;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/*
Solution 1: Naive Iteration
res[i][j] = Σ mat1[i][k] * mat2[k][j], k = 0..n-1
T(l,m,n) = O(l*m*n)
S(l,m,n) = O(l*n)
 */
class Solution1 {
    /*
    res[i][j] = Σ mat1[i][k] * mat2[k][j], k = 0..n-1
     */
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int l = mat1.length;
        int m = mat1[0].length;
        int n = mat2[0].length;
        
        // Product matrix will have 'l x n' size.
        int[][] res = new int[l][n];
        for (int i = 0; i < l; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < m; ++k) {
                    res[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        
        return res;
    }
}

}
