package JavaCore;

import java.util.Scanner;

class NegativeSizeException extends Exception {
	public NegativeSizeException() {
		System.out.println("Negative Values not Allowed");
	}

}

public class qns1Assesment4 {

	public static void main(String[] args) throws NegativeSizeException {
		// TODO Auto-generated method stub
		int a[] = new int[5];
		Scanner sc = new Scanner(System.in);

		int x;
		try {
			for (int i = 0; i < 5; i++) {
				x = sc.nextInt();
				if (x >= 0) {
					a[i] = x;
				} else {
					NegativeSizeException ne = new NegativeSizeException();
					throw ne;
				}
			}
		} catch (NegativeSizeException e) {
			System.out.println("Error");
		}
		// for (int i = 0; i < 5; i++) {
		// System.out.println(a[i]);
		// }
	}

}
