package comparaison;

import camera.Image;


public class BaseDonneesCartes {
	
	private Image[] tab;
	private double[][] averageTab;
	private double[][] sigmaTab;
	private int width;
	private int height;
	
	public BaseDonneesCartes (int debut, int fin, String source){
		
		int size = fin-debut+1;
		double[] average, variance;
		averageTab = new double[size][3];
		sigmaTab = new double[size][3];
		
		tab = new Image[size];
		
		for(int k = 0; k<size; k++){
			
			tab[k] = new Image(source+(k+1)+".jpg");
			average = new double[3];
			variance = new double[3];
			width = tab[k].getWidth();
			height = tab[k].getHeight();
			for (int i =0; i<width; i++)
			{
				for (int j=0; j<height; j++)
				{
					for (int p =0; p<3; p++){
						average[p] += tab[k].getRgbByte(i, j)[p];
						variance[p] += Math.pow(tab[k].getRgbByte(i, j)[p]-average[p],2);
					}
					
				}
			}	
			
			for(int p =0; p<3; p++){
				averageTab[k][p] = average[p]/(width*height);
				sigmaTab[k][p] = Math.sqrt(variance[p]/(width*height));
			}
		}
		 
			
		
		
	}

	public int[] getValue(int k, int i, int j){
	return tab[i].getRgbByte(i, j);
	}
 
	public double[] getAverage(int i){
		return averageTab[i];
	}
	
   
	public double[] getSigma(int i){
		return sigmaTab[i];
		
	}
	
}