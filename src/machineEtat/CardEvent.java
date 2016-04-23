package machineEtat;

import structure.Carte;
import structure.CarteInterface;

public class CardEvent 
{
	private CarteInterface carte;
	
	public CardEvent(CarteInterface carte)
	{
		this.carte= carte;
	}

	public CarteInterface getCarte() {
		return carte;
	}

	public void setLabel(String label) 
	{
		carte.setLabelNum(label);
		
	}

	public void setSuit(String suit) 
	{
		carte.setSuit(suit);
	}
	
}
