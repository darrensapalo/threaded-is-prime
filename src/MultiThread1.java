import java.math.BigInteger;
import java.util.ArrayList;



public class MultiThread1 {
	
	BigInteger prime;
	
	public MultiThread1(BigInteger prime) {
		this.prime = prime;
	}
	
	public void threadIt() {
		// big prime: 472882049
		
		System.out.println("Beginning to check from 2 to sqrt(n)...");
		
		BigInteger from = new BigInteger("2");
		BigInteger to = prime.shiftRight(1);
		
		PrimeThread p = new PrimeThread(prime, from, to);
		p.start();
		
		ArrayList<PrimeThread> threads = new ArrayList<PrimeThread>();
		threads.add(p);
		ThreadWaiter tw = new ThreadWaiter(threads, prime);
		
	}
}
