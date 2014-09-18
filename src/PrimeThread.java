import java.math.BigInteger;


class PrimeThread extends Thread {
	private BigInteger to;
	private BigInteger from;
	private BigInteger prime;
	public boolean isPrime;
	public BigInteger factor;

	public PrimeThread (BigInteger prime, BigInteger from, BigInteger to){
		this.prime = prime;
		this.from = from;
		this.to = to;
		this.isPrime = true;
	}
	
	@Override
	public void run() {
		BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
		if (prime.mod(TWO).equals(BigInteger.ZERO))
		{
			this.isPrime = true;
			this.factor = TWO;
		}else {
			for (BigInteger i = from.add(BigInteger.ONE); i.compareTo(to) <= 0; i = i.add(TWO)) {
				if (prime.mod(i).equals(BigInteger.ZERO)) {
					System.out.println("boop!");
					factor = i;
					this.isPrime = false;
					break;
				}
			}
		}
		super.run();
	}
}