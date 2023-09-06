package Calculator;

public class Interest{
	
	public static double simpleInterest(double principal, double rate, int years) {
			double total = principal + (principal * (rate/100) * years);
			return total;
	}

	
	public static double compoundInterest(double principal, double rate, int years) {
			double total = principal * Math.pow((1 + rate/100), years);
			return total;
	}
}
