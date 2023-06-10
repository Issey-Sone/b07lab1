import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintStream;

public class Driver {
	public static void main(String[] args)
	{
		// Testing empty case
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		// Testing add
		System.out.println("Testing add");
		double[] c1 = {-2, 6, 5};
		int[] e1 = {1, 0, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double[] c2 = {2, -1, 3, 2};
		int[] e2 = {0, 1, 2, 3};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("First Polynomial Add");
		for (int i = 0; i < s.coeff.length; i++) {
			System.out.println("Coeff = " + s.coeff[i]);
			System.out.println("Exp = " + s.exp[i]);
		}

		double[] bc1 = {5, 9, 22, 10};
		int[]  be1 = {3, 7, 23, 4};
		Polynomial bp1 = new Polynomial(bc1, be1);
		double[] bc2 = {-22, 3, 11, 19, 17};
		int[] be2 = {23, 1, 0, 7, 13};
		Polynomial bp2 = new Polynomial(bc2, be2);
		Polynomial bct = bp2.add(bp1);
		System.out.println("Second Polynomial Add");
		for (int i = 0; i < bct.coeff.length; i++) {
			System.out.println("Coeff = " + bct.coeff[i]);
			System.out.println("Exp = " + bct.exp[i]);
		}

		// Testing evaluate
		System.out.println("Testing evaluate");
		System.out.println("s(1) = " + s.evaluate(1));
		System.out.println("s(2) = " + s.evaluate(2));
		System.out.println("bct(0) = " + bct.evaluate(0));
		System.out.println("bct(1) = " + bct.evaluate(1));
		System.out.println("bct(2) = " + bct.evaluate(2));
		// Testing multiply
		System.out.println("Testing Multiply");
		double[] c3 = {-1.0, 1.0};
		int[] e3 = {0, 1};
		Polynomial p3 = new Polynomial(c3, e3);
		double[] c4 = {-2.0, 1.0};
		int[] e4 = {0, 1};
		Polynomial p4 = new Polynomial(c4, e4);
		Polynomial s2 = p3.multiply(p4);
		System.out.println("First Multiply");
		for (int i = 0; i < s2.coeff.length; i++) {
			System.out.println("Coeff = " + s2.coeff[i]);
			System.out.println("Exp = " + s2.exp[i]);
		}

		double[] c5 = {5, 7, 11};
		int[] e5 = {1, 0, 2};
		Polynomial p5 = new Polynomial(c5, e5);
		double[] c6 = {2, 8, 6, 7};
		int[] e6 = {0, 1, 3, 2};
		Polynomial p6 = new Polynomial(c6, e6);
		Polynomial s3 = p6.multiply(p5);
		System.out.println("Second multiply");
		for (int i = 0; i < s3.coeff.length; i++) {
			System.out.println("Coeff = " + s3.coeff[i]);
			System.out.println("Exp = " + s3.exp[i]);
		}
		System.out.println("Testing hasRoot");
		if (s2.hasRoot(2)) {
			System.out.println("2 is a root of s2");
		}
		else {
			System.out.println("2 is not a root of s2");
		}
		if (s2.hasRoot(1)) {
			System.out.println("1 is a root of s2");
		}
		else {
			System.out.println("1 is not a root of s2");
		}
		if (s2.hasRoot(0)) {
			System.out.println("0 is a root of s2");
		}
		else {
			System.out.println("0 is not a root of s2");
		}
		if (s3.hasRoot(-2)) {
			System.out.println("-2 is a root of s3");
		}
		else {
			System.out.println("-2 is not a root of s3");
		}
		if (s3.hasRoot(4)) {
			System.out.println("4 is a root of s3");
		}
		else {
			System.out.println("4 is not a root of s3");
		}

		System.out.println("Testing File reading");
		File f = new File("C:\\Users\\Issey\\Documents\\B07\\lab2fix\\abc.txt");
		Polynomial p7 = new Polynomial(f);
		for (int i = 0; i < p7.coeff.length; i++) {
			System.out.println("Coeff = " + p7.coeff[i]);
			System.out.println("Exp = " + p7.exp[i]);
		}
		System.out.println("Writing to File");
		s3.saveToFile("abc.txt");
	}
}
