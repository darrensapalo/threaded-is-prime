import java.math.BigInteger;
import java.util.ArrayList;



public class MultiThread4 {
	
	BigInteger prime;
	
	public MultiThread4(BigInteger prime) {
		this.prime = prime;
	}
	
	public void threadIt() {
		System.out.println("Beginning to check from 2 to sqrt(n)...");
		
		// START OF: THIS IS FOR FOUR THREADS
		
		BigInteger div = prime.shiftRight(1).divide(new BigInteger("4"));
		
		BigInteger from1 = new BigInteger("2");
		BigInteger to1 = div;
		BigInteger from2 = to1.add(BigInteger.ONE);
		BigInteger to2 = div.multiply(new BigInteger("2"));
		BigInteger from3 = to2.add(BigInteger.ONE);
		BigInteger to3 = div.multiply(new BigInteger("3"));
		BigInteger from4 = to3.add(BigInteger.ONE);
		BigInteger to4 = div.multiply(new BigInteger("4"));
		
		PrimeThread p1 = new PrimeThread(prime, from1, to1);
		PrimeThread p2 = new PrimeThread(prime, from2, to2);
		PrimeThread p3 = new PrimeThread(prime, from3, to3);
		PrimeThread p4 = new PrimeThread(prime, from4, to4);
		
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		
		// END OF: THIS IS FOR FOUR THREADS
		
		ArrayList<PrimeThread> threads = new ArrayList<PrimeThread>();
		threads.add(p1);
		threads.add(p2);
		threads.add(p3);
		threads.add(p4);
		ThreadWaiter tw = new ThreadWaiter(threads, prime);
		
	}
}
