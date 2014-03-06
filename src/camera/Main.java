package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.awt.GridLayout;

import javax.swing.JFrame;

import patternmatching.BinaryImage2;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_core.CvMat;

import static com.googlecode.javacv.cpp.opencv_highgui.*;



public class Main {
	static int Compt = 0 ;

	public static void main(String[] args) throws Exception {
		
				
		  /* for(int i = 0; i<=4; i++){
		      		Capture.captureFrame("data/database/capt"+i+".jpg");
		      	
		      		
		      		Thread.sleep(5000);
		      		/*i++;
		      		Capture.captureFrame("data/capture/capt"+i+".jpg");
		      		IplImage img1 = cvLoadImage("data/database/capt"+(i-1)+".jpg");
			      	IplImage img2 = cvLoadImage("data/capture/capt"+i+".jpg");
			      	Image image1 = new Image(img1), image2 = new Image (img2);
			      	BinaryImage bin = image1.differenceNeighbour(image2);
			      	
			      	cvSaveImage("data/binary/bin"+i/2+".jpg", bin.getBinaryImage());
			      	
			      	
		      		
		      	}*/
		      	 
		      	/* IplImage img1 = cvLoadImage("data/capture0.jpg");*/
		      	IplImage img0 = cvLoadImage("data/database/capt0.jpg");
		      	IplImage img1 = cvLoadImage("data/database/capt1.jpg");
		      	IplImage img2 = cvLoadImage("data/database/capt2.jpg");
		      	IplImage img3 = cvLoadImage("data/database/capt3.jpg");
		      	IplImage img4 = cvLoadImage("data/database/capt4.jpg");
		      	
		
		      	/*Image image1 = new Image(img1);*/
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
		    int[][] mat2 = new int[][]{{908,266},{1182,252},{928,652},{1204,636}};
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
		

		IplImage img1 = cvLoadImage("data/binbis.jpg");
		BinaryImage bin = new BinaryImage (new Image(img1));
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
		
		cvSaveImage("data/bin2.jpg", bin2.getBinaryImage()); */
		
		      	
		      	
		      
		     
		    
		
		   
	
				
				
		        
		    }


	

}
