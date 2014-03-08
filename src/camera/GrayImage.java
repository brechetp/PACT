package camera;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;




import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;


public class GrayImage extends Image{
	
	private int[][] grayMatrix; // matrice de niveaux de gris
	private IplImage grayImage; // image en niveaux de gris
	private ByteBuffer grayByteBuffer; // tableau de bytes de l'image

	public GrayImage(IplImage rgbImage){
		
		super(rgbImage);
		this.grayImage = cvCreateImage(cvSize(width, height), 8, 3);
		grayByteBuffer = this.grayImage.getByteBuffer();
		grayMatrix = new int[height][width];
		
		for(int i =0; i<width*height; i++){
				
				int pixelIndex = 3*i;
				int [] values= getRgbByte(pixelIndex); // on recupere les valeurs RGB du pixel
				
				int grayValue = (int) (0.2125*(values[2]) + 0.7154*(values[1]) + 0.0721*(values[0]));
				 setGrayByte(pixelIndex, (byte) grayValue); // on modifie la valeur de gris
				
				 grayMatrix[i/width][i%width] =  grayValue;
		}
	}
	
	public GrayImage (int[][] grayMatrix) { // initialise une image a partir d'une matrice
		
		super(cvCreateImage(cvSize(grayMatrix[0].length, grayMatrix.length), 8, 3));
		this.grayMatrix = grayMatrix ;
		grayImage = cvCreateImage(cvSize(width, height), 8, 3);
		grayByteBuffer = grayImage.getByteBuffer();
		
		for (int i =0; i<width; i++){
			for (int j=0; j<height; j++){
				for(int k = 0; k<3; k++){
					
					grayByteBuffer.put(3*i + grayImage.widthStep()*j+k, (byte) grayMatrix[j][i]);
				}
				
			}
		}
		
		
		
	}
	
	public GrayImage (String fileName){
		
		this(cvLoadImage(fileName));
	}
	/*
	 *  Settes et getters
	 */
		
	public void setGrayByte(int index, byte value){ // sauvegarde la valeur d'un byte en gris
		grayByteBuffer.put(index, value);
		grayByteBuffer.put(index+1, value);
		grayByteBuffer.put(index+2, value);

	}
	
	public IplImage getGrayImage(){
		
		return grayImage;
	}
	
	public int get(int i, int j){
		return grayMatrix[j][i];
	}
	public int[][] getMatrix(){
		
		return grayMatrix;
	}
	

	/*
	 * 
	 * Difference entre deux images pour donner une image binaire
	 * 
	 */
	
	public BinaryImage binaryDifference(GrayImage grayImage){
		
		
		int[][] matrix = grayImage.getMatrix();
		int width = getWidth();
		int height = getHeight();
		int[][] diff = new int[height][width];
		if (getWidth() == grayImage.getWidth() && getHeight() == grayImage.getHeight()){
			for (int i = 0 ; i< width; i++){
				for(int j = 0; j < height; j++){
					if (Math.abs((grayMatrix[j][i] - matrix[j][i])) > 10){
						
						diff[j][i] = 1;
					} else {
						diff[j][i] = 0 ;
					}
				}
			}
		}
		BinaryImage bin = new BinaryImage(diff);
		return bin ;
		
		
	}
	
	public GrayImage conv(int[][] conv){ // convolution d'une matrice avec un noyau
		
		
		int[][] res = new int[height][width];
		for (int i = 1 ; i < width-1 ; i++){
			for (int j = 1; j < height-1; j++){
				
				float pixelValue = 0 ;
				float tot = 0;
				for (int k = 0; k <3; k++){
					for(int p=0; p<3; p++){
						
						pixelValue = pixelValue + conv[p][k]*this.grayMatrix[j+(p-1)][i+(k-1)];
						tot = tot + conv[p][k];
					}
				}
				res[j][i] = Math.round(pixelValue/tot);
			}
		}
		GrayImage result = new GrayImage(res);
		return result;
		
		
		
	}
	
	@Override
	public void save(String fileName){
		 if (grayImage != null) {
           cvSaveImage(fileName, grayImage);
		 }
	}


