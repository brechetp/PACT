package camera;

import logiqueDeJeux.BeloteCoinche;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.Decoder;

public class QRCodeMatch implements Runnable {

	private IplImage largeImage;
	private int compteur;
	private BeloteCoinche belote;


	public QRCodeMatch(IplImage largeImage,
			int compteur ,BeloteCoinche belote) {
		this.largeImage = largeImage;
		this.compteur = compteur;
		this.belote = belote;

	}

	public void run() {

		Image image = new Image(largeImage);
		LuminanceSource source = new BufferedImageLuminanceSource(image.getRgbImage().getBufferedImage());
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result[] result = null;
		try {
			result = new QRCodeMultiReader().decodeMultiple(bitmap);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result != null){
			for(Result res : result){
				System.out.println(res.getText());
			}
		}else{
			System.out.println("Pas de QRCode trouvé");
		}
	}


}


