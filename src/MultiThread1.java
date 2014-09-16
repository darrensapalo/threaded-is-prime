import java.math.BigInteger;



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
