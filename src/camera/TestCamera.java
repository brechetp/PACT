package camera;

import iug.ViewController;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;

public class TestCamera {

	public static void main(String[] args) {
		

		EtatDuJeu etat = new EtatDuJeu();
		ViewController controller = new ViewController();
		BeloteCoinche coiche = new BeloteCoinche(etat, controller);
		
		new Thread(new CaptureLive(coiche)).start();
		

	}

}
