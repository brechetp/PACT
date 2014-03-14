package camera;

import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import patternmatching.Matrice;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.nio.ByteBuffer;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;


public class Image {
	

	public static final int threshold = 200;
	
	protected int compt =0; //compte le nombre de pixels non blancs
	

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
					compt++;
				
				for(int k =0; k<3; k++){
					average[k] += rgbByte[k];
				}
			}
		}	
		for(int k =0; k<3; k++){
			average[k] = average[k]/((float) height*width);

		}
	}
	
	public void computeThresholdedAverage(){
		
		thresholdedAverage = new double[3];
		compt = 0;
		for (int j =0; j<height; j++)
		{
			for (int i=0; i<width; i++)
			{
				int[] rgbByte = getRgbByte(i,j);
				if(! isWhite(rgbByte)){
					for(int k =0; k<3; k++){
						thresholdedAverage[k] += rgbByte[k];
					}
					compt++;
				
				}
			}
		}	
		for(int k =0; k<3; k++){
			thresholdedAverage[k] = thresholdedAverage[k]/( compt);

		}
	}
	public void computeSigma(){

	
		
		for (int j =0; j<height; j++)
		{
			for (int i=0; i<width; i++)
			{
				int[] rgbByte = getRgbByte(i,j);
				if (true){
					for(int k =0; k<3; k++){
						sigma[k] += Math.pow(rgbByte[k]-average[k],2);
					}
				}
			}
		}
		for(int k =0; k<3; k++){
			sigma[k] = Math.sqrt(sigma[k]/(height*width));
		} 
	}
	
	public double[] getAverage(){
		

		if (average == null)
			computeAverage();
		return average;
	}
	
	public double[] getThresholdedAverage(){
		

		if (thresholdedAverage == null)
			computeThresholdedAverage();
		return thresholdedAverage;
	}
	

	public double[] getSigma() {

		if (sigma == null)
			computeSigma();
		return sigma;
	}
	

	
	
	
	
	/*
	 * 
	 * Sauvegarde
	 * 
	 * 
	 */

	public boolean isWhite(int[] rgbByte){
		
		return (rgbByte[0] > threshold && rgbByte[1] > threshold && rgbByte[2] > threshold);
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
	
	public BinaryImage differenceNeighbour(Image image, int threshold, int nbr){
		
		
		int[][] diff = new int[height][width];
		int[] voisin , pixel;
		int distance = 0;
		for (int i = 0 ; i< width; i++){
			for(int j = 0; j < height; j++){
				distance = 0;
				pixel = getRgbByte(i,j);
				voisin = image.neighbour(i, j, pixel, nbr);
				for (int k = 0; k < 2; k ++){
					distance = distance + Math.abs(voisin[k] - pixel[k]);	
				}	
				
				if (distance > threshold)
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
			
				if (distance > 60)
					diff[j][i] = 1;
				else
					diff[j][i] = 0 ;
			}
		}
		BinaryImage bin = new BinaryImage(diff);
		return bin ;
	}
	
	public Image resample(int[][] coin, int width, int height){ // reechantillonnage on donne les coins et les dimensions de la nouvelle image
		
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
		 System.out.println(X.isInversible());
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
					 pixels[k] = getRgbByte((int)Math.ceil(xValue)+k/2, (int)Math.ceil(yValue)+k%2);
				 }
				 
				 for(int k = 0; k<3; k++){
					 tab[3*i + 3*width*j + k] = (int) Math.round((1-xValue%1)*(1-yValue%1)*pixels[0][k]+
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
		
		int[] tab = new int[3*width*height];

		for(int i=0; i< width; i++){
			for(int j=0; j<height; j++ ){
				
				int[] rgbByte = getRgbByte(i,j);

			
				if (isWhite(rgbByte)){

					
					for(int p =0; p<3; p++){
						tab[3*i + 3*width*j+p] = version*255;
					}
				}else{
					for(int p =0; p<3; p++){

						tab[3*i + 3*width*j+p] = 255 - version*255;

					}
					
					
			}
		}
		}
		Image res = new Image(tab, width, height);
		return new BinaryImage(res);

	}
	public Image threshold(){ // on seuille selon le blanc
		
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
	
	public BinaryImage detect(Image image, int threshold, int nbr){
		
		BinaryImage bin1 = this.differenceNeighbour(image, threshold, nbr);
		BinaryImage bin2 = this.binaryThreshold(1);
		
		return bin2.and(bin1);
		
	}

}

