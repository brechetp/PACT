package camera;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_legacy.cvQueryHistValue_1D;

import java.util.ArrayList;

import com.googlecode.javacv.cpp.opencv_core.CvArr;




public class Main {


	public static void main(String[] args) throws Exception {
		
				
		  
		      	 
		      	/* IplImage img1 = cvLoadImage("data/capture0.jpg");
		      	IplImage img0 = cvLoadImage("data/database/capt0.jpg");
		      	IplImage img1 = cvLoadImage("data/database/capt1.jpg");
		      	IplImage img2 = cvLoadImage("data/database/capt2.jpg");
		      	IplImage img3 = cvLoadImage("data/database/capt3.jpg");
		      	IplImage img4 = cvLoadImage("data/database/capt4.jpg");
		      	
		
		      	Image image1 = new Image(img1);
		      	Image image0 = new Image(img0);
		      	Image image1 = new Image(img1);
		      	Image image2 = new Image(img2);
		      	Image image3 = new Image(img3);
		      	Image image4 = new Image(img4); 
		      	
		   
		//IplImage image = cvLoadImage("data/binary/bintest.jpg");
		//BinaryImage bin = new BinaryImage(new Image (image));
		
		//bin.saveToText("data/text2.txt");
		
		//int[][] mattest = BinaryImage.matrixFromTextFile("data/text2.txt");
		
		
		
		
		  int[][] mat0 = new int[][]{{862,330},{1138,342},{850,714},{1124,724}};
		    int[][] mat1 = new int[][]{{882,222},{1160,236},{870,606},{1142,620}};
		    int[][] mat2 = new int[][]{{1204,636},{928,652},{1182,252},{908,266}};
		    int[][] mat3 = new int[][]{{954,260},{1228,266},{942,646},{1216,652}};
		    int[][] mat4 = new int[][]{{926,292},{1198,272},{958,678},{1234,656}};
		    
		   Image imagebis0 = image0.resample(mat0, 635, 889);
		   Image imagebis1 = image1.resample(mat1, 635, 889);
		   Image imagebis2 = image2.resample(mat2, 635, 889);
		   Image imagebis3 = image3.resample(mat3, 635, 889);
		   Image imagebis4 = image4.resample(mat4, 635, 889);
		   
		   cvSaveImage("damecarreau.jpg", imagebis0.getRgbImage());
		   cvSaveImage("dixpique.jpg", imagebis1.getRgbImage());
		   cvSaveImage("astrefle.jpg", imagebis2.getRgbImage());
		   cvSaveImage("septcarreau.jpg", imagebis3.getRgbImage());
		   cvSaveImage("roicarreau.jpg", imagebis4.getRgbImage()); 
		   
			
			int [][] grayMatrix = (new BinaryImage(image4)).getBinaryMatrix();
			
			int [][] tab0 =  (new BinaryImage(image0)).getBinaryMatrix();  
			int [][] tab1 =(new BinaryImage(image1)).getBinaryMatrix();
			int [][] tab2 =(new BinaryImage(image2)).getBinaryMatrix();
			int [][] tab3 = (new BinaryImage(image3)).getBinaryMatrix();
			int [][] tab4 =(new BinaryImage(image4)).getBinaryMatrix();
			
			BaseDonneesCartes baseDonneesCartes = new BaseDonneesCartes(tab0, tab1, tab2, tab3, tab4);
			Comparaison imageTest = new Comparaison (grayMatrix);	
		    System.out.println(imageTest.getCardValue(baseDonneesCartes));
		    //for (int i=0; i<5; i++) {System.out.println(imageTest.matchTable[i]);}
		    //for (int i=0; i<5; i++) { System.out.println(imageTest.compare(baseDonneesCartes,i));}
		        
		      
		       /* JFrame mainframe = new JFrame();
		        mainframe.setLayout(new GridLayout(1, 1));
		        mainframe.setVisible(true);
		        
		        CanvasFrame rgb_frame = new CanvasFrame("AVI Playback Demo");        
		        mainframe.getContentPane().add(rgb_frame.getCanvas() );
		        rgb_frame.setVisible(false);
		        
		        rgb_frame.dispose(); 
		        BinaryImage binrgbvoisins = image1.difference(image2);
		        BinaryImage bingrayvoisins = grayImage1.binaryDifference(grayImage2);
		        BinaryImage binrgb = image1.difference2(image2);
		        BinaryImage bingray = grayImage1.binaryDifference2(grayImage2);
		        
		        
		    
		        cvSaveImage("data/binary/binrgbvoisins"+Compt+".jpg", binrgbvoisins.getBinaryImage());
		        cvSaveImage("data/binary/bingrayvoisins"+Compt+".jpg", bingrayvoisins.getBinaryImage());
		        cvSaveImage("data/binary/binrgb"+Compt+".jpg", binrgb.getBinaryImage());
		        cvSaveImage("data/binary/bingray"+Compt+".jpg", bingray.getBinaryImage());
		        cvSaveImage("data/binary/bingray"+Compt+".jpg", bingray.getBinaryImage());
		      	cvSaveImage("data/binary/test.jpg", image3.getRgbImage()); 
		

		IplImage img = cvLoadImage("data/bin2.jpg");
		BinaryImage bin = new BinaryImage (new Image(img));
		int [][] matrice = bin.getBinaryMatrix();
	
		BinaryImage2 bi = new BinaryImage2(matrice); 
		
		int[][] rep = bi.conncetedComponents();
		
		for (int i = 0; i < 166; i++){
			
			for (int j = 0; j < 169; j++){
				
				System.out.print(rep[j][i]);
			}
			System.out.println();
		}
		
		int [][] rep = bi.largestComponent();
		
		BinaryImage bin2 = new BinaryImage (rep);
		
		cvSaveImage("data/binbis2.jpg", bin2.getBinaryImage()); */
		
		//Capture(0, 10, "data/database/capture/capt");
		/*BinaryImage[] tab = Difference(0, 2, 2, 30,"data/database/capture/capt","data/database/binary/bin" );
		int[][] coins0 = tab[0].getCorners();
		
		
		Image img0 = new Image(tab[0].getRgbImage());
		Image resample0 = img0.resample(coins0, 635, 889);
		
		cvSaveImage("data/database/resample/resample0.jpg", resample0.getRgbImage());
		int[][] coins1 = tab[1].getCorners();
		Image img1 = new Image(tab[1].getRgbImage());
		Image resample1 = img1.resample(coins1, 635, 889);
		cvSaveImage("data/database/resample/resample1.jpg", resample1.getRgbImage());  	*/
		
		

		//Capture.database(36, 64, 20, 1, "data/capture/carte", "data/database/database5/carte");
		 /*CardDatabase database = new CardDatabase(1, 5, "data/database/carte");
		 @SuppressWarnings("unused")
		Card carte = new Card("data/database/carte4.jpg");
		  System.out.println(carte.findIn(database));*/
		
		/*
		 * Initialisation
		 * 
		 */
	//	Initialisation.setCardSize();
	//	Capture.symbolDatabase(0, 4, "data/database/symbols/symbol", "data/database/symbols/text");

		Initialisation.setSymbolDatabase("data/database/symbols/text");
		//Capture.letterDatabase(0, 3, "data/database/letters/letter", "data/database/letters/text");
		Initialisation.setLetterDatabase("data/database/letters/text");
		
		
	/*
	 * Capture de deux cartes
	 * 
	 */

		//new Thread(new CaptureLive(), ).start();
		//Capture.liveCapture();
		Thread.sleep(3000);
		Image im1 = new Image(Capture.captureFrame());
		//Image im1 = new Image("data/test/im1.jpg");
		im1.save("data/test/im1.jpg");;
		Thread.sleep(3000);
		Image im2 = new Image(Capture.captureFrame());
		//Image im2 = new Image("data/test/im2.jpg");
		im2.save("data/test/im2.jpg");
		Capture.setWidth(1920); 
		Capture.setHeight(1080);
		 Image largeImage = new Image(Capture.captureFrame());
		//Image largeImage = new Image("data/test/largeimage.jpg");
		largeImage.save("data/test/largeimage.jpg");
		
		cvSaveImage("data/test/hist.jpg", largeImage.DrawHistogram());
		
		CvHistogram hist = largeImage.getHueHistogram();
		float[][] tab = null;
	
		int accumulate = 0;
		CvArr[] arr = new CvArr[256];
		CvArr mask = null;
		double[] histTab = new double[255];
		cvNormalizeHist(hist, 1000 );
		for(int i =0; i<255; i++){
		histTab[i] = (cvQueryHistValue_1D(hist, i));
		}
		
		
		int maxId = maxId(histTab, 0, 255);
		int threshold = minId(histTab, maxId, 255);
	
		System.out.println(maxId+" "+threshold);
		afficheTab(histTab);
		
		BinaryImage bin1 = im2.differenceNeighbour(im1);
		bin1.save("data/test/binary/bin1.jpg");
		BinaryImage bin2 = im2.binaryThreshold(1);
		bin2.save("data/test/binary/bin2.jpg");
		BinaryImage bin = bin1.and(bin2);
		
		
		
		BinaryComponent bin3 = bin.largestComponent();
		bin3.save("data/test/binary/bin.jpg");
		bin3.getEdge().save("data/test/contour.jpg");
		
		int[][] coins = bin3.getCornersRansac(3, largeImage.getHeight()/im2.getHeight());
		
		Card carte = new Card(largeImage.resample(coins, 635, 889).getRgbImage());
		carte.save("data/test/cartetest.jpg");
		
		
		
	//	carte.binaryThreshold(0).getEdge().save("data/test/contour2.jpg");
		carte.binaryThreshold(0).save("data/test/binaire.jpg");
	

		
		afficheListe(carte.getSignature());
		System.out.println(carte.getType());
		
		/*new Database("data/database/database5/carte");
		System.out.println(carte.find(Database.database));
		
		//Database database = new Database("data/database/database5/carte");*/
	
		
		

		/*for (int i =1; i<64; i++){
			
			Card cartebis = new Card("data/database/database5/carte"+i+".jpg");
			System.out.println(i+" : "+cartebis.getType());
			
		}
		Image im = new Image ("data/database/carte1.jpg");
		
		BinaryImage bin = new BinaryImage(im.threshold(Image.threshold));
		bin.save("data/testtest.jpg");
		CardDatabase[] tab = new CardDatabase[6]; */
		
	/*for(int i =0; i <2; i++){
			for(int j =0; j< 5; j++){
				tab[i][j] = new CardDatabase((i*16)+(2*j+1),(i*16)+(2*j+2), "data/database/database2/carte");
			}
			for(int j =5; j< 6; j++){
				tab[i][j] = new CardDatabase((i*16)+(2*j+1),(i*16)+(2*j+6), "data/database/database2/carte");
			}
	
		} */
		
			/*System.out.println("Base de donnee");
				for(int j =0; j< 5; j++){
					tab[j] = new CardDatabase((4*j+1),(4*j+4), "data/database/database2/carte");
				}
				for(int j =5; j< 6; j++){
					tab[j] = new CardDatabase((4*j+1),(4*j+12), "data/database/database2/carte");
				}
	
				System.out.println("Base de donnee fini");*/
		
			
	
		
		//System.out.println(carte.find(Database.database));
		
		

				
				
		        
	}
	
	
	
	private static int minId(double[] histTab, int maxId, int i) {
		// TODO Auto-generated method stub
		return 0;
	}



	private static int maxId(double[] histTab, int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}



	public static void resample(int debut, int fin, String binSource, String rgbSource, String destination) throws Exception{
		
		int[][] coins ;

		for(int i = debut; i <= fin; i++){
			
			BinaryImage bin = new BinaryImage(binSource+i+".jpg");
			BinaryComponent card = bin.largestComponent();
			Image img = new Image(rgbSource+i+".jpg");
			coins = card.getCornersRansac(3, 1);
			Image resample = img.resample(coins, 635, 889);
	      	cvSaveImage(destination+i+".jpg", resample.getRgbImage());
	      	
		}
		
	}
	
	public static void afficheListe(ArrayList<Double> liste){
		for(Double current : liste){
			System.out.println(current);
			
		}
	}
	
	public static void afficheTab(double tab[]){
		for(int i =0; i<tab.length; i++){
			System.out.println(tab[i]);
			
		}
	}


}
