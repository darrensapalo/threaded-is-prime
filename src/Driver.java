import java.math.BigInteger;
import java.util.Scanner;



public class Driver {
	public static void main (String args[]){
		Scanner s = new Scanner(System.in);
		
		// big prime: 472882049
		
		System.out.print("Please enter a number to check if it is prime: ");
		
		BigInteger prime = s.nextBigInteger();
		
		new MultiThread1(prime).threadIt();
		// new MultiThread2(prime).threadIt();
		// new MultiThread3(prime).threadIt();
		// new MultiThread4(prime).threadIt();
		// new MultiThread5(prime).threadIt();
		
	}
}
