public class Polynomial
{
	double[] anArray;
	public Polynomial()
	{
		anArray = new double[1];
		anArray[0] = 0;
	}
	public Polynomial(double[] inputArray)
	{
		int size = inputArray.length;
		double[] anArray = new double[size];
		System.arraycopy(inputArray, 0, anArray, 0, size);
	}
	public Polynomial add(Polynomial argPoly)
	{
		Polynomial newpoly = new Polynomial(argPoly.anArray);
		for (int i = 0; i < anArray.length; i++) {
			newpoly[i] = anArray[i] + argPoly.anArray[i];
		}
		return newpoly;
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