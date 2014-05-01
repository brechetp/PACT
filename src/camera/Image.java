package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import patternmatching.Matrice;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.nio.ByteBuffer;

import com.googlecode.javacv.cpp.opencv_legacy;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;


public class Image {


	
	public static final int DISTANCE_THRESHOLD = 30; // pour la distance entre deux images
	private static final int NEIGHBOUR_NUMBER = 0; // pour l'algorithme de distance

	private static int[] WHITE_VECTOR = new int[]{220, 220, 220};
	private static int[] GRAY_VECTOR = new int[]{100, 180, 180};
	private static final int COLOR_THRESHOLD = 10;
	private static final int[] RED_VECTOR = new int[]{87,88, 200};
	private static final int[] BLACK_VECTOR = new int[]{70, 64, 67};

	//protected int compt =0; //compte le nombre de pixels non blancs


	protected int width;
	protected int height;
	private IplImage rgbImage;
	private ByteBuffer rgbByteBuffer;

	protected double[] average = null;
	protected double[] thresholdedAverage = null;
	protected double[] sigma = null;





	protected String name;

	/*
	 * 
	 * Constructeurs
	 * 
	 */

	public Image(IplImage image){ 

		width = image.width();
		height = image.height();
		rgbImage = image;
		rgbByteBuffer = rgbImage.getByteBuffer();



	}

	public Image (int[] tab, int width, int height) { // construit a parir d'un tableau de bytes

		this.width = width;
		this.height = height;
		rgbImage = cvCreateImage(cvSize(width, height), 8, 3);
		rgbByteBuffer = rgbImage.getByteBuffer();

		for (int i =0; i<width; i++){
			for (int j=0; j<height; j++){
				for(int k = 0; k<3; k++){

					rgbByteBuffer.put(3*i + rgbImage.widthStep()*j+k, (byte) tab[3*i + 3*width*j+k]);
				}

			}
		}



	}

	public Image(String fileName){

		this(cvLoadImage(fileName));
		name = fileName;
	}

	/*
	 * Setters et getters
	 */

	public void setRgbByte(int index, byte byt){

		rgbByteBuffer.put(index, byt);
	}

	public ByteBuffer getRgbByteBuffer(){

		return rgbByteBuffer;

	}

	public int[] getRgbByte(int i, int j){ // retourne le vecteur pour le pixel (i,j)

		int[] res = new int[3];
		for(int k = 0; k <3; k++){
			res[k] = (rgbByteBuffer.get(3*i + rgbImage.widthStep()*j+k) + 255) % 255;
		}
		return res;

	}

	public int[] getRgbByte(int pixelIndex){ // idem pour le pixel � l'index pixelIndex

		int[] res = new int[3];
		for(int k = 0; k <3; k++){
			res[k] = (rgbByteBuffer.get(pixelIndex+k) + 255) % 255;
		}
		return res;

	}


	public IplImage getRgbImage() {

		return rgbImage;
	}

	public void setRgbImage(IplImage rgbImage) {

		this.rgbImage = rgbImage;
	}
	public int getWidth(){

		return width;
	}

	public int getHeight(){

		return height;
	}


	public String getName(){

		return name;
	}



	public void computeAverage(){

		average = new double[3];

		for (int j =0; j<height; j++)
		{
			for (int i=0; i<width; i++)
			{
				int[] rgbByte = getRgbByte(i,j);
				if (! isWhite(rgbByte))
				//	compt++;

				for(int k =0; k<3; k++){
					average[k] += rgbByte[k];
				}
			}
		}	
		for(int k =0; k<3; k++){
			average[k] = average[k]/((float) height*width);

		}
	}

//	public void computeThresholdedAverage(){
//
//		thresholdedAverage = new double[3];
//		//compt = 0;
//		for (int j =0; j<height; j++)
//		{
//			for (int i=0; i<width; i++)
//			{
//				int[] rgbByte = getRgbByte(i,j);
//				if(! isWhite(rgbByte)){
//					for(int k =0; k<3; k++){
//						thresholdedAverage[k] += rgbByte[k];
//					}
//				//	compt++;
//
//				}
//			}
//		}	
//		for(int k =0; k<3; k++){
//			thresholdedAverage[k] = thresholdedAverage[k]/( compt);
//
//		}
//	}
//	public void computeSigma(){
//
//
//
//		for (int j =0; j<height; j++)
//		{
//			for (int i=0; i<width; i++)
//			{
//				int[] rgbByte = getRgbByte(i,j);
//				if (true){
//					for(int k =0; k<3; k++){
//						sigma[k] += Math.pow(rgbByte[k]-average[k],2);
//					}
//				}
//			}
//		}
//		for(int k =0; k<3; k++){
//			sigma[k] = Math.sqrt(sigma[k]/(height*width));
//		} 
//	}
//
//	public double[] getAverage(){
//
//
//		if (average == null)
//			computeAverage();
//		return average;
//	}
//
//	public double[] getThresholdedAverage(){
//
//
//		if (thresholdedAverage == null)
//			computeThresholdedAverage();
//		return thresholdedAverage;
//	}
//
//
//	public double[] getSigma() {
//
//		if (sigma == null)
//			computeSigma();
//		return sigma;
//	}
//
//




