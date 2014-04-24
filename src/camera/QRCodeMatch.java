package camera;

import logiqueDeJeux.BeloteCoinche;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.qrcode.decoder.Decoder;

public class QRCodeMatch extends Match implements Runnable {

	public QRCodeMatch(IplImage imageA, IplImage imageB, IplImage largeImage,
			int compteur, BeloteCoinche belote) {
		super(imageA, imageB, largeImage, compteur, belote);
		
	}
	
	public void run() {

		Image im1 = new Image(getImageA());
		Image im2 = new Image (getImageB());
		Image largeImg = new Image(getLargeImage());
		BinaryImage bin1 = im2.differenceNeighbour(im1);
		bin1.save("data/courant/binary/bin"+(3*getCompteur())+".jpg");
		BinaryImage bin2 = im2.binaryThreshold(1).largeComponents();
		BinaryImage bin_im1 = im1.binaryThreshold(1).largeComponents();
		if (bin_im1.getCompt() > bin2.getCompt()){
			System.out.println("Une carte a été retirée");
			
		}
		bin2.save("data/courant/binary/bin"+(3*getCompteur()+1)+".jpg");
		BinaryImage bin = bin1.and(bin2);
		
		
		
		BinaryComponent bin3 = bin.largestComponent();
		bin3.save("data/courant/binary/bin"+(3*getCompteur()+2)+".jpg");
		bin3.getEdge().save("data/courant/contour/contour"+getCompteur()+".jpg");
		
		int[][] coins = bin3.getCornersRansac(3, (float)largeImg.getHeight()/im2.getHeight());
		
		Card carte = new Card(largeImg.resample(coins, 635, 889).getRgbImage()); 
		BinaryImage binCard = new BinaryImage(carte);
		carte.save("data/courant/resample/cartee"+getCompteur()+".jpg");
		String type = null;
		/*
		LuminanceSource luminanceSource = new LuminanceSource();
		Binarizer binarizer = Binarizer.createBinarizer(luminanceSource);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Decoder decoder = new Decoder();
		String type = null;
		try {
			type = decoder.decode(binCard.getBooleanMatrix()).toString();
		} catch (ChecksumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		*/
		System.out.println(type);
		
		newCard(getColorDatabase()[(int)type.charAt(0)-48][(int)type.charAt(1)-48], getValueDatabase()[(int) type.charAt(2)-48]);

	}

}
