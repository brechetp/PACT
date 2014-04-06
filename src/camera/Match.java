package camera;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Match implements Runnable {
	
	private IplImage imageA, imageB;
	private int compteur;
	
	public Match(IplImage imageA, IplImage imageB, int compteur){
		
		this.imageA = imageA;
		this.imageB = imageB;
		this.compteur = compteur%100;
	}

	@Override
	public void run() {

		Image im1 = new Image(imageA);
		Image im2 = new Image (imageB);
		BinaryImage bin1 = im2.differenceNeighbour(im1);
		bin1.save("data/test/binary/bin"+(3*compteur)+".jpg");
		BinaryImage bin2 = im2.binaryThreshold(1).largeComponents();
		bin2.save("data/test/binary/bin"+(3*compteur+1)+".jpg");
		BinaryImage bin = bin1.and(bin2);
		
		
		
		BinaryComponent bin3 = bin.largestComponent();
		bin3.save("data/test/binary/bin"+(3*compteur+2)+".jpg");
		bin3.getEdge().save("data/test/contour/contour"+compteur+".jpg");
		
		int[][] coins = bin3.getCornersRansac(3);
		
		Card carte = new Card(im2.resample(coins, 635, 889).getRgbImage()); 
		carte.save("data/test/resample/carte"+compteur+".jpg");

	
		
		System.out.println(carte.getType());

	}

}
