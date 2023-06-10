import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Polynomial {
	double[] coeff;
	int[] exp;

	public Polynomial()
	{
		coeff = new double[1];
		coeff[0] = 0.0;
		exp = new int[1];
		exp[0] = 0;
	}

	public Polynomial(double[] inputCoeff, int[] inputExp)
	{
		this.coeff = inputCoeff;
		this.exp = inputExp;
	}

	public Polynomial(File f)
	{
		try {
			BufferedReader b = new BufferedReader(new FileReader(f));
			String line = null;
			try {
				line = b.readLine();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			String linerem1 = line.replace("+", " +");
			String linerem2 = linerem1.replace("-", " -");
			linerem2 = linerem2.strip();
			System.out.println(linerem2);
			String[] parts = linerem2.split(" ");
			int size = parts.length;
			coeff = new double[size];
			exp = new int[size];
			int i = 0;
			for (String part : parts) {
				int len = part.length();
				if (len == 1 || (len == 2 && part.charAt(0) == '-') || (len == 2 && part.charAt(0) == '+')) {
					double element = Double.parseDouble(part);
					exp[i] = 0;
					coeff[i] = element;
					i++;
				}
				else {
					if (part.charAt(0) == '-') {
						int idx = part.indexOf('x');
						String sub = part.substring(1, idx);
						double elem = Double.parseDouble(sub);
						coeff[i] = -elem;
						String sub2 = part.substring(idx + 1);
						int elem2 = Integer.parseInt(sub2);
						exp[i] = elem2;
						i++;
					}
					else if (part.charAt(0) == '+') {
						int idx = part.indexOf('x');
						String sub = part.substring(1, idx);
						double elem = Double.parseDouble(sub);
						coeff[i] = elem;
						String sub2 = part.substring(idx + 1);
						int elem2 = Integer.parseInt(sub2);
						exp[i] = elem2;
						i++;
					}
					else {
						int idx = part.indexOf('x');
						String sub = part.substring(0, idx);
						double elem = Double.parseDouble(sub);
						coeff[i] = elem;
						String sub2 = part.substring(idx + 1);
						int elem2 = Integer.parseInt(sub2);
						exp[i] = elem2;
						i++;
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Polynomial add(Polynomial argPoly)
	{
		int match = 0;
		for (int i = 0; i < coeff.length; i++) {
			for (int j = 0; j < argPoly.coeff.length; j++) {
				if (exp[i] == argPoly.exp[j]) {
					match++;
				}
			}
		}

		int newlen = coeff.length + argPoly.coeff.length - match;
		double[] resCoeff = new double[newlen];
		int[] resExp = new int[newlen];

		for (int i = 0; i < coeff.length; i++) {
			resCoeff[i] = coeff[i];
			resExp[i] = exp[i];
		}

		for (int i = 0; i < argPoly.coeff.length; i++) {
			int argExp = argPoly.exp[i];
			double argCoeff = argPoly.coeff[i];
			boolean isInThis = false;
			for (int j = 0; j < coeff.length; j++) {
				if (argExp == exp[j]) {
					isInThis = true;
					resCoeff[j] += argCoeff;
					break;
				}
			}
			if (isInThis == false) {
				for (int k = 1; k < resCoeff.length; k++) {
					if (resCoeff[k] == 0) {
						resCoeff[k] = argCoeff;
						resExp[k] = argExp;
						break;
					}
				}
			}
		}
		int numzeros = 0;
		for (int i = 0; i < resCoeff.length; i++) {
			if (resCoeff[i] == 0) {
				numzeros++;
			}
		}
		if (numzeros == 0) {
			return new Polynomial(resCoeff, resExp);
		}
		else {
			int fixlen = newlen - numzeros;
			double [] remCoeff = new double[fixlen];
			int [] remExp = new int[fixlen];
			for (int i = 0, k = 0; i < resCoeff.length; i++){
				if (resCoeff[i] != 0) {
					remCoeff[k] = resCoeff[i];
					remExp[k] = resExp[i];
					k++;
				}
			}
			return new Polynomial(remCoeff, remExp);
		}
	}

	public double evaluate(double argVal)
	{
		int size = coeff.length;
		double val = 0;
		for (int i = 0; i < size; i++) {
			val += coeff[i] * Math.pow(argVal, exp[i]);
		}
		return val;
	}

	public boolean hasRoot(double argVal)
	{
		double val;
		val = evaluate(argVal);
		return val == 0;
	}

	public Polynomial multiply(Polynomial argPoly)
	{
		// Sort the arrays
		double[] Coeffsort = new double[coeff.length];
		Coeffsort = coeff.clone();
		int[] Expsort = new int[exp.length];
		Expsort = exp.clone();
		double[] argCoeffsort = new double[argPoly.coeff.length];
		argCoeffsort = argPoly.coeff.clone();
		int [] argExpsort = new int[argPoly.exp.length];
		argExpsort = argPoly.exp.clone();
		// Bubble sort
		boolean swap = false;
		int tempExp = 0;
		double tempCoeff = 0;
		for (int i = 0; i < argExpsort.length - 1; i++) {
			for (int j = 0; j < argExpsort.length - i - 1; j++) {
				if (argExpsort[j] > argExpsort[j + 1]) {
					tempExp = argExpsort[j];
					argExpsort[j] = argExpsort[j + 1];
					argExpsort[j + 1] = tempExp;
					tempCoeff = argCoeffsort[j];
					argCoeffsort[j] = argCoeffsort[j + 1];
					argCoeffsort[j + 1] = tempCoeff;
					swap = true;
				}
			}
			if (swap == false) {
				break;
			}
		}
		swap = false;
		tempExp = 0;
		tempCoeff = 0;
		for (int i = 0; i < Expsort.length - 1; i++) {
			for (int j = 0; j < Expsort.length - i - 1; j++) {
				if (Expsort[j] > Expsort[j + 1]) {
					tempExp = Expsort[j];
					Expsort[j] = Expsort[j + 1];
					Expsort[j + 1] = tempExp;
					tempCoeff = Coeffsort[j];
					Coeffsort[j] = Coeffsort[j + 1];
					Coeffsort[j + 1] = tempCoeff;
					swap = true;
				}
			}
			if (swap == false) {
				break;
			}
		}


		int maxlen = Coeffsort.length + argCoeffsort.length - 1;
		double[] resCoeff = new double[maxlen];
		int[] resExp = new int[maxlen];
		for (int i = 0; i < Coeffsort.length; i++) {
			for (int j = 0; j < argCoeffsort.length; j++) {
				if (resExp[i + j] == 0) {
					resExp[i + j] = Expsort[i] + argExpsort[j];
					resCoeff[i + j] += Coeffsort[i] * argCoeffsort[j];
				}
				else {
					resCoeff[i + j] += Coeffsort[i] * argCoeffsort[j];
				}
			}
		}
		return new Polynomial(resCoeff, resExp);
	}

	public void saveToFile(String filename)
	{
		StringBuilder strRes = new StringBuilder("");
		int len = coeff.length;
		for (int i = 0; i < len; i++) {
			double currCoeff = coeff[i];
			int currExp = exp[i];
			if (currExp == 0) {
				strRes.append(currCoeff);
			}
			else {
				if (currCoeff > 0) {
					strRes.append("+" + currCoeff);
					strRes.append("x" + currExp);
				}
				else {
					strRes.append(currCoeff);
					strRes.append("x" + currExp);

				}
			}
		}
		PrintStream ps = null;
		try {
			ps = new PrintStream(filename);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ps.println(strRes);
	}
}
