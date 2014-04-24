package camera;

import structure.Carte;
import logiqueDeJeux.BeloteCoinche;
import machineEtat.CardEvent;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Match implements Runnable {
	
	private static final int COEFF = 10;
	private static final String[][] COLOR_DATABASE = new String[][]{{"trefle", "pique"},{"carreau", "coeur"}};
	private static final String[] VALUE_DATABASE = {"as", "7", "8", "9", "10", "valet", "reine", "roi"};
	private IplImage imageA, imageB, largeImage;
	private int compteur;
	private BeloteCoinche belote;
	
	public Match(IplImage imageA, IplImage imageB, IplImage largeImage, int compteur, BeloteCoinche belote){
		
		this.imageA = imageA;
		this.imageB = imageB;
		this.compteur = compteur%100;
		this.largeImage = largeImage;
		this.belote = belote;
	}

	@Override
	public void run() {

		Image im1 = new Image(imageA);
		Image im2 = new Image (imageB);
		Image largeImg = new Image(largeImage);
		BinaryImage bin1 = im2.differenceNeighbour(im1);
		bin1.save("data/courant/binary/bin"+(3*compteur)+".jpg");
		BinaryImage bin2 = im2.binaryThreshold(1).largeComponents();
		BinaryImage bin_im1 = im1.binaryThreshold(1).largeComponents();
		if (bin_im1.getCompt() > bin2.getCompt()){
			System.out.println("Une carte a été retirée");
			
		}
		bin2.save("data/courant/binary/bin"+(3*compteur+1)+".jpg");
		BinaryImage bin = bin1.and(bin2);
		
		
		
		BinaryComponent bin3 = bin.largestComponent();
		bin3.save("data/courant/binary/bin"+(3*compteur+2)+".jpg");
		bin3.getEdge().save("data/courant/contour/contour"+compteur+".jpg");
		
		int[][] coins = bin3.getCornersRansac(3, (float)largeImg.getHeight()/im2.getHeight());
		
		Card carte = new Card(largeImg.resample(coins, 635, 889).getRgbImage()); 
		carte.save("data/courant/resample/carte"+compteur+".jpg");
		//carte.getCorner().binaryThreshold(0).save("data/courant/resample/coins"+compteur+".jpg");
		new BinaryImage(carte.getSymbol().getMatrix()).save("data/courant/resample/symbol"+compteur+".jpg");
	
		
		String type = carte.getType();
		System.out.println(type);
		
		newCard(COLOR_DATABASE[(int)type.charAt(0)-48][(int)type.charAt(1)-48], VALUE_DATABASE[(int) type.charAt(2)-48]);

	}
	
	
	
	protected void newCard(String color, String value){
	
		belote.nouvelleCarte(new CardEvent(new Carte(value,color, belote.getEtat())));
	}

	public IplImage getImageA() {
		return imageA;
	}

	public void setImageA(IplImage imageA) {
		this.imageA = imageA;
	}

	public IplImage getImageB() {
		return imageB;
	}

	public void setImageB(IplImage imageB) {
		this.imageB = imageB;
	}

	public IplImage getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(IplImage largeImage) {
		this.largeImage = largeImage;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public static String[][] getColorDatabase() {
		return COLOR_DATABASE;
	}

	public static String[] getValueDatabase() {
		return VALUE_DATABASE;
	}

}
