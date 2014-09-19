package finalprime;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimeChecker {
	private Scanner s = new Scanner(System.in);
	private ArrayList<PrimeThread> pool;
	private long startTime; // for benchmarking
	private long endTime; // for benchmarking
	private BigInteger num; // user input
	private int nThread; // number of threads;
	private BigInteger[] start; // start parameter for thread
	private BigInteger[] end; // end parameter for thread

	// big prime: 472882049 -- 27704267971
	// 32416190071
	// 48112959837082048697
	// 2074722246773485207821695222107608587480996474721117292752992589912196684750549658310084416732550077
	public PrimeChecker() {
		getUserInput();
		initStartEnd();
		runThreads();

		System.out.println();
		System.out.println("Is " + num + " a prime number? " + checkResults());
		System.out.println("Execution Time: " + (endTime - startTime) + " ms");
	}

	public void getUserInput() {
		// System.out.print("Please enter a number to check if it is prime: ");
		// num = s.nextDouble();
		num = new BigInteger("32416190071");
		System.out.print("How many threads? ");
		nThread = s.nextInt();
		System.out.println();
	}

	public static BigInteger bigIntSqRootFloor(BigInteger x)
			throws IllegalArgumentException {
		if (x.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("Negative argument.");
		}
		// square roots of 0 and 1 are trivial and
		// y == 0 will cause a divide-by-zero exception
		if (x == BigInteger.ZERO || x == BigInteger.ONE) {
			return x;
		} // end if
		BigInteger two = BigInteger.valueOf(2L);
		BigInteger y;
		// starting with y = x / 2 avoids magnitude issues with x squared
		for (y = x.divide(two); y.compareTo(x.divide(y)) > 0; y = ((x.divide(y))
				.add(y)).divide(two))
			;
		return y;
	} // end bigIntSqRootFloor

	public void initStartEnd() {
		start = new BigInteger[nThread];
		end = new BigInteger[nThread];
		BigInteger limit = bigIntSqRootFloor(num);

		BigInteger size = limit.divide(new BigInteger(nThread+""));

		// initialize start-end
		start[0] = new BigInteger("2");
		
		for (int i = 0; i < nThread - 1; i++) {
			start[i + 1] = start[i].add(size);
			end[i] = start[i + 1].min(BigInteger.ONE);
		}
		end[nThread - 1] = limit;

		// print
		/*
		 * for (int i = 0; i < nThread; i++) { System.out.println("Thread #" +
		 * (i + 1) + ": " + start[i] + "-" + end[i]); } System.out.println();
		 */
	}

	public void runThreads() {
		pool = new ArrayList<PrimeThread>();

		// initialize threads
		for (int i = 0; i < nThread; i++) {
			PrimeThread thread = new PrimeThread(i + 1, start[i], end[i], num);
			pool.add(thread);
		}

		// start benchmark
		startTime = System.currentTimeMillis();

		// run threads
		for (PrimeThread thread : pool) {
			thread.start();
		}

		for (PrimeThread thread : pool) {
			while (thread.isAlive()) {
				// do nothing
			}
		}
		endTime = System.currentTimeMillis();
		// end benchmark
	}

	public boolean checkResults() {
		for (PrimeThread i : pool) {
			// System.out.println("CHECK "+i.result);
			if (!i.result)
				return false;
		}
		return true;
	}
}
