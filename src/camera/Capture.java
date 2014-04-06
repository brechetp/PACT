package camera;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Capture {




	private static final int DISTANCE_THRESHOLD = 100;

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
				cvSaveImage("data/binary/database4/carte"+i+".jpg", bin.getBinaryImage());
				cvSaveImage("data/binary/database4bis/carte"+i+".jpg", card.getBinaryImage());
				coins = card.getCornersRansac(3);
				Image resample = image1.resample(coins, 635, 889);
				cvSaveImage(destination+i+".jpg", resample.getRgbImage());

			}




		}
	}



	public static void liveCapture() throws Exception {

		try{ 




			/*creation de la fenetre principale*/
			/*JFrame mainframe = new JFrame();
		mainframe.setLayout(new GridLayout(1, 1));
		mainframe.setVisible(true);*/

			/*creation de la fenetre utilisée pour l'affichage de la video. L'objet CanvasFrame en JavaCV peut utiliser
    l'accélération materielle pour afficher les vidéos, profitons-en ! */
			/*	CanvasFrame rgb_frame = new CanvasFrame("AVI Playback Demo");        
		mainframe.getContentPane().add(rgb_frame.getCanvas() );
		rgb_frame.setVisible(false);*/

			/*creation de l'objet d'acquisition de trames video à partir du fichier indiqué comme paramêtre du programme*/
			OpenCVFrameGrabber grabber = null;
			//        grabber = new OpenCVFrameGrabber(args[0]);
			grabber = new OpenCVFrameGrabber(1);

			grabber.start();

			IplImage rgb_image = grabber.grab();

			//mainframe.setSize(width/5, height/5);
			cvSaveImage("data/courant/image1.jpg", rgb_image);
			int compteur = 0;

			/* Ligne magique de JavaCV - elle permet de faire en sorte que les trames videos non utilisées sont bien libérées de la mémoire
    (en quelque sorte en forcant un appel au "Garbage Collector"*/
			CvMemStorage storage = CvMemStorage.create();

			while ((rgb_image = grabber.grab()) != null ) {
				if ( compteur>=30) {



					IplImage image1 = cvLoadImage("data/courant/image1.jpg");
					if(areDifferent(image1, rgb_image))
						System.out.println("Les images sont differentes");

					cvSaveImage("data/courant/image1.jpg", rgb_image);




				}
				compteur++;


				/*affichage de l'image*/          
				//rgb_frame.showImage(rgb_image);


				/*deuxième ligne magique JavaCV, à appeler régulièrement (après chaque capture ou affichage de trame, ...)*/
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
				for (int q = 0; k < 3; k ++){
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
}









