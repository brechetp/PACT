package leapmotion.main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;

import structure.Carte;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;
import machineEtat.CardEvent;
import adaBoost.BaseApprentissage;
import adaBoost.ClassiFinal;
import adaBoost.ClassiFinauxListe;
import adaBoost.Classification;

import com.leapmotion.leap.Controller;

public class LeapMotionMain {

	public static void main(String[] args) throws InterruptedException {
		
		// Création d'une fenêtre pour un usage plus convivial
		KListener kk = new KListener();
		JFrame fenetre = new JFrame();
		fenetre.addKeyListener(kk);
		fenetre.setVisible(true);
		fenetre.setSize(100, 50);
		fenetre.setLocation(25, 25);
		fenetre.setTitle("Leap Motion Control Panel");
		fenetre.setResizable(false);
		
		
		try {
			File fichier = new File("./adaboost/Classificateurs Finaux.ser");
			ObjectInputStream ois;
			ois = new ObjectInputStream(new FileInputStream(fichier));
			ClassiFinauxListe classilol = (ClassiFinauxListe)ois.readObject();
			
			BeloteCoinche belote = new BeloteCoinche(null, null);
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
		}
		
		/*ClassiFinal[] classilol = BaseApprentissage.main(null);
		EtatDuJeu etat = new EtatDuJeu();
		BeloteCoinche belote = new BeloteCoinche(etat, null);
		Classification classi = new Classification(classilol);
		classi.addListener(belote);*/
        MyListener listener = new MyListener();
        Controller controller = new Controller();
        controller.addListener(listener);
        /*
        Carte RoiCa = new Carte("roi","carreau",etat);
		CardEvent RoiCaEvent = new CardEvent(RoiCa);
		Carte RoiCo = new Carte("roi","coeur",etat);
		CardEvent RoiCoEvent = new CardEvent(RoiCo);
		Carte RoiPi = new Carte("roi","pique",etat);
		CardEvent RoiPiEvent = new CardEvent(RoiPi);
		Carte RoiTr = new Carte("roi","trefle",etat);
		CardEvent RoiTrEvent = new CardEvent(RoiTr);
		
		Carte RenCa = new Carte("reine","carreau",etat);
		CardEvent RenCaEvent = new CardEvent(RenCa);
		Carte RenCo = new Carte("reine","coeur",etat);
		CardEvent RenCoEvent = new CardEvent(RenCo);
		Carte RenPi = new Carte("reine","pique",etat);
		CardEvent RenPiEvent = new CardEvent(RenPi);
		Carte RenTr = new Carte("reine","trefle",etat);
		CardEvent RenTrEvent = new CardEvent(RenTr);
		
		Carte ValCa = new Carte("valet","carreau",etat);
		CardEvent ValCaEvent = new CardEvent(ValCa);
		Carte ValCo = new Carte("valet","coeur",etat);
		CardEvent ValCoEvent = new CardEvent(ValCo);
		Carte ValPi = new Carte("valet","pique",etat);
		CardEvent ValPiEvent = new CardEvent(ValPi);
		Carte ValTr = new Carte("valet","trefle",etat);
		CardEvent ValTrEvent = new CardEvent(ValTr);
		
		Carte DixCa = new Carte("10","carreau",etat);
		CardEvent DixCaEvent = new CardEvent(DixCa);
		Carte DixCo = new Carte("10","coeur",etat);
		CardEvent DixCoEvent = new CardEvent(DixCo);
		Carte DixPi = new Carte("10","pique",etat);
		CardEvent DixPiEvent = new CardEvent(DixPi);
		Carte DixTr = new Carte("10","trefle",etat);
		CardEvent DixTrEvent = new CardEvent(DixTr);
		
		Carte NeuCa = new Carte("9","carreau",etat);
		CardEvent NeuCaEvent = new CardEvent(NeuCa);
		Carte NeuCo = new Carte("9","coeur",etat);
		CardEvent NeuCoEvent = new CardEvent(NeuCo);
		Carte NeuPi = new Carte("9","pique",etat);
		CardEvent NeuPiEvent = new CardEvent(NeuPi);
		Carte NeuTr = new Carte("9","trefle",etat);
		CardEvent NeuTrEvent = new CardEvent(NeuTr);
		
		Carte HuiCa = new Carte("8","carreau",etat);
		CardEvent HuiCaEvent = new CardEvent(HuiCa);
		Carte HuiCo = new Carte("8","coeur",etat);
		CardEvent HuiCoEvent = new CardEvent(HuiCo);
		Carte HuiPi = new Carte("8","pique",etat);
		CardEvent HuiPiEvent = new CardEvent(HuiPi);
		Carte HuiTr = new Carte("8","trefle",etat);
		CardEvent HuiTrEvent = new CardEvent(HuiTr);
		
		Carte SepCa = new Carte("7","carreau",etat);
		CardEvent SepCaEvent = new CardEvent(SepCa);
		Carte SepCo = new Carte("7","coeur",etat);
		CardEvent SepCoEvent = new CardEvent(SepCo);
		Carte SepPi = new Carte("7","pique",etat);
		CardEvent SepPiEvent = new CardEvent(SepPi);
		Carte SepTr = new Carte("7","trefle",etat);
		CardEvent SepTrEvent = new CardEvent(SepTr);
		
		Carte AsCa = new Carte("as","carreau",etat);
		CardEvent AsCaEvent = new CardEvent(AsCa);
		Carte AsCo = new Carte("as","coeur",etat);
		CardEvent AsCoEvent = new CardEvent(AsCo);
		Carte AsPi = new Carte("as","pique",etat);
		CardEvent AsPiEvent = new CardEvent(AsPi);
		Carte AsTr = new Carte("as","trefle",etat);
		CardEvent AsTrEvent = new CardEvent(AsTr);
		
//Distribution
		belote.nouvelleCarte(ValCaEvent);
		Thread.sleep(100);
		belote.nouvelleCarte(NeuCaEvent);
		Thread.sleep(100);
		belote.nouvelleCarte(RenPiEvent);
		Thread.sleep(100);
		belote.nouvelleCarte(NeuPiEvent);
		Thread.sleep(100);
		belote.nouvelleCarte(AsTrEvent);
		Thread.sleep(100);
		belote.nouvelleCarte(AsCoEvent);
		Thread.sleep(100);
		belote.nouvelleCarte(ValCoEvent);
		Thread.sleep(100);
		belote.nouvelleCarte(DixCoEvent);
		Thread.sleep(100);
        */
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
