package camera;

import java.util.ArrayList;

public class EdgeImage extends BinaryImage {

	private static final double lineThreshold = 2; // distance � laquelle un point est dans une droite



	private ArrayList<int[]> pointsList = new ArrayList<int[]>();

	public EdgeImage(int[][] matrix, ArrayList<int[]> pointsList){

		super(matrix);
		this.pointsList = pointsList;

	}

	public int[][] ransac( int type){ //donne les coins du contour

		int max = 0;
		int[][] res = new int[4][2];
		int size = pointsList.size();
		int i = 0;

		if (type ==3){
			while (max < ((double)5/3)*(Card.WIDTH + Card.HEIGHT) && i < 3*size){
				i++;

				int[][] points = new int[3][2];
				double corners[][] = new double[4][2];

				do{
					for(int j =0; j<3; j++){
						int random =(int) Math.floor(Math.random()*size);
						points[j] = pointsList.get(random); // trois points au hasard parmi le contour
					}
					corners[0]= projection(points[0], points[1], points[2]); // on recupere H
				} while(!isNotInBetween(points[0], points[1], corners[0]));


				for(int k=0; k<2; k++){// k est le type du masque

					corners = corners3(points, k);

					if (isPossible(corners)){

						int compt = 0; // compte le nombre de votes pour ces trois points
						Line line1 = new Line(corners[0], corners[2]), line2 = new Line(corners[0], corners[1]), line3 = new Line(corners[1], corners[3]), line4 = new Line(corners[2], corners[3]);
						for(int[] point : pointsList){ // pour tous les points du contour

							if (line1.isIn(point, lineThreshold) || line2.isIn(point, lineThreshold)
									|| line3.isIn(point, lineThreshold) || line4.isIn(point, lineThreshold))
								compt++;


						}
						if (compt > max){

							max = compt;
							for (int p =0; p<4; p++){
								res[p][0] =(int)  Math.round(corners[p][0]);
								res[p][1] =(int)  Math.round(corners[p][1]);
							}
						}
					}

				}
			}
		}
		else{ //type==5
			while ( i < 10*size){
				i++;
				int[][] points = new int[5][2];
				double corners[][] = new double[4][2];


				for(int j =0; j<5; j++){
					int random =(int) Math.floor(Math.random()*size);
					points[j] = pointsList.get(random); // trois points au hasard parmi le contour
				}


				corners = corners5(points);

				if (isPossible(corners)){

					int compt = 0; // compte le nombre de votes pour ces trois points
					Line line1 = new Line(corners[0], corners[2]), line2 = new Line(corners[0], corners[1]), line3 = new Line(corners[1], corners[3]), line4 = new Line(corners[2], corners[3]);
					for(int[] point : pointsList){ // pour tous les points du contour

						if (line1.isIn(point, lineThreshold) || line2.isIn(point, lineThreshold)
								|| line3.isIn(point, lineThreshold) || line4.isIn(point, lineThreshold))
							compt++;


					}
					if (compt > max){

						max = compt;
						for (int p =0; p<4; p++){
							res[p][0] =(int)  Math.round(corners[p][0]);
							res[p][1] =(int)  Math.round(corners[p][1]);
						}
					}
				}

			}

		}
		return res;
	}

	private double[][] corners3(int[][] points, int type){ // renvoie les quatres coins de la carte

		// type = 1 pour la longueur, pour la largeur
		double[][] coins = new double[4][2];

		double x0 = points[0][0], y0 = points[0][1], x1 = points[1][0], y1 = points[1][1], x2 = points[2][0], y2 = points[2][1];
		double alpha = x1-x0, beta = y1-y0;

		double squareNorm = (Math.pow(alpha, 2) + Math.pow(beta, 2));

		coins[0][0] = x1 + (((x2-x1)*(alpha) + (y2-y1)*beta)/(squareNorm))*alpha;
		coins[0][1] = y1 + (((x2-x1)*(alpha) + (y2-y1)*beta)/(squareNorm))*beta;
		// coordonnes de H projete de 2 sur la droite 01

		alpha = x0 - coins[0][0];
		beta = y0 - coins[0][1];

		double gamma = (x2 - coins[0][0]), delta = y2-coins[0][1];
		double dNorm = Math.sqrt(Math.pow(alpha, 2) + Math.pow(beta, 2)),
				nNorm = Math.sqrt(Math.pow(gamma, 2) + Math.pow(delta, 2));



		coins[1][0] = coins[0][0] + (Card.WIDTH*type + Card.HEIGHT*(1-type))*(gamma)/nNorm;
		coins[1][1] = coins[0][1] + (Card.WIDTH*type + Card.HEIGHT*(1-type))*(delta)/nNorm;

		coins[2][0] = coins[0][0] + (Card.HEIGHT*type + Card.WIDTH*(1-type))*(alpha)/dNorm;
		coins[2][1] = coins[0][1] + (Card.HEIGHT*type + Card.WIDTH*(1-type))*(beta)/dNorm;

		coins[3][0] = coins[1][0] + (Card.HEIGHT*type + Card.WIDTH*(1-type))*(alpha)/dNorm;
		coins[3][1] = coins[1][1] + (Card.HEIGHT*type + Card.WIDTH*(1-type))*(beta)/dNorm;



		return coins;

	}

