package camera;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class GrayImage {
	
	private int[][] matrix;
	IplImage grayImage ;
	

	public GrayImage(int width, int height, IplImage rgbImage){
		
		matrix = new int[height][width];
		grayImage = rgbImage;
		ByteBuffer rgbData = rgbImage.getByteBuffer();
		ByteBuffer grayData = grayImage.getByteBuffer();
		for(int i =0; i<width*height; i++){
				
				int pixelIndex = 3*i;
				int blueValue = rgbData.get(pixelIndex), greenValue = rgbData.get(pixelIndex + 1),
						redValue = rgbData.get(pixelIndex + 2);
				 if (blueValue < 0 ) blueValue = 255 + blueValue;
				 if (greenValue < 0 ) greenValue = 255 + greenValue;
				 if (redValue < 0 ) redValue = 255 + redValue;
				 
				 int grayValue = (int) (0.2125*redValue + 0.7154*greenValue + 0.0721*blueValue);
				 grayData.put(pixelIndex, (byte) grayValue);
				 grayData.put(pixelIndex+1, (byte) grayValue);
				 grayData.put(pixelIndex+2, (byte) grayValue);  
				 matrix[i/width][i%width] =  grayValue;
		}
	}
			
		
	
	
	public int get(int i, int j){
		return matrix[j][i];
	}
	
	public void save(String fileName){
		 if (grayImage != null) {
             cvSaveImage(fileName, grayImage);
		 }
	}
}
