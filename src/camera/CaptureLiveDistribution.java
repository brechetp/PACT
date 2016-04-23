package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import structure.Carte;
import logiqueDeJeux.BeloteCoinche;
import machineEtat.CardEvent;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

public class CaptureLiveDistribution extends CaptureLive implements Runnable {



	private boolean run = true ;
	public CaptureLiveDistribution(BeloteCoinche belote){
		super(belote);
	}

	@Override
	public void run(){	

		setCapture(opencv_highgui.cvCreateCameraCapture(getWebcam()));

		opencv_highgui.cvSetCaptureProperty(getCapture(), opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 36);
		opencv_highgui.cvSetCaptureProperty(getCapture(), opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 64);




		IplImage image2, image1 = null, largeImage = null;
		//mainframe.setSize(width/5, height/5);

		int compteur = 0;
		int comptA = 0;
		boolean hasMoved = false;



		/* Ligne magique de JavaCV - elle permet de faire en sorte que les trames videos non utilis�es sont bien lib�r�es de la m�moire
(en quelque sorte en forcant un appel au "Garbage Collector"*/
		CvMemStorage storage = CvMemStorage.create();
		System.out.println("Début des QRCodes");

		while ((image2 = opencv_highgui.cvQueryFrame(getCapture())) != null && run) {
			try{ 
				if(!run)
				{
					Thread.currentThread().interrupt();
					Thread.sleep(1);
				}

				//				cvSaveImage("data/courant/capture/capture"+compteur%1000+".jpg", image2);
				//				if (compteur == 10){
				//					opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 360);
				//					opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 640);
				////					imageA = image2.clone();
				////					cvSaveImage("data/courant/compare/A.jpg", imageA);			
				//					opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 36);
				//					opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 64);
				//
				//
				//
				//				}

				if ( compteur>10) {


					//cvSaveImage("data/courant/image2.jpg", image2);
					//
					//					if(areDifferent(image1, image2, compteur)){
					//						System.out.println("Les images sont differentes");
					//
					//						hasMoved =true;
					//					}
					//					else{ // les images sont identiques
					//						if (hasMoved){
					//							hasMoved = false;




					opencv_highgui.cvSetCaptureProperty(getCapture(), opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 1080);
					opencv_highgui.cvSetCaptureProperty(getCapture(), opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 1920);

					largeImage =	opencv_highgui.cvQueryFrame(getCapture()).clone();
					//cvClearMemStorage(storage);

					//							opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 360);
					//							opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 640);
					//							imageB = opencv_highgui.cvQueryFrame(capture).clone();
					//cvClearMemStorage(storage);

					//							cvSaveImage("data/courant/compare/imageA"+comptA+".jpg",imageA);
					//							cvSaveImage("data/courant/compare/imageB"+comptA+".jpg",imageB);
					//							cvSaveImage("data/courant/compare/largeimage"+comptA+".jpg",largeImage);
					//System.out.println("On lance la comparaison "+(++comptA)+".");
					LuminanceSource source = new BufferedImageLuminanceSource(largeImage.getBufferedImage());
					BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

					Result[] result = null;
					try {
						result = new QRCodeMultiReader().decodeMultiple(bitmap);
					} catch (NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread.sleep(1);
					if(result != null){
						for(Result res : result){
							if (getBelote().isStateDistrib()) {
								//System.out.println(res.getText());
								Thread.sleep(100);
								String[] card = res.getText().split(" ");
								getBelote().nouvelleCarte(
										new CardEvent(new Carte(card[0],
												card[1], getBelote()
												.getEtat())));
							}
						}
					}else{
						System.out.println("Pas de QRCode trouvé");
					}
					//							imageA = imageB.clone();

					opencv_highgui.cvSetCaptureProperty(getCapture(), opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 36);
					opencv_highgui.cvSetCaptureProperty(getCapture(), opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 64);

					//cvClearMemStorage(storage);
					image2 = opencv_highgui.cvQueryFrame(getCapture());


					//	}
				}
				Thread.sleep(500);

				//}

				image1 = image2.clone();
				compteur++;


				/*affichage de l'image*/          
				//rgb_frame.showImage(rgb_image);


				/*deuxi�me ligne magique JavaCV, � appeler r�guli�rement (apr�s chaque capture ou affichage de trame, ...)*/
				cvClearMemStorage(storage);

				Thread.sleep(1);
			} catch(InterruptedException e){
				e.printStackTrace();
				break;
			}       
		}
	// rgb_frame.dispose();
	}


	//	protected static boolean areDifferent(IplImage image1, IplImage image2, int compteur) {
	//		boolean res = false;
	//		int compt =0;
	//		int i = 0, j =0;
	//		int[][]mat =new int[image2.height()][image2.width()];
	//		//cvSaveImage("data/courant/a"+2*compteur+".jpg", image1);
	//		//cvSaveImage("data/courant/a"+(2*compteur+1)+".jpg", image2);
	//
	//
	//		while(!res && i < image2.width()){
	//			while(!res && j < image2.height()){
	//
	//				if(binDifferent(image1, image2, i, j)){ // si les pixels i et j sont differents
	//					compt++;
	//					mat[j][i] = 1 ;
	//				}
	//
	//				res = (compt > getDifNum());
	//				j++;
	//
	//
	//			}
	//			j=0;
	//			i++;
	//		}
	//		new BinaryImage(mat).save("data/courant/bin/bin"+compteur+".jpg");
	//		return res;
	//	}






	//	private static boolean different(IplImage image1, IplImage image2,
	//			int i, int j, int k) {
	//
	//		boolean res = false;
	//
	//		int distance = 0 , distanceMin = Integer.MAX_VALUE;
	//		int n = Math.max(0, i-k), p = Math.max(0, j-k);
	//		int[] pixel = getRgbByte(image1, i, j);
	//		while(n <= Math.min(image1.width()-1, i+k)){
	//			while( p <= Math.min(image1.height()-1,  j+k)){
	//				int[] rgbByte = getRgbByte(image2, n, p); // pixel de l'image1
	//				distance = 0;
	//				for (int q = 0; q < 3; q++){
	//					distance = distance + Math.abs(rgbByte[q] - pixel[q]);
	//				}
	//				if (distance < distanceMin){
	//					distanceMin = distance;
	//
	//				}
	//				p++;
	//
	//			}
	//			p=0;
	//			n++;
	//		}
	//
	//
	//		res = (distanceMin > DISTANCE_THRESHOLD);
	//
	//
	//		return res;
	//	}



	@Override
	public void stop(){

		run = false;
	}





}
