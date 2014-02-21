package camera;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Capture {
	
	
	private static int nbr;
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
	
	

	
	  public static int captureFrame()
	    {
	        OpenCVFrameGrabber grabber=new OpenCVFrameGrabber(0);
	        try
	        {
	        	
	            grabber.start();
	            IplImage img=grabber.grab();
	            if(img!=null)
	            {
	                cvSaveImage("data/capture"+nbr+".jpg", img);
	            }
	            Thread.sleep(5000);
	            nbr++;
	            grabber.start();
	            IplImage img2=grabber.grab();
	            if(img!=null)
	            {
	                cvSaveImage("data/capture"+nbr+".jpg", img2);
	            }
	        
	        
	            return nbr;
	            
	        }
	        catch(Exception ae)
	        {
	            ae.printStackTrace();
	            return 0;

	        }
	    }
	        
	        
}
	   


