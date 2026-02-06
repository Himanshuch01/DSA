public class multiply_matrices 
  class GfG {
    public static void multiply(int A[][], int B[][], int C[][], int N) {
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                
                int sum = 0;
                
                for (int k = 0; k < N; k++) {
                    sum += A[i][k] * B[k][j];
                }
                
                C[i][j] = sum;
            }
        }
    }
}

  

