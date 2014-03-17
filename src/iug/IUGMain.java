package iug;

public class IUGMain {

	public static void main(String[] args) {
		
		int k = 0;
		
		ViewController vc = new ViewController();

			vc.joueurEnCours(3);
			vc.contreCoinche();
			vc.afficheAnnonce("100", "carreau");
			vc.afficherCarte("7trefle");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vc.afficheAnnonce("100", "carreau");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vc.valideValeurAnnonce();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vc.actualiseAnnonce("100", "coeur");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vc.effacerCartes();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vc.modeJeu();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vc.partieTerminer();
		}

}
