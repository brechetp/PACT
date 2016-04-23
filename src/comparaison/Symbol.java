package comparaison;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

import camera.Card;



public class Symbol {

	
	private static final double RED_OO = 30000;//37790;
	private static final double BLACK_OO = -4910.2;
	private static final double RED_COEFF =1.0602;
	private static final double BLACK_COEFF = 0.9831; 
	private static ArrayList<ArrayList<Double>> SYMBOL_DATABASE =
			new ArrayList<ArrayList<Double>>();
	private static int[][][][] SYMBOL_DATABASE2;
	private static double[][] AVERAGE_DATABASE = new double[2][2];
	private static double[][] SIGMA_DATABASE = new double[2][2];
	private int[][] doubleTab; // initialisée avec une image à une composante connexe
	private int size1;
	private int size2;
	private int perimeter;
	private Hashtable<Double, Double> signature;
	private double average;
	private double sigma;
	double [] matchTable = new double [2];
	double [] translaTable = new double [10];
	double [] matchTable2 = new double [2];
	int taille ;
	private ArrayList<Double> signatureTable;


	public Symbol (int[][] binaryMatrix, Card carte){
		this.size1 = binaryMatrix[0].length;
		this.size2 = binaryMatrix.length;

		this.taille = 80; // reussite avant le pan4
		this.doubleTab = binaryMatrix; // a 17h pile direction odeon 
		// n'oublie pas
		// <3  <3  <3
		/*		int somme =0;

		for(int i = 0; i< size2; i++)
		{
			for (int j =0; j < size1; j++){
				redAverage += binaryMatrix[i][j] * carte.getRgbByte(i, j)[2];
				somme += binaryMatrix[i][j];
			}
		}
		redAverage = redAverage/somme;*/



		/*this.flagTab = new int[size2][size1];
		for int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				flagTab[i][j]=0;
			}
		}*/

		signature = new Hashtable<Double, Double>();
		signatureTable = new ArrayList<Double>();
	}

	/*	public int getRedAverage(){
		return redAverage;
	}*/

	public static void setSymbolsDatabase(String fileName) throws NumberFormatException, IOException{



		for(int type =0; type<4; type++){
			FileReader fis = new FileReader(fileName+(type/2)+(type%2)+".txt");
			BufferedReader bis = new BufferedReader (fis);
			String value;
			ArrayList<Double> res = new ArrayList<Double>();

			while((value = bis.readLine()) != null){
				res.add(Double.parseDouble(value));
				// Calcul de average et sigma 

			}
			bis.close();
			double average = 0;
			double variance = 0;
			double perimeter = res.size();

			for (int i =0; i<perimeter; i++){	
				average += res.get(i);
			}	

			average = average/(perimeter);	

			for (int i =0; i<perimeter; i++){
				variance += Math.pow(res.get(i)-average,2);
			}
			double sigma = Math.sqrt(variance/(perimeter)); //sigma=1; average = 0;


			AVERAGE_DATABASE[type/2][type%2] = average;
			SIGMA_DATABASE[type/2][type%2] = sigma;
			SYMBOL_DATABASE.add(type,res);
		}


	}

	public void computeSignature() throws Exception{

		//détermination du barycentre
		int xb = 0;
		int yb =0;
		int sum = 0;

		for (int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				xb = xb+j*doubleTab[i][j];
				sum = sum + doubleTab[i][j];
				yb = yb+i*doubleTab[i][j];;
			}
		}
		if (sum !=0){
			xb=xb/sum;
			yb=yb/sum;
		}
		else
			throw new Exception("Pas de symbol trouvé");

		/*remplissage de la table
		for (int s=0 ; s<perimeter ; s++){
			int t =0;
			if (doubleTab[(int) Math.floor(t*Math.cos(s))+xb][(int) Math.floor(t*Math.sin(s))+yb]==0 ){
				signature[s]=Math.sqrt( Math.pow(t*Math.cos(s),2) + Math.pow(t*Math.sin(s),2));
			} else {
				t++;
			}
		}*/
		double key =0;
		for (int i=yb-taille ; i<yb+taille ; i++){
			for (int k = xb-taille; k<xb+taille; k++){
				if (doubleTab[i][k]==1){
					if(k-xb != 0)
						key=Math.atan((i-yb)/(k-xb));

					double radius=(i-yb)*(i-yb) + (k-xb)*(k-xb);
					if (signature.containsKey(key)==false){
						signature.put(key, radius);
					} else {
						if (radius > signature.get(key)){
							signature.put(key, radius);
						}
					}
				}
			}
		}

