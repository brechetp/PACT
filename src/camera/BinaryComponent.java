package camera;

public class BinaryComponent extends BinaryImage{

	private int compt = 0; // compte le nombre de pixels de la composante
	private EdgeImage edgeImage ;

	public BinaryComponent(int[][] matrix, int compt){

		super(matrix);
		edgeImage = this.getEdge();
		this.compt = compt;
	}

	public int getCompt(){

		return compt;
	}

	public int[][] getCornersRansac(int type, double coeff){

		int[][] corners = edgeImage.ransac(type, coeff);
		int[][] res = new int[][]{{0,(int)coeff*height},{0,0},{(int)coeff*width,0},{0,0}};
		for(int i =0; i < 4; i++){

			if (corners[i][0] < res[2][0]) // xMin
				res[2] = corners[i];
			if (corners[i][1] < res[0][1]) // yMin
				res[0] = corners[i];
			if (corners[i][0] > res[1][0]) // xMax
				res[1] = corners[i];
			if (corners[i][1] > res[3][1]) // yMax
				res[3] = corners[i];

		}

		// si (ymin-y)^2 + (xmin - x)^2 < ymin ... + xmax...
		if((Math.pow(res[2][1]-res[0][1], 2)+Math.pow(res[2][0]-res[0][0], 2)) < (Math.pow(res[0][1]-res[1][1], 2)+Math.pow(res[0][0]-res[1][0  ], 2))){
			int[] yMin = res[0], xMax = res[1], xMin = res[2], yMax = res[3];
			res[0] = xMin;
			res[1] = yMin;
			res[2] = yMax;
			res[3] = xMax;


		}


		return res;

	}

	public int[][] getCorners() { // renvoie les coins d'une carte binaire

		int[][] res = new int[][]{{0,height},{0,0},{width,0},{0,0}};


		for(int i = 0 ; i<width; i++){
			for (int j = 0; j< height; j++){

				if (binaryMatrix[j][i] == 1)
				{
					if (i < res[2][0]) // xMin
						res[2] = new int[]{i,j};
					if (j < res[0][1]) // yMin
						res[0] = new int[]{i,j};
					if (i > res[1][0]) // xMax
						res[1] = new int[]{i,j};
					if (j > res[3][1]) // yMax
						res[3] = new int[]{i,j};

				}
			}
		}
		// si (ymin-y)^2 + (xmin - x)^2 < ymin ... + xmax...
		if((Math.pow(res[2][1]-res[0][1], 2)+Math.pow(res[2][0]-res[0][0], 2)) < (Math.pow(res[0][1]-res[1][1], 2)+Math.pow(res[0][0]-res[1][0  ], 2))){
			int[] yMin = res[0], xMax = res[1], xMin = res[2], yMax = res[3];
			res[0] = xMin;
			res[1] = yMin;
			res[2] = yMax;
			res[3] = xMax;


		}



		return res;
	}




}
