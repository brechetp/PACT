package adaBoost;

import java.io.Serializable;


public abstract class ClassifSouche implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassifSouche()
	{
	}
	
	public abstract int result(double[] X);

	
}