	public void saveToText(String fileName){ // sauvegarde dans un fichier texte
	
	try{
		FileOutputStream fos = new FileOutputStream(fileName);
		PrintWriter pw = new PrintWriter(fos);
		int i; // parcours en largeur
		int j; // parcours en hauteur
		String accu;
		for(j=0; j<height; j++){ 
			
			for(i=0;i<width;i++){
				accu = ""+get(i,j)+"";
				while (accu.length() != 3)
						accu = "0"+accu;
				
				pw.print(accu); // on Žcrit l'etiquette

			}
			pw.print("\n");	// a la fin d'une ligne, on va a la ligne
		}
		pw.close();
  }
	catch (Exception e){
	  
	  e.printStackTrace();
  }
	}
	
	public static int[][] matrixFromTextFile(String fileName){ // retourne une matrice d'entiers a partir d'un fichier texte
		int[][] matrix = new int[1080][1920] ;
		try{
			FileReader fis = new FileReader(fileName);
			BufferedReader bis = new BufferedReader (fis);
			String currentLine; // ligne courrante lue
			int j = 0; // j comptera les lignes du fichier 
			String accu;
			
			while((currentLine = bis.readLine()) != null && j<1080){
				// tant que le fichier n'est pas fini
				
				if(currentLine.length() == 3*1920){ // lorsque la largeur est la bonne
					
					for(int i=0; i<1920; i++){ // i compte la colonne
						
						accu = currentLine.substring(3*i, 3*(i+1));
						matrix[j][i] = Integer.parseInt(accu);
						
						// on remplit la matrice
					}
					j++; // a la fin d'une ligne on incremente le nombre de lignes lues
				}
				else{ // quand la largeur n'est pas la bonne
					bis.close();
					throw new Exception();
				}
			}
			if (j != 1080 || (currentLine = bis.readLine()) != null){ 
				// si on s'arrete avant la fin du fichier ou si le fichier est trop grand
				bis.close();
				throw new Exception();
			}
			else // tout s'est bien passe, on ferme bis
				bis.close(); 
			return matrix;
				
		}
		catch(IOException e){ // probleme d'ouverture de fichier
			
			e.printStackTrace();
		}
		catch(Exception e){ // probleme general
			
			e.printStackTrace();
		}
		return matrix;
	}
	
	public BinaryImage whiteThresholding(){ // on ne garde que les regions blanches
	
	
	int width = getWidth();
	int height = getHeight();
	int[][] res = new int[height][width];
	
		for (int i = 0 ; i< width; i++){
			for(int j = 0; j < height; j++){
				if (grayMatrix[j][i] > 235){
					res[j][i] = 1;
				} else {
					res[j][i] = 0 ;
				}
			}
		}
	
	BinaryImage bin = new BinaryImage(res);
	return bin ;
	
	}

	
	public int neighbour(int i, int j, int pixel){ // retourne le pixel voisin de pixel
	
	int res =0;
	int distance = 0 , distanceMin = Integer.MAX_VALUE;
	for(int n = Math.max(0, i-1); n <= Math.min(width-1, i+1); n++){
		for(int p = Math.max(0, j-1); p <= Math.min(height-1,  j+1); p++){
			
			distance = 0;
			
				distance = Math.abs(grayMatrix[p][n] - pixel);
			
			if (distance < distanceMin){
				distanceMin = distance;
				res = grayMatrix[p][n];
			}
		}
	}
	
	return res;
	}

	public BinaryImage binaryDifferenceNeighbour(GrayImage grayImage) {
	
		int width = getWidth();
		int height = getHeight();
		int[][] diff = new int[height][width];
		int voisin = 0 ;
		if (getWidth() == grayImage.getWidth() && getHeight() == grayImage.getHeight()){
			for (int i = 0 ; i< width; i++){
				for(int j = 0; j < height; j++){
					voisin = neighbour(i, j, grayMatrix[j][i]);
					if (Math.abs((grayMatrix[j][i] - voisin)) > 20){
						diff[j][i] = 1;
					} else {
						diff[j][i] = 0 ;
					}
				}
			}
		}
		BinaryImage bin = new BinaryImage(diff);
		return bin ;
	
	}
}


	
	



