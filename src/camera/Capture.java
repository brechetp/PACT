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
	        OpenCVFrameGrabber grabber=new OpenCVFrameGrabber(0);
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
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
	  		}
			
		}
		
		public static void database(int debut, int fin, int threshold, int nbr, String capture, String destination){
			
			int[][] coins ;

			for(int i = debut; i <= fin; i++){ 
				
				 
		  		
		  	
					
						
					
				
				Capture.captureFrame(capture+i+".jpg");
		  		System.out.println("Photo "+(i+1)+" prise");
		  		System.out.println("Vous pouvez poser la carte "+(i+1));
		  		if (i==0)
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  	if (i!=0){
		  	
					Image image0 = new Image("data/capture/carte0.jpg");
					Image image1 = new Image(capture+i+".jpg");
					BinaryImage bin = image1.differenceNeighbour(image0, threshold, nbr);
					
			      	BinaryImage card = bin.largestComponent();
			      	cvSaveImage("data/binary/database4/carte"+i+".jpg", bin.getBinaryImage());
			      	cvSaveImage("data/binary/database4bis/carte"+i+".jpg", card.getBinaryImage());
			      	coins = card.getCorners();
					Image resample = image1.resample(coins, 635, 889);
			      	cvSaveImage(destination+i+".jpg", resample.getRgbImage());

		  	}
		  	
		  	
		      	
		      	
			}
			
		
			
		}
		
		

		
	        
}
	   


