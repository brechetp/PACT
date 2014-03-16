package camera;

public class Line {
	
	private double[] vector;
	private double[] equation;
	private double[][] extremities;
	
	public Line(double[] pointA, double[] pointB){
		
		extremities = new double[][]{pointA, pointB};
		vector = new double[2];
		equation = new double[3];
		vector[0] = pointB[0] - pointA[0];
		vector[1] = pointB[1] - pointA[1];
		equation[0] = pointB[1] - pointA[1];
		equation[1] = pointA[0]- pointB[0];
		equation[2] = pointA[1] * (pointB[0] - pointA[0]) - pointA[0] * (pointB[1] - pointA[1]);
		
	}
	
	public boolean isIn(int[] point, double threshold){ //  teste si le point est dans le semgment [AB]
		
		return 
			(Math.abs(equation[0]*point[0] + equation[1]*point[1] + equation[2])/(Math.sqrt(Math.pow(equation[0], 2)+Math.pow(equation[1], 2))) <= threshold
					&& point[0] >= Math.min(extremities[0][0], extremities[1][0])
					&& point[0] <= Math.max(extremities[0][0], extremities[1][0])
					&& point[1] >= Math.min(extremities[0][1], extremities[1][1])
					&& point[1] <= Math.max(extremities[0][1], extremities[1][1]));
		
	}

}
