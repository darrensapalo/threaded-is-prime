import java.math.BigInteger;
import java.util.Scanner;



public class Driver {
	public static void main (String args[]){
		Scanner s = new Scanner(System.in);
		
		// big prime: 472882049
		
		System.out.print("Please enter a number to check if it is prime: ");
		
		BigInteger prime = s.nextBigInteger();
		
		System.out.println("Beginning to check from 2 to sqrt(n)...");
		
		BigInteger from = new BigInteger("2");
		BigInteger to = prime.shiftRight(1);
		
		PrimeThread p = new PrimeThread(prime, from, to);
		p.start();
		
		while (p.isAlive())
		{
			try {
				Thread.sleep(1000);
				System.out.println("Still not finished...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(p.isPrime);
		System.out.println(p.factor);
		
	}
}
