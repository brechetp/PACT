package camera;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.nio.ByteBuffer;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Image {
	
	protected int width;
	protected int height;
	private IplImage rgbImage;
	private ByteBuffer byteBuffer;
	
	public Image(IplImage image){
		
		this.width = image.width();
		this.height = image.height();
		this.rgbImage = image;
		this.byteBuffer = rgbImage.getByteBuffer();
	}
	
	public void setRgbByte(int index, byte byt){
		
		byteBuffer.put(index, byt);
	}
	
	public ByteBuffer getRgbByteBuffer(){
		
		return byteBuffer;
		
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


}
