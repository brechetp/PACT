package camera;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeReader;

public class QRTest {

	public static void main(String[] args) {



		Image image = new Image("data/test/qrcode/7P-7T2.jpg");
		LuminanceSource source = new BufferedImageLuminanceSource(image.getRgbImage().getBufferedImage());
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result[] result = null;
		try {
			result = new QRCodeMultiReader().decodeMultiple(bitmap);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Result res : result){
			System.out.println(res.getText());
		}

	}
}


