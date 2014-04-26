package menus;

import adaBoost.Classification;
import iug.ImageMenu;
import iug.ViewControllerInterface;
import structure.CircularArray;
import logiqueDeJeux.GlobalListener;
import machineEtat.CardEvent;
import machineEtat.MouvementEvent;

public class MainMenu implements GlobalListener
{
	private CircularArray<menu> liste;
	private ViewControllerInterface vci;

	public MainMenu(Classification classi,ViewControllerInterface vci)
	{
		this.vci=vci;
		
		menu[] tab = new menu[3];
		CircularArray<menu> menus = new CircularArray<menu>(tab);
		
		tab[1] = new MenuBelote(new ImageMenu(1), vci, classi,this);
		tab[0] = new MenuTest(new ImageMenu(2), vci, classi);
		tab[2] = new MenuTest(new ImageMenu(3), vci, classi);
		
		this.liste = menus;
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
			vci.gauche(liste.get(2).getImage());
		}
		else if (mouvement.getLabel()=="retour")
		{
			liste.moveRight();
			vci.droite(liste.get(0).getImage());
		}
		else if (mouvement.getLabel()=="coinche")
			;//nothing pour l'instant
		else if (mouvement.getLabel()=="accepter")
		{
			vci.validerMenu();
			liste.get(1).run(); //gere le fait que l'on as accepter
		}
		else if (mouvement.getLabel()=="quitter")
			System.exit(0);

	}

	public void finPli() 
	{
		//nothing
	}

	public void init() 
	{
		vci.init(liste.get(0).getImage(), liste.get(1).getImage(), liste.get(2).getImage());
	}

	@Override
	public void nouvelleCarteJoueurDistant(CardEvent carte) 
	{
		//nothing
		
	}
	
	
	

}
