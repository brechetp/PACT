package leapmotion.main;

import java.io.Serializable;

public class GesteIndex implements Serializable{

	private static final long serialVersionUID = -5624587783379380181L;
	private FramesGestes framesGestes;
	private int x;
	
	public GesteIndex(FramesGestes framesGestes, int x){
		this.framesGestes = framesGestes;
		this.x = x;
	}
	
	public void printInfo(){
		System.out.println(x + " " + framesGestes.getHandPosition() + " "  );
	}

}
