package camera;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;



public class BinaryImage extends GrayImage {
	
	private static final int COMPONENT_THRESHOLD = 3000;
	protected int[][] binaryMatrix ;
	private IplImage binaryImage;
	private ByteBuffer binaryByteBuffer;
	
	private int maxNbTags=100000;
	private int[] connectionTable = new int[maxNbTags];
	public int[][] taggedBinaryImage; // image initiale o� les 1 sont
												// remplac�s par des pixels
												// �tiquet�s
	private int nbTags = 0;
	
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
	
	public BinaryImage(String string) {
		this(new Image(cvLoadImage(string)));
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
	public int get(int i, int j){
		
		return binaryMatrix[j][i];
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
					
					pw.print(get(i,j)); // on �crit l'etiquette

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
			bis.close();
				
		}
		catch(IOException e){ // probleme d'ouverture de fichier
			
			e.printStackTrace();
		}
		catch(Exception e){ // probleme general
			
			e.printStackTrace();
		}
		return matrix;
	}
	
	
	// algorithme de double passage
	public int[][] connectedComponents() {


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
							
							// mise � jour de la table de la table de correspondance
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
		nbTags = k;
		return taggedBinaryImage; 
		
	}

	public BinaryComponent largestComponent(){
		
		int [][] tab = connectedComponents();
		int[][] largest = new int[height][width];
		int[] compteur = new int[nbTags];
		for (int i=0; i<nbTags; i++){
			compteur[i]=0;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (tab[i][j] !=0) {
					compteur[tab[i][j]]++ ;
				}
			}
		}
		
		int max = 0 ; 
		int imax = 0;
		for (int i=0; i<nbTags; i++){
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
		
		return new BinaryComponent (largest, max);
	}
	

	public BinaryComponent largeComponents(){ // renvoie les grandes composantes
		
		int [][] tab = connectedComponents();
		int[][] largest = new int[height][width];
		int[] compteur = new int[nbTags];
		int compt = 0; // compte le nombre de pixels gardes
		
		for (int i=0; i<nbTags; i++){
			compteur[i]=0;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (tab[i][j] !=0) {
					compteur[tab[i][j]]++ ;
				}
			}
		}
		
		
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (compteur[tab[i][j]] >= COMPONENT_THRESHOLD) {
					largest[i][j] =1 ;
					compt++;
				} else {
					largest[i][j]=0;
				}
			}
		}	
		
		return new BinaryComponent (largest, compt);
	}
	
	public int[] componentsNumberAndFirst(int size, int sizeMax){
		
		int [][] tab = connectedComponents();
	
		int[] compteur = new int[nbTags];
		for (int i=0; i<nbTags; i++){
			compteur[i]=0;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (tab[i][j] !=0) {
					compteur[tab[i][j]]++ ;
				}
			}
		}
		
		int[] res = new int[]{0,-1}; // res[0] contient le nombre de composantes connexes, res[1] l'indice de la première
		for (int i=0; i<nbTags; i++){
			if (compteur[i] >= size){
				res[0]++;
				if(res[1] == -1 && compteur[i] <= sizeMax){
					res[1] = i;
				}
				
			}
		}
		
			
		
		return res;
	}
	
	public int[][] filter(int indice){
		
		int[][] res  = new int[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (taggedBinaryImage[i][j] == indice) {
					res[i][j] = 1;
				}
				else
					res[i][j] =0;
			}
		}
		return res;
	}
	
	
	/*
	 * 
	 * Et logique entre deux images binaire
	 * 
	 * 
	 */
	
	public BinaryImage and(BinaryImage image){
		
		int[][] res = new int[height][width];
		
		for(int j = 0; j<height; j++){
			for(int i =0; i< width; i++){
				
				res[j][i] = this.get(i,j) * image.get(i, j);
			}
		}
		
		
		return new BinaryImage(res);
		
		
	}
	
	/*
	 * Detection de contours
	 * 
	 */
	
	public EdgeImage getEdge(){
		
		int[][] res = new int[height][width];
		ArrayList<int[]> list = new ArrayList<int[]>();
		
		
		for (int i =1; i < width-1; i ++){
			for(int j =1; j < height-1; j++){
				
				res[j][i] = binaryMatrix[j][i]*Math.min(1, (Math.abs(binaryMatrix[j][i]-binaryMatrix[j+1][i]) +Math.abs(binaryMatrix[j][i]-binaryMatrix[j+1][i])+Math.abs(binaryMatrix[j][i]-binaryMatrix[j-1][i])+Math.abs(binaryMatrix[j][i+1]-binaryMatrix[j][i-1]))) ;
				if (res[j][i]==1)
					list.add(new int[]{i,j});
			}
		}
		
		return new EdgeImage (res, list);
		
	}
	
	public int[][] ransac(int type){
		
		return getEdge().ransac(type);
	}

}
	
	

	
	




