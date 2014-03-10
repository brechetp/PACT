import structure.*;
import logiqueDeJeux.*;
import machineEtat.*;


public class PremierTest {

	public static void main(String[] args) 
	{
		
		EtatDuJeu etat = new EtatDuJeu();
		BeloteCoinche belote = new BeloteCoinche(etat);
		
/******************************* Mouvement ***********************************************/
		MouvementEvent passer = new MouvementEvent("passer");
		MouvementEvent quitter = new MouvementEvent("quitter");
		MouvementEvent retour = new MouvementEvent("retour");
		MouvementEvent accepter = new MouvementEvent("accepter");
		MouvementEvent coinche = new MouvementEvent("coinche");
		
/*******************************Creation des carte event***********************************/
		
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
		belote.nouvelleCarte(NeuCaEvent);
		belote.nouvelleCarte(RenPiEvent);
		belote.nouvelleCarte(NeuPiEvent);
		belote.nouvelleCarte(AsTrEvent);
		belote.nouvelleCarte(AsCoEvent);
		belote.nouvelleCarte(ValCoEvent);
		belote.nouvelleCarte(DixCoEvent);
		try {
			Thread.sleep(1000);
			belote.nouveauGeste(passer); //joueur 1 passe
			Thread.sleep(1000);
			belote.nouveauGeste(passer); //joueur 2 passe
			Thread.sleep(1000);
			belote.nouveauGeste(accepter);
			Thread.sleep(1000);
			belote.nouveauGeste(accepter);
			Thread.sleep(1000);
			belote.nouveauGeste(passer);
			Thread.sleep(1000);
			belote.nouveauGeste(passer);
			Thread.sleep(1000);
			belote.nouveauGeste(accepter); //joueur 3 prend a 80 pique
			Thread.sleep(1000);
			belote.setAnnonce(null); //joueur 4 passe
			Thread.sleep(1000);
			belote.nouveauGeste(accepter);
			Thread.sleep(1000);
			belote.nouveauGeste(passer);
			Thread.sleep(1000);
			belote.nouveauGeste(accepter);
			Thread.sleep(1000);
			belote.nouveauGeste(passer);
			Thread.sleep(1000);
			belote.nouveauGeste(passer);
			Thread.sleep(1000);
			belote.nouveauGeste(accepter); // joueur 1 prend a 100 carreau
			Thread.sleep(1000);
			belote.nouveauGeste(passer); //Joueur 2 passer
			Thread.sleep(1000);
			belote.nouveauGeste(passer); //Joueur 3 passer
			Thread.sleep(1000);
			belote.setAnnonce(null); //Joueur 4 passe
			Thread.sleep(1000);
			
			belote.nouvelleCarte(ValCaEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(HuiCaEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(RoiCaEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(SepCaEvent);
			Thread.sleep(1000);
			belote.finPli();
			Thread.sleep(1000);
			
			belote.nouvelleCarte(NeuCaEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(RenCaEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(DixCaEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(AsCaEvent);
			Thread.sleep(1000);
			belote.finPli();
			Thread.sleep(1000);
			
			belote.nouvelleCarte(AsTrEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(ValTrEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(DixTrEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(SepTrEvent);
			Thread.sleep(1000);
			belote.finPli();
			Thread.sleep(1000);
			
			belote.nouvelleCarte(AsCoEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(SepCoEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(RenCoEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(RoiCoEvent);
			Thread.sleep(1000);
			belote.finPli();
			Thread.sleep(1000);
			
			belote.nouvelleCarte(DixCoEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(NeuCoEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(RoiPiEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(SepPiEvent);
			Thread.sleep(1000);
			belote.finPli();
			Thread.sleep(1000);
			
			belote.nouvelleCarte(ValCoEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(HuiCoEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(ValPiEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(HuiTrEvent);
			Thread.sleep(1000);
			belote.finPli();
			Thread.sleep(1000);
			
			belote.nouvelleCarte(RenPiEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(HuiPiEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(NeuTrEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(AsPiEvent);
			Thread.sleep(1000);
			belote.finPli();
			Thread.sleep(1000);
			
			belote.nouvelleCarte(RenTrEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(NeuPiEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(DixPiEvent);
			Thread.sleep(1000);
			belote.nouvelleCarte(RoiTrEvent);
			Thread.sleep(1000);
			belote.finPli();
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}               
}             
              