package iug;

public class IUGMain {

	public static void main(String[] args) throws InterruptedException {
		
		int k = 0;
		
		ViewController vc = new ViewController();
		vc.modeMenu();
		vc.init(new ImageMenu(2), new ImageMenu(1), new ImageMenu(3));
		Thread.sleep(100);
		vc.droite(new ImageMenu(4));
		vc.gauche(new ImageMenu(3));
		vc.gauche(new ImageMenu(4));
		vc.validerMenu();
		vc.modeOption();
		vc.option(1);
		Thread.sleep(100);
		vc.modifOption1();
		Thread.sleep(100);
		vc.modifOption1();
		Thread.sleep(100);
		vc.modifOption1();
		Thread.sleep(100);
		vc.option(2);
		Thread.sleep(100);
		vc.modifOption2();
		Thread.sleep(100);
		vc.modifOption2();
		Thread.sleep(100);
		vc.option(3);
		Thread.sleep(100);
		vc.modifOption3(2200);
		Thread.sleep(100);
		vc.modifOption3(1800);
		Thread.sleep(100);
		vc.option(4);
		Thread.sleep(100);
		vc.validerOptions();
		vc.modePartie();
		vc.distribution();
		Thread.sleep(3000);
		vc.modeAnnonce();
		vc.joueurEnCours(1);
		Thread.sleep(1000);
		vc.joueurEnCours(2);
		Thread.sleep(1000);
		vc.afficheAnnonce("80", "coeur");
		Thread.sleep(1000);
		vc.actualiseAnnonce("100", "coeur");
		Thread.sleep(1000);
		vc.valideValeurAnnonce();
		Thread.sleep(1000);
		vc.actualiseAnnonce("100", "carreau");
		Thread.sleep(1000);
		vc.valideValeurAnnonce();
		vc.effaceAnnonce();
		vc.joueurEnCours(3);
		Thread.sleep(1000);
		vc.joueurEnCours(4);
		Thread.sleep(1000);
		vc.annonceJoueurDistant("", "");
		Thread.sleep(100);
		vc.joueurEnCours(1);
		Thread.sleep(100);
		vc.afficheAnnonce("110", "carreau");
		Thread.sleep(100);
		vc.actualiseAnnonce("g�n�rale", "carreau");
		Thread.sleep(100);
		vc.valideValeurAnnonce();
		Thread.sleep(100);
		vc.actualiseAnnonce("g�n�rale", "pique");
		Thread.sleep(100);
		vc.valideValeurAnnonce();
		vc.effaceAnnonce();
		vc.joueurEnCours(2);
		Thread.sleep(100);
		vc.joueurEnCours(3);
		Thread.sleep(100);
		vc.coinche(3);
		vc.modeJeu();
		vc.joueurEnCours(1);
		Thread.sleep(100);
		vc.joueurEnCours(2);
		Thread.sleep(100);
		vc.joueurEnCours(3);
		Thread.sleep(100);
		vc.joueurEnCours(4);
		Thread.sleep(100);
		vc.afficherCarte("7coeur");
		vc.joueurEnCours(1);
		Thread.sleep(100);
		vc.joueurEnCours(2);
		Thread.sleep(100);
		vc.joueurEnCours(3);
		Thread.sleep(100);
		vc.effacerCartes();
		vc.joueurEnCours(4);
		Thread.sleep(100);
		vc.afficherCarte("8trefle");
		vc.joueurEnCours(1);
		Thread.sleep(100);
		vc.joueurEnCours(2); 	
		Thread.sleep(100);
		vc.joueurEnCours(3);
		Thread.sleep(100);
		vc.effacerCartes();
		vc.joueurEnCours(4);
		Thread.sleep(100);
		vc.afficherCarte("ascarreau");
		vc.joueurEnCours(1);
		Thread.sleep(100);
		vc.joueurEnCours(2);
		Thread.sleep(100);
		vc.joueurEnCours(3);
		Thread.sleep(100);
		vc.effacerCartes();
		vc.joueurEnCours(4);
		Thread.sleep(100);
		vc.afficherCarte("roipique");
		Thread.sleep(100);
		vc.joueurEnCours(1);
		Thread.sleep(100);
		vc.modeQuitter(5);
		Thread.sleep(100);
		vc.modeQuitter(4);
		Thread.sleep(100);
		vc.modeQuitter(3);
		Thread.sleep(100);
		vc.modeQuitter(2);
		Thread.sleep(100);
		vc.modeQuitter(1);
		Thread.sleep(100);
		vc.modeMenu();
		}

}
