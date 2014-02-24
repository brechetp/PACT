package adaBoost;

import java.util.ArrayList;

public class Classification 
{
	private int compteur = 0;
	private double[] moyenneVecteur = new double[100];
	private double[] nextMoyenneVecteur = new double[100];
	private ClassiFinal[] classi;
	
	
	
	public void onFrame(double[] frame)
	{
		compteur++;
		this.ajoute(frame);
		
		if (compteur>=60)
		{
			this.ajouteNext(frame);
		}
		
		if (compteur == 120)
		{
			//envoy moyenneVecteur a la classi
			this.moyenneVecteur = this.nextMoyenneVecteur;
			this.nextMoyenneVecteur = new double[100];
			compteur = 60;
		}
	}
	
	public void determineClasse (double[] mvment)
	{
		int indiceMax=0;
		double resultMax =-1;
		
		for(int k=0;k<classi.length;k++)
		{
			double result = classi[k].result(mvment);
			if (result>resultMax)
			{
				indiceMax=k;
				resultMax=result;
			}
		}
		
		this.envoiMouvement(indiceMax);
	}
	
	public void ajoute(double[] frame)
	{
		for (int j=0;j<this.moyenneVecteur.length;j++)
		{
			this.moyenneVecteur[j]=this.moyenneVecteur[j]+frame[j];
		
		}
	}
	
	public void ajouteNext(double[] frame)
	{
		for (int j=0;j<this.nextMoyenneVecteur.length;j++)
		{
			this.nextMoyenneVecteur[j]=this.nextMoyenneVecteur[j]+frame[j];
		
		}
	}
	
	public void envoiMouvement (int i)
	{
		switch(i)
		{
		case 0:
			
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		}
	}
	
}
