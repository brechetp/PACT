package camera;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Match implements Runnable {
	
	private IplImage imageA, imageB;
	
	public Match(IplImage imageA, IplImage imageB){
		
		this.imageA = imageA;
		this.imageB = imageB;
	}

	@Override
	public void run() {

		Image im1 = new Image(imageA);
		Image im2 = new Image (imageB);
		
		BinaryImage bin1 = im2.differenceNeighbour(im1);
		bin1.save("data/test/binary/bin1.jpg");
		BinaryImage bin2 = im2.binaryThreshold(1);
		bin2.save("data/test/binary/bin2.jpg");
		BinaryImage bin = bin1.and(bin2);
		
		
		
		BinaryComponent bin3 = bin.largeComponents();
		bin3.save("data/test/binary/bin.jpg");
		bin3.getEdge().save("data/test/contour.jpg");
		
		int[][] coins = bin3.getCornersRansac(3);
		
		Card carte = new Card(im2.resample(coins, 635, 889).getRgbImage()); 
		carte.save("data/test/cartetest.jpg");
		
		
		carte.binaryThreshold(0).getEdge().save("data/test/contour2.jpg");
		carte.binaryThreshold(0).save("data/test/binaire.jpg");
	
		
	
		
		System.out.println(carte.getType());

	}

}
