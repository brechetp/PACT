package adaBoost;

import java.io.Serializable;


public final class ClassiFinal implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8869425576675025163L;
	
	private double[] A;
	private ClassifSouche[] H;
	
	public ClassiFinal(double[] a, ClassifSouche[] h) 
	{
		super();
		A = a;
		H = h;
	}
	
	public double result(double[] X)
	{
		
		double result = 0;
		for(int k=0;k<X.length;k++)
		{
			result=result+A[k]*H[k].result(X);
		}
		return result;
	}
}
