package camera;

import java.util.ArrayList;

public class EdgeImage extends BinaryImage {
	
	private ArrayList<int[]> pointsList = new ArrayList<int[]>();
	
	public EdgeImage(int[][] matrix, ArrayList<int[]> pointsList){
		
		super(matrix);
		this.pointsList = pointsList;
		
	}
	

}
