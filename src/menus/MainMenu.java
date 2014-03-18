package menus;

import structure.CircularArray;
import logiqueDeJeux.GlobalListener;
import machineEtat.CardEvent;
import machineEtat.MouvementEvent;

public class MainMenu implements GlobalListener
{
	private CircularArray liste;

	public MainMenu()
	{
		
	}
	
	public void nouvelleCarte(CardEvent carte) 
	{
		//nothing
	}

	public void nouveauGeste(MouvementEvent mouvement) 
	{
		//a completer avec les actions
		if (mouvement.getLabel()=="passer")
		{
			liste.moveLeft();
			//iug
		}
		else if (mouvement.getLabel()=="retour")
		{
			liste.moveRight();
		}
		else if (mouvement.getLabel()=="coinche")
			;//nothing pour l'instant
		else if (mouvement.getLabel()=="accepter")
			;// gere le fait que l'on as accepter
		else if (mouvement.getLabel()=="quitter")
			System.exit(0);
		
	}

	public void finPli() 
	{
		//nothing
	}
	
	
	

}
