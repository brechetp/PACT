package patternmatching;

public class BinaryImage {

	private int[][] frame; // image binaire 0=background 1=objet
	private int[] connectionTable = new int[10000];
	public Pixel[][] taggedBinaryImage = null; // image initiale o� les 1 sont
												// remplac�s par des pixels
												// �tiquet�s
	private int size;
	private int maxNbTags;
	
	public int[][] getFrame(){
		
		return frame;
	}

	public BinaryImage (int [][] matrice){
		this.size = matrice.length;
		this.frame = matrice ;
		this.maxNbTags = 10000;
		
	}
	
	// algorithme de double passage
	public int conncetedComponents() {

		taggedBinaryImage = new Pixel[size][size];
		// construction des Pixel
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				taggedBinaryImage[i][j] = new Pixel(i, j, this);
				
			}
		}

		int k = 1;

		// initialisation de la table de correspondance
		for (int i = 1; i < maxNbTags; i++) {
			connectionTable[i] = i;
		}

		// Premier passage
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
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
		for (int i1 = 1; i1 < size; i1++) {
			for (int j = 1; j < size; j++) {
				if (taggedBinaryImage[i1][j].getTag() != 0) {
					taggedBinaryImage[i1][j].
					setTag(connectionTable[taggedBinaryImage[i1][j]
							.getTag()]);
				}
			}
		}
		
		// passage de pixel � image binaire
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				frame[i][j] = taggedBinaryImage[i][j].getTag() ;
			}
		}

		return 0; 
		
	}

	

	public int getFrameValue(int x, int y) {
		return frame[x][y];
	}
	
	public int getSize(){
		return size;
	}
	
	public int[][] getLargestComponent(){
		
		
		
		return frame;
		
		
	}

}
