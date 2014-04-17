package camera;

import java.util.ArrayList;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import comparaison.CardDatabase;
import comparaison.Letter;
import comparaison.Symbol;


public class Card extends Image{



	// double [] matchTable = new double [5];
	private static double WIDTH = 62.80442690779988; // taille de la carte sur l'ecran
	private static double HEIGHT = 89.75719688630852;




	private Image corner;
	private Symbol symbol;
	private int number=-1;
	private Letter letter;





	public Card(IplImage image) {

		super(image);
		average = new double[3]; // moyenne sur RGB
		sigma = new double[3]; // ecart type sur RGB

		corner = this.cut(0,0,110, 220);
		symbol = new Symbol(getFirstSymbol()); // letter est définie ici
		

	//new BinaryImage(getFirstSymbol()).save("data/database/symbols/symboltest.jpg");

		computeAverage();
		computeSigma();

	}

	public Card(String fileName) {

		super(fileName);
		name = fileName;
		average = new double[3]; // moyenne sur RGB
		sigma = new double[3]; // ecart type sur RGB


		corner = this.cut(0,0, 110,  220);
		symbol = new Symbol(getFirstSymbol());





		computeAverage();
		computeSigma();

	}



	/*
	 * 
	 * Setters and getters
	 * 
	 */

	/*public double[] getAverage(){

		return average;
	}

	public double[] getSigma(){

		return sigma;
	}*/

	public String getName(){

		return name;
	}

	public int getCompt(){

		return compt;
	}








	public String findIn(CardDatabase database){

		int size = database.getSize();
		double[][] matchTable = new double[size][3];

		// comparaison avec chaque carte de la base de donn�e	
		for (int i=0 ; i < size ; i++)
		{
			matchTable[i] = compare(database.getCard(i), 0);
		}

		// d�termination du meilleur match	

		double maxDistance = 0;
		int imax = 0;

		for (int i=0 ; i < size ; i++)
		{
			double norme = 0;
			for(int compt = 0; compt <3; compt++){
				norme += Math.pow(matchTable[i][compt], 2);
			}
			if (maxDistance<norme) 
			{
				imax = i; maxDistance = norme;
			}
		}
		return database.getCard(imax).getName();
	}

	public double[] compare(Card card, int nbr){ // (2*nbr+1)^2 est le nombre de voisins 

		double[] rep =new double[3];
		double[] pixel = new double[3];
		int[] rgbByte;


		for (int j =0; j<height; j++)
		{
			for (int i=0; i<width; i++)
			{
				rgbByte = getRgbByte(i,j);
				pixel = new double[3];
				for(int k =0; k<3; k++){
					pixel[k] = normalize(rgbByte, average, sigma, k);
				}
				double[] neighbour = card.neighbourPixel(i, j, pixel, nbr);
				for(int k =0; k<3; k++){
					rep[k] = rep[k] + pixel[k]*neighbour[k];
				} 

			}

		}	
		//System.out.println(rep);
		return rep;

	}
	public double[] neighbourPixel(int i, int j, double[] pixel, int nbr){ // retourne le pixel voisin de pixel 

		double distance = 0 , distanceMin = Integer.MAX_VALUE;
		double res[] = new double[3];
		for(int p = Math.max(0, j-nbr); p <= Math.min(height-1, j+nbr); p++){
			for(int n = Math.max(0, i-nbr); n <= Math.min(width-1,  i+nbr); n++){

				int[] rgbByte = getRgbByte(n,p); // Byte de la carte dans la db
				distance = 0 ;
				for(int compt = 0; compt<3; compt++){

					distance += Math.abs(normalize(rgbByte, average, sigma, compt) -pixel[compt]);

				}

				if (distance < distanceMin){
					for(int compt = 0; compt<3; compt++){

						res[compt] = Math.abs(normalize(rgbByte, average, sigma, compt));

					}
					distanceMin = distance;
				}
			}
		}
		//prendre garde � rajouter kmin et jmin pour grdes images
		return res ;
	}
	public double normalize(double[] value, double[] average, double[] sigma, int k){

		return (value[k]-average[k])/sigma[k];
	}
	public double normalize(int[] value, double[] average, double[] sigma, int k){

		return (value[k]-average[k])/sigma[k];
	}









	public String getType(){

		String string;
		double[] average = corner.getThresholdedAverage();
		if (average[2] > 180 ) // carte rouge
		string = "1";
		else
			string = "0"; // carte noire

		string = string+symbol.getCardValue(string);

		if (number ==0)
			string = null;
		else if (number ==1 && compt < 100000) // as
			string = string+0;
		else if (number >= 7 && number <= 10) // 7, 8, 9, 10
			string =string+ (number-6);
		else 
			string = string+(letter.getType()+5); // V=5, D=6, R=7
		return string;


	}

	public int[][] getFirstSymbol(){

		BinaryImage bin = (this.binaryThreshold(0)); 
		bin.save("data/database/symbols/test.jpg");
		letter = new Letter (bin.cut(0,0, 100, 160).largestComponent().getBinaryMatrix());
		int[] res = bin.componentsNumberAndFirst();
		number = res[0];
		new BinaryImage(bin.filter(res[1])).save("data/test/image/image1.jpg");
		return bin.filter(res[1]);

	}



	public String find(CardDatabase[][] tab){

		String res;
		String string = getType();

		if (string != null) {
			res = findIn(tab[(int) string.charAt(0) - 48][(int) string
			                                              .charAt(1) - 48]);
			return res;
		} else {
			return "Pas de carte sur la table";
		}



	}
	/*public String find(CardDatabase[] tab){

			String res;
			int nbr = getType();

			res = findIn(tab[nbr]);

			return res;



		}*/



	public ArrayList<Double> getSignature(){
		return symbol.getSignature();
	}


	public Image getCorner() {
		return corner;

	}

	public static double getWIDTH() {
		return WIDTH;
	}

	public static void setWIDTH(double wIDTH) {
		WIDTH = wIDTH;
	}

	public static double getHEIGHT() {
		return HEIGHT;
	}

	public static void setHEIGHT(double hEIGHT) {
		HEIGHT = hEIGHT;
	}







}
