package patternmatching;

public class BinaryImage2 {

	private int[][] frame; // image binaire 0=background 1=objet
	private int[] connectionTable = new int[999999];
	public Pixel[][] taggedBinaryImage = null; // image initiale o� les 1 sont
												// remplac�s par des pixels
												// �tiquet�s
	private int size1 = 166;
	private int size2 = 169;
	private int maxNbTags;

	public BinaryImage2 (int [][] matrice){
		this.frame = matrice ;
		this.maxNbTags = 999999;
		
	}
	
	// algorithme de double passage
	public int[][] conncetedComponents() {

		taggedBinaryImage = new Pixel[size2][size1];
		// construction des Pixel
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size1; j++) {
				taggedBinaryImage[i][j] = new Pixel(i, j, this);
				
			}
		}

		int k = 1;

		// initialisation de la table de correspondance
		for (int i = 1; i < maxNbTags; i++) {
			connectionTable[i] = i;
		}

		// Premier passage
		for (int i = 1; i < size2; i++) {
			for (int j = 1; j < size1; j++) {
				if (frame[i][j] == 1)
				{
					if (frame[i-1][j] == 0 && frame[i][j-1] == 0) 
					{
						taggedBinaryImage[i][j].setTag(k);
						k++;
					} 
					else 
					{
						if (taggedBinaryImage[i][j].getNorthNeighbor().getTag() == taggedBinaryImage[i][j].getWestNeighbor().getTag() && frame[i-1][j]==1) 
						{
							taggedBinaryImage[i][j].setTag(taggedBinaryImage[i][j].getNorthNeighbor().getTag());
						} 
						else 
						{

							int e1 = Math.min(taggedBinaryImage[i][j].getNorthNeighbor().getTag(), taggedBinaryImage[i][j].getWestNeighbor().getTag());
							if (e1 == 0) 
							{
								e1 = Math.max(taggedBinaryImage[i][j].getNorthNeighbor().getTag(), taggedBinaryImage[i][j].getWestNeighbor().getTag());
							}
							taggedBinaryImage[i][j].setTag(connectionTable[e1]);
							
							// mise � jour de la table de la table de correspondance
							int a = taggedBinaryImage[i][j].getNorthNeighbor().getTag();
							if (connectionTable[a] != connectionTable[e1]) 
							{
								while (connectionTable[a] != a) 
								{
									int l = connectionTable[a];
									connectionTable[a] = connectionTable[e1];
									a = l;
								}
							}
							
							a = taggedBinaryImage[i][j].getWestNeighbor().getTag();
							if (connectionTable[a] != connectionTable[e1]) 
							{
								while (connectionTable[a] != a) 
								{
									int l = connectionTable[a];
								connectionTable[a] = connectionTable[e1];
								a = l;
								}
							}
						}
					}
				}
			}
		}

		// actualisation de T
		for (int m = 1; m < maxNbTags; m++) {
			int n = m;
			while (connectionTable[n] != n) {
				n = connectionTable[n];
				connectionTable[m] = n;
			}
		}

		// second balayage
		for (int i1 = 1; i1 < size2; i1++) {
			for (int j = 1; j < size1; j++) {
				if (taggedBinaryImage[i1][j].getTag() != 0) {
					taggedBinaryImage[i1][j].
					setTag(connectionTable[taggedBinaryImage[i1][j]
							.getTag()]);
				}
			}
		}
		
		// passage de pixel � image binaire
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size1; j++) {
				frame[i][j] = taggedBinaryImage[i][j].getTag() ;
			}
		}

		return frame; 
		
	}

	public int [][] largestComponent(){
		conncetedComponents();
		int[][] largest = new int[size2][size1];
		int[] compteur = new int[maxNbTags];
		for (int i=0; i<maxNbTags; i++){
			compteur[i]=0;
		}
		
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size1; j++) {
				if (frame[i][j] !=0) {
					compteur[frame[i][j]] +=1 ;
				}
			}
		}
		
		int max = 0 ; 
		int imax = 0;
		for (int i=0; i<maxNbTags; i++){
			if (compteur[i] >= max){
				max = compteur[i];
				imax = i;
			}
		}
		
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size1; j++) {
				if (frame[i][j] != imax) {
					largest[i][j] =0 ;
				} else {
					largest[i][j]=1;
				}
			}
		}	
		
		return largest;
	}
	

	public int getFrameValue(int x, int y) {
		
		return frame[x][y];
	}
	

}
