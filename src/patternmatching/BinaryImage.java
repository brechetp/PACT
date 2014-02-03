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

	// algorithme de double passage
	public int[][] connectedComponents() {

		int k = 2;

		// initialisation de la table de correspondance
		for (int i = 2; i < maxNbTags; i++) {
			connectionTable[i] = i;
		}

		// Premier passage
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (taggedBinaryImage[i][j].getNorthNeighbor().getTag() == 0
						&& taggedBinaryImage[i][j].getWestNeighbor().getTag() == 0) {
					taggedBinaryImage[i][j].setTag(k);
					k++;
				} else {
					if (taggedBinaryImage[i][j].getNorthNeighbor().getTag() == taggedBinaryImage[i][j]
							.getWestNeighbor().getTag()) {
						taggedBinaryImage[i][j].setTag(taggedBinaryImage[i][j]
								.getNorthNeighbor().getTag());
					} else {
						int e = min(taggedBinaryImage[i][j].getNorthNeighbor()
								.getTag(), taggedBinaryImage[i][j]
								.getWestNeighbor().getTag());
						if (e == 0) {
							e = max(taggedBinaryImage[i][j].getNorthNeighbor()
									.getTag(), taggedBinaryImage[i][j]
									.getWestNeighbor().getTag());
						}
						taggedBinaryImage[i][j].setTag(connectionTable[e]);
						// mise � jour de la table de la table de correspondance
						int a = taggedBinaryImage[i][j].getNorthNeighbor()
								.getTag();
						if (connectionTable[a] != connectionTable[e]) {
							while (connectionTable[a] != a) {
								int l = connectionTable[a];
								connectionTable[a] = connectionTable[e];
								a = l;
							}
						}
						a = taggedBinaryImage[i][j].getWestNeighbor().getTag();
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

		// actualisation de T
		for (int m = 2; m < maxNbTags; m++) {
			int n = m;
			while (connectionTable[n] != n) {
				n = connectionTable[n];
				connectionTable[m] = n;
			}
		}

		// second balayage
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (taggedBinaryImage[i][j].getTag() != 0) {
					taggedBinaryImage[i][j]
							.setTag(connectionTable[taggedBinaryImage[i][j]
									.getTag()]);
				}
			}
		}

		return connectedComponents();

	}

	public int getFrameValue(int x, int y) {
		return frame[x][y];
	}

}
