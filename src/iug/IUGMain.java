package iug;

public class IUGMain {

	public static void main(String[] args) {
		
		int k = 0;
		
		ViewController vc = new ViewController();

			vc.joueurEnCours(3);
			vc.coinche(2);
			vc.afficherCarte("astrefle");
			try {
				Thread.sleep(5000);
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
			
			vc.partieTermin�e();
		}

}