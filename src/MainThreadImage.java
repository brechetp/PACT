
import logiqueDeJeux.BeloteCoinche;
import camera.BinaryComponent;
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
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			compt = compt + 1 % 10;
			//mettre un test qu'il n'y a pas de main dans la zone ici: par exemple un bool a false quand la leap detecte et true sinon
			Capture.captureFrame("data/test/capture"+compt+".jpg");
			System.out.println("Photo prise");
			
			Image im2 = new Image ("data/test/capture"+compt+".jpg");
			
			BinaryImage bin = im2.detect(fond, 30, 1);
			
			bin.save("data/test/binary/binary"+compt+".jpg");
			
			Capture.captureFrame("data/test/fond"+compt+".jpg");
			System.out.println("fond pris !");
			fond= new Image ("data/test/fond"+compt+".jpg");
			
			BinaryComponent bin2 = bin.largestComponent();
			try{
				
				int[][] coins = bin2.getCorners();
				Card carte = new Card(im2.resample(coins, 635, 889).getRgbImage());
				carte.save("data/test/resample/resample"+compt+".jpg");
				carte.binaryThreshold(0).cut(50, 50, 535, 789).save("data/test/binary/binary"+compt+".jpg");
				new Comparaison(carte,belote).run(); //retire la thread pour éviter les confusion
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

}
