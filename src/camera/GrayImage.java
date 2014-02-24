package camera;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class GrayImage extends Image{
	
	private int[][] matrix;
	private IplImage grayImage;
	private ByteBuffer grayByteBuffer;

	

	public GrayImage(IplImage rgbImage){
		
		super(rgbImage);
		
		grayImage = cvCreateImage(cvSize(width, height), 8, 3);
		
		grayByteBuffer = grayImage.getByteBuffer();
		
		matrix = new int[height][width];
		
	
		ByteBuffer rgbData = getRgbByteBuffer();
		
		for(int i =0; i<width*height; i++){
				
				int pixelIndex = 3*i;
				int blueValue = rgbData.get(pixelIndex), greenValue = rgbData.get(pixelIndex + 1),
						redValue = rgbData.get(pixelIndex + 2);
				 if (blueValue < 0 ) blueValue = 255 + blueValue;
				 if (greenValue < 0 ) greenValue = 255 + greenValue;
				 if (redValue < 0 ) redValue = 255 + redValue;
				 
				 int grayValue = (int) (0.2125*redValue + 0.7154*greenValue + 0.0721*blueValue);
				 setGrayByte(pixelIndex, (byte) grayValue);
				
				 matrix[i/width][i%width] =  grayValue;
		}
	}
	
	public GrayImage (int[][] matrix) {
		
		super(cvCreateImage(cvSize(matrix[0].length, matrix.length), 8, 3));
		this.matrix = matrix ;
		grayImage = cvCreateImage(cvSize(width, height), 8, 3);
		grayByteBuffer = grayImage.getByteBuffer();
		
		for (int i =0; i<width; i++){
			for (int j=0; j<height; j++){
				for(int k = 0; k<3; k++){
					
					grayByteBuffer.put(3*i + grayImage.widthStep()*j+k, (byte) matrix[j][i]);
				}
				
			}
		}
		
		
		
	}
	
		
	public void setGrayByte(int index, byte value){
		grayByteBuffer.put(index, value);
		grayByteBuffer.put(index+1, value);
		grayByteBuffer.put(index+2, value);

	}
	
	public IplImage getGrayImage(){
		
		return grayImage;
	}
	
	public int get(int i, int j){
		return matrix[j][i];
	}
	public int[][] getMatrix(){
		
		return matrix;
	}
	
	public BinaryImage difference(GrayImage grayImage2){
		
		
		int[][] matrix2 = grayImage2.getMatrix();
		int width = getWidth();
		int height = getHeight();
		int[][] diff = new int[height][width];
		if (getWidth() == grayImage2.getWidth() && getHeight() == grayImage2.getHeight()){
			for (int i = 0 ; i< width; i++){
				for(int j = 0; j < height; j++){
					if (Math.pow((matrix[j][i] - matrix2[j][i]), 2) > 200 && matrix2[j][i] < 230){
						
						diff[j][i] = 1;
					} else {
						diff[j][i] = 0 ;
					}
				}
			}
		}
		BinaryImage bin = new BinaryImage(diff);
		return bin ;
		
		
	}
	
	public GrayImage conv(int[][] conv){
		
		
		int[][] res = new int[height][width];
		for (int i = 1 ; i < width-1 ; i++){
			for (int j = 1; j < height-1; j++){
				
				float pixelValue = 0 ;
				float tot = 0;
				for (int k = 0; k <3; k++){
					for(int p=0; p<3; p++){
						
						pixelValue = pixelValue + conv[p][k]*this.matrix[j+(p-1)][i+(k-1)];
						tot = tot + conv[p][k];
					}
				}
				res[j][i] = Math.round(pixelValue/tot);
			}
		}
		GrayImage result = new GrayImage(res);
		return result;
		
		
		
	}
}
	
	