	private boolean isNotInBetween(int[] A, int[] B, double[] M){

		return M[0] > Math.max(A[0], B[0]) || M[0] < Math.min(A[0], B[0]) 
				|| M[1] > Math.max(A[1], B[1]) || M[1] < Math.min(A[1], B[1]) ;

	}

	private boolean isPossible(double[][] corners){

		boolean test = true;
		int i =0, j =0;
		while(test && i <4){
			j=0;
			while(j<2){
				test = test && 0 <= corners[i][j] && corners[i][j] <= (width*(1-j)+height*j);
				j++;
			}
			i++;
		}

		return test;

	}




	private double[][] corners5(int[][] points){ // renvoie les quatres coins de la carte

		// type = 1 pour la longueur, pour la largeur
		double[][] corners = new double[4][2];

		double x0 = points[0][0], y0 = points[0][1];
		double alpha, beta;


		corners[0]= projection(points[0], points[1], points[2]);// on recupere H
		corners[2] = projection(points[0], points[1], points[3]);
		// coordonnes de H projete de 2 sur la droite 01

		alpha = x0 - corners[0][0];
		beta = y0 - corners[0][1];

		double[] vector = new double[]{alpha, beta};

		corners[1] = projection(points[4], vector, points[2]);
		corners[3] = projection(points[4], vector, points[3]);



		return corners;

	}

	/*
	 * Projections orthogonales
	 * 
	 * 
	 */

	private double[] projection(int[] pointA, double[] vector, int[] pointC){

		double[] projection= new double[2];
		double x0 = pointA[0], y0 = pointA[1], x2 = pointC[0], y2 = pointC[1];
		double alpha = vector[0], beta = vector[1];

		double squareNorm = (Math.pow(alpha, 2) + Math.pow(beta, 2));

		projection[0] = x0 + (((x2-x0)*(alpha) + (y2-y0)*beta)/(squareNorm))*alpha;
		projection[1] = y0 + (((x2-x0)*(alpha) + (y2-y0)*beta)/(squareNorm))*beta;

		return projection;
	}

	private double[] projection(int[] pointA, int[] pointB, int[] pointC){

		double[] projection= new double[2];
		double x0 = pointA[0], y0 = pointA[1], x1 = pointB[0], y1 = pointB[1], x2 = pointC[0], y2 = pointC[1];
		double alpha = x1-x0, beta = y1-y0;

		double squareNorm = (Math.pow(alpha, 2) + Math.pow(beta, 2));

		projection[0] = x1 + (((x2-x1)*(alpha) + (y2-y1)*beta)/(squareNorm))*alpha;
		projection[1] = y1 + (((x2-x1)*(alpha) + (y2-y1)*beta)/(squareNorm))*beta;

		return projection;
	}


	/*private double[][] widthCorners(int[][] points){ // renvoie les quatres coins de la carte

		double[][] coins = new double[4][2];

		double x0 = points[0][0], y0 = points[0][1], x1 = points[1][0], y1 = points[1][1], x2 = points[2][0], y2 = points[2][1];
		double alpha = x1-x0, beta = y1-y0, gamma = -beta, delta = alpha;
		double norm = Math.sqrt(Math.pow(alpha, 2) + Math.pow(beta, 2));
		double squareNorm = (Math.pow(alpha, 2) + Math.pow(beta, 2));

		coins[2][0] = x1 + (((x2-x1)*(alpha) + (y2-y1)*beta)/(squareNorm))*alpha;
		coins[2][1] = y1 + (((x2-x1)*(alpha) + (y2-y1)*beta)/(squareNorm))*beta;
		// coordonnes de H projete de 2 sur la droite 01

		coins[0][0] = coins[2][0] + Card.HEIGHT*(gamma)/norm;
		coins[0][1] = coins[2][1] + Card.HEIGHT*(delta)/norm;

		coins[2][0] = coins[0][0] + Card.WIDTH*(alpha)/norm;
		coins[2][1] = coins[0][1] + Card.WIDTH*(beta)/norm;

		coins[1][0] = coins[0][0] + Card.WIDTH*(alpha)/norm;
		coins[1][1] = coins[0][0] + Card.WIDTH*(beta)/norm;


		return coins;

	}*/
}



