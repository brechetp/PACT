package camera;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.nio.ByteBuffer;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Image {
	
	protected int width;
	protected int height;
	private IplImage rgbImage;
	private ByteBuffer rgbByteBuffer;
	
	public Image(IplImage image){
		
		width = image.width();
		height = image.height();
		rgbImage = image;
		rgbByteBuffer = rgbImage.getByteBuffer();
		
	}
	
	public void setRgbByte(int index, byte byt){
		
		rgbByteBuffer.put(index, byt);
	}
	
	public ByteBuffer getRgbByteBuffer(){
		
		return rgbByteBuffer;
		
	}
	public int[] getRgbByte(int i, int j){
		
		int[] res = new int[3];
		for(int k = 0; k <2; k++){
			res[k] = (rgbByteBuffer.get(3*i + rgbImage.widthStep()*j+k) + 255) % 255;
		}
		return res;
		
	}


	public IplImage getRgbImage() {
		
		return rgbImage;
	}

	
	public int getWidth(){
		
		return width;
	}
	
	public int getHeight(){
		
		return height;
	}

	public void save(String fileName){
		 if (rgbImage != null) {
            cvSaveImage(fileName, rgbImage);
		 }
	}
	
	public int[] voisin(int i, int j, int[] pixel){ // retourne le pixel voisin de (i,j)
		
		int[] res = new int[3];
		int distance = 0 , distanceMin = Integer.MAX_VALUE;
		for(int n = Math.max(0, i-2); n <= Math.min(width-1, i+2); n++){
			for(int p = Math.max(0, j-2); p <= Math.min(height-1,  j+2); p++){
				int[] rgbByte = getRgbByte(n, p);
				distance = 0;
				for (int k = 0; k < 2; k ++){
					distance = distance + Math.abs(rgbByte[k] - pixel[k]);
				}
				if (distance < distanceMin){
					distanceMin = distance;
					res = rgbByte;
				}
			}
		}
		
		return res;
	}
	
public BinaryImage difference(Image image){
		
		
		int[][] diff = new int[height][width];
		int[] voisin , pixel;
		int distance = 0;
		for (int i = 0 ; i< width; i++){
			for(int j = 0; j < height; j++){
				distance = 0;
				pixel = getRgbByte(i,j);
				voisin = image.voisin(i, j, pixel);
				for (int k = 0; k < 2; k ++){
					distance = distance + Math.abs(voisin[k] - pixel[k]);	
				}	
				
				if (distance > 40)
					diff[j][i] = 1;
				else
					diff[j][i] = 0 ;
					}
				}
			
		
		BinaryImage bin = new BinaryImage(diff);
		return bin ;
		
		
	}

public BinaryImage difference2(Image image) {
	int[][] diff = new int[height][width];
	int[] pixel2 , pixel;
	int distance = 0;
	for (int i = 0 ; i< width; i++){
		for(int j = 0; j < height; j++){
			distance = 0;
			pixel = getRgbByte(i,j);
			pixel2 = image.getRgbByte(i, j);
			for (int k = 0; k < 2; k ++){
				distance = distance + Math.abs(pixel2[k] - pixel[k]);	
			}	
			
			if (distance > 40)
				diff[j][i] = 1;
			else
				diff[j][i] = 0 ;
				}
			}
		
	
	BinaryImage bin = new BinaryImage(diff);
	return bin ;
}
}
