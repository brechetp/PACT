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
		
		
		      	int nbr = Capture.captureFrame();
		      	IplImage image1 = cvLoadImage("data/capture0.jpg");
		      	IplImage image2 = cvLoadImage("data/capture1.jpg");
		        GrayImage grayImage1 = new GrayImage(image1);
		        GrayImage grayImage2 = new GrayImage(image2);
		       
		        JFrame mainframe = new JFrame();
		        mainframe.setLayout(new GridLayout(1, 1));
		        mainframe.setVisible(true);
		        
		        CanvasFrame rgb_frame = new CanvasFrame("AVI Playback Demo");        
		        mainframe.getContentPane().add(rgb_frame.getCanvas() );
		        rgb_frame.setVisible(false);
		        
		        rgb_frame.dispose();
		        BinaryImage bin = grayImage1.difference(grayImage2);
		        cvSaveImage("data/bin.jpg", bin.getImage());
		        cvSaveImage("data/gray0.jpg", grayImage1.getGrayImage());
		        cvSaveImage("data/gray1.jpg", grayImage2.getGrayImage());
		        int[][] mat = {{1,2,1},{2,4,1},{1,2,1}};
		        GrayImage test = grayImage1.conv(mat);
		        cvSaveImage("data/gray2.jpg", test.getGrayImage());
		        rgb_frame.showImage(bin.getImage());
		       
	
				
				
		        
		    }


	

}
