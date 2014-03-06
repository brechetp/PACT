package adaBoost;

import java.io.Serializable;


public final class ClassiFinal implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -971724694737317174L;
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
	
	public double valeurA()
	{
		double retour = 0;
		for(double a : A)
		{
			retour=retour+a;
		}
		return retour;
	}
	
	public void normaliser()
	{
		double total = this.valeurA();
		for (int k=0;k<A.length;k++)
		{
			A[k]= A[k]/total;
		}
	}
}
