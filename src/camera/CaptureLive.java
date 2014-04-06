package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class CaptureLive implements Runnable {

	private static final int DISTANCE_THRESHOLD = 15;
	private static final int DIF_NUM = 200; // nombre de pixels qui doivent etre differents
	public void run(){	

		try{ 




			/*creation de la fenetre principale*/
			/*JFrame mainframe = new JFrame();
	mainframe.setLayout(new GridLayout(1, 1));
	mainframe.setVisible(true);*/

			/*creation de la fenetre utilis�e pour l'affichage de la video. L'objet CanvasFrame en JavaCV peut utiliser
l'acc�l�ration materielle pour afficher les vid�os, profitons-en ! */
			/*	CanvasFrame rgb_frame = new CanvasFrame("AVI Playback Demo");        
	mainframe.getContentPane().add(rgb_frame.getCanvas() );
	rgb_frame.setVisible(false);*/

			/*creation de l'objet d'acquisition de trames video � partir du fichier indiqu� comme param�tre du programme*/
			OpenCVFrameGrabber grabber = null;
			//        grabber = new OpenCVFrameGrabber(args[0]);
			grabber = new OpenCVFrameGrabber(0);

			grabber.start();

			IplImage image2, image1 = null, imageA = null;
			//mainframe.setSize(width/5, height/5);
	
			int compteur = 0;
			int comptA = 0;
			boolean hasMoved = false;
			
			

			/* Ligne magique de JavaCV - elle permet de faire en sorte que les trames videos non utilis�es sont bien lib�r�es de la m�moire
(en quelque sorte en forcant un appel au "Garbage Collector"*/
			CvMemStorage storage = CvMemStorage.create();

			while ((image2 = grabber.grab()) != null ) {
				if (compteur == 30){
					imageA = image2.clone();
					image1 = image2.clone();
					
				}
				
				if ( compteur>30) {

					
					//cvSaveImage("data/courant/image2.jpg", image2);

					if(areDifferent(image1, image2)){
						System.out.println("Les images sont differentes");
						hasMoved =true;
					}
					else{
						if (hasMoved){
							hasMoved = false;
							System.out.println("On lance la comparaison "+(++comptA)+".");
							new Thread(new Match(imageA, image2, compteur)).start();
							imageA = image2.clone();
							
						}
					}

					image1 = image2.clone();




				}
				compteur++;


				/*affichage de l'image*/          
				//rgb_frame.showImage(rgb_image);


				/*deuxi�me ligne magique JavaCV, � appeler r�guli�rement (apr�s chaque capture ou affichage de trame, ...)*/
				cvClearMemStorage(storage);
				Thread.sleep(100);
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

				if(different(image1, image2, i, j, 0)) // si les pixels i et j sont differents
					compt++;

				res = (compt > DIF_NUM);
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
		int[] pixel = getRgbByte(image1, i, j);
		while(n <= Math.min(image1.width()-1, i+k)){
			while( p <= Math.min(image1.height()-1,  j+k)){
				int[] rgbByte = getRgbByte(image2, n, p); // pixel de l'image1
				distance = 0;
				for (int q = 0; q < 3; q++){
					distance = distance + Math.abs(rgbByte[q] - pixel[q]);
				}
				if (distance < distanceMin){
					distanceMin = distance;

				}
				p++;

			}
			n++;
		}
		

		res = (distanceMin > DISTANCE_THRESHOLD);
			

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
