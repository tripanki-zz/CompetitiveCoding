/**
 * Created by Ankit Tripathi.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 *
 * @see https://oj.leetcode.com/problems/search-a-2d-matrix/
 */
public class MatrixSearch {

	public static void main(String[] args) {
		int mat[][] = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		int target = 21;
		
		System.out.println(searchMatrix(mat, target));
	}

	public static boolean searchMatrix(int[][] matrix, int target) {
		int row = matrix.length;		//	prints 3
		int col = matrix[0].length;		//	prints 4
		int i, j, mid, x, y;
		
		i = 0;
		j = (row * col) - 1; 
		while (i <= j) {
			mid = i + (j - i) / 2;
			x = mid / col;
			y = mid % col;
			
			if (matrix[x][y] == target) {
				return true;
			} else if (matrix[x][y] > target) {
				if (y == 0) {
					if (x == 0) {
						return false;
					}
					x = x - 1;
					y = col - 1;
				} else {
					y = y - 1;
				}
				j = x * col + y;
			} else {
				if (y == col - 1) {
					if (x == row - 1) {
						return false;
					}
					x = x + 1;
					y = 0;
				} else {
					y = y + 1;
				}
				i = x * col + y;
			}
		}
		return false;
    }
}
