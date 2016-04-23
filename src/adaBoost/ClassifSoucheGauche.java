package adaBoost;

public class ClassifSoucheGauche extends ClassifSouche
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7897354197334687538L;
	private double seuil;
	private int indice;
	
	public ClassifSoucheGauche(double seuil,int indice) 
	{
		this.seuil=seuil;
		this.indice=indice;
	}
	
	public int result(double[] X) 
	{
		if (X[indice]<seuil)
			return 1;
		else return -1;
	}

}
