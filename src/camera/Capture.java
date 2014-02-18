package camera;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Capture {
	
	public final static void saveToText(String fileName, int WIDTH, int HEIGHT, GrayImage matrix){ // sauvegarde dans un fichier texte
		
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos);
			int i; // parcours en largeur
			int j; // parcours en hauteur
			for(j=0; j<HEIGHT; j++){ 
				
				for(i=0;i<WIDTH;i++){
					
					pw.print(matrix.get(i,j)+" "); // on Žcrit l'etiquette

				}
				pw.print("\n");	// a la fin d'une ligne, on va a la ligne
			}
			pw.close();
      }
		catch (Exception e){
    	  
    	  e.printStackTrace();
      }
	}
	
	

	
	    private static void captureFrame() {
	        // 0-default camera, 1 - next...so on
	        final OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
	        try {
	            grabber.start();
	            IplImage img = grabber.grab();
	            int width  = img.width(); // largeur de l'image
	            int height = img.height(); // hauteur de l'image
	            if (img != null) {
	                GrayImage grayImage = new GrayImage(width, height, img); // on convertir en gris
	                grayImage.save("data/capture.jpg"); // on enregistre l'image grise
	                saveToText("data/text.txt", width, height, grayImage);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static void main(String[] args) {
	        captureFrame();
	    }


}
