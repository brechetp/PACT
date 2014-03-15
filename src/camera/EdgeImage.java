package camera;

import java.util.ArrayList;

public class EdgeImage extends BinaryImage {
	
	private static final double lineThreshold = 0.1; // distance ˆ laquelle un point est dans une droite
	private static final int NBR_RANSAC = 100000;
	private ArrayList<int[]> pointsList = new ArrayList<int[]>();
	
	public EdgeImage(int[][] matrix, ArrayList<int[]> pointsList){
		
		super(matrix);
		this.pointsList = pointsList;
		
	}
	
	public int[][] ransac(){ //donne les coins du contour
		
		int max = 0;
		int[][] res = new int[4][2];
		int size = pointsList.size();
		for(int i=0; i< NBR_RANSAC; i++){
			
			int[][] points = new int[3][2];
			for (int j =0; j<3; j++){
				int random =(int) Math.floor(Math.random()*size);
				points[j] = pointsList.get(random); // trois points au hasard parmi le contour
			}
			int compt = 0; // compte le nombre de votes pour ces trois points
			
			double[][] coins = coins(points);
			
			Line line1 = new Line(coins[0], coins[2]), line2 = new Line(coins[0], coins[1]), line3 = new Line(coins[1], coins[3]), line4 = new Line(coins[2], coins[3]);
			for(int[] point : pointsList){ // pour tous les points du contour
				if (line1.isIn(point, lineThreshold) || line2.isIn(point, lineThreshold)
						|| line3.isIn(point, lineThreshold) || line4.isIn(point, lineThreshold))
					compt++;
			
				
			}
			if (compt > max){
				
				max = compt;
				for (int p =0; p<4; p++){
					res[p][0] =(int)  Math.round(coins[p][0]);
					res[p][1] =(int)  Math.round(coins[p][1]);
				}
			}
		}
		return res;
	}
	
	private double[][] coins(int[][] points){ // renvoie les quatres coins de la carte
		
		double[][] coins = new double[4][2];
		
		double x0 = points[0][0], y0 = points[0][1], x1 = points[1][0], y1 = points[1][1], x2 = points[2][0], y2 = points[2][1];
		double alpha = x1-x0, beta = y1-y0, gamma = -beta, delta = alpha;
		double norm = Math.sqrt(Math.pow(alpha, 2) + Math.pow(beta, 2));
		double squareNorm = Math.sqrt(Math.pow(alpha, 2) + Math.pow(beta, 2));
		
		coins[0][0] = x1 + (((x2-x1)*(alpha) + (y2-y1)*beta)/(squareNorm))*alpha;
		coins[0][1] = y1 + (((x2-x1)*(alpha) + (y2-y1)*beta)/(squareNorm))*beta;
		// coordonnes de H projete de 2 sur la droite 01
		
		coins[1][0] = coins[0][0] + Card.WIDTH*(gamma)/norm;
		coins[1][1] = coins[0][1] + Card.WIDTH*(delta)/norm;
		
		coins[2][0] = coins[0][0] + Card.HEIGHT*(alpha)/norm;
		coins[2][1] = coins[0][1] + Card.HEIGHT*(beta)/norm;
		
		coins[3][0] = coins[1][0] + Card.HEIGHT*(alpha)/norm;
		coins[3][1] = coins[1][0] + Card.HEIGHT*(beta)/norm;
		
		
		return coins;
		
	}
}

	

