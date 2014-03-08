package camera;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

public class BinaryImage extends GrayImage {
	
	private int[][] binaryMatrix ;
	private IplImage binaryImage;
	private ByteBuffer binaryByteBuffer;
	
	private int maxNbTags=10000;
	private int[] connectionTable = new int[maxNbTags];
	public int[][] taggedBinaryImage; // image initiale oï¿½ les 1 sont
												// remplacï¿½s par des pixels
												// ï¿½tiquetï¿½s
	
/*
 * 
 * Constructeurs
 */

	public BinaryImage(Image image){ // a partir d'une image
		
		super(image.getRgbImage());
		binaryMatrix = new int[height][width];
		binaryImage = cvCreateImage(cvSize(width, height), 8, 3);
		binaryByteBuffer = binaryImage.getByteBuffer();
		taggedBinaryImage = new int[height][width];
		int[] rgbByte;
		int value = 0 ;
		
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				
				rgbByte = getRgbByte(i,j);
				value = rgbByte[0] + rgbByte[1] + rgbByte[2] / 3 ;
				
				taggedBinaryImage[j][i] = 0 ;
				
				if (value>127){
					
					binaryMatrix[j][i] = 1;
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 255);
					}
				} else {
					
					binaryMatrix[j][i] = 0 ;
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 0);
					}
				}
				
				
			}
			
		}
	}

		public BinaryImage(GrayImage grayImage){ // a partir d'une grayImage
		
		super(grayImage.getRgbImage());
		binaryMatrix = new int[height][width];
		taggedBinaryImage = new int[height][width];
		binaryImage = cvCreateImage(cvSize(width, height), 8, 3);
		binaryByteBuffer = binaryImage.getByteBuffer();
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				
				taggedBinaryImage[j][i] = 0 ;
				if (grayImage.get(i, j)>127){
					
					binaryMatrix[j][i] = 1;
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 255);
					}
				} else {
					
					binaryMatrix[j][i] = 0 ;
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 0);
					}
				}
				
				
			}
			
		}
	}


	public BinaryImage(int[][] binaryMatrix) { // a partir d'une matrice binaire
		
		super(binaryMatrix);
		this.binaryMatrix = binaryMatrix ;
		taggedBinaryImage = new int[height][width];
		height = binaryMatrix.length;
		width = binaryMatrix[0].length;
		binaryImage = cvCreateImage(cvSize(width, height), 8, 3);
		binaryByteBuffer = binaryImage.getByteBuffer();
		
		for (int i =0; i<width; i++){
			for (int j=0; j<height; j++){
				
				if (binaryMatrix[j][i] == 1){
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 255);
					}
					
				} else {
					for(int k = 0; k<3; k++){
						binaryByteBuffer.put(3*i + binaryImage.widthStep()*j+k, (byte) 0);
					}
					
				}
				taggedBinaryImage[j][i] = 0;
			}
		}
		
		
		
	}
	

	/*
	 * Setters et getters
	 * 
	 */

	public IplImage getBinaryImage(){
		
		return binaryImage;
	}
	
	
	
	public int[][] getBinaryMatrix(){
		
		return binaryMatrix;
	}
	
	@Override
	public void save(String fileName){
		 if (binaryImage != null) {
           cvSaveImage(fileName, binaryImage);
		 }
	}
	
	
	@Override
	public final void saveToText(String fileName){ // sauvegarde dans un fichier texte
		
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos);
			int i; // parcours en largeur
			int j; // parcours en hauteur
			for(j=0; j<height; j++){ 
				
				for(i=0;i<width;i++){
					
					pw.print(get(i,j)); // on Žcrit l'etiquette

				}
				pw.print("\n");	// a la fin d'une ligne, on va a la ligne
			}
			pw.close();
      }
		catch (Exception e){
    	  
    	  e.printStackTrace();
      }
	}
	
	public static int[][] matrixFromTextFile(String fileName){
		
	int[][] matrix = new int[1080][1920];
		try{
			FileReader fis = new FileReader(fileName);
			BufferedReader bis = new BufferedReader (fis);
			String currentLine; // ligne courrante lue
			int j = 0; // j comptera les lignes du fichier 
			while((currentLine = bis.readLine()) != null && j<1080){
				// tant que le fichier n'est pas fini
				
				if(currentLine.length() == 1920){ // lorsque la largeur est la bonne
					
					for(int i=0; i<1920; i++){ // i compte la colonne
						
						matrix[j][i] = currentLine.charAt(i) - 48;
						
						// on remplit la matrice
					}
					j++; // a la fin d'une ligne on incremente le nombre de lignes lues
				}
			}
				
		}
		catch(IOException e){ // probleme d'ouverture de fichier
			
			e.printStackTrace();
		}
		catch(Exception e){ // probleme general
			
			e.printStackTrace();
		}
		return matrix;
	}
	
	public int[][] getCorners(){ // renvoie les coins d'une carte binaire
		
		int[][] res = new int[][]{{0,height},{0,0},{width,0},{0,0}};
		
		
		for(int i = 0 ; i<width; i++){
			for (int j = 0; j< height; j++){
				
				if (binaryMatrix[j][i] == 1)
				{
					if (i < res[2][0]) // xMin
						res[2] = new int[]{i,j};
					if (j < res[0][1]) // yMin
						res[0] = new int[]{i,j};
					if (i > res[1][0]) // xMax
						res[1] = new int[]{i,j};
					if (j > res[3][1]) // yMax
						res[3] = new int[]{i,j};
					
				}
			}
		}
		// si ymin^2 - xmin^2 < ymin^2 - xmax^2 
		if((Math.pow(res[3][1], 2)-Math.pow(res[1][0], 2)) < (Math.pow(res[3][1], 2)-Math.pow(res[2][0], 2))){
			int[] yMin = res[0], xMax = res[1], xMin = res[2], yMax = res[3];
			res[0] = xMin;
			res[1] = yMin;
			res[2] = yMax;
			res[3] = xMax;
			
			
		}
		
		
		return res;
	}

	
	// algorithme de double passage
	public int[][] conncetedComponents() {


		int k = 1;

		// initialisation de la table de correspondance
		for (int i = 1; i < maxNbTags; i++) {
			connectionTable[i] = i;
		}

		// Premier passage
		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				if (binaryMatrix[i][j] == 1)
				{
					if (binaryMatrix[i-1][j] == 0 && binaryMatrix[i][j-1] == 0) 
					{
						taggedBinaryImage[i][j]=k;
						k++;
					} 
					else 
					{
						if (taggedBinaryImage[i-1][j] == taggedBinaryImage[i][j-1] && binaryMatrix[i-1][j]==1) 
						{
							taggedBinaryImage[i][j] = taggedBinaryImage[i-1][j];
						} 
						else 
						{

							int e1 = Math.min(taggedBinaryImage[i-1][j], taggedBinaryImage[i][j-1]);
							int e2 = Math.max(taggedBinaryImage[i-1][j], taggedBinaryImage[i][j-1]);
							if (e1 == 0) 
							{
								taggedBinaryImage[i][j] = connectionTable[e2];
							}
							else
							{
								taggedBinaryImage[i][j] = connectionTable[e1];
								connectionTable[e2]=connectionTable[e1];
							}
							
							// mise ï¿½ jour de la table de la table de correspondance
							/**int a = taggedBinaryImage[i-1][j];
							if (connectionTable[a] != connectionTable[e1]) 
							{
								while (connectionTable[a] != a) 
								{
									int l = connectionTable[a];
									connectionTable[a] = connectionTable[e1];
									a = l;
								}
							}
							
							a = taggedBinaryImage[i][j-1];
							if (connectionTable[a] != connectionTable[e1]) 
							{
								while (connectionTable[a] != a) 
								{
									int l = connectionTable[a];
								connectionTable[a] = connectionTable[e1];
								a = l;
								}
							}*/
						}
					}
				}
			}
		}

		// actualisation de T
		for (int m = 1; m < k; m++) 
		{
				connectionTable[m] = connectionTable[connectionTable[m]];
		}

		// second balayage
		for (int i1 = 1; i1 < height; i1++) {
			for (int j = 1; j < width; j++) {
				if (taggedBinaryImage[i1][j] != 0) {
					taggedBinaryImage[i1][j] = connectionTable[taggedBinaryImage[i1][j]];
				}
			}
		}
		
		return taggedBinaryImage; 
		
	}

	public int [][] largestComponent(){
		int [][] tab = conncetedComponents();
		int[][] largest = new int[height][width];
		int[] compteur = new int[maxNbTags];
		for (int i=0; i<maxNbTags; i++){
			compteur[i]=0;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (tab[i][j] !=0) {
					compteur[tab[i][j]] +=1 ;
				}
			}
		}
		
		int max = 0 ; 
		int imax = 0;
		for (int i=0; i<maxNbTags; i++){
			if (compteur[i] >= max){
				max = compteur[i];
				imax = i;
			}
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (tab[i][j] != imax) {
					largest[i][j] =0 ;
				} else {
					largest[i][j]=1;
				}
			}
		}	
		
		return largest;
	}
	
}


	
	




