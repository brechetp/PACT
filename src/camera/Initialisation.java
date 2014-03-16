package camera;

public class Initialisation {
	
	public static void setCardSize(){
		
		Capture.captureFrame("data/initialisation/fond.jpg");
		System.out.println("Le fond est pris, posez la carte blanche");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		Capture.captureFrame("data/initialisation/carteblanche.jpg");
		System.out.println("La carte blanche a �t� prise");
		
		
		Image im1 = new Image("data/initialisation/fond.jpg");
		Image im2 = new Image ("data/initialisation/carteblanche.jpg");
		
		BinaryImage bin1 = im2.difference(im1);
		//bin1.save("data/test/binary/bin1.jpg");
		BinaryImage bin2 = im2.binaryThreshold(1);
		//bin2.save("data/test/binary/bin2.jpg");
		BinaryImage bin = bin1.and(bin2);
		
		bin.save("data/test/binary/bin.jpg");
		
		BinaryComponent bin3 = bin.largestComponent();
	
		bin3.save("data/test/binaire3.jpg");
		
		int[][] coins = bin3.getCorners();
		
		Card.WIDTH= (Math.sqrt(Math.pow(coins[0][0]-coins[1][0], 2)+ Math.pow(coins[0][1]-coins[1][1],  2))+Math.sqrt(Math.pow(coins[2][0]-coins[3][0], 2)+ Math.pow(coins[2][1]-coins[3][1],  2)))/2;
		Card.HEIGHT= (Math.sqrt(Math.pow(coins[0][0]-coins[2][0], 2)+ Math.pow(coins[0][1]-coins[2][1],  2))+Math.sqrt(Math.pow(coins[1][0]-coins[3][0], 2)+ Math.pow(coins[1][1]-coins[3][1],  2)))/2;
		System.out.println("Taille initialis�e � "+ Card.WIDTH+", "+Card.HEIGHT+".");
		
	
	}

}
