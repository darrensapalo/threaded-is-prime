import java.math.BigInteger;



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
		
		while (p1.isAlive() && p2.isAlive())
		{
			try {
				Thread.sleep(1000);
				System.out.println("Still not finished...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(p1.isPrime && p2.isPrime)
			System.out.println("The number " + prime + " is A PRIME number!");
		else
			System.out.println("The number " + prime + " is NOT A PRIME number!");
		
		//System.out.println(p.factor);
		
	}
}
