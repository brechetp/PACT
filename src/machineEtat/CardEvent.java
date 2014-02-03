package machineEtat;

import structure.Carte;

public class CardEvent 
{
	private Carte carte;
	
	public CardEvent(Carte carte)
	{
		this.carte= carte;
	}

	public Carte getCarte() {
		return carte;
	}
	
}
