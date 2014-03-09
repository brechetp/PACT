package camera;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import comparaison.CardDatabase;


public class Card extends Image{
	
	

	private static final double[] yellow = new double[]{150,200,200};
	private static final double[] averageYellow = new double[]{163.4125, 247.32, 248.835};
	private static final double[] sigmaYellow = new double[]{8.154896918416563, 3.0153938382904544, 2.786175694388279};
	// double [] matchTable = new double [5];


	private Image corner;

	
	

	
	public Card(IplImage image) throws Exception{
		
		super(image);
		average = new double[3]; // moyenne sur RGB
		sigma = new double[3]; // ecart type sur RGB
		if (width != 635 || height != 889){
			throw new Exception("Ce n'est pas une carte");
		}

		corner = this.cut(0,0,100, 250);


		
		computeAverage();
		computeSigma();
		
	}
	
	public Card(String fileName) throws Exception{
		
		super(fileName);
		name = fileName;
		average = new double[3]; // moyenne sur RGB
		sigma = new double[3]; // ecart type sur RGB
		if (width != 635 || height != 889){
			throw new Exception("Ce n'est pas une carte");
		}

		corner = this.cut(0,0, 100,  250);


		
		computeAverage();
		computeSigma();
		
	}
	


/*
 * 
 * Setters and getters
 * 
 */
	
	public double[] getAverage(){
		
		return average;
	}
	
	public double[] getSigma(){
		
		return sigma;
	}
	
	public String getName(){
		
		return name;
	}
	
	public int getCompt(){
		
		return compt;
	}
	
	
	


	
	
	
	public String findIn(CardDatabase database){
		
		  int size = database.getSize();
		  double[][] matchTable = new double[size][3];
		  
		  // comparaison avec chaque carte de la base de donnée	
			for (int i=0 ; i < size ; i++)
			{
				matchTable[i] = compare(database.getCard(i), 3);
			}
			
		  // détermination du meilleur match	
		    
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
			//prendre garde à rajouter kmin et jmin pour grdes images
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
			double[] average = corner.getAverage();
			if (average[2] > 200) // carte rouge
				string = "1";
			else
				string = "0"; // carte noire
			int nbr = getComponentsNumber (6000);
			if (nbr ==1) // as
				string = string+"0";
			else if (nbr >= 7 && nbr <= 10) // 7, 8, 9, 10
				string = string+(nbr-6);
			else 
				string = string+"5"; // V, D, R
			return string;
				
			
		}
		
		public int getComponentsNumber(int size){
			
			BinaryImage bin = new BinaryImage(this.threshold(threshold).cut(50, 50, 535, 789)); //on enleve les bords
			int res = bin.componentsNumber(size);
			
			return res;
			

			
		}
		
		public String find(CardDatabase[][] tab){
			
			String res;
			String string = getType();
			
			res = findIn(tab[(int)string.charAt(0)-48][(int)string.charAt(1)-48]);
			
			return res;
			
			
			
		}
		
		
			

	

}
