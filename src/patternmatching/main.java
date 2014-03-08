package patternmatching;

import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import camera.BinaryImage;
import camera.Image;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class main {
	public static void main(String[] args) {
		
		
		IplImage img1 = cvLoadImage("data/bin.jpg");
		BinaryImage bin = new BinaryImage (new Image(img1));
		int [][] matrice = bin.getBinaryMatrix();
	
		BinaryImage bi = new BinaryImage(matrice); 
		
		//int rep = bi.conncetedComponents();
		
		int [][] rep = bi.largestComponent();
		
		BinaryImage bin2 = new BinaryImage (rep);
		
		cvLoadImage("data/bin2.jpg");
		
	}
}

	

