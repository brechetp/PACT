package leapmotion.main;
import java.io.IOException;

import javax.swing.JFrame;

import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;
import adaBoost.Classification;

import com.leapmotion.leap.Controller;

public class LeapMotionMain {

	public static void main(String[] args) {
		
		// Création d'une fenêtre pour un usage plus convivial
		KListener kk = new KListener();
		JFrame fenetre = new JFrame();
		fenetre.addKeyListener(kk);
		fenetre.setVisible(true);
		fenetre.setSize(100, 50);
		fenetre.setLocation(25, 25);
		fenetre.setTitle("Leap Motion Control Panel");
		fenetre.setResizable(false);
		
		
		
		 // Create a sample listener and controller
		EtatDuJeu etat = new EtatDuJeu();
		BeloteCoinche belote = new BeloteCoinche(etat);
		Classification classi = new Classification(null);
		classi.addListener(belote);
        MyListener listener = new MyListener(classi); // Erreur logique parce que il n'y a pas de classification ici et le constructeur a ete modifie depuis
        Controller controller = new Controller();
        
        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);


		


	}

}
