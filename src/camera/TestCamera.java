package camera;

import java.io.IOException;

import iug.ViewController;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;

public class TestCamera {

	public static void main(String[] args) {

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




		try {
			Initialisation.setCardSize("data/database/size/size.txt");
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Initialisation.setSymbolDatabase("data/database/symbols/text");

		Initialisation.setLetterDatabase("data/database/letters/text");

		ViewController controller = new ViewController();
		BeloteCoinche coiche = null;
		try {
			coiche = new BeloteCoinche(controller, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Thread(new CaptureLive(coiche)).start();


	}

}
