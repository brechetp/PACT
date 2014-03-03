package camera;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Capture {
	
	
	private static int nbr;

	
	

	
	  public static void captureFrame(String fileName)
	    {
	        OpenCVFrameGrabber grabber=new OpenCVFrameGrabber(1);
	        try
	        {
	        	
	            grabber.start();
	            IplImage img=grabber.grab();
	            if(img!=null)
	            {
	                cvSaveImage(fileName, img);
	            }
	            grabber.stop();
	          
	        
	        
	            
	            
	        }
	        catch(Exception ae)
	        {
	            ae.printStackTrace();
	            

	        }
	    }
	        
	        
}
	   


