package menus;

import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;
import adaBoost.Classification;
import iug.ImageMenu;
import iug.ViewControllerInterface;

public class MenuBelote extends menu {

	private MainMenu mainMenu;
	public MenuBelote(ImageMenu image, ViewControllerInterface vci, Classification classi,MainMenu mainMenu) {
		super(image, vci, classi);
		this.mainMenu = mainMenu;
	}

	@Override
	public void run() 
	{
		getClassi().removeListener(mainMenu);
		MenuOptions menuOptions = new MenuOptions(getVci(),this);
		menuOptions.init();
		getClassi().addListener(menuOptions);
		
	}
	
	public void startBelote(MenuOptions menuOptions)
	{
		getClassi().removeListener(menuOptions);
		//Cration logique de jeu
		EtatDuJeu etat = new EtatDuJeu();
		BeloteCoinche belote = new BeloteCoinche(etat, getVci());
		getClassi().addListener(belote);
		getVci().modePartie();
		getVci().distribution();
		//Start Camera
		//new Thread(new MainThreadImage(belote)).start();
	}
}
