package camera;

import java.util.ArrayList;

public class Contour extends BinaryImage{
	
	private ArrayList<int[]> pointsList = new ArrayList<int[]>();
	
	public Contour(int[][] matrix, ArrayList<int[]> pointsList){
		
		super(matrix);
		this.pointsList = pointsList;
		
	}

}
