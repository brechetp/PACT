package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.awt.GridLayout;

import javax.swing.JFrame;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_core.CvMat;

import static com.googlecode.javacv.cpp.opencv_highgui.*;



public class Main {

	public static void main(String[] args) throws Exception {
		
				
		   for(int i = 0 ; i<=1; i++){
		      		Capture.captureFrame("data/capturebis"+i+".jpg");
		      		
		      		
		      	}
		      	/*IplImage img1 = cvLoadImage("data/capture0.jpg");
		      	IplImage img2 = cvLoadImage("data/capture1.jpg");
		
		      	Image image1 = new Image(img1);
		      	Image image2 = new Image(img2);
		        
		      	GrayImage grayImage1 = new GrayImage(img1);
		      	GrayImage grayImage2 = new GrayImage(img2);
		       
		       /* JFrame mainframe = new JFrame();
		        mainframe.setLayout(new GridLayout(1, 1));
		        mainframe.setVisible(true);
		        
		        CanvasFrame rgb_frame = new CanvasFrame("AVI Playback Demo");        
		        mainframe.getContentPane().add(rgb_frame.getCanvas() );
		        rgb_frame.setVisible(false);
		        
		       // rgb_frame.dispose();
		        BinaryImage binrgbvoisins = image1.difference(image2);
		        BinaryImage bingrayvoisins = grayImage1.binaryDifference(grayImage2);
		        BinaryImage binrgb = image1.difference2(image2);
		        BinaryImage bingray = grayImage1.binaryDifference2(grayImage2);
		        
		        
		    
		        cvSaveImage("data/binrgbvoisins.jpg", binrgbvoisins.getBinaryImage());
		        cvSaveImage("data/bingrayvoisins.jpg", bingrayvoisins.getBinaryImage());
		        cvSaveImage("data/binrgb.jpg", binrgb.getBinaryImage());
		        cvSaveImage("data/bingray.jpg", bingray.getBinaryImage());*/
		     
		    
		
		   
	
				
				
		        
		    }


	

}
