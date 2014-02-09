package adaBoost;


public final class ClassifSouche 
{
	private double seuil;
	private int indice;
	private double erreur;
	
	public ClassifSouche(double seuil, int indice)
	{
		this.seuil=seuil;
		this.indice=indice;
		this.erreur=0;
	}
	
	public int result(double[] X)
	{
		if (X[indice]<seuil)
			return -1;
		else return 1;
	}

	public double getErreur() {
		return erreur;
	}

	public void setErreur(double erreur) {
		this.erreur = erreur;
	}
	
}
