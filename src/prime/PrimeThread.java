package prime;
import java.math.BigInteger;

/**
 * A worker thread that checks if a number is prime or not.
 * At the end of processing, the public attribute isPrime is set to true of false.
 *
 */
public class PrimeThread extends Thread {
	public int threadNumber;
	private BigInteger start;
	private BigInteger end;
	private BigInteger input;
	public boolean isPrime;
	
	public PrimeThread(int threadNumber, BigInteger start, BigInteger end, BigInteger input) {
		this.threadNumber = threadNumber;
		this.start = start;
		this.end = end;
		this.input = input;
		System.out.println("Created thread #" + threadNumber + ".");
	}

	public void run() {
		isPrime = isPrime(input);
	}

	public boolean isPrime(BigInteger n) {
		for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(BigInteger.ONE)) {
			if (n.mod(i).equals(BigInteger.ZERO)) {
				System.out.println("Factor: " + i);
				return false;
			}
		}

		return true;
	}
}
