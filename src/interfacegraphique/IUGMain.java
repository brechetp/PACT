package interfacegraphique;

import java.awt.*;
import javax.swing.*;

public class IUGMain {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = environment.getDefaultScreenDevice();
		device.setFullScreenWindow(frame);
		
	}

}
