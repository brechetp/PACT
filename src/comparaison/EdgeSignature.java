package comparaison;

public class EdgeSignature {
	
	private int[][] doubleTab; // initialisée avec une image à une composante connexe
	private int size1;
	private int size2;
	private int perimeter;
	private int[][] flagTab;
	private double[] signature;
	
	public EdgeSignature (int[][] binaryMatrix){
		this.size1 = 635;
		this.size2 = 889;
		this.perimeter = 2000;
		
		this.doubleTab = new int[size2][size1];
		this.doubleTab = binaryMatrix;
		
		this.flagTab = new int[size2][size1];
		for (int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				flagTab[i][j]=0;
			}
		}
		
		this.signature = new double[perimeter];
		for (int i =0; i<perimeter; i++)
		{
				signature[i]=0;
		} 
		
	}
	
	public void getEdge(){
		//modifie doubleTab	
	}
	
	public int[] getNext(int x, int y){
		int[] rep = new int[2];
		flagTab[y][x]=1;
		if(doubleTab[y][x+1]==1 && flagTab[y][x+1]==0){
			rep[0]=x+1; rep[1]=y;}
			else{
				if(doubleTab[y-1][x+1]==1 && flagTab[y-1][x+1]==0){
					rep[0]=x+1; rep[1]=y-1;}
					else{
						if(doubleTab[y-1][x]==1 && flagTab[y-1][x]==0){
							rep[0]=x; rep[1]=y-1;}
							else{
								if(doubleTab[y-1][x-1]==1 && flagTab[y-1][x-1]==0){
									rep[0]=x-1; rep[1]=y-1;}
									else{
										if(doubleTab[y][x-1]==1 && flagTab[y][x-1]==0){
											rep[0]=x-1; rep[1]=y;}
											else{
												if(doubleTab[y+1][x-1]==1 && flagTab[y+1][x-1]==0){
													rep[0]=x-1; rep[1]=y+1;}
													else{
														if(doubleTab[y+1][x]==1 && flagTab[y+1][x]==0){
															rep[0]=x; rep[1]=y+1;}
															else{
																if(doubleTab[y+1][x+1]==1 && flagTab[y+1][x+1]==0){
																	rep[0]=x+1; rep[1]=y+1;}
																	
															}
													
													}
												
											}
										
									}
								
							}
			            }
		         
		   }
		
	
		return rep;
	}
	
	public double[] getSignature(){
		this.getEdge();
		
		//détermination du barycentre
		int xb = 0;
		int yb =0;
		for (int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				xb += xb+j*doubleTab[i][j];
				yb += yb+i*doubleTab[i][j];
			}
		}
		xb=xb/perimeter;
		yb=yb/perimeter;
		
		//détermination du premier pixel à tester
		int x = xb;
		int y = yb;
		while (doubleTab[y][x] == 0){
			y++;
		}
		
		//remplissage de la table
		for (int s=0 ; s<perimeter ; s++){
		 signature[s]=Math.sqrt( Math.pow(x-xb,2) + Math.pow(y-yb,2) );
		 x = getNext(x,y)[0];
		 y = getNext(x,y)[1];
		}
				
		return signature;
	}
	
	
	
	public int compareSignature(SignatureBase signatureBase){

		return 0;
	}
	
	
}


