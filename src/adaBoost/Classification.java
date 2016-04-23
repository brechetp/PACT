package adaBoost;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import logiqueDeJeux.GlobalListener;
import machineEtat.MouvementEvent;

public class Classification 
{
	private int compteur = 0;
	public static final int NOMBRE_DE_FEATURES = 14;
	private double[] moyenneVecteur = new double[NOMBRE_DE_FEATURES];
	private double[] nextMoyenneVecteur = new double[NOMBRE_DE_FEATURES];
	private ClassiFinal[] classi;
	private final EventListenerList listeners = new EventListenerList();
	
	public Classification(ClassiFinal[] classi)
	{
		this.classi=classi;
	}
	
	
	public void onFrame(double[] frame)
	{
		
		//System.out.println(compteur);
		this.ajoute(frame);
		
//		if (compteur>=30)
//		{
//			//this.ajouteNext(frame);
//		}
		
		if (compteur == 59)
		{
			this.ajoute(frame);
			normaliser();
			determineClasse(this.moyenneVecteur);
			this.moyenneVecteur = new double[Classification.NOMBRE_DE_FEATURES]; //this.nextMoyenneVecteur;
			//this.nextMoyenneVecteur = new double[NOMBRE_DE_FEATURES];
			compteur = 0;
		}
	}
	
	public void determineClasse (double[] mvment)
	{		
		//System.out.println(mvment[0]+" "+mvment[1]);
		if (mvment[1]<0.7) 
		{
			envoiMouvement(2);
		}
		else if (mvment[0]>1.9&&mvment[1]>6) 
		{
			envoiMouvement(4);
		}
		else if (mvment[1]<2.1)
		{
			envoiMouvement(1);
		}
		else if (mvment[1]>3) 
		{
			envoiMouvement(3);
		}
		else
		{
			ArrayList<Integer> classesRetour = new ArrayList<Integer>();
			double resultMax = 0;
			int indice = 0;
//			for (int k = 0; k < classi.length; k++) {
//				double result = classi[k].result(mvment);
//				if (result > 0) {
//					classesRetour.add(new Integer(k));
//				}
//				if (result > resultMax) {
//					resultMax = result;
//					indice = k;
//				}
//			}
//			if (classesRetour.size() == 1)
//				this.envoiMouvement(classesRetour.get(0));
//			else if (classesRetour.size() > 1)
//				envoiMouvement(indice);
			double result = classi[0].result(mvment);
			double result2 = classi[1].result(mvment);
			if (result*result2>0)
				System.out.println("Non !");
			else if(result>0)
				envoiMouvement(1);
			else 
				envoiMouvement(3);
			
			}
	}
	
	public void ajoute(double[] frame)
	{
		compteur++;
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
	
    public void addListener(GlobalListener listener) 
    {
        listeners.add(GlobalListener.class, listener);
    }
    
    public void removeListener(GlobalListener listener)
    {
    	listeners.remove(GlobalListener.class, listener);
    }
    
    public GlobalListener[] getListeners() {
        return listeners.getListeners(GlobalListener.class);
    }
	
	public void envoiMouvement (int i)
	{
		switch(i)
		{
		// Pour le prototype all�ger on change retour en passer.
		
		case 0:
			//envoiMouvement(new MouvementEvent("passer"));
			//System.out.println("Passer");
			break;
		case 1:
			//envoiMouvement(new MouvementEvent("retour"));
			envoiMouvement(new MouvementEvent("passer"));
			break;
		case 2:
			envoiMouvement(new MouvementEvent("coinche"));
			break;
		case 3:
			envoiMouvement(new MouvementEvent("accepter"));
			break;
		case 4:
			envoiMouvement(new MouvementEvent("quitter"));
			break;
		}
	}
	
	private void envoiMouvement(MouvementEvent mvmt) 
	{
            for(GlobalListener listener : getListeners()) 
            {
                listener.nouveauGeste(mvmt);
            }
	}


	public void lancerClassi() 
	{
		if (compteur>30)
		{
			normaliser();
			determineClasse(this.moyenneVecteur);
			this.moyenneVecteur = new double[Classification.NOMBRE_DE_FEATURES];
			this.nextMoyenneVecteur = new double[Classification.NOMBRE_DE_FEATURES];
			compteur = 0;
		}
		else if (compteur>0)
			compteur = 0;
		
	}
	
	public void normaliser()
	{
		for (int j=0;j<this.moyenneVecteur.length;j++)
		{
			this.moyenneVecteur[j]=this.moyenneVecteur[j]/(double)compteur;
		
		}
	}
}