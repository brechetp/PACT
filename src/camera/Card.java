package camera;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import comparaison.CardDatabase;
import comparaison.Symbol;


public class Card extends Image{
	
	

	// double [] matchTable = new double [5];
	static double WIDTH = 154.4845505703325 ; // taille de la carte sur l'ecran
	static double HEIGHT = 216.90557484451733;
	



	private Image corner;
	private Symbol symbol;
	private int number=-1;

	
	

	
	public Card(IplImage image) {
		
		super(image);
		average = new double[3]; // moyenne sur RGB
		sigma = new double[3]; // ecart type sur RGB
		
		corner = this.cut(0,0,110, 220);
		symbol = new Symbol(this.cut(60, 90, 185, 185).binaryThreshold(0).getBinaryMatrix());


		
		computeAverage();
		computeSigma();
		
	}
	
	public Card(String fileName) {
		
		super(fileName);
		name = fileName;
		average = new double[3]; // moyenne sur RGB
		sigma = new double[3]; // ecart type sur RGB
	

		corner = this.cut(0,0, 110,  220);
		symbol = new Symbol(this.cut(60, 90, 185, 185).binaryThreshold(0).getBinaryMatrix());


		
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
		
		
		
		

		
		
		
		public boolean isThereYellow(){
			
			float compt = 0;
			int k = 0;
			while( compt/(height*width) < 0.1 && k<height*width){
				
				int[] rgbByte = getRgbByte(k);
				/*double distance = 0;
				for(int p=0; p<3; p++){
					distance += Math.pow(rgbByte[p]-yellow[p], 2);
				}
				if (distance < threshold)
					res = true;*/
				if (rgbByte[0] < 180 && rgbByte[1] > 240 && rgbByte[2] > 240)
					compt++;
				k++;
			}
			return compt/((float) height*width) >= 0.1;
			
			
		}

	
		
		public String getType(){
			
			String string;
			double[] average = corner.getThresholdedAverage();
			if (average[2] > 180 ) // carte rouge
				string = "1";
			else
				string = "0"; // carte noire
			
			symbol = new Symbol (getFirstSymbol(10000, 50000));
			
			if (number ==0)
				string = null;
			else if (number ==1 && compt < 100000) // as
				string = string+0;
			else if (number >= 7 && number <= 10) // 7, 8, 9, 10
				string =string+ (number-6);
			else 
				string = string+5; // V, D, R
			return string;
				
			
		}
		
		public int[][] getFirstSymbol(int sizeMin, int sizeMax){
			
			BinaryImage bin = (this.binaryThreshold(0)); 
			bin.save("data/test/binary/test.jpg");
			int[] res = bin.componentsNumberAndFirst(sizeMin, sizeMax);
			number = res[0];
			
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
		
			
			
		public double[] getSignature(){
			return symbol.getSignature();
		}
		

		public Image getCorner() {
			return corner;
			
		}
		
		
			

	

}
