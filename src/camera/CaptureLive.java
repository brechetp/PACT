package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class CaptureLive implements Runnable {

	private static final int DISTANCE_THRESHOLD = 20;

	public void run(){	

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
			grabber = new OpenCVFrameGrabber(0);

			grabber.start();

			IplImage image1 = null, image2, imageA = null;
			//mainframe.setSize(width/5, height/5);
	
			int compteur = 0;
			int comptA = 0;
			boolean hasMoved = false;
			
			

			/* Ligne magique de JavaCV - elle permet de faire en sorte que les trames videos non utilisées sont bien libérées de la mémoire
(en quelque sorte en forcant un appel au "Garbage Collector"*/
			CvMemStorage storage = CvMemStorage.create();

			while ((image2 = grabber.grab()) != null ) {
				if (compteur == 30){
					cvSaveImage("data/courant/image1.jpg", image2);
					cvSaveImage("data/courant/imageA.jpg", image2);
				}
				
				if ( compteur>30) {

					image1 = cvLoadImage("data/courant/image1.jpg");

					if(areDifferent(image1, image2)){
						System.out.println("Les images sont differentes");
						hasMoved =true;
					}
					else{
						if (hasMoved){
							hasMoved = false;
							imageA = cvLoadImage("data/courant/imageA.jpg");
							System.out.println("On lance la comparaison "+(++comptA)+".");
							new Thread(new Match(imageA, image2)).start();
							cvSaveImage("data/courant/imageA.jpg",image2);
						}
					}

					cvSaveImage("data/courant/image1.jpg", image2);
					




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

	private static boolean areDifferent(IplImage image1, IplImage image2) {
		boolean res = false;
		int compt =0;
		int i = 0, j =0;

		while(!res && i < image2.width()){
			while(!res && j < image2.height()){

				if(different(image1, image2, i, j, 6)) // si les pixels i et j sont differents
					compt++;

				res = (compt > 50);
				j++;


			}
			i++;
		}
		return res;
	}

	private static boolean different(IplImage image1, IplImage image2,
			int i, int j, int k) {

		boolean res = false;

		int distance = 0 , distanceMin = Integer.MAX_VALUE;
		int n = Math.max(0, i-k), p = Math.max(0, j-k);
		int[] pixel = getRgbByte(image2, i, j);
		while(n <= Math.min(image1.width()-1, i+k)&& !res){
			while( p <= Math.min(image1.height()-1,  j+k)&& !res){
				int[] rgbByte = getRgbByte(image1, n, p); // pixel de l'image1
				distance = 0;
				for (int q = 0; q < 3; q ++){
					distance = distance + Math.abs(rgbByte[q] - pixel[q]);
				}
				if (distance < distanceMin){
					distanceMin = distance;

				}
				p++;

			}
			n++;
		}
		

		if (distanceMin > DISTANCE_THRESHOLD)
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
