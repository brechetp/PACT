package leapmotion.main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;

import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;
import adaBoost.BaseApprentissage;
import adaBoost.ClassiFinal;
import adaBoost.ClassiFinauxListe;
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
		
		
		/*try {
			File fichier = new File("./adaboost/Classificateurs Finaux.ser");
			ObjectInputStream ois;
			ois = new ObjectInputStream(new FileInputStream(fichier));
			ClassiFinauxListe classilol = (ClassiFinauxListe)ois.readObject();
			EtatDuJeu etat = new EtatDuJeu();
			BeloteCoinche belote = new BeloteCoinche(etat);
			Classification classi = new Classification(classilol.get());
			classi.addListener(belote);
	        MyListener listener = new MyListener(classi);
	        Controller controller = new Controller();
	        controller.addListener(listener);
	        try {
	            System.in.read();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        controller.removeListener(listener);
	        ois.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		ClassiFinal[] classilol =BaseApprentissage.main(null);
		EtatDuJeu etat = new EtatDuJeu();
		BeloteCoinche belote = new BeloteCoinche(etat);
		Classification classi = new Classification(classilol);
		classi.addListener(belote);
        MyListener listener = new MyListener(classi);
        Controller controller = new Controller();
        controller.addListener(listener);

	}

}