		Set<Double> set = signature.keySet(); //hashtable
		Object[] tab = set.toArray();
		Arrays.sort(tab);

		for (int i=0 ; i<tab.length ; i++){
			signatureTable.add(i,signature.get(tab[i]));
		}



	}

	public ArrayList<Double> getSignature() throws Exception{

		if (signatureTable.size() == 0)
			computeSignature();
		return signatureTable;
	}



	public int getCardValue (String string) throws Exception{

		if (signatureTable.size() == 0)
			computeSignature();

		// Calcul de average et sigma 
		average = 0; double variance = 0;
		perimeter = signatureTable.size();

		for (int i =0; i<perimeter; i++){	
			average += signatureTable.get(i);
		}	

		average = average/(perimeter);	

		for (int i =0; i<perimeter; i++){
			variance += Math.pow(signatureTable.get(i)-average,2);
		}
		sigma = Math.sqrt(variance/(perimeter)); //sigma=1; average = 0;
		int color = (int) string.charAt(0) - 48; // 0 si noir, 1 si rouge

		// comparaison avec chaque carte de la base de donnée	
		for (int i=0 ; i < 2 ; i++)
		{
			matchTable[i] = compare(color, i) ;//-MOYENNE[(int) color.charAt(0) - 48][i];
		}

		// détermination du meilleur match	


		if (matchTable[1] > ((1-color)*BLACK_COEFF+color*RED_COEFF)*matchTable[0]+((1-color)*BLACK_OO+color*RED_OO))
			return 1;
		else
			return 0;

	}



	public double[] getMatchTable(String color) throws Exception{
		if (matchTable == null)
			getCardValue(color);
		return matchTable;
	}

	public double compare(int color, int forme ){

		double rep=0;

		int minsize = Math.min(perimeter,Math.min(SYMBOL_DATABASE.get(2*color+forme).size(), SYMBOL_DATABASE.get(2*color+(1-forme)).size()));

		for (int j=0 ; j<1 ; j++){//translation désactivée, réactiver avec minsize
			rep = 0;
			for (int k =0; k<minsize; k++){
				double pixel = (signatureTable.get((k+j)%minsize)-average)/sigma;
				rep = rep + pixel*(SYMBOL_DATABASE.get(2*color+forme).get(k)-AVERAGE_DATABASE[color][forme]/SIGMA_DATABASE[color][forme]) ;
			}
			translaTable[j] = rep;
		}
		// détermination de la translation la plus proche
		rep = 0;
		//int formeMax = 0;
		for (int k=0 ; k < 1 ; k++)
		{
			if (rep<translaTable[k]) 
			{
				rep = translaTable[k];
			}
		}
		return rep;
	}

	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return doubleTab;
	}


	public int getCardValue2 (String color){


		sigma=1; average = 0; // A enlever si niveaux de gris

		// comparaison avec chaque carte de la base de donnée	
		for (int i=0 ; i < 2 ; i++)
		{
			matchTable[i] = compare2(color, i);
		}

		// détermination du meilleur match	
		double min = Integer.MAX_VALUE;
		int iMin = 0;

		for (int i=0 ; i < 2 ; i++)
		{
			if (matchTable2[i]<min) 
			{
				iMin = i; min = matchTable[i];
			}
		}
		return iMin;
	}

	private double compare2(String string, int forme) {

		double rep=0;
		int color1 = (int) string.charAt(0) - 48; // 0 si noir, 1 si rouge

		rep = 0;

		for (int k =0; k<size2; k++)
		{
			for (int j=0; j<size1; j++)
			{
				rep = rep + doubleTab[k][j]*SYMBOL_DATABASE2[color1][forme][k][j];
			}
		}

		return rep;
	}

	public static void setSymbolDatabase2(String fileName) throws IOException{

		for(int color =0; color<2; color++){
			for(int forme =0; forme<2; forme++){
				FileReader fis = new FileReader(fileName+(color)+(forme)+".txt");
				BufferedReader bis = new BufferedReader (fis);
				String line;
				int j =0; // compte les colonnes


				while((line = bis.readLine()) != null){
					for(int i = 0; i < line.length(); i++){

						SYMBOL_DATABASE2[color][forme][j][i]= (int) line.charAt(i) - 48;

					}
					j++;


					bis.close();
				}
			}
		}
	}
	
	public void writeSignature(String fileName) throws FileNotFoundException {

		if (signatureTable.size() == 0)
			try {
				computeSignature();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos);


			for(Double current : signatureTable){ 


				pw.println(current); // on �crit l'etiquette

			}

			pw.close();
		
	}

}


