package menus;

import java.io.IOException;

import camera.Capture;
import camera.Initialisation;
import iug.ImageMenu;
import iug.ViewControllerInterface;
import adaBoost.Classification;

public class MenuSettings extends menu {

	public MenuSettings(ImageMenu image, ViewControllerInterface vci,
			Classification classi, MainMenu mainMenu) {
		super(image, vci, classi, mainMenu);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		getClassi().removeListener(getMainMenu());
		getVci().modeSettings();
		try {
			Capture.cardSize("data/database/size/", getVci());
			Initialisation.setCardSize("data/database/size/size.txt", getVci());
		
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			getVci().messageSettings("Erreur lors de l'initialisation de la taille");
		}


		try {
			Capture.symbolDatabase(0, 4, "data/database/symbols/symbol", "data/database/symbols/text", getVci());
			Capture.letterDatabase(0, 3, "data/database/letters/letter", "data/database/letters/text", getVci());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getVci().messageSettings("Erreur lors de la capture des bases de données");
		}
		getClassi().addListener(getMainMenu());
		getVci().modeMenu();
	}

	

}
