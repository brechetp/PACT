package leapmotion.main;
import java.io.IOException;

import javax.swing.JFrame;

import com.leapmotion.leap.Controller;

public class LeapMotionMain {

	public static void main(String[] args) {
		
		KListener kk = new KListener();
		JFrame fenetre = new JFrame();
		fenetre.addKeyListener(kk);
		fenetre.setVisible(true);
		 // Create a sample listener and controller
        MyListener listener = new MyListener();
        Controller controller = new Controller();
        
        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);


		


	}

}
