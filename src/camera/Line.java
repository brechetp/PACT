package camera;

public class Line {
	
	private double[] vector;
	private double[] equation;
	
	public Line(double[] pointA, double[] pointB){
		
		vector = new double[2];
		equation = new double[3];
		vector[0] = pointB[0] - pointA[0];
		vector[1] = pointB[1] - pointA[1];
		equation[0] = pointB[1] - pointA[1];
		equation[1] = pointA[0]- pointB[0];
		equation[2] = pointA[1] * (pointB[0] - pointA[0]) - pointA[0] * (pointB[1] - pointA[1]);
		
	}
	
	public boolean isIn(double[] point, double threshold){
		
		return Math.abs(equation[0]*point[0] + equation[1]*point[1] + equation[2]) < threshold;
		
	}

}
