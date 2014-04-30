package menus;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;

import camera.CaptureLive;
import camera.Initialisation;
import structure.Carte;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;
import machineEtat.CardEvent;
import machineEtat.MouvementEvent;
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
		try {
			BeloteCoinche belote = new BeloteCoinche(getVci(),this);
			getVci().modePartie();
			getVci().distribution(0);
			getClassi().addListener(belote);

//			while(belote.isStateDistrib()){
//				String s = (String)JOptionPane.showInputDialog(null, "Carte � distribuer :", "Distribution", JOptionPane.PLAIN_MESSAGE, null, null, null);
//				if (s!="") 
//				{
//					String[] tabcarte = s.split(" ");
//					String valeur = tabcarte[0];
//					
//					String couleur="";
//					if (tabcarte.length>1) {
//						couleur = tabcarte[1];
//					}
//					Carte card = new Carte(valeur, couleur, belote.getEtat());
//					CardEvent event = new CardEvent(card);
//					belote.nouvelleCarte(event);
//				}
//			}
//			
//			belote.nouveauGeste(new MouvementEvent("quitter"));
			
		} catch (IOException e) {
			this.stopBelote(null);
		}
	}

	public void stopBelote(BeloteCoinche belote){
		getClassi().addListener(mainMenu);
		getVci().modeMenu();
		getVci().supprimerMessage();
		try {
			getClassi().removeListener(belote);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
