package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

import logiqueDeJeux.BeloteCoinche;

public class CaptureQRCodes extends CaptureLive implements Runnable{

	public CaptureQRCodes(BeloteCoinche belote) {
		super(belote);
			}
	
	public void run(){	

		try{ 


			CvCapture capture = opencv_highgui.cvCreateCameraCapture(getWebcam());

			opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 36);
			opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 64);




			IplImage image2, image1 = null, imageA = null, imageB = null, largeImage = null;
			//mainframe.setSize(width/5, height/5);

			int compteur = 0;
			int comptA = 0;
			boolean hasMoved = false;



			/* Ligne magique de JavaCV - elle permet de faire en sorte que les trames videos non utilis�es sont bien lib�r�es de la m�moire
(en quelque sorte en forcant un appel au "Garbage Collector"*/
			CvMemStorage storage = CvMemStorage.create();

			while ((image2 = opencv_highgui.cvQueryFrame(capture)) != null ) {
				cvSaveImage("data/courant/capture/capture"+compteur%1000+".jpg", image2);
				if (compteur == 10){
					opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 360);
					opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 640);
					imageA = image2.clone();
					cvSaveImage("data/courant/compare/A.jpg", imageA);			
					opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 36);
					opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 64);



				}

				if ( compteur>10) {


					//cvSaveImage("data/courant/image2.jpg", image2);

					if(areDifferent(image1, image2, compteur)){
						System.out.println("Les images sont differentes");

						hasMoved =true;
					}
					else{ // les images sont identiques
						if (hasMoved){
							hasMoved = false;

						


							opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 1080);
							opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 1920);
			
							largeImage =	opencv_highgui.cvQueryFrame(capture).clone();
							//cvClearMemStorage(storage);

							opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 360);
							opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 640);
							imageB = opencv_highgui.cvQueryFrame(capture).clone();
							//cvClearMemStorage(storage);

							cvSaveImage("data/courant/compare/imageA"+comptA+".jpg",imageA);
							cvSaveImage("data/courant/compare/imageB"+comptA+".jpg",imageB);
							cvSaveImage("data/courant/compare/largeimage"+comptA+".jpg",largeImage);
							System.out.println("On lance la comparaison "+(++comptA)+".");
							new Thread(new Match(imageA, imageB, largeImage, comptA, getBelote())).start();
							imageA = imageB.clone();

							opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 36);
							opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 64);

							//cvClearMemStorage(storage);
							image2 = opencv_highgui.cvQueryFrame(capture);


						}
					}
					Thread.sleep(500);

				}

				image1 = image2.clone();
				compteur++;


				/*affichage de l'image*/          
				//rgb_frame.showImage(rgb_image);


				/*deuxi�me ligne magique JavaCV, � appeler r�guli�rement (apr�s chaque capture ou affichage de trame, ...)*/
				cvClearMemStorage(storage);



			}
			//nettoyage des ressources        
			opencv_highgui.cvReleaseCapture(capture);
			// rgb_frame.dispose();
		} catch(Exception e){
			System.out.println(e.getStackTrace());
		}       
	}

}
