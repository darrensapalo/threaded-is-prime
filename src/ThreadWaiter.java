import java.math.BigInteger;
import java.util.ArrayList;


public class ThreadWaiter {
	private ArrayList<PrimeThread> threads;

	public ThreadWaiter(ArrayList<PrimeThread> threads, BigInteger prime)
	{
		this.threads = threads;
		
		boolean alive = true;
		int secondsElapsed = 0;
		do
		{
			for (PrimeThread p : threads) 
			{
				alive = alive && p.isAlive();
			}
			
			if (alive == false)
				break;
			
			try {
				Thread.sleep(1000);
				System.out.println("Current runtime: " + (++secondsElapsed)+"s...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while (alive == true);
		
		boolean isPrime = true;
		for (PrimeThread p : threads) 
		{
			isPrime = isPrime && p.isPrime;
		}
		
		System.out.println("The number " + prime + " is " + ((isPrime) ? "" : "not ") + "a prime number!");
	}
}
