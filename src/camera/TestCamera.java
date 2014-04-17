package camera;

import iug.ViewController;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;

public class TestCamera {

	public static void main(String[] args) {
		
		//Initialisation.setCardSize();
		try {
			//Capture.symbolDatabase(0, 4, "data/database/symbols/symbol", "data/database/symbols/text");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
		try {
			//Capture.letterDatabase(0, 3, "data/database/letters/letter", "data/database/letters/text");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			
		
		Initialisation.setSymbolDatabase("data/database/symbols/text");
		
		Initialisation.setLetterDatabase("data/database/letters/text");
		EtatDuJeu etat = new EtatDuJeu();
		ViewController controller = new ViewController();
		BeloteCoinche coiche = new BeloteCoinche(etat, controller);
		
		new Thread(new CaptureLive(coiche)).start();
		

	}

}
