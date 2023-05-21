public class Polynomial
{
	double[] anArray;
	public Polynomial()
	{
		anArray = new double[1];
		anArray[0] = 0.0;
	}
	public Polynomial(double[] inputArray)
	{
		// int size = inputArray.length;
		// double[] anArray = new double[size];
		// System.arraycopy(inputArray, 0, anArray, 0, size);
		anArray = inputArray.clone();
	}
	public Polynomial add(Polynomial argPoly)
	{
		int max = Math.max(anArray.length, argPoly.anArray.length);
		double[] res = new double[max];
		
		for (int i = 0; i < anArray.length; i++) {
			res[i] += anArray[i];
		}
		
		for (int i = 0; i < argPoly.anArray.length; i++) {
			res[i] += argPoly.anArray[i];
		}
		return new Polynomial(res);
	}
	public double evaluate(double argVal)
	{
		int size = anArray.length;
		double val = 0;
		for (int i = 0; i < size; i++) {
			val += anArray[i] * Math.pow(argVal, i);
		}
		return val;
	}
	public boolean hasRoot(double argVal)
	{
		double val;
		val = evaluate(argVal);
		return val == 0;
	}
	
	
}