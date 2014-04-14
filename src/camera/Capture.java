package camera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

import comparaison.Letter;
import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Capture {




	private static final int DISTANCE_THRESHOLD = 100;
	private static final int WEBCAM = 1;
	private static final double GAMMA = 0.2;
	private static double HEIGHT = CaptureLive.getHeight();
	private static double WIDTH = CaptureLive.getWidth();

	public static void captureFrame(String fileName)
	{
	
		try
		{
			CvCapture capture = opencv_highgui.cvCreateCameraCapture(WEBCAM);

			opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, getHeight());
			opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, getWidth());


			IplImage img=opencv_highgui.cvQueryFrame(capture);
		
			if(img!=null)
			{
				cvSaveImage(fileName, img);
			}
			opencv_highgui.cvReleaseCapture(capture);






		}
		catch(Exception ae)
		{
			ae.printStackTrace();


		}
	}

	public static IplImage captureFrame(){

	
		IplImage res = null ;
		try
		{

			CvCapture capture = opencv_highgui.cvCreateCameraCapture(WEBCAM);

			opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, getHeight());
			opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, getWidth());
			
			IplImage img=opencv_highgui.cvQueryFrame(capture);
		
			if(img!=null)
			{
				res = img.clone();
			}
			opencv_highgui.cvReleaseCapture(capture);


		}
		catch(Exception ae)
		{
			ae.printStackTrace();


		}
		return res;
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

	public static void database(int debut, int fin, int threshold, int nbr, String capture, String destination) throws Exception{

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
				BinaryImage bin = image1.differenceNeighbour(image0);

				BinaryComponent card = bin.largestComponent();
				cvSaveImage("data/binary/symbol_database4/carte"+i+".jpg", bin.getBinaryImage());
				cvSaveImage("data/binary/database4bis/carte"+i+".jpg", card.getBinaryImage());
				coins = card.getCornersRansac(3, 1);
				Image resample = image1.resample(coins, 635, 889);
				cvSaveImage(destination+i+".jpg", resample.getRgbImage());

			}




		}

	}

	/*
	 * 
	 * Symbols
	 * 
	 * 
	 */

	public static void symbolDatabase(int debut, int fin, String capture, String fileName) throws Exception{



		for(int i = debut; i <= fin; i++){ 

			Capture.captureFrame(capture+i+".jpg");
			System.out.println("Photo "+(i)+" prise");
			System.out.println("Vous pouvez poser la carte "+(i+1));
			if (i==0)
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (i!=0){

				Image im1 = new Image(capture+0+".jpg");
				Image im2= new Image(capture+i+".jpg");



				BinaryImage bin1 = im2.differenceNeighbour(im1);
				bin1.save("data/database/symbols/"+4*i+".jpg");
				BinaryImage bin2 = im2.binaryThreshold(1);
				bin2.save("data/database/symbols/"+(4*i+1)+".jpg");
				BinaryImage bin = bin1.and(bin2);
				bin.save("data/database/symbols/"+(4*i+2)+".jpg");


				BinaryComponent bin3 = bin.largeComponents();
				bin3.save("data/database/symbols/"+(4*i+3)+".jpg");
				//bin3.getEdge().save("data/test/contour.jpg");

				int[][] coins = bin3.getCornersRansac(3, 1);

				Card carte = new Card(im2.resample(coins, 635, 889).getRgbImage()); 
				carte.save(capture+"bis"+i+".jpg");
				BinaryImage binary = (carte.binaryThreshold(0)); 
				binary.save(capture+"threshold"+i+".jpg");


				write(carte.getSignature(), fileName+((i-1)/2)+((i-1)%2)+".txt");




			}
		}

	}


	private static void write(ArrayList<Double> signature, String fileName) {

		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos);


			for(Double current : signature){ 


				pw.println(current); // on �crit l'etiquette

			}

			pw.close();
		}
		catch (Exception e){

			e.printStackTrace();
		}
	}

	public static void letterDatabase(int debut, int fin, String capture, String fileName) throws Exception{



		for(int i = debut; i <= fin; i++){ 

			Capture.captureFrame(capture+i+".jpg");
			System.out.println("Photo "+(i)+" prise");
			System.out.println("Vous pouvez poser la carte "+(i+1));
			if (i==0)
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (i!=0){

				Image im1 = new Image(capture+0+".jpg");
				Image im2= new Image(capture+i+".jpg");



				BinaryImage bin1 = im2.differenceNeighbour(im1);
				bin1.save("data/database/letters/"+4*i+".jpg");
				BinaryImage bin2 = im2.binaryThreshold(1);
				bin2.save("data/database/letters/"+(4*i+1)+".jpg");
				BinaryImage bin = bin1.and(bin2);
				bin.save("data/database/letters/"+(4*i+2)+".jpg");


				BinaryComponent bin3 = bin.largeComponents();
				bin3.save("data/database/letters/"+(4*i+3)+".jpg");
				//bin3.getEdge().save("data/test/contour.jpg");

				int[][] coins = bin3.getCornersRansac(3, 1);

				Card carte = new Card(im2.resample(coins, 635, 889).getRgbImage()); 
				carte.save(capture+"bis"+i+".jpg");
				BinaryImage binary = (carte.binaryThreshold(0)); 
				binary.save(capture+"threshold"+i+".jpg");

				carte.cut(7, 9, 100, 140).binaryThreshold(0).largestComponent().save(capture+"ter"+i+".jpg");
				Letter letter = new Letter (carte.cut(7,9, 100, 140).binaryThreshold(0).largestComponent().getBinaryMatrix());
				write(letter.getCenteredLetter(), fileName+(i-1)+".txt");




			}
		}

	}



	public static void write(int[][] centeredLetter, String fileName) {
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos);
			String line;

			for(int j = 0; j< centeredLetter.length;j++ ){
				line = ""+centeredLetter[j][0];
				for(int i = 1; i < centeredLetter[0].length; i++){

					line += centeredLetter[j][i]; // on buff la ligne

				}


				if (j != (centeredLetter.length -1))
					pw.println(line); // on �crit l'etiquette et on saute une ligne
				else 
					pw.print(line);

			}

			pw.close();
		}
		catch (Exception e){

			e.printStackTrace();
		}

	}

	public static void liveCapture() throws Exception {

		try{ 




			/*creation de la fenetre principale*/
			/*JFrame mainframe = new JFrame();
		mainframe.setLayout(new GridLayout(1, 1));
		mainframe.setVisible(true);*/


			/*creation de la fenetre utilis�e pour l'affichage de la video. L'objet CanvasFrame en JavaCV peut utiliser
    l'acc�l�ration materielle pour afficher les vid�os, profitons-en ! */

			/*creation de la fenetre utilisÈe pour l'affichage de la video. L'objet CanvasFrame en JavaCV peut utiliser
    l'accÈlÈration materielle pour afficher les vidÈos, profitons-en ! */

			/*	CanvasFrame rgb_frame = new CanvasFrame("AVI Playback Demo");        
		mainframe.getContentPane().add(rgb_frame.getCanvas() );
		rgb_frame.setVisible(false);*/


			/*creation de l'objet d'acquisition de trames video � partir du fichier indiqu� comme param�tre du programme*/
			OpenCVFrameGrabber grabber = null;
			//        grabber = new OpenCVFrameGrabber(args[0]);
			grabber = new OpenCVFrameGrabber(WEBCAM);


			grabber.start();

			IplImage rgb_image = grabber.grab();

			//mainframe.setSize(width/5, height/5);
			cvSaveImage("data/courant/image1.jpg", rgb_image);
			int compteur = 0;

			/* Ligne magique de JavaCV - elle permet de faire en sorte que les trames videos non utilis�es sont bien lib�r�es de la m�moire
    (en quelque sorte en forcant un appel au "Garbage Collector"*/
			CvMemStorage storage = CvMemStorage.create();

			while ((rgb_image = grabber.grab()) != null ) {
				if ( compteur>=30) {



					IplImage image1 = cvLoadImage("data/courant/image1.jpg");
					if(areDifferent(image1, rgb_image))
						System.out.println("Les images sont differentes "+compteur);

					cvSaveImage("data/courant/image1.jpg", rgb_image);




				}
				compteur++;


				/*deuxiËme ligne magique JavaCV, ‡ appeler rÈguliËrement (aprËs chaque capture ou affichage de trame, ...)*/

				cvClearMemStorage(storage);
			}
			//nettoyage des ressources        
			grabber.stop();
			// rgb_frame.dispose();
		} catch(Exception e){
			System.out.println(e.getStackTrace());
		}       

	}

	private static boolean areDifferent(IplImage image1, IplImage rgb_image) {
		boolean res = false;
		int compt =0;
		int i = 0, j =0;

		while(!res && i < rgb_image.width()){
			while(!res && j < rgb_image.height()){

				if(different(image1, rgb_image, i, j, 6)) // si les pixels i et j sont differents
					compt++;

				res = (compt > 50);
				j++;


			}
			i++;
		}
		return res;
	}


	private static boolean different(IplImage image1, IplImage rgb_image,
			int i, int j, int k) {

		boolean res = false;
		int[] vector = new int[3];
		int distance = 0 , distanceMin = Integer.MAX_VALUE;
		int n = Math.max(0, i-k), p = Math.max(0, j-k);
		int[] pixel = getRgbByte(rgb_image, i, j);
		while(n <= Math.min(image1.width()-1, i+k)&& !res){
			while( p <= Math.min(image1.height()-1,  j+k)&& !res){
				int[] rgbByte = getRgbByte(image1, n, p); // pixel de l'image1
				distance = 0;

				for (int q = 0; q < 3; q++){

					distance = distance + Math.abs(rgbByte[q] - pixel[q]);
				}
				if (distance < distanceMin){
					distanceMin = distance;
					vector = rgbByte;
				}
				p++;


			}
			n++;
		}
		distance = 0;
		for (int q = 0; q < 3; q++){
			distance = distance + Math.abs(vector[q] - pixel[q]);	
		}	

		if (distance > DISTANCE_THRESHOLD)
			res = true;

		return res;
	}

	private static int[] getRgbByte(IplImage image1, int n, int p) {


		int[] res = new int[3];
		for(int k = 0; k <3; k++){
			res[k] = (image1.getByteBuffer().get(3*n + image1.widthStep()*p+k) + 255) % 255;
		}
		return res;
	}

	public static double getHeight() {
		return HEIGHT;
	}

	public static double getWidth() {
		return WIDTH;
	}

	public static void setHeight(int i) {
		HEIGHT = i;
		
	}

	public static void setWidth(int i) {

		WIDTH = i;
	}
}









