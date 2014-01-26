
public final class AdaBoost 
{
	private static double erreur(ClassifSouche classi, double[][] M,int[] Y,double[] D)
	{
		double erreur = 0.; 
		
 		for(int k=0; k<	M.length;k++)
 		{
 			if (classi.result(M[k])!=Y[k])
 			{
 				erreur=erreur+D[k];
 			}
 		}
 		
 		return erreur;
	}
	
	private static ClassifSouche bestClassificateur(double[][] M,int[] Y,double[] D) 
	{
		ClassifSouche classiRetour = null;
		double erreurMin=1;
		double hauteur=M.length;
		double longueur=M[0].length;
		
		for (int j=0;j<longueur;j++)
		{
			for(int i=0;i<hauteur;i++)
			{
				ClassifSouche classi = new ClassifSouche(M[i][j], j);
				double erreur = AdaBoost.erreur(classi, M, Y, D);
				if (erreur<erreurMin)
				{
					erreurMin=erreur;
					classiRetour=classi;
				}
			}
			
		}
		
		return classiRetour;
	}
	
	public static ClassiFinal adaBoost(double[][] M, int[] Y, int T)
	{
		int m = M.length;
		double[] D= new double[m];
		ClassifSouche[] H=new ClassifSouche[T];
		double[] A = new double[T];
		
		for(int i=0;i<m;i++)
		{
			D[i]=1/m;
		}
		
		for(int t=0;t<T;t++)
		{
			ClassifSouche classi = AdaBoost.bestClassificateur(M, Y, D);
			double erreur = AdaBoost.erreur(classi, M, Y, D);
			H[t]=classi;
			A[t]=(1/2)*Math.log((1-erreur)/erreur);
		
			for(int i=0;i<m;i++)
			{
				if(H[t].result(M[i])!=Y[i])
					D[i]=D[i]*Math.exp(A[t]);
				else 
					D[i]=D[i]*Math.exp(-A[t]);
			}
			
			double denom=0;
			for(double valeur : D)
				denom=denom+valeur;
			
			for(int i=0;i<m;i++)
			{
				D[i]=D[i]/denom;
			}
		}
			
		return new ClassiFinal(A, H);
	}
}
