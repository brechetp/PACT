package camera;


import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

public class Webcam {

    public static void main(String[] args) throws Exception {
        CvCapture capture = opencv_highgui.cvCreateCameraCapture(1);

        opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 270);
        opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 480);

        IplImage grabbedImage = opencv_highgui.cvQueryFrame(capture);

        CanvasFrame frame = new CanvasFrame("Webcam");

        while (frame.isVisible() && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) != null) {
            frame.showImage(grabbedImage);
        }

        frame.dispose();
        opencv_highgui.cvReleaseCapture(capture);
    }

}