	/*
	 * 
	 * Sauvegarde
	 * 
	 * 
	 */

	public static boolean isWhite(int[] rgbByte){

		return (/*colorDistance(rgbByte, WHITE_VECTOR)<COLOR_THRESHOLD || */(rgbByte[0] > WHITE_VECTOR[0] && rgbByte[1] > WHITE_VECTOR[1] && rgbByte[2] > WHITE_VECTOR[2] ));
	}
	
	public static boolean isGray(int[] rgbByte){

		return (/*colorDistance(rgbByte, WHITE_VECTOR)<COLOR_THRESHOLD || */(rgbByte[0] > GRAY_VECTOR[0] && rgbByte[1] > GRAY_VECTOR[1] && rgbByte[2] > GRAY_VECTOR[2] ));
	}

	private static int colorDistance(int[] rgbByte, int[] reference) {

		int distance = Math.abs(rgbByte[0]-reference[0])+Math.abs(rgbByte[1]-reference[1])+Math.abs(rgbByte[2]-reference[2]);
		return distance;
	}

	public static boolean isRed(int[] rgbByte){

		return ((float) 2*rgbByte[2]/(rgbByte[0]+rgbByte[1])) > 1.3;
	}

	public static boolean isBlack(int[] rgbByte){

		return (colorDistance(rgbByte, BLACK_VECTOR)<COLOR_THRESHOLD);
	}

	public void save(String fileName){
		if (rgbImage != null) {
			cvSaveImage(fileName, rgbImage);
		}
	}

	public int[] neighbour(int i, int j, int[] pixel, int nbr){ // retourne le pixel voisin de (i,j)

		int[] res = new int[3];
		int distance = 0 , distanceMin = Integer.MAX_VALUE;
		for(int n = Math.max(0, i-nbr); n <= Math.min(width-1, i+nbr); n++){
			for(int p = Math.max(0, j-nbr); p <= Math.min(height-1,  j+nbr); p++){
				int[] rgbByte = getRgbByte(n, p);
				distance = 0;
				for (int k = 0; k < 3; k ++){
					distance = distance + Math.abs(rgbByte[k] - pixel[k]);
				}
				if (distance < distanceMin){
					distanceMin = distance;
					res = rgbByte;
				}
			}
		}

		return res;
	}

	public BinaryImage differenceNeighbour(Image image){


		int[][] diff = new int[height][width];
		int[] voisin , pixel;
		int distance = 0;
		for (int i = 0 ; i< width; i++){
			for(int j = 0; j < height; j++){
				distance = 0;
				pixel = getRgbByte(i,j);
				voisin = image.neighbour(i, j, pixel, NEIGHBOUR_NUMBER);
				for (int k = 0; k < 3; k ++){
					distance = distance + Math.abs(voisin[k] - pixel[k]);	
				}	

				if (distance > DISTANCE_THRESHOLD)
					diff[j][i] = 1;
				else
					diff[j][i] = 0 ;
			}
		}


		BinaryImage bin = new BinaryImage(diff);
		return bin ;


	}

	public BinaryImage difference(Image image) {
		int[][] diff = new int[height][width];
		int[] pixel2 , pixel;
		int distance = 0;
		for (int i = 0 ; i< width; i++){
			for(int j = 0; j < height; j++){
				distance = 0;
				pixel = getRgbByte(i,j);
				pixel2 = image.getRgbByte(i, j);
				for (int k = 0; k < 3; k ++){
					distance = distance + Math.abs(pixel2[k] - pixel[k]);	
				}	

				if (distance > DISTANCE_THRESHOLD)
					diff[j][i] = 1;
				else
					diff[j][i] = 0 ;
			}
		}
		BinaryImage bin = new BinaryImage(diff);
		return bin ;
	}

