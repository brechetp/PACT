package adaBoost;


public final class ClassifSouche 
{
	private double seuil;
	private int indice;
	
	public ClassifSouche(double seuil, int indice)
	{
		this.seuil=seuil;
		this.indice=indice;
	}
	
	public int result(double[] X)
	{
		if (X[indice]<seuil)
			return -1;
		else return 1;
	}

	
}
