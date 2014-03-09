package camera;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import comparaison.CardDatabase;


public class Card extends Image{
	
	

	private static final double[] yellow = new double[]{150,200,200};
	private static final double[] averageYellow = new double[]{163.4125, 247.32, 248.835};
	private static final double[] sigmaYellow = new double[]{8.154896918416563, 3.0153938382904544, 2.786175694388279};
	// double [] matchTable = new double [5];




	
	

	
	public Card(IplImage image) throws Exception{
		
		super(image);
		average = new double[3]; // moyenne sur RGB
		sigma = new double[3]; // ecart type sur RGB
	

		corner = this.cut(0,0,100, 250);


		
		computeAverage();
		computeSigma();
		
	}
	
	public Card(String fileName) throws Exception{
		
		super(fileName);
		name = fileName;
		average = new double[3]; // moyenne sur RGB
		sigma = new double[3]; // ecart type sur RGB
		

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
	public Image getCorner(){
		
		return corner;
	}
	
	


	
	
	
	public String findIn(CardDatabase database){
		
		  int size = database.getSize();
		  double[][] matchTable = new double[size][3];
		  
		  // comparaison avec chaque carte de la base de donnée	
			for (int i=0 ; i < size ; i++)
			{
				matchTable[i] = corner.compare(database.getCard(i).getCorner(), 3);
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
