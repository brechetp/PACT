
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
		return (int) Math.signum(X[indice]-seuil);
	}
	
}
