package camera;

import java.io.FileNotFoundException;
import java.io.IOException;

import iug.ViewController;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;

public class TestCamera {

	public static void main(String[] args) {

		
		try {
			//Capture.cardSize("data/database/size/");
			Initialisation.setCardSize("data/database/size/size.txt");
		
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			//Capture.symbolDatabase(0, 4, "data/database/symbols/symbol", "data/database/symbols/text");
			//Capture.letterDatabase(0, 3, "data/database/letters/letter", "data/database/letters/text");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	

		try { // Initialisation de la base des bases de données
		
			Initialisation.setSymbolDatabase("data/database/symbols/text");
			Initialisation.setLetterDatabase("data/database/letters/text");
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	

//		ViewController controller = new ViewController();
//		BeloteCoinche coiche = null;
//		try {
//			coiche = new BeloteCoinche(controller, null);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		new Thread(new CaptureLive(/*coiche*/)).start();


	}

}
