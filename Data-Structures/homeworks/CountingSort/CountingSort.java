/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
public class CountingSort {
	
	/*
	 * The sort method takes in an array with a values no less than 0. It than sorts the array from least to greatest. 
	 */
	public static int[] sort ( int [] A ) {
		/*
		 * Checks to see if the array is empty, if it is, an error is returned
		 */
		if (A==null || A.length <= 0) {
			throw new IllegalArgumentException("Array is Empty or Null");
		}
		else {
			int k=A[0];
			int n=A.length;
			/*
			 * Finds the max value in the array
			 */
			for(int i=1; i<n;i++){
	
				if(A[i]>k) {
					k=A[i];
				}
			}
			int[] C= new int[k+1];
			for(int i=0; i<k+1;i++) {
				C[i]=0;
			}
			for(int i=0; i<n;i++) {
				C[A[i]]=C[A[i]]+1;
			}
			
			for(int i=1; i<C.length;i++) {
				C[i]=C[i]+C[i-1];
			}
			
			int[] B= new int[n];
			for(int i=n-1; i>-1;i--) {
				B[C[A[i]]-1] = A[i];
				C[A[i]] = C[A[i]] - 1;
			}
			/*
			 * array B represents the correct sorted array
			 */
			return B;
		}
	}
	
}
