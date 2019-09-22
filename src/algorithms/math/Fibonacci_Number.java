package algorithms.math;

/**
 * @author Yangxiao Wang on 9/19/2019
 */
public class Fibonacci_Number {
	
	public int fib(int N) {
		if (N == 0) return 0;
		if (N == 1) return 1;
		int pre = 0, cur = 1;
		for (int i = 2; i <= N; i++) {
			int res = pre + cur;
			pre = cur;
			cur = res;
		}
		
		return cur;
	}
	
	public int fib_math(int N) {
		double goldenRatio = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(Math.pow(goldenRatio, N) / Math.sqrt(5));
	}
	
	class Solution_Matrix {
		int fib(int N) {
			if (N <= 1) {
				return N;
			}
			int[][] A = new int[][]{{1, 1}, {1, 0}};
			matrixPower(A, N - 1);
			
			return A[0][0];
		}
		
		void matrixPower(int[][] A, int N) {
			if (N <= 1) {
				return;
			}
			matrixPower(A, N / 2);
			multiply(A, A);
			
			int[][] B = new int[][]{{1, 1}, {1, 0}};
			if (N % 2 != 0) {
				multiply(A, B);
			}
		}
		
		void multiply(int[][] A, int[][] B) {
			int x = A[0][0] * B[0][0] + A[0][1] * B[1][0];
			int y = A[0][0] * B[0][1] + A[0][1] * B[1][1];
			int z = A[1][0] * B[0][0] + A[1][1] * B[1][0];
			int w = A[1][0] * B[0][1] + A[1][1] * B[1][1];
			
			A[0][0] = x;
			A[0][1] = y;
			A[1][0] = z;
			A[1][1] = w;
		}
	}
}
