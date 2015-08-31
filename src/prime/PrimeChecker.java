package prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimeChecker {
	/**
	 * A scanner used for getting input from the user.
	 */
	private Scanner s = new Scanner(System.in);

	/**
	 * A pool of threads for processing the results.
	 */
	private ArrayList<PrimeThread> pool;

	/**
	 * The start time when the processing began. This is used for benchmarking.
	 */
	private long startTime;

	/**
	 * The end time when the processing ended. This is used for benchmarking.
	 */
	private long endTime;

	/**
	 * This is the number which is suspected to be a prime number.
	 */
	private BigInteger num;

	/**
	 * This is the number of threads to be created.
	 */
	private int nThread;

	/**
	 * This is the array used by the multiple threads to identify which
	 * BigInteger to begin with.
	 */
	private BigInteger[] start;

	/**
	 * This is the array used by the multiple threads to identify which
	 * BigInteger to end at.
	 */
	private BigInteger[] end;

	public static final String Small   = "472882049";
	public static final String Medium  = "27704267971";
	public static final String Large   = "32416190071";
	public static final String XLarge  = "48112959837082048697";
	public static final String XXLarge = "2074722246773485207821695222107608587480996474721117292752992589912196684750549658310084416732550077";
	
	public static final boolean willGetUserInput = false;
	public static final String defaultValue = Large;


	public static void main(String args[]) {
		new PrimeChecker();	
	}
	
	public PrimeChecker() {
		try {
			getUserInput();
			initStartEnd();
			runThreads();

			System.out.println();
			System.out.println("Is " + num + " a prime number? " + checkResults());
			System.out.println("Execution Time: " + (endTime - startTime) + " ms");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets the user's input; namely the suspect number and the number of threads to create. 
	 * @throws Exception when the input is invalid (string instead of numbers)
	 */
	public void getUserInput() throws Exception {
		if (willGetUserInput) {
			System.out.print("Please enter a number to check if it is prime: ");
			num = s.nextBigInteger();
		} else {
			num = new BigInteger(defaultValue);
		}
		System.out.print("How many threads do you wish to create? ");
		nThread = s.nextInt();
		System.out.println();
	}

	/**
	 * @param x
	 * @return the square root of a big integer x
	 * @throws IllegalArgumentException when x is a negative number
	 */
	public static BigInteger bigIntSqRootFloor(BigInteger x) throws IllegalArgumentException {
		if (x.compareTo(BigInteger.ZERO) < 0) 
			throw new IllegalArgumentException("Negative argument.");
		
		// square roots of 0 and 1 are trivial and
		// y == 0 will cause a divide-by-zero exception
		if (x == BigInteger.ZERO || x == BigInteger.ONE) {
			return x;
		} // end if
		
		BigInteger two = BigInteger.valueOf(2L);
		BigInteger y;
		// starting with y = x / 2 avoids magnitude issues with x squared
		for (y = x.divide(two); y.compareTo(x.divide(y)) > 0; y = ((x.divide(y)).add(y)).divide(two))
			;
		return y;
	} // end bigIntSqRootFloor

	/**
	 * Initializes the multiple threads and their division of work loads.
	 */
	public void initStartEnd() {
		start = new BigInteger[nThread];
		end = new BigInteger[nThread];
		
		/**
		 * We only compute for divisibility until the square root of the suspect number
		 */
		BigInteger limit = bigIntSqRootFloor(num);

		/**
		 * The size of a work load
		 */
		BigInteger size = limit.divide(BigInteger.valueOf(nThread));

		// initialize start-end
		start[0] = BigInteger.valueOf(2);

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
			// distribute work load
			PrimeThread thread = new PrimeThread(i + 1, start[i], end[i], num);
			
			// add to pool
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
			// If any of the threads identify the number as not prime, then it is not a prime number.
			if (!i.isPrime)
				return false;
		}
		
		// If all of the threads identify it as a prime, then it is a prime number.
		return true;
	}
}
