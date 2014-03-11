import logiqueDeJeux.BeloteCoinche;
import camera.BinaryImage;
import camera.Capture;
import camera.Card;
import camera.Database;
import camera.Image;


public class MainThreadImage implements Runnable{
	
	private Image fond;
	private BeloteCoinche belote;
	
	public MainThreadImage(BeloteCoinche belote)
	{
		Capture.captureFrame("data/test/fond.jpg");
		System.out.println("fond pris !");
		fond= new Image ("data/test/fond.jpg");
		this.belote = belote;
	}

	@Override
	public void run() 
	{
		int compt =0;
		new Database("data/database/database5/carte");
		while (true)
		{
			compt++;
			try {
				Thread.sleep(10000);
				System.out.println("Photo prise");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Capture.captureFrame("data/test/capture"+compt+".jpg");
			Image im2 = new Image ("data/test/capture"+compt+".jpg");
			
			BinaryImage bin = im2.difference(fond);
			
			BinaryImage bin2 = bin.largestComponent();
			
			int[][] coins = bin2.getCorners();
			
			try {
				Card carte = new Card(im2.resample(coins, 635, 889).getRgbImage());
				carte.save("data/test/resample/resample"+compt+".jpg");
				carte.binaryThreshold().cut(50, 50, 535, 789).save("data/test/binary/binary"+compt+".jpg");
				new Comparaison(carte,belote).run(); //retire la thread pour éviter les confusion
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

}
