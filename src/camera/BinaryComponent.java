package camera;

public class BinaryComponent extends BinaryImage{
	
	private int compt = 0;
	
	public BinaryComponent(int[][] matrix, int compt){
		
		super(matrix);
		this.compt = compt;
	}
	
	public int getCompt(){
		
		return compt;
	}
	
	public int[][] getCorners() throws Exception{
		
		if (compt < 100){
			throw new Exception();
		}
		return super.getCorners();
		
	}

}
