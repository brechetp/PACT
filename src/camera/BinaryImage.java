package camera;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.awt.GridLayout;

import javax.swing.JFrame;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_core.CvMat;

import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.nio.ByteBuffer;
import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.nio.ShortBuffer;
import java.awt.*;
import java.awt.image.*;

public class BinaryImage {
	
	private int[][] matrix ;
	private int width;
	private int height;
	private IplImage binaryImage;
	private ByteBuffer binaryByteBuffer;

	
	public BinaryImage(GrayImage grayImage){
		
		
		width = grayImage.getWidth();
		height = grayImage.getHeight();
		matrix = new int[height][width];
		binaryImage = grayImage.getGrayImage();
		binaryByteBuffer = binaryImage.getByteBuffer();
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				
				if (grayImage.get(i, j)>127){
					
					matrix[j][i] = 1;
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 255);
					}
				} else {
					
					matrix[j][i] = 0 ;
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 0);
					}
				}
				
				
			}
			
		}
	}


	public BinaryImage(int[][] matrix) {
		
		this.matrix = matrix ;
		height = matrix.length;
		width = matrix[0].length;
		binaryImage = cvCreateImage(cvSize(width, height), 8, 3);
		binaryByteBuffer = binaryImage.getByteBuffer();
		
		for (int i =0; i<width; i++){
			for (int j=0; j<height; j++){
				
				if (matrix[j][i] == 1){
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 255);
					}
					
				} else {
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 0);
					}
					
				}
			}
		}
		
		
		
	}
	



	public IplImage getImage(){
		
		return binaryImage;
	}
	
	public int[][] getMatrix(){
		
		return matrix;
	}
	
	


	
	


}

