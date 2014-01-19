package patternmatching;

import java.util.ArrayList;

public class BinaryImage {
	
	private int[][] frame = null; // image binaire 0=background 1=objet
	private ArrayList<int[][]> connectedComponents = null; //table de correspondance
	private Pixel[] taggedBinaryImage = null; // image initiale o� les 1 sont remplac�s par des pixels �tiquet�s
	private int size;
	
	public int[][] conncetedComponents(){
		// on conserve une table de correspondane tab initialis�e � tab[i] = i
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
