package camera;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import comparaison.CardDatabase;
import comparaison.Letter;
import comparaison.Symbol;


public class Card extends Image{



	// double [] matchTable = new double [5];
	private static double WIDTH = 82.49242389456137 /*67.8627318771778*/; // taille de la carte sur l'ecran
	private static double HEIGHT =  116.9700816448377 /*96.99174958400414*/;




	//private Image corner;
	private Symbol symbol;
	private int number=-1;
	private Letter letter;
	private int[] colorAverage = new int[3];
	
	private int compt = 0;





	public Card(IplImage image) {

		super(image);

		//corner = this.cut(0,0,110, 220);
		symbol = new Symbol(getFirstSymbol(), this); // letter est définie ici


		//new BinaryImage(getFirstSymbol()).save("data/database/symbols/symboltest.jpg");

	}

	public Card(String fileName) {

		super(fileName);
		name = fileName;
	

		//corner = this.cut(0,0, 110,  220);
		symbol = new Symbol(getFirstSymbol(), this);




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

	//public int getCompt(){

//		return compt;
//	}








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









	public String getType() throws Exception{

		String string;

		if (isRed(colorAverage)) // carte rouge
			string = "1";
		else{
			string = "0"; // carte noire
			if (number ==1 && compt > 50000)
				return string+"10"; // As de pique
			else if (number == 1)
				return string+"00";
		}
		

		string = string+symbol.getCardValue(string);

		if (number ==0)
			throw new Exception("Pas de composantes sur la carte");
		else if (number ==1 && compt < 100000) // as autre que pique
			string = string+0;
		else if (number >= 7 && number <= 10) // 7, 8, 9, 10
			string =string+ (number-6);
		else 
			string = string+(letter.getType()+5); // V=5, D=6, R=7
		return string;


	}
	
	public void printInfos(String fileName) throws Exception{
		try {
			FileWriter fw = new FileWriter(fileName,true);
			String infos = colorAverage[0] +" "+ colorAverage[1] +" "+ colorAverage[2]+" ";
			infos += number +" "+compt+" "+ letter.momentum(2)+" ";
			if(isRed(colorAverage))
				infos += symbol.getMatchTable("1")[0]+" " +symbol.getMatchTable("1")[1]+" ";
			else
				infos+=  symbol.getMatchTable("0")[0]+" " +symbol.getMatchTable("0")[1]+" ";
			infos += letter.getDistanceTable()[0]+" "+letter.getDistanceTable()[1]+" "+letter.getDistanceTable()[2]+" ";
			fw.write(infos+"\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int[][] getFirstSymbol(){
		
		
		BinaryImage bin = (this.binaryThreshold(3)); // Ce qui n'est pas blanc
		compt = bin.getCompt();
		bin.save("data/database/symbols/test.jpg");
		letter = new Letter (bin.cut(0,0, 100, 160).largestComponent().getBinaryMatrix());
		 bin = (this.binaryThreshold(3)); 
		int[] res = bin.componentsNumberAndFirst();
		number = res[0];
		new BinaryImage(bin.filter(res[1])).save("data/test/image/image1.jpg");
		int[][] mat = bin.filter(res[1]);
		int somme = 0;
		
		for(int i =0; i < mat[0].length; i++){
			for(int j=0; j< mat.length; j++){
				int[] rgbByte = getRgbByte(i,j);
				for(int p =0; p<3; p++){
					colorAverage[p] += rgbByte[p]*mat[j][i];
					
					
				}
				somme += mat[j][i];
			}
		}
		
		for(int p =0; p<3; p++){
			colorAverage[p] = (int) Math.round((float)colorAverage[p]/somme);
		}
		
		return mat;

	}

	public void computeAverageColor(){


	}


/*
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
	/public String find(CardDatabase[] tab){

			String res;
			int nbr = getType();

			res = findIn(tab[nbr]);

			return res;



		}*/



	public ArrayList<Double> getSignature() throws Exception{
		return symbol.getSignature();
	}


	//public Image getCorner() {
	//	return corner;

	//}

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

	public Symbol getSymbol() {
		// TODO Auto-generated method stub
		return symbol;
	}

	public Letter getLetter() {
		// TODO Auto-generated method stub
		return letter;
	}







}
