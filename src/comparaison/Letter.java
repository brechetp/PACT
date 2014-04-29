package comparaison;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import camera.BinaryImage;
import camera.Capture;
import camera.Image;

public class Letter extends BinaryImage{

	private static final int CENTERED_WIDTH = 91;
	private static final int CENTERED_HEIGHT = 117;
	private static int[][][][] LETTER_DATABASE = new int[3][1][getCenteredHeight()][getCenteredWidth()];
	private int type = -1;
	private int[][] centeredLetter = null;
	private int[] distanceTable = new int[3]; // distances aux lettres de la DB
	private int[] barycentre = null;

	public Letter(int[][] binaryMatrix){

		super(binaryMatrix);

	}

	public int[][] getCenteredLetter(){

		if(centeredLetter == null)
			center(getBarycentre()); // on centre autour du barycentre
		return centeredLetter;
	}

	public void computeType(){

		int res = 0;
		int[] barycentre = getBarycentre();
		center(barycentre); // centre l'image autour de son barycentre


		int maxDistance = 0;
		int d =0;
		for (int index =0; index <3; index++){
			d = distance(index);
			distanceTable[index] = d;

			if (maxDistance < d){
				res = index;
				maxDistance = d;
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

				distance += (centeredLetter[j][i]*LETTER_DATABASE[index][0][j][i]) ;
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


	public void computeBarycentre(){

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

		barycentre = new int[]{xb, yb};

	}

	public int[] getBarycentre(){

		if(barycentre == null)
			computeBarycentre();
		return barycentre;
	}

	public static void setLetterDatabase(String fileName) throws IOException{

		for(int letter =0; letter<3; letter++){
			FileReader fis = new FileReader(fileName+(letter)+".txt");
			BufferedReader bis = new BufferedReader (fis);
			String line;
			int j =0; // compte les colonnes


			while((line = bis.readLine()) != null){
				for(int i = 0; i < getCenteredWidth(); i++){


					LETTER_DATABASE[letter][0][j][i]= (int) line.charAt(i) - 48;

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

	public void write(String fileName) {

		if (centeredLetter == null)
			center(getBarycentre());
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos);
			String line;

			for(int j = 0; j< centeredLetter.length;j++ ){
				line = ""+centeredLetter[j][0];
				for(int i = 1; i < centeredLetter[0].length; i++){

					line += centeredLetter[j][i]; // on buff la ligne

				}


				if (j != (centeredLetter.length -1))
					pw.println(line); // on �crit l'etiquette et on saute une ligne
				else 
					pw.print(line);

			}

			pw.close();
		}
		catch (Exception e){

			e.printStackTrace();
		}

	}

	public double momentum(double ordre){

		if (centeredLetter == null)
			center(getBarycentre()); 
		double res =0;

		for(int i =0; i < CENTERED_WIDTH; i++){
			for(int j = 0; j< CENTERED_HEIGHT; j++){
				res += centeredLetter[j][i]*Math.pow(i-barycentre[0], ordre)*Math.pow(j-barycentre[1],  0);
			}
		}

		res = res/(Math.pow(getCompt(), ordre+1));
		return res;



	}

	public void test(int compteur){

		int[][] res = new int[CENTERED_HEIGHT][CENTERED_WIDTH];
		for(int index =0; index < 3;index++){

			for(int i = 0; i < getCenteredWidth(); i++){

				for(int j = 0; j < getCenteredHeight(); j++){

					res[j][i] = (centeredLetter[j][i]*LETTER_DATABASE[index][0][j][i]) ;
				}
			}

			BinaryImage bin = new BinaryImage(res);
			bin.save("data/test/letters/letter"+6*compteur+index+".jpg");

		}
		for(int index =3; index < 6;index++){

			for(int i = 0; i < getCenteredWidth(); i++){

				for(int j = 0; j < getCenteredHeight(); j++){

					res[j][i] = ((centeredLetter[j][i]+LETTER_DATABASE[index-3][0][j][i])%2) ;
				}
			}

			BinaryImage bin = new BinaryImage(res);
			bin.save("data/test/letters/letter"+6*compteur+index+".jpg");

		}





	}

	public BinaryImage xor(BinaryImage image){

		int[][] res = new int[height][width];

		for(int j = 0; j<height; j++){
			for(int i =0; i< width; i++){

				res[j][i] = ((this.get(i,j) + image.get(i, j)) % 2);
			}
		}


		return new BinaryImage(res);
	}
}
