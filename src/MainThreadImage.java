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
		new Database();
		while (true)
		{
		
			try {
				Thread.sleep(15000);
				System.out.println("Photo prise");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Capture.captureFrame("data/test/capture.jpg");
			Image im2 = new Image ("data/test/capture.jpg");
			
			BinaryImage bin = im2.difference(fond);
			
			BinaryImage bin2 = bin.largestComponent();
			
			int[][] coins = bin2.getCorners();
			
			try {
				Card carte = new Card(im2.resample(coins, 635, 889).getRgbImage());
				new Thread(new ThreadComparaison(carte,belote)).start();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

}