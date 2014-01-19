package patternmatching;

import java.util.ArrayList;

public class BinaryImage {
	
	private int[][] frame = null; // image binaire 0=background 1=objet
	private ArrayList<int[][]> connectedComponents = null; //table de correspondance
	private Pixel[] taggedBinaryImage = null; // image initiale où les 1 sont remplacés par des pixels étiquetés
	private int size;
	
	public int[][] conncetedComponents(){
		// on conserve une table de correspondane tab initialisée à tab[i] = i
		// algo de double passage
		
		for (int i=0; i<size; i++){
			for (int j=0; j<size; j++){
			 taggedBinaryImage[i][j] = new Pixel (i,j,this);
			}
		}
		
		return taggedBinaryImage;
	}
	
	public int getFrameValue(int x, int y){
		return frame[x][y];
	}

}
