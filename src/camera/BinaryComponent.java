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

	public int[][] getCorners(){

		int[][] corners = edgeImage.ransac();
		int[][] res = new int[][]{{0,height},{0,0},{width,0},{0,0}};
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
		if((Math.pow(res[3][1]-res[3][0], 2)+Math.pow(res[1][0]-res[1][1], 2)) < (Math.pow(res[3][1]-res[3][0], 2)+Math.pow(res[2][0]-res[2][1], 2))){
			int[] yMin = res[0], xMax = res[1], xMin = res[2], yMax = res[3];
			res[0] = xMin;
			res[1] = yMin;
			res[2] = yMax;
			res[3] = xMax;


		}


		return res;

	}

}
