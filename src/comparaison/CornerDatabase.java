package comparaison;

import camera.Card;
import camera.Image;


public class CornerDatabase {
	
	private double[] tab1;
	private double[] tab0;
	private double[] averageTab;
	private double[] sigmaTab;
	private int size;
	private int perimeter;
	
	public CornerDatabase (){
		
		size = 2;
		averageTab = new double[size];
		sigmaTab = new double[size];
		perimeter = 2000;
		
		tab0 = new double[perimeter];
		tab1 = new double[perimeter];
		tab0 = carreau.getSignature();
		tab1 = coeur.getSignature();
		
		// Calcul de average et sigma 
		averageTab[0] = 0; averageTab[1]=0; double variance0 = 0; double variance1 = 0;
					for (int i =0; i<perimeter; i++){	
							averageTab[0] += tab0[i];
							averageTab[1] += tab1[i];
					}	
					averageTab[0] = averageTab[0]/(perimeter);	
					averageTab[1] = averageTab[1]/(perimeter);	
					
					for (int i =0; i<perimeter; i++){
							variance1 += Math.pow(tab1[i]-averageTab[1],2);
							variance0 += Math.pow(tab0[i]-averageTab[0],2);
						}
					sigmaTab[1] = Math.sqrt(variance1/(perimeter)); //sigma=1; average = 0;
					sigmaTab[0] = Math.sqrt(variance0/(perimeter));
	}
		

		
		
		

	
	


	public double getSign(int i, int k){
		
		if (i==0){
			return tab0[k];
		}
		else {
			return tab1[k];	
		}
	}
 
	public double getSignAverage(int i){
		return averageTab[i];
	}
	
   
	public double getSignSigma(int i){
		return sigmaTab[i];
		
	}


	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	
	
	
}