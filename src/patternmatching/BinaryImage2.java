package patternmatching;

public class BinaryImage2 {

	private int[][] frame; // image binaire 0=background 1=objet
	private int[] connectionTable = new int[10000];
	public int[][] taggedBinaryImage; // image initiale o� les 1 sont
												// remplac�s par des pixels
												// �tiquet�s
	private int size1 = 1920;
	private int size2 = 1080;
	private int maxNbTags;

	public BinaryImage2 (int [][] matrice){
		this.frame = matrice ;
		this.maxNbTags = 10000;
		this.taggedBinaryImage = new int[size2][size1];
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size1; j++) {
				taggedBinaryImage[i][j] = 0;
				
			}
		}
		
	}
	
	
	// algorithme de double passage
	public int[][] conncetedComponents() {


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
						taggedBinaryImage[i][j]=k;
						k++;
					} 
					else 
					{
						if (taggedBinaryImage[i-1][j] == taggedBinaryImage[i][j-1] && frame[i-1][j]==1) 
						{
							taggedBinaryImage[i][j] = taggedBinaryImage[i-11][j];
						} 
						else 
						{

							int e1 = Math.min(taggedBinaryImage[i-1][j], taggedBinaryImage[i][j-1]);
							if (e1 == 0) 
							{
								e1 = Math.max(taggedBinaryImage[i-1][j], taggedBinaryImage[i][j-1]);
							}
							taggedBinaryImage[i][j] = connectionTable[e1];
							
							// mise � jour de la table de la table de correspondance
							int a = taggedBinaryImage[i-1][j];
							if (connectionTable[a] != connectionTable[e1]) 
							{
								while (connectionTable[a] != a) 
								{
									int l = connectionTable[a];
									connectionTable[a] = connectionTable[e1];
									a = l;
								}
							}
							
							a = taggedBinaryImage[i][j-1];
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
				if (taggedBinaryImage[i1][j] != 0) {
					taggedBinaryImage[i1][j] = connectionTable[taggedBinaryImage[i1][j]];
				}
			}
		}
		
		return taggedBinaryImage; 
		
	}

	public int [][] largestComponent(){
		int [][] tab = conncetedComponents();
		int[][] largest = new int[size2][size1];
		int[] compteur = new int[maxNbTags];
		for (int i=0; i<maxNbTags; i++){
			compteur[i]=0;
		}
		
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size1; j++) {
				if (tab[i][j] !=0) {
					compteur[tab[i][j]] +=1 ;
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
	
}