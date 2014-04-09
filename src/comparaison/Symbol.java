package comparaison;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;


public class Symbol {

	private int[][] doubleTab; // initialisée avec une image à une composante connexe
	private int size1;
	private int size2;
	private int perimeter;
	private Hashtable<Double, Double> signature;
	private double average;
	private double sigma;
	double [] matchTable = new double [2];
	double [] translaTable = new double [perimeter];
	int taille ;
	private ArrayList<Double> signatureTable;

	public Symbol (int[][] binaryMatrix){
		this.size1 = binaryMatrix[0].length;
		this.size2 = binaryMatrix.length;
		this.perimeter = 1000;
		this.taille = 75; // reussite avant le pan4
		this.doubleTab = binaryMatrix; // a 17h pile direction odeon 
		// n'oublie pas
		// <3  <3  <3



		/*this.flagTab = new int[size2][size1];
		for (int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				flagTab[i][j]=0;
			}
		}*/

		this.signature = new Hashtable<Double, Double>();
		signatureTable = new ArrayList<Double>();
	}

	public ArrayList<Double> getSignature(){

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
		xb=xb/sum;
		yb=yb/sum;

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

		return signatureTable;
	}



	public int getCardValue (CornerDatabase baseDonneesCoin){
		getSignature();

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

		// comparaison avec chaque carte de la base de donnée	
		for (int i=0 ; i < 2 ; i++)
		{
			matchTable[i] = compare(baseDonneesCoin, i);
		}

		// détermination du meilleur match	
		double max = 0;
		int imax = 0;

		for (int i=0 ; i < 2 ; i++)
		{
			if (max<matchTable[i]) 
			{
				imax = i; max = matchTable[i];
			}
		}
		return imax;
	}

	public double compare(CornerDatabase baseDonnesCoin, int i ){

		double rep;
		for (int j=0 ; j<perimeter ; i++){
			rep = 0;
			for (int k =0; k<perimeter; k++){
				double pixel = (signatureTable.get(k+j)-average)/sigma;
				rep = rep + pixel*(baseDonnesCoin.getSign(i,k)-baseDonnesCoin.getSignAverage(i))/baseDonnesCoin.getSignSigma(i) ;
			}
			translaTable[j] = rep;
		}
		// détermination de la translation la plus proche
		rep = 0;
		int imax = 0;
		for (int k=0 ; k < perimeter ; k++)
		{
			if (rep<translaTable[k]) 
			{
				imax = i; rep = translaTable[k];
			}
		}
		return rep;
	}



}


