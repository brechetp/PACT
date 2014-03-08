package camera;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Capture {
	
	
	public static void main(String[] args) throws Exception {
		
		capture(0, 44, "data/capture/carte");
	}

	
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
		public static void capture(int debut, int compt, String fileName){ //enregistre compt images
			
			for(int i = debut; i<compt; i++){
	  		Capture.captureFrame(fileName+i+".jpg");
	  		System.out.println("Photo "+(i+1)+" prise");
	  	
	  		
	  		if (i!= compt-1)
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
	  		}
			
		}
		
		

		
	        
}
	   


