import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import structure.Carte;

import com.leapmotion.leap.Controller;

import adaBoost.BaseApprentissage;
import adaBoost.ClassiFinal;
import adaBoost.Classification;
import leapmotion.main.ListeDeMouvements;
import leapmotion.main.MyListener;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;
import machineEtat.CardEvent;


public class PremierMain {

	public static void main(String[] args) throws InterruptedException 
	{
		
		//Creation base de classi
		ClassiFinal[] classilol = BaseApprentissage.main(null);
		//Cration logique de jeu
		EtatDuJeu etat = new EtatDuJeu();
		BeloteCoinche belote = new BeloteCoinche(etat);
		Classification classi = new Classification(classilol);
		classi.addListener(belote);
		//Start Leap
        MyListener listener = new MyListener(classi);
        Controller controller = new Controller();
        controller.addListener(listener);
        //Start Camera
        new Thread(new MainThreadImage(belote)).start();
        
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
        
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
