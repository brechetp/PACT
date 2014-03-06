package adaBoost;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public final class AdaBoost 
{
	private static double erreur(ClassifSouche classi, double[][] X,int[] Y,double[] D)
	{
		double erreur = 0.; 
		
 		for(int k=0; k<	X.length;k++)
 		{
 			int result =classi.result(X[k]);
 			if (result!=Y[k])
 			{
 				erreur=erreur+D[k];
 			}
 		}
 		return erreur;
	}
	
	private static ClassifSouche bestClassificateur(double[][] X,int[] Y,double[] D) 
	{
		ClassifSouche classiRetour = null;
		double erreurMin=1;
		double hauteur=X.length;
		double longueur=X[0].length;
		
		for (int j=0;j<longueur;j++)
		{
			for(int i=0;i<hauteur;i++)
			{
				ClassifSouche classiG = new ClassifSoucheGauche(X[i][j], j);
				ClassifSouche classiD = new ClassifSoucheDroite(X[i][j], j);
				double erreurG = AdaBoost.erreur(classiG, X, Y, D);
				double erreurD = AdaBoost.erreur(classiD, X, Y, D);
				if (erreurG<=erreurMin)
				{
					erreurMin=erreurG;
					classiRetour=classiG;
				}
				if (erreurD<=erreurMin)
				{
					erreurMin=erreurD;
					classiRetour=classiD;
				}
			}
		}
		
		return classiRetour;
	}
	
	public static ClassiFinal adaBoost(double[][] X, int[] Y, int T)
	{
		int n = X.length;
		double[] D= new double[n];
		ClassifSouche[] H=new ClassifSouche[T];
		double[] A = new double[T];
		
		for(int i=0;i<n;i++)
		{
			D[i]= (double)1/n;
		}
		
		for(int t=0;t<T;t++)
		{
			ClassifSouche classi = AdaBoost.bestClassificateur(X, Y, D);
			double erreur = AdaBoost.erreur(classi, X, Y, D);
			H[t]=classi;
			A[t]=(0.5)*Math.log((1.0-erreur)/erreur);
			for(int i=0;i<n;i++)
			{
				if(H[t].result(X[i])!=Y[i])
					D[i]=D[i]*Math.exp(A[t]);
				else 
					D[i]=D[i]*Math.exp(-A[t]);
			}
			
			double denom=0;
			for(double valeur : D)
				denom=denom+valeur;
			
			for(int i=0;i<n;i++)
			{
				D[i]=D[i]/denom;
			}
		}
		ClassiFinal classiFinal = new ClassiFinal(A, H);
		classiFinal.normaliser();
		
		return classiFinal;
	}
}