	public Image resample(int[][] coin, int width, int height) throws Exception{ // reechantillonnage on donne les coins et les dimensions de la nouvelle image

		int [][][] coins = new int[2][4][2];
		coins[0] = coin;
		coins[1] = new int[][]{{0,0}, {width, 0}, {0, height}, {width, height}};

		double[][] x = new double[8][8] ; // matrice � inverser
		double[][] y = new double[8][1]; // point image
		int[] tab = new int[width*height*3]; // tableau de l'image produite
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 6; j++){
				if ((j%3) < 2)
					x[i][j] = ((i%2 + (1-j/3)) % 2) * coins[0][i/2][j%3];
				else 
					x[i][j] = ((i%2 + (1-j/3)) % 2) * 1 ;
			}

			for (int j = 6; j<8; j++){
				x[i][j] = - coins[0][i/2][j%2]*coins[1][i/2][i%2];
			}
			y[i][0] = coins[1][i/2][i%2];

		}
		Matrice X = new Matrice(x);
		if (! X.isInversible())
			throw new Exception("Le rééchantillonnage n'a pas foncitonné");
		Matrice Y = new Matrice(y);
		Matrice Xinv = X.getMatriceInverse();
		Matrice A = Xinv.multiply(Y);

		for (int i = 0 ; i< width; i++){
			for (int j = 0; j < height; j++){
				double[][] h = new double[2][2];

				double [][] pointImage = new double[2][1];

				for (int n = 0; n <2; n++){
					for(int p = 0; p < 2; p++){

						h[n][p] = A.getValue(6+p, 0)*(j*n+i*(1-n))-A.getValue(n*3+p, 0);
					}
					pointImage[n][0] = A.getValue(n*3+2, 0)-(j*n+i*(1-n));

				}
				Matrice H = new Matrice(h);

				Matrice PointImage = new Matrice(pointImage);
				Matrice Hinv = (H.getMatriceInverse());
				Matrice Point = Hinv.multiply(PointImage);
				double xValue = Point.getValue(0, 0), yValue = Point.getValue(1, 0);


				int[][] pixels = new int[4][3];

				for (int k = 0; k<4; k++){
					pixels[k] = getRgbByte((int)Math.floor(xValue)+k/2, (int)Math.floor(yValue)+k%2);
				}

				for(int k = 0; k<3; k++){
					tab[3*i + 3*width*j + k] = (int) Math.round( (1-xValue%1)*(1-yValue%1)*pixels[0][k]+
							(xValue%1)*(1-yValue%1)*pixels[1][k]+
							(1-xValue%1)*(yValue%1)*pixels[2][k]+
							(xValue%1)*(yValue%1)*pixels[3][k]) ;
				}

			}




		}


		Image res = new Image(tab, width, height);
		return res;

	}

	public Image cut(int x, int y, int width, int height){

		int[] tab = new int[3*width*height];

		for(int i=x; i< x+width; i++){
			for(int j=y; j<y+height; j++ ){

				int[] rgbByte = getRgbByte(i,j);
				for(int p =0; p<3; p++){
					tab[3*(i-x) + 3*width*(j-y)+p] = rgbByte[p];

				}

			}
		}
		Image cut = new Image(tab, width, height);
		return cut;
	}


	public BinaryImage binaryThreshold(int version){ // on seuille selon le blanc
		// si version = 1, on garde les blancs , si version = 0 on garde les non blancs
		// version = 2 on garde les rouges ou les noirs
		int[] tab = new int[3*width*height];

		if (version <=1) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {

					int[] rgbByte = getRgbByte(i, j);

					if (isWhite(rgbByte)) {

						for (int p = 0; p < 3; p++) {
							tab[3 * i + 3 * width * j + p] = version * 255;
						}
					} else {
						for (int p = 0; p < 3; p++) {

							tab[3 * i + 3 * width * j + p] = 255 - version * 255;

						}

					}
				}
			}
		} else if (version == 2){ // rouge ou noir

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {

					int[] rgbByte = getRgbByte(i, j);

					if (isBlack(rgbByte) || isRed(rgbByte)) {

						for (int p = 0; p < 3; p++) {
							tab[3 * i + 3 * width * j + p] = 255;
						}
					} else {
						for (int p = 0; p < 3; p++) {

							tab[3 * i + 3 * width * j + p] = 0;

						}

					}
				}
			}

		}else if (version == 3){ // rouge ou noir

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {

					int[] rgbByte = getRgbByte(i, j);

					if (!isGray(rgbByte)) {

						for (int p = 0; p < 3; p++) {
							tab[3 * i + 3 * width * j + p] = 255;
						}
					} else {
						for (int p = 0; p < 3; p++) {

							tab[3 * i + 3 * width * j + p] = 0;

						}

					}
				}
			}

		}

		Image res = new Image(tab, width, height);
		return new BinaryImage(res);

	}
	public Image whiteThreshold(){ // on seuille selon le blanc

		int[] tab = new int[3*width*height];

		for(int i=0; i< width; i++){
			for(int j=0; j<height; j++ ){

				int[] rgbByte = getRgbByte(i,j);


				if (isWhite(rgbByte)){


					for(int p =0; p<3; p++){
						tab[3*i + 3*width*j+p] = 0;
					}
				}else{
					for(int p =0; p<3; p++){

						tab[3*i + 3*width*j+p] = rgbByte[p];

					}


				}
			}
		}
		Image res = new Image(tab, width, height);
		return res;

	}

	public BinaryImage detect(Image image){

		BinaryImage bin1 = this.differenceNeighbour(image);
		BinaryImage bin2 = this.binaryThreshold(1);

		return bin2.and(bin1);

	}
	public CvHistogram getHueHistogram(){

	    if(rgbImage==null || rgbImage.nChannels()<3) new Exception("Error!");
	    IplImage hsvImage= cvCreateImage(rgbImage.cvSize(), rgbImage.depth(), 3);
	    cvCvtColor(rgbImage, hsvImage, CV_BGR2HSV);
	    // Split the 3 channels into 3 images
	    IplImageArray hsvChannels = splitChannels(hsvImage);
	    //bins and value-range
	    int numberOfBins=255;
	    float minRange= 0f;
	    float maxRange= 180f;
	    // Allocate histogram object
	   int dims = 1;
	   int[]sizes = new int[]{numberOfBins};
	   int histType = CV_HIST_ARRAY;
	   float[] minMax = new  float[]{minRange, maxRange};
	   float[][] ranges = new float[][]{minMax};
	    int uniform = 1;
	    CvHistogram hist = cvCreateHist(dims, sizes, histType, ranges, uniform);
	    // Compute histogram
	
	    int accumulate = 1;
	    IplImage mask = null;
	    cvCalcHist(hsvChannels.position(0),hist, accumulate, null);
	    return hist;
	}
	       
	private IplImageArray splitChannels(IplImage hsvImage) {
	    CvSize size = hsvImage.cvSize();
	    int depth=hsvImage.depth();
	    IplImage channel0 = cvCreateImage(size, depth, 1);
	    IplImage channel1 = cvCreateImage(size, depth, 1);
	    IplImage channel2 = cvCreateImage(size, depth, 1);
	    cvSplit(hsvImage, channel0, channel1, channel2, null);
	    return new IplImageArray(channel0, channel1, channel2);
	}
	
	public IplImage DrawHistogram() {//draw histogram
		
		CvHistogram hist = getHueHistogram();
		IplImage image= rgbImage;
	    int scaleX = 1;
	    int scaleY = 1;
	    int i;
	    float[] max_value = {0};
	    int[] int_value = {0};
	    cvGetMinMaxHistValue(hist, max_value, max_value, int_value, int_value);//get min and max value for histogram

	    IplImage imgHist = cvCreateImage(cvSize(256, image.height() ),IPL_DEPTH_8U,1);//create image to store histogram
	    cvZero(imgHist);
	    CvPoint pts = new CvPoint(5);

	    for (i = 0; i < 254; i++) {//draw the histogram 
	        float value = opencv_legacy.cvQueryHistValue_1D(hist, i);
	        float nextValue = opencv_legacy.cvQueryHistValue_1D(hist, i + 1);

	        pts.position(0).x(i * scaleX).y(image.height() * scaleY);
	        pts.position(1).x(i * scaleX + scaleX).y(image.height() * scaleY);
	        pts.position(2).x(i * scaleX + scaleX).y((int)((image.height() - nextValue * image.height() /max_value[0]) * scaleY));
	        pts.position(3).x(i * scaleX).y((int)((image.height() - value * image.height() / max_value[0]) * scaleY));
	        pts.position(4).x(i * scaleX).y(image.height() * scaleY);
	        cvFillConvexPoly(imgHist, pts.position(0), 5, CvScalar.RED, CV_AA, 0);
	    }
	    return imgHist;
	}

	

	public void setWhiteVector(int[] whiteVector){
		
		WHITE_VECTOR = whiteVector;
	}
	
	public int[] getARGBArray(){
		
		int[] res = new int[height*width*4];
		for (int i = 0; i < height*width; i++){
			int[] rgbByte = getRgbByte(i);
			res[4*i] = 255;
			for(int p =1; p<4; p++){
				res[4*i+p] = rgbByte[4*i+p-1];
			}
		}
		
	
		return res;
	
	}

}

