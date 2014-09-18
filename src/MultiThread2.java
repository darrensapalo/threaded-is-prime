import java.math.BigInteger;
import java.util.ArrayList;



public class MultiThread2 {
	
	BigInteger prime;
	
	public MultiThread2(BigInteger prime) {
		this.prime = prime;
	}
	
	public void threadIt() {
		System.out.println("Beginning to check from 2 to sqrt(n)...");
		
		// START OF: THIS IS FOR TWO THREADS
		
		BigInteger div = prime.shiftRight(1).divide(new BigInteger("2"));
		
		BigInteger from1 = new BigInteger("2");
		BigInteger to1 = div;
		BigInteger from2 = to1.add(BigInteger.ONE);
		BigInteger to2 = div.multiply(new BigInteger("2"));
		
		PrimeThread p1 = new PrimeThread(prime, from1, to1);
		PrimeThread p2 = new PrimeThread(prime, from2, to2);
		
		p1.start();
		p2.start();
		
		// END OF: THIS IS FOR TWO THREADS
		ArrayList<PrimeThread> threads = new ArrayList<PrimeThread>();
		threads.add(p1);
		threads.add(p2);
		ThreadWaiter tw = new ThreadWaiter(threads, prime);
		
		//System.out.println(p.factor);
		
	}
}
