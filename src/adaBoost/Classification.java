package adaBoost;

import javax.swing.event.EventListenerList;
import logiqueDeJeux.GlobalListener;
import machineEtat.MouvementEvent;

public class Classification 
{
	private int compteur = 0;
	private double[] moyenneVecteur = new double[100];
	private double[] nextMoyenneVecteur = new double[100];
	private ClassiFinal[] classi;
	private final EventListenerList listeners = new EventListenerList();
	
	public Classification(ClassiFinal[] classi)
	{
		this.classi=classi;
	}
	
	
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
			determineClasse(this.moyenneVecteur);
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
	
    public void addListener(GlobalListener listener) 
    {
        listeners.add(GlobalListener.class, listener);
    }
    
    public GlobalListener[] getListeners() {
        return listeners.getListeners(GlobalListener.class);
    }
	
	public void envoiMouvement (int i)
	{
		switch(i)
		{
		case 0:
			envoiMouvement(new MouvementEvent("passer"));
			break;
		case 1:
			envoiMouvement(new MouvementEvent("retour"));
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
}