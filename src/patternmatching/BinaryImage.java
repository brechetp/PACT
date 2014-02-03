package patternmatching;

import java.util.ArrayList;

public class BinaryImage {

	private int[][] frame = null; // image binaire 0=background 1=objet
	private int[] connectionTable = null; // tableau des correspondances pour la
											// detection des composantes
											// connexes
	private int[][] taggedBinaryImage = null; // image avec chaque pixel (case
												// du tableau) etiquete par un
												// entier
	private int size;
	private int maxNbTags;

	public int getFrameValue(int x, int y) {
		return frame[x][y];
	}

	// algorithme de double passage
	public void connectedComponents() {

		int k = 1;

		// initialisation de la table de correspondance
		for (int i = 1; i < maxNbTags; i++) {
			connectionTable[i] = i;
		}

		// Premier passage
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// si les voisins de gauche et du haut ne sont pas etiquetes
				if (taggedBinaryImage[i - 1][j] == 0
						&& taggedBinaryImage[i][j - 1] == 0) {

					taggedBinaryImage[i][j] = k;
					k++;
				} else {
					// si les voisins gauche et haut ont la meme etiquete
					if (taggedBinaryImage[i - 1][j] == taggedBinaryImage[i][j - 1]) {

						taggedBinaryImage[i][j] = taggedBinaryImage[i - 1][j];
					} else {
						int e = Math.min(taggedBinaryImage[i - 1][j],
								taggedBinaryImage[i][j - 1]);
						// si l'un des deux voisins n'est pas etiquete, on prend
						// l'etiquete max (celle qui est > 0)
						if (e == 0) {
							e = Math.max(taggedBinaryImage[i - 1][j],
									taggedBinaryImage[i][j - 1]);
						}
						taggedBinaryImage[i][j] = e;
						// mise a jour de la table de la table de correspondance
						if (taggedBinaryImage[i - 1][j] > 0
								&& taggedBinaryImage[i][j - 1] > 0) {
							int a = Math.max(taggedBinaryImage[i - 1][j],
									taggedBinaryImage[i][j - 1]);
							if (connectionTable[a] != connectionTable[e]) {
								while (connectionTable[a] != a) {
									int l = connectionTable[a];
									connectionTable[a] = connectionTable[e];
									a = l;
								}
							}
						}
					}
				}

			}
		}

		return;

	}

}
