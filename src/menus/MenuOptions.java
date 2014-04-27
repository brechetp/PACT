package menus;

import iug.ViewControllerInterface;
import logiqueDeJeux.EtatDuJeu;
import logiqueDeJeux.GlobalListener;
import machineEtat.CardEvent;
import machineEtat.MouvementEvent;
import structure.CircularArray;

public class MenuOptions implements GlobalListener
{
	private ViewControllerInterface vci;
	private MenuBelote menuBelote;
	
	public static boolean ttatout = true;
	public static boolean annonceCartes = true;

	public MenuOptions(ViewControllerInterface vci, MenuBelote menuBelote)
	{
		this.vci=vci;
		this.menuBelote=menuBelote;
	}
	
	public void nouvelleCarte(CardEvent carte) 
	{
		//nothing
	}

	public void nouveauGeste(MouvementEvent mouvement) 
	{
		
		int optionEnCours = vci.optionEnCours();
		
		if (mouvement.getLabel().equals("passer"))
		{

			if (optionEnCours == 1){
				vci.modifOption1();
				ttatout=!ttatout;
			}

			if (optionEnCours == 2){
				vci.modifOption2();
				annonceCartes = !annonceCartes;
			}

			if (optionEnCours == 3){
				EtatDuJeu.valeurFinPartie=EtatDuJeu.valeurFinPartie+100;
				vci.modifOption3(EtatDuJeu.valeurFinPartie);
			}
		}
		else if (mouvement.getLabel().equals("quitter")) /*retour*/
		{
			if (optionEnCours == 3){
				EtatDuJeu.valeurFinPartie=EtatDuJeu.valeurFinPartie-100;
				vci.modifOption3(EtatDuJeu.valeurFinPartie);
			}
		}
		else if (mouvement.getLabel().equals("coinche"))
		{
			if (optionEnCours != 1){
				vci.option(optionEnCours-1);
			}
		}
		else if (mouvement.getLabel().equals("accepter"))
		{
			if (optionEnCours != 4){
				vci.option(optionEnCours+1);
			}
			else{
				System.out.println("StartBelote from: MenuOptions");
				vci.validerOptions();
				menuBelote.startBelote(this);
			}
		}

	}

	public void finPli() 
	{
		//nothing
	}

	public void init() 
	{
		vci.modeOption();
		vci.option(1);
	}

	@Override
	public void nouvelleCarteJoueurDistant(CardEvent carte) 
	{
		//nothing
	}
	
	
	

}