package patternmatching;

public class Pixel {
	
	private final int x;
	private final int y;
	private int tag;
	private final BinaryImage binaryImage;
	private Pixel northneighbor ; 
	private Pixel westneighbor;
	
	
	public Pixel (int x, int y, BinaryImage binaryImage) {
		this.binaryImage = binaryImage;
		this.x = x;
		this.y = y;
		this.tag = this.binaryImage.getFrameValue(x, y);
		
		  if (y>0){
			northneighbor = this.binaryImage.taggedBinaryImage[x][y-1];
		  } 
		  if (x>0){
			westneighbor = this.binaryImage.taggedBinaryImage[x-1][y];
		  } 
		  
	}

	
	public void setTag (int tag){
		this.tag = tag;
	}
	
	public int getTag () {
		return tag;
	}
	
	public int getRow() {
		return y;
	}
	
	public int getColumn() {
		return x;
	}
	
	public Pixel getNorthNeighbor() {
		return northneighbor;
	}
	
	public Pixel getWestNeighbor() {
		return westneighbor;
	}
	

}

