package serveur;

import java.io.IOException;

import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;
import machineEtat.CardEvent;
import structure.Annonce;
import structure.Carte;

public class testServeur 
{

	public static void main(String[] args) throws IOException 
	{
		EtatDuJeu etat = null;
		BeloteCoinche belote = null;
		Serveur serveur = new Serveur(belote);
		serveur.start();
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

		try {
			Thread.sleep(100);
			serveur.envoiCarteDistribution(AsTr);
			Thread.sleep(300);
			serveur.envoiCarteDistribution(AsPi);
			Thread.sleep(300);
			serveur.envoiCarteDistribution(NeuPi);
			Thread.sleep(300);
			serveur.envoiCarteDistribution(ValPi);
			Thread.sleep(300);
			serveur.envoiCarteDistribution(RenTr);
			Thread.sleep(300);
			serveur.envoiCarteDistribution(RoiCo);
			Thread.sleep(300);
			serveur.envoiCarteDistribution(HuiCa);
			Thread.sleep(300);
			serveur.envoiCarteDistribution(DixCa);
			Thread.sleep(1000);
			System.out.println("Envoi terminer");
			serveur.envoiAnnonce(null, 1);
			Thread.sleep(2000);
			serveur.envoiAnnonce(new Annonce("coeur", 80, 1), 2);
			Thread.sleep(2000);
			serveur.envoiAnnonce(new Annonce("trefle", 90, 1), 3);
			Thread.sleep(100);
			serveur.envoiTourAnnonce();
			Thread.sleep(5000);
			serveur.envoiAnnonce(null, 1);
			Thread.sleep(2000);
			serveur.envoiAnnonce(null, 2);
			Thread.sleep(2000);
			serveur.envoiAnnonce(null, 3);
			Thread.sleep(1);
			serveur.envoiFinAnnonce();
			System.out.println("fin des annonces");
			Thread.sleep(3000);
			serveur.envoiCarte(DixTr, 1);
			Thread.sleep(3000);
			serveur.envoiCarte(SepTr, 2);
			Thread.sleep(3000);
			serveur.envoiCarte(HuiTr, 3);
			System.out.println("Fin envoi carte");
			Thread.sleep(100);
			serveur.waitCarte();
			Thread.sleep(5000);
			serveur.envoiFinDeTour(1);
			System.out.println("Fin de tour");
			Thread.sleep(3000);
			serveur.envoiCarte(DixTr, 1);
			Thread.sleep(3000);
			serveur.envoiCarte(SepTr, 2);
			Thread.sleep(3000);
			serveur.envoiCarte(HuiTr, 3);
			System.out.println("Fin envoi carte");
			Thread.sleep(100);
			serveur.waitCarte();
			Thread.sleep(5000);
			serveur.envoiFinDeTour(1);
			System.out.println("Fin de tour");
			Thread.sleep(3000);
			serveur.envoiCarte(DixTr, 1);
			Thread.sleep(3000);
			serveur.envoiCarte(SepTr, 2);
			Thread.sleep(3000);
			serveur.envoiCarte(HuiTr, 3);
			System.out.println("Fin envoi carte");
			Thread.sleep(100);
			serveur.waitCarte();
			Thread.sleep(5000);
			serveur.envoiFinDeTour(1);
			System.out.println("Fin de tour");
			Thread.sleep(3000);
			serveur.envoiCarte(DixTr, 1);
			Thread.sleep(3000);
			serveur.envoiCarte(SepTr, 2);
			Thread.sleep(3000);
			serveur.envoiCarte(HuiTr, 3);
			System.out.println("Fin envoi carte");
			Thread.sleep(100);
			serveur.waitCarte();
			Thread.sleep(5000);
			serveur.envoiFinDeTour(1);
			System.out.println("Fin de tour");
			Thread.sleep(3000);
			serveur.envoiCarte(DixTr, 1);
			Thread.sleep(3000);
			serveur.envoiCarte(SepTr, 2);
			Thread.sleep(3000);
			serveur.envoiCarte(HuiTr, 3);
			System.out.println("Fin envoi carte");
			Thread.sleep(100);
			serveur.waitCarte();
			Thread.sleep(5000);
			serveur.envoiFinDeTour(1);
			System.out.println("Fin de tour");
			Thread.sleep(3000);
			serveur.envoiCarte(DixTr, 1);
			Thread.sleep(3000);
			serveur.envoiCarte(SepTr, 2);
			Thread.sleep(3000);
			serveur.envoiCarte(HuiTr, 3);
			System.out.println("Fin envoi carte");
			Thread.sleep(100);
			serveur.waitCarte();
			Thread.sleep(5000);
			serveur.envoiFinDeTour(1);
			System.out.println("Fin de tour");
			Thread.sleep(3000);
			serveur.envoiCarte(DixTr, 1);
			Thread.sleep(3000);
			serveur.envoiCarte(SepTr, 2);
			Thread.sleep(3000);
			serveur.envoiCarte(HuiTr, 3);
			System.out.println("Fin envoi carte");
			Thread.sleep(100);
			serveur.waitCarte();
			Thread.sleep(5000);
			serveur.envoiFinDeTour(1);
			System.out.println("Fin de tour");
			Thread.sleep(3000);
			serveur.envoiCarte(SepCa, 1);
			Thread.sleep(3000);
			serveur.envoiCarte(RoiCa, 2);
			Thread.sleep(3000);
			serveur.envoiCarte(NeuCa, 3);
			System.out.println("Fin envoi carte");
			Thread.sleep(100);
			serveur.waitCarte();
			Thread.sleep(5000);
			serveur.envoiFinDeTour(1);
			System.out.println("Fin de tour");
			
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
