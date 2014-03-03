package adaBoost;

public class ClassifSoucheDroite extends ClassifSouche
{
	private double seuil;
	private int indice;
	
	public ClassifSoucheDroite(double seuil,int indice) 
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
