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
		
				
		   for(int i = 10 ; i<=19; i++){
		      		Capture.captureFrame("data/capture/capt"+i+".jpg");
		      		Thread.sleep(5000);
		      		i++;
		      		Capture.captureFrame("data/capture/capt"+i+".jpg");
		      		IplImage img1 = cvLoadImage("data/capture/capt"+(i-1)+".jpg");
			      	IplImage img2 = cvLoadImage("data/capture/capt"+i+".jpg");
			      	Image image1 = new Image(img1), image2 = new Image (img2);
			      	BinaryImage bin = image1.difference(image2);
			      	
			      	cvSaveImage("data/binary/bin"+i/2+".jpg", bin.getBinaryImage());
			      	
			      	
		      		
		      	}
		      	 
		      	/* IplImage img1 = cvLoadImage("data/capture0.jpg");
		      	IplImage img2 = cvLoadImage("data/capture1.jpg");
		
		      	Image image1 = new Image(img1);
		      	Image image2 = new Image(img2);
		      	
		   
		IplImage image = cvLoadImage("data/binary/bintest.jpg");
		BinaryImage bin = new BinaryImage(new Image (image));
		
		bin.saveToText("data/text2.txt");
		
		int[][] mattest = BinaryImage.matrixFromTextFile("data/text2.txt");
		
		System.out.println(mattest[0][0]);
		
		
		
		      //	int[][] mat = new int[][]{{855,602},{1116,610},{947,484},{1023,728}};
		      
		      //	Image image3 = image2.resample(mat, 67, 95);
		      	
		        
		      
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
