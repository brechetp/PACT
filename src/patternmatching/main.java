package patternmatching;

public class main {
	public static void main(String[] args) {
		
		int [][] matrice = new int [1000][1000];
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[0].length; j++) {
				matrice [i][j] =0;
			}
		}
		matrice[3][3]=1;
		matrice[3][4]=1;
		
		matrice[5][5]=1;
		BinaryImage bi = new BinaryImage(matrice); 
		int rep = bi.conncetedComponents();
		
		/*for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[0].length; j++) {
				System.out.print(bi.getFrameValue(i, j)); 
			}
			System.out.println();
		}*/
		System.out.print("lol");
		
	}
}

	
