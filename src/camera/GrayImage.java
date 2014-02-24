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
	
	private int[][] grayMatrix;
	private IplImage grayImage;
	private ByteBuffer grayByteBuffer;


	

	public GrayImage(IplImage grayImage2){
		
		super(grayImage2);
		
		grayImage = cvCreateImage(cvSize(width, height), 8, 3);
		
		grayByteBuffer = grayImage.getByteBuffer();
		
		grayMatrix = new int[height][width];
		
	
		ByteBuffer rgbData = getRgbByteBuffer();
		
		for(int i =0; i<width*height; i++){
				
				int pixelIndex = 3*i;
				int blueValue = rgbData.get(pixelIndex), greenValue = rgbData.get(pixelIndex + 1),
						redValue = rgbData.get(pixelIndex + 2);
				
				 
				 int grayValue = (int) (0.2125*((redValue + 255) % 255) + 0.7154*((greenValue + 255) % 255) + 0.0721*((blueValue + 255) % 255));
				 setGrayByte(pixelIndex, (byte) grayValue);
				
				 grayMatrix[i/width][i%width] =  grayValue;
		}
	}
	
	public GrayImage (int[][] grayMatrix) {
		
		super(cvCreateImage(cvSize(grayMatrix[0].length, grayMatrix.length), 8, 3));
		this.grayMatrix = grayMatrix ;
		grayImage = cvCreateImage(cvSize(width, height), 8, 3);
		grayByteBuffer = grayImage.getByteBuffer();
		
		for (int i =0; i<width; i++){
			for (int j=0; j<height; j++){
				for(int k = 0; k<3; k++){
					
					grayByteBuffer.put(3*i + grayImage.widthStep()*j+k, (byte) grayMatrix[j][i]);
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
		return grayMatrix[j][i];
	}
	public int[][] getMatrix(){
		
		return grayMatrix;
	}
	

	
	public BinaryImage binaryDifference(GrayImage grayImage){
		
		
		int[][] matrix = grayImage.getMatrix();
		int width = getWidth();
		int height = getHeight();
		int[][] diff = new int[height][width];
		if (getWidth() == grayImage.getWidth() && getHeight() == grayImage.getHeight()){
			for (int i = 0 ; i< width; i++){
				for(int j = 0; j < height; j++){
					if (Math.abs((grayMatrix[j][i] - matrix[j][i])) > 10){
						
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
						
						pixelValue = pixelValue + conv[p][k]*this.grayMatrix[j+(p-1)][i+(k-1)];
						tot = tot + conv[p][k];
					}
				}
				res[j][i] = Math.round(pixelValue/tot);
			}
		}
		GrayImage result = new GrayImage(res);
		return result;
		
		
		
	}


	public void saveToText(String fileName){ // sauvegarde dans un fichier texte
	
	try{
		FileOutputStream fos = new FileOutputStream(fileName);
		PrintWriter pw = new PrintWriter(fos);
		int i; // parcours en largeur
		int j; // parcours en hauteur
		for(j=0; j<height; j++){ 
			
			for(i=0;i<width;i++){
				
				pw.print(get(i,j)+" "); // on �crit l'etiquette

			}
			pw.print("\n");	// a la fin d'une ligne, on va a la ligne
		}
		pw.close();
  }
	catch (Exception e){
	  
	  e.printStackTrace();
  }
	}
public BinaryImage test(){
	
	
	int width = getWidth();
	int height = getHeight();
	int[][] diff = new int[height][width];
	
		for (int i = 0 ; i< width; i++){
			for(int j = 0; j < height; j++){
				if (grayMatrix[j][i] > 235){
					
					diff[j][i] = 1;
				} else {
					diff[j][i] = 0 ;
				}
			}
		}
	
	BinaryImage bin = new BinaryImage(diff);
	return bin ;
	
}


public int voisin(int i, int j, int pixel){ // retourne le pixel voisin de (i,j)
	
	int res =0;
	int distance = 0 , distanceMin = Integer.MAX_VALUE;
	for(int n = Math.max(0, i-1); n <= Math.min(width-1, i+1); n++){
		for(int p = Math.max(0, j-1); p <= Math.min(height-1,  j+1); p++){
			
			distance = 0;
			
				distance = Math.abs(grayMatrix[j][i] - pixel);
			
			if (distance < distanceMin){
				distanceMin = distance;
				res = grayMatrix[j][i];
			}
		}
	}
	
	return res;
}

public BinaryImage binaryDifference2(GrayImage grayImage) {
	
	int[][] matrix2 = grayImage.getMatrix();
	int width = getWidth();
	int height = getHeight();
	int[][] diff = new int[height][width];
	int voisin = 0 ;
	if (getWidth() == grayImage.getWidth() && getHeight() == grayImage.getHeight()){
		for (int i = 0 ; i< width; i++){
			for(int j = 0; j < height; j++){
				voisin = voisin(i, j, grayMatrix[j][i]);
				if (Math.abs((grayMatrix[j][i] - voisin)) > 20){
					
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
}


	
	


