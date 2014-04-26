package comparaison;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import camera.BinaryImage;
import camera.Capture;
import camera.Image;

public class Letter extends BinaryImage{

	private static final int CENTERED_WIDTH = 91;
	private static final int CENTERED_HEIGHT = 117;
	private static int[][][] LETTER_DATABASE = new int[3][getCenteredHeight()][getCenteredWidth()];
	private int type = -1;
	private int[][] centeredLetter = null;
	private int[] distanceTable = new int[3]; // distances aux lettres de la DB

	public Letter(int[][] binaryMatrix){

		super(binaryMatrix);

	}
	
	public int[][] getCenteredLetter(){
		
		if(centeredLetter == null)
			center(barycentre()); // on centre autour du barycentre
		return centeredLetter;
	}

	public void computeType(){

		int res = 0;
		int[] barycentre = barycentre();
		center(barycentre); // centre l'image autour de son barycentre
		Capture.write(centeredLetter, "data/test/lettre.txt");

		int minDistance = Integer.MAX_VALUE;
		int d =0;
		for (int index =0; index <3; index++){
			d = distance(index);
			distanceTable[index] = d;

			if (d  < minDistance){
				res = index;
				minDistance = d;
			}
				
		}

		type = res ;



	}
	
	public int[] getDistanceTable(){
		
		if (distanceTable == null)
			computeType();
		return distanceTable;
	}

	public int getType(){

		if (type == -1)
			computeType();
		return type;
	}



	private int distance(int index) { // index désigne J, Q ou K

		int distance = 0;
		for(int i = 0; i < getCenteredWidth(); i++){

			for(int j = 0; j < getCenteredHeight(); j++){

				distance += (binaryMatrix[j][i] + LETTER_DATABASE[index][j][i]) % 2;
			}
		}


		return distance;
	}

	private void center(int[] barycentre){

		centeredLetter = new int[getCenteredHeight()][getCenteredWidth()];
		int xb = barycentre[0];
		int yb = barycentre[1];
		for(int i =0; i < getCenteredWidth(); i++){

			for(int j =0; j< getCenteredHeight(); j++){

				if (0<=j+yb-getCenteredHeight()/2 && j+yb-getCenteredHeight()/2 < height 
						&& 0 <= i+xb-getCenteredWidth()/2 && i+xb-getCenteredWidth()/2 < width)
					centeredLetter[j][i] = binaryMatrix[j+yb-getCenteredHeight()/2][i+xb-getCenteredWidth()/2];
			
			}
		}

	}


	public int[] barycentre(){

		int xb = 0;
		int yb =0;
		double sum = 0;

		for (int i =0; i<width; i++)
		{
			for (int j=0; j<height; j++)
			{
				xb = xb+i*binaryMatrix[j][i];
				sum = sum + binaryMatrix[j][i];
				yb = yb+j*binaryMatrix[j][i];;
			}
		}
		xb=(int) Math.round(xb/sum);
		yb=(int) Math.round(yb/sum);

		return new int[]{xb, yb};

	}

	public static void setLetterDatabase(String fileName) throws IOException{

		for(int letter =0; letter<3; letter++){
			FileReader fis = new FileReader(fileName+(letter)+".txt");
			BufferedReader bis = new BufferedReader (fis);
			String line;
			int j =0; // compte les colonnes


			while((line = bis.readLine()) != null){
				for(int i = 0; i < getCenteredWidth(); i++){


					LETTER_DATABASE[letter][j][i]= (int) line.charAt(i) - 48;

				}
				j++;

			}
			bis.close();


		}


	}

	public static int getCenteredWidth() {
		return CENTERED_WIDTH;
	}

	public static int getCenteredHeight() {
		return CENTERED_HEIGHT;
	}

}
