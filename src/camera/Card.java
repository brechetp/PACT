package camera;

import comparaison.CardDatabase;

public class Card extends Image{
	

	// double [] matchTable = new double [5];
	private double[] average;
	private double[] sigma;
	private String name;
	
	public Card(String fileName) throws Exception{
		
		super(fileName);
		name = fileName;
		average = new double[3];
		sigma = new double[3];
		if (width != 635 || height != 889){
			throw new Exception("Ce n'est pas une carte");
		}
		
		for (int i =0; i<width; i++)
		{
			for (int j=0; j<height; j++)
			{
				for (int p =0; p<3; p++){
					average[p] += getRgbByte(i, j)[p];
				}
				
			}
		}	
		
		for (int i =0; i<width; i++)
		{
			for (int j=0; j<height; j++)
			{
				for (int p =0; p<3; p++){
					sigma[p] += Math.pow(getRgbByte(i, j)[p]-average[p],2);					}
				
			}
		}	
		
		for(int p =0; p<3; p++){
			average[p] = average[p]/(width*height);
			sigma[p] = Math.sqrt(sigma[p]/(width*height));
		}
		
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
	
	public String findIn(CardDatabase database){
		
		  int size = database.getSize();
		  double[][] matchTable = new double[size][3];
		  
		  // comparaison avec chaque carte de la base de donnŽe	
			for (int i=0 ; i < size ; i++)
			{
				matchTable[i] = compare(database, i);
			}
			
		  // dŽtermination du meilleur match	
		    double[] max = new double[3];
		    double maxDistance = 0;
		    int imax = 0;
		    
		    for (int i=0 ; i < 5 ; i++)
		    {
		    	double val = 0;
		    	for(int compt = 0; compt <3; compt++){
		    		val += Math.pow(max[compt]-matchTable[i][compt], 2);
		    	}
				if (maxDistance<val) 
				{
					imax = i; max = matchTable[i]; maxDistance = val;
				}
			}
			return database.getCard(imax).getName();
		}
		
		public double[] compare(CardDatabase database, int p ){
			
			double[] rep,pixel =new double[3];
			int[] rgbByte;
			
	
			for (int j =0; j<height; j++)
			{
				for (int i=0; i<width; i++)
				{
					rgbByte = getRgbByte(i,j);
					pixel = new double[3];
					for(int k =0; k<3; k++){
						pixel[k] = (rgbByte[k]-average[k])/sigma[k];
						rep[k] = rep[k] + pixel[k]*voisin(database.getCard(p),i,j,pixel) ;
					}
				}
			}	
			//System.out.println(rep);
			return rep;
			
		}

		public double neighbour(int i, int j, int nbr, double[] pixel){ // retourne le pixel voisin de (i,j)
			
			double distance = 0 , distanceMin = Integer.MAX_VALUE;
			int imin = 0, jmin=0;
			for(int p = Math.max(0, j-nbr); p <= Math.min(height-1, j+nbr); p++){
				for(int n = Math.max(0, i-nbr); n <= Math.min(width-1,  i+nbr); n++){
					
					int[] rgbByte = card.getRgbByte(n,p);
					
					for(int compt = 0; compt<3; compt++){
						
						distance = Math.abs((card.get-card.getAverage(i))/baseDonneesCartes.getSigma(i) - pixel);
					
						if (distance < distanceMin){
							kmin = n;
							jmin =p;
					}
				}
			}
			//prendre garde ˆ rajouter kmin et jmin pour grdes images
			}
			return (baseDonneesCartes.getValue(i,k,j)-baseDonneesCartes.getAverage(i))/baseDonneesCartes.getSigma(i) ;
		}
		public int[] neighbour(int i, int j, int[] pixel, int nbr){ // retourne le pixel voisin de (i,j)
			
			int[] res = new int[3];
			int distance = 0 , distanceMin = Integer.MAX_VALUE;
			for(int n = Math.max(0, i-nbr); n <= Math.min(width-1, i+nbr); n++){
				for(int p = Math.max(0, j-nbr); p <= Math.min(height-1,  j+nbr); p++){
					int[] rgbByte = getRgbByte(n, p);
					distance = 0;
					for (int k = 0; k < 3; k ++){
						distance = distance + Math.abs(rgbByte[k] - pixel[k]);
					}
					if (distance < distanceMin){
						distanceMin = distance;
						res = rgbByte;
					}
				}
			}
			
			return res;
		}
		
		
	

}
