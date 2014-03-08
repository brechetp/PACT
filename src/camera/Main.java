package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.awt.GridLayout;

import javax.swing.JFrame;




import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_core.CvMat;

import comparaison.BaseDonneesCartes;
import comparaison.Comparaison;
import static com.googlecode.javacv.cpp.opencv_highgui.*;



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
		
		
	
		difference(0, 43, 1, 20, 0, "data/capture/carte", "data/binary/carte");
		resample(0, 43, "data/binary/carte", "data/capture/carte", "data/database/carte");
		   
	
				
				
		        
		    }
	
	public static void difference(int debut, int fin, int pas, int threshold, int nbr, String source, String destination){
		
		for(int i = debut; i <= fin; i+=pas){ // pas = 1 ou 2
			
			IplImage img0 = cvLoadImage("data/capture/fond0.jpg");
			IplImage img1 = cvLoadImage(source+i+".jpg");
			Image image0 = new Image(img0);
	      	Image image1 = new Image(img1);
	      	BinaryImage bin = image1.differenceNeighbour(image0, threshold, nbr);
	      	bin.setRgbImage(img1);
	      	bin.save(destination+(i/pas)+".jpg");
	      	
	      	
		}
		
	
		
	}
	
	public static void resample(int debut, int fin, String binSource, String rgbSource, String destination){
		
		int[][] coins ;

		for(int i = debut; i <= fin; i++){
			
			BinaryImage bin = new BinaryImage(binSource+i+".jpg");
			BinaryImage card = bin.largestComponent();
			Image img = new Image(rgbSource+i+".jpg");
			coins = card.getCorners();
			Image resample = img.resample(coins, 635, 889);
	      	cvSaveImage(destination+i+".jpg", resample.getRgbImage());
	      	
		}
		
	}


}
