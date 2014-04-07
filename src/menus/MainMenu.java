package menus;

import iug.ViewControllerInterface;
import structure.CircularArray;
import logiqueDeJeux.GlobalListener;
import machineEtat.CardEvent;
import machineEtat.MouvementEvent;

public class MainMenu implements GlobalListener
{
	private CircularArray<menu> liste;
	private ViewControllerInterface vci;
	
	public MainMenu(CircularArray<menu> liste,ViewControllerInterface vci)
	{
		this.vci=vci;
		this.liste=liste;
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
			vci.gauche(liste.get(1).getImage());
		}
		else if (mouvement.getLabel()=="retour")
		{
			liste.moveRight();
			vci.droite(liste.get(-1).getImage());
		}
		else if (mouvement.getLabel()=="coinche")
			;//nothing pour l'instant
		else if (mouvement.getLabel()=="accepter")
			liste.get(0).run();// gere le fait que l'on as accepter
		else if (mouvement.getLabel()=="quitter")
			System.exit(0);
		
	}

	public void finPli() 
	{
		//nothing
	}
	
	
	

}
