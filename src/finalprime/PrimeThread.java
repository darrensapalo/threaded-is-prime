package finalprime;
import java.math.BigInteger;

public class PrimeThread extends Thread {
	private Thread t;
	public int tNum;
	private BigInteger start;
	private BigInteger end;
	private BigInteger input;
	public boolean result;
	
	/*
	 * public void start() { System.out.println("Starting Thread #" + tNum); }
	 */

	public PrimeThread(int tNum, BigInteger start, BigInteger end, BigInteger input) {
		this.tNum = tNum;
		this.start = start;
		this.end = end;
		this.input = input;
		System.out.println("Thread #" + tNum + " is created...");
	}

	public void run() {
		result = isPrime(input);
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